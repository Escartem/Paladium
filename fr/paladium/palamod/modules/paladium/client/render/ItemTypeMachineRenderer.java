package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import fr.paladium.palamod.modules.hunter.proxies.CommonProxy;
import fr.paladium.palamod.modules.paladium.client.model.ModelTypeset;
import fr.paladium.palamod.modules.paladium.client.model.ModelTypesetBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemTypeMachineRenderer implements IItemRenderer {
  private ModelTypeset tmodel = new ModelTypeset();
  
  private ModelTypesetBase bmodel = new ModelTypesetBase();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    switch (type) {
      case ENTITY:
        renderTypeTable(0.0F, 0.27F, 0.1F, true);
        break;
      case EQUIPPED:
        renderTypeTable(0.8F, 1.0F, 0.6F, false);
        break;
      case EQUIPPED_FIRST_PERSON:
        renderTypeTable(0.8F, 1.0F, 0.6F, false);
        break;
      case INVENTORY:
        renderTypeTable(0.98F, 1.18F, 1.0F, false);
        break;
    } 
  }
  
  private void renderTypeTable(float i, float j, float k, boolean entity) {
    float scale = 0.0625F;
    Tessellator tesselator = Tessellator.field_78398_a;
    GL11.glPushMatrix();
    GL11.glTranslatef(i, j, k);
    if (entity)
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); 
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(0.85F, 0.85F, 0.85F);
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a(CommonProxy.TYPESET_TOP);
    this.tmodel.renderTop();
    this.tmodel.renderChaseStack(3);
    this.tmodel.renderlowerRightPlate();
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a(CommonProxy.TYPESET_BASE);
    this.bmodel.renderBase();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemTypeMachineRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */