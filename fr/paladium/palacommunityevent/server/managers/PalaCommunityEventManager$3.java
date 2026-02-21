package fr.paladium.palacommunityevent.server.managers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palacommunityevent.PalaCommunityEventMod;
import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.network.SCOpenCommunityEventHomePacket;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import fr.paladium.palaforgeutils.lib.api.callback.SilentRetrofitCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null extends SilentRetrofitCallback<EventCommunityData> {
  public void onSuccess(EventCommunityData data) {
    PalaCommunityEventMod.proxy.getNetwork().sendTo((IMessage)new SCOpenCommunityEventHomePacket(communityEvent, (eep.getDataOf(communityEvent.id)).progress, data.progress), (EntityPlayerMP)p);
  }
  
  public void onFail(EventCommunityData value, Throwable error) {
    super.onFail(value, error);
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\managers\PalaCommunityEventManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */