package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import net.minecraft.client.Minecraft;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketUpdateInventory, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketUpdateInventory message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.field_71071_by = SCPacketUpdateInventory.access$000(message);
    (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70459_e = true;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\SCPacketUpdateInventory$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */