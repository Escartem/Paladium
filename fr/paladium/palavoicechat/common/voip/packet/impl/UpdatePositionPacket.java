package fr.paladium.palavoicechat.common.voip.packet.impl;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import fr.paladium.palavoicechat.utils.PlayerLocation;
import io.netty.buffer.ByteBuf;
import java.util.List;

public class UpdatePositionPacket implements Packet<UpdatePositionPacket> {
  private List<PlayerLocation> locations;
  
  public UpdatePositionPacket() {}
  
  public UpdatePositionPacket(List<PlayerLocation> locations) {
    this.locations = locations;
  }
  
  public List<PlayerLocation> getLocations() {
    return this.locations;
  }
  
  public UpdatePositionPacket fromBytes(ByteBuf buf) {
    return this;
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.locations.size());
    for (PlayerLocation location : this.locations) {
      ByteBufUtils.writeUUID(buf, location.getPlayerUUID());
      ByteBufUtils.writeUTF8String(buf, location.getPlayerName());
      ByteBufUtils.writeUTF8String(buf, location.getServerName());
      buf.writeInt(location.getDimension());
      buf.writeFloat(location.getPosX());
      buf.writeFloat(location.getPosY());
      buf.writeFloat(location.getPosZ());
      buf.writeBoolean(location.isCanSpeak());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\UpdatePositionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */