package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.impl.BiicodeStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AugustBiicodeStructure extends BiicodeStructure {
  public static ItemStack[] REWARDS = new ItemStack[] { new ItemStack(ItemsRegister.SUMMER_WATER_BOTTLE, 1), new ItemStack(ItemsRegister.SUMMER_WATER_BOTTLE, 1), new ItemStack(ItemsRegister.SUMMER_WATER_BOTTLE, 1), new ItemStack(ItemsRegister.SOLAR_CREAM, 3), new ItemStack(ItemsRegister.SUMMER_INSTRUCTIONS, 1), new ItemStack(ItemsRegister.JOB_QUEST_POTION, 1), new ItemStack(BlocksRegister.BLOCK_PALADIUM, 5), new ItemStack(BlocksRegister.BLOCK_TITANE, 20), new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 2), new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3) };
  
  public static final int[] CODES = new int[] { 9, 7, 4 };
  
  public AugustBiicodeStructure(EntityPlayer player) {
    super(player, CODES, REWARDS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\structures\AugustBiicodeStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */