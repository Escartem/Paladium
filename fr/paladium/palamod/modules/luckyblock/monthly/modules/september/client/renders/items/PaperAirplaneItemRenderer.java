package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.items;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities.ModelPaperAirplane;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class PaperAirplaneItemRenderer implements IItemRenderer {
  public static ModelPaperAirplane model = new ModelPaperAirplane();
  
  public static ResourceLocation texture;
  
  public PaperAirplaneItemRenderer() {
    texture = new ResourceLocation("palamod", "textures/entity/paper_airplane.png");
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    switch (type) {
      case ENTITY:
        GL11.glTranslated(0.3D, -2.4D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glTranslated(0.55D, -2.4D, -0.2D);
        GL11.glRotated(-45.0D, 0.0D, 1.0D, 0.0D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glTranslated(-0.47D, -3.4D, 0.0D);
        GL11.glRotated(35.0D, 0.0D, 1.0D, 0.0D);
        break;
      case INVENTORY:
        GL11.glTranslated(0.3D, -2.4D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\items\PaperAirplaneItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */