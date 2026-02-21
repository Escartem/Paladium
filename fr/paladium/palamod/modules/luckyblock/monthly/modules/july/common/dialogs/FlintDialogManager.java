package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers.JulyConfigManager;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class FlintDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Capitaine Flint";
  
  public static final String[] TEXTS = new String[] { "Je connais une bonne planque de contrebandiers, tu devrais y jeter un oeil !", "Mon ancien maître avait un nom qui en jette : Long John Sliver ! C'est mieux que le nom de mon nouveau propriétaire : toi !", "Cela fait longtemps que je cherche une île secrète : l'île aux trésors ! J'espère que tu m'y emmèneras un jour !", "Si un jour tu trouves un message dans une bouteille, essaie de le sécher dans un four, peut être qu'il redeviendra lisible" };
  
  private final Random random;
  
  public FlintDialogManager() {
    super("Capitaine Flint", Arrays.asList(new String[] { "Capitaine Flint", "Flint" }));
    this.random = new Random();
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CustomDialog dialog = new CustomDialog(this.title, pickRandomText());
    dialog.setCloseGui();
    return dialog;
  }
  
  public boolean sendDialog(EntityPlayerMP player, Entity entity) {
    boolean ret = super.sendDialog(player, entity);
    return ret;
  }
  
  private String formatCoords(String text) {
    JulyConfigManager manager = JulyConfigManager.getInstance();
    Optional<DoubleLocation> result = manager.getLocation(manager.getConfig().getFlintLocations());
    if (!result.isPresent())
      return text; 
    DoubleLocation location = result.get();
    return text + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
  }
  
  private String pickRandomText() {
    int index = this.random.nextInt(TEXTS.length);
    if (index == 0)
      return formatCoords(TEXTS[index]); 
    return TEXTS[index];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\dialogs\FlintDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */