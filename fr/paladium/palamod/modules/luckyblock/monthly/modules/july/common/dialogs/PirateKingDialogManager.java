package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import java.util.Collections;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PirateKingDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Roi des pirates";
  
  public static final String TEXT = "Mon trésor ? Je vous le laisse si vous voulez. Trouvez-le ! Je l'ai laissé quelque part dans ce monde !";
  
  public PirateKingDialogManager() {
    super("Roi des pirates", Collections.singletonList("Roi des pirates"));
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CustomDialog dialog = new CustomDialog(this.title, "Mon trésor ? Je vous le laisse si vous voulez. Trouvez-le ! Je l'ai laissé quelque part dans ce monde !");
    dialog.setCloseGui();
    dialog.addOption("Recevoir le trésor");
    dialog.addOption("Fermer");
    dialog.setCallback(ret -> {
          if (ret.intValue() == 1)
            giveReward(player, entity); 
        });
    return dialog;
  }
  
  public boolean sendDialog(EntityPlayerMP player, Entity entity) {
    boolean ret = super.sendDialog(player, entity);
    return ret;
  }
  
  private boolean giveReward(EntityPlayerMP player, Entity entity) {
    if (!(entity instanceof EntityNPC))
      return false; 
    World world = player.field_70170_p;
    EntityNPC npc = (EntityNPC)entity;
    ItemUtils.spawnItemAtEntity((Entity)npc, new ItemStack(ItemsRegister.TREASURE_MAP, 1));
    npc.func_70106_y();
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\dialogs\PirateKingDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */