package fr.paladium.palamod.modules.trixium.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.trixium.items.ItemCloud;
import fr.paladium.palamod.modules.trixium.tileentities.TileEntityCloud;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCloud extends Block implements ITooltipWiki {
  public BlockCloud() {
    super(Material.field_151580_n);
    func_149711_c(0.0F);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("cloud");
    func_149658_d("palamod:cloud");
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    super.func_149689_a(world, x, y, z, entity, item);
    if (world.field_72995_K)
      return; 
    if (!(item.func_77973_b() instanceof ItemCloud)) {
      world.func_147468_f(x, y, z);
      return;
    } 
    TileEntity te = world.func_147438_o(x, y, z);
    if (!(te instanceof TileEntityCloud)) {
      world.func_147468_f(x, y, z);
      return;
    } 
    ItemCloud itemCloud = (ItemCloud)item.func_77973_b();
    int duration = itemCloud.getDuration(item);
    ((TileEntityCloud)te).setDuration(duration * 20);
  }
  
  public int func_149745_a(Random random) {
    return 0;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityCloud();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/concours-trixium/objets-exclusifs";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\blocks\BlockCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */