package fr.paladium.palamod.modules.luckyblock.network.may.dialog;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockGoldenCage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class GoldenCageDialogManager extends AbstractDialogManager {
  private static GoldenCageDialogManager instance;
  
  public GoldenCageDialogManager() {
    super("Cage dorée");
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    return CustomDialog.of(this, "Quelle apparence de familier souhaitez-vous obtenir ?")
      .addOptions(getOptions())
      .setCallback(option -> {
          BlockGoldenCage blockCage = (BlockGoldenCage)BlocksRegister.GOLDEN_CAGE;
          if (option.intValue() == 1) {
            blockCage.changeDefaultPet(player, "cat");
          } else if (option.intValue() == 2) {
            blockCage.changeDefaultPet(player, "rabbit");
          } else if (option.intValue() == 3) {
            blockCage.changeDefaultPet(player, "dog");
          } 
        });
  }
  
  private String[] getOptions() {
    BlockGoldenCage blockCage = (BlockGoldenCage)BlocksRegister.GOLDEN_CAGE;
    String[] options = new String[blockCage.getDefaultPets().size()];
    int i = 0;
    for (String pet : blockCage.getDefaultPets().values()) {
      options[i] = pet;
      i++;
    } 
    return options;
  }
  
  public static GoldenCageDialogManager getInstance() {
    if (instance == null)
      instance = new GoldenCageDialogManager(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\dialog\GoldenCageDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */