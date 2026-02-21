package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<PacketCrusherButton, IMessage> {
  public IMessage onMessage(PacketCrusherButton message, MessageContext ctx) {
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    if (PacketCrusherButton.access$000(message) < 0 || PacketCrusherButton.access$000(message) > 4)
      return null; 
    if (entityPlayerMP.func_130014_f_() != null) {
      World world = entityPlayerMP.func_130014_f_();
      TileEntity tile = world.func_147438_o(PacketCrusherButton.access$100(message), PacketCrusherButton.access$200(message), PacketCrusherButton.access$300(message));
      if (tile != null && tile instanceof TileCrusher) {
        TileCrusher tileCrusher = (TileCrusher)tile;
        if (tileCrusher.func_70300_a((EntityPlayer)entityPlayerMP) && tileCrusher.getExtractItem().equals(TileCrusher.EnumCrusherResult.NONE))
          tileCrusher.setCraft(TileCrusher.EnumCrusherResult.values()[PacketCrusherButton.access$000(message)]); 
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketCrusherButton$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */