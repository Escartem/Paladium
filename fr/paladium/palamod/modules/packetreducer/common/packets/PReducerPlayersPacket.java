package fr.paladium.palamod.modules.packetreducer.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.packetreducer.client.PPacketReducerProxyClient;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PReducerPlayersPacket implements IMessage {
  private final List<UUID> itemList;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PReducerPlayersPacket))
      return false; 
    PReducerPlayersPacket other = (PReducerPlayersPacket)o;
    if (!other.canEqual(this))
      return false; 
    Object<UUID> this$itemList = (Object<UUID>)getItemList(), other$itemList = (Object<UUID>)other.getItemList();
    return !((this$itemList == null) ? (other$itemList != null) : !this$itemList.equals(other$itemList));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PReducerPlayersPacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object<UUID> $itemList = (Object<UUID>)getItemList();
    return result * 59 + (($itemList == null) ? 43 : $itemList.hashCode());
  }
  
  public String toString() {
    return "PReducerPlayersPacket(itemList=" + getItemList() + ")";
  }
  
  public PReducerPlayersPacket() {
    this.itemList = new ArrayList<>();
  }
  
  public List<UUID> getItemList() {
    return this.itemList;
  }
  
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {
    PPacketReducerProxyClient client = PPacketReducerProxyClient.getInstance();
    buf.writeInt(client.getEntitiesOnScreen().size());
    for (Integer id : client.getEntitiesOnScreen())
      buf.writeInt(id.intValue()); 
  }
  
  public static class Handler implements IMessageHandler<PReducerPlayersPacket, IMessage> {
    public IMessage onMessage(PReducerPlayersPacket message, MessageContext ctx) {
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\common\packets\PReducerPlayersPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */