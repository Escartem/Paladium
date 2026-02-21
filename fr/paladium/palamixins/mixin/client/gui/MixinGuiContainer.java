package fr.paladium.palamixins.mixin.client.gui;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.client.KeyTypedEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiContainer.class})
public abstract class MixinGuiContainer extends GuiScreen {
  @Shadow
  private Slot field_147006_u;
  
  @Inject(method = {"keyTyped"}, at = {@At("HEAD")}, remap = true, cancellable = true)
  protected void onKeyTypedHead(char c, int keyCode, CallbackInfo ci) {
    GuiContainer guiContainer = (GuiContainer)this;
    if (MinecraftForge.EVENT_BUS.post((Event)new KeyTypedEvent.Pre((GuiScreen)guiContainer, c, keyCode)))
      ci.cancel(); 
  }
  
  @Inject(method = {"keyTyped"}, at = {@At("TAIL")}, remap = true, cancellable = true)
  protected void onKeyTypedTail(char c, int keyCode, CallbackInfo ci) {
    GuiContainer guiContainer = (GuiContainer)this;
    MinecraftForge.EVENT_BUS.post((Event)new KeyTypedEvent.Post((GuiScreen)guiContainer, c, keyCode));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\gui\MixinGuiContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */