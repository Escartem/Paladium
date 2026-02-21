package fr.paladium.palamod.modules.luckyblock.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

public class CustomPlayerRender extends EntityRenderer {
  private Minecraft mc;
  
  private float yOffset = -1.0F;
  
  public CustomPlayerRender(Minecraft mc) {
    super(mc, mc.func_110442_L());
    this.mc = mc;
  }
  
  public void func_78480_b(float partialTicks) {
    if (this.mc.field_71439_g == null || this.mc.field_71439_g.func_70608_bn()) {
      super.func_78480_b(partialTicks);
      return;
    } 
    this.mc.field_71439_g.field_70129_M -= this.yOffset;
    super.func_78480_b(partialTicks);
    this.mc.field_71439_g.field_70129_M = 1.62F;
  }
  
  public void func_78473_a(float partialTicks) {
    if (this.mc.field_71439_g == null || this.mc.field_71439_g.func_70608_bn()) {
      super.func_78473_a(partialTicks);
      return;
    } 
    this.mc.field_71439_g.field_70163_u += this.yOffset;
    this.mc.field_71439_g.field_70167_r += this.yOffset;
    this.mc.field_71439_g.field_70137_T += this.yOffset;
    super.func_78473_a(partialTicks);
    this.mc.field_71439_g.field_70163_u -= this.yOffset;
    this.mc.field_71439_g.field_70167_r -= this.yOffset;
    this.mc.field_71439_g.field_70137_T -= this.yOffset;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\CustomPlayerRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */