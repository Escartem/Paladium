package fr.paladium.palamod.modules.luckyblock.renders.june;

import fr.paladium.palamod.modules.luckyblock.renders.models.june.ModelSwordGuitarOfTheApocalypse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderSwordGuitarOfTheApocalypse implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/sword_guitar_of_the_apocalypse.png");
  
  private static ModelSwordGuitarOfTheApocalypse model = new ModelSwordGuitarOfTheApocalypse();
  
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
        GL11.glRotated(225.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(50.0D, 1.0D, 0.0D, 0.0D);
        GL11.glTranslated(0.0D, -2.3D, 1.2D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(70.0D, 0.0D, 0.0D, 1.0D);
          GL11.glRotated(-20.0D, 0.0D, 1.0D, 0.0D);
          GL11.glTranslated(3.0D, -1.9D, -1.0D);
        } 
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glScaled(0.6D, 0.6D, 0.6D);
        GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
        GL11.glTranslated(0.0D, -3.8D, -0.1D);
        GL11.glRotated(10.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-30.0D, 1.0D, 0.0D, 0.0D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(40.0D, 1.0D, 0.0D, 0.0D);
          GL11.glRotated(10.0D, 0.0D, 1.0D, 0.0D);
          GL11.glTranslated(0.2D, 0.8D, -1.0D);
        } 
        break;
      case INVENTORY:
        GL11.glScaled(0.35D, 0.35D, 0.35D);
        GL11.glRotated(135.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-140.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(40.0D, 0.0D, 1.0D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\june\RenderSwordGuitarOfTheApocalypse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */