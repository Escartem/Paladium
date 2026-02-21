package fr.paladium.palamod.modules.miner.dimminer.client.render;

import fr.paladium.palamod.modules.miner.dimminer.client.model.MinerBossModel;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMinerBoss extends RenderLiving {
  public RenderMinerBoss() {
    super((ModelBase)new MinerBossModel(), 1.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    EntityMinerBoss boss = (EntityMinerBoss)entity;
    return new ResourceLocation("palamod:textures/entity/miner_boss/miner_boss_" + boss
        .getState() + ".png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\render\RenderMinerBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */