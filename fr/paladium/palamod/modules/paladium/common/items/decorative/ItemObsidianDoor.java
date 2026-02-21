package fr.paladium.palamod.modules.paladium.common.items.decorative;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BlockObsidianDoor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemObsidianDoor extends ItemDoor {
  private String unlocalizedName = "obsidianItemDoor";
  
  public ItemObsidianDoor() {
    super(Material.field_151576_e);
    func_77625_d(1);
    func_77655_b(this.unlocalizedName);
    func_77637_a((CreativeTabs)Registry.DECORATIVE_TAB);
    func_111206_d("palamod:decorative/" + this.unlocalizedName);
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (p_77648_7_ != 1)
      return false; 
    p_77648_5_++;
    BlockObsidianDoor blockObsidianDoor = BlocksRegister.OBSIDIAN_BLOCK_DOOR;
    if (p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_
      .func_82247_a(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_)) {
      if (!blockObsidianDoor.func_149742_c(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
        return false; 
      int i1 = MathHelper.func_76128_c(((p_77648_2_.field_70177_z + 180.0F) * 4.0F / 360.0F) - 0.5D) & 0x3;
      func_150924_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1, (Block)blockObsidianDoor);
      p_77648_1_.field_77994_a--;
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\decorative\ItemObsidianDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */