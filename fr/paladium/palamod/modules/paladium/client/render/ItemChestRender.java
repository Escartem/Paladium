package fr.paladium.palamod.modules.paladium.client.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemChestRender implements IItemRenderer {
  private ModelChest chestModel;
  
  private TileEntity tileEntity;
  
  public ItemChestRender(TileEntity tileEntity) {
    this.chestModel = new ModelChest();
    this.tileEntity = tileEntity;
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    GL11.glPushMatrix();
    GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
    if (type != IItemRenderer.ItemRenderType.ENTITY) {
      GL11.glTranslatef(-1.0F, -0.1F, 0.0F);
    } else {
      GL11.glTranslatef(-0.5F, -0.45F, -0.5F);
    } 
    TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemChestRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */