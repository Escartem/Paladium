package fr.paladium.palamixins.mixin.client.model;

import fr.paladium.palamixins.accessor.client.model.ModelRendererAccessor;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ModelRenderer.class})
public class MixinModelRenderer implements ModelRendererAccessor {
  private ModelRenderer parent;
  
  public ModelRenderer getParent() {
    return this.parent;
  }
  
  @Inject(method = {"addChild"}, at = {@At("HEAD")})
  private void addChild(ModelRenderer child, CallbackInfo ci) {
    ((ModelRendererAccessor)child).setParent((ModelRenderer)this);
  }
  
  public void setParent(ModelRenderer parent) {
    this.parent = parent;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\model\MixinModelRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */