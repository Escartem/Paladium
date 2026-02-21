package fr.paladium.palarpg.module.dungeon.client.ui.finish;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.slot.FakeSlotNode;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.client.ui.utils.DungeonSynchronizedUI;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.utils.RomanNumberFormatter;
import fr.paladium.palarpg.module.dungeon.common.world.generator.DungeonGenerator;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.modifier.GLScale;
import fr.paladium.zephyrui.lib.opengl.modifier.GLVector;
import fr.paladium.zephyrui.lib.opengl.transform.glto.GLTOScaling;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.TransformNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@UIData(background = false)
public class UIDungeonFinish extends DungeonSynchronizedUI {
  private final boolean success;
  
  private final UIDungeonFinishData data;
  
  private final TweenAnimator cardAnimator;
  
  private final TweenAnimator slideAnimator;
  
  private final TweenAnimator revealAnimator;
  
  public UIDungeonFinish(@NonNull Boolean success, @NonNull UIDungeonFinishData data) {
    if (success == null)
      throw new NullPointerException("success is marked non-null but is null"); 
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    setMain(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g));
    this.success = success.booleanValue();
    this.data = data;
    this.cardAnimator = TweenAnimator.create(0.0F);
    this.slideAnimator = TweenAnimator.create(0.0F);
    this.revealAnimator = TweenAnimator.create(0.0F);
  }
  
  public void init() {
    super.init();
    int pathCount = (ForgeEnv.isIDE() && this.data.paths.size() < 5) ? 10 : this.data.paths.size();
    this.cardAnimator.setValue(0.0F);
    this.slideAnimator.setValue(0.0F);
    this.revealAnimator.setValue(0.0F);
    schedule(() -> {
          this.slideAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
          schedule((), 300L);
        }1500L);
    Color color = this.success ? Color.decode("#00E397") : Color.decode("#E13429");
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          BackgroundNode.create().attach(container);
          ContainerNode.create(0.0D, 0.0D, container.w(), container.h()).body(()).animate(this.cardAnimator).attach(container);
          ContainerNode.create(0.0D, 411.0D, 1920.0D, 204.0D).body(()).onAnimate(()).animate(this.slideAnimator).zlevel(1000.0D).attach(container);
          TextButtonNode.create(container.dw(2.0D), 963.0D).text("retour au hub").onClick(()).effect(()).animate(this.revealAnimator).anchorX(Align.CENTER).attach(container);
        }).attach((UI)this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR);
  }
  
  public boolean close() {
    return false;
  }
  
  private String formatBigNumber(double value) {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.FRANCE);
    symbols.setDecimalSeparator(',');
    symbols.setGroupingSeparator('.');
    if (value >= 1000.0D) {
      DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
      return decimalFormat.format(Math.floor(value));
    } 
    double truncated = Math.floor(value * 100.0D) / 100.0D;
    if (truncated == (long)truncated)
      return String.valueOf((long)truncated); 
    DecimalFormat df = new DecimalFormat("0.##", symbols);
    return df.format(truncated);
  }
  
  public static class UIDungeonFinishData {
    private final DungeonConfig dungeon;
    
    private final List<DungeonGenerator.DungeonRoomPath> paths;
    
    private final Map<String, RPGItemRarity> rawItems;
    
    private final long time;
    
    private final int jobLevel;
    
    private final JobType jobType;
    
    private final double jobExperience;
    
    private final double jobProgress;
    
    private final double dungeonExperience;
    
    private final int level;
    
    private transient Set<RPGDungeonPlayerData.RPGDungeonItem> items;
    
    public UIDungeonFinishData(DungeonConfig dungeon, List<DungeonGenerator.DungeonRoomPath> paths, Map<String, RPGItemRarity> rawItems, long time, int jobLevel, JobType jobType, double jobExperience, double jobProgress, double dungeonExperience, int level) {
      this.dungeon = dungeon;
      this.paths = paths;
      this.rawItems = rawItems;
      this.time = time;
      this.jobLevel = jobLevel;
      this.jobType = jobType;
      this.jobExperience = jobExperience;
      this.jobProgress = jobProgress;
      this.dungeonExperience = dungeonExperience;
      this.level = level;
    }
    
    public DungeonConfig getDungeon() {
      return this.dungeon;
    }
    
    public List<DungeonGenerator.DungeonRoomPath> getPaths() {
      return this.paths;
    }
    
    public Map<String, RPGItemRarity> getRawItems() {
      return this.rawItems;
    }
    
    public long getTime() {
      return this.time;
    }
    
    public int getJobLevel() {
      return this.jobLevel;
    }
    
    public JobType getJobType() {
      return this.jobType;
    }
    
    public double getJobExperience() {
      return this.jobExperience;
    }
    
    public double getJobProgress() {
      return this.jobProgress;
    }
    
    public double getDungeonExperience() {
      return this.dungeonExperience;
    }
    
    public int getLevel() {
      return this.level;
    }
    
    public Set<RPGDungeonPlayerData.RPGDungeonItem> getItems() {
      if (this.items != null)
        return this.items; 
      this.items = new LinkedHashSet<>();
      for (Map.Entry<String, RPGItemRarity> entry : this.rawItems.entrySet()) {
        RPGDungeonPlayerData.RPGDungeonItem item = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), "default", ItemStackSerializer.read64(entry.getKey()), entry.getValue());
        this.items.add(item);
      } 
      return this
        
        .items = (Set<RPGDungeonPlayerData.RPGDungeonItem>)this.items.stream().sorted((o1, o2) -> {
            int rarityCompare = o2.getRarity().compareTo((Enum)o1.getRarity());
            if (rarityCompare != 0)
              return rarityCompare; 
            int nameCompare = o1.getItem().func_82833_r().compareToIgnoreCase(o2.getItem().func_82833_r());
            return (nameCompare != 0) ? nameCompare : Integer.compare((o2.getItem()).field_77994_a, (o1.getItem()).field_77994_a);
          }).collect(Collectors.toCollection(LinkedHashSet::new));
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\finish\UIDungeonFinish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */