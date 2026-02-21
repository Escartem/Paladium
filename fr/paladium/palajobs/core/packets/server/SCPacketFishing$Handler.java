package fr.paladium.palajobs.core.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.client.ui.fishing.UIFishing;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.registry.FishingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Handler implements IMessageHandler<SCPacketFishing, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketFishing message, MessageContext ctx) {
    FishingCategory category = (FishingCategory)FishingRegistry.categoriesByName.get(SCPacketFishing.access$000(message));
    if (category != null)
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIFishing(category)); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketFishing$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */