package fr.paladium.palamod.modules.spellsv2.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityEgg extends EntityLiving {
  private EntityPlayer owner;
  
  public EntityPlayer getOwner() {
    return this.owner;
  }
  
  public void setOwner(EntityPlayer owner) {
    this.owner = owner;
  }
  
  public EntityEgg(World world) {
    super(world);
    func_70659_e(0.0F);
  }
  
  protected void func_110147_ax() {
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111266_c)
      .func_111128_a(1000.0D);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111265_b)
      .func_111128_a(0.0D);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111267_a)
      .func_111128_a(200.0D);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111263_d)
      .func_111128_a(0.0D);
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public void func_70106_y() {
    if (this.owner != null)
      this.owner.func_82142_c(false); 
    super.func_70106_y();
  }
  
  public void func_70030_z() {
    if (this.owner != null && !this.field_70170_p.field_72995_K) {
      if (this.owner.field_70128_L)
        func_70106_y(); 
      func_70080_a(this.owner.field_70165_t, this.owner.field_70163_u, this.owner.field_70161_v, 0.0F, 0.0F);
    } 
    super.func_70030_z();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\entity\EntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */