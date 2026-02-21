package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.items.ModelSuitcase;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class SuitcaseItemRenderer implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/items/suitcase.png");
  
  private static ModelSuitcase model = new ModelSuitcase();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    float scale = 0.8F;
    GL11.glScalef(scale, scale, scale);
    switch (type) {
      case ENTITY:
        GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.0D, -2.5D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glTranslated(0.5D, 3.1D, 0.2D);
        GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
        GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glTranslated(0.0D, 3.5D, 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
        break;
      case INVENTORY:
        GL11.glTranslated(-0.2D, 2.2D, 0.0D);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\items\SuitcaseItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */