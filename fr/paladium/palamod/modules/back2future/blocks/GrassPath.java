package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class GrassPath extends Block implements IConfigurable {
  @SideOnly(Side.CLIENT)
  private IIcon sideIcon;
  
  public GrassPath() {
    super(Material.field_151577_b);
    func_149711_c(0.6F);
    func_149713_g(255);
    setHarvestLevel("shovel", 0);
    this.field_149783_u = true;
    func_149672_a(field_149779_h);
    func_149658_d("grass_path");
    func_149663_c(Utils.getUnlocalisedName("grass_path"));
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
    func_149647_a(Back2Future.enableGrassPath ? Back2Future.creativeTab : null);
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1));
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return Blocks.field_150346_d.func_149650_a(meta, rand, fortune);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a())
      world.func_147449_b(x, y, z, Blocks.field_150346_d); 
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return (side == ForgeDirection.DOWN);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side == 0) ? Blocks.field_150346_d.func_149691_a(side, 0) : ((side == 1) ? this.field_149761_L : this.sideIcon);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_149761_L = reg.func_94245_a(func_149641_N() + "_top");
    this.sideIcon = reg.func_94245_a(func_149641_N() + "_side");
  }
  
  public boolean isEnabled() {
    return Back2Future.enableGrassPath;
  }
  
  public static void onPlayerInteract(PlayerInteractEvent event) {
    if (Back2Future.enableGrassPath && 
      event.entityPlayer != null) {
      World world = event.entityPlayer.field_70170_p;
      if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && 
        world.func_147439_a(event.x, event.y, event.z) == Blocks.field_150349_c) {
        ItemStack stack = event.entityPlayer.func_71045_bC();
        if (stack != null && stack.func_77973_b() instanceof net.minecraft.item.ItemSpade) {
          world.func_147449_b(event.x, event.y, event.z, ModBlocks.grass_path);
          event.entityPlayer.func_71038_i();
          stack.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
          world.func_72908_a((event.x + 0.5F), (event.y + 0.5F), (event.z + 0.5F), Block.field_149767_g
              .func_150498_e(), 1.0F, 0.8F);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\GrassPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */