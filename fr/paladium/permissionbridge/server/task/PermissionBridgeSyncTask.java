package fr.paladium.permissionbridge.server.task;

import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

@Schedule(every = "1m")
public class PermissionBridgeSyncTask extends ATask {
  public PermissionBridgeSyncTask() {
    super("permission-bridge-sync-task");
    start();
  }
  
  public void run() {
    for (Object playerObj : (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b) {
      if (!(playerObj instanceof EntityPlayerMP))
        continue; 
      EntityPlayerMP player = (EntityPlayerMP)playerObj;
      PermissionManager.inst().sync(player);
    } 
  }
  
  public static void create() {
    new PermissionBridgeSyncTask();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\server\task\PermissionBridgeSyncTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */