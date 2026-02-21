package fr.paladium.palamod.modules.alchimiste.client.renderer.inventory;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.client.render.shader.Shader;
import fr.paladium.palamod.client.render.shader.ShaderFactory;
import fr.paladium.palamod.modules.alchimiste.client.model.ModelBlock;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityWood;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import fr.paladium.palamod.modules.world.PWorld;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TIleEntityWoodInventoryRenderer implements ISimpleBlockRenderingHandler {
  private static Shader disableShader;
  
  public TIleEntityWoodInventoryRenderer() {
    try {
      if (disableShader == null)
        disableShader = (new ShaderFactory()).getShader("grey", new ResourceLocation[] { new ResourceLocation("palamod", "shader/greyscale.frag"), new ResourceLocation("palamod", "shader/greyscale.vert") }); 
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void renderInventoryBlock(Block b, int metadata, int modelId, RenderBlocks renderer) {
    GL11.glPushMatrix();
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslated(0.0D, -1.5D, 0.0D);
    ResourceLocation tex = new ResourceLocation("palamod", "textures/blocks/log/erable.png");
    Minecraft.func_71410_x().func_110434_K().func_110577_a(tex);
    (new ModelBlock()).renderAll();
    GL11.glPopMatrix();
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    TileEntityWood wood = (TileEntityWood)world.func_147438_o(x, y, z);
    Tessellator tessellator = Tessellator.field_78398_a;
    IIcon icotop = null;
    IIcon icoside = null;
    int level = wood.getSeveLevel();
    double real = level / 36.0D;
    if (wood.getSeveType().equals("Ostrya")) {
      icotop = ((BlockLog)PWorld.LOG_OSTRYA).func_149691_a(1, 0);
      icoside = ((BlockLog)PWorld.LOG_OSTRYA).func_149691_a(4, 0);
    } 
    if (wood.getSeveType().equals("Erable")) {
      icotop = ((BlockLog)PWorld.LOG_ERABLE).func_149691_a(1, 0);
      icoside = ((BlockLog)PWorld.LOG_ERABLE).func_149691_a(4, 0);
    } 
    if (wood.getSeveType().equals("Jacaranda")) {
      icotop = ((BlockLog)PWorld.LOG_JACARANDA).func_149691_a(1, 0);
      icoside = ((BlockLog)PWorld.LOG_JACARANDA).func_149691_a(4, 0);
    } 
    if (wood.getSeveType().equals("Judeecercis")) {
      icotop = ((BlockLog)PWorld.LOG_JUDEECERCIS).func_149691_a(1, 0);
      icoside = ((BlockLog)PWorld.LOG_JUDEECERCIS).func_149691_a(4, 0);
    } 
    if (icotop == null || icoside == null)
      return false; 
    int brightness = block.func_149677_c(world, x, y, z);
    if (real != 1.0D) {
      renderer.field_147857_k = 1.0D;
      renderer.field_147855_j = real;
      tessellator.func_78380_c((renderer.field_147859_h > 0.0D) ? brightness : block
          .func_149677_c(world, x - 1, y, z));
      tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
      renderer.func_147798_e(block, x, y, z, ((BlockLog)BlocksRegister.DEAD_WOOD).func_149691_a(4, 0));
      tessellator.func_78380_c((renderer.field_147861_i < 1.0D) ? brightness : block
          .func_149677_c(world, x + 1, y, z));
      tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
      renderer.func_147764_f(block, x, y, z, ((BlockLog)BlocksRegister.DEAD_WOOD).func_149691_a(4, 0));
      tessellator.func_78380_c((renderer.field_147855_j > 0.0D) ? brightness : block
          .func_149677_c(world, x, y - 1, z));
      tessellator.func_78386_a(0.5F, 0.5F, 0.5F);
      renderer.func_147768_a(block, x, y, z, ((BlockLog)BlocksRegister.DEAD_WOOD).func_149691_a(1, 0));
      tessellator.func_78380_c((renderer.field_147857_k < 1.0D) ? brightness : block
          .func_149677_c(world, x, y + 1, z));
      tessellator.func_78386_a(1.0F, 1.0F, 1.0F);
      renderer.func_147806_b(block, x, y, z, ((BlockLog)BlocksRegister.DEAD_WOOD).func_149691_a(1, 0));
      tessellator.func_78380_c((renderer.field_147851_l > 0.0D) ? brightness : block
          .func_149677_c(world, x, y, z - 1));
      tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
      renderer.func_147761_c(block, x, y, z, ((BlockLog)BlocksRegister.DEAD_WOOD).func_149691_a(4, 0));
      tessellator.func_78380_c((renderer.field_147853_m < 1.0D) ? brightness : block
          .func_149677_c(world, x, y, z + 1));
      tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
      renderer.func_147734_d(block, x, y, z, ((BlockLog)BlocksRegister.DEAD_WOOD).func_149691_a(4, 0));
      renderer.field_147857_k = 1.0D;
      renderer.field_147855_j = 0.0D;
    } 
    renderer.field_147857_k = real;
    tessellator.func_78380_c((renderer.field_147859_h > 0.0D) ? brightness : block
        .func_149677_c(world, x - 1, y, z));
    tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
    renderer.func_147798_e(block, x, y, z, icoside);
    tessellator.func_78380_c((renderer.field_147861_i < 1.0D) ? brightness : block
        .func_149677_c(world, x + 1, y, z));
    tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
    renderer.func_147764_f(block, x, y, z, icoside);
    tessellator.func_78380_c((renderer.field_147855_j > 0.0D) ? brightness : block
        .func_149677_c(world, x, y - 1, z));
    tessellator.func_78386_a(0.5F, 0.5F, 0.5F);
    renderer.func_147768_a(block, x, y, z, icotop);
    tessellator.func_78380_c((renderer.field_147857_k < 1.0D) ? brightness : block
        .func_149677_c(world, x, y + 1, z));
    tessellator.func_78386_a(1.0F, 1.0F, 1.0F);
    renderer.func_147806_b(block, x, y, z, icotop);
    tessellator.func_78380_c((renderer.field_147851_l > 0.0D) ? brightness : block
        .func_149677_c(world, x, y, z - 1));
    tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
    renderer.func_147761_c(block, x, y, z, icoside);
    tessellator.func_78380_c((renderer.field_147853_m < 1.0D) ? brightness : block
        .func_149677_c(world, x, y, z + 1));
    tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
    renderer.func_147734_d(block, x, y, z, icoside);
    return true;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return ClientProxy.woodAlchimist;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\inventory\TIleEntityWoodInventoryRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */