package fr.paladium.palamod.modules.paladium.network.data;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

@Packet(side = Side.CLIENT)
public class SCPacketPaladiumPlayerDataSync implements IMessage {
  PaladiumPlayer cosmetiques;
  
  String uuid;
  
  public SCPacketPaladiumPlayerDataSync() {}
  
  public SCPacketPaladiumPlayerDataSync(PaladiumPlayer cosmetiques, String uuid) {
    this.cosmetiques = cosmetiques;
    this.uuid = uuid;
  }
  
  public void fromBytes(ByteBuf buf) {
    NBTTagCompound nbt = ByteBufUtils.readTag(buf);
    this.cosmetiques = new PaladiumPlayer();
    this.cosmetiques.loadNBTData(nbt);
    this.uuid = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    NBTTagCompound nbt = new NBTTagCompound();
    this.cosmetiques.saveNBTData(nbt);
    ByteBufUtils.writeTag(buf, nbt);
    ByteBufUtils.writeUTF8String(buf, this.uuid);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketPaladiumPlayerDataSync, IMessage> {
    public IMessage onMessage(SCPacketPaladiumPlayerDataSync message, MessageContext ctx) {
      NBTTagCompound nbt = new NBTTagCompound();
      message.cosmetiques.saveNBTData(nbt);
      EntityPlayer player = (Minecraft.func_71410_x()).field_71441_e.func_152378_a(FastUUID.parseUUID(message.uuid));
      if (player != null) {
        PaladiumPlayer extendData = PaladiumPlayer.get((Entity)player);
        if (extendData != null)
          extendData.loadNBTData(nbt); 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\data\SCPacketPaladiumPlayerDataSync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */