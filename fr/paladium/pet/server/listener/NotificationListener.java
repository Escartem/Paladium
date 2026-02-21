package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.assignment.AssignmentFinishEvent;
import fr.paladium.pet.common.event.level.PetLevelUpEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.pet.BBOpenUIPacket;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class NotificationListener {
  @SubscribeEvent
  public void onLevelUp(PetLevelUpEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    S29PacketSoundEffect packet = new S29PacketSoundEffect("palajobs:palajobs.levelup", player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    player.field_71135_a.func_147359_a((Packet)packet);
    MinecraftTitle title = new MinecraftTitle(PetTranslateEnum.NOTIFICATION_TITLE.text(), PetTranslateEnum.NOTIFICATION_LEVEL_UP.text((EntityPlayer)player, new Object[] { Integer.valueOf(event.getNewLevel()) }), 1000L, TimeUnit.SECONDS.toMillis(3L), 1000L);
    ModuleTitle.getInstance().sendTitle(title, player);
    int oldSlots = event.getCurrentSlots();
    int newSlots = event.getNewSlots();
    if (oldSlots < newSlots)
      PetTranslateEnum.NOTIFICATION_SLOT_UNLOCKED.notification(player, new Object[] { Integer.valueOf(newSlots) }); 
  }
  
  @SubscribeEvent
  public void onAssignmentFinish(AssignmentFinishEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    PetTranslateEnum.NOTIFICATION_ASSIGNMENT_FINISH.notification(player, new Object[] { event.getAssignment().getName((EntityPlayer)player) });
    if (event.getAssignment().getType().equals(AssignmentType.ITEM))
      PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBOpenUIPacket(player, PetPlayer.get((EntityPlayer)player)), player); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\NotificationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */