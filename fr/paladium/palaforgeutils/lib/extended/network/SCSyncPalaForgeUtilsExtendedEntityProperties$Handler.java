package fr.paladium.palaforgeutils.lib.extended.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Handler implements IMessageHandler<SCSyncPalaForgeUtilsExtendedEntityProperties, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCSyncPalaForgeUtilsExtendedEntityProperties message, MessageContext ctx) {
    if (SCSyncPalaForgeUtilsExtendedEntityProperties.access$000(message) == null || SCSyncPalaForgeUtilsExtendedEntityProperties.access$100(message) == null || SCSyncPalaForgeUtilsExtendedEntityProperties.access$200(message) == null)
      return null; 
    EntityPlayer targettedPlayer = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_152378_a(SCSyncPalaForgeUtilsExtendedEntityProperties.access$100(message));
    if (targettedPlayer == null)
      return null; 
    ExtendedEntityProperties eep = (ExtendedEntityProperties)targettedPlayer.getExtendedProperties(SCSyncPalaForgeUtilsExtendedEntityProperties.access$000(message));
    if (eep == null) {
      System.out.println("ExtendedEntityProperties not found for " + SCSyncPalaForgeUtilsExtendedEntityProperties.access$000(message) + " with target " + SCSyncPalaForgeUtilsExtendedEntityProperties.access$100(message));
      return null;
    } 
    eep.loadNBTData(SCSyncPalaForgeUtilsExtendedEntityProperties.access$200(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\extended\network\SCSyncPalaForgeUtilsExtendedEntityProperties$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */