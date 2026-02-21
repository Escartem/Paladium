package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers.JulyConfigManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class LandInSightEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Terre en vue ?";
  
  private static final String TEXTURE_PATH = "july/land_in_sight";
  
  private static final int RARITY = 250;
  
  private static final boolean IS_BAD = false;
  
  private static final String FAILURE_MESSAGE = "&cImpossible de vous téléporter au rafiot !";
  
  private static final String SUCCESS_MESSAGE = "&aVous avez été téléporté au rafiot !";
  
  private final DoubleLocation location;
  
  public LandInSightEvent() {
    JulyConfigManager manager = JulyConfigManager.getInstance();
    this.location = manager.getLocation(manager.getConfig().getLandInSightLocations()).orElse(null);
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (this.location == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cImpossible de vous téléporter au rafiot !" });
      return;
    } 
    this.location.teleportServer((EntityPlayer)player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez été téléporté au rafiot !" });
  }
  
  public String getName() {
    return "Terre en vue ?";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "july/land_in_sight";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\LandInSightEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */