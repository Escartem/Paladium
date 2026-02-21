package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.impl.BiicodeStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class JulyBiicodeStructure extends BiicodeStructure {
  public static ItemStack[] REWARDS = new ItemStack[] { new ItemStack(ItemsRegister.ORANGE_CONSUMABLE, 5), new ItemStack(BlocksRegister.BARREL, 5), new ItemStack(BlocksRegister.BLOCK_PALADIUM, 3), new ItemStack(Blocks.field_150340_R, 10), new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 2), new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3) };
  
  public static final int[] CODES = new int[] { 7, 7, 7 };
  
  public JulyBiicodeStructure(EntityPlayer player) {
    super(player, CODES, REWARDS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\structures\JulyBiicodeStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */