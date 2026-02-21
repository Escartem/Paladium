package fr.paladium.palamod.modules.luckyblock.renders.may;

import fr.paladium.palamod.modules.luckyblock.renders.models.may.ModelLightSaber;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderLightSaber implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/light_saber.png");
  
  private static ModelLightSaber model = new ModelLightSaber();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    switch (type) {
      case EQUIPPED:
        GL11.glScaled(1.35D, 1.35D, 1.35D);
        GL11.glRotated(175.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(-40.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.55D, -2.0D, -0.6D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glRotated(160.0D, 1.0D, 0.0D, 0.0D);
        GL11.glTranslated(0.0D, -2.65D, -1.0D);
        break;
      case INVENTORY:
        GL11.glScaled(0.65D, 0.65D, 0.65D);
        GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
        GL11.glTranslated(0.0D, -1.3D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\may\RenderLightSaber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */