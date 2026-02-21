package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items.ModelPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class PipeItemRenderer implements IItemRenderer {
  public static ModelPipe model = new ModelPipe();
  
  public static ResourceLocation texture;
  
  public PipeItemRenderer() {
    texture = new ResourceLocation("palamod", "textures/items/pipe.png");
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    float scale = 1.5F;
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glScaled(scale, scale, scale);
    switch (type) {
      case ENTITY:
        GL11.glTranslated(0.7D, -2.3D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glRotated(-45.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslatef(0.3F, -2.8F, 1.1F);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glRotated(10.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslatef(0.5F, -3.1F, 1.0F);
        break;
      case INVENTORY:
        GL11.glTranslated(-0.0D, -2.6D, 1.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\items\PipeItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */