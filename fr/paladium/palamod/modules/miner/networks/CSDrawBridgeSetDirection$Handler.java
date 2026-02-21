package fr.paladium.palamod.modules.miner.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityDrawBridge;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<CSDrawBridgeSetDirection, IMessage> {
  public IMessage onMessage(CSDrawBridgeSetDirection message, MessageContext ctx) {
    if (!(ctx.getServerHandler()).field_147369_b.field_70170_p.func_72899_e(message.x, message.y, message.z))
      return null; 
    if ((ctx.getServerHandler()).field_147369_b.field_70170_p.func_147438_o(message.x, message.y, message.z) != null) {
      TileEntity te = (ctx.getServerHandler()).field_147369_b.field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (te instanceof TileEntityDrawBridge) {
        TileEntityDrawBridge tileEntityDrawBridge = (TileEntityDrawBridge)te;
        EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
        if (player.func_70011_f(message.x, message.y, message.z) < 8.0D && 
          player.field_71070_bA instanceof fr.paladium.palamod.modules.miner.container.ContainerDrawBridge)
          tileEntityDrawBridge.setDirection(message.direction); 
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\networks\CSDrawBridgeSetDirection$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */