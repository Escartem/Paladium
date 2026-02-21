package fr.paladium.palamod.modules.luckyblock.renders.june;

import fr.paladium.palamod.modules.luckyblock.renders.models.june.ModelSitar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderSitar implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/sitar.png");
  
  private static ModelSitar model = new ModelSitar();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    switch (type) {
      case EQUIPPED:
        GL11.glScaled(1.35D, 1.35D, 1.35D);
        GL11.glRotated(-40.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(90.0D, 0.0D, 0.0D, 1.0D);
        GL11.glRotated(-40.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.9D, -1.1D, -0.1D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(60.0D, 1.0D, 0.0D, 0.0D);
          GL11.glRotated(20.0D, 0.0D, 1.0D, 0.0D);
          GL11.glTranslated(0.2D, -0.3D, -0.5D);
        } 
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glScaled(1.35D, 1.35D, 1.35D);
        GL11.glRotated(60.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-10.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.1D, -0.4D, 0.6D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(10.0D, 0.0D, 0.0D, 1.0D);
          GL11.glTranslated(-0.3D, 0.0D, 0.2D);
        } 
        break;
      case INVENTORY:
        GL11.glScaled(0.7D, 0.7D, 0.7D);
        GL11.glTranslated(-1.2D, -1.6D, 0.0D);
        GL11.glRotated(-135.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(45.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(40.0D, 0.0D, 0.0D, 1.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\june\RenderSitar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */