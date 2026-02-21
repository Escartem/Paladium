package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.network.ArmourStandInteractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArmourStand extends EntityLiving {
  private static final Rotations DEFAULT_HEAD_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
  
  private static final Rotations DEFAULT_BODY_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
  
  private static final Rotations DEFAULT_LEFTARM_ROTATION = new Rotations(-10.0F, 0.0F, -10.0F);
  
  private static final Rotations DEFAULT_RIGHTARM_ROTATION = new Rotations(-15.0F, 0.0F, 10.0F);
  
  private static final Rotations DEFAULT_LEFTLEG_ROTATION = new Rotations(-1.0F, 0.0F, -1.0F);
  
  private static final Rotations DEFAULT_RIGHTLEG_ROTATION = new Rotations(1.0F, 0.0F, 1.0F);
  
  private boolean canInteract;
  
  private long punchCooldown;
  
  private Rotations headRotation;
  
  private Rotations bodyRotation;
  
  private Rotations leftArmRotation;
  
  private Rotations rightArmRotation;
  
  private Rotations leftLegRotation;
  
  private Rotations rightLegRotation;
  
  public EntityArmourStand(World world) {
    super(world);
    this.headRotation = DEFAULT_HEAD_ROTATION;
    this.bodyRotation = DEFAULT_BODY_ROTATION;
    this.leftArmRotation = DEFAULT_LEFTARM_ROTATION;
    this.rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
    this.leftLegRotation = DEFAULT_LEFTLEG_ROTATION;
    this.rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;
    this.field_70145_X = hasNoGravity();
    func_70105_a(0.5F, 1.975F);
  }
  
  public EntityArmourStand(World world, double posX, double posY, double posZ) {
    this(world);
    func_70107_b(posX, posY, posZ);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    addRotationsToDataWatcher(12, DEFAULT_HEAD_ROTATION);
    addRotationsToDataWatcher(15, DEFAULT_BODY_ROTATION);
    addRotationsToDataWatcher(18, DEFAULT_LEFTARM_ROTATION);
    addRotationsToDataWatcher(21, DEFAULT_RIGHTARM_ROTATION);
    addRotationsToDataWatcher(24, DEFAULT_LEFTLEG_ROTATION);
    addRotationsToDataWatcher(27, DEFAULT_RIGHTLEG_ROTATION);
    this.field_70180_af.func_75682_a(30, Byte.valueOf((byte)0));
    func_110163_bv();
  }
  
  private void addRotationsToDataWatcher(int index, Rotations rotations) {
    this.field_70180_af.func_75682_a(index, Float.valueOf(rotations.getX()));
    this.field_70180_af.func_75682_a(index + 1, Float.valueOf(rotations.getY()));
    this.field_70180_af.func_75682_a(index + 2, Float.valueOf(rotations.getZ()));
  }
  
  protected void func_70626_be() {}
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return new ItemStack(ModItems.armour_stand);
  }
  
  @SideOnly(Side.CLIENT)
  public ItemStack getCurrentArmor(int slotIn) {
    return func_71124_b(slotIn + 1);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("Invisible", func_82150_aj());
    nbt.func_74757_a("Small", isSmall());
    nbt.func_74757_a("ShowArms", getShowArms());
    nbt.func_74757_a("NoGravity", hasNoGravity());
    nbt.func_74757_a("NoBasePlate", hasNoBasePlate());
    nbt.func_74782_a("Pose", (NBTBase)readPoseFromNBT());
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    func_82142_c(nbt.func_74767_n("Invisible"));
    setSmall(nbt.func_74767_n("Small"));
    setShowArms(nbt.func_74767_n("ShowArms"));
    setNoGravity(nbt.func_74767_n("NoGravity"));
    setNoBasePlate(nbt.func_74767_n("NoBasePlate"));
    this.field_70145_X = hasNoGravity();
    NBTTagCompound nbttagcompound1 = nbt.func_74775_l("Pose");
    writePoseToNBT(nbttagcompound1);
  }
  
  private void writePoseToNBT(NBTTagCompound tagCompound) {
    NBTTagList nbttaglist = tagCompound.func_150295_c("Head", 5);
    if (nbttaglist.func_74745_c() > 0) {
      setHeadRotation(new Rotations(nbttaglist));
    } else {
      setHeadRotation(DEFAULT_HEAD_ROTATION);
    } 
    NBTTagList nbttaglist1 = tagCompound.func_150295_c("Body", 5);
    if (nbttaglist1.func_74745_c() > 0) {
      setBodyRotation(new Rotations(nbttaglist1));
    } else {
      setBodyRotation(DEFAULT_BODY_ROTATION);
    } 
    NBTTagList nbttaglist2 = tagCompound.func_150295_c("LeftArm", 5);
    if (nbttaglist2.func_74745_c() > 0) {
      setLeftArmRotation(new Rotations(nbttaglist2));
    } else {
      setLeftArmRotation(DEFAULT_LEFTARM_ROTATION);
    } 
    NBTTagList nbttaglist3 = tagCompound.func_150295_c("RightArm", 5);
    if (nbttaglist3.func_74745_c() > 0) {
      setRightArmRotation(new Rotations(nbttaglist3));
    } else {
      setRightArmRotation(DEFAULT_RIGHTARM_ROTATION);
    } 
    NBTTagList nbttaglist4 = tagCompound.func_150295_c("LeftLeg", 5);
    if (nbttaglist4.func_74745_c() > 0) {
      setLeftLegRotation(new Rotations(nbttaglist4));
    } else {
      setLeftLegRotation(DEFAULT_LEFTLEG_ROTATION);
    } 
    NBTTagList nbttaglist5 = tagCompound.func_150295_c("RightLeg", 5);
    if (nbttaglist5.func_74745_c() > 0) {
      setRightLegRotation(new Rotations(nbttaglist5));
    } else {
      setRightLegRotation(DEFAULT_RIGHTLEG_ROTATION);
    } 
  }
  
  private NBTTagCompound readPoseFromNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    if (!DEFAULT_HEAD_ROTATION.equals(this.headRotation))
      nbt.func_74782_a("Head", (NBTBase)this.headRotation.writeToNBT()); 
    if (!DEFAULT_BODY_ROTATION.equals(this.bodyRotation))
      nbt.func_74782_a("Body", (NBTBase)this.bodyRotation.writeToNBT()); 
    if (!DEFAULT_LEFTARM_ROTATION.equals(this.leftArmRotation))
      nbt.func_74782_a("LeftArm", (NBTBase)this.leftArmRotation.writeToNBT()); 
    if (!DEFAULT_RIGHTARM_ROTATION.equals(this.rightArmRotation))
      nbt.func_74782_a("RightArm", (NBTBase)this.rightArmRotation.writeToNBT()); 
    if (!DEFAULT_LEFTLEG_ROTATION.equals(this.leftLegRotation))
      nbt.func_74782_a("LeftLeg", (NBTBase)this.leftLegRotation.writeToNBT()); 
    if (!DEFAULT_RIGHTLEG_ROTATION.equals(this.rightLegRotation))
      nbt.func_74782_a("RightLeg", (NBTBase)this.rightLegRotation.writeToNBT()); 
    return nbt;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  protected void func_82167_n(Entity entity) {}
  
  protected void func_85033_bc() {}
  
  public boolean func_70085_c(EntityPlayer player) {
    if (this.field_70170_p.field_72995_K) {
      Back2Future.networkWrapper.sendToServer((IMessage)new ArmourStandInteractMessage(this.field_70170_p.field_73011_w.field_76574_g, this, player));
      return true;
    } 
    return false;
  }
  
  public boolean interact(EntityPlayer player, Vec3 hitPos) {
    if (!this.field_70170_p.field_72995_K) {
      byte b0 = 0;
      ItemStack itemstack = player.func_71045_bC();
      boolean flag = (itemstack != null);
      if (flag && itemstack.func_77973_b() instanceof ItemArmor) {
        ItemArmor itemarmor = (ItemArmor)itemstack.func_77973_b();
        if (itemarmor.field_77881_a == 3) {
          b0 = 1;
        } else if (itemarmor.field_77881_a == 2) {
          b0 = 2;
        } else if (itemarmor.field_77881_a == 1) {
          b0 = 3;
        } else if (itemarmor.field_77881_a == 0) {
          b0 = 4;
        } 
      } 
      if (flag && (itemstack.func_77973_b() == Items.field_151144_bL || itemstack
        .func_77973_b() == Item.func_150898_a(Blocks.field_150423_aK)))
        b0 = 4; 
      byte b1 = 0;
      boolean isSmall = isSmall();
      double d3 = isSmall ? (hitPos.field_72448_b * 2.0D) : hitPos.field_72448_b;
      if (d3 >= 0.1D && d3 < 0.1D + (isSmall ? 0.8D : 0.45D) && func_71124_b(1) != null) {
        b1 = 1;
      } else if (d3 >= 0.9D + (isSmall ? 0.3D : 0.0D) && d3 < 0.9D + (isSmall ? 1.0D : 0.7D) && 
        func_71124_b(3) != null) {
        b1 = 3;
      } else if (d3 >= 0.4D && d3 < 0.4D + (isSmall ? 1.0D : 0.8D) && func_71124_b(2) != null) {
        b1 = 2;
      } else if (d3 >= 1.6D && func_71124_b(4) != null) {
        b1 = 4;
      } 
      boolean flag2 = (func_71124_b(b1) != null);
      if (flag && b0 == 0 && !getShowArms())
        return true; 
      if (flag) {
        func_175422_a(player, b0);
      } else if (flag2) {
        func_175422_a(player, b1);
      } 
      return true;
    } 
    return true;
  }
  
  private void func_175422_a(EntityPlayer player, int slot) {
    ItemStack itemstack = func_71124_b(slot);
    if (itemstack == null || itemstack.field_77994_a <= 0)
      func_70062_b(slot, null); 
    int j = player.field_71071_by.field_70461_c;
    ItemStack itemstack1 = player.field_71071_by.func_70301_a(j);
    if (player.field_71075_bZ.field_75098_d && (itemstack == null || itemstack
      .func_77973_b() == Item.func_150898_a(Blocks.field_150350_a)) && itemstack1 != null) {
      ItemStack itemstack2 = itemstack1.func_77946_l();
      itemstack2.field_77994_a = 1;
      func_70062_b(slot, itemstack2);
    } else if (itemstack1 != null && itemstack1.field_77994_a > 1) {
      if (itemstack == null) {
        ItemStack itemstack2 = itemstack1.func_77946_l();
        itemstack2.field_77994_a = 1;
        func_70062_b(slot, itemstack2);
        itemstack1.field_77994_a--;
        if (itemstack1.field_77994_a <= 0)
          itemstack1 = null; 
        player.field_71071_by.func_70299_a(j, itemstack1);
      } 
    } else {
      func_70062_b(slot, itemstack1);
      player.field_71071_by.func_70299_a(j, itemstack);
    } 
  }
  
  public boolean func_70097_a(DamageSource source, float amount) {
    if (!this.field_70170_p.field_72995_K && !this.canInteract) {
      if (DamageSource.field_76380_i.equals(source)) {
        func_70106_y();
        return false;
      } 
      if (func_85032_ar())
        return false; 
      if (source.func_94541_c()) {
        dropequipment();
        func_70106_y();
        return false;
      } 
      if (DamageSource.field_76372_a.equals(source)) {
        if (!func_70027_ad()) {
          func_70015_d(5);
        } else {
          damageArmorStand(0.15F);
        } 
        return false;
      } 
      if (DamageSource.field_76370_b.equals(source) && func_110143_aJ() > 0.5F) {
        damageArmorStand(4.0F);
        return false;
      } 
      boolean flag = "arrow".equals(source.func_76355_l());
      boolean flag1 = "player".equals(source.func_76355_l());
      if (!flag1 && !flag)
        return false; 
      if (source.func_76364_f() instanceof net.minecraft.entity.projectile.EntityArrow)
        source.func_76364_f().func_70106_y(); 
      if (source.func_76346_g() instanceof EntityPlayer && 
        !((EntityPlayer)source.func_76346_g()).field_71075_bZ.field_75099_e)
        return false; 
      if (source.func_76346_g() instanceof EntityPlayer && 
        ((EntityPlayer)source.func_76346_g()).field_71075_bZ.field_75098_d) {
        playParticles();
        func_70106_y();
        return false;
      } 
      long i = this.field_70170_p.func_82737_E();
      if (i - this.punchCooldown > 5L && !flag) {
        this.punchCooldown = i;
      } else {
        dropBlock();
        playParticles();
        func_70106_y();
      } 
      return false;
    } 
    return false;
  }
  
  private void playParticles() {}
  
  private void damageArmorStand(float p_175406_1_) {
    float f1 = func_110143_aJ();
    f1 -= p_175406_1_;
    if (f1 <= 0.5F) {
      dropequipment();
      func_70106_y();
    } else {
      func_70606_j(f1);
    } 
  }
  
  private void dropBlock() {
    func_70099_a(new ItemStack(ModItems.armour_stand), 0.0F);
    dropequipment();
  }
  
  private void dropequipment() {
    for (int i = 0; i < 5; i++) {
      if (func_71124_b(i) != null && (func_71124_b(i)).field_77994_a > 0 && 
        func_71124_b(i) != null) {
        func_70099_a(func_71124_b(i), 0.0F);
        func_70062_b(i, null);
      } 
    } 
  }
  
  protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
    this.field_70760_ar = this.field_70126_B;
    this.field_70761_aq = this.field_70177_z;
    return 0.0F;
  }
  
  public float func_70047_e() {
    return func_70631_g_() ? (this.field_70131_O * 0.5F) : (this.field_70131_O * 0.9F);
  }
  
  public void func_70612_e(float p_70612_1_, float p_70612_2_) {}
  
  public void func_70071_h_() {
    super.func_70071_h_();
  }
  
  public void func_82142_c(boolean invisible) {
    this.canInteract = invisible;
    super.func_82142_c(invisible);
  }
  
  public boolean func_70631_g_() {
    return isSmall();
  }
  
  private void setSmall(boolean p_175420_1_) {
    byte b0 = this.field_70180_af.func_75683_a(30);
    if (p_175420_1_) {
      b0 = (byte)(b0 | 0x1);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFFE);
    } 
    this.field_70180_af.func_75692_b(30, Byte.valueOf(b0));
  }
  
  public boolean isSmall() {
    return ((this.field_70180_af.func_75683_a(30) & 0x1) != 0);
  }
  
  public void setNoGravity(boolean p_175425_1_) {
    byte b0 = this.field_70180_af.func_75683_a(30);
    if (p_175425_1_) {
      b0 = (byte)(b0 | 0x2);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFFD);
    } 
    this.field_70180_af.func_75692_b(30, Byte.valueOf(b0));
  }
  
  public boolean hasNoGravity() {
    return ((this.field_70180_af.func_75683_a(30) & 0x2) != 0);
  }
  
  private void setShowArms(boolean p_175413_1_) {
    byte b0 = this.field_70180_af.func_75683_a(30);
    if (p_175413_1_) {
      b0 = (byte)(b0 | 0x4);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFFB);
    } 
    this.field_70180_af.func_75692_b(30, Byte.valueOf(b0));
  }
  
  public boolean getShowArms() {
    return ((this.field_70180_af.func_75683_a(30) & 0x4) != 0);
  }
  
  private void setNoBasePlate(boolean p_175426_1_) {
    byte b0 = this.field_70180_af.func_75683_a(30);
    if (p_175426_1_) {
      b0 = (byte)(b0 | 0x8);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFF7);
    } 
    this.field_70180_af.func_75692_b(30, Byte.valueOf(b0));
  }
  
  public boolean hasNoBasePlate() {
    return ((this.field_70180_af.func_75683_a(30) & 0x8) != 0);
  }
  
  public void setHeadRotation(Rotations p_175415_1_) {
    this.headRotation = p_175415_1_;
    updateRotations(12, p_175415_1_);
  }
  
  public void setBodyRotation(Rotations p_175424_1_) {
    this.bodyRotation = p_175424_1_;
    updateRotations(15, p_175424_1_);
  }
  
  public void setLeftArmRotation(Rotations p_175405_1_) {
    this.leftArmRotation = p_175405_1_;
    updateRotations(18, p_175405_1_);
  }
  
  public void setRightArmRotation(Rotations p_175428_1_) {
    this.rightArmRotation = p_175428_1_;
    updateRotations(21, p_175428_1_);
  }
  
  public void setLeftLegRotation(Rotations p_175417_1_) {
    this.leftLegRotation = p_175417_1_;
    updateRotations(24, p_175417_1_);
  }
  
  public void setRightLegRotation(Rotations p_175427_1_) {
    this.rightLegRotation = p_175427_1_;
    updateRotations(27, p_175427_1_);
  }
  
  private void updateRotations(int index, Rotations rotations) {
    this.field_70180_af.func_75692_b(index, Float.valueOf(rotations.getX()));
    this.field_70180_af.func_75692_b(index + 1, Float.valueOf(rotations.getY()));
    this.field_70180_af.func_75692_b(index + 2, Float.valueOf(rotations.getZ()));
  }
  
  public Rotations getHeadRotation() {
    return this.headRotation;
  }
  
  public Rotations getBodyRotation() {
    return this.bodyRotation;
  }
  
  @SideOnly(Side.CLIENT)
  public Rotations getLeftArmRotation() {
    return this.leftArmRotation;
  }
  
  @SideOnly(Side.CLIENT)
  public Rotations getRightArmRotation() {
    return this.rightArmRotation;
  }
  
  @SideOnly(Side.CLIENT)
  public Rotations getLeftLegRotation() {
    return this.leftLegRotation;
  }
  
  @SideOnly(Side.CLIENT)
  public Rotations getRightLegRotation() {
    return this.rightLegRotation;
  }
  
  protected void func_70679_bo() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityArmourStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */