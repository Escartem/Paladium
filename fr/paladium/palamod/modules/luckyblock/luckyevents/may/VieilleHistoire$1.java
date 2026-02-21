package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenBookSeller;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

class null extends EntityNPC {
  null(World world, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract) {
    super(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (playerId.equals(FastUUID.toString((Entity)player))) {
      this.interact = true;
      CustomDialog mainDiag = new CustomDialog("CONTEUR", "J'ai une histoire pour toi !");
      List<String> pages = VieilleHistoire.splitStory(id);
      List<CustomDialog> dialogs = new ArrayList<>();
      for (int i = 0; i < pages.size(); i++) {
        CustomDialog dialog = new CustomDialog("CONTEUR", pages.get(i));
        if (i == 0) {
          mainDiag.setCallback(v1 -> VieilleHistoire.this.sendDialog(dialog, (EntityPlayerMP)player));
        } else {
          ((CustomDialog)dialogs.get(i - 1)).setCallback(v1 -> VieilleHistoire.this.sendDialog(dialog, (EntityPlayerMP)player));
        } 
        dialogs.add(dialog);
      } 
      CustomDialog winDialog = new CustomDialog("CONTEUR", "Félicitations, c'est la bonne réponse ! Voici le livre de cette histoire en récompense.");
      winDialog.setCloseGui();
      winDialog.setCallback(v1 -> {
            VieilleHistoire.WAITING_PLAYERS.add(player.func_110124_au());
            PalaMod.network.sendTo((IMessage)new PacketOpenBookSeller(), (EntityPlayerMP)player);
            func_70106_y();
          });
      CustomDialog loseDialog = new CustomDialog("CONTEUR", "Perdu, ce n'est pas la bonne réponse !");
      loseDialog.setCloseGui();
      loseDialog.setCallback(v1 -> func_70106_y());
      CustomDialog questionDialog = new CustomDialog("CONTEUR", "Quelle est la morale de cette histoire ?");
      questionDialog.addOption(VieilleHistoire.answers[id][0]);
      questionDialog.addOption(VieilleHistoire.answers[id][1]);
      questionDialog.addOption(VieilleHistoire.answers[id][2]);
      questionDialog.setCallback(v1 -> {
            if (v1.intValue() == VieilleHistoire.solutions[id]) {
              VieilleHistoire.this.sendDialog(winDialog, (EntityPlayerMP)player);
            } else {
              VieilleHistoire.this.sendDialog(loseDialog, (EntityPlayerMP)player);
            } 
          });
      ((CustomDialog)dialogs.get(dialogs.size() - 1)).setCallback(v1 -> VieilleHistoire.this.sendDialog(questionDialog, (EntityPlayerMP)player));
      VieilleHistoire.this.sendDialog(mainDiag, (EntityPlayerMP)player);
      return true;
    } 
    PlayerUtils.sendMessage(player, "Ce conteur n'a pas été activé par vous.");
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\VieilleHistoire$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */