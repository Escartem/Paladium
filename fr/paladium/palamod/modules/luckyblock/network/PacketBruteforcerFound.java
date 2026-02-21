package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class PacketBruteforcerFound implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  public PacketBruteforcerFound() {}
  
  public PacketBruteforcerFound(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<PacketBruteforcerFound, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketBruteforcerFound message, MessageContext ctx) {
      EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
      TileEntity tile = ((EntityPlayer)entityClientPlayerMP).field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (tile == null || !(tile instanceof TileEntityBruteforcer))
        return null; 
      TileEntityBruteforcer bruteforcer = (TileEntityBruteforcer)tile;
      bruteforcer.found = true;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketBruteforcerFound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */