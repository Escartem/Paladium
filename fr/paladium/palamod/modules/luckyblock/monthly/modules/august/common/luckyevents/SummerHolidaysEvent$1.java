package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCPlanePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palaschematic.utils.Schematic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(SummerHolidaysEvent.access$000());
      MonthlyUtils.playSound(player, "panic");
      PalaMod.network.sendTo((IMessage)new SCPlanePacket(), player);
      Thread.sleep(SummerHolidaysEvent.access$100());
      SummerHolidaysEvent.this.alert(player);
      player.field_70143_R = 0.0F;
      TeleportUtils.teleport((EntityPlayer)player, spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ());
      SchematicUtils.clean(finalSchematic, world, spawnLocation);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SummerHolidaysEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */