package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items.ModelGunSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class GunSwordItemRenderer implements IItemRenderer {
  public static ModelGunSword model = new ModelGunSword();
  
  public static ResourceLocation texture;
  
  public GunSwordItemRenderer() {
    texture = new ResourceLocation("palamod", "textures/items/gun_sword.png");
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(80.0D, 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(2.0D, -2.0D, -0.5D);
    GL11.glRotated(45.0D, 1.0D, 0.0D, 0.0D);
    switch (type) {
      case ENTITY:
        GL11.glTranslated(-2.0D, 0.0D, 0.0D);
        break;
      case EQUIPPED:
        GL11.glScaled(1.0D, 1.5D, 1.5D);
        GL11.glRotated(-100.0D, 1.0D, 0.0D, 0.0D);
        GL11.glRotated(100.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(100.0D, 0.0D, 1.0D, 1.0D);
        GL11.glTranslated(1.0D, -1.83D, -0.6D);
        GL11.glRotated(-160.0D, 0.0D, 1.0D, 0.0D);
        if (entityClientPlayerMP.func_71052_bv() >= 8) {
          GL11.glRotated(40.0D, 1.0D, 0.0D, 0.0D);
          GL11.glTranslated(0.0D, -0.5D, -1.4D);
        } 
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glTranslated(-0.8D, -0.3D, 0.0D);
        GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(315.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(-0.8D, -0.3D, 0.0D);
        break;
      case INVENTORY:
        GL11.glScaled(0.6D, 0.6D, 0.6D);
        GL11.glRotated(60.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(260.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.0D, 0.4D, 2.5D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\items\GunSwordItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */