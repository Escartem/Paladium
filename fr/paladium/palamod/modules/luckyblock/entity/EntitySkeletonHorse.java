package fr.paladium.palamod.modules.luckyblock.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntitySkeletonHorse extends EntityHorse {
  public EntitySkeletonHorse(World world) {
    super(world);
    func_94058_c("§cCaballo de la muerte");
    func_110234_j(true);
    func_110251_o(true);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  public void func_70636_d() {
    func_110214_p(4);
    super.func_70636_d();
    if (this.field_70170_p instanceof WorldServer) {
      WorldServer world = (WorldServer)this.field_70170_p;
      world.func_147487_a("smoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, 50, 0.0D, 0.0D, 0.0D, 0.01D);
    } else {
      for (int i = 0; i < 50; i++)
        this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntitySkeletonHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */