package fr.paladium.palacommunityevent.server.managers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palacommunityevent.PalaCommunityEventMod;
import fr.paladium.palacommunityevent.common.CommonProxy;
import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.extended.data.CommunityEventData;
import fr.paladium.palacommunityevent.common.network.SCOpenCommunityEventDepositPacket;
import fr.paladium.palacommunityevent.common.network.SCOpenCommunityEventHomePacket;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.ServerProxy;
import fr.paladium.palacommunityevent.server.api.inputs.ProgressInput;
import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.api.callback.SilentRetrofitCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PalaCommunityEventManager {
  public static PalaCommunityEventManager instance;
  
  public List<CommunityEvent> communityEvents = new ArrayList<>();
  
  public List<CommunityEvent> getCommunityEvents() {
    return this.communityEvents;
  }
  
  public HashMap<UUID, String> communityEventGUIBrige = new HashMap<>();
  
  public HashMap<UUID, String> getCommunityEventGUIBrige() {
    return this.communityEventGUIBrige;
  }
  
  public CommunityEvent getCommunityEventById(String id) {
    if (this.communityEvents.contains(new CommunityEvent(id)))
      return this.communityEvents.get(this.communityEvents.indexOf(new CommunityEvent(id))); 
    return null;
  }
  
  public void progress(EntityPlayer player, String eventName, int amount) {
    PalaCommunityEventEEP eep = PalaCommunityEventEEP.get((Entity)player);
    CommunityEventData data = eep.getDataOf(eventName);
    data.progress(amount, player);
  }
  
  public void asyncProgressEvent(EntityPlayerMP player, String eventId, int progress, final IRetrofitCallback<String> call) {
    ServerProxy.getInstance().getApi().progressEvent(new ProgressInput(player.func_110124_au().toString(), eventId, progress)).enqueue(new Callback<String>() {
          public void onResponse(Call<String> callPlayerQuest, Response<String> response) {
            if (!response.isSuccessful()) {
              call.onFail("", new Throwable("Unexcepted response | Code: " + response.code()));
              return;
            } 
            if (response == null || response.body() == null) {
              call.onFail("", new Throwable("Unexcepted response | Code: " + response.code()));
              return;
            } 
            call.onSuccess(response.body());
          }
          
          public void onFailure(Call<String> callPlayerQuest, Throwable error) {
            call.onFail(null, error);
          }
        });
  }
  
  public void asyncGetEventDataById(EntityPlayerMP player, String eventId, final IRetrofitCallback<EventCommunityData> call) {
    ServerProxy.getInstance().getApi().getEventDataById(eventId).enqueue(new Callback<EventCommunityData>() {
          public void onResponse(Call<EventCommunityData> callPlayerQuest, Response<EventCommunityData> response) {
            if (!response.isSuccessful()) {
              call.onFail(new EventCommunityData(), new Throwable("Unexcepted response | Code: " + response.code()));
              return;
            } 
            if (response == null || response.body() == null) {
              call.onFail(new EventCommunityData(), new Throwable("Unexcepted response | Code: " + response.code()));
              return;
            } 
            call.onSuccess(response.body());
          }
          
          public void onFailure(Call<EventCommunityData> callPlayerQuest, Throwable error) {
            call.onFail(null, error);
          }
        });
  }
  
  public void registerCommunityEvent(CommunityEvent communityEvent) {
    if (communityEvent.verify()) {
      this.communityEvents.add(communityEvent);
    } else {
      System.err.println("Can't validate and register community event with id " + communityEvent.id);
    } 
  }
  
  public PalaCommunityEventManager() {
    instance = this;
  }
  
  public static PalaCommunityEventManager getInstance() {
    if (instance == null)
      new PalaCommunityEventManager(); 
    return instance;
  }
  
  public boolean openGui(final EntityPlayer p, final CommunityEvent communityEvent) {
    final PalaCommunityEventEEP eep = PalaCommunityEventEEP.get((Entity)p);
    if (eep == null)
      return false; 
    asyncGetEventDataById((EntityPlayerMP)p, communityEvent.id, (IRetrofitCallback<EventCommunityData>)new SilentRetrofitCallback<EventCommunityData>() {
          public void onSuccess(EventCommunityData data) {
            PalaCommunityEventMod.proxy.getNetwork().sendTo((IMessage)new SCOpenCommunityEventHomePacket(communityEvent, (eep.getDataOf(communityEvent.id)).progress, data.progress), (EntityPlayerMP)p);
          }
          
          public void onFail(EventCommunityData value, Throwable error) {
            super.onFail(value, error);
            error.printStackTrace();
          }
        });
    return true;
  }
  
  public boolean openGuiDeposit(final EntityPlayer p, final CommunityEvent communityEvent) {
    final PalaCommunityEventEEP eep = PalaCommunityEventEEP.get((Entity)p);
    if (eep == null)
      return false; 
    asyncGetEventDataById((EntityPlayerMP)p, communityEvent.id, (IRetrofitCallback<EventCommunityData>)new SilentRetrofitCallback<EventCommunityData>() {
          public void onSuccess(EventCommunityData data) {
            (PalaCommunityEventManager.getInstance()).communityEventGUIBrige.put(p.func_110124_au(), communityEvent.id);
            p.openGui(PalaCommunityEventMod.getInstance(), CommonProxy.COMMUNITYEVENT_DEPOSIT, p.field_70170_p, 0, 0, 0);
            PalaCommunityEventMod.proxy.getNetwork().sendTo((IMessage)new SCOpenCommunityEventDepositPacket(communityEvent, (eep.getDataOf(communityEvent.id)).progress, data.progress), (EntityPlayerMP)p);
          }
        });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\managers\PalaCommunityEventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */