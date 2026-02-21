package fr.paladium.palamod.modules.luckyblock.renders.may;

import fr.paladium.palamod.modules.luckyblock.renders.models.may.ModelDarkKnightHelmet;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderDarkKnightHelmet implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/dark_knight_helmet.png");
  
  private static ModelDarkKnightHelmet model = new ModelDarkKnightHelmet();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    switch (type) {
      case ENTITY:
        GL11.glScaled(0.8D, 0.8D, 0.8D);
        GL11.glRotated(150.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(-40.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-20.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.0D, 0.0D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glRotated(110.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(90.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.3D, 1.2D, 0.5D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glRotated(100.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(30.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(1.2D, 0.2D, 0.6D);
        break;
      case INVENTORY:
        GL11.glScaled(0.8D, 0.8D, 0.8D);
        GL11.glRotated(150.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(-40.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-20.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.0D, 0.4D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\may\RenderDarkKnightHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */