package fr.paladium.pet.server.assignement.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.assignement.AssignmentManager;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AssignmentTickListener {
  public static final int TICKS_PER_SECOND = 20;
  
  private static final AssignmentType[] EVERY_SECOND_ASSIGNMENT_TYPES = new AssignmentType[] { AssignmentType.WATER, AssignmentType.WALK, AssignmentType.ANGELIC_WATER, AssignmentType.LIGHT, AssignmentType.DARK, AssignmentType.SLEEP, AssignmentType.CONNECTION };
  
  private int tick = 0;
  
  private AssignmentManager manager = AssignmentManager.getInstance();
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (!canHandle(event))
      return; 
    AssignmentConfig config = this.manager.getConfig();
    if (config == null)
      return; 
    PlayerUtils.getOnlinePlayers().forEach(player -> this.manager.performAssignments(player, PetPlayer.get((EntityPlayer)player), null, EVERY_SECOND_ASSIGNMENT_TYPES));
  }
  
  private boolean canHandle(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return false; 
    if (this.tick++ < 20)
      return false; 
    this.tick = 0;
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\listener\AssignmentTickListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */