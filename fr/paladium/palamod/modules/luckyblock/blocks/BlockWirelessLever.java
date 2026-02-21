package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityWirelessLever;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWirelessLever extends Block implements ITooltipWiki {
  public BlockWirelessLever() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("wireless_lever");
    func_149658_d("palamod:wireless_lever");
    func_149711_c(5.0F);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    world.func_147464_a(x, y, z, this, 2);
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityWirelessLever();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    TileEntityWirelessLever te = (TileEntityWirelessLever)world.func_147438_o(x, y, z);
    if (te != null && !world.field_72995_K && 
      item.func_77942_o() && 
      item.func_77978_p().func_74764_b("x")) {
      te.setX(item.func_77978_p().func_74762_e("x"));
      te.setY(item.func_77978_p().func_74762_e("y"));
      te.setZ(item.func_77978_p().func_74762_e("z"));
    } 
    super.func_149689_a(world, x, y, z, entity, item);
  }
  
  public static Field t(Class clazz) throws NoSuchFieldException {
    return clazz.getDeclaredField(EventUtils._ab);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random) {
    TileEntityWirelessLever te = (TileEntityWirelessLever)world.func_147438_o(x, y, z);
    if (te != null && !world.field_72995_K) {
      te.setActive(world.func_72864_z(x, y, z));
      world.func_147464_a(te.getX(), te.getY(), te.getZ(), this, 2);
      world.func_147459_d(te.getX(), te.getY(), te.getZ(), world
          .func_147439_a(te.getX(), te.getY(), te.getZ()));
    } 
    world.func_147464_a(x, y, z, this, 2);
    super.func_149674_a(world, x, y, z, random);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockWirelessLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */