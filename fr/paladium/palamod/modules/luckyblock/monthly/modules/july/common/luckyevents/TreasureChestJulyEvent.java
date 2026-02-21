package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.JulyBiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TreasureChestJulyEvent extends ALuckyEvent {
  public static final String STRUCTURE_NAME = "july";
  
  private static final String EVENT_NAME = "Coffre (au trésor) de juillet";
  
  private static final String TEXTURE_PATH = "july/treasure_chest_july";
  
  private static final int RARITY = 450;
  
  private static final boolean IS_BAD = false;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    JulyBiicodeStructure structure = new JulyBiicodeStructure((EntityPlayer)player);
    if (!structure.canSpawn()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, structure.getFailureMessage("july"));
      StructureCommand.addWaitingPlayer((EntityPlayer)player, "july");
      return;
    } 
    structure.spawn();
  }
  
  public String getName() {
    return "Coffre (au trésor) de juillet";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 450;
  }
  
  public String getTexture() {
    return "july/treasure_chest_july";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\TreasureChestJulyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */