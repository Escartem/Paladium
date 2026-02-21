package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.utils;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ScreenShotUtils {
  private static IntBuffer pixelBuffer;
  
  private static int[] pixelValues;
  
  public static BufferedImage getScreenShot(int width, int height, Framebuffer framebuffer) {
    if (OpenGlHelper.func_148822_b()) {
      width = framebuffer.field_147622_a;
      height = framebuffer.field_147620_b;
    } 
    int totalPixels = width * height;
    if (pixelBuffer == null || pixelBuffer.capacity() < totalPixels) {
      pixelBuffer = BufferUtils.createIntBuffer(totalPixels);
      pixelValues = new int[totalPixels];
    } 
    GL11.glPixelStorei(3333, 1);
    GL11.glPixelStorei(3317, 1);
    pixelBuffer.clear();
    if (OpenGlHelper.func_148822_b()) {
      GL11.glBindTexture(3553, framebuffer.field_147617_g);
      GL11.glGetTexImage(3553, 0, 6408, 5121, pixelBuffer);
    } else {
      GL11.glReadPixels(0, 0, width, height, 6408, 5121, pixelBuffer);
    } 
    pixelBuffer.get(pixelValues);
    TextureUtil.func_147953_a(pixelValues, width, height);
    BufferedImage bufferedImage = null;
    if (OpenGlHelper.func_148822_b()) {
      bufferedImage = new BufferedImage(framebuffer.field_147621_c, framebuffer.field_147618_d, 1);
      int[] var7 = new int[framebuffer.field_147622_a * framebuffer.field_147620_b];
      int var8 = framebuffer.field_147620_b - framebuffer.field_147618_d;
      for (int var9 = var8; var9 < framebuffer.field_147620_b; var9++) {
        for (int var10 = 0; var10 < framebuffer.field_147621_c; var10++)
          var7[var10 + var9 * framebuffer.field_147622_a] = pixelValues[(framebuffer.field_147620_b - var9 - 1) * framebuffer.field_147622_a + var10]; 
      } 
      bufferedImage.setRGB(0, 0, framebuffer.field_147622_a, framebuffer.field_147620_b, var7, 0, framebuffer.field_147622_a);
    } else {
      bufferedImage = new BufferedImage(width, height, 1);
      bufferedImage.setRGB(0, 0, width, height, pixelValues, 0, width);
    } 
    return bufferedImage;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\clien\\utils\ScreenShotUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */