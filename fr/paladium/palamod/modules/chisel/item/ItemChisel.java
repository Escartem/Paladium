package fr.paladium.palamod.modules.chisel.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.common.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChisel extends BaseItem {
  public ItemChisel(String texture) {
    super(texture);
    func_77625_d(1);
    func_77656_e(500);
    func_77637_a((CreativeTabs)Registry.CHISEL_TAB);
  }
  
  public boolean func_150894_a(ItemStack itemstack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
    if (p_150894_3_.func_149712_f(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
      itemstack.func_77972_a(2, p_150894_7_); 
    return true;
  }
  
  public boolean func_77644_a(ItemStack itemstack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
    itemstack.func_77972_a(1, p_77644_3_);
    return true;
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (!world.field_72995_K)
      entityplayer.openGui(PalaMod.instance, PGuiRegistry.GUI_CHISEL_ID, world, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v); 
    return itemstack;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77662_d() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\item\ItemChisel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */