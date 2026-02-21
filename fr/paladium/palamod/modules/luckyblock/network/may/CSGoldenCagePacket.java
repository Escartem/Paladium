package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockGoldenCage;
import io.netty.buffer.ByteBuf;

public class CSGoldenCagePacket implements IMessage {
  private String petName;
  
  private int x;
  
  private int y;
  
  private int z;
  
  public CSGoldenCagePacket(String petName) {
    this.petName = petName;
    this.x = SCGoldenCagePacket.LOCATION.getBlockX();
    this.y = SCGoldenCagePacket.LOCATION.getBlockY();
    this.z = SCGoldenCagePacket.LOCATION.getBlockZ();
  }
  
  public void fromBytes(ByteBuf buf) {
    this.petName = ByteBufUtils.readUTF8String(buf);
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.petName);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<CSGoldenCagePacket, IMessage> {
    public IMessage onMessage(CSGoldenCagePacket message, MessageContext ctx) {
      if (ctx.side != Side.SERVER)
        return null; 
      BlockGoldenCage blockCage = (BlockGoldenCage)BlocksRegister.GOLDEN_CAGE;
      blockCage.changeDefaultPet((ctx.getServerHandler()).field_147369_b, message.petName);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\CSGoldenCagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */