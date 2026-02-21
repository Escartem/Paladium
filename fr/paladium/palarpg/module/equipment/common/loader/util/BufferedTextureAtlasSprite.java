package fr.paladium.palarpg.module.equipment.common.loader.util;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.ResourceLocation;

public class BufferedTextureAtlasSprite extends TextureAtlasSprite {
  private final BufferedImage image;
  
  private final AnimationMetadataSection metadata;
  
  public BufferedTextureAtlasSprite(String name, BufferedImage image) {
    this(name, image, (AnimationMetadataSection)null);
  }
  
  public BufferedTextureAtlasSprite(String name, BufferedImage image, AnimationMetadataSection metadata) {
    super(name);
    this.image = image;
    this.metadata = metadata;
  }
  
  public boolean load(IResourceManager manager, ResourceLocation location) {
    int mipmapLevels = ((Integer)ObfuscationReflectionHelper.getPrivateValue(TextureMap.class, Minecraft.func_71410_x().func_147117_R(), new String[] { "mipmapLevels", "field_147636_j" })).intValue();
    BufferedImage[] abufferedimage = new BufferedImage[1 + mipmapLevels];
    Arrays.fill((Object[])abufferedimage, this.image);
    func_110968_a(Lists.newArrayList());
    this.field_110973_g = 0;
    this.field_110983_h = 0;
    int i = abufferedimage[0].getWidth();
    int j = abufferedimage[0].getHeight();
    this.field_130223_c = i;
    this.field_130224_d = j;
    int[][] aint = new int[abufferedimage.length][];
    int k;
    for (k = 0; k < abufferedimage.length; k++) {
      BufferedImage bufferedimage = abufferedimage[k];
      if (bufferedimage != null) {
        aint[k] = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
        bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), aint[k], 0, bufferedimage.getWidth());
      } 
    } 
    if (this.metadata == null) {
      fixTransparentPixels(aint);
      this.field_110976_a.add(aint);
    } else {
      k = j / i;
      int j1 = i;
      int l = i;
      this.field_130224_d = this.field_130223_c;
      if (this.metadata.func_110473_c() > 0) {
        Iterator<?> iterator = this.metadata.func_130073_e().iterator();
        while (iterator.hasNext()) {
          int i1 = ((Integer)iterator.next()).intValue();
          if (i1 >= k)
            throw new RuntimeException("invalid frameindex " + i1); 
          allocateFrameTextureData(i1);
          this.field_110976_a.set(i1, getFrameTextureData(aint, j1, l, i1));
        } 
        ObfuscationReflectionHelper.setPrivateValue(TextureAtlasSprite.class, this, this.metadata, new String[] { "animationMetadata", "field_110982_k" });
      } else {
        List<AnimationFrame> arraylist = Lists.newArrayList();
        for (int i1 = 0; i1 < k; i1++) {
          this.field_110976_a.add(getFrameTextureData(aint, j1, l, i1));
          arraylist.add(new AnimationFrame(i1, -1));
        } 
        ObfuscationReflectionHelper.setPrivateValue(TextureAtlasSprite.class, this, new AnimationMetadataSection(arraylist, this.field_130223_c, this.field_130224_d, this.metadata.func_110469_d()), new String[] { "animationMetadata", "field_110982_k" });
      } 
    } 
    return false;
  }
  
  private void allocateFrameTextureData(int p_130099_1_) {
    if (this.field_110976_a.size() <= p_130099_1_)
      for (int j = this.field_110976_a.size(); j <= p_130099_1_; j++)
        this.field_110976_a.add(null);  
  }
  
  private static int[][] getFrameTextureData(int[][] p_147962_0_, int p_147962_1_, int p_147962_2_, int p_147962_3_) {
    int[][] aint1 = new int[p_147962_0_.length][];
    for (int l = 0; l < p_147962_0_.length; l++) {
      int[] aint2 = p_147962_0_[l];
      if (aint2 != null) {
        aint1[l] = new int[(p_147962_1_ >> l) * (p_147962_2_ >> l)];
        System.arraycopy(aint2, p_147962_3_ * (aint1[l]).length, aint1[l], 0, (aint1[l]).length);
      } 
    } 
    return aint1;
  }
  
  private void fixTransparentPixels(int[][] p_147961_1_) {
    int[] aint1 = p_147961_1_[0];
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    int i1;
    for (i1 = 0; i1 < aint1.length; i1++) {
      if ((aint1[i1] & 0xFF000000) != 0) {
        j += aint1[i1] >> 16 & 0xFF;
        k += aint1[i1] >> 8 & 0xFF;
        l += aint1[i1] >> 0 & 0xFF;
        i++;
      } 
    } 
    if (i != 0) {
      j /= i;
      k /= i;
      l /= i;
      for (i1 = 0; i1 < aint1.length; i1++) {
        if ((aint1[i1] & 0xFF000000) == 0)
          aint1[i1] = j << 16 | k << 8 | l; 
      } 
    } 
  }
  
  public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loade\\util\BufferedTextureAtlasSprite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */