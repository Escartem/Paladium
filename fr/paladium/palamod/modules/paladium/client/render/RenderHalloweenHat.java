package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.client.model.HalloweenHatModel;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderHalloweenHat implements IItemRenderer {
  private final HalloweenHatModel model = new HalloweenHatModel();
  
  private final ResourceLocation texture = new ResourceLocation("palamod:textures/models/halloween_hat.png");
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return false;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    switch (type) {
      case EQUIPPED:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glTranslated(0.4D, 0.3D, 0.5D);
        GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glTranslated(0.4D, 0.3D, 0.5D);
        GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
      case INVENTORY:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glScaled(10.0D, 10.0D, 10.0D);
        GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(40.0D, 1.0D, 0.0D, 1.0D);
        GL11.glTranslated(-0.2D, 2.0D, 0.5D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
      case ENTITY:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(0.0D, 1.0D, 0.0D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderHalloweenHat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */