package fr.paladium.palamixins.mixin.client.shader;

import net.minecraft.client.shader.Shader;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Shader.class})
public class MixinShader {
  private boolean depthTestEnabled;
  
  private boolean alphaTestEnabled;
  
  private boolean blendEnabled;
  
  private boolean lightingEnabled;
  
  private boolean fogEnabled;
  
  private boolean colorMaterialEnabled;
  
  @Inject(method = {"loadShader"}, at = {@At("HEAD")})
  private void saveGLState(float partialTicks, CallbackInfo ci) {
    this.depthTestEnabled = GL11.glIsEnabled(2929);
    this.alphaTestEnabled = GL11.glIsEnabled(3008);
    this.blendEnabled = GL11.glIsEnabled(3042);
    this.lightingEnabled = GL11.glIsEnabled(2896);
    this.fogEnabled = GL11.glIsEnabled(2912);
    this.colorMaterialEnabled = GL11.glIsEnabled(2903);
  }
  
  @Inject(method = {"loadShader"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/shader/ShaderManager;func_147993_b()V", shift = At.Shift.BEFORE)})
  private void restoreGLState(float partialTicks, CallbackInfo ci) {
    setGLState(2929, this.depthTestEnabled);
    setGLState(3008, this.alphaTestEnabled);
    setGLState(3042, this.blendEnabled);
    setGLState(2896, this.lightingEnabled);
    setGLState(2912, this.fogEnabled);
    setGLState(2903, this.colorMaterialEnabled);
  }
  
  private void setGLState(int cap, boolean enabled) {
    if (enabled) {
      GL11.glEnable(cap);
    } else {
      GL11.glDisable(cap);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\shader\MixinShader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */