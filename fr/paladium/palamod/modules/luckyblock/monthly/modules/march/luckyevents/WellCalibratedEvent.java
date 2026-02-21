package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class WellCalibratedEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Bien calibré";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 300;
  
  private static final String TEXTURE_PATH = "march/well_calibrated";
  
  public static final String CANT_ACTIVATE_MESSAGE = "§cTu n'a pas de satellite à calibrer.";
  
  private static final String ACTIVATE_MESSAGE = "§eLa PSA (Paladium Space Agency) te demande de l'aide pour calibrer son satellite. Fais la commande /satellite pour les aider.";
  
  public static final String SUCCESS_MESSAGE = "§aLa PSA te remercie pour tes services. Tu obtiens un unclaim finder orange.";
  
  public static final String FAIL_MESSAGE = "§cTu n'as pas réussi à calibrer le satellite.";
  
  public static final HashSet<UUID> WAITING_PLAYERS = new HashSet<>();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    this;
    addWaitingPlayer(player.func_110124_au());
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eLa PSA (Paladium Space Agency) te demande de l'aide pour calibrer son satellite. Fais la commande /satellite pour les aider." });
  }
  
  public String getName() {
    return "Bien calibré";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "march/well_calibrated";
  }
  
  private static void addWaitingPlayer(UUID uuid) {
    WAITING_PLAYERS.add(uuid);
  }
  
  public static boolean isWaitingPlayer(UUID uuid) {
    return WAITING_PLAYERS.contains(uuid);
  }
  
  public static boolean perform(UUID uuid) {
    if (!isWaitingPlayer(uuid))
      return false; 
    WAITING_PLAYERS.remove(uuid);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\WellCalibratedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */