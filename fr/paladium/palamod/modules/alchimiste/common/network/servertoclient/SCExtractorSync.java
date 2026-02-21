package fr.paladium.palamod.modules.alchimiste.common.network.servertoclient;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityExtractor;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SCExtractorSync implements IMessage {
  public ItemStack stack;
  
  public boolean isEnabled;
  
  public int x;
  
  public int y;
  
  public int z;
  
  public SCExtractorSync(ItemStack stack, boolean isEnabled, int x, int y, int z) {
    this.stack = stack;
    this.isEnabled = isEnabled;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public SCExtractorSync() {}
  
  public void fromBytes(ByteBuf buf) {
    boolean isStackNull = buf.readBoolean();
    if (!isStackNull)
      this.stack = ByteBufUtils.readItemStack(buf); 
    this.isEnabled = buf.readBoolean();
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean((this.stack == null));
    if (this.stack != null)
      ByteBufUtils.writeItemStack(buf, this.stack); 
    buf.writeBoolean(this.isEnabled);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<SCExtractorSync, IMessage> {
    public IMessage onMessage(SCExtractorSync message, MessageContext ctx) {
      TileEntity tile = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (tile != null && tile instanceof TileEntityExtractor) {
        ((TileEntityExtractor)tile).setEnabled(message.isEnabled);
        ((TileEntityExtractor)tile).setStack(message.stack);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\network\servertoclient\SCExtractorSync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */