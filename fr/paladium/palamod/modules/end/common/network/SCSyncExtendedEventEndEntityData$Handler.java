package fr.paladium.palamod.modules.end.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<SCSyncExtendedEventEndEntityData, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCSyncExtendedEventEndEntityData message, MessageContext ctx) {
    if (SCSyncExtendedEventEndEntityData.access$000(message) == null || (Minecraft.func_71410_x()).field_71439_g == null)
      return null; 
    ExtendedEventEndEntity eep = ExtendedEventEndEntity.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
    if (eep == null)
      return null; 
    eep.loadNBTData(SCSyncExtendedEventEndEntityData.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\network\SCSyncExtendedEventEndEntityData$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */