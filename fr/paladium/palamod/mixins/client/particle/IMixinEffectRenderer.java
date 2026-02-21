package fr.paladium.palamod.mixins.client.particle;

import java.util.List;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EffectRenderer.class})
public interface IMixinEffectRenderer {
  @Accessor
  List[] getFxLayers();
  
  @Accessor
  TextureManager getRenderer();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\particle\IMixinEffectRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */