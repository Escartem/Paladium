package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketUpdateBiome implements IMessage {
  public int x;
  
  public int z;
  
  public byte biome;
  
  public PacketUpdateBiome() {}
  
  public PacketUpdateBiome(int x, int z, byte biome) {
    this.x = x;
    this.z = z;
    this.biome = biome;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.z = buf.readInt();
    this.biome = buf.readByte();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.z);
    buf.writeByte(this.biome);
  }
  
  public static class Handler implements IMessageHandler<PacketUpdateBiome, IMessage> {
    public IMessage onMessage(PacketUpdateBiome message, MessageContext ctx) {
      ClientProxy.updateBiome(message.x, message.z, message.biome);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUpdateBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */