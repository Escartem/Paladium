package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.client.model.ModelPistolTank;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderPistolTank implements IItemRenderer {
  private final ModelPistolTank model = new ModelPistolTank();
  
  private final ResourceLocation texture = new ResourceLocation("palamod:textures/models/pistol_tank.png");
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return (type == IItemRenderer.ItemRenderType.INVENTORY);
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    switch (type) {
      case EQUIPPED:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glTranslatef(0.2F, 0.2F, -0.9F);
        GL11.glRotated(120.0D, 90.0D, 170.0D, 100.0D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glTranslated(-1.5D, 0.6D, 0.0D);
        GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(-85.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslatef(0.0F, 1.2F, -0.3F);
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
      case INVENTORY:
        GL11.glPushMatrix();
        GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(0.0D, -1.0D, 0.0D);
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
      case ENTITY:
        GL11.glPushMatrix();
        (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.texture);
        GL11.glTranslated(-1.5D, 0.6D, 0.0D);
        GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(-85.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslatef(0.2F, 1.2F, -0.3F);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glRotated(20.0D, 1.0D, 0.0D, 0.0D);
        this.model.renderAll();
        GL11.glPopMatrix();
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderPistolTank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */