package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderEntityPlayerNpc extends RenderBiped {
  public RenderEntityPlayerNpc() {
    super(new ModelBiped(0.0F), 0.5F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return ((EntityPlayerNPC)entity).getPlayerSkin();
  }
  
  protected void func_77036_a(EntityLivingBase entity, float f1, float f2, float f3, float f4, float f5, float f6) {
    func_110777_b((Entity)entity);
    this.field_77045_g.func_78088_a((Entity)entity, f1, f2, f3, f4, f5, f6);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderEntityPlayerNpc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */