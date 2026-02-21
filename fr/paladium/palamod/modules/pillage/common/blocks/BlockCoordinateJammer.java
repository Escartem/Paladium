package fr.paladium.palamod.modules.pillage.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.pillage.common.blocks.tileentities.TECoordinateJammer;
import fr.paladium.palamod.modules.pillage.common.items.ItemBlockMultipleTypes;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCoordinateJammer extends BlockBase implements ITooltipWiki {
  Block frontBlock;
  
  BlockPos frontBlockPos;
  
  public BlockCoordinateJammer(String unlocalizedName) {
    super(Material.field_151576_e, unlocalizedName);
  }
  
  public void register() {
    GameRegistry.registerBlock(this, ItemBlockMultipleTypes.class, func_149739_a());
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> itemList) {
    itemList.add(new ItemStack(this));
  }
  
  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    EnumFacing frontFacing = EnumFacing.getFront(side).getOpposite();
    BlockPos blockPos = new BlockPos(x + frontFacing.getFrontOffsetX(), y + frontFacing.getFrontOffsetY(), z + frontFacing.getFrontOffsetZ());
    Block block = world.func_147439_a(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    this.frontBlock = !(block instanceof BlockCoordinateJammer) ? block : null;
    this.frontBlockPos = !(block instanceof BlockCoordinateJammer) ? blockPos : null;
    return super.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, meta);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
    super.func_149689_a(world, x, y, z, placer, stack);
    if (placer instanceof EntityPlayer) {
      TECoordinateJammer te = (TECoordinateJammer)world.func_147438_o(x, y, z);
      te.setOwner(((EntityPlayer)placer).func_146103_bH());
      te.setBlockToCopy(this.frontBlock, this.frontBlockPos);
      te.randCoordX(25000, -25000);
      te.randCoordZ(25000, -25000);
      while ((te.getFakeX() + x < 1000 && te.getFakeX() + x > 25000) || (te
        .getFakeX() + x > -1000 && te.getFakeX() + x < -25000))
        te.randCoordX(25000, -25000); 
      while ((te.getFakeZ() + z < 1000 && te.getFakeZ() + z > 25000) || (te
        .getFakeZ() + z > -1000 && te.getFakeZ() + z < -25000))
        te.randCoordZ(25000, -25000); 
    } 
  }
  
  public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (!(te instanceof TECoordinateJammer))
      return super.getLightOpacity(world, x, y, z); 
    TECoordinateJammer coordinateJammer = (TECoordinateJammer)world.func_147438_o(x, y, z);
    return (coordinateJammer.getBlockToCopy() != null) ? coordinateJammer.getBlockToCopy().getLightOpacity(world, x, y, z) : super
      .getLightOpacity(world, x, y, z);
  }
  
  public int func_149720_d(IBlockAccess world, int x, int y, int z) {
    if (world.func_147438_o(x, y, z) instanceof TECoordinateJammer) {
      TileEntity te = world.func_147438_o(x, y, z);
      if (!(te instanceof TECoordinateJammer))
        return super.func_149720_d(world, x, y, z); 
      TECoordinateJammer coordinateJammer = (TECoordinateJammer)world.func_147438_o(x, y, z);
      try {
        return (coordinateJammer.getBlockToCopy() != null) ? coordinateJammer.getBlockToCopy().func_149720_d(world, x, y, z) : super
          .func_149720_d(world, x, y, z);
      } catch (Exception e) {
        return super.func_149720_d(world, x, y, z);
      } 
    } 
    return super.func_149720_d(world, x, y, z);
  }
  
  public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (!(te instanceof TECoordinateJammer))
      return super.func_149673_e(world, x, y, z, side); 
    TECoordinateJammer coordinateJammer = (TECoordinateJammer)world.func_147438_o(x, y, z);
    if (coordinateJammer.getBlockPosToCopy() == null)
      return super.func_149673_e(world, x, y, z, side); 
    return (coordinateJammer.getBlockToCopy() != null) ? coordinateJammer
      .getBlockToCopy().func_149673_e(world, coordinateJammer.getBlockPosToCopy().getX(), coordinateJammer
        .getBlockPosToCopy().getY(), coordinateJammer.getBlockPosToCopy().getZ(), side) : super
      .func_149673_e(world, x, y, z, side);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TECoordinateJammer();
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public enum EnumFacing {
    DOWN(0, 1, 0, -1, 0),
    UP(1, 0, 0, 1, 0),
    NORTH(2, 3, 0, 0, -1),
    SOUTH(3, 2, 0, 0, 1),
    EAST(4, 5, -1, 0, 0),
    WEST(5, 4, 1, 0, 0);
    
    private final int index;
    
    private final int opposite;
    
    private final int frontOffsetX;
    
    private final int frontOffsetY;
    
    private final int frontOffsetZ;
    
    private static final EnumFacing[] faceList = new EnumFacing[6];
    
    static {
      for (EnumFacing facing : values())
        faceList[facing.index] = facing; 
    }
    
    EnumFacing(int index, int opposite, int dirX, int dirY, int dirZ) {
      this.index = index;
      this.opposite = opposite;
      this.frontOffsetX = dirX;
      this.frontOffsetY = dirY;
      this.frontOffsetZ = dirZ;
    }
    
    public int getFrontOffsetX() {
      return this.frontOffsetX;
    }
    
    public int getFrontOffsetY() {
      return this.frontOffsetY;
    }
    
    public int getFrontOffsetZ() {
      return this.frontOffsetZ;
    }
    
    public static EnumFacing getFront(int side) {
      return faceList[Math.abs(side % faceList.length)];
    }
    
    public EnumFacing getOpposite() {
      return getFront(this.opposite);
    }
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#9.-home-remover-et-coordinate-jammer";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\BlockCoordinateJammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */