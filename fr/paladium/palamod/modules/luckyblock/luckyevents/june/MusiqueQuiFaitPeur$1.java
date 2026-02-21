package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.entity.june.EntityShark;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

class null implements Runnable {
  public void run() {
    try {
      PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("jaws"), player);
      for (int i = 0; i < 58; i++) {
        Vec3 spawnPos = EventUtils.getBlockInFront((EntityPlayer)player, (30 - i / 2));
        entity.func_70634_a(spawnPos.field_72450_a, player.field_70163_u, spawnPos.field_72449_c);
        Thread.sleep(163L);
      } 
      entity.setShowOnlyFin(false);
      Thread.sleep(1000L);
      entity.func_70106_y();
      PlayerUtils.dropItemStackAtEntity((Entity)player, new ItemStack(BlocksRegister.SHARK_PLUSH));
    } catch (InterruptedException interruptedException) {}
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\MusiqueQuiFaitPeur$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */