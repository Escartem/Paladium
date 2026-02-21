package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.SandmanEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCDarkScreenPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class SandmanDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Sandman";
  
  private static final String FIRST_DIALOG = "Ça te dit de faire affaire avec moi ? Je te propose :";
  
  private static final String[] ANSWERS = new String[] { "Un château de sable contre 1000$", "16 sables mouvants” contre 1000$", "2048 sables contre 500$", "12 sable des âmes contre 750$", "Aucun objet" };
  
  private static final String WITHDRAW_MESSAGE = "&aVous venez de payer %s$ !";
  
  private static final String UNSUCCESSFUL_WITHDRAW_MESSAGE = "&cVous n'avez pas assez d'argent pour payer !";
  
  private static final String COMMAND_WARNING_MESSAGE = "&eUtilisez la commande &c/sandcastle &epour faire apparaître votre château de sable !";
  
  private static final double[] PRICES = new double[] { 1000.0D, 1000.0D, 500.0D, 750.0D, -1.0D };
  
  private final List<UUID> finishedMobs;
  
  public SandmanDialogManager() {
    super("Sandman", Collections.singletonList("Sandman"));
    this.finishedMobs = new ArrayList<>();
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CustomDialog dialog = (new CustomDialog("Sandman", "Ça te dit de faire affaire avec moi ? Je te propose :")).addOptions(ANSWERS).setCallback(callback -> {
          if (callback.intValue() < 1 || callback.intValue() > ANSWERS.length)
            return; 
          giveReward(player, entity, callback.intValue() - 1);
        }).setCloseGui();
    return dialog;
  }
  
  public void giveReward(EntityPlayerMP player, Entity entity, int index) {
    if (player.field_70170_p.field_72995_K)
      return; 
    if (entity == null || entity.field_70128_L)
      return; 
    if (this.finishedMobs.contains(entity.func_110124_au()))
      return; 
    double price = PRICES[index];
    if (price <= 0.0D) {
      handleTask(player, entity, index);
      this.finishedMobs.add(entity.func_110124_au());
      return;
    } 
    removeMoney(player, entity, index, price);
  }
  
  public void handleTask(final EntityPlayerMP player, final Entity entity, final int index) {
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              int i;
              if (player == null || player.field_70128_L || entity == null || entity.field_70128_L)
                return; 
              SeptemberCommonModule.INSTANCE.getNetwork().sendTo((IMessage)new SCDarkScreenPacket(SCDarkScreenPacket.FIRST_DARK_DURATION), player);
              MonthlyUtils.playSound(player, "sandman");
              Thread.sleep(TimeUnit.SECONDS.toMillis(2L));
              SeptemberCommonModule.INSTANCE.getNetwork().sendTo((IMessage)new SCDarkScreenPacket(SCDarkScreenPacket.SECOND_DARK_DURATION), player);
              Thread.sleep(SCDarkScreenPacket.SECOND_DARK_DURATION);
              switch (index + 1) {
                case 1:
                  MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eUtilisez la commande &c/sandcastle &epour faire apparaître votre château de sable !" });
                  SandmanEvent.INSTANCE.addWaitingPlayer(player.func_110124_au());
                  break;
                case 2:
                  ItemUtils.spawnItemAtEntity(entity, new ItemStack(BlocksRegister.QUICKSAND, 16));
                  break;
                case 3:
                  for (i = 0; i < 32; i++)
                    ItemUtils.spawnItemAtEntity(entity, new ItemStack((Block)Blocks.field_150354_m, 64)); 
                  break;
                case 4:
                  ItemUtils.spawnItemAtEntity(entity, new ItemStack(Blocks.field_150425_aM, 12));
                  break;
              } 
              entity.func_70106_y();
            } catch (Exception e) {
              e.printStackTrace();
            } 
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public void removeMoney(final EntityPlayerMP player, final Entity entity, final int index, final double amount) {
    CresusCallback<CresusResponse> callback = new CresusCallback<CresusResponse>() {
        public void onSuccess(CresusResponse cresusResponse) {
          if (!cresusResponse.transactionSuccess()) {
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
            return;
          } 
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&aVous venez de payer %s$ !", new Object[] { Double.valueOf(this.val$amount) }) });
          SandmanDialogManager.this.handleTask(player, entity, index);
        }
        
        public void onFail(CresusResponse cresusResponse, Throwable throwable) {
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour payer !" });
        }
      };
    CresusManager.getInstance().withdrawPlayerAsync(player
        .func_110124_au(), amount, "Sandman", callback);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\SandmanDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */