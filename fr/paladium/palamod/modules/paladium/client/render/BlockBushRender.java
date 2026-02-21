package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class BlockBushRender implements ISimpleBlockRenderingHandler {
  public static int model = RenderingRegistry.getNextAvailableRenderId();
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    if (modelId == model) {
      int md = world.func_72805_g(x, y, z);
      if (md < 4) {
        renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
        renderer.func_147784_q(block, x, y, z);
      } else if (md < 8) {
        renderer.func_147782_a(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D);
        renderer.func_147784_q(block, x, y, z);
      } else {
        renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderer.func_147784_q(block, x, y, z);
      } 
      return true;
    } 
    return false;
  }
  
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    if (modelID == model) {
      renderer.func_147782_a(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D);
      Tessellator tessellator = Tessellator.field_78398_a;
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, getIconSafe(block.func_149691_a(0, metadata)));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, getIconSafe(block.func_149691_a(1, metadata)));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, getIconSafe(block.func_149691_a(2, metadata)));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, getIconSafe(block.func_149691_a(3, metadata)));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, getIconSafe(block.func_149691_a(4, metadata)));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, getIconSafe(block.func_149691_a(5, metadata)));
      tessellator.func_78381_a();
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    } 
  }
  
  public boolean shouldRender3DInInventory(int modelID) {
    return true;
  }
  
  public int getRenderId() {
    return model;
  }
  
  private IIcon getIconSafe(IIcon icon) {
    if (icon != null)
      return icon; 
    return (IIcon)((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110575_b)).func_110572_b("missingno");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\BlockBushRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */