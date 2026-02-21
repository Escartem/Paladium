package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityRandomStatue;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRandomStatue extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "random_statue";
  
  public BlockRandomStatue() {
    super(Material.field_151573_f);
    func_149663_c("random_statue");
    func_149658_d("palamod:random_statue");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityRandomStatue))
      return true; 
    Block block = generateStatue(tile);
    tile.func_145831_w().func_147449_b(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, block);
    tile.func_145831_w().func_147444_c(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, block);
    return true;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityRandomStatue))
      return; 
    Block block = generateStatue(tile);
    ItemStack stack = new ItemStack(block);
    ItemUtils.spawnItemInWorld(world, x, y, z, stack);
  }
  
  private Block generateStatue(TileEntity tile) {
    Block block = null;
    int random = (new Random()).nextInt(3);
    if (random == 0) {
      block = BlocksRegister.FEVE_DANCAROK;
    } else if (random == 1) {
      block = BlocksRegister.FEVE_RAVIROK;
    } else if (random == 2) {
      block = BlocksRegister.FEVE_TEDAROK;
    } 
    return block;
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityRandomStatue();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderRandomStatue;
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Une étrange statue...");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\block\BlockRandomStatue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */