package fr.paladium.palamod.modules.alchimiste.client.renderer.tesr;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.client.model.ModelBlock;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityAlchemistPortalController;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.PortalRecipe;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityAlchemistPortalControllerRenderer extends TileEntitySpecialRenderer {
  private static final ModelBlock block = new ModelBlock();
  
  private static ResourceLocation tex = new ResourceLocation("textures/blocks/stone.png");
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float ticks) {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    Block b = tile.func_145831_w().func_147439_a(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    GL11.glPushMatrix();
    GL11.glEnable(2896);
    GL11.glTranslated(x + 0.5D, y - 1.0D, z + 0.5D);
    if (b == BlocksRegister.AMETHYSTE_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/amethyste_portal_block.png");
    } else if (b == BlocksRegister.TITANE_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/titane_portal_block.png");
    } else if (b == BlocksRegister.PALADIUM_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/paladium_portal_block.png");
    } else if (b == BlocksRegister.ENDIUM_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/endium_portal_block.png");
    } 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(tex);
    block.renderAll();
    if (((TileEntityAlchemistPortalController)tile).getStack() != null) {
      ItemStack stack = ((TileEntityAlchemistPortalController)tile).getStack();
      GL11.glTranslated(0.0D, 2.0D, 0.0D);
      GL11.glScaled(0.45D, 0.45D, 0.45D);
      GL11.glRotated(1.0D, 0.0D, -0.001D, 0.0D);
      GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(30.0D, 1.0D, 0.0D, 1.0D);
      GL11.glRotated(5.0D, -12.0D, -7.0D, -7.0D);
      (new ItemRenderer(Minecraft.func_71410_x())).func_78443_a((EntityLivingBase)(Minecraft.func_71410_x()).field_71439_g, stack, (int)ticks);
    } 
    GL11.glDisable(2896);
    GL11.glPopMatrix();
    PortalRecipe recipe = PAlchimiste.portalController.getRecipeFor(((TileEntityAlchemistPortalController)tile).getType());
    if (recipe != null) {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y + 2.0D, z + 0.5D);
      GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
      GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
      GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
      GL11.glScaled(0.33D, 0.33D, 0.33D);
      GL11.glDisable(2896);
      String name = "§eSève : §f" + ((TileEntityAlchemistPortalController)tile).getSeveTotal() + "/" + (recipe.getRequiredFullSeve() * 1000);
      int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
      MovingObjectPosition lastPosition = (Minecraft.func_71410_x()).field_71439_g.func_70614_a(8.0D, 1.0F);
      if (lastPosition != null && MovingObjectPosition.MovingObjectType.BLOCK.equals(lastPosition.field_72313_a) && 
        tile.field_145851_c == lastPosition.field_72311_b && tile.field_145848_d == lastPosition.field_72312_c && tile.field_145849_e == lastPosition.field_72309_d)
        (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215); 
      GL11.glEnable(2896);
      GL11.glPopMatrix();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\tesr\TileEntityAlchemistPortalControllerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */