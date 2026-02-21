package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.UUID;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFakePlayer extends EntityMob {
  private ItemStack item;
  
  public EntityFakePlayer(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
  }
  
  public EntityFakePlayer(World world, UUID cloneUUID, String cloneName, String pathSkin, double x, double y, double z) {
    super(world);
    setCloneUUID(cloneUUID);
    setCloneName(cloneName);
    func_70107_b(x, y + 2.0D, z);
    setCloneSkin(pathSkin);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(12, "");
    this.field_70180_af.func_75682_a(13, "Unknown");
    this.field_70180_af.func_75682_a(15, "textures/entity/steve.png");
  }
  
  public void func_70784_b(Entity p_70784_1_) {}
  
  public boolean func_70097_a(DamageSource damageSource, float p_70097_2_) {
    if (func_85032_ar())
      return false; 
    if (this.item == null)
      return false; 
    return super.func_70097_a(damageSource, p_70097_2_);
  }
  
  public void func_70109_d(NBTTagCompound tagCompound) {
    super.func_70109_d(tagCompound);
    tagCompound.func_74778_a("PlayerUUID", getCloneUUID().toString());
    tagCompound.func_74778_a("PlayerName", "");
    tagCompound.func_74778_a("PlayerSkin", getCloneSkin());
    if (this.item != null)
      tagCompound.func_74782_a("item", (NBTBase)this.item.func_77955_b(new NBTTagCompound())); 
  }
  
  public void func_70020_e(NBTTagCompound tagCompound) {
    super.func_70020_e(tagCompound);
    setCloneUUID(UUID.fromString(tagCompound.func_74779_i("PlayerUUID")));
    setCloneName(tagCompound.func_74779_i("PlayerName"));
    setCloneSkin(tagCompound.func_74779_i("PlayerSkin"));
    if (this.item != null)
      this.item = ItemStack.func_77949_a(tagCompound.func_74775_l("item")); 
  }
  
  public void func_70645_a(DamageSource damageSource) {
    if (!this.field_70170_p.field_72995_K)
      if (this.item != null)
        PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, this.item);  
    super.func_70645_a(damageSource);
  }
  
  public boolean func_70685_l(Entity p_70685_1_) {
    return false;
  }
  
  protected Entity func_70782_k() {
    return null;
  }
  
  public boolean func_70601_bi() {
    return true;
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public UUID getCloneUUID() {
    return UUID.fromString(this.field_70180_af.func_75681_e(12));
  }
  
  public void setCloneUUID(UUID uuid) {
    this.field_70180_af.func_75692_b(12, uuid.toString());
  }
  
  public String getCloneName() {
    return this.field_70180_af.func_75681_e(13);
  }
  
  public void setCloneName(String name) {
    this.field_70180_af.func_75692_b(13, name);
  }
  
  public String getCloneSkin() {
    return this.field_70180_af.func_75681_e(15);
  }
  
  public void setCloneSkin(String name) {
    this.field_70180_af.func_75692_b(15, name);
  }
  
  public ResourceLocation getPlayerSkin() {
    return (getCloneSkin() != null) ? new ResourceLocation(getCloneSkin()) : AbstractClientPlayer.field_110314_b;
  }
  
  public String func_70005_c_() {
    return "";
  }
  
  public void setItemDeath(ItemStack item) {
    this.item = item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityFakePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */