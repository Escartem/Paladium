package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import io.netty.buffer.ByteBuf;

public class PacketClientActiveSpell implements IMessage {
  public int spell;
  
  public String uuid;
  
  public boolean active;
  
  public PacketClientActiveSpell() {}
  
  public PacketClientActiveSpell(int spell, String uuid, boolean active) {
    this.spell = spell;
    this.uuid = uuid;
    this.active = active;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.spell = buf.readInt();
    this.uuid = ByteBufUtils.readUTF8String(buf);
    this.active = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.spell);
    ByteBufUtils.writeUTF8String(buf, this.uuid);
    buf.writeBoolean(this.active);
  }
  
  public static class Handler implements IMessageHandler<PacketClientActiveSpell, IMessage> {
    public IMessage onMessage(PacketClientActiveSpell message, MessageContext ctx) {
      if (message.active) {
        ClientManager.addActiveSpell(message.spell, message.uuid);
      } else {
        ClientManager.removeActiveSpell(message.spell, message.uuid);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientActiveSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */