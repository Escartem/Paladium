package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.utils.IItem;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class ItemPlate extends Item implements IItem {
  private String name;
  
  public ItemPlate(String name) {
    this.name = name;
    func_77655_b(name);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77625_d(1);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister) {
    this.field_77791_bV = par1IconRegister.func_94245_a("palamod:" + this.name);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer plater, List<String> list, boolean whatsthis) {
    String book = StatCollector.func_74838_a("plate.notValid");
    NBTTagCompound bookTag = stack.func_77978_p();
    if (bookTag != null) {
      book = bookTag.func_74779_i("bookName");
    } else {
      NBTTagCompound newbooktag = new NBTTagCompound();
      newbooktag.func_74778_a("bookName", book);
      stack.func_77982_d(newbooktag);
    } 
    list.add(book);
  }
  
  public String getName() {
    return this.name;
  }
  
  public Item getObject() {
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemPlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */