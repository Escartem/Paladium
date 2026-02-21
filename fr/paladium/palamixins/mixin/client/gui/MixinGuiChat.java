package fr.paladium.palamixins.mixin.client.gui;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.client.chat.ClientChatEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({GuiChat.class})
public abstract class MixinGuiChat {
  @Overwrite
  public void func_146403_a(String message) {
    Minecraft mc = Minecraft.func_71410_x();
    mc.field_71456_v.func_146158_b().func_146239_a(message);
    ClientChatEvent event = new ClientChatEvent(message);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return; 
    message = event.getMessage();
    if (ClientCommandHandler.instance.func_71556_a((ICommandSender)mc.field_71439_g, message) != 0)
      return; 
    mc.field_71439_g.func_71165_d(message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\gui\MixinGuiChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */