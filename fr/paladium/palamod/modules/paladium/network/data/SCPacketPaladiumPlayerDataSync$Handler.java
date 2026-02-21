package fr.paladium.palamod.modules.paladium.network.data;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketPaladiumPlayerDataSync, IMessage> {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\data\SCPacketPaladiumPlayerDataSync$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */