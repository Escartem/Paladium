package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.ui.DealInBagUI;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class Handler implements IMessageHandler<SCDealInBagPacket, IMessage> {
  @SideOnly(Side.CLIENT)
  public void openUI(List<ItemStack> rewards, ItemStack reward) {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new DealInBagUI(rewards, reward));
  }
  
  public IMessage onMessage(SCDealInBagPacket message, MessageContext ctx) {
    if (ctx.side != Side.CLIENT)
      return null; 
    openUI(SCDealInBagPacket.access$000(message), SCDealInBagPacket.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\SCDealInBagPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */