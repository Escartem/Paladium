package fr.paladium.palamod.modules.paladium.common.blocks.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockObsidianDoor extends BlockDoor implements ITooltipWiki {
  private String unlocalizedName = "obsidianBlockDoor";
  
  public BlockObsidianDoor() {
    super(Material.field_151576_e);
    func_149663_c(this.unlocalizedName);
    func_149658_d("palamod:decorative/" + this.unlocalizedName);
    func_149752_b(100.0F);
    func_149711_c(50.0F);
    setHarvestLevel("pickaxe", 2);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return ((p_149650_1_ & 0x8) != 0) ? null : (Item)ItemsRegister.OBSIDIAN_ITEM_DOOR;
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return (Item)ItemsRegister.OBSIDIAN_ITEM_DOOR;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-la-construction#1.-les-objets-a-base-dobsidienne";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\decorative\BlockObsidianDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */