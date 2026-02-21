package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBeacon;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class NewBeacon extends BlockBeacon implements IConfigurable {
  public NewBeacon() {
    func_149715_a(1.0F);
    func_149647_a(null);
    func_149658_d("beacon");
    func_149663_c(Utils.getUnlocalisedName("beacon"));
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return Item.func_150898_a((Block)Blocks.field_150461_bJ);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z) {
    return Item.func_150898_a((Block)Blocks.field_150461_bJ);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityNewBeacon();
  }
  
  public boolean isEnabled() {
    return Back2Future.enableColourfulBeacons;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\NewBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */