package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.SandmanEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCDarkScreenPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

class null implements Runnable {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\SandmanDialogManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */