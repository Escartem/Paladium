package fr.paladium.palashop.provider.box.client.render.item;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palashop.provider.box.common.item.ItemBoxKeyLindworm;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class ItemBoxKeyLindwormRenderer implements IItemRenderer {
  public ItemBoxKeyLindwormRenderer() {
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    if (!(stack.func_77973_b() instanceof ItemBoxKeyLindworm))
      return; 
    ItemBoxKeyLindworm item = (ItemBoxKeyLindworm)stack.func_77973_b();
    if (item.getBoxId() == null)
      return; 
    GL11.glPushMatrix();
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
      GL11.glScaled(1.2D, 1.2D, 1.2D);
      GL11.glTranslated(-0.3D, 0.5D, 0.3D);
      GL11.glRotated(-50.0D, 0.0D, 1.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(1.6D, 1.6D, 1.6D);
      GL11.glTranslated(0.2D, 0.0D, 0.2D);
      GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(0.0D, 1.0D, 0.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glEnable(3008);
      GL11.glScaled(1.1D, 1.1D, 1.1D);
      GL11.glTranslated(0.0D, -0.3D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
      GL11.glScaled(1.2D, 1.2D, 1.2D);
    } 
    item.getTexture().bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, item.getModel()));
    if (type == IItemRenderer.ItemRenderType.INVENTORY)
      GL11.glDisable(3008); 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\render\item\ItemBoxKeyLindwormRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */