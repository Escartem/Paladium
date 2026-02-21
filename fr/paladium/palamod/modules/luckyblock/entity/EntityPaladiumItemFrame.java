package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityPaladiumItemFrame extends EntityItemFrame implements IEntityAdditionalSpawnData {
  private float itemDropChance = 1.0F;
  
  private static final String __OBFID = "CL_00001547";
  
  public EntityPaladiumItemFrame(World p_i1590_1_) {
    super(p_i1590_1_);
  }
  
  public EntityPaladiumItemFrame(World p_i1591_1_, int p_i1591_2_, int p_i1591_3_, int p_i1591_4_, int p_i1591_5_) {
    super(p_i1591_1_, p_i1591_2_, p_i1591_3_, p_i1591_4_, p_i1591_5_);
    func_82328_a(p_i1591_5_);
  }
  
  protected void func_70088_a() {
    func_70096_w().func_82709_a(2, 5);
    func_70096_w().func_75682_a(3, Byte.valueOf((byte)0));
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (func_85032_ar())
      return false; 
    if (func_82335_i() != null) {
      if (!this.field_70170_p.field_72995_K) {
        func_146065_b(p_70097_1_.func_76346_g(), false);
        func_82334_a((ItemStack)null);
      } 
      return true;
    } 
    return super.func_70097_a(p_70097_1_, p_70097_2_);
  }
  
  public int func_82329_d() {
    return 9;
  }
  
  public int func_82330_g() {
    return 9;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double p_70112_1_) {
    double d1 = 16.0D;
    d1 *= 64.0D * this.field_70155_l;
    return (p_70112_1_ < d1 * d1);
  }
  
  public void func_110128_b(Entity p_110128_1_) {
    func_146065_b(p_110128_1_, true);
  }
  
  public void func_146065_b(Entity p_146065_1_, boolean p_146065_2_) {
    ItemStack itemstack = func_82335_i();
    if (p_146065_1_ instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)p_146065_1_;
      if (entityplayer.field_71075_bZ.field_75098_d) {
        removeFrameFromMap(itemstack);
        return;
      } 
    } 
    if (p_146065_2_)
      func_70099_a(new ItemStack(ItemsRegister.PALA_ITEM_FRAME), 0.0F); 
  }
  
  private void removeFrameFromMap(ItemStack p_110131_1_) {
    if (p_110131_1_ != null && 
      p_110131_1_.func_77973_b() == Items.field_151098_aY) {
      MapData mapdata = ((ItemMap)p_110131_1_.func_77973_b()).func_77873_a(p_110131_1_, this.field_70170_p);
      mapdata.field_76203_h.remove("frame-" + func_145782_y());
    } 
  }
  
  public ItemStack func_82335_i() {
    return func_70096_w().func_82710_f(2);
  }
  
  public void func_82334_a(ItemStack p_82334_1_) {
    if (p_82334_1_ != null) {
      p_82334_1_ = p_82334_1_.func_77946_l();
      p_82334_1_.field_77994_a = 1;
    } 
    func_70096_w().func_75692_b(2, p_82334_1_);
    func_70096_w().func_82708_h(2);
  }
  
  public int func_82333_j() {
    return func_70096_w().func_75683_a(3);
  }
  
  public void func_82336_g(int p_82336_1_) {
    func_70096_w().func_75692_b(3, Byte.valueOf((byte)(p_82336_1_ % 4)));
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    if (func_82335_i() != null) {
      p_70014_1_.func_74782_a("Item", (NBTBase)func_82335_i().func_77955_b(new NBTTagCompound()));
      p_70014_1_.func_74774_a("ItemRotation", (byte)func_82333_j());
      p_70014_1_.func_74776_a("ItemDropChance", this.itemDropChance);
    } 
    super.func_70014_b(p_70014_1_);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    NBTTagCompound nbttagcompound1 = p_70037_1_.func_74775_l("Item");
    if (nbttagcompound1 != null && !nbttagcompound1.func_82582_d()) {
      func_82334_a(ItemStack.func_77949_a(nbttagcompound1));
      func_82336_g(p_70037_1_.func_74771_c("ItemRotation"));
      if (p_70037_1_.func_150297_b("ItemDropChance", 99))
        this.itemDropChance = p_70037_1_.func_74760_g("ItemDropChance"); 
    } 
    super.func_70037_a(p_70037_1_);
  }
  
  public boolean func_130002_c(EntityPlayer p_130002_1_) {
    if (func_82335_i() == null) {
      ItemStack itemstack = p_130002_1_.func_70694_bm();
      if (itemstack != null && !this.field_70170_p.field_72995_K)
        func_82334_a(itemstack); 
    } else if (!this.field_70170_p.field_72995_K) {
      func_82336_g(func_82333_j() + 1);
    } 
    return true;
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(this.field_146063_b);
    buffer.writeInt(this.field_146064_c);
    buffer.writeInt(this.field_146062_d);
    buffer.writeInt(this.field_82332_a);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.field_146063_b = additionalData.readInt();
    this.field_146064_c = additionalData.readInt();
    this.field_146062_d = additionalData.readInt();
    this.field_82332_a = additionalData.readInt();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityPaladiumItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */