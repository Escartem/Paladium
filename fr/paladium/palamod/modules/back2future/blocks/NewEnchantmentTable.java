package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class NewEnchantmentTable extends BlockEnchantmentTable implements IConfigurable {
  public NewEnchantmentTable() {
    func_149711_c(5.0F);
    func_149647_a(null);
    func_149752_b(2000.0F);
    func_149658_d("enchanting_table");
    func_149663_c(Utils.getUnlocalisedName("enchantment_table"));
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return Item.func_150898_a(Blocks.field_150381_bn);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z) {
    return Item.func_150898_a(Blocks.field_150381_bn);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    player.openGui(PalaMod.instance, -1, world, x, y, z);
    return true;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableEnchants;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\NewEnchantmentTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */