package fr.paladium.palamod.mixins.client.renderer;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public abstract class IMixinEntityRenderer {
  @Shadow
  public int field_147713_ae;
  
  @Shadow
  public static int field_147708_e;
  
  @Shadow
  public ShaderGroup field_147707_d;
  
  @Shadow
  private Minecraft field_78531_r;
  
  @Shadow
  private IResourceManager field_147711_ac;
  
  @Shadow
  private static ResourceLocation[] field_147712_ad;
  
  @Inject(method = {"updateFogColor"}, at = {@At("RETURN")})
  public void updateFogColor(float p_78466_1_, CallbackInfo ci) {
    EntityClientPlayerMP entity = this.field_78531_r.field_71439_g;
    if (entity.func_70644_a((Potion)PLuckyBlock.FLIP)) {
      if (this.field_147713_ae != 8)
        activateNextShader(8); 
    } else if (entity.func_70644_a((Potion)PLuckyBlock.CINEMA_MUET)) {
      if (this.field_147713_ae != 16)
        activateNextShader(16); 
    } else if (this.field_147713_ae == 8 || this.field_147713_ae == 16) {
      if (this.field_147707_d != null)
        this.field_147707_d.func_148021_a(); 
      this.field_147707_d = null;
      this.field_147713_ae = field_147708_e;
    } 
  }
  
  public void activateNextShader(int index) {
    if (OpenGlHelper.func_148822_b())
      if (OpenGlHelper.field_148824_g) {
        if (this.field_147707_d != null)
          this.field_147707_d.func_148021_a(); 
        this.field_147713_ae = index;
        if (this.field_147713_ae != field_147708_e) {
          try {
            this.field_147707_d = new ShaderGroup(this.field_78531_r.func_110434_K(), this.field_147711_ac, this.field_78531_r.func_147110_a(), field_147712_ad[this.field_147713_ae]);
            this.field_147707_d.func_148026_a(this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
          } catch (IOException var2) {
            this.field_147713_ae = field_147708_e;
          } 
        } else {
          this.field_147707_d = null;
        } 
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\renderer\IMixinEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */