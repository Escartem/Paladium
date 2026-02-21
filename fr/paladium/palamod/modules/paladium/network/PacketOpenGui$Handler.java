package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<PacketOpenGui, IMessage> {
  public IMessage onMessage(PacketOpenGui message, MessageContext ctx) {
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    if (message.id == PGuiRegistry.GUI_ALCHEMY_CREATOR_ARROW || message.id == PGuiRegistry.GUI_ALCHEMY_CREATOR_POTION) {
      AlchemyCreatorLogic tile = (AlchemyCreatorLogic)AlchemyCreatorLogic.oppenedGui.get(UUIDUtils.toString((Entity)entityPlayerMP));
      if (tile != null)
        entityPlayerMP.openGui(PalaMod.instance, message.id, ((EntityPlayer)entityPlayerMP).field_70170_p, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e); 
      return null;
    } 
    entityPlayerMP.openGui(PalaMod.instance, message.id, ((EntityPlayer)entityPlayerMP).field_70170_p, message.x, message.y, message.z);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketOpenGui$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */