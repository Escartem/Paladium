package fr.paladium.palamod.modules.back2future.client;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.AnimationMetadataSection;

@SideOnly(Side.CLIENT)
public class PrismarineIcon extends TextureAtlasSprite {
  protected int[][] interpolatedFrameData;
  
  private Field fanimationMetadata;
  
  public PrismarineIcon(String name) {
    super(name);
    this
      .fanimationMetadata = ReflectionHelper.findField(TextureAtlasSprite.class, new String[] { "animationMetadata", "field_110982_k" });
    this.fanimationMetadata.setAccessible(true);
  }
  
  public void func_94219_l() {
    super.func_94219_l();
    try {
      updateAnimationInterpolated();
    } catch (Exception exception) {}
  }
  
  private void updateAnimationInterpolated() throws IllegalArgumentException, IllegalAccessException {
    AnimationMetadataSection animationMetadata = (AnimationMetadataSection)this.fanimationMetadata.get(this);
    double d0 = 1.0D - this.field_110983_h / animationMetadata.func_110472_a(this.field_110973_g);
    int i = animationMetadata.func_110468_c(this.field_110973_g);
    int j = (animationMetadata.func_110473_c() == 0) ? this.field_110976_a.size() : animationMetadata.func_110473_c();
    int k = animationMetadata.func_110468_c((this.field_110973_g + 1) % j);
    if (i != k && k >= 0 && k < this.field_110976_a.size()) {
      int[][] aint = this.field_110976_a.get(i);
      int[][] aint1 = this.field_110976_a.get(k);
      if (this.interpolatedFrameData == null || this.interpolatedFrameData.length != aint.length)
        this.interpolatedFrameData = new int[aint.length][]; 
      for (int l = 0; l < aint.length; l++) {
        if (this.interpolatedFrameData[l] == null)
          this.interpolatedFrameData[l] = new int[(aint[l]).length]; 
        if (l < aint1.length && (aint1[l]).length == (aint[l]).length)
          for (int i1 = 0; i1 < (aint[l]).length; i1++) {
            int j1 = aint[l][i1];
            int k1 = aint1[l][i1];
            int l1 = (int)(((j1 & 0xFF0000) >> 16) * d0 + ((k1 & 0xFF0000) >> 16) * (1.0D - d0));
            int i2 = (int)(((j1 & 0xFF00) >> 8) * d0 + ((k1 & 0xFF00) >> 8) * (1.0D - d0));
            int j2 = (int)((j1 & 0xFF) * d0 + (k1 & 0xFF) * (1.0D - d0));
            this.interpolatedFrameData[l][i1] = j1 & 0xFF000000 | l1 << 16 | i2 << 8 | j2;
          }  
      } 
      TextureUtil.func_147955_a(this.interpolatedFrameData, this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\PrismarineIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */