package fr.paladium.palakeybind.mixins.client.gui;

import fr.paladium.palakeybind.client.ui.GuiNewControls;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiControls.class})
public abstract class IMixinGuiControls extends GuiScreen {
  @Shadow
  private GuiScreen field_146496_h;
  
  @Shadow
  private GameSettings field_146497_i;
  
  @Inject(method = {"initGui"}, at = {@At("HEAD")}, cancellable = true)
  public void initGui(CallbackInfo ci) {
    this.field_146297_k.func_147108_a((GuiScreen)new GuiNewControls(this.field_146496_h, this.field_146497_i));
    ci.cancel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\mixins\client\gui\IMixinGuiControls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */