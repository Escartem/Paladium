package fr.paladium.palamod.modules.hunter.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFlowerMonster extends RenderLiving {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/monster_flower.png");
  
  public RenderFlowerMonster() {
    super((ModelBase)new ModelZombie(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderFlowerMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */