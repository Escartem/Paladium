package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoatFurnace extends Entity {
  private boolean isBoatEmpty;
  
  private double speedMultiplier;
  
  private int boatPosRotationIncrements;
  
  private double boatX;
  
  private double boatY;
  
  private double boatZ;
  
  private double boatYaw;
  
  private double boatPitch;
  
  @SideOnly(Side.CLIENT)
  private double velocityX;
  
  @SideOnly(Side.CLIENT)
  private double velocityY;
  
  @SideOnly(Side.CLIENT)
  private double velocityZ;
  
  private int coal = 0;
  
  private long burningTime = 0L;
  
  public EntityBoatFurnace(World p_i1704_1_) {
    super(p_i1704_1_);
    this.isBoatEmpty = true;
    this.speedMultiplier = 0.07D * getBurningMultiplier();
    this.field_70156_m = true;
    func_70105_a(1.5F, 0.6F);
    this.field_70129_M = this.field_70131_O / 2.0F;
  }
  
  protected boolean func_70041_e_() {
    return false;
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_75682_a(17, new Integer(0));
    this.field_70180_af.func_75682_a(18, new Integer(1));
    this.field_70180_af.func_75682_a(19, new Float(0.0F));
  }
  
  public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
    return p_70114_1_.field_70121_D;
  }
  
  public AxisAlignedBB func_70046_E() {
    return this.field_70121_D;
  }
  
  public boolean func_70104_M() {
    return true;
  }
  
  public EntityBoatFurnace(World p_i1705_1_, double p_i1705_2_, double p_i1705_4_, double p_i1705_6_) {
    this(p_i1705_1_);
    func_70107_b(p_i1705_2_, p_i1705_4_ + this.field_70129_M, p_i1705_6_);
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    this.field_70169_q = p_i1705_2_;
    this.field_70167_r = p_i1705_4_;
    this.field_70166_s = p_i1705_6_;
  }
  
  public double func_70042_X() {
    return this.field_70131_O * 0.0D - 0.30000001192092896D;
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (func_85032_ar())
      return false; 
    if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
      setForwardDirection(-getForwardDirection());
      setTimeSinceHit(10);
      setDamageTaken(getDamageTaken() + p_70097_2_ * 10.0F);
      func_70018_K();
      boolean flag = (p_70097_1_.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75098_d);
      if (flag || getDamageTaken() > 40.0F) {
        if (this.field_70153_n != null)
          this.field_70153_n.func_70078_a(this); 
        if (!flag)
          func_145778_a(Items.field_151124_az, 1, 0.0F); 
        func_70106_y();
      } 
      return true;
    } 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70057_ab() {
    setForwardDirection(-getForwardDirection());
    setTimeSinceHit(10);
    setDamageTaken(getDamageTaken() * 11.0F);
  }
  
  public boolean func_70067_L() {
    return !this.field_70128_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
    if (this.isBoatEmpty) {
      this.boatPosRotationIncrements = p_70056_9_ + 5;
    } else {
      double d3 = p_70056_1_ - this.field_70165_t;
      double d4 = p_70056_3_ - this.field_70163_u;
      double d5 = p_70056_5_ - this.field_70161_v;
      double d6 = d3 * d3 + d4 * d4 + d5 * d5;
      if (d6 <= 1.0D)
        return; 
      this.boatPosRotationIncrements = 3;
    } 
    this.boatX = p_70056_1_;
    this.boatY = p_70056_3_;
    this.boatZ = p_70056_5_;
    this.boatYaw = p_70056_7_;
    this.boatPitch = p_70056_8_;
    this.field_70159_w = this.velocityX;
    this.field_70181_x = this.velocityY;
    this.field_70179_y = this.velocityZ;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
    this.velocityX = this.field_70159_w = p_70016_1_;
    this.velocityY = this.field_70181_x = p_70016_3_;
    this.velocityZ = this.field_70179_y = p_70016_5_;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.coal > 0 && 
      this.burningTime <= 0L) {
      this.coal--;
      this.burningTime = 200L;
    } 
    if (this.burningTime > 0L)
      this.burningTime--; 
    if (getTimeSinceHit() > 0)
      setTimeSinceHit(getTimeSinceHit() - 1); 
    if (getDamageTaken() > 0.0F)
      setDamageTaken(getDamageTaken() - 1.0F); 
    this.field_70169_q = this.field_70165_t;
    this.field_70167_r = this.field_70163_u;
    this.field_70166_s = this.field_70161_v;
    byte b0 = 5;
    double d0 = 0.0D;
    for (int i = 0; i < b0; i++) {
      double d1 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (i + 0) / b0 - 0.125D;
      double d3 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (i + 1) / b0 - 0.125D;
      AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a(this.field_70121_D.field_72340_a, d1, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d3, this.field_70121_D.field_72334_f);
      if (this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151586_h))
        d0 += 1.0D / b0; 
    } 
    double d10 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
    if (d10 > 0.26249999999999996D) {
      double d2 = Math.cos(this.field_70177_z * Math.PI / 180.0D);
      double d4 = Math.sin(this.field_70177_z * Math.PI / 180.0D);
      for (int j = 0; j < 1.0D + d10 * 60.0D; j++) {
        double d5 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F);
        double d6 = (this.field_70146_Z.nextInt(2) * 2 - 1) * 0.7D;
        if (this.field_70146_Z.nextBoolean()) {
          double d8 = this.field_70165_t - d2 * d5 * 0.8D + d4 * d6;
          double d9 = this.field_70161_v - d4 * d5 * 0.8D - d2 * d6;
          if (this.burningTime > 0L) {
            this.field_70170_p.func_72869_a("smoke", d8, this.field_70163_u - 0.125D, d9, this.field_70159_w, this.field_70181_x, this.field_70179_y);
          } else {
            this.field_70170_p.func_72869_a("splash", d8, this.field_70163_u - 0.125D, d9, this.field_70159_w, this.field_70181_x, this.field_70179_y);
          } 
        } 
      } 
    } 
    if (this.field_70170_p.field_72995_K && this.isBoatEmpty) {
      if (this.boatPosRotationIncrements > 0) {
        double d2 = this.field_70165_t + (this.boatX - this.field_70165_t) / this.boatPosRotationIncrements;
        double d4 = this.field_70163_u + (this.boatY - this.field_70163_u) / this.boatPosRotationIncrements;
        double d11 = this.field_70161_v + (this.boatZ - this.field_70161_v) / this.boatPosRotationIncrements;
        double d12 = MathHelper.func_76138_g(this.boatYaw - this.field_70177_z);
        this.field_70177_z = (float)(this.field_70177_z + d12 / this.boatPosRotationIncrements);
        this.field_70125_A = (float)(this.field_70125_A + (this.boatPitch - this.field_70125_A) / this.boatPosRotationIncrements);
        this.boatPosRotationIncrements--;
        func_70107_b(d2, d4, d11);
        func_70101_b(this.field_70177_z, this.field_70125_A);
      } else {
        double d2 = this.field_70165_t + this.field_70159_w;
        double d4 = this.field_70163_u + this.field_70181_x;
        double d11 = this.field_70161_v + this.field_70179_y;
        func_70107_b(d2, d4, d11);
        if (this.field_70122_E) {
          this.field_70159_w *= 0.5D;
          this.field_70181_x *= 0.5D;
          this.field_70179_y *= 0.5D;
        } 
        this.field_70159_w *= 0.9900000095367432D;
        this.field_70181_x *= 0.949999988079071D;
        this.field_70179_y *= 0.9900000095367432D;
      } 
    } else {
      if (d0 < 1.0D) {
        double d = d0 * 2.0D - 1.0D;
        this.field_70181_x += 0.03999999910593033D * d;
      } else {
        if (this.field_70181_x < 0.0D)
          this.field_70181_x /= 2.0D; 
        this.field_70181_x += 0.007000000216066837D;
      } 
      if (this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
        EntityLivingBase entitylivingbase = (EntityLivingBase)this.field_70153_n;
        float f = this.field_70153_n.field_70177_z + -entitylivingbase.field_70702_br * 90.0F;
        this.field_70159_w += -Math.sin((f * 3.1415927F / 180.0F)) * this.speedMultiplier * entitylivingbase.field_70701_bs * 0.05000000074505806D * 
          getBurningMultiplier();
        this.field_70179_y += Math.cos((f * 3.1415927F / 180.0F)) * this.speedMultiplier * entitylivingbase.field_70701_bs * 0.05000000074505806D * 
          getBurningMultiplier();
      } 
      double d2 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      if (d2 > 0.35D * getBurningMultiplier()) {
        double d = 0.35D * getBurningMultiplier() / d2;
        this.field_70159_w *= d;
        this.field_70179_y *= d;
        d2 = 0.35D * getBurningMultiplier();
      } 
      if (d2 > d10 && this.speedMultiplier < 0.35D * getBurningMultiplier()) {
        this.speedMultiplier += (0.35D * getBurningMultiplier() - this.speedMultiplier) / 35.0D * 
          getBurningMultiplier();
        if (this.speedMultiplier > 0.35D * getBurningMultiplier())
          this.speedMultiplier = 0.35D * getBurningMultiplier(); 
      } else {
        this.speedMultiplier -= (this.speedMultiplier - 0.07D * getBurningMultiplier()) / 35.0D * 
          getBurningMultiplier();
        if (this.speedMultiplier < 0.07D * getBurningMultiplier())
          this.speedMultiplier = 0.07D * getBurningMultiplier(); 
      } 
      for (int l = 0; l < 4; l++) {
        int i1 = MathHelper.func_76128_c(this.field_70165_t + ((l % 2) - 0.5D * 
            getBurningMultiplier()) * 0.8D * getBurningMultiplier());
        int j = MathHelper.func_76128_c(this.field_70161_v + ((l / 2) - 0.5D * 
            getBurningMultiplier()) * 0.8D * getBurningMultiplier());
        for (int j1 = 0; j1 < 2; j1++) {
          int k = MathHelper.func_76128_c(this.field_70163_u) + j1;
          Block block = this.field_70170_p.func_147439_a(i1, k, j);
          if (block == Blocks.field_150431_aC) {
            this.field_70170_p.func_147468_f(i1, k, j);
            this.field_70123_F = false;
          } else if (block == Blocks.field_150392_bi) {
            this.field_70170_p.func_147480_a(i1, k, j, true);
            this.field_70123_F = false;
          } 
        } 
      } 
      if (this.field_70122_E) {
        this.field_70159_w *= 0.5D * getBurningMultiplier();
        this.field_70181_x *= 0.5D * getBurningMultiplier();
        this.field_70179_y *= 0.5D * getBurningMultiplier();
      } 
      func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      if (!this.field_70123_F || d10 <= 0.2D * getBurningMultiplier()) {
        this.field_70159_w *= 0.9900000095367432D;
        this.field_70181_x *= 0.949999988079071D;
        this.field_70179_y *= 0.9900000095367432D;
      } 
      this.field_70125_A = 0.0F;
      double d4 = this.field_70177_z;
      double d11 = this.field_70169_q - this.field_70165_t;
      double d12 = this.field_70166_s - this.field_70161_v;
      if (d11 * d11 + d12 * d12 > 0.001D)
        d4 = (float)(Math.atan2(d12, d11) * 180.0D / Math.PI); 
      double d7 = MathHelper.func_76138_g(d4 - this.field_70177_z);
      if (d7 > 20.0D)
        d7 = 20.0D; 
      if (d7 < -20.0D)
        d7 = -20.0D; 
      this.field_70177_z = (float)(this.field_70177_z + d7);
      func_70101_b(this.field_70177_z, this.field_70125_A);
      if (!this.field_70170_p.field_72995_K) {
        List<Entity> list = this.field_70170_p.func_72839_b(this, this.field_70121_D
            .func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if (list != null && !list.isEmpty())
          for (int k1 = 0; k1 < list.size(); k1++) {
            Entity entity = list.get(k1);
            if (entity != this.field_70153_n && entity.func_70104_M() && entity instanceof EntityBoatFurnace)
              entity.func_70108_f(this); 
          }  
        if (this.field_70153_n != null && this.field_70153_n.field_70128_L)
          this.field_70153_n = null; 
      } 
    } 
  }
  
  public void func_70043_V() {
    if (this.field_70153_n != null) {
      double d0 = Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.4D;
      double d1 = Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.4D;
      this.field_70153_n.func_70107_b(this.field_70165_t + d0, this.field_70163_u + 
          func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + d1);
    } 
  }
  
  protected void func_70014_b(NBTTagCompound p_70014_1_) {}
  
  protected void func_70037_a(NBTTagCompound p_70037_1_) {}
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }
  
  public boolean canRiderInteract() {
    return true;
  }
  
  public boolean func_130002_c(EntityPlayer p_130002_1_) {
    if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != p_130002_1_)
      return true; 
    if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n == p_130002_1_) {
      if (!this.field_70170_p.field_72995_K)
        if (p_130002_1_.field_71071_by.func_70448_g() != null && p_130002_1_.field_71071_by
          .func_70448_g().func_77973_b().equals(Items.field_151044_h)) {
          this.coal++;
          for (int i = 0; i < p_130002_1_.field_71071_by.field_70462_a.length; i++) {
            ItemStack it = p_130002_1_.field_71071_by.field_70462_a[i];
            if (it != null && 
              it.func_77969_a(new ItemStack(Items.field_151044_h)) && 
              ItemStack.func_77970_a(it, new ItemStack(Items.field_151044_h))) {
              it.field_77994_a--;
              if (it.field_77994_a <= 0) {
                p_130002_1_.field_71071_by.func_70299_a(i, null);
                p_130002_1_.field_71071_by.field_70459_e = true;
              } else {
                p_130002_1_.field_71071_by.func_70299_a(i, it.func_77946_l());
                p_130002_1_.field_71071_by.field_70459_e = true;
              } 
            } 
          } 
          p_130002_1_.func_146105_b((IChatComponent)new ChatComponentText("§eVous venez d'ajouter du charbon au four. §a+1"));
          p_130002_1_.func_146105_b((IChatComponent)new ChatComponentText("§eContenu du four:"));
          p_130002_1_
            .func_146105_b((IChatComponent)new ChatComponentText("§7 - §a" + this.coal + " Charbon(s)§7."));
          p_130002_1_.func_146105_b((IChatComponent)new ChatComponentText("§7 - §a" + (this.burningTime / 20L + (this.coal * 10)) + "s §ede boost."));
        } else {
          p_130002_1_.func_146105_b((IChatComponent)new ChatComponentText("§eAjoutez §cdu charbon §edans le four du bateau."));
          p_130002_1_.func_146105_b((IChatComponent)new ChatComponentText("§eContenu du four:"));
          p_130002_1_
            .func_146105_b((IChatComponent)new ChatComponentText("§7 - §a" + this.coal + " Charbon(s)§7."));
          p_130002_1_.func_146105_b((IChatComponent)new ChatComponentText("§7 - §a" + (this.burningTime / 20L + (this.coal * 10)) + "s §ede boost."));
        }  
      return true;
    } 
    if (!this.field_70170_p.field_72995_K)
      p_130002_1_.func_70078_a(this); 
    return true;
  }
  
  protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70163_u);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    if (p_70064_3_) {
      if (this.field_70143_R > 3.0F) {
        func_70069_a(this.field_70143_R);
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
          func_70106_y();
          int l;
          for (l = 0; l < 3; l++)
            func_145778_a(Item.func_150898_a(Blocks.field_150344_f), 1, 0.0F); 
          for (l = 0; l < 2; l++)
            func_145778_a(Items.field_151055_y, 1, 0.0F); 
        } 
        this.field_70143_R = 0.0F;
      } 
    } else if (this.field_70170_p.func_147439_a(i, j - 1, k).func_149688_o() != Material.field_151586_h && p_70064_1_ < 0.0D) {
      this.field_70143_R = (float)(this.field_70143_R - p_70064_1_);
    } 
  }
  
  public void setDamageTaken(float p_70266_1_) {
    this.field_70180_af.func_75692_b(19, Float.valueOf(p_70266_1_));
  }
  
  public float getDamageTaken() {
    return this.field_70180_af.func_111145_d(19);
  }
  
  public void setTimeSinceHit(int p_70265_1_) {
    this.field_70180_af.func_75692_b(17, Integer.valueOf(p_70265_1_));
  }
  
  public int getTimeSinceHit() {
    return this.field_70180_af.func_75679_c(17);
  }
  
  public void setForwardDirection(int p_70269_1_) {
    this.field_70180_af.func_75692_b(18, Integer.valueOf(p_70269_1_));
  }
  
  public int getForwardDirection() {
    return this.field_70180_af.func_75679_c(18);
  }
  
  @SideOnly(Side.CLIENT)
  public void setIsBoatEmpty(boolean p_70270_1_) {
    this.isBoatEmpty = p_70270_1_;
  }
  
  private double getBurningMultiplier() {
    if (this.burningTime > 0L)
      return 2.0D; 
    return 1.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityBoatFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */