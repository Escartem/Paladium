package fr.paladium.palaautomation.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.client.ClientProxy;
import fr.paladium.palaautomation.common.tab.AutomationTab;
import fr.paladium.palaautomation.common.tile.impl.TileEntityInjector;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInjector extends Block implements ITileEntityProvider {
  public static final String NAME = "injector";
  
  public BlockInjector() {
    super(Material.field_76233_E);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)AutomationTab.getInstance());
    func_149663_c("injector");
    func_149658_d("palaautomation:injector");
    func_149711_c(1.5F);
    func_149752_b(10.0F);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityInjector();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderInjectorId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\block\BlockInjector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */