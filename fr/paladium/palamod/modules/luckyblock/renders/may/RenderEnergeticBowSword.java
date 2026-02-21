package fr.paladium.palamod.modules.luckyblock.renders.may;

import fr.paladium.palamod.modules.luckyblock.renders.models.may.ModelEnergeticBowSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderEnergeticBowSword implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/energetic_bow_sword.png");
  
  private static ModelEnergeticBowSword model = new ModelEnergeticBowSword();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    GL11.glRotated(25.0D, 0.0D, 1.0D, 0.0D);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(0.0D, -2.0D, -0.5D);
    GL11.glRotated(45.0D, 1.0D, 0.0D, 0.0D);
    switch (type) {
      case EQUIPPED:
        GL11.glRotated(80.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(20.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.75D, 1.0D, -0.5D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glRotated(-90.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-120.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(-1.0D, -1.8D, 0.0D);
        GL11.glRotated(-70.0D, 0.0D, 1.0D, 0.0D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(15.0D, 0.0D, 0.0D, 1.0D);
          GL11.glRotated(15.0D, 0.0D, 1.0D, 0.0D);
          GL11.glRotated(20.0D, 1.0D, 0.0D, 0.0D);
        } 
        break;
      case INVENTORY:
        GL11.glScaled(0.7D, 0.7D, 0.7D);
        GL11.glRotated(160.0D, 0.0D, 0.0D, 1.0D);
        GL11.glRotated(-180.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-2.0D, -3.95D, -2.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\may\RenderEnergeticBowSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */