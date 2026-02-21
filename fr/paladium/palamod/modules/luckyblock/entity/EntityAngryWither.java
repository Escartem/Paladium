package fr.paladium.palamod.modules.luckyblock.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.world.World;

public class EntityAngryWither extends EntityWither {
  public EntityAngryWither(World world) {
    super(world);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(900.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.8D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityAngryWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */