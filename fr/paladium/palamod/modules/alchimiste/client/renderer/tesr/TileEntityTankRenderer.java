package fr.paladium.palamod.modules.alchimiste.client.renderer.tesr;

import fr.paladium.palamod.modules.alchimiste.client.model.ModelBlock;
import fr.paladium.palamod.modules.alchimiste.client.model.ModelLiquid;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityTank;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumTank;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityTankRenderer extends TileEntitySpecialRenderer {
  public static ModelLiquid model = new ModelLiquid();
  
  public static ModelBlock blockModel = new ModelBlock();
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float p_147500_8_) {
    TileEntityTank tank = (TileEntityTank)tile;
    GL11.glPushMatrix();
    GL11.glTranslated(x, y - 1.0E-4D, z);
    GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    String liquid = ((TileEntityTank)tile).getLiquid();
    if (liquid == null)
      return; 
    if (liquid.trim().length() > 1) {
      String parsed = liquid.substring(5);
      if ("sulfuricwater".equals(parsed)) {
        func_147499_a(new ResourceLocation("palamod", "textures/blocks/fluids/sulfuricwater_flowing.png"));
      } else if ("water".equals(parsed)) {
        func_147499_a(new ResourceLocation("textures/blocks/water_still.png"));
      } else if ("lava".equals(parsed)) {
        func_147499_a(new ResourceLocation("textures/blocks/lava_still.png"));
      } else if (parsed.startsWith("eve")) {
        String type = parsed.split("-")[1];
        func_147499_a(new ResourceLocation("palamod", "textures/alchimiste/seve-" + type.toLowerCase() + ".png"));
      } 
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2896);
      GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
      float percentage = tank.getLiquidLevel() / tank.getTank().getMaxSeve() * 16.0F;
      GL11.glScaled(0.98D, 0.98D, (-percentage * 2.0F));
      drawTexturedModalRect(0, 0, 0, 32, 32, 32, 1.0D);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      GL11.glScaled(1.0D, (percentage / 16.0F), 1.0D);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      for (int i = 0; i < 4; i++) {
        GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-32.0D, 0.0D, 0.0D);
        drawTexturedModalRect(0, 0, 0, 0, 32, 32, 1.0D);
      } 
      GL11.glDisable(3042);
      GL11.glEnable(2896);
    } 
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    ResourceLocation toRender = new ResourceLocation("textures/blocks/stone.png");
    switch (tank.getTank()) {
      case AMETHYSTE:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/amethyste_tank.png");
        break;
      case PALADIUM:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/paladium_tank.png");
        break;
      case TITANE:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/titane_tank.png");
        break;
      case GOLD:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/gold_tank.png");
        break;
    } 
    GL11.glTranslated(x + 0.5D, y - 1.0D, z + 0.5D);
    func_147499_a(toRender);
    blockModel.renderAll();
    GL11.glPopMatrix();
    int var6 = MathHelper.func_76128_c((
        (Minecraft.func_71410_x()).field_71439_g.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 1.0D, y + 0.8D, z);
    GL11.glTranslated((var6 == 2 || var6 == 3) ? -1.0D : 0.0D, 0.0D, (var6 == 2 || var6 == 1) ? 1.0D : 0.0D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((90 * var6), 0.0D, 1.0D, 0.0D);
    GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
    GL11.glScaled(0.25D, 0.25D, 0.25D);
    int fontSize = (Minecraft.func_71410_x()).field_71466_p.field_78288_b;
    int separatorSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(" / ");
    int maxLiquidSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(tank.getTank().getMaxSeve() + "");
    int liquidSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(tank.getLiquidLevel() + "");
    String name = EnumChatFormatting.DARK_RED + I18n.func_135052_a(tank.getLiquid() + ".name", new Object[0]);
    int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(I18n.func_135052_a(tank.getLiquid() + ".name", new Object[0]));
    if (tank.getLiquid().trim().length() < 3) {
      nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a("Vide");
      name = EnumChatFormatting.DARK_RED + "Vide";
    } 
    GL11.glDisable(2896);
    MovingObjectPosition lastPosition = (Minecraft.func_71410_x()).field_71439_g.func_70614_a(8.0D, 1.0F);
    if (lastPosition == null || lastPosition.field_72313_a == null || tile == null)
      return; 
    if (MovingObjectPosition.MovingObjectType.BLOCK.equals(lastPosition.field_72313_a) && tile.field_145851_c == lastPosition.field_72311_b && tile.field_145848_d == lastPosition.field_72312_c && tile.field_145849_e == lastPosition.field_72309_d) {
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(tank.getLiquidLevel() + "", (64 - liquidSize) / 2, 0, 16777215);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(" / ", (64 - separatorSize) / 2, fontSize + 1, 16777215);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(tank.getTank().getMaxSeve() + "", (64 - maxLiquidSize) / 2, fontSize * 2 + 2, 16777215);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, (64 - nameSize) / 2, fontSize * 3 + 3, 16777215);
    } 
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
  
  public void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_, double zLevel) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((p_73729_1_ + 0), (p_73729_2_ + p_73729_6_), zLevel, ((p_73729_3_ + 0) * 0.00390625F), ((p_73729_4_ + p_73729_6_) * 0.00390625F));
    tessellator.func_78374_a((p_73729_1_ + p_73729_5_), (p_73729_2_ + p_73729_6_), zLevel, ((p_73729_3_ + p_73729_5_) * 0.00390625F), ((p_73729_4_ + p_73729_6_) * 0.00390625F));
    tessellator.func_78374_a((p_73729_1_ + p_73729_5_), (p_73729_2_ + 0), zLevel, ((p_73729_3_ + p_73729_5_) * 0.00390625F), ((p_73729_4_ + 0) * 0.00390625F));
    tessellator.func_78374_a((p_73729_1_ + 0), (p_73729_2_ + 0), zLevel, ((p_73729_3_ + 0) * 0.00390625F), ((p_73729_4_ + 0) * 0.00390625F));
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\tesr\TileEntityTankRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */