package fr.paladium.palamod.modules.luckyblock.renders;

import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;

public class RenderSleigh extends RenderMinecart {
  private ResourceLocation sleighTexture = new ResourceLocation("palamod", "textures/entity/sleigh.png");
  
  protected ResourceLocation func_110775_a(EntityMinecart entity) {
    return this.sleighTexture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderSleigh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */