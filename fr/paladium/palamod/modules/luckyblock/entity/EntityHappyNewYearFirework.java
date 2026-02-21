package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityHappyNewYearFirework extends EntityFireworkRocket {
  private int fireworkAge;
  
  private int lifetime;
  
  private final int[][] FLAGS = new int[][] { 
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 2, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 2, 2, 0, 0, 0, 0, 0, 0 }, { 0, 0, 2, 2, 2, 2, 0, 0, 0, 0 }, { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0, 0, 2, 2, 0, 0 }, { 0, 1, 2, 0, 0, 0, 2, 2, 0, 0 }, { 1, 1, 2, 2, 2, 2, 2, 0, 0, 0 }, 
      { 1, 1, 2, 2, 2, 2, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
  
  private double account;
  
  private double accountY;
  
  private int facing;
  
  public EntityHappyNewYearFirework(World world) {
    super(world);
    func_70105_a(0.25F, 0.25F);
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_82709_a(8, 5);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double dost) {
    return (dost < 4096.0D);
  }
  
  public EntityHappyNewYearFirework(World world, double x, double y, double z, int facing) {
    super(world);
    this.fireworkAge = 0;
    func_70105_a(0.25F, 0.25F);
    func_70107_b(x, y, z);
    this.field_70181_x = 0.05D;
    this.lifetime = 20;
    this.facing = facing;
  }
  
  public void func_70071_h_() {
    this.field_70142_S = this.field_70165_t;
    this.field_70137_T = this.field_70163_u;
    this.field_70136_U = this.field_70161_v;
    super.func_70071_h_();
    this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
    if (this.fireworkAge == 0)
      this.field_70170_p.func_72956_a((Entity)this, "fireworks.launch", 3.0F, 1.0F); 
    this.fireworkAge++;
    if (this.field_70170_p.field_72995_K && this.fireworkAge % 2 < 2 && func_70089_S())
      createFlag(); 
    if (this.field_70170_p.field_72995_K && this.fireworkAge > this.lifetime && func_70089_S())
      func_70106_y(); 
  }
  
  private void createFlag() {
    for (int i = 0; i < this.FLAGS.length; i++) {
      for (int j = 0; j < (this.FLAGS[i]).length; j++) {
        if (this.facing == 6 || this.facing == 5) {
          if (j == 9)
            this.account = 0.0D; 
          this.account = 0.2D + this.account;
          double z = this.field_70136_U + this.account;
          this.accountY = 0.2D * i;
          double y = this.field_70137_T + this.accountY;
          switch (this.FLAGS[i][j]) {
            case 0:
              createParticle(this.field_70165_t, y, z - 0.5D, 0.0D, Color.DARK_GRAY);
              break;
            case 1:
              createParticle(this.field_70165_t, y, z - 0.5D, 0.0D, Color.RED);
              break;
            case 2:
              createParticle(this.field_70165_t, y, z - 0.5D, 0.0D, Color.YELLOW);
              break;
          } 
        } 
        if (this.facing == 2 || this.facing == 1) {
          if (j == 9)
            this.account = 0.0D; 
          this.account = 0.2D + this.account;
          double z = this.field_70136_U + -this.account;
          this.accountY = 0.2D * i;
          double y = this.field_70137_T + this.accountY;
          switch (this.FLAGS[i][j]) {
            case 0:
              createParticle(this.field_70165_t, y, z + 1.5D, 0.0D, Color.DARK_GRAY);
              break;
            case 1:
              createParticle(this.field_70165_t, y, z + 1.5D, 0.0D, Color.RED);
              break;
            case 2:
              createParticle(this.field_70165_t, y, z + 1.5D, 0.0D, Color.YELLOW);
              break;
          } 
        } 
        if (this.facing == 0 || this.facing == 7) {
          if (j == 9)
            this.account = 0.0D; 
          this.account = 0.2D + this.account;
          double x = this.field_70142_S + -this.account;
          this.accountY = 0.2D * i;
          double y = this.field_70137_T + this.accountY;
          switch (this.FLAGS[i][j]) {
            case 0:
              createParticle(x + 1.0D, y, this.field_70161_v, 0.0D, Color.DARK_GRAY);
              break;
            case 1:
              createParticle(x + 1.0D, y, this.field_70161_v, 0.0D, Color.RED);
              break;
            case 2:
              createParticle(x + 1.0D, y, this.field_70161_v, 0.0D, Color.YELLOW);
              break;
          } 
        } 
        if (this.facing == 4 || this.facing == 3) {
          if (j == 9)
            this.account = 0.0D; 
          this.account = 0.2D + this.account;
          double x = this.field_70142_S + this.account;
          this.accountY = 0.2D * i;
          double y = this.field_70137_T + this.accountY;
          switch (this.FLAGS[i][j]) {
            case 0:
              createParticle(x, y, this.field_70161_v, 0.0D, Color.DARK_GRAY);
              break;
            case 1:
              createParticle(x, y, this.field_70161_v, 0.0D, Color.RED);
              break;
            case 2:
              createParticle(x, y, this.field_70161_v, 0.0D, Color.YELLOW);
              break;
          } 
        } 
      } 
    } 
  }
  
  private void createParticle(double x, double y, double z, double velocity, Color color) {
    this.field_70170_p.func_72869_a("reddust", x, y, z, color.getRed() / 255.0D, color
        .getGreen() / 255.0D, color.getBlue() / 255.0D);
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    p_70014_1_.func_74768_a("Life", this.fireworkAge);
    p_70014_1_.func_74768_a("LifeTime", this.lifetime);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    this.fireworkAge = p_70037_1_.func_74762_e("Life");
    this.lifetime = p_70037_1_.func_74762_e("LifeTime");
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }
  
  public float func_70013_c(float p_70013_1_) {
    return super.func_70013_c(p_70013_1_);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_70070_b(float p_70070_1_) {
    return super.func_70070_b(p_70070_1_);
  }
  
  public boolean func_70075_an() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityHappyNewYearFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */