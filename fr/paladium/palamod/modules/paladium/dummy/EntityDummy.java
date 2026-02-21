package fr.paladium.palamod.modules.paladium.dummy;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.dummy.network.DamageMessage;
import fr.paladium.palamod.modules.paladium.dummy.network.SyncEquipmentMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor;

public class EntityDummy extends EntityLiving implements IEntityAdditionalSpawnData {
  private float customRotation;
  
  public float shake;
  
  public float shakeAnimation;
  
  public float field_110153_bc;
  
  public int lastDamageTick;
  
  public int firstDamageTick;
  
  public float damageTaken;
  
  public EntityFloatingNumber myLittleNumber;
  
  public EntityDummy(World world) {
    super(world);
    for (int i = 0; i < this.field_82174_bp.length; i++)
      this.field_82174_bp[i] = 1.1F; 
  }
  
  public void setPlacementRotation(Vec3 lookVector, int side) {
    int r = 0;
    if (side == 0 || side == 1) {
      r = (int)(Math.atan2(lookVector.field_72449_c, lookVector.field_72450_a) * 360.0D / 6.283185307179586D);
      r += 90;
    } else if (side == 2) {
      r = 180;
    } else if (side == 3) {
      r = 0;
    } else if (side == 4) {
      r = 90;
    } else if (side == 5) {
      r = 270;
    } 
    this.customRotation = r;
    setCustomRotationStuff();
  }
  
  private void setCustomRotationStuff() {
    float r = this.customRotation;
    this.field_70758_at = this.field_70759_as = r;
    this.field_70126_B = this.field_70177_z = r;
    this.field_70712_bm = r;
    this.field_70760_ar = this.field_70761_aq = r;
    this.field_70704_bt = 0.0F;
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    ItemStack stack = player.func_71045_bC();
    if (stack == null) {
      if (!player.func_70093_af())
        return false; 
      for (int j = 0; j < 4; ) {
        ItemStack armor = func_71124_b(4 - j);
        if (armor == null) {
          j++;
          continue;
        } 
        if (!this.field_70170_p.field_72995_K) {
          if (!player.field_71075_bZ.field_75098_d)
            func_70099_a(armor, 1.0F); 
          PalaMod.getNetwork().sendToAllAround((IMessage)new SyncEquipmentMessage(
                func_145782_y(), 4 - j, null), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 20.0D));
        } 
        func_70062_b(4 - j, null);
        return true;
      } 
      return false;
    } 
    Item item = stack.func_77973_b();
    for (int i = 0; i < 4; i++) {
      if (item.isValidArmor(stack, i, (Entity)player)) {
        ItemStack armor = func_71124_b(4 - i);
        if (armor != null && !this.field_70170_p.field_72995_K)
          func_70099_a(armor, 1.0F); 
        armor = stack.func_77946_l();
        armor.field_77994_a = 1;
        if (!this.field_70170_p.field_72995_K)
          PalaMod.getNetwork().sendToAllAround((IMessage)new SyncEquipmentMessage(
                func_145782_y(), 4 - i, armor), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 20.0D)); 
        func_70062_b(4 - i, armor);
        stack.field_77994_a--;
        return true;
      } 
    } 
    return false;
  }
  
  public void dismantle() {
    if (!this.field_70170_p.field_72995_K) {
      func_82160_b(true, 999);
      func_145779_a(ItemsRegister.itemDummy, 1);
    } 
    func_70106_y();
  }
  
  protected void func_70675_k(float p_70675_1_) {}
  
  protected float func_70672_c(DamageSource p_70672_1_, float p_70672_2_) {
    if (p_70672_1_.func_151517_h())
      return p_70672_2_; 
    if (func_70644_a(Potion.field_76429_m) && p_70672_1_ != DamageSource.field_76380_i) {
      int k = (func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
      int j = 50 - k;
      float f1 = p_70672_2_ * j;
      p_70672_2_ = f1 / 50.0F;
    } 
    if (p_70672_2_ <= 0.0F)
      return 0.0F; 
    int i = EnchantmentHelper.func_77508_a(func_70035_c(), p_70672_1_);
    if (i > 20)
      i = 20; 
    if (i > 0 && i <= 20) {
      int j = 50 - i;
      float f1 = p_70672_2_ * j;
      p_70672_2_ = f1 / 50.0F;
    } 
    return p_70672_2_;
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (source.field_76373_n.equals("player")) {
      EntityPlayer player = (EntityPlayer)source.func_76346_g();
      if (player.func_70093_af() && player.func_71045_bC() == null) {
        dismantle();
        return false;
      } 
    } 
    if (!func_85032_ar()) {
      damage = ForgeHooks.onLivingHurt((EntityLivingBase)this, source, damage);
      if (damage > 0.0F) {
        damage = ISpecialArmor.ArmorProperties.ApplyArmor((EntityLivingBase)this, func_70035_c(), source, damage);
        damage = func_70672_c(source, damage);
        float f1 = damage;
        damage = Math.max(damage - func_110139_bj(), 0.0F);
        func_110149_m(func_110139_bj() - f1 - damage);
      } 
    } 
    if (this.field_70172_ad > this.field_70771_an / 2.0F) {
      if (damage <= this.field_110153_bc)
        return false; 
      this.field_110153_bc = damage;
    } else {
      this.field_110153_bc = damage;
      this.field_70172_ad = this.field_70771_an;
    } 
    if (this.lastDamageTick == this.field_70173_aa) {
      this.field_110153_bc += damage;
      this.shake += damage;
      this.shake = Math.min(this.shake, 30.0F);
    } else {
      this.shake = Math.min(damage, 30.0F);
      this.field_110153_bc = damage;
      this.lastDamageTick = this.field_70173_aa;
    } 
    this.field_70737_aN = this.field_70738_aO = 10;
    if (!this.field_70170_p.field_72995_K) {
      if (this.myLittleNumber != null && !this.myLittleNumber.field_70128_L)
        this.myLittleNumber.func_70106_y(); 
      EntityFloatingNumber number = new EntityFloatingNumber(this.field_70170_p, damage, this.field_70165_t, this.field_70163_u + 2.0D, this.field_70161_v);
      this.myLittleNumber = number;
      this.field_70170_p.func_72838_d(number);
      PalaMod.getNetwork().sendToAllAround((IMessage)new DamageMessage(this.field_110153_bc, this.shake, this, this.myLittleNumber), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 20.0D));
      this.damageTaken += damage;
      if (this.firstDamageTick == 0)
        this.firstDamageTick = this.field_70173_aa; 
    } 
    return true;
  }
  
  protected void func_70619_bc() {}
  
  public void func_70071_h_() {
    if (this.shake > 0.0F) {
      this.shakeAnimation++;
      this.shake -= 0.8F;
      if (this.shake <= 0.0F) {
        this.shakeAnimation = 0.0F;
        this.shake = 0.0F;
      } 
    } 
    if (this.field_70737_aN > 0)
      this.field_70737_aN--; 
    if (this.field_70172_ad > 0)
      this.field_70172_ad--; 
    if (this.field_70170_p.field_72995_K)
      func_70066_B(); 
    if (!this.field_70170_p.field_72995_K && this.damageTaken > 0.0F && this.field_70173_aa - this.lastDamageTick > 30) {
      if (this.firstDamageTick < this.lastDamageTick) {
        float seconds = (this.lastDamageTick - this.firstDamageTick) / 20.0F + 1.0F;
        float dps = this.damageTaken / seconds;
        EntityFloatingNumber number = new EntityDpsFloatingNumber(this.field_70170_p, dps, this.field_70165_t, this.field_70163_u + 3.0D, this.field_70161_v);
        this.field_70170_p.func_72838_d(number);
      } 
      this.damageTaken = 0.0F;
      this.firstDamageTick = 0;
    } 
  }
  
  public void func_70030_z() {}
  
  protected boolean func_70610_aX() {
    return true;
  }
  
  public void func_70636_d() {}
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_70089_S() {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_70067_L() {
    return true;
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeFloat(this.customRotation);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.customRotation = additionalData.readFloat();
    setCustomRotationStuff();
  }
  
  public void func_70014_b(NBTTagCompound tag) {
    super.func_70014_b(tag);
    tag.func_74776_a("customRotation", this.customRotation);
    tag.func_74776_a("shake", this.shake);
  }
  
  public void func_70037_a(NBTTagCompound tag) {
    super.func_70037_a(tag);
    this.customRotation = tag.func_74760_g("customRotation");
    this.shake = tag.func_74760_g("shake");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\EntityDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */