package fr.paladium.palamod.mixins.client;

import fr.paladium.palamod.modules.argus.methods.ObjectTracer;
import fr.paladium.palamod.modules.miner.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityRenderer.class})
public abstract class IMixinEntityRenderer {
  @Shadow
  private float field_78490_B;
  
  @Shadow
  private float field_78491_C;
  
  @Shadow
  private Minecraft field_78531_r;
  
  @Shadow
  private Entity field_78528_u;
  
  @Overwrite
  public void func_78473_a(float v) {
    this.field_78528_u = ObjectTracer.trace(this.field_78531_r, this.field_78528_u, v);
  }
  
  @Inject(method = {"getFOVModifier"}, at = {@At("HEAD")}, cancellable = true)
  private void getFOVModifier(float p_78481_1_, boolean p_78481_2_, CallbackInfoReturnable<Float> callback) {
    if (ClientProxy.inMinage)
      callback.setReturnValue(Float.valueOf(70.0F)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\IMixinEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */