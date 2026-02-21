package fr.paladium.palamod.modules.luckyblock.entity.christmas;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityChristmasSnowGolem extends EntityAnimal {
  public EntityChristmasSnowGolem(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.5D));
  }
  
  public EntityChristmasSnowGolem(World world, double x, double y, double z) {
    super(world);
    func_70634_a(x, y, z);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.0D);
  }
  
  public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\christmas\EntityChristmasSnowGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */