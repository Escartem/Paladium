package fr.paladium.palajobs.api.constant;

import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestPlayerData;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class JobsConstants {
  public static final String MOD_PREFIX = "palajobs:";
  
  public static final String JOBS_MESSAGE_PREFIX = "§7[§aJobs§7]§r ";
  
  public static final double BOAT_SPEED_MULTIPLIER = 3.0D;
  
  public static final String POTION_NAME = "jobs.exp";
  
  public static final int DAILY_QUESTS_AMOUNT = 3;
  
  public static final double MAX_DAILY_MULTIPLIER = 3.0D;
  
  public static final double PER_DAY_QUEST_MULTIPLIER = 1.0D;
  
  public static final long ROOT_UPDATE_DELAY = TimeUnit.SECONDS.toMillis(1L);
  
  public static final int ROOT_GROWTH_CHANCE = 1;
  
  public static final int ROOT_GROWTH_MAX = 90;
  
  public static final Block[] ROOT_BLACKLISTED_BLOCKS = new Block[] { Blocks.field_150357_h, Blocks.field_150343_Z };
  
  public static final String TITAN_PERMISSION = "palajobs.titan";
  
  public static final Double TITAN_MULTIPLIER = Double.valueOf(5.0D);
  
  public static final String PALADIN_PERMISSION = "palajobs.paladin";
  
  public static final Double PALADIN_MULTIPLIER = Double.valueOf(10.0D);
  
  public static final String ENDIUM_PERMISSION = "palajobs.endium";
  
  public static final Double ENDIUM_MULTIPLIER = Double.valueOf(15.0D);
  
  public static final String CRAFTING_NOT_ALLOWED = "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour craft ceci.";
  
  public static final String USE_NOT_ALLOWED = "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour utiliser ceci.";
  
  public static String getQuestAdvancementMessage(AbstractQuest quest, QuestPlayerData data) {
    return "§7[§aJobs§7]§r §6" + quest.getName() + " §e=> §6" + data.getProgress() + "§7/§c" + quest.getQuantity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\constant\JobsConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */