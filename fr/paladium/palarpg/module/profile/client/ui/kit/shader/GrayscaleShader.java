package fr.paladium.palarpg.module.profile.client.ui.kit.shader;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.zephyrui.lib.shader.impl.GLShaderImpl;
import net.minecraft.util.ResourceLocation;

public class GrayscaleShader extends GLShaderImpl {
  private static final GrayscaleShader INSTANCE = new GrayscaleShader();
  
  private GrayscaleShader() {
    load((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/shader/grayscale.vsh"), (ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/shader/grayscale.fsh"));
  }
  
  public static void use(float progress, Runnable runnable) {
    if (!INSTANCE.isAvailable())
      return; 
    INSTANCE.bind();
    INSTANCE.shader.getFloatUniform("progress").setValue(progress);
    if (runnable != null)
      runnable.run(); 
    INSTANCE.unbind();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\shader\GrayscaleShader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */