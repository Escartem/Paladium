package fr.paladium.palamod.modules.alchimiste.common.network.servertoclient;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<SCExtractorSync, IMessage> {
  public IMessage onMessage(SCExtractorSync message, MessageContext ctx) {
    TileEntity tile = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147438_o(message.x, message.y, message.z);
    if (tile != null && tile instanceof TileEntityExtractor) {
      ((TileEntityExtractor)tile).setEnabled(message.isEnabled);
      ((TileEntityExtractor)tile).setStack(message.stack);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\network\servertoclient\SCExtractorSync$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */