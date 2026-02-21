package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Renderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBlockSpike implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    renderSpikeBlock((IBlockAccess)(Minecraft.func_71410_x()).field_71441_e, 0, 0, 0, 1, 0, block, renderer, -1);
    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
  
  public boolean renderSpikeBlock(IBlockAccess world, int x, int y, int z, int side, int type, Block block, RenderBlocks renderer, int brightness) {
    float ax = 0.0F;
    float ay = 0.0F;
    float az = 0.0F;
    float bx = 1.0F;
    float by = 0.0F;
    float bz = 0.0F;
    float cx = 0.0F;
    float cy = 0.0F;
    float cz = 1.0F;
    float dx = 1.0F;
    float dy = 0.0F;
    float dz = 1.0F;
    float ex = 0.0F;
    float ey = 1.0F;
    float ez = 0.0F;
    float fx = 1.0F;
    float fy = 1.0F;
    float fz = 0.0F;
    float gx = 0.0F;
    float gy = 1.0F;
    float gz = 1.0F;
    float hx = 1.0F;
    float hy = 1.0F;
    float hz = 1.0F;
    switch (side) {
      case 0:
        ax = az = bx = bz = cx = cz = dx = dz = 0.5F;
        break;
      case 1:
        ex = ez = fx = fz = gx = gz = hx = hz = 0.5F;
        break;
      case 2:
        ay = by = ey = fy = ax = bx = ex = fx = 0.5F;
        break;
      case 3:
        cy = dy = gy = hy = cx = dx = gx = hx = 0.5F;
        break;
      case 4:
        ay = cy = ey = gy = az = cz = ez = gz = 0.5F;
        break;
      case 5:
        by = dy = fy = hy = bz = dz = fz = hz = 0.5F;
        break;
      default:
        return false;
    } 
    IIcon texture = block.func_149691_a(side, type);
    if (renderer.func_147744_b())
      texture = renderer.field_147840_d; 
    Tessellator tessellator = Tessellator.field_78398_a;
    if (brightness >= 0)
      tessellator.func_78380_c(brightness); 
    boolean inventory = (brightness < 0);
    if (brightness >= 0)
      tessellator.func_78386_a(0.5F, 0.5F, 0.5F); 
    if (!renderer.func_147744_b())
      texture = block.func_149691_a(0, side + type * 6); 
    if (inventory) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
    } 
    if (side != 0) {
      float[] u = { ax, bx, dx, cx };
      float[] v = { az, bz, dz, cz };
      int rotation = calcRotation(0, side);
      tessellator.func_78374_a((x + ax), (y + ay), (z + az), getU(0, texture, rotation, u, v), 
          getV(0, texture, rotation, u, v));
      tessellator.func_78374_a((x + bx), (y + by), (z + bz), getU(1, texture, rotation, u, v), 
          getV(1, texture, rotation, u, v));
      tessellator.func_78374_a((x + dx), (y + dy), (z + dz), getU(2, texture, rotation, u, v), 
          getV(2, texture, rotation, u, v));
      tessellator.func_78374_a((x + cx), (y + cy), (z + cz), getU(3, texture, rotation, u, v), 
          getV(3, texture, rotation, u, v));
    } 
    if (inventory)
      tessellator.func_78381_a(); 
    if (inventory) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    } 
    if (brightness >= 0)
      tessellator.func_78386_a(1.0F, 1.0F, 1.0F); 
    if (!renderer.func_147744_b())
      texture = block.func_149691_a(1, side + type * 6); 
    if (side != 1) {
      float[] u = { ex, gx, hx, fx };
      float[] v = { ez, gz, hz, fz };
      int rotation = calcRotation(1, side);
      tessellator.func_78374_a((x + ex), (y + ey), (z + ez), getU(0, texture, rotation, u, v), 
          getV(0, texture, rotation, u, v));
      tessellator.func_78374_a((x + gx), (y + gy), (z + gz), getU(1, texture, rotation, u, v), 
          getV(1, texture, rotation, u, v));
      tessellator.func_78374_a((x + hx), (y + hy), (z + hz), getU(2, texture, rotation, u, v), 
          getV(2, texture, rotation, u, v));
      tessellator.func_78374_a((x + fx), (y + fy), (z + fz), getU(3, texture, rotation, u, v), 
          getV(3, texture, rotation, u, v));
    } 
    if (inventory)
      tessellator.func_78381_a(); 
    if (brightness >= 0)
      if (side == 0) {
        tessellator.func_78386_a(0.65F, 0.65F, 0.65F);
      } else if (side == 1) {
        tessellator.func_78386_a(0.9F, 0.9F, 0.9F);
      } else {
        tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
      }  
    if (!renderer.func_147744_b())
      texture = block.func_149691_a(2, side + type * 6); 
    if (inventory) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.445F, 0.894F);
    } 
    if (side != 2) {
      float[] u = { 1.0F - ax, 1.0F - ex, 1.0F - fx, 1.0F - bx };
      float[] v = { 1.0F - ay, 1.0F - ey, 1.0F - fy, 1.0F - by };
      int rotation = calcRotation(2, side);
      tessellator.func_78374_a((x + ax), (y + ay), (z + az), getU(0, texture, rotation, u, v), 
          getV(0, texture, rotation, u, v));
      tessellator.func_78374_a((x + ex), (y + ey), (z + ez), getU(1, texture, rotation, u, v), 
          getV(1, texture, rotation, u, v));
      tessellator.func_78374_a((x + fx), (y + fy), (z + fz), getU(2, texture, rotation, u, v), 
          getV(2, texture, rotation, u, v));
      tessellator.func_78374_a((x + bx), (y + by), (z + bz), getU(3, texture, rotation, u, v), 
          getV(3, texture, rotation, u, v));
    } 
    if (inventory)
      tessellator.func_78381_a(); 
    if (brightness >= 0)
      if (side == 0) {
        tessellator.func_78386_a(0.65F, 0.65F, 0.65F);
      } else if (side == 1) {
        tessellator.func_78386_a(0.9F, 0.9F, 0.9F);
      } else {
        tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
      }  
    if (!renderer.func_147744_b())
      texture = block.func_149691_a(3, side + type * 6); 
    if (inventory) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.445F, -0.894F);
    } 
    if (side != 3) {
      float[] u = { dx, hx, gx, cx };
      float[] v = { 1.0F - dy, 1.0F - hy, 1.0F - gy, 1.0F - cy };
      int rotation = calcRotation(3, side);
      tessellator.func_78374_a((x + dx), (y + dy), (z + dz), getU(0, texture, rotation, u, v), 
          getV(0, texture, rotation, u, v));
      tessellator.func_78374_a((x + hx), (y + hy), (z + hz), getU(1, texture, rotation, u, v), 
          getV(1, texture, rotation, u, v));
      tessellator.func_78374_a((x + gx), (y + gy), (z + gz), getU(2, texture, rotation, u, v), 
          getV(2, texture, rotation, u, v));
      tessellator.func_78374_a((x + cx), (y + cy), (z + cz), getU(3, texture, rotation, u, v), 
          getV(3, texture, rotation, u, v));
    } 
    if (inventory)
      tessellator.func_78381_a(); 
    if (brightness >= 0)
      if (side == 0) {
        tessellator.func_78386_a(0.55F, 0.55F, 0.55F);
      } else if (side == 1) {
        tessellator.func_78386_a(0.7F, 0.7F, 0.7F);
      } else {
        tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
      }  
    if (!renderer.func_147744_b())
      texture = block.func_149691_a(4, side + type * 6); 
    if (inventory) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.894F, 0.445F, 0.0F);
    } 
    if (side != 4) {
      float[] u = { cz, gz, ez, az };
      float[] v = { 1.0F - cy, 1.0F - gy, 1.0F - ey, 1.0F - ay };
      int rotation = calcRotation(4, side);
      tessellator.func_78374_a((x + cx), (y + cy), (z + cz), getU(0, texture, rotation, u, v), 
          getV(0, texture, rotation, u, v));
      tessellator.func_78374_a((x + gx), (y + gy), (z + gz), getU(1, texture, rotation, u, v), 
          getV(1, texture, rotation, u, v));
      tessellator.func_78374_a((x + ex), (y + ey), (z + ez), getU(2, texture, rotation, u, v), 
          getV(2, texture, rotation, u, v));
      tessellator.func_78374_a((x + ax), (y + ay), (z + az), getU(3, texture, rotation, u, v), 
          getV(3, texture, rotation, u, v));
    } 
    if (inventory)
      tessellator.func_78381_a(); 
    if (brightness >= 0)
      if (side == 0) {
        tessellator.func_78386_a(0.55F, 0.55F, 0.55F);
      } else if (side == 1) {
        tessellator.func_78386_a(0.7F, 0.7F, 0.7F);
      } else {
        tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
      }  
    if (!renderer.func_147744_b())
      texture = block.func_149691_a(5, side + type * 6); 
    if (inventory) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(-0.894F, 0.445F, 0.0F);
    } 
    if (side != 5) {
      float[] u = { 1.0F - bz, 1.0F - fz, 1.0F - hz, 1.0F - dz };
      float[] v = { 1.0F - by, 1.0F - fy, 1.0F - hy, 1.0F - dy };
      int rotation = calcRotation(5, side);
      tessellator.func_78374_a((x + bx), (y + by), (z + bz), getU(0, texture, rotation, u, v), 
          getV(0, texture, rotation, u, v));
      tessellator.func_78374_a((x + fx), (y + fy), (z + fz), getU(1, texture, rotation, u, v), 
          getV(1, texture, rotation, u, v));
      tessellator.func_78374_a((x + hx), (y + hy), (z + hz), getU(2, texture, rotation, u, v), 
          getV(2, texture, rotation, u, v));
      tessellator.func_78374_a((x + dx), (y + dy), (z + dz), getU(3, texture, rotation, u, v), 
          getV(3, texture, rotation, u, v));
    } 
    if (inventory)
      tessellator.func_78381_a(); 
    return true;
  }
  
  public float getU(int i, IIcon texture, int rotation, float[] u, float[] v) {
    switch (rotation % 4) {
      case 0:
        return texture.func_94214_a((u[i % 4] * 16.0F));
      case 1:
        return texture.func_94214_a((v[i % 4] * 16.0F));
      case 2:
        return texture.func_94214_a((16.0F - u[i % 4] * 16.0F));
      case 3:
        return texture.func_94214_a((16.0F - v[i % 4] * 16.0F));
    } 
    return 0.0F;
  }
  
  public float getV(int i, IIcon texture, int rotation, float[] u, float[] v) {
    switch (rotation % 4) {
      case 0:
        return texture.func_94207_b((v[i % 4] * 16.0F));
      case 1:
        return texture.func_94207_b((16.0F - u[i % 4] * 16.0F));
      case 2:
        return texture.func_94207_b((16.0F - v[i % 4] * 16.0F));
      case 3:
        return texture.func_94207_b((u[i % 4] * 16.0F));
    } 
    return 0.0F;
  }
  
  public int calcRotation(int side, int direction) {
    if (side == direction)
      return 0; 
    if (side == Facing.field_71588_a[direction])
      return 0; 
    if (direction == 1)
      return 0; 
    if (direction == 0)
      return 2; 
    if (side == 0 || side == 1) {
      (new int[4])[0] = 0;
      (new int[4])[1] = 2;
      (new int[4])[2] = 3;
      (new int[4])[3] = 1;
      return (new int[4])[direction - 2];
    } 
    return 1 + (side + direction + direction / 2) % 2 * 2;
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    int side = world.func_72805_g(x, y, z) % 6;
    int type = (world.func_72805_g(x, y, z) - side) / 6;
    int brightness = block.func_149677_c(world, x, y, z);
    return renderSpikeBlock(world, x, y, z, side, type, block, renderer, brightness);
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return PRegister_Renderer.renderBlockSpikeId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderBlockSpike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */