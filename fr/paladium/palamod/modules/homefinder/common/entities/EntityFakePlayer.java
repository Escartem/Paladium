package fr.paladium.palamod.modules.homefinder.common.entities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationSaver;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationType;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.SearchStatus;
import fr.paladium.palamod.modules.homefinder.common.utils.HUtils;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFakePlayer extends EntityMob implements IEntityAdditionalSpawnData {
  public ResourceLocation playerSkin;
  
  private String targetName;
  
  private String targetUniqueId;
  
  private TileEntityHomeFinder homeFinder;
  
  private LocationSaver saved;
  
  private LocationType type;
  
  private Long despawnTimeMillis;
  
  public String getTargetName() {
    return this.targetName;
  }
  
  public String getTargetUniqueId() {
    return this.targetUniqueId;
  }
  
  public TileEntityHomeFinder getHomeFinder() {
    return this.homeFinder;
  }
  
  public LocationSaver getSaved() {
    return this.saved;
  }
  
  public LocationType getType() {
    return this.type;
  }
  
  public Long getDespawnTimeMillis() {
    return this.despawnTimeMillis;
  }
  
  public EntityFakePlayer(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
  }
  
  public EntityFakePlayer(String targetName, UUID targetUniqueId, TileEntityHomeFinder homeFinder, LocationSaver saved, Location location, World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.homeFinder = homeFinder;
    this.saved = saved;
    this.type = saved.getLocationType();
    this.targetUniqueId = targetUniqueId.toString();
    this.targetName = targetName;
    this.despawnTimeMillis = Long.valueOf(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2L));
    func_70107_b(location.getX(), location.getY(), location.getZ());
  }
  
  public String getDespawnTime() {
    return "§7(§c" + HUtils.formatTime(this.despawnTimeMillis.longValue() - System.currentTimeMillis()) + "§7)";
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(3.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(14, Byte.valueOf((byte)1));
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (this.field_70170_p.field_72995_K)
      return true; 
    ItemStack held = player.func_70694_bm();
    if (held == null)
      return true; 
    if (!isInGhostMode())
      return true; 
    if (held.func_77973_b() == PPRegisterer.PPItems.HOUSING_STONE) {
      initAttackMod(player);
    } else if (held.func_77973_b() instanceof ItemTool && ((ItemTool)held.func_77973_b()).func_150913_i().equals(PToolMaterial.endium)) {
      held.func_77972_a(100, (EntityLivingBase)player);
      if (held.func_77960_j() >= held.func_77958_k())
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
      initAttackMod(player);
    } 
    return true;
  }
  
  private void initAttackMod(EntityPlayer player) {
    setGhostMode(false);
    func_70784_b((Entity)player);
    this.homeFinder.setSearchStatus(SearchStatus.FIGHTING);
    HUtils.sendMessage(player, new String[] { String.format("§8[§6HomeFinder§8]§r §eLe combat contre le gardien §6%s §evient de §acommencer§e !", new Object[] { this.saved.getPlayerName() }) });
  }
  
  public boolean func_70097_a(DamageSource damageSource, float p_70097_2_) {
    if (func_85032_ar())
      return false; 
    if (isInGhostMode())
      return false; 
    return super.func_70097_a(damageSource, p_70097_2_);
  }
  
  public void func_70109_d(NBTTagCompound tagCompound) {
    super.func_70109_d(tagCompound);
    tagCompound.func_74778_a("PlayerName", this.targetName);
    tagCompound.func_74778_a("PlayerUUID", this.field_96093_i.toString());
    tagCompound.func_74768_a("LocationType", this.type.ordinal());
    tagCompound.func_74772_a("DespawnTime", this.despawnTimeMillis.longValue());
  }
  
  public void func_70020_e(NBTTagCompound tagCompound) {
    super.func_70020_e(tagCompound);
    this.targetName = tagCompound.func_74779_i("PlayerName");
    this.targetUniqueId = tagCompound.func_74779_i("PlayerUUID");
    this.type = LocationType.values()[tagCompound.func_74762_e("LocationType")];
    this.despawnTimeMillis = Long.valueOf(tagCompound.func_74763_f("DespawnTime"));
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.despawnTimeMillis != null && this.despawnTimeMillis.longValue() - System.currentTimeMillis() <= 0L) {
      func_70106_y();
      return;
    } 
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
    if (damageSource.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)damageSource.func_76346_g();
      String type = (this.saved.getLocationType() == LocationType.DISCONNECTION) ? "§edu point de §cdéconnexion" : "§edu §cHome";
      HUtils.sendMessage(player, new String[] { String.format("§8[§6HomeFinder§8]§r §eLe combat contre le gardien §6%s §ea été §agagné§e, suppression %s.", new Object[] { this.targetName, type }) });
      UseItemAchievement.performCheck(player, player.func_71045_bC(), "HOME_REMOVER_GHOST_KILL");
      this.homeFinder.replaceLocation(this.saved);
    } 
  }
  
  public boolean func_70601_bi() {
    return true;
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  protected Entity func_70782_k() {
    return isInGhostMode() ? null : super.func_70782_k();
  }
  
  public boolean func_85032_ar() {
    return isInGhostMode();
  }
  
  public boolean func_70104_M() {
    return !isInGhostMode();
  }
  
  protected void func_82167_n(Entity p_82167_1_) {
    if (!isInGhostMode())
      super.func_82167_n(p_82167_1_); 
  }
  
  protected void func_85033_bc() {
    if (!isInGhostMode())
      super.func_85033_bc(); 
  }
  
  protected boolean func_70780_i() {
    return isInGhostMode();
  }
  
  public ItemStack func_70694_bm() {
    return null;
  }
  
  public ItemStack func_71124_b(int slotID) {
    return null;
  }
  
  public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {}
  
  public ItemStack[] func_70035_c() {
    return new ItemStack[0];
  }
  
  public boolean isInGhostMode() {
    return (this.field_70180_af.func_75683_a(14) == 1);
  }
  
  public void setGhostMode(boolean ghostMode) {
    this.field_70180_af.func_75692_b(14, Byte.valueOf(ghostMode ? 1 : 0));
  }
  
  public ResourceLocation getPlayerSkin() {
    return (this.playerSkin != null) ? this.playerSkin : AbstractClientPlayer.field_110314_b;
  }
  
  public String func_70005_c_() {
    if (this.type == null)
      return "§eHomeFinder"; 
    String end = this.targetName + " " + getDespawnTime();
    if (this.type == LocationType.DISCONNECTION)
      return "§eDéconnexion de §c" + end; 
    return "§eHome de §c" + end;
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    try {
      LocationType type = (this.saved == null) ? LocationType.DISCONNECTION : this.saved.getLocationType();
      ByteBufUtils.writeUTF8String(buffer, this.targetName);
      ByteBufUtils.writeUTF8String(buffer, this.targetUniqueId);
      buffer.writeInt(type.ordinal());
      buffer.writeLong(this.despawnTimeMillis.longValue());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    try {
      this.targetName = ByteBufUtils.readUTF8String(additionalData);
      this.targetUniqueId = ByteBufUtils.readUTF8String(additionalData);
      this.type = LocationType.values()[additionalData.readInt()];
      this.despawnTimeMillis = Long.valueOf(additionalData.readLong());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\entities\EntityFakePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */