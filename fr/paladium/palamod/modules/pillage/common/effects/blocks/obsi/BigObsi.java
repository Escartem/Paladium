package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BigObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    world.func_147449_b((int)x, (int)y, (int)z, Blocks.field_150343_Z);
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 4), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150343_Z, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE });
    GameRegistry.addRecipe(new ItemStack(block, 4), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), BlocksRegister.HARDENED_OBSIDIAN, 
          Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE });
    return this;
  }
  
  public String getName() {
    return "big_obsi";
  }
  
  public String getDescription() {
    return "Obsidienne qui repose un bloc d’obsi quand celle-ci est cassée";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.BIG_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\BigObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */