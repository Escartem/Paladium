package fr.paladium.palaforgeutils.lib.server.internal.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.server.ServerType;

public class ServerChangeEvent extends Event {
  private final Side side;
  
  private final ServerType serverType;
  
  public Side getSide() {
    return this.side;
  }
  
  public ServerType getServerType() {
    return this.serverType;
  }
  
  public ServerChangeEvent(Side side, ServerType serverType) {
    this.side = side;
    this.serverType = serverType;
  }
  
  public static class Pre extends ServerChangeEvent {
    public Pre(Side side, ServerType serverType) {
      super(side, serverType);
    }
  }
  
  public static class Post extends ServerChangeEvent {
    public Post(Side side, ServerType serverType) {
      super(side, serverType);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\internal\event\ServerChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */