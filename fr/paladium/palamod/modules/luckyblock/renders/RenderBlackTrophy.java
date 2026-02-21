package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.trophy.ModelBlackTrophy1;
import fr.paladium.palamod.modules.luckyblock.renders.models.trophy.ModelBlackTrophy2;
import fr.paladium.palamod.modules.luckyblock.renders.models.trophy.ModelBlackTrophy3;
import fr.paladium.palamod.modules.luckyblock.renders.models.trophy.ModelBlackTrophy4;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderBlackTrophy extends TileEntitySpecialRenderer implements IItemRenderer {
  private static ModelBlackTrophy1 model1 = new ModelBlackTrophy1();
  
  private static ModelBlackTrophy2 model2 = new ModelBlackTrophy2();
  
  private static ModelBlackTrophy3 model3 = new ModelBlackTrophy3();
  
  private static ModelBlackTrophy4 model4 = new ModelBlackTrophy4();
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/trophy_black_lb.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float partialTicks) {
    GL11.glPushMatrix();
    GL11.glDisable(2896);
    GL11.glTranslated(x + 0.5D, y, z + 0.5D);
    func_147499_a(texture);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glScaled(0.075D, 0.075D, 0.075D);
    GL11.glTranslated(0.0D, -25.0D, 0.0D);
    switch ((Minecraft.func_71410_x()).field_71441_e.func_72805_g(te.field_145851_c, te.field_145848_d, te.field_145849_e)) {
      case 3:
        model4.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case 0:
        model1.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case 1:
        model2.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case 2:
        model3.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
    } 
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    GL11.glPushMatrix();
    GL11.glDisable(2896);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glScaled(0.075D, 0.075D, 0.075D);
    GL11.glTranslated(0.0D, -15.0D, 0.0D);
    switch (item.func_77960_j()) {
      case 3:
        model4.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case 0:
        model1.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case 1:
        model2.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
      case 2:
        model3.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        break;
    } 
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderBlackTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */