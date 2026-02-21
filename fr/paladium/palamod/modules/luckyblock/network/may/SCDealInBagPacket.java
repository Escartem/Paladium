package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.ui.DealInBagUI;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class SCDealInBagPacket implements IMessage {
  private List<ItemStack> rewards;
  
  private ItemStack reward;
  
  public SCDealInBagPacket() {
    this.rewards = new ArrayList<>();
    this.reward = null;
  }
  
  public SCDealInBagPacket(List<ItemStack> rewards, ItemStack reward) {
    this.rewards = rewards;
    this.reward = reward;
  }
  
  public void fromBytes(ByteBuf buf) {
    int size = buf.readInt();
    for (int i = 0; i < size; i++)
      this.rewards.add(ByteBufUtils.readItemStack(buf)); 
    this.reward = ByteBufUtils.readItemStack(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.rewards.size());
    for (ItemStack reward : this.rewards)
      ByteBufUtils.writeItemStack(buf, reward); 
    ByteBufUtils.writeItemStack(buf, this.reward);
  }
  
  public static class Handler implements IMessageHandler<SCDealInBagPacket, IMessage> {
    @SideOnly(Side.CLIENT)
    public void openUI(List<ItemStack> rewards, ItemStack reward) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new DealInBagUI(rewards, reward));
    }
    
    public IMessage onMessage(SCDealInBagPacket message, MessageContext ctx) {
      if (ctx.side != Side.CLIENT)
        return null; 
      openUI(message.rewards, message.reward);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\SCDealInBagPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */