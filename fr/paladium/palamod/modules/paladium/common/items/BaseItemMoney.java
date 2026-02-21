package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BaseItemMoney extends Item {
  int valeur;
  
  public BaseItemMoney(int value) {
    this.valeur = value;
    func_111206_d("customnpcs:npcMoney");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    super.func_77624_a(stack, player, list, b);
    list.add(EnumChatFormatting.GREEN + "Click droit pour obtenir " + this.valeur + "$");
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    System.out.println("[SEVERE][Duplication][Money] Player " + p_77659_3_.func_70005_c_() + " use deprecated money item (" + this.valeur + "$)");
    int size = 0;
    if ((size = ConsumeFullstack(this, p_77659_3_.field_71071_by)) != 0) {
      p_77659_3_.func_85030_a("random.orb", 0.1F, 0.5F * ((p_77659_2_.field_73012_v
          .nextFloat() - p_77659_2_.field_73012_v.nextFloat()) * 0.7F + 1.8F));
      try {
        addMoney(p_77659_3_, size);
      } catch (NoSuchMethodError noSuchMethodError) {}
    } 
    return p_77659_1_;
  }
  
  public int ConsumeFullstack(Item item, InventoryPlayer inventory) {
    int i = func_146029_c(item, inventory);
    if (i < 0)
      return 0; 
    int size = (inventory.field_70462_a[i]).field_77994_a;
    inventory.field_70462_a[i] = null;
    return size;
  }
  
  private int func_146029_c(Item p_146029_1_, InventoryPlayer inventory) {
    for (int i = 0; i < inventory.field_70462_a.length; i++) {
      if (inventory.field_70462_a[i] != null && inventory.field_70462_a[i]
        .func_77973_b() == p_146029_1_)
        return i; 
    } 
    return -1;
  }
  
  @SideOnly(Side.SERVER)
  public void addMoney(EntityPlayer p_77659_3_, int size) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\BaseItemMoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */