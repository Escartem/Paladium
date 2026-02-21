package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import fr.paladium.palamod.modules.hunter.proxies.CommonProxy;
import fr.paladium.palamod.modules.paladium.client.model.ModelInkPlate;
import fr.paladium.palamod.modules.paladium.client.model.ModelPrintPress;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemPrintPressRenderer implements IItemRenderer {
  private ModelPrintPress pressmodel = new ModelPrintPress();
  
  private ModelInkPlate ipmodel = new ModelInkPlate();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    switch (type) {
      case ENTITY:
        renderPrintPress(-0.12F, 0.46F, 0.0F, true);
        break;
      case EQUIPPED:
        renderPrintPress(0.8F, 1.0F, 0.6F, false);
        break;
      case EQUIPPED_FIRST_PERSON:
        renderPrintPress(0.8F, 1.0F, 0.6F, false);
        break;
      case INVENTORY:
        renderPrintPress(1.12F, 1.32F, 1.0F, false);
        break;
    } 
  }
  
  private void renderPrintPress(float i, float j, float k, boolean entity) {
    float scale = 0.0625F;
    Tessellator tesselator = Tessellator.field_78398_a;
    GL11.glPushMatrix();
    GL11.glTranslatef(i, j, k);
    if (entity)
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); 
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(0.85F, 0.85F, 0.85F);
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a(CommonProxy.PRINTMACHINE_PNG);
    this.pressmodel.renderMain();
    this.pressmodel.press.field_78795_f = 0.5F;
    this.pressmodel.renderPress();
    this.pressmodel.renderPlate();
    this.pressmodel.inkRollers.field_78795_f = 0.2F;
    this.pressmodel.renderRollers();
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a(CommonProxy.INKPLATE0);
    this.ipmodel.render();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemPrintPressRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */