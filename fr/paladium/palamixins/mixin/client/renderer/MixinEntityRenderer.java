package fr.paladium.palamixins.mixin.client.renderer;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({EntityRenderer.class})
public class MixinEntityRenderer {
  @Redirect(method = {"updateCameraAndRender"}, at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glMatrixMode(I)V", ordinal = 0))
  private void replaceShaderMatrixMode(int mode, float partialTicks) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\renderer\MixinEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */