package fr.paladium.palamod.modules.back2future.client.renderer.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.LayeredColorMaskTexture;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.client.model.ModelBanner;
import fr.paladium.palamod.modules.back2future.lib.EnumColour;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityBannerRenderer extends TileEntitySpecialRenderer {
  private static final Map<String, TimedBannerTexture> CANVAS_TEXTURES = Maps.newHashMap();
  
  private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("textures/entity/banner_base.png");
  
  private final ModelBanner bannerModel = new ModelBanner();
  
  private ResourceLocation getTexture(TileEntityBanner banner) {
    String s = banner.func_175116_e();
    if (banner.getBaseColor() == 16)
      return new ResourceLocation("textures/entity/banner_corsair.png"); 
    if (banner.getBaseColor() == 17)
      return new ResourceLocation("textures/entity/banner_september.png"); 
    if (s.isEmpty())
      return null; 
    TimedBannerTexture texture = CANVAS_TEXTURES.get(s);
    if (texture == null) {
      if (CANVAS_TEXTURES.size() >= 256) {
        long i = System.currentTimeMillis();
        Iterator<String> iterator = CANVAS_TEXTURES.keySet().iterator();
        while (iterator.hasNext()) {
          String s1 = iterator.next();
          TimedBannerTexture texture1 = CANVAS_TEXTURES.get(s1);
          if (i - texture1.time > 60000L) {
            Minecraft.func_71410_x().func_110434_K().func_147645_c(texture1.texture);
            iterator.remove();
          } 
        } 
        if (CANVAS_TEXTURES.size() >= 256)
          return null; 
      } 
      List<TileEntityBanner.EnumBannerPattern> list1 = banner.getPatternList();
      List<EnumColour> list = banner.getColorList();
      ArrayList<String> arraylist = Lists.newArrayList();
      Iterator<TileEntityBanner.EnumBannerPattern> patters = list1.iterator();
      while (patters.hasNext())
        arraylist.add("textures/entity/banner/" + ((TileEntityBanner.EnumBannerPattern)patters.next()).getPatternName() + ".png"); 
      texture = new TimedBannerTexture();
      texture.texture = new ResourceLocation(s);
      Minecraft.func_71410_x().func_110434_K().func_110579_a(texture.texture, (ITextureObject)new LayeredColorMaskTexture(BASE_TEXTURE, arraylist, list));
      CANVAS_TEXTURES.put(s, texture);
    } 
    texture.time = System.currentTimeMillis();
    return texture.texture;
  }
  
  @SideOnly(Side.CLIENT)
  static class TimedBannerTexture {
    public long time;
    
    public ResourceLocation texture;
    
    private TimedBannerTexture() {}
  }
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks) {
    TileEntityBanner banner = (TileEntityBanner)tile;
    int meta = (tile.func_145831_w() != null) ? banner.func_145832_p() : 0;
    OpenGLHelper.pushMatrix();
    float f1 = 0.6666667F;
    if (banner.isStanding) {
      OpenGLHelper.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
      OpenGLHelper.rotate(-((meta * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
      this.bannerModel.bannerStand.field_78806_j = true;
    } else {
      float f = 0.0F;
      if (meta == 2)
        f = 180.0F; 
      if (meta == 4)
        f = 90.0F; 
      if (meta == 5)
        f = -90.0F; 
      OpenGLHelper.translate((float)x + 0.5F, (float)y - 0.25F * f1, (float)z + 0.5F);
      OpenGLHelper.rotate(-f, 0.0F, 1.0F, 0.0F);
      OpenGLHelper.translate(0.0F, -0.3125F, -0.4375F);
      this.bannerModel.bannerStand.field_78806_j = false;
    } 
    long worldTime = (banner.func_145831_w() != null) ? banner.func_145831_w().func_82737_E() : 0L;
    float f3 = (banner.field_145851_c * 7 + banner.field_145848_d * 9 + banner.field_145849_e * 13) + (float)worldTime + partialTicks;
    this.bannerModel.bannerSlate
      .field_78795_f = (-0.0125F + 0.01F * MathHelper.func_76134_b(f3 * 3.1415927F * 0.02F)) * 3.1415927F;
    OpenGLHelper.enableRescaleNormal();
    ResourceLocation resourcelocation = getTexture(banner);
    if (resourcelocation != null) {
      func_147499_a(resourcelocation);
      if (banner.getBaseColor() == 16 || banner.getBaseColor() == 17) {
        OpenGLHelper.pushMatrix();
        OpenGLHelper.scale(f1, -f1, -f1);
        this.bannerModel.renderAll();
        OpenGLHelper.popMatrix();
      } else {
        OpenGLHelper.pushMatrix();
        OpenGLHelper.scale(f1, -f1, -f1);
        if (tile.func_145831_w() != null) {
          Color color = EnumColour.fromDamage(banner.getBaseColor()).getColour();
          GL11.glColor3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
        } 
        this.bannerModel.renderAll();
        OpenGLHelper.popMatrix();
      } 
    } 
    OpenGLHelper.colour(1.0F, 1.0F, 1.0F);
    OpenGLHelper.popMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\tileentity\TileEntityBannerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */