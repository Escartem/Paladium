package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColoredLamp extends BlockBase implements ITooltipWiki {
  public BlockColoredLamp() {
    super(Material.field_151576_e);
    func_149715_a(0.0F);
    func_149663_c("colored_lamp");
    func_149658_d("palamod:colored_lamp");
    func_149711_c(0.3F);
    func_149672_a(field_149778_k);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, this, 2);
  }
  
  public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
    return true;
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random) {
    if (world.field_72995_K)
      return; 
    int metadata = world.func_72805_g(x, y, z);
    if (world.func_72864_z(x, y, z)) {
      metadata++;
      if (metadata > 14)
        metadata = 0; 
    } else {
      metadata = 0;
    } 
    func_149715_a((metadata > 0) ? 1.0F : 0.0F);
    world.func_72921_c(x, y, z, metadata, 1);
    world.func_147471_g(x, y, z);
    world.func_147464_a(x, y, z, this, 2);
    super.func_149674_a(world, x, y, z, random);
  }
  
  public int func_149741_i(int p_149741_1_) {
    return func_149635_D();
  }
  
  public int func_149635_D() {
    return super.func_149635_D();
  }
  
  public int func_149720_d(IBlockAccess iblockaccess, int x, int y, int z) {
    int metadata = iblockaccess.func_72805_g(x, y, z);
    switch (metadata) {
      case 0:
        return (new Color(255, 255, 255)).getRGB();
      case 1:
        return (new Color(0, 255, 0)).getRGB();
      case 2:
        return (new Color(0, 0, 255)).getRGB();
      case 3:
        return (new Color(255, 255, 0)).getRGB();
      case 4:
        return (new Color(255, 0, 255)).getRGB();
      case 5:
        return (new Color(0, 255, 255)).getRGB();
      case 6:
        return (new Color(255, 255, 255)).getRGB();
      case 7:
        return (new Color(255, 125, 0)).getRGB();
      case 8:
        return (new Color(255, 125, 125)).getRGB();
      case 9:
        return (new Color(125, 255, 125)).getRGB();
      case 10:
        return (new Color(0, 255, 125)).getRGB();
      case 11:
        return (new Color(0, 125, 255)).getRGB();
      case 12:
        return (new Color(125, 125, 255)).getRGB();
      case 13:
        return (new Color(0, 125, 255)).getRGB();
      case 14:
        return (new Color(255, 0, 125)).getRGB();
    } 
    return (new Color(255, 255, 255)).getRGB();
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(BlocksRegister.COLORED_LAMP);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a(BlocksRegister.COLORED_LAMP);
  }
  
  protected ItemStack func_149644_j(int p_149644_1_) {
    return new ItemStack(BlocksRegister.COLORED_LAMP);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockColoredLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */