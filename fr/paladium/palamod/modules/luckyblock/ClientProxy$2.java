package fr.paladium.palamod.modules.luckyblock;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

final class null implements IImageBuffer {
  public BufferedImage func_78432_a(BufferedImage p_78432_1_) {
    if (imagebufferdownload != null)
      p_78432_1_ = imagebufferdownload.func_78432_a(p_78432_1_); 
    return p_78432_1_;
  }
  
  public void func_152634_a() {
    if (imagebufferdownload != null)
      imagebufferdownload.func_152634_a(); 
    if (p_152789_3_ != null)
      p_152789_3_.func_152121_a(p_152789_2_, resourcelocation); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ClientProxy$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */