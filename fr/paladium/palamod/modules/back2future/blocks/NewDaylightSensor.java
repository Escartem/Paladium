package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class NewDaylightSensor extends BlockDaylightDetector implements IConfigurable {
  public NewDaylightSensor() {
    func_149711_c(0.2F);
    func_149647_a(null);
    func_149672_a(field_149766_f);
    func_149658_d("daylight_detector");
    func_149663_c(Utils.getUnlocalisedName("daylight_sensor"));
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return Item.func_150898_a((Block)Blocks.field_150453_bW);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z) {
    return Item.func_150898_a((Block)Blocks.field_150453_bW);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
      world.func_147449_b(x, y, z, ModBlocks.inverted_daylight_detector); 
    return true;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableInvertedDaylightSensor;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\NewDaylightSensor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */