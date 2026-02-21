package fr.paladium.palamod.modules.communityevents.client.render.piratechest;

import fr.paladium.palamod.modules.communityevents.client.model.ModelPirateChest;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class PirateChestItemRenderer implements IItemRenderer {
  public static ModelPirateChest model = new ModelPirateChest();
  
  public static ResourceLocation texture;
  
  public PirateChestItemRenderer() {
    texture = new ResourceLocation("palamod", "textures/items/piratechest.png");
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    GL11.glPushMatrix();
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    switch (type) {
      case ENTITY:
        GL11.glTranslatef(0.0F, -1.25F, 0.0F);
        break;
      case EQUIPPED:
        GL11.glTranslatef(-0.5F, -1.7F, 0.5F);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glTranslatef(-0.5F, -2.0F, 0.5F);
        break;
      case INVENTORY:
        GL11.glTranslatef(-0.0F, -1.2F, 0.0F);
        break;
      default:
        GL11.glTranslatef(0.0F, -1.5F, 0.0F);
        break;
    } 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\render\piratechest\PirateChestItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */