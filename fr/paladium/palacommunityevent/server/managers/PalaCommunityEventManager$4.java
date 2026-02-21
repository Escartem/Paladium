package fr.paladium.palacommunityevent.server.managers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palacommunityevent.PalaCommunityEventMod;
import fr.paladium.palacommunityevent.common.CommonProxy;
import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.network.SCOpenCommunityEventDepositPacket;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import fr.paladium.palaforgeutils.lib.api.callback.SilentRetrofitCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null extends SilentRetrofitCallback<EventCommunityData> {
  public void onSuccess(EventCommunityData data) {
    (PalaCommunityEventManager.getInstance()).communityEventGUIBrige.put(p.func_110124_au(), communityEvent.id);
    p.openGui(PalaCommunityEventMod.getInstance(), CommonProxy.COMMUNITYEVENT_DEPOSIT, p.field_70170_p, 0, 0, 0);
    PalaCommunityEventMod.proxy.getNetwork().sendTo((IMessage)new SCOpenCommunityEventDepositPacket(communityEvent, (eep.getDataOf(communityEvent.id)).progress, data.progress), (EntityPlayerMP)p);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\managers\PalaCommunityEventManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */