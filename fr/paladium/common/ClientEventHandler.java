package fr.paladium.common;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class ClientEventHandler {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderPlay(RenderPlayerEvent.Pre e) {
    if (!(e.entityPlayer instanceof AbstractClientPlayer))
      return; 
    AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)e.entityPlayer;
    (Minecraft.func_71410_x()).field_71424_I.func_76320_a("loadingOfflineSkin");
    ResourceLocation resourceLocation = AbstractClientPlayer.field_110314_b;
    resourceLocation = AbstractClientPlayer.func_110311_f(abstractClientPlayer.func_70005_c_());
    getDownloadImageSkin(resourceLocation, "DevZeldown".equalsIgnoreCase(abstractClientPlayer.func_70005_c_()) ? "Zeldown" : abstractClientPlayer.func_70005_c_());
    abstractClientPlayer.func_152121_a(MinecraftProfileTexture.Type.SKIN, resourceLocation);
    (Minecraft.func_71410_x()).field_71424_I.func_76319_b();
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderHand(RenderHandEvent e) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (!(entityClientPlayerMP instanceof AbstractClientPlayer))
      return; 
    AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)entityClientPlayerMP;
    (Minecraft.func_71410_x()).field_71424_I.func_76320_a("loadingOfflineSkin");
    ResourceLocation resourceLocation = AbstractClientPlayer.field_110314_b;
    resourceLocation = AbstractClientPlayer.func_110311_f(abstractClientPlayer.func_70005_c_());
    getDownloadImageSkin(resourceLocation, "DevZeldown".equalsIgnoreCase(abstractClientPlayer.func_70005_c_()) ? "Zeldown" : abstractClientPlayer.func_70005_c_());
    abstractClientPlayer.func_152121_a(MinecraftProfileTexture.Type.SKIN, resourceLocation);
    (Minecraft.func_71410_x()).field_71424_I.func_76319_b();
  }
  
  @SideOnly(Side.CLIENT)
  private ThreadDownloadImageData getDownloadImageSkin(ResourceLocation texture, String playerName) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(texture);
    if (object == null) {
      object = new ThreadDownloadImageData(null, String.format("https://minotar.net/skin/%s", new Object[] { playerName }), AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload());
      texturemanager.func_110579_a(texture, (ITextureObject)object);
    } else if (!(object instanceof ThreadDownloadImageData)) {
      return null;
    } 
    return (ThreadDownloadImageData)object;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\ClientEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */