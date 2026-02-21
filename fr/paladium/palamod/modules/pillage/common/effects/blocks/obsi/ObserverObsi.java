package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ObserverObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {}
  
  public ObjectEffect registerRecipes(Block block) {
    return this;
  }
  
  public String getName() {
    return "obsi_observer";
  }
  
  public String getDescription() {
    return "Observer bloc";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\ObserverObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */