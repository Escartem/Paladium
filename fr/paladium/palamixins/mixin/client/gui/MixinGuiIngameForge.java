package fr.paladium.palamixins.mixin.client.gui;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.client.RenderRecordOverlayEvent;
import fr.paladium.palamixins.event.client.RenderToolHightlightEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiIngameForge.class})
public abstract class MixinGuiIngameForge extends GuiIngame {
  public MixinGuiIngameForge(Minecraft mc) {
    super(mc);
  }
  
  @Inject(method = {"renderToolHightlight"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  public void renderToolHightlightPre(int width, int height, CallbackInfo ci) {
    if (MinecraftForge.EVENT_BUS.post((Event)new RenderToolHightlightEvent.Pre()))
      ci.cancel(); 
  }
  
  @Inject(method = {"renderToolHightlight"}, at = {@At("TAIL")}, remap = false)
  public void renderToolHightlightPost(int width, int height, CallbackInfo ci) {
    MinecraftForge.EVENT_BUS.post((Event)new RenderToolHightlightEvent.Post());
  }
  
  @Inject(method = {"renderRecordOverlay"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  public void renderRecordOverlayPre(int width, int height, float partialTicks, CallbackInfo ci) {
    if (this.field_73845_h > 0 && 
      MinecraftForge.EVENT_BUS.post((Event)new RenderRecordOverlayEvent.Pre(this.field_73838_g)))
      ci.cancel(); 
  }
  
  @Inject(method = {"renderRecordOverlay"}, at = {@At("TAIL")}, remap = false)
  public void renderRecordOverlayPost(int width, int height, float partialTicks, CallbackInfo ci) {
    if (this.field_73845_h > 0)
      MinecraftForge.EVENT_BUS.post((Event)new RenderRecordOverlayEvent.Post(this.field_73838_g)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\gui\MixinGuiIngameForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */