package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.pet.BBUpdateClientSkillValuesPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SkillSyncListener {
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    BBUpdateClientSkillValuesPacket packet = new BBUpdateClientSkillValuesPacket(pet);
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)packet, player);
  }
  
  @SubscribeEvent
  public void onPetUpdate(PetStatChangeEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null)
      return; 
    UpdateStatType type = event.getType();
    if (!type.needUpdate())
      return; 
    BBUpdateClientSkillValuesPacket packet = new BBUpdateClientSkillValuesPacket(pet);
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)packet, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\SkillSyncListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */