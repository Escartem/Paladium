package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LavaObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    world.func_147449_b((int)x, (int)y, (int)z, (Block)Blocks.field_150356_k);
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack(Items.field_151129_at, 1), new ItemStack(Blocks.field_150343_Z, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack(Items.field_151129_at, 1), new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) });
    return this;
  }
  
  public String getName() {
    return "lava_obsi";
  }
  
  public String getDescription() {
    return "Fait apparaître une source de lave après l’explosion de l’obsidienne";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.LAVA_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\LavaObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */