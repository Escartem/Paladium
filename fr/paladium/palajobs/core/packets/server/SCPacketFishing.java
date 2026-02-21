package fr.paladium.palajobs.core.packets.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.client.ui.fishing.UIFishing;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.registry.FishingRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SCPacketFishing implements IMessage {
  private String fishingCategory;
  
  public SCPacketFishing() {}
  
  public SCPacketFishing(String fishingCategory) {
    this.fishingCategory = fishingCategory;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.fishingCategory = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.fishingCategory);
  }
  
  public static class Handler implements IMessageHandler<SCPacketFishing, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketFishing message, MessageContext ctx) {
      FishingCategory category = (FishingCategory)FishingRegistry.categoriesByName.get(message.fishingCategory);
      if (category != null)
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIFishing(category)); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketFishing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */