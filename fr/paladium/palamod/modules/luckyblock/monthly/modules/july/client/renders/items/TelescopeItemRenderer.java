package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items.ModelTelescope;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class TelescopeItemRenderer implements IItemRenderer {
  public static ModelTelescope model = new ModelTelescope();
  
  public static ResourceLocation texture;
  
  public TelescopeItemRenderer() {
    texture = new ResourceLocation("palamod", "textures/items/telescope.png");
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
    float scale = 1.35F;
    switch (type) {
      case ENTITY:
        GL11.glTranslated(-0.0D, -2.2D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glScaled(scale, scale, scale);
        GL11.glRotated(-45.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-0.0D, -1.8D, 0.0D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glScaled(scale, scale, scale);
        GL11.glRotated(-120.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-0.0D, -1.5D, 0.0D);
        break;
      case INVENTORY:
        GL11.glTranslated(-0.8D, -1.8D, 0.8D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\items\TelescopeItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */