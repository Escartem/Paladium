package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCMessPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCDarkScreenPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    try {
      DoubleLocation location = new DoubleLocation((Entity)player);
      TimedSchematic schematic = new TimedSchematic(-1L, location, "lb09_cockpit.schematic");
      World world = player.field_70170_p;
      schematic.paste(player, false);
      MonthlyUtils.playSound(player, "rocket");
      Thread.sleep(TimeUnit.SECONDS.toMillis(10L));
      PalaMod.network.sendTo((IMessage)new SCMessPacket(TimeUnit.SECONDS.toMillis(3L)), player);
      Thread.sleep(TimeUnit.SECONDS.toMillis(3L));
      SeptemberCommonModule.INSTANCE.getNetwork().sendTo((IMessage)new SCDarkScreenPacket(TimeUnit.SECONDS.toMillis(3L)), player);
      DoubleLocation topLocation = new DoubleLocation(player.field_70165_t, player.field_70163_u + 100.0D, player.field_70161_v, player.field_70177_z, player.field_70125_A);
      int blockX = topLocation.getBlockX();
      int blockY = topLocation.getBlockY();
      int blockZ = topLocation.getBlockZ();
      schematic.remove(world);
      TimedSchematic moonSchematic = new TimedSchematic(-1L, topLocation, "lb09_lune.schematic");
      moonSchematic.paste(player, false);
      if (moonSchematic.getSchematic() != null && EventUtils.canInteract((EntityPlayer)player, blockX, blockY, blockZ)) {
        world.func_147449_b(blockX, blockY, blockZ, (Block)Blocks.field_150349_c);
        topLocation.clone().add(0.0D, 2.0D, 0.0D).teleportServer((EntityPlayer)player);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\DreamJobEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */