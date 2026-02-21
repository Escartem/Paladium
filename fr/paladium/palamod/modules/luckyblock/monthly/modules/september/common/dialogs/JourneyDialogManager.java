package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs;

import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.HolidayPayEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class JourneyDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Journey";
  
  private static final String START_DIALOG = " Quelle destination te ferait plaisir ?";
  
  private static final String ISLAND_REWARD_MESSAGE = "Tu peux désormais effectuer la commande /vacances sur le serveur event pour être téléporté sur l’île des vacances !";
  
  private static final String[] ANSWERS = new String[] { "L’île des vacances", "Le terrain de camping" };
  
  public JourneyDialogManager() {
    super("Journey");
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CustomDialog dialog = (new CustomDialog("Journey", " Quelle destination te ferait plaisir ?")).addOptions(ANSWERS).setCallback(callback -> giveReward(player, callback.intValue(), entity)).setCloseGui();
    return dialog;
  }
  
  public void giveReward(EntityPlayerMP player, int callback, Entity entity) {
    PlayerLuckyBlockProperties properties;
    if (entity == null || entity.field_70128_L)
      return; 
    entity.func_70106_y();
    switch (callback) {
      case 1:
        properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
        if (properties == null)
          return; 
        properties.setOwnHolidayCommand(true);
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu peux désormais effectuer la commande /vacances sur le serveur event pour être téléporté sur l’île des vacances !" });
        break;
      case 2:
        HolidayPayEvent.INSTANCE.getWaitingPlayers().add(player.func_110124_au());
        HolidayPayEvent.INSTANCE.handle(player);
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\JourneyDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */