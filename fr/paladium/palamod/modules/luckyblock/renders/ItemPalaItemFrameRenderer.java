package fr.paladium.palamod.modules.luckyblock.renders;

import cpw.mods.fml.client.FMLClientHandler;
import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemPalaItemFrameRenderer implements IItemRenderer {
  private ModelItemFrame model = new ModelItemFrame();
  
  private static ResourceLocation texture = new ResourceLocation("palamod:textures/blocks/item_frame.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    int metaTest = item.func_77960_j();
    switch (type) {
      case ENTITY:
        renderStatue(0.0F, -0.52F, 0.0F, metaTest, item);
        break;
      case EQUIPPED:
        renderStatue(0.2F, 0.0F, 0.8F, metaTest, item);
        break;
      case EQUIPPED_FIRST_PERSON:
        renderStatue(0.5F, 0.9F, 0.6F, metaTest, item);
        break;
      case INVENTORY:
        renderStatue(1.0F, 0.3F, 1.0F, metaTest, item);
        break;
    } 
  }
  
  public void renderStatue(float x, float y, float z, int metaTest, ItemStack item) {
    GL11.glPushMatrix();
    GL11.glTranslatef(x, y + 0.05F, z);
    GL11.glScalef(0.007F, 0.007F, 0.007F);
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a(texture);
    this.model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\ItemPalaItemFrameRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */