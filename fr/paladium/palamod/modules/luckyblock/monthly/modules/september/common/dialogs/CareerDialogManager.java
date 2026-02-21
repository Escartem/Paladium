package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs;

import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.CareerEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.CareerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CareerDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Carrière";
  
  private static final String[] DIALOGS = new String[] { "C'est l'heure de faire carrière ! Te sens tu prêt pour miner 40 stones en 30 secondes ?", "C'est l'heure de faire carrière ! Te sens tu prêt pour miner 50 stones en 30 secondes ? Si tu sens que cette carrière n'est pas pour toi, tu peux t'arrêter dès maintenant.", "C'est l'heure de faire carrière ! Te sens tu prêt pour miner 60 stones en 30 secondes ? Si tu sens que cette carrière n'est pas pour toi, tu peux t'arrêter dès maintenant.", "C'est l'heure de faire carrière ! Te sens tu prêt pour miner 75 stones en 30 secondes ? Si tu sens que cette carrière n'est pas pour toi, tu peux t'arrêter dès maintenant.", "Félicitations, tu as fait une belle carrière ! Tu mérites bien ces quelques blocs !" };
  
  private static final int[] OBJECTIVES = new int[] { 40, 50, 60, 75 };
  
  private static final String[] ANSWERS = new String[] { "OUI", "NON" };
  
  public CareerDialogManager() {
    super("Carrière");
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CareerData data = CareerEvent.INSTANCE.get(player);
    if (data == null || !entity.func_110124_au().equals(data.getEntityUniqueId()))
      return null; 
    if (data.isStarted())
      return null; 
    CustomDialog dialog = (new CustomDialog("Carrière", DIALOGS[data.getCareerLevel()])).addOptions(ANSWERS).setCallback(callback -> handleEvent(player, entity, data, callback.intValue())).setCloseGui();
    return dialog;
  }
  
  public void handleEvent(EntityPlayerMP player, Entity entity, CareerData data, int callback) {
    if (entity == null || entity.field_70128_L)
      return; 
    if (data.isStarted() || data.isGived())
      return; 
    if (data.getCareerLevel() == 4) {
      data.setExpirationMillis(0L);
      data.giveReward(player);
      entity.func_70106_y();
      return;
    } 
    if (callback == 1) {
      data.startWave(player, OBJECTIVES[data.getCareerLevel()]);
    } else {
      data.setExpirationMillis(0L);
      data.giveReward(player);
    } 
    entity.func_70106_y();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\CareerDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */