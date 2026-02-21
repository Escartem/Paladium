package fr.paladium.palawarzoneevent.module.largage.common.entity;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.FactionEloChangeReason;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palawarzoneevent.module.largage.LargageModule;
import fr.paladium.palawarzoneevent.module.largage.api.event.LargageRewardEvent;
import fr.paladium.palawarzoneevent.module.largage.server.config.LargageConfig;
import fr.paladium.palawarzoneevent.module.largage.server.manager.LargageEventManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.entity.animation.Animation;
import software.bernie.geckolib3.entity.animation.AnimationType;
import software.bernie.geckolib3.entity.impl.AnimatedEntityLiving;

public class EntityLargage extends AnimatedEntityLiving {
  public void setInvinsible(boolean invinsible) {
    this.invinsible = invinsible;
  }
  
  public void setSpawned(boolean spawned) {
    this.spawned = spawned;
  }
  
  public void setEventLinked(boolean eventLinked) {
    this.eventLinked = eventLinked;
  }
  
  public void setRewards(List<LargageConfig.ItemReward> rewards) {
    this.rewards = rewards;
  }
  
  public void setMinNbRewards(int minNbRewards) {
    this.minNbRewards = minNbRewards;
  }
  
  public void setMaxNbRewards(int maxNbRewards) {
    this.maxNbRewards = maxNbRewards;
  }
  
  private boolean invinsible = true;
  
  private boolean spawned = false;
  
  private boolean eventLinked = true;
  
  private List<LargageConfig.ItemReward> rewards;
  
  private int minNbRewards;
  
  private int maxNbRewards;
  
  public EntityLargage(World world) {
    super(world);
    func_70105_a(1.0F, 1.0F);
    setDefaultAnimation(AnimationType.DEATH, new Animation[] { new Animation("open", 3500L) });
    getAnimated().setAnimationTransition(0.0F);
    this.field_98038_p = true;
    this.field_70158_ak = true;
    this.field_70771_an = 10;
  }
  
  public EntityLargage(World world, double x, double y, double z) {
    super(world, x, y, z);
    func_70105_a(1.0F, 1.0F);
    setDefaultAnimation(AnimationType.DEATH, new Animation[] { new Animation("open", 3500L) });
    getAnimated().setAnimationTransition(0.0F);
    this.field_98038_p = true;
    this.field_70158_ak = true;
    this.field_70771_an = 10;
  }
  
  public void func_70071_h_() {
    if (!this.spawned && this.field_70170_p.field_72995_K) {
      playAnimation("animation.model.new", 1100L, false);
      this.spawned = true;
      return;
    } 
    if (!this.field_70170_p.field_72995_K && this.eventLinked && LargageEventManager.inst().getRunningSpawnPoint() == null) {
      func_70106_y();
      return;
    } 
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    if (!this.field_70122_E || this.field_70143_R > 0.0F)
      this.field_70181_x = -0.08D; 
    super.func_70071_h_();
    this.field_70177_z = 0.0F;
    this.field_70126_B = 0.0F;
    this.field_70125_A = 0.0F;
    this.field_70127_C = 0.0F;
    this.field_70759_as = 0.0F;
    this.field_70758_at = 0.0F;
    this.field_70761_aq = 0.0F;
    this.field_70760_ar = 0.0F;
    if (this.field_70170_p.field_72995_K || this.field_70128_L)
      return; 
    if (this.field_70143_R <= 0.0F && this.field_70121_D != null && this.field_70173_aa % 20 == 0) {
      double checkRadius = LargageModule.getServer().getConfig().getInvulnerabilityFactionThreshold();
      if (checkRadius <= 0.0D) {
        this.invinsible = false;
        return;
      } 
      AxisAlignedBB detectionZone = this.field_70121_D.func_72329_c().func_72314_b(checkRadius, 2.0D, checkRadius);
      Set<IFaction> allFactions = new HashSet<>();
      PlayerSelector.AREA(detectionZone).apply(player -> {
            IPlayer iPlayer = FactionAPI.getInstance().getPlayer((EntityPlayer)player);
            if (iPlayer == null || !iPlayer.hasFaction())
              return; 
            if (!allFactions.isEmpty()) {
              List<IFaction> factions = new ArrayList<>(allFactions);
              factions.removeIf(());
              for (int i = 0; i < factions.size(); i++) {
                IFaction otherFac = factions.get(i);
                if (!iPlayer.getFaction().getRelationshipEntity().getFactionAllies().contains(otherFac.getUuid()) && !iPlayer.getFaction().getRelationshipEntity().getFactionTruces().contains(otherFac.getUuid())) {
                  allFactions.add(iPlayer.getFaction());
                  break;
                } 
              } 
              return;
            } 
            allFactions.add(iPlayer.getFaction());
          });
      this
        .invinsible = (allFactions.size() >= LargageModule.getServer().getConfig().getInvulnerabilityFactionThreshold());
    } 
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    super.func_70014_b(compound);
    compound.func_74757_a("invinsible", this.invinsible);
    compound.func_74757_a("spawned", this.spawned);
    compound.func_74757_a("eventLinked", this.eventLinked);
    compound.func_74768_a("minNbRewards", this.minNbRewards);
    compound.func_74768_a("maxNbRewards", this.maxNbRewards);
    NBTTagList tagList = new NBTTagList();
    if (this.rewards != null && !this.rewards.isEmpty())
      this.rewards.forEach(itemReward -> {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.func_74778_a("item", itemReward.getItem());
            nbt.func_74768_a("weight", itemReward.getWeight());
            tagList.func_74742_a((NBTBase)nbt);
          }); 
    compound.func_74782_a("itemRewards", (NBTBase)tagList);
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    super.func_70037_a(compound);
    this.invinsible = compound.func_74767_n("invinsible");
    this.spawned = compound.func_74767_n("spawned");
    this.eventLinked = compound.func_74767_n("eventLinked");
    this.minNbRewards = compound.func_74762_e("minNbRewards");
    this.maxNbRewards = compound.func_74762_e("maxNbRewards");
    if (compound.func_74764_b("itemRewards")) {
      this.rewards = new ArrayList<>();
      NBTTagList list = compound.func_150295_c("itemRewards", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound nbtAt = list.func_150305_b(i);
        this.rewards.add(new LargageConfig.ItemReward(nbtAt.func_74779_i("item"), nbtAt.func_74762_e("weight")));
      } 
    } 
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (source.func_76352_a())
      return false; 
    if (!this.field_70170_p.field_72995_K && this.field_70143_R <= 0.0F && func_85032_ar() && source.func_76346_g() instanceof EntityPlayer)
      ((EntityPlayer)source.func_76346_g()).func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§cVous ne pouvez pas taper le largage tant qu'il y a des ennemis à votre faction autour de lui")); 
    return super.func_70097_a(source, damage);
  }
  
  public void func_70645_a(DamageSource source) {
    super.func_70645_a(source);
    if (this.field_70170_p.field_72995_K)
      return; 
    if (this.eventLinked) {
      LargageEventManager.inst().onLargageDeath(func_110124_au());
      List<LargageConfig.ItemReward> reward = LargageModule.getServer().getConfig().getRewardItems();
      int minItemDrop = LargageModule.getServer().getConfig().getMinItemDrop();
      int maxItemDrop = LargageModule.getServer().getConfig().getMaxItemDrop();
      LargageEventManager.inst().rollRewards(reward, minItemDrop, maxItemDrop).forEach(item -> ItemUtils.spawnItemAtEntity((Entity)this, item));
      if (source.func_76346_g() instanceof EntityPlayerMP) {
        EntityPlayer killer = (EntityPlayer)source.func_76346_g();
        MinecraftForge.EVENT_BUS.post((Event)new LargageRewardEvent((EntityPlayerMP)killer));
        LargageModule.getServer().getConfig().executeCommandRewards(killer);
        if (LargageModule.getServer().getConfig().getRewardElo() <= 0)
          return; 
        IPlayer factionPlayer = FactionAPI.getInstance().getPlayer(killer);
        if (factionPlayer == null || !factionPlayer.hasFaction())
          return; 
        factionPlayer.getFaction().getLevelEntity().addElo(LargageModule.getServer().getConfig().getRewardElo(), FactionEloChangeReason.EVENT_LARGAGE);
      } 
      return;
    } 
    if (this.rewards == null || this.rewards.isEmpty()) {
      List<LargageConfig.ItemReward> reward = LargageModule.getServer().getConfig().getRewardItems();
      int minItemDrop = LargageModule.getServer().getConfig().getMinItemDrop();
      int maxItemDrop = LargageModule.getServer().getConfig().getMaxItemDrop();
      LargageEventManager.inst().rollRewards(reward, minItemDrop, maxItemDrop).forEach(item -> ItemUtils.spawnItemAtEntity((Entity)this, item));
      return;
    } 
    if (this.minNbRewards >= 0 && this.maxNbRewards > 0)
      LargageEventManager.inst().rollRewards(this.rewards, this.minNbRewards, this.maxNbRewards).forEach(item -> ItemUtils.spawnItemAtEntity((Entity)this, item)); 
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  protected void func_70069_a(float damage) {}
  
  protected void func_110147_ax() {
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111267_a);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111266_c);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111263_d);
    if (!this.field_70170_p.field_72995_K) {
      double largageMaxHealth = LargageModule.getServer().getConfig().getLargageMaxHealth();
      func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(largageMaxHealth);
      func_70606_j((float)largageMaxHealth);
    } 
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData livingdata) {
    return livingdata;
  }
  
  public boolean func_85032_ar() {
    return this.invinsible;
  }
  
  protected void func_70623_bb() {}
  
  public boolean func_90999_ad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\common\entity\EntityLargage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */