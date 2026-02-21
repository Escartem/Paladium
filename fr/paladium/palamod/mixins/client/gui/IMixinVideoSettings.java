package fr.paladium.palamod.mixins.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiVideoSettings.class})
public abstract class IMixinVideoSettings extends GuiScreen {
  @Inject(method = {"initGui"}, at = {@At("TAIL")})
  private void onInitGui(CallbackInfo ci) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\gui\IMixinVideoSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */