package fr.paladium.palamod.modules.luckyblock.renders.christmas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.blocks.models.christmas.ModelDeerMockup;
import fr.paladium.palamod.modules.luckyblock.blocks.models.christmas.ModelDeerRushingMockup;
import fr.paladium.palamod.modules.luckyblock.blocks.models.christmas.ModelDeerWalkingMockup;
import fr.paladium.palamod.modules.luckyblock.blocks.models.christmas.ModelSantaClausMockup;
import fr.paladium.palamod.modules.luckyblock.blocks.models.christmas.ModelSleighMockup;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasMockup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderChristmasMockup extends TileEntitySpecialRenderer implements IItemRenderer {
  private static ModelBase[] mockupModels = new ModelBase[] { (ModelBase)new ModelDeerRushingMockup(), (ModelBase)new ModelDeerMockup(), (ModelBase)new ModelDeerRushingMockup(), (ModelBase)new ModelDeerWalkingMockup(), (ModelBase)new ModelDeerMockup(), (ModelBase)new ModelDeerWalkingMockup(), (ModelBase)new ModelSleighMockup(), (ModelBase)new ModelSantaClausMockup() };
  
  private static ResourceLocation[] mockupTextures = new ResourceLocation[] { new ResourceLocation("palamod", "textures/models/deer_0.png"), new ResourceLocation("palamod", "textures/models/deer_1.png"), new ResourceLocation("palamod", "textures/models/deer_2.png"), new ResourceLocation("palamod", "textures/models/deer_3.png"), new ResourceLocation("palamod", "textures/models/deer_4.png"), new ResourceLocation("palamod", "textures/models/deer_5.png"), new ResourceLocation("palamod", "textures/models/sleigh.png"), new ResourceLocation("palamod", "textures/models/santa.png") };
  
  public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
    int metadata = (Minecraft.func_71410_x()).field_71441_e.func_72805_g(tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
    if (metadata >= 8 || metadata < 0)
      metadata = 0; 
    GL11.glPushMatrix();
    GL11.glDisable(2896);
    GL11.glTranslated(x + 0.5D, y, z + 0.5D);
    func_147499_a(mockupTextures[metadata]);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(((TileEntityChristmasMockup)tileEntity).getOrientation(), 0.0D, 1.0D, 0.0D);
    if (metadata == 6) {
      GL11.glScaled(0.065D, 0.05D, 0.06D);
      GL11.glTranslated(1.5D, -25.0D, -23.0D);
    } else if (metadata == 7) {
      GL11.glScaled(0.05D, 0.05D, 0.05D);
      GL11.glTranslated(5.0D, -25.0D, 10.0D);
    } else {
      GL11.glScaled(0.05D, 0.05D, 0.05D);
      GL11.glTranslated(0.0D, -25.0D, 0.0D);
    } 
    mockupModels[metadata].func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
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
    Minecraft.func_71410_x().func_110434_K().func_110577_a(mockupTextures[item.func_77960_j()]);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    if (item.func_77960_j() == 6) {
      GL11.glScaled(0.012D, 0.012D, 0.012D);
    } else if (item.func_77960_j() == 7) {
      GL11.glScaled(0.04D, 0.04D, 0.04D);
    } else {
      GL11.glScaled(0.032D, 0.032D, 0.032D);
    } 
    if (item.func_77960_j() == 1 || item.func_77960_j() == 3 || item.func_77960_j() == 4 || item.func_77960_j() == 5) {
      GL11.glTranslated(0.0D, -7.0D, 0.0D);
    } else if (item.func_77960_j() == 6) {
      GL11.glTranslated(35.0D, -35.0D, 0.0D);
    } else if (item.func_77960_j() == 7) {
      GL11.glTranslated(4.0D, -7.0D, 0.0D);
    } else {
      GL11.glTranslated(0.0D, -5.0D, 0.0D);
    } 
    mockupModels[item.func_77960_j()].func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\christmas\RenderChristmasMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */