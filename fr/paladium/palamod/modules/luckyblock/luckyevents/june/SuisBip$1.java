package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

class null implements Runnable {
  public void run() {
    try {
      boolean fail = false;
      PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("leger_start"), player);
      Thread.sleep(8250L);
      EventUtils.teleportPlayer(player, playerLocation);
      for (int i = 0; i < 4; i++) {
        if (i != 0)
          PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("leger_bip"), player); 
        Thread.sleep((7000 - i * 1000));
        Cuboid center = (i % 2 == 0) ? secondCenter : firstCenter;
        if (!SuisBip.access$000(SuisBip.this, center, (EntityPlayer)player)) {
          fail = true;
          PlayerUtils.sendMessage((EntityPlayer)player, "§cVous avez perdu !");
        } 
      } 
      PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("leger_end"), player);
      if (fail)
        player.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 2400, 1)); 
      ModuleLuckyEvent.getInstance().getNetwork()
        .sendTo((IMessage)new PacketLuckyEventHelios(false, SuisBip.class.getName()), player);
      locationList.forEach(location -> player.field_70170_p.func_147468_f((int)location.getX(), (int)location.getY(), (int)location.getZ()));
      EventUtils.teleportPlayer(player, spawnLocation);
    } catch (InterruptedException interruptedException) {}
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\SuisBip$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */