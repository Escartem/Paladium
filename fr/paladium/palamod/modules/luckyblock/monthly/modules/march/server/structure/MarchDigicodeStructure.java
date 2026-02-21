package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.structure;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.impl.BiicodeStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MarchDigicodeStructure extends BiicodeStructure {
  public MarchDigicodeStructure(EntityPlayer player) {
    super(player, new int[] { 3, 7, 1 }, new ItemStack[] { new ItemStack(ItemsRegister.MOON_PIECE, 1), new ItemStack(ItemsRegister.GALACTIC_PILE, 1), new ItemStack(ItemsRegister.GALACTIC_PILE, 1), new ItemStack(BlocksRegister.BLOCK_PALADIUM, 4), new ItemStack(BlocksRegister.BLOCK_TITANE, 20), new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 1), new ItemStack(ItemsRegister.SPLASH_POTION_JUPITER_GRAVITY, 1), new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\structure\MarchDigicodeStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */