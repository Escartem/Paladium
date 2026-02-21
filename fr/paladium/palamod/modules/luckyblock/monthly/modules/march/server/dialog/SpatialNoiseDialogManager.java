package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog;

import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.DialogData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SpatialNoiseDialogManager extends AbstractDialogManager {
  private static SpatialNoiseDialogManager instance;
  
  public static SpatialNoiseDialogManager getInstance() {
    return instance;
  }
  
  public static final HashMap<UUID, Integer> PLAYER_STEP = new HashMap<>();
  
  private static final String NAME = "Bruit Spatial";
  
  private static final DialogData INITIAL_DIALOG_DATA = DialogData.builder()
    .dialog("La PSA (Paladium Space Agency) te demande de décrypter le message de ses astronautes.")
    .answers(new String[] { "Commencer le décryptage" }).build();
  
  private static final DialogData STEP1_DIALOG_DATA = DialogData.builder()
    .dialog("Quel son as-tu entendu ?")
    .answers(new String[] { "Petit pas", "E.T. pas", "Petit pois" }).build();
  
  private static final DialogData STEP2_DIALOG_DATA = DialogData.builder()
    .dialog("Quel son as-tu entendu ?")
    .answers(new String[] { "Pomme rend", "Homme cran", "Homme grand" }).build();
  
  private static final DialogData STEP3_DIALOG_DATA = DialogData.builder()
    .dialog("Quel son as-tu entendu ?")
    .answers(new String[] { "Poids homme allité", "Chat humanité", "Pas humanité" }).build();
  
  public SpatialNoiseDialogManager() {
    super("Bruit Spatial");
    instance = this;
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    if (!PLAYER_STEP.containsKey(player.func_110124_au()))
      return null; 
    CustomDialog dialog = setupInitialDialog();
    dialog.setCallback(id -> handleInitialResponse(player, id.intValue()));
    return dialog;
  }
  
  private CustomDialog setupInitialDialog() {
    return CustomDialog.of(this, INITIAL_DIALOG_DATA.getDialog())
      .addOptions(INITIAL_DIALOG_DATA.getAnswers());
  }
  
  private void handleInitialResponse(EntityPlayerMP player, int id) {
    int step = ((Integer)PLAYER_STEP.get(player.func_110124_au())).intValue();
    if (step == 0 && id == 1) {
      proceedToNextStep(player, 1);
      sendStep1Dialog(player);
    } else {
      fail(player);
    } 
  }
  
  private void sendStep1Dialog(EntityPlayerMP player) {
    CustomDialog step1Dialog = CustomDialog.of(this, STEP1_DIALOG_DATA.getDialog()).addOptions(STEP1_DIALOG_DATA.getAnswers());
    MonthlyUtils.playSound(player, "spatial_1");
    step1Dialog.setCallback(c -> {
          int step = ((Integer)PLAYER_STEP.get(player.func_110124_au())).intValue();
          if (step == 1 && c.intValue() == 1) {
            proceedToNextStep(player, 2);
            sendStep2Dialog(player);
          } else {
            fail(player);
          } 
        });
    sendOtherDialog(player, step1Dialog);
  }
  
  private void sendStep2Dialog(EntityPlayerMP player) {
    CustomDialog step1Dialog = CustomDialog.of(this, STEP2_DIALOG_DATA.getDialog()).addOptions(STEP2_DIALOG_DATA.getAnswers());
    MonthlyUtils.playSound(player, "spatial_2");
    step1Dialog.setCallback(c -> {
          int step = ((Integer)PLAYER_STEP.get(player.func_110124_au())).intValue();
          if (step == 2 && c.intValue() == 3) {
            proceedToNextStep(player, 3);
            sendStep3Dialog(player);
          } else {
            fail(player);
          } 
        });
    sendOtherDialog(player, step1Dialog);
  }
  
  private void sendStep3Dialog(EntityPlayerMP player) {
    CustomDialog step1Dialog = CustomDialog.of(this, STEP3_DIALOG_DATA.getDialog()).addOptions(STEP3_DIALOG_DATA.getAnswers());
    MonthlyUtils.playSound(player, "spatial_3");
    step1Dialog.setCallback(c -> {
          int step = ((Integer)PLAYER_STEP.get(player.func_110124_au())).intValue();
          if (step == 3 && c.intValue() == 3) {
            success(player);
          } else {
            fail(player);
          } 
        });
    sendOtherDialog(player, step1Dialog);
  }
  
  private void success(EntityPlayerMP player) {
    PLAYER_STEP.remove(player.func_110124_au());
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aFélicitations, tu as décrypté tous les messages !" });
  }
  
  private void fail(EntityPlayerMP player) {
    MonthlyUtils.playSound(player, "soft_fail");
    PLAYER_STEP.remove(player.func_110124_au());
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi à décoder le message, mission échouée." });
  }
  
  private void proceedToNextStep(EntityPlayerMP player, int nextStep) {
    PLAYER_STEP.put(player.func_110124_au(), Integer.valueOf(nextStep));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\dialog\SpatialNoiseDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */