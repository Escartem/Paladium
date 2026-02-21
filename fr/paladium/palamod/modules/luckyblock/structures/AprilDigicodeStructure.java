package fr.paladium.palamod.modules.luckyblock.structures;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.impl.BiicodeStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class AprilDigicodeStructure extends BiicodeStructure {
  public AprilDigicodeStructure(EntityPlayer player) {
    super(player, new int[] { 7, 1, 9 }, new ItemStack[] { new ItemStack(ItemsRegister.RAINBOW_LEATHER, 3), new ItemStack(ItemsRegister.CHOCOLATE_EGG, 10), new ItemStack(ItemsRegister.GOLDEN_EGG, 3), new ItemStack(BlocksRegister.BLOCK_PALADIUM, 3), new ItemStack(Blocks.field_150340_R, 5), new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 3), new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\AprilDigicodeStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */