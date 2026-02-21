package fr.paladium.palavoicechat.client.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

public class RenderPlayerRepoHeadEvent extends Event {
  private final EntityPlayer player;
  
  private float angle;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public float getAngle() {
    return this.angle;
  }
  
  public void setAngle(float angle) {
    this.angle = angle;
  }
  
  public RenderPlayerRepoHeadEvent(EntityPlayer player) {
    this.player = player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\event\RenderPlayerRepoHeadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */