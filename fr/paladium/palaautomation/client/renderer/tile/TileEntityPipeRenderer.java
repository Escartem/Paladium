package fr.paladium.palaautomation.client.renderer.tile;

import fr.paladium.palaautomation.client.model.ModelAnglePipe;
import fr.paladium.palaautomation.client.model.ModelPipe;
import fr.paladium.palaautomation.common.block.pipe.PipeType;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import fr.paladium.palaautomation.common.util.IntLocation;
import fr.paladium.palaautomation.common.util.PipeError;
import fr.paladium.palaautomation.common.util.PipeUtils;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntityPipeRenderer extends TileEntitySpecialRenderer {
  public static final ModelPipe PIPE_MODEL = new ModelPipe();
  
  public static final ModelAnglePipe ANGLE_PIPE_MODEL = new ModelAnglePipe();
  
  public static final HashMap<PipeType, ResourceLocation> PIPE_TEXTURES = new HashMap<>();
  
  public static final HashMap<PipeType, ResourceLocation> ANGLE_PIPE_TEXTURES = new HashMap<>();
  
  static {
    PIPE_TEXTURES.put(PipeType.PALADIUM, new ResourceLocation("palaautomation", "textures/blocks/pipe/pipe_paladium.png"));
    PIPE_TEXTURES.put(PipeType.GREEN_PALADIUM, new ResourceLocation("palaautomation", "textures/blocks/pipe/pipe_green_paladium.png"));
    PIPE_TEXTURES.put(PipeType.MIXED_ENDIUM, new ResourceLocation("palaautomation", "textures/blocks/pipe/pipe_mixed_endium.png"));
    ANGLE_PIPE_TEXTURES.put(PipeType.PALADIUM, new ResourceLocation("palaautomation", "textures/blocks/pipe/pipe_angle_paladium.png"));
    ANGLE_PIPE_TEXTURES.put(PipeType.GREEN_PALADIUM, new ResourceLocation("palaautomation", "textures/blocks/pipe/pipe_angle_green_paladium.png"));
    ANGLE_PIPE_TEXTURES.put(PipeType.MIXED_ENDIUM, new ResourceLocation("palaautomation", "textures/blocks/pipe/pipe_angle_mixed_endium.png"));
  }
  
  public static ResourceLocation getTextureByType(PipeType type, boolean angle) {
    if (angle)
      return ANGLE_PIPE_TEXTURES.get(type); 
    return PIPE_TEXTURES.get(type);
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((ATileEntityPipe)te, x, y, z, ticks);
  }
  
  private EnumPipeFacing findVerticalFacing(ATileEntityPipe pipe, World world, int x, int y, int z) {
    EnumPipeFacing upperFacing = getUpperAngleFacing(pipe, world, x, y, z);
    if (upperFacing != null)
      return upperFacing; 
    EnumPipeFacing lowerFacing = getLowerAngleFacing(pipe, world, x, y, z);
    return lowerFacing;
  }
  
  private EnumPipeFacing getUpperAngleFacing(ATileEntityPipe pipe, World world, int x, int y, int z) {
    return getTargetFacing(pipe, world, x, y + 1, z);
  }
  
  private EnumPipeFacing getLowerAngleFacing(ATileEntityPipe pipe, World world, int x, int y, int z) {
    return getTargetFacing(pipe, world, x, y - 1, z);
  }
  
  private EnumPipeFacing getTargetFacing(ATileEntityPipe pipe, World world, int x, int y, int z) {
    ATileEntityPipe targetPipe = getPipe(world, x, y, z);
    if (targetPipe == null)
      return null; 
    if (!isVertical(targetPipe.getFacing()))
      return null; 
    return targetPipe.getFacing();
  }
  
  private boolean isVertical(EnumPipeFacing facing) {
    return (facing == EnumPipeFacing.UP || facing == EnumPipeFacing.DOWN);
  }
  
  private ATileEntityPipe getPipe(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity instanceof ATileEntityPipe)
      return (ATileEntityPipe)tileEntity; 
    return null;
  }
  
  private ATileEntityPipe findNextPipe(ATileEntityPipe pipe, World world, int x, int y, int z) {
    EnumPipeFacing facing = pipe.getFacing();
    if (facing == null)
      return null; 
    return getPipe(world, x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing
        .getFrontOffsetZ());
  }
  
  private void renderAll(ModelBase modelBase) {
    if (modelBase instanceof ModelPipe) {
      ((ModelPipe)modelBase).renderAll();
    } else if (modelBase instanceof ModelAnglePipe) {
      ((ModelAnglePipe)modelBase).renderAll();
    } 
  }
  
  private void renderPipe(ATileEntityPipe pipe, ResourceLocation texture, ModelBase model, double x, double y, double z, float rotX, float rotY, float rotZ) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y - 0.75D, z + 0.5D);
    GL11.glRotatef(rotX, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(rotY, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(rotZ, 0.0F, 0.0F, 1.0F);
    renderError(pipe);
    func_147499_a(texture);
    renderAll(model);
    GL11.glPopMatrix();
  }
  
  private void renderAnglePipe(ResourceLocation texture, ModelBase model, double x, double y, double z, float rotX, float rotY, float rotZ) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 0.5D, z - 0.5D);
    GL11.glRotatef(rotX, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(rotY, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(rotZ, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    renderAll(model);
    GL11.glPopMatrix();
  }
  
  private void renderDebugText(String text, double x, double y, double z) {
    double textOffsetY = 1.5D;
    RenderManager renderManager = RenderManager.field_78727_a;
    FontRenderer fontRenderer = func_147498_b();
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + textOffsetY, z + 0.5D);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-renderManager.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(renderManager.field_78732_j, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(-0.025F, -0.025F, 0.025F);
    GL11.glDisable(2896);
    GL11.glDepthMask(false);
    GL11.glDisable(2929);
    GL11.glDepthMask(true);
    GL11.glEnable(2929);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
  
  private ATileEntityPipe findParentPipe(ATileEntityPipe pipe) {
    IntLocation parentLocation = pipe.getParentLocation();
    if (parentLocation == null)
      return null; 
    TileEntity tileEntity = pipe.func_145831_w().func_147438_o(parentLocation.getX(), parentLocation.getY(), parentLocation
        .getZ());
    if (!(tileEntity instanceof ATileEntityPipe)) {
      pipe.updateParent(null);
      return null;
    } 
    return (ATileEntityPipe)tileEntity;
  }
  
  private EnumPipeFacing[] findFacing(ATileEntityPipe current, ATileEntityPipe parent, ATileEntityPipe next, double x, double y, double z) {
    if (parent == null || next == null) {
      if (parent != null) {
        Block block = PipeUtils.findNextBlock(current);
        if (block != null)
          return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
      } 
      return new EnumPipeFacing[] { current.getFacing() };
    } 
    if (current.getFacing() == EnumPipeFacing.UP || current.getFacing() == EnumPipeFacing.DOWN)
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
    if (next.getFacing() == EnumPipeFacing.DOWN)
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
    if (next.getFacing() == EnumPipeFacing.UP)
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
    if (EnumPipeFacing.isSameGroup(parent.getFacing(), current.getFacing()))
      return new EnumPipeFacing[] { current.getFacing() }; 
    if (EnumPipeFacing.isOpposite(parent.getFacing(), current.getFacing()))
      return new EnumPipeFacing[] { current.getFacing() }; 
    if (EnumPipeFacing.isOpposite(current.getFacing(), next.getFacing()))
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
    if (EnumPipeFacing.isOpposite(parent.getFacing(), next.getFacing()))
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
    if (parent.getFacing() == next.getFacing())
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() }; 
    if (parent.getFacing() == EnumPipeFacing.UP || parent.getFacing() == EnumPipeFacing.DOWN) {
      if (parent.getFacing() == EnumPipeFacing.UP && current.field_145848_d != parent.field_145848_d + 1)
        return new EnumPipeFacing[] { current.getFacing() }; 
      if (parent.getFacing() == EnumPipeFacing.DOWN && current.field_145848_d != parent.field_145848_d - 1)
        return new EnumPipeFacing[] { current.getFacing() }; 
      return new EnumPipeFacing[] { parent.getFacing(), current.getFacing() };
    } 
    return new EnumPipeFacing[] { parent.getFacing(), next.getFacing() };
  }
  
  private void renderError(ATileEntityPipe pipe) {
    if (pipe.getError() == null || pipe.getError() == PipeError.NO_ERROR)
      return; 
    GL11.glColor3f(0.4F, 0.0F, 0.0F);
  }
  
  private EnumPipeFacing findParentFacing(EnumPipeFacing[] facings) {
    return facings[0];
  }
  
  private EnumPipeFacing findNextFacing(EnumPipeFacing[] facings) {
    return facings[1];
  }
  
  private boolean isAngle(EnumPipeFacing[] facings) {
    if (facings.length != 2)
      return false; 
    EnumPipeFacing parentFacing = findParentFacing(facings);
    EnumPipeFacing nextFacing = findNextFacing(facings);
    return !EnumPipeFacing.isSameGroup(parentFacing, nextFacing);
  }
  
  private void renderBlockTileEntityAt(ATileEntityPipe tile, double x, double y, double z, float ticks) {
    if (tile == null || tile.func_145831_w() == null)
      return; 
    World world = tile.func_145831_w();
    PipeType pipeType = tile.getType();
    ATileEntityPipe nextPipe = PipeUtils.findNextPipe(tile, world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    ATileEntityPipe parentPipe = findParentPipe(tile);
    if (parentPipe != null && 
      tile.hasError() && !parentPipe.hasError())
      tile.setError(PipeError.NO_ERROR); 
    if (nextPipe != null && !nextPipe.hasParent())
      nextPipe.updateParent(tile); 
    if (parentPipe == null)
      tile.updateParent(null); 
    EnumPipeFacing[] facings = findFacing(tile, parentPipe, nextPipe, x, y, z);
    boolean isAngle = isAngle(facings);
    ModelBase model = isAngle ? (ModelBase)ANGLE_PIPE_MODEL : (ModelBase)PIPE_MODEL;
    ResourceLocation texture = getTextureByType(pipeType, isAngle);
    if (facings.length == 1 || !isAngle) {
      EnumPipeFacing facing = tile.getFacing();
      if (facing == null)
        return; 
      tile.updateClientFacing(facing);
      switch (facing) {
        case NORTH:
          renderPipe(tile, texture, model, x, y, z, 0.0F, 90.0F, 0.0F);
          break;
        case SOUTH:
          renderPipe(tile, texture, model, x, y, z, 0.0F, 270.0F, 0.0F);
          break;
        case EAST:
          renderPipe(tile, texture, model, x, y, z, 0.0F, 0.0F, 0.0F);
          break;
        case WEST:
          renderPipe(tile, texture, model, x, y, z, 0.0F, 180.0F, 0.0F);
          break;
        case UP:
          renderPipe(tile, texture, model, x + 1.25D, y + 1.25D, z, 0.0F, 0.0F, 90.0F);
          break;
        case DOWN:
          renderPipe(tile, texture, model, x - 1.25D, y + 1.25D, z, 0.0F, 0.0F, 270.0F);
          break;
      } 
      return;
    } 
    EnumPipeFacing parentFacing = findParentFacing(facings);
    EnumPipeFacing nextFacing = findNextFacing(facings);
    renderDebugText(parentFacing + "_" + nextFacing, x, y, z);
    if (parentFacing == null || nextFacing == null)
      return; 
    tile.updateClientFacing(parentFacing, nextFacing);
    float rotX = 0.0F, rotY = 0.0F, rotZ = 0.0F;
    double posX = x, posY = y, posZ = z;
    if (parentFacing == EnumPipeFacing.NORTH) {
      if (nextFacing == EnumPipeFacing.EAST) {
        rotX = 90.0F;
        rotZ = 180.0F;
        posY -= 0.25D;
      } else if (nextFacing == EnumPipeFacing.WEST) {
        rotX = 270.0F;
        posY += 1.25D;
      } else if (nextFacing == EnumPipeFacing.UP) {
        rotX = 0.0F;
        rotZ = 270.0F;
        rotY = 90.0F;
        posX += 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.DOWN) {
        rotX = 180.0F;
        rotZ = 270.0F;
        rotY = 270.0F;
        posX += -0.75D;
        posY += 0.5D;
      } 
    } else if (parentFacing == EnumPipeFacing.SOUTH) {
      if (nextFacing == EnumPipeFacing.EAST) {
        rotX = 270.0F;
        rotZ = 180.0F;
        posY += 1.25D;
      } else if (nextFacing == EnumPipeFacing.WEST) {
        rotX = 90.0F;
        posY -= 0.25D;
      } else if (nextFacing == EnumPipeFacing.UP) {
        rotX = 180.0F;
        rotZ = 90.0F;
        rotY = 270.0F;
        posX -= 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.DOWN) {
        rotX = 180.0F;
        rotZ = 270.0F;
        rotY = 90.0F;
        posX += 0.75D;
        posY += 0.5D;
      } 
    } else if (parentFacing == EnumPipeFacing.EAST) {
      if (nextFacing == EnumPipeFacing.NORTH) {
        rotX = 270.0F;
        rotZ = 270.0F;
        posY += 1.25D;
      } else if (nextFacing == EnumPipeFacing.SOUTH) {
        rotX = 90.0F;
        rotZ = 270.0F;
        posY -= 0.25D;
      } else if (nextFacing == EnumPipeFacing.UP) {
        rotX = 0.0F;
        rotZ = 270.0F;
        rotY = 0.0F;
        posZ += 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.DOWN) {
        rotX = 180.0F;
        rotZ = 270.0F;
        rotY = 0.0F;
        posZ -= 0.75D;
        posY += 0.5D;
      } 
    } else if (parentFacing == EnumPipeFacing.WEST) {
      if (nextFacing == EnumPipeFacing.NORTH) {
        rotX = 90.0F;
        rotZ = 90.0F;
        posY -= 0.25D;
      } else if (nextFacing == EnumPipeFacing.SOUTH) {
        rotX = 90.0F;
        rotY = 180.0F;
        rotZ = 270.0F;
        posY += 1.25D;
      } else if (nextFacing == EnumPipeFacing.UP) {
        rotZ = 270.0F;
        rotY = 180.0F;
        posY += 0.5D;
        posZ -= 0.75D;
        posX -= 0.0D;
      } else if (nextFacing == EnumPipeFacing.DOWN) {
        rotZ = 90.0F;
        posY += 0.5D;
        posZ += 0.75D;
        posX -= 0.0D;
      } 
    } else if (parentFacing == EnumPipeFacing.UP) {
      if (nextFacing == EnumPipeFacing.NORTH) {
        rotX = 180.0F;
        rotZ = 180.0F;
        rotY = 270.0F;
        posX -= 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.SOUTH) {
        rotX = 90.0F;
        rotZ = 270.0F;
        rotY = 90.0F;
        posX += 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.EAST) {
        rotX = 180.0F;
        rotZ = 180.0F;
        rotY = 0.0F;
        posZ -= 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.WEST) {
        rotX = 0.0F;
        rotZ = 0.0F;
        rotY = 0.0F;
        posZ += 0.75D;
        posY += 0.5D;
      } 
    } else if (parentFacing == EnumPipeFacing.DOWN) {
      if (nextFacing == EnumPipeFacing.NORTH) {
        rotX = 90.0F;
        rotZ = 90.0F;
        rotY = 90.0F;
        posX += 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.SOUTH) {
        rotX = 90.0F;
        rotZ = -90.0F;
        rotY = -90.0F;
        posX -= 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.EAST) {
        rotX = 180.0F;
        rotZ = 90.0F;
        rotY = 0.0F;
        posZ -= 0.75D;
        posY += 0.5D;
      } else if (nextFacing == EnumPipeFacing.WEST) {
        rotX = 180.0F;
        rotZ = 90.0F;
        rotY = 180.0F;
        posZ += 0.75D;
        posY += 0.5D;
      } 
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY - 0.5D, posZ);
    GL11.glTranslated(0.5D, 0.5D, 0.5D);
    GL11.glRotatef(rotX, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(rotY, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(rotZ, 0.0F, 0.0F, 1.0F);
    GL11.glTranslated(-0.5D, -0.5D, -0.5D);
    renderError(tile);
    func_147499_a(texture);
    renderAll(model);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\renderer\tile\TileEntityPipeRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */