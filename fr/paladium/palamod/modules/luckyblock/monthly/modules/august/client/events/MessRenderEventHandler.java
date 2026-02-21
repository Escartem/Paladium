package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraftforge.client.event.FOVUpdateEvent;

public class MessRenderEventHandler {
  private static final float FOV_MODIFIER = 1.2F;
  
  public static MessRenderEventHandler INSTANCE;
  
  private long messExpirationMillis;
  
  private long planeExpirationMillis;
  
  public void setMessExpirationMillis(long messExpirationMillis) {
    this.messExpirationMillis = messExpirationMillis;
  }
  
  public void setPlaneExpirationMillis(long planeExpirationMillis) {
    this.planeExpirationMillis = planeExpirationMillis;
  }
  
  public long getMessExpirationMillis() {
    return this.messExpirationMillis;
  }
  
  public long getPlaneExpirationMillis() {
    return this.planeExpirationMillis;
  }
  
  public MessRenderEventHandler() {
    INSTANCE = this;
    this.messExpirationMillis = 0L;
    this.planeExpirationMillis = 0L;
  }
  
  @SubscribeEvent
  public void onFovUpdate(FOVUpdateEvent e) {
    long now = System.currentTimeMillis();
    if (now > this.messExpirationMillis && now > this.planeExpirationMillis)
      return; 
    Random random = e.entity.field_70170_p.field_73012_v;
    e.entity.field_70726_aT = random.nextFloat() / 1.2F;
    e.entity.field_71109_bG = random.nextFloat() / 20.0F / 1.2F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\events\MessRenderEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */