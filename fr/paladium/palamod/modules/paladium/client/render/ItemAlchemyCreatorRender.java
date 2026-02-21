package fr.paladium.palamod.modules.paladium.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemAlchemyCreatorRender implements IItemRenderer {
  private final TileEntitySpecialRenderer render;
  
  private final TileEntity entity;
  
  public ItemAlchemyCreatorRender(TileEntitySpecialRenderer render, TileEntity entity) {
    this.render = render;
    this.entity = entity;
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    if (type == IItemRenderer.ItemRenderType.ENTITY)
      GL11.glTranslatef(-0.5F, 0.0F, -0.5F); 
    if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glPushMatrix();
      GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
      GL11.glTranslated(0.0D, -0.6D, 0.0D);
      GL11.glTranslated(-0.2D, 0.0D, -0.2D);
      this.render.func_147500_a(this.entity, 0.0D, 0.0D, 0.0D, 10.0F);
      GL11.glPopMatrix();
      return;
    } 
    if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(1.5D, 1.5D, 1.5D);
      GL11.glTranslated(0.6D, 0.0D, 0.8D);
    } 
    GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
    this.render.func_147500_a(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemAlchemyCreatorRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */