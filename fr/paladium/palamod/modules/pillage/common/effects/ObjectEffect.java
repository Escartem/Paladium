package fr.paladium.palamod.modules.pillage.common.effects;

import fr.paladium.palamod.modules.pillage.common.BaseEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface ObjectEffect extends BaseEffect {
  void applyEffect(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, Entity paramEntity);
  
  ObjectEffect registerRecipes(Block paramBlock);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\ObjectEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */