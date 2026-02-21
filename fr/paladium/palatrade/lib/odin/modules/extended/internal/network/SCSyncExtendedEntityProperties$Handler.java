package fr.paladium.palatrade.lib.odin.modules.extended.internal.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedEntityProperties;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<SCSyncExtendedEntityProperties, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCSyncExtendedEntityProperties message, MessageContext ctx) {
    if (SCSyncExtendedEntityProperties.access$000(message) == null)
      return null; 
    ExtendedEntityProperties eep = (ExtendedEntityProperties)(Minecraft.func_71410_x()).field_71439_g.getExtendedProperties(SCSyncExtendedEntityProperties.access$000(message));
    eep.loadNBTData(SCSyncExtendedEntityProperties.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\internal\network\SCSyncExtendedEntityProperties$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */