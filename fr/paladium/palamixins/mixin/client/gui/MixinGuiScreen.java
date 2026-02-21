package fr.paladium.palamixins.mixin.client.gui;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.client.DrawHoveringTextEvent;
import fr.paladium.palamixins.event.client.KeyTypedEvent;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiScreen.class})
public abstract class MixinGuiScreen extends Gui {
  @Inject(method = {"renderToolTip"}, at = {@At("HEAD")}, cancellable = true, remap = true)
  protected void drawHoveringTextHead(ItemStack stack, int mouseX, int mouseY, CallbackInfo ci) {
    GuiScreen guiScreen = (GuiScreen)this;
    if (MinecraftForge.EVENT_BUS.post((Event)new DrawHoveringTextEvent.PreItem(stack, guiScreen, mouseX, mouseY))) {
      stack.func_82840_a((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, (Minecraft.func_71410_x()).field_71474_y.field_82882_x);
      ci.cancel();
    } 
  }
  
  @Inject(method = {"func_146283_a"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  protected void drawHoveringTabText(List<String> list, int mouseX, int mouseY, CallbackInfo ci) {
    GuiScreen guiScreen = (GuiScreen)this;
    if (MinecraftForge.EVENT_BUS.post((Event)new DrawHoveringTextEvent.PreTab(guiScreen, list, mouseX, mouseY)))
      ci.cancel(); 
  }
  
  @Inject(method = {"drawHoveringText"}, at = {@At("TAIL")}, remap = false)
  protected void drawHoveringTextTail(List<String> hover, int mouseX, int mouseY, FontRenderer font, CallbackInfo ci) {
    GuiScreen guiScreen = (GuiScreen)this;
    MinecraftForge.EVENT_BUS.post((Event)new DrawHoveringTextEvent.Post(guiScreen, mouseX, mouseY, hover, font));
  }
  
  @Inject(method = {"keyTyped"}, at = {@At("HEAD")}, remap = true)
  protected void onKeyTypedHead(char c, int keyCode, CallbackInfo ci) {
    GuiScreen guiScreen = (GuiScreen)this;
    if (MinecraftForge.EVENT_BUS.post((Event)new KeyTypedEvent.Pre(guiScreen, c, keyCode)))
      ci.cancel(); 
  }
  
  @Inject(method = {"keyTyped"}, at = {@At("HEAD")}, remap = true)
  protected void onKeyTypedTail(char c, int keyCode, CallbackInfo ci) {
    GuiScreen guiScreen = (GuiScreen)this;
    MinecraftForge.EVENT_BUS.post((Event)new KeyTypedEvent.Post(guiScreen, c, keyCode));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\gui\MixinGuiScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */