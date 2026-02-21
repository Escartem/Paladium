package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palashop.provider.cosmetic.common.event.CosmeticExecuteWheelEvent;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common.network.SCPacketEmoteCosmeticExecute;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EmoteCosmeticExecuteServerListener {
  @SubscribeEvent
  public void onEmoteExecute(CosmeticExecuteWheelEvent.Post event) {
    if (event.getCosmetic().getFactory() != EmoteCosmeticFactory.getInstance())
      return; 
    World world = (event.getEntity()).field_70170_p;
    if (!(world instanceof WorldServer))
      return; 
    int entityId = event.getEntity().func_145782_y();
    String emoteId = event.getCosmetic().getId();
    EntityTracker tracker = ((WorldServer)world).func_73039_n();
    for (EntityPlayer entityPlayer : tracker.getTrackingPlayers(event.getEntity())) {
      if (!(entityPlayer instanceof EntityPlayerMP))
        continue; 
      (new SCPacketEmoteCosmeticExecute(entityId, emoteId, false)).send((EntityPlayerMP)entityPlayer);
    } 
    if (event.getEntity() instanceof EntityPlayerMP)
      (new SCPacketEmoteCosmeticExecute(entityId, emoteId, true)).send((EntityPlayerMP)event.getEntity()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\server\listener\EmoteCosmeticExecuteServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */