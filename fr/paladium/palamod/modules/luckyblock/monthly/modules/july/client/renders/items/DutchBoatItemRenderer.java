package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.entities.ModelDutchBoat;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class DutchBoatItemRenderer implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/dutch_boat.png");
  
  private static ModelDutchBoat model = new ModelDutchBoat();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    float scale = 0.4F;
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glScaled(scale, scale, scale);
    switch (type) {
      case ENTITY:
        GL11.glTranslated(0.0D, -1.5D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glRotated(-45.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(0.5D, -4.0D, 1.5D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glTranslated(0.0D, -4.0D, 1.0D);
        break;
      case INVENTORY:
        GL11.glTranslated(0.0D, -1.0D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\items\DutchBoatItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */