package fr.paladium.palamod.modules.paladium.common.blocks.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseBlockSlabHardened extends BlockSlab {
  private Block block;
  
  public BaseBlockSlabHardened(Block thisblock, Material material, String unlocalizedName, String texture, float resistance, float hardness, String toolClass, int levelTool) {
    super(false, material);
    this.block = thisblock;
    func_149647_a((CreativeTabs)Registry.DECORATIVE_TAB);
    func_149663_c(unlocalizedName);
    func_149658_d(texture);
    func_149752_b(100.0F);
    func_149711_c(-1.0F);
    setHarvestLevel(toolClass, levelTool);
  }
  
  public String func_150002_b(int p_150002_1_) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a(this.block);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    if (world.field_72995_K)
      return false; 
    Block b = world.func_147439_a(x, y, z);
    if (player != null && b != null && player.func_71045_bC() != null) {
      ItemStack is = player.func_71045_bC();
      if (is.func_77973_b().equals(ItemsRegister.MAGICAL_TOOL)) {
        EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Item.func_150898_a(b)));
        if (PFactions.instance.getImpl().hasPermission(player, "BUILD_MAGICAL_TOOL", x, z) != Tristate.FALSE) {
          world.func_72838_d((Entity)item);
          BlockUtils.setBlockToAir(world, x, y, z);
        } 
        return true;
      } 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\decorative\BaseBlockSlabHardened.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */