package fr.paladium.palawither.client.render.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;

public class RenderVanillaWither extends RenderWither {
  public RenderVanillaWither() {
    func_76976_a(RenderManager.field_78727_a);
  }
  
  public void func_76986_a(EntityWither wither, double x, double y, double z, float yaw, float pitch) {
    super.func_76986_a(wither, x, y, z, yaw, pitch);
    BossStatus.field_82827_c = null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\entity\RenderVanillaWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */