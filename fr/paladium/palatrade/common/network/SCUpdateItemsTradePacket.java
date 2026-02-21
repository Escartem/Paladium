package fr.paladium.palatrade.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class SCUpdateItemsTradePacket extends ForgePacket {
  private ItemStack[] items;
  
  public SCUpdateItemsTradePacket() {}
  
  public SCUpdateItemsTradePacket(ItemStack[] items) {
    this.items = items;
  }
  
  public void write(ByteBuf buf) {
    for (int i = 0; i < this.items.length; i++) {
      ItemStack item = this.items[i];
      buf.writeBoolean((item != null));
      if (item != null)
        writeItemStack(buf, item); 
    } 
  }
  
  public void read(ByteBuf buf) {
    this.items = new ItemStack[21];
    for (int i = 0; i < this.items.length; i++) {
      boolean hasItem = buf.readBoolean();
      if (!hasItem) {
        this.items[i] = null;
      } else {
        this.items[i] = readItemStack(buf);
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Container container = (Minecraft.func_71410_x()).field_71439_g.field_71070_bA;
    if (!(container instanceof fr.paladium.palatrade.common.container.ContainerTrade))
      return; 
    for (int i = 0; i < this.items.length; i++)
      container.func_75141_a(i + 21, this.items[i]); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\SCUpdateItemsTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */