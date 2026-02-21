package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.AquariumStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class FeedTheFishEvent extends ALuckyEvent {
  public static final String LOCATION_NOT_FOUND = "&cAucun emplacement n'a été trouvé sur ce serveur.";
  
  public static final String TELEPORT_SUCCESS = "&aVous avez été téléporté à l'emplacement de l'événement.";
  
  public static final String STRUCTURE_NAME = "aquarium";
  
  private static final String EVENT_NAME = "Tu vas nourrir les poissons !";
  
  private static final String TEXTURE_PATH = "july/feed_the_fish";
  
  private static final int RARITY = 350;
  
  private static final boolean IS_BAD = true;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    AquariumStructure structure = new AquariumStructure((EntityPlayer)player);
    if (!structure.canSpawn()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, structure.getFailureMessage("aquarium"));
      StructureCommand.addWaitingPlayer((EntityPlayer)player, "aquarium");
      return;
    } 
    structure.spawn();
  }
  
  public String getName() {
    return "Tu vas nourrir les poissons !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "july/feed_the_fish";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\FeedTheFishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */