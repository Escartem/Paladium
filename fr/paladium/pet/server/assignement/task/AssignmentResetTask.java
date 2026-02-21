package fr.paladium.pet.server.assignement.task;

import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.pet.common.network.data.PetPlayer;
import java.util.Calendar;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

@Schedule(every = "1s")
public class AssignmentResetTask extends ATask {
  public AssignmentResetTask() {
    super("AssignmentResetTask");
  }
  
  public void run() {
    Calendar calendar = Calendar.getInstance();
    if (!isMidnight(calendar))
      return; 
    long now = System.currentTimeMillis();
    List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
    for (EntityPlayerMP player : players) {
      PetPlayer pet = PetPlayer.get((EntityPlayer)player);
      if (pet == null || !pet.has())
        continue; 
      pet.tryResetAssignment(player, now);
    } 
  }
  
  private boolean isMidnight(Calendar calendar) {
    return (calendar.get(11) == 0 && calendar.get(13) == 0 && calendar.get(12) == 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\task\AssignmentResetTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */