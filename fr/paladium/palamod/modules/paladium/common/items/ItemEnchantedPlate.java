package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.utils.IItem;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

public class ItemEnchantedPlate extends Item implements IItem {
  private String name;
  
  public ItemEnchantedPlate(String name) {
    this.name = name;
    func_77655_b(name);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77625_d(1);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister) {
    this.field_77791_bV = par1IconRegister.func_94245_a("palamod:plate");
  }
  
  public boolean func_77636_d(ItemStack stack) {
    return true;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean whatsthis) {
    String nbt = StatCollector.func_74838_a("plate.notValid");
    NBTTagList taglist = getEnchantmentTagList(stack);
    int tagscount = 0;
    if (taglist != null)
      tagscount = taglist.func_74745_c(); 
    if (tagscount > 0) {
      for (int x = 0; x < taglist.func_74745_c(); x++) {
        short var7 = taglist.func_150305_b(x).func_74765_d("id");
        short var8 = taglist.func_150305_b(x).func_74765_d("lvl");
        if (Enchantment.field_77331_b[var7] != null) {
          String ench = Enchantment.field_77331_b[var7].func_77316_c(var8);
          list.add(ench);
        } 
      } 
    } else {
      list.add(nbt);
    } 
  }
  
  public NBTTagList getEnchantmentTagList(ItemStack stack) {
    return (stack.field_77990_d != null && stack.field_77990_d.func_74764_b("StoredEnchantments")) ? (NBTTagList)stack.field_77990_d
      .func_74781_a("StoredEnchantments") : new NBTTagList();
  }
  
  public String getName() {
    return this.name;
  }
  
  public Item getObject() {
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemEnchantedPlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */