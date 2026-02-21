package fr.paladium.palamod.modules.back2future.client.renderer.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemBannerRenderer implements IItemRenderer {
  private final TileEntityBanner banner = new TileEntityBanner();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    this.banner.setItemValues(stack);
    this.banner.isStanding = true;
    switch (type) {
      case ENTITY:
        renderBanner(-0.5F, -0.75F, -0.5F, 90.0F, 1.0F);
        break;
      case EQUIPPED:
        renderBanner(0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case EQUIPPED_FIRST_PERSON:
        renderBanner(-0.75F, 0.0625F, -0.0625F, 67.5F, 1.0F);
        break;
      case INVENTORY:
        renderBanner(0.0F, -0.25F, 0.6F, 22.5F, 0.9F);
        break;
    } 
  }
  
  private void renderBanner(float x, float y, float z, float angle, float scale) {
    OpenGLHelper.pushMatrix();
    OpenGLHelper.rotate(angle, 0.0F, 1.0F, 0.0F);
    OpenGLHelper.scale(scale, scale, scale);
    OpenGLHelper.translate(x, y, z);
    TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)this.banner, 0.0D, 0.0D, 0.0D, 0.0F);
    OpenGLHelper.popMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\item\ItemBannerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */