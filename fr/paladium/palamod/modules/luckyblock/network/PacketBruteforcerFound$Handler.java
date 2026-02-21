package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<PacketBruteforcerFound, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(PacketBruteforcerFound message, MessageContext ctx) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    TileEntity tile = ((EntityPlayer)entityClientPlayerMP).field_70170_p.func_147438_o(PacketBruteforcerFound.access$000(message), PacketBruteforcerFound.access$100(message), PacketBruteforcerFound.access$200(message));
    if (tile == null || !(tile instanceof TileEntityBruteforcer))
      return null; 
    TileEntityBruteforcer bruteforcer = (TileEntityBruteforcer)tile;
    bruteforcer.found = true;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketBruteforcerFound$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */