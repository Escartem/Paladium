package fr.paladium.palarpg.module.profile.client.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class FakeEntityPlayerMP extends EntityOtherPlayerMP {
  private final EntityPlayer player;
  
  private ResourceLocation customSkin;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public ResourceLocation getCustomSkin() {
    return this.customSkin;
  }
  
  public FakeEntityPlayerMP(EntityPlayer player) {
    super(player.field_70170_p, new GameProfile(player.func_110124_au(), player.func_70005_c_()));
    this.player = player;
    this.field_70173_aa = 41;
    this.field_71071_by = player.field_71071_by;
    ResourceLocation resourceLocation = AbstractClientPlayer.field_110314_b;
    resourceLocation = AbstractClientPlayer.func_110311_f(func_70005_c_());
    downloadImageSkin(resourceLocation, func_70005_c_());
    func_152121_a(MinecraftProfileTexture.Type.SKIN, resourceLocation);
    setCustomSkin(resourceLocation);
  }
  
  public ResourceLocation func_110306_p() {
    if (this.customSkin != null)
      return this.customSkin; 
    return super.func_110306_p();
  }
  
  public void setCustomSkin(ResourceLocation customSkin) {
    this.customSkin = customSkin;
  }
  
  private void downloadImageSkin(ResourceLocation texture, String name) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(texture);
    if (object == null) {
      object = new ThreadDownloadImageData((File)null, String.format("https://minotar.net/skin/%s", new Object[] { StringUtils.func_76338_a(name) }), AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload());
      texturemanager.func_110579_a(texture, (ITextureObject)object);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\util\FakeEntityPlayerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */