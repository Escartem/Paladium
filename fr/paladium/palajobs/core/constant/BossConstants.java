package fr.paladium.palajobs.core.constant;

import fr.paladium.palajobs.api.type.JobType;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BossConstants {
  public static final Map<JobType, ItemStack> BOSS_ITEM = new HashMap<>();
  
  public static final Map<JobType, Integer> BOSS_QUANTITY = new HashMap<>();
  
  static {
    BOSS_ITEM.put(JobType.FARMER, new ItemStack(Items.field_151174_bG));
    BOSS_ITEM.put(JobType.HUNTER, new ItemStack(Items.field_151078_bh));
    BOSS_ITEM.put(JobType.MINER, new ItemStack(Blocks.field_150347_e));
    BOSS_ITEM.put(JobType.ALCHEMIST, new ItemStack((Block)Blocks.field_150328_O));
    BOSS_QUANTITY.put(JobType.FARMER, Integer.valueOf(250000));
    BOSS_QUANTITY.put(JobType.HUNTER, Integer.valueOf(1000000));
    BOSS_QUANTITY.put(JobType.MINER, Integer.valueOf(5000000));
    BOSS_QUANTITY.put(JobType.ALCHEMIST, Integer.valueOf(200000));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\constant\BossConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */