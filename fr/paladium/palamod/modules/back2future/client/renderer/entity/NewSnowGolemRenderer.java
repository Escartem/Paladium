package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.entities.EntityNewSnowGolem;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.monster.EntitySnowman;

@SideOnly(Side.CLIENT)
public class NewSnowGolemRenderer extends RenderSnowMan {
  protected void func_77029_c(EntitySnowman entity, float partialTickTime) {
    if (((EntityNewSnowGolem)entity).hasPumpkin())
      super.func_77029_c(entity, partialTickTime); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\NewSnowGolemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */