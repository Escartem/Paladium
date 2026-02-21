package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityBunnyPlush;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockBunnyPlush extends Block implements ITileEntityProvider {
  private static final String NAME = "bunny_plush";
  
  public BlockBunnyPlush() {
    super(Material.field_151575_d);
    func_149663_c("bunny_plush");
    func_149658_d("palamod:bunny_plush");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityBunnyPlush))
      return; 
    ItemStack stack = new ItemStack(BlocksRegister.BUNNY_PLUSH);
    ItemUtils.spawnItemInWorld(world, x, y, z, stack);
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
    return (TileEntity)new TileEntityBunnyPlush();
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderBunnyPlush;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockBunnyPlush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */