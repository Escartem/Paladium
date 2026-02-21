package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.ShootingStarEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.DialogData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ShootingStarDialogManager extends AbstractDialogManager {
  private static ShootingStarDialogManager instance;
  
  private static final String NAME = "Étoile filante";
  
  public static ShootingStarDialogManager getInstance() {
    return instance;
  }
  
  public ShootingStarDialogManager() {
    super("Étoile filante");
    instance = this;
  }
  
  private static final DialogData DIALOG_DATA = DialogData.builder()
    .dialog("Tu as vu une étoile filante ! Quel vœu souhaites-tu faire ? ")
    .answers(new String[] { "Je souhaite obtenir plus d'expérience", "Je souhaite obtenir plus de puissance", "Je souhaite obtenir plus de richesse", "Je souhaite prendre plus de temps libre" }).build();
  
  public CustomDialog getDialog(final EntityPlayerMP player, Entity entity) {
    ShootingStarEvent instance = ShootingStarEvent.getInstance();
    if (!instance.hasWished(player.func_110124_au()))
      return null; 
    return CustomDialog.of(this, DIALOG_DATA.getDialog())
      .addOptions(DIALOG_DATA.getAnswers())
      .setCallback(id -> {
          instance.removeWished(player.func_110124_au());
          if (id.intValue() == 1) {
            JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
            if (jobsPlayer != null)
              jobsPlayer.addXp(JobType.values()[(new Random()).nextInt((JobType.values()).length)], ObjectiveType.QUEST, 5000.0D, (EntityPlayer)player); 
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous venez de 5000xp dans un métier aléatoire." });
          } else if (id.intValue() == 2) {
            player.func_82242_a(10);
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous venez d'obtenir 10 niveaux d'expérience." });
          } else if (id.intValue() == 3) {
            CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), 7500.0D, "[LuckyBlock] Mars -> Etoile Filante 7500$", new CresusCallback<CresusResponse>() {
                  public void onSuccess(CresusResponse arg0) {
                    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous venez de recevoir 7500$." });
                  }
                  
                  public void onFail(CresusResponse arg0, Throwable arg1) {}
                });
          } else if (id.intValue() == 4) {
            InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.JOB_QUEST_POTION));
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous venez de recevoir une potion de quête métier." });
          } 
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\dialog\ShootingStarDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */