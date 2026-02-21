package fr.paladium.palaautomation.common.block.pipe.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.common.block.pipe.PipeType;
import fr.paladium.palaautomation.common.tab.AutomationTab;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaautomation.server.listener.PipePlaceListener;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class ABlockPipe extends Block {
  public static final String TILE_ID = "tileEntityPipe";
  
  protected static final String PREFIX_NAME = "pipe_%s";
  
  public PipeType type;
  
  public PipeType getType() {
    return this.type;
  }
  
  public ABlockPipe(PipeType type) {
    super(Material.field_76233_E);
    String name = String.format("pipe_%s", new Object[] { type.getName() });
    String textureName = String.format("palaautomation:pipe/" + name, new Object[0]);
    func_149663_c(name);
    func_149658_d(textureName);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)AutomationTab.getInstance());
    this.type = type;
    func_149711_c(1.5F);
    func_149752_b(10.0F);
  }
  
  private ATileEntityPipe getPipe(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity instanceof ATileEntityPipe)
      return (ATileEntityPipe)tileEntity; 
    return null;
  }
  
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    ATileEntityPipe pipe = getPipe(world, x, y, z);
    if (pipe == null)
      return null; 
    EnumPipeFacing facing = pipe.getFacing();
    if (facing != null)
      return getBoundingBox(x, y, z, pipe.getClientAngles()); 
    return super.func_149668_a(world, x, y, z);
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    ATileEntityPipe pipe = getPipe(world, x, y, z);
    if (pipe == null)
      return null; 
    EnumPipeFacing facing = pipe.getFacing();
    if (facing != null)
      return getBoundingBox(x, y, z, pipe.getClientAngles()); 
    return super.func_149668_a(world, x, y, z);
  }
  
  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity entity) {
    ATileEntityPipe pipe = getPipe(world, x, y, z);
    if (pipe == null || pipe.getClientAngles() == null)
      return; 
    AxisAlignedBB box = getBoundingBox(x, y, z, pipe.getClientAngles());
    if (mask.func_72326_a(box))
      list.add(box); 
  }
  
  private AxisAlignedBB getBoundingBox(int x, int y, int z, EnumPipeFacing[] facings) {
    if (facings == null)
      return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1)); 
    if (facings.length == 1)
      return createBox(x, y, z, facings[0]); 
    EnumPipeFacing firstFacing = facings[0];
    EnumPipeFacing secondFacing = facings[1];
    if (firstFacing == EnumPipeFacing.NORTH) {
      if (secondFacing == EnumPipeFacing.EAST)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.25F), (x + 1.0F), (y + 0.75F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.WEST)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.25F), (x + 0.8F), (y + 0.75F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.UP)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.2F), (x + 0.8F), (y + 1.0F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.DOWN)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.0F), (z + 0.2F), (x + 0.8F), (y + 0.75F), (z + 1.0F)); 
    } else if (firstFacing == EnumPipeFacing.SOUTH) {
      if (secondFacing == EnumPipeFacing.EAST)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.0F), (x + 1.0F), (y + 0.75F), (z + 0.75F)); 
      if (secondFacing == EnumPipeFacing.WEST)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.0F), (x + 0.8F), (y + 0.75F), (z + 0.75F)); 
      if (secondFacing == EnumPipeFacing.UP)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.0F), (x + 0.8F), (y + 1.0F), (z + 0.8F)); 
      if (secondFacing == EnumPipeFacing.DOWN)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.0F), (z + 0.0F), (x + 0.8F), (y + 0.75F), (z + 0.8F)); 
    } else if (firstFacing == EnumPipeFacing.EAST) {
      if (secondFacing == EnumPipeFacing.NORTH)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.0F), (x + 0.75F), (y + 0.75F), (z + 0.75F)); 
      if (secondFacing == EnumPipeFacing.SOUTH)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.25F), (x + 0.75F), (y + 0.75F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.UP)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.2F), (x + 0.75F), (y + 1.0F), (z + 0.8F)); 
      if (secondFacing == EnumPipeFacing.DOWN)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.2F), (x + 0.75F), (y + 0.75F), (z + 0.8F)); 
    } else if (firstFacing == EnumPipeFacing.WEST) {
      if (secondFacing == EnumPipeFacing.NORTH)
        return AxisAlignedBB.func_72330_a((x + 0.25F), (y + 0.25F), (z + 0.0F), (x + 1.0F), (y + 0.75F), (z + 0.75F)); 
      if (secondFacing == EnumPipeFacing.SOUTH)
        return AxisAlignedBB.func_72330_a((x + 0.25F), (y + 0.25F), (z + 0.25F), (x + 1.0F), (y + 0.75F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.UP)
        return AxisAlignedBB.func_72330_a((x + 0.25F), (y + 0.25F), (z + 0.2F), (x + 1.0F), (y + 1.0F), (z + 0.8F)); 
      if (secondFacing == EnumPipeFacing.DOWN)
        return AxisAlignedBB.func_72330_a((x + 0.25F), (y + 0.0F), (z + 0.2F), (x + 1.0F), (y + 0.75F), (z + 0.8F)); 
    } else if (firstFacing == EnumPipeFacing.UP) {
      if (secondFacing == EnumPipeFacing.NORTH)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.0F), (z + 0.0F), (x + 0.8F), (y + 0.75F), (z + 0.75F)); 
      if (secondFacing == EnumPipeFacing.SOUTH)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.0F), (z + 0.25F), (x + 0.8F), (y + 0.75F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.EAST)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.0F), (z + 0.2F), (x + 1.0F), (y + 0.75F), (z + 0.8F)); 
      if (secondFacing == EnumPipeFacing.WEST)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.2F), (x + 0.8F), (y + 0.75F), (z + 0.8F)); 
    } else if (firstFacing == EnumPipeFacing.DOWN) {
      if (secondFacing == EnumPipeFacing.NORTH)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.0F), (x + 0.8F), (y + 1.0F), (z + 0.75F)); 
      if (secondFacing == EnumPipeFacing.SOUTH)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.25F), (x + 0.8F), (y + 1.0F), (z + 1.0F)); 
      if (secondFacing == EnumPipeFacing.EAST)
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.2F), (x + 1.0F), (y + 1.0F), (z + 0.8F)); 
      if (secondFacing == EnumPipeFacing.WEST)
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.2F), (x + 0.8F), (y + 1.0F), (z + 0.8F)); 
    } 
    return createBox(x, y, z, firstFacing);
  }
  
  private AxisAlignedBB createBox(int x, int y, int z, EnumPipeFacing facing) {
    switch (facing) {
      case NORTH:
      case SOUTH:
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.25F), (z + 0.0F), (x + 0.8F), (y + 0.75F), (z + 1.0F));
      case EAST:
      case WEST:
        return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.25F), (z + 0.2F), (x + 1.0F), (y + 0.75F), (z + 0.8F));
      case UP:
      case DOWN:
        return AxisAlignedBB.func_72330_a((x + 0.2F), (y + 0.0F), (z + 0.2F), (x + 0.8F), (y + 1.0F), (z + 0.8F));
    } 
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1));
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
    super.func_149689_a(world, x, y, z, entity, itemStack);
    if (entity instanceof EntityPlayer) {
      ATileEntityPipe pipe = getPipe(world, x, y, z);
      if (pipe == null)
        return; 
      UUID uuid = entity.func_110124_au();
      Integer side = PipePlaceListener.consume(uuid);
      EnumPipeFacing facing = getPlacementFacing(entity, x, y, z, side);
      PipeUtils.setFacing((EntityPlayer)entity, world, x, y, z, facing);
    } 
  }
  
  public EnumPipeFacing getDirectionFromYaw(float yaw) {
    float angle = (yaw % 360.0F + 360.0F) % 360.0F;
    if (angle >= 45.0F && angle < 135.0F)
      return EnumPipeFacing.EAST; 
    if (angle >= 135.0F && angle < 225.0F)
      return EnumPipeFacing.SOUTH; 
    if (angle >= 225.0F && angle < 315.0F)
      return EnumPipeFacing.WEST; 
    return EnumPipeFacing.NORTH;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    ATileEntityPipe pipe = getPipe(world, x, y, z);
    if (pipe == null)
      return false; 
    PipeUtils.tryRotate((EntityPlayerMP)player, world, x, y, z);
    if (pipe.hasError())
      pipe.getError().sendMessage((ICommandSender)player); 
    return false;
  }
  
  public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z) {
    ATileEntityPipe pipe = (blockAccess.func_147438_o(x, y, z) instanceof ATileEntityPipe) ? (ATileEntityPipe)blockAccess.func_147438_o(x, y, z) : null;
    if (pipe == null || pipe.getClientAngles() == null) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      return;
    } 
    EnumPipeFacing[] facings = pipe.getClientAngles();
    AxisAlignedBB box = getBoundingBox(x, y, z, facings);
    if (box != null)
      func_149676_a((float)(box.field_72340_a - x), (float)(box.field_72338_b - y), (float)(box.field_72339_c - z), (float)(box.field_72336_d - x), (float)(box.field_72337_e - y), (float)(box.field_72334_f - z)); 
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
  }
  
  private EnumPipeFacing getPlacementFacing(EntityLivingBase entity, int x, int y, int z, Integer side) {
    if (entity == null)
      return EnumPipeFacing.NORTH; 
    if (side != null) {
      EnumPipeFacing facingFromBlock = getFacingFromSide(side.intValue());
      if (facingFromBlock != null)
        return facingFromBlock; 
    } 
    float pitch = entity.field_70125_A;
    if (pitch < -45.0F)
      return EnumPipeFacing.UP; 
    if (pitch > 45.0F)
      return EnumPipeFacing.DOWN; 
    float yaw = (entity.field_70177_z % 360.0F + 360.0F) % 360.0F;
    if (yaw >= 45.0F && yaw < 135.0F)
      return EnumPipeFacing.EAST; 
    if (yaw >= 135.0F && yaw < 225.0F)
      return EnumPipeFacing.SOUTH; 
    if (yaw >= 225.0F && yaw < 315.0F)
      return EnumPipeFacing.WEST; 
    return EnumPipeFacing.NORTH;
  }
  
  private EnumPipeFacing getFacingFromSide(int side) {
    switch (side) {
      case 0:
        return EnumPipeFacing.UP;
      case 1:
        return EnumPipeFacing.DOWN;
      case 2:
        return EnumPipeFacing.SOUTH;
      case 3:
        return EnumPipeFacing.NORTH;
      case 4:
        return EnumPipeFacing.EAST;
      case 5:
        return EnumPipeFacing.WEST;
    } 
    return EnumPipeFacing.NORTH;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\block\pipe\impl\ABlockPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */