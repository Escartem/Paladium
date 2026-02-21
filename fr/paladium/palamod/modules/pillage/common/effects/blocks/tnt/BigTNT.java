package fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BigTNT implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    float range = 6.0F;
    if (entity != null) {
      entity.getEntityData().func_74757_a("canExplodeCanon", true);
      world.func_72876_a(entity, x, y, z, range, true);
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 3), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE });
    return this;
  }
  
  public String getName() {
    return "big_tnt";
  }
  
  public String getDescription() {
    return "TNT ayant une explosion plus grande que celle de base";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.ENDIUM_TNT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\tnt\BigTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */