package fr.paladium.mixins;

import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiScreen.class})
public abstract class MixinGuiScreen extends Gui {
  @Inject(method = {"drawHoveringText"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  protected void drawHoveringTextHead(List<String> hover, int mouseX, int mouseY, FontRenderer font, CallbackInfo ci) {}
}


/* Location:              E:\Paladium\!\fr\paladium\mixins\MixinGuiScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */