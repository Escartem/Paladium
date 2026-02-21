package fr.paladium.palamod.modules.luckyblock.network.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasMockup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<PacketChristmasMockup, PacketChristmasMockup> {
  public PacketChristmasMockup onMessage(PacketChristmasMockup packet, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return handleClient(packet, (PalaMod.proxy.getPlayerEntity(ctx)).field_70170_p); 
    if (ctx.side == Side.SERVER)
      return handleServer(packet, (ctx.getServerHandler()).field_147369_b.field_70170_p); 
    return null;
  }
  
  public PacketChristmasMockup handleClient(PacketChristmasMockup packet, World world) {
    if (world == null)
      return null; 
    TileEntity buffer = world.func_147438_o(PacketChristmasMockup.access$000(packet), PacketChristmasMockup.access$100(packet), PacketChristmasMockup.access$200(packet));
    if (buffer instanceof TileEntityChristmasMockup) {
      TileEntityChristmasMockup tileEntity = (TileEntityChristmasMockup)world.func_147438_o(PacketChristmasMockup.access$000(packet), PacketChristmasMockup.access$100(packet), PacketChristmasMockup.access$200(packet));
      tileEntity.setOrientation(PacketChristmasMockup.access$300(packet));
    } 
    return null;
  }
  
  public PacketChristmasMockup handleServer(PacketChristmasMockup packet, World world) {
    if (world == null)
      return null; 
    if (!world.func_72899_e(PacketChristmasMockup.access$000(packet), PacketChristmasMockup.access$100(packet), PacketChristmasMockup.access$200(packet)))
      return null; 
    TileEntity buffer = world.func_147438_o(PacketChristmasMockup.access$000(packet), PacketChristmasMockup.access$100(packet), PacketChristmasMockup.access$200(packet));
    if (buffer instanceof TileEntityChristmasMockup) {
      TileEntityChristmasMockup tileEntity = (TileEntityChristmasMockup)world.func_147438_o(PacketChristmasMockup.access$000(packet), PacketChristmasMockup.access$100(packet), PacketChristmasMockup.access$200(packet));
      return new PacketChristmasMockup(tileEntity, tileEntity.getOrientation());
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\christmas\PacketChristmasMockup$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */