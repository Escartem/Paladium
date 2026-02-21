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

public class RedstoneObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity placer) {}
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack(Items.field_151137_ax, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack(Items.field_151137_ax, 1) });
    return this;
  }
  
  public String getName() {
    return "redstone_obsi";
  }
  
  public String getDescription() {
    return "A toutes les propriétés de l’obsidienne mais permet de transporter la redstone";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.REDSTONE_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\RedstoneObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */