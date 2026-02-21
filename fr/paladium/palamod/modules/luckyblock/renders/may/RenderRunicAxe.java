package fr.paladium.palamod.modules.luckyblock.renders.may;

import fr.paladium.palamod.modules.luckyblock.renders.models.may.ModelRunicAxe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderRunicAxe implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/runic_axe.png");
  
  private static ModelRunicAxe model = new ModelRunicAxe();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    GL11.glRotated(-30.0D, 0.0D, 1.0D, 0.0D);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(0.0D, -2.0D, -0.5D);
    GL11.glRotated(45.0D, 1.0D, 0.0D, 0.0D);
    switch (type) {
      case EQUIPPED:
        GL11.glScaled(1.35D, 1.35D, 1.35D);
        GL11.glRotated(-30.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(30.0D, 0.0D, 0.0D, 1.0D);
        GL11.glRotated(10.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-0.2D, -0.0D, 0.3D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(-60.0D, 1.0D, 0.0D, 0.0D);
          GL11.glTranslated(0.0D, -0.7D, 1.4D);
        } 
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(315.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(-0.8D, -0.35D, 0.0D);
        break;
      case INVENTORY:
        GL11.glScaled(0.65D, 0.65D, 0.65D);
        GL11.glRotated(280.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-0.5D, 1.1D, 2.7D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\may\RenderRunicAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */