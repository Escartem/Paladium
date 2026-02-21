package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketCrash;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      PlayerUtils.sendMessage((EntityPlayer)player, "§cMalheureusement nous allons nous quitter d'ici 5 secondes");
      Thread.sleep(1000L);
      PlayerUtils.sendMessage((EntityPlayer)player, "§cContent d'avoir passé du temps avec toi");
      Thread.sleep(1000L);
      PlayerUtils.sendMessage((EntityPlayer)player, "§cPlus que 3 secondes :(");
      Thread.sleep(1000L);
      PlayerUtils.sendMessage((EntityPlayer)player, "§cPlus que 2 secondes :(");
      Thread.sleep(1000L);
      PlayerUtils.sendMessage((EntityPlayer)player, "§cT'es encore là ?");
      Thread.sleep(1000L);
      PlayerUtils.sendMessage((EntityPlayer)player, "§cBye bye...");
      Thread.sleep(1000L);
      PalaMod.getNetwork().sendTo((IMessage)new PacketCrash("Hasta la vista"), player);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Crash$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */