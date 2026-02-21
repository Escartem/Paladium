package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FakeObsi implements ObjectEffect {
  private TypeEffects type = TypeEffects.FAKE_OBSIDIAN;
  
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    world.func_147449_b((int)x, (int)y, (int)z, (Block)BlocksRegister.FLUIDS_SULFURICWATER);
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC, 1), new ItemStack(Blocks.field_150343_Z, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC, 1), new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) });
    return this;
  }
  
  public String getName() {
    return "fake_obsi";
  }
  
  public String getDescription() {
    return "Fait apparaître une source de fake water quand l’obsidienne explose";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.FAKE_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\FakeObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */