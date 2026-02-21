package fr.paladium.palarpg.module.profile.client.ui.kit.hotbar;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.profile.client.ui.kit.shader.GrayscaleShader;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.stat.client.constant.RPGStatConstant;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class PlayerHudContainer extends Node {
  private static final TextInfo HEALTH_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE).shadow(0.0F, 1.0F).shadow(Color.WHITE.darker(0.6F));
  
  private static final Color BACKGROUND_BAR_COLOR = Color.decode("#282828");
  
  private static final Color BACKGROUND_RESISTANCE_BAR_COLOR = Color.decode("#BFC5D4");
  
  private static final Color FOREGROUND_HEALTH_BAR_COLOR = Color.decode("#FF3C3C");
  
  private final TweenAnimator healthAnimator = TweenAnimator.create(0.0F);
  
  private final TweenAnimator deathAnimator = TweenAnimator.create(0.0F);
  
  private final TweenAnimator deathIconAnimator = TweenAnimator.create(0.0F);
  
  private final FloatSignal dungeonExpSignal = new FloatSignal(0.0F);
  
  private final IntegerSignal dungeonLevelSignal = new IntegerSignal(0);
  
  private final FloatSignal jobExpSignal = new FloatSignal(0.0F);
  
  private final IntegerSignal jobLevelSignal = new IntegerSignal(0);
  
  private final Signal<JobType> jobTypeSignal = new Signal(null);
  
  private DungeonPlayer dungeonPlayer;
  
  private boolean sub = false;
  
  private float lastHealth = 0.0F;
  
  private boolean wasDead = false;
  
  private EntityPlayer player;
  
  protected PlayerHudContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static PlayerHudContainer create(double x, double y, double width, double height) {
    return new PlayerHudContainer(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.dungeonPlayer == null)
      return; 
    EntityPlayer onlinePlayer = this.dungeonPlayer.getOnlinePlayer().orElse(null);
    if (onlinePlayer != null)
      this.player = onlinePlayer; 
    if (this.player == null)
      return; 
    PlayerHudNode.create(0.0D, 0.0D, h())
      .dungeonPlayer(this.dungeonPlayer)
      .size(1.5D)
      .yaw(-40.0F)
      .attach(this);
    if (this.sub)
      return; 
    ContainerNode.create(-20.0D, 65.0D, 77.0D, 94.0D)
      .zlevel(1000.0D)
      .body(rect -> {
          ImageNode.create(0.0D, 0.0D, 77.0D, 94.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/exp/empty_background.png")).linear()).attach(rect);
          ImageNode.create(0.0D, 0.0D, 77.0D, 94.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/exp/fill.png")).linear()).effect(()).attach(rect);
          ImageNode.create(0.0D, 0.0D, 77.0D, 94.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/exp/fill.png")).linear()).effect(()).attach(rect);
          ImageNode.create(0.0D, 0.0D, 77.0D, 94.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/exp/empty.png")).linear()).body(()).attach(rect);
        }).watch((Signal)this.dungeonLevelSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.dungeonExpSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
    ContainerNode.create(-36.0D, 30.0D, 44.0D, 54.0D)
      .zlevel(1000.0D)
      .body(rect -> {
          if (this.jobTypeSignal.getOrDefault() == null)
            return; 
          ImageNode.create(0.0D, 0.0D, 44.0D, 54.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/job/empty_background.png")).linear()).attach(rect);
          ImageNode.create(0.0D, 0.0D, 44.0D, 54.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/job/fill.png")).linear()).effect(()).attach(rect);
          ImageNode.create(0.0D, 0.0D, 44.0D, 54.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/job/fill.png")).linear()).effect(()).attach(rect);
          ImageNode.create(0.0D, 0.0D, 44.0D, 54.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/finish/job/empty.png")).linear()).body(()).attach(rect);
        }).watch(this.jobTypeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.jobLevelSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.jobExpSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.dungeonPlayer == null)
      return; 
    EntityPlayer onlinePlayer = this.dungeonPlayer.getOnlinePlayer().orElse(null);
    if (onlinePlayer != null)
      this.player = onlinePlayer; 
    if (this.player == null)
      return; 
    if (RPGPlayer.getData((Entity)this.player, "profile") != null) {
      RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)this.player, "profile");
      this.dungeonLevelSignal.set(Integer.valueOf(profile.getLevel()));
      this.dungeonExpSignal.set(Float.valueOf((float)profile.getProgressionPercentage()));
    } 
    int level = this.dungeonPlayer.getWorld().getLevel();
    Optional<DungeonLevelConfig> optLevelConfig = (DungeonWorld.getClient().isPresent() && ((DungeonWorld)DungeonWorld.getClient().get()).getDungeon() != null) ? ((DungeonWorld)DungeonWorld.getClient().get()).getDungeon().getLevel(level + 1) : Optional.<DungeonLevelConfig>empty();
    optLevelConfig.ifPresent(lvlConfig -> {
          if (lvlConfig.getRewards() != null && lvlConfig.getRewards().getJobs() != null)
            this.jobTypeSignal.set(JobType.valueOf(lvlConfig.getRewards().getJobs().getJob())); 
        });
    if (this.jobTypeSignal.getOrDefault() != null)
      PalaJobsAPI.inst().getJobsPlayer((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g).ifPresent(jobPlayer -> {
            this.jobLevelSignal.set(Integer.valueOf(jobPlayer.getLevel((JobType)this.jobTypeSignal.getOrDefault())));
            double jobExp = jobPlayer.getExperience((JobType)this.jobTypeSignal.getOrDefault()) - JobExpUtils.getNeededXpForLvl(((Integer)this.jobLevelSignal.getOrDefault()).intValue());
            double jobNextLevelExp = (JobExpUtils.getNeededXpForLvl(((Integer)this.jobLevelSignal.getOrDefault()).intValue() + 1) - JobExpUtils.getNeededXpForLvl(((Integer)this.jobLevelSignal.getOrDefault()).intValue()));
            if (jobExp > jobNextLevelExp)
              jobExp = jobNextLevelExp; 
            this.jobExpSignal.set(Float.valueOf((float)(jobExp / jobNextLevelExp)));
          }); 
    this.healthAnimator.update();
    this.deathAnimator.update();
    this.deathIconAnimator.update();
    double x = getX() + ah(20.0D);
    double width = aw(-(20.0D + h()));
    DrawUtils.TEXT.drawText(x, getY() + -16.0D, Text.create(this.player.func_70005_c_(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, this.sub ? 24.0F : 40.0F, Color.WHITE)));
    if (RPGPlayer.getData((Entity)this.player, "stats") == null)
      return; 
    boolean djAlive = this.dungeonPlayer.isAlive();
    if (this.wasDead == djAlive) {
      this.wasDead = !djAlive;
      this.deathAnimator.clear();
      this.deathAnimator.setValue(this.wasDead ? 0.0F : 1.0F);
      this.deathAnimator.sequence(500.0F, this.wasDead ? 1.0F : 0.0F).start();
      this.deathIconAnimator.clear();
      this.deathIconAnimator.setValue(this.wasDead ? 0.0F : 1.0F);
      this.deathIconAnimator.sequence(500.0F, this.wasDead ? 1.0F : 0.0F, this.wasDead ? (TweenEquation)TweenEquations.BACK_OUT : (TweenEquation)TweenEquations.BACK_IN).start();
    } 
    GLHelper.pushMatrix();
    double deathIconSize = h() * 0.65D;
    double scale = this.deathIconAnimator.getValue();
    GLHelper.translate(getX() + dh(2.0D), getY() + dh(2.0D));
    GLHelper.scale(scale, scale);
    GLHelper.rotateZ(30.0D * (1.0D - scale));
    GLHelper.translate(-(getX() + dh(2.0D)), -(getY() + dh(2.0D)));
    GLHelper.translate(0.0D, 0.0D, 10.0D);
    DrawUtils.RESOURCE.drawImage(getX() + dh(2.0D) - deathIconSize / 2.0D, getY() + dh(2.0D) - deathIconSize / 2.0D, deathIconSize, deathIconSize, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/death.png")).nearest());
    GLHelper.popMatrix();
    DrawUtils.SHAPE.drawBorder(x, getY() + ah(-70.0D), x + width, getY() + ah(-47.0D), Color.BLACK, 3.0D);
    DrawUtils.SHAPE.drawRect(x, getY() + ah(-70.0D), width, 23.0D, Color.BLACK);
    DrawUtils.SHAPE.drawRect(x + 46.0D, getY() + ah(-70.0D) + 4.0D, width - 50.0D, 15.0D, BACKGROUND_RESISTANCE_BAR_COLOR);
    DrawUtils.SHAPE.drawBorder(x + 46.0D, getY() + ah(-70.0D) + 4.0D, x + 46.0D + width - 50.0D, getY() + ah(-70.0D) + 4.0D + 15.0D, BACKGROUND_RESISTANCE_BAR_COLOR, 4.0D);
    DrawUtils.SHAPE.drawBorder(x, getY() + ah(-32.0D), x + width, getY() + h(), Color.BLACK, 3.0D);
    DrawUtils.SHAPE.drawRect(x, getY() + ah(-32.0D), width, 32.0D, Color.BLACK);
    GrayscaleShader.use(this.deathAnimator.getValue(), () -> {
          DrawUtils.RESOURCE.drawImage(x + 9.0D, getY() + ah(-70.0D) + 1.0D, 22.0D, 22.0D, RPGStatConstant.getIcon(EnumStats.RESISTANCE));
          DrawUtils.RESOURCE.drawImage(x + 8.0D, getY() + ah(-32.0D) + 5.0D, 24.0D, 24.0D, RPGStatConstant.getIcon(EnumStats.MAX_HEALTH));
        });
    String resistance = String.format("%.0f", new Object[] { Float.valueOf(StatsManager.RESISTANCE.getResistance((EntityLivingBase)this.player)) });
    DrawUtils.TEXT.drawText(x + 56.0D, getY() + ah(-70.0D) + 11.5D - HEALTH_INFO.getHeight("/") / 2.0D, Text.create("DÉFENSE", HEALTH_INFO));
    DrawUtils.TEXT.drawText(x + 40.0D + width - 50.0D, getY() + ah(-70.0D) + 11.5D - HEALTH_INFO.getHeight("/") / 2.0D, resistance, HEALTH_INFO, Align.END, Align.START);
    double healthWidth = width - 50.0D;
    double height = 24.0D;
    double currentHealth = !djAlive ? 0.0D : Math.ceil(this.player.func_110143_aJ());
    String healthDisplay = String.format("%.0f", new Object[] { Double.valueOf(currentHealth) }) + "/" + String.format("%.0f", new Object[] { Double.valueOf(Math.ceil(this.player.func_110138_aP())) });
    float healthPercent = !djAlive ? 0.0F : (this.player.func_110143_aJ() / this.player.func_110138_aP());
    DrawUtils.SHAPE.drawBorder(x + 46.0D, getY() + ah(-24.0D) - 4.0D, x + 46.0D + healthWidth, getY() + ah(-24.0D) - 4.0D + 24.0D, BACKGROUND_BAR_COLOR, 4.0D);
    DrawUtils.SHAPE.drawRect(x + 46.0D, getY() + ah(-24.0D) - 4.0D, healthWidth, 24.0D, BACKGROUND_BAR_COLOR);
    if (this.lastHealth != healthPercent) {
      this.healthAnimator.clear();
      this.healthAnimator.setValue(this.lastHealth);
      this.healthAnimator.sequence(250.0F, healthPercent).start();
      this.lastHealth = healthPercent;
    } 
    getUi().mask(x + 46.0D - 4.0D, getY() + ah(-24.0D) - 8.0D, (healthWidth + 8.0D) * this.healthAnimator.getValue(), 32.0D, () -> {
          DrawUtils.SHAPE.drawBorder(x + 46.0D, getY() + ah(-24.0D) - 4.0D, x + 46.0D + healthWidth, getY() + ah(-24.0D) - 4.0D + 24.0D, FOREGROUND_HEALTH_BAR_COLOR, 4.0D);
          DrawUtils.SHAPE.drawRect(x + 46.0D, getY() + ah(-24.0D) - 4.0D, healthWidth, 24.0D, FOREGROUND_HEALTH_BAR_COLOR);
        });
    DrawUtils.TEXT.drawText(x + 56.0D, getY() + ah(-24.0D) - 4.0D + 12.0D - HEALTH_INFO.getHeight("VIE") / 2.0D, Text.create("VIE", HEALTH_INFO));
    DrawUtils.TEXT.drawText(x + 40.0D + healthWidth, getY() + ah(-24.0D) - 4.0D + 12.0D - HEALTH_INFO.getHeight("/") / 2.0D, healthDisplay, HEALTH_INFO, Align.END, Align.START);
  }
  
  public PlayerHudContainer dungeonPlayer(@NonNull DungeonPlayer djPlayer) {
    if (djPlayer == null)
      throw new NullPointerException("djPlayer is marked non-null but is null"); 
    this.dungeonPlayer = djPlayer;
    return this;
  }
  
  public PlayerHudContainer isSub(boolean isSub) {
    this.sub = isSub;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\hotbar\PlayerHudContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */