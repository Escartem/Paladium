package fr.paladium.palamod.modules.luckyblock.dialog;

import fr.paladium.helios.utils.TeleportUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityGiantFish;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class GiantFishDialogManager extends AbstractDialogManager {
  private static final String DIALOG_NAME = "Poisson géant";
  
  private static final String DIALOG = "Clique ici pour confirmer l'utilisation du poisson. Fais attention, il va faire des dégâts où il passe !";
  
  private static final String[] ANSWERS = new String[] { "Confirmer", "Refuser" };
  
  private static final String CANT_SPAWN_MESSAGE = "&cVous ne pouvez pas faire apparaître un poisson géant ici.";
  
  private static final String SPAWN_MESSAGE = "&aVous avez fait apparaître un poisson géant à la couche 255.";
  
  private static GiantFishDialogManager instance;
  
  public static GiantFishDialogManager getInstance() {
    return instance;
  }
  
  public GiantFishDialogManager() {
    super("Poisson géant");
    instance = this;
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    ItemStack hand = player.func_70694_bm();
    if (hand == null || hand.func_77973_b() == null || !hand.func_77973_b().equals(ItemsRegister.GIANT_FISH))
      return null; 
    return CustomDialog.of(this, "Clique ici pour confirmer l'utilisation du poisson. Fais attention, il va faire des dégâts où il passe !")
      .addOptions(ANSWERS)
      .setCallback(id -> {
          if (id.intValue() == 1) {
            spawn(player);
            return;
          } 
        });
  }
  
  private void spawn(EntityPlayerMP player) {
    Location location = new Location((Entity)player);
    ItemStack heldItem = player.func_70694_bm();
    if (heldItem == null || heldItem.func_77973_b() == null || !heldItem.func_77973_b().equals(ItemsRegister.GIANT_FISH))
      return; 
    if (!EventUtils.canInteractBlock((EntityPlayer)player, location)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous ne pouvez pas faire apparaître un poisson géant ici." });
      return;
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez fait apparaître un poisson géant à la couche 255." });
    MonthlyUtils.decrementCurrentItem((EntityPlayer)player, heldItem);
    DoubleLocation playerLocation = new DoubleLocation((Entity)player);
    TeleportUtils.teleport((EntityPlayer)player, playerLocation.getX() + 5.0D, 245.0D, playerLocation.getZ() + 5.0D);
    EntityGiantFish giantFish = new EntityGiantFish((EntityPlayer)player);
    player.field_70170_p.func_72838_d((Entity)giantFish);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\dialog\GiantFishDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */