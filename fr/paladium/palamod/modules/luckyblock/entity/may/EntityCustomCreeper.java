package fr.paladium.palamod.modules.luckyblock.entity.may;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityCustomCreeper extends EntityCreeper {
  private int lastActiveTime;
  
  private int timeSinceIgnited;
  
  private int fuseTime = 30;
  
  private float explosionRadius = 3.0F;
  
  private boolean creepy;
  
  private boolean invincible;
  
  public void setInvincible(boolean invincible) {
    this.invincible = invincible;
  }
  
  public EntityCustomCreeper(World world) {
    super(world);
  }
  
  public EntityCustomCreeper(World world, boolean forceIgnite, boolean creepy, float explosionDamage) {
    super(world);
    if (forceIgnite)
      this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)1)); 
    this.explosionRadius = explosionDamage;
    this.creepy = creepy;
  }
  
  public void func_70071_h_() {
    if (func_70089_S()) {
      this.lastActiveTime = this.timeSinceIgnited;
      if (func_146078_ca())
        func_70829_a(1); 
      int i = func_70832_p();
      if (i > 0 && this.timeSinceIgnited == 0)
        func_85030_a("creeper.primed", 1.0F, 0.5F); 
      this.timeSinceIgnited += i;
      if (this.timeSinceIgnited < 0)
        this.timeSinceIgnited = 0; 
      if (this.timeSinceIgnited >= this.fuseTime) {
        this.timeSinceIgnited = this.fuseTime;
        func_146077_cc();
      } 
    } 
    super.func_70071_h_();
  }
  
  protected Item func_146068_u() {
    if (this.creepy)
      return Item.func_150898_a(BlocksRegister.CREEPER_PLUSH); 
    return Items.field_151016_H;
  }
  
  private void func_146077_cc() {
    if (!this.field_70170_p.field_72995_K) {
      boolean flag = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
      this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.explosionRadius, flag);
      func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\may\EntityCustomCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */