package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.structures;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.impl.BiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SeptemberBiicodeStructure extends BiicodeStructure {
  public static ItemStack[] REWARDS = new ItemStack[] { MonthlyUtils.getEnchantedBook(PLuckyBlock.EXPERIENCE.field_77352_x, 2), new ItemStack(ItemsRegister.JOB_QUEST_POTION, 1), new ItemStack(ItemsRegister.JOB_OFFER, 1), new ItemStack(BlocksRegister.BLOCK_PALADIUM, 5), new ItemStack(BlocksRegister.BLOCK_TITANE, 20), new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 2), new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3) };
  
  public static final int[] CODES = new int[] { 1, 9, 1 };
  
  public SeptemberBiicodeStructure(EntityPlayer player) {
    super(player, CODES, REWARDS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\structures\SeptemberBiicodeStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */