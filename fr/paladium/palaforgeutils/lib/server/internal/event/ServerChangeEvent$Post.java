package fr.paladium.palaforgeutils.lib.server.internal.event;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.server.ServerType;

public class Post extends ServerChangeEvent {
  public Post(Side side, ServerType serverType) {
    super(side, serverType);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\internal\event\ServerChangeEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */