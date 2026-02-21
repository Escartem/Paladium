package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.item;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.item.LaserGunModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderLaserGun implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/items/laser_gun.png");
  
  private static LaserGunModel model = new LaserGunModel();
  
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
        GL11.glScaled(1.5D, 1.5D, 1.5D);
        GL11.glRotated(50.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.15D, -3.0D, 0.3D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glRotated(-40.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.0D, -3.5D, 0.5D);
        break;
      case INVENTORY:
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
        GL11.glTranslated(0.0D, -2.2D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\item\RenderLaserGun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */