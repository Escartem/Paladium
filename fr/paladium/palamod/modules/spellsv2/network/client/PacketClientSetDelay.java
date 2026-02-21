package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class PacketClientSetDelay implements IMessage {
  public int spell;
  
  public long date;
  
  public PacketClientSetDelay() {}
  
  public PacketClientSetDelay(int spell, long date) {
    this.spell = spell;
    this.date = date;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.spell = buf.readInt();
    this.date = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.spell);
    buf.writeLong(this.date);
  }
  
  public static class Handler implements IMessageHandler<PacketClientSetDelay, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketClientSetDelay message, MessageContext ctx) {
      ClientManager.addSpellDelay(message.spell, FastUUID.toString((Entity)(Minecraft.func_71410_x()).field_71439_g), message.date);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientSetDelay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */