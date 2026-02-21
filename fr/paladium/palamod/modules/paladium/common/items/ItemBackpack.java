package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBackpack extends Item implements ITooltipWiki {
  public ItemBackpack() {
    func_77625_d(1);
    func_77655_b("backpack");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_111206_d("palamod:backpack");
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_BACKPACK, world, 0, 0, 0); 
    return stack;
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity e, int in, boolean b) {
    if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("Open") && 
      !b)
      stack.func_77978_p().func_74757_a("Open", false); 
    super.func_77663_a(stack, world, e, in, b);
  }
  
  public void func_77622_d(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
    itemStack.field_77990_d = new NBTTagCompound();
    itemStack.field_77990_d.func_74757_a("Enderchest", false);
    itemStack.field_77990_d.func_74778_a("Interdit", "Interdit dans les enderchest");
  }
  
  public void func_77624_a(ItemStack itemStack, EntityPlayer player, List<String> list, boolean par4) {
    if (itemStack.field_77990_d != null) {
      String owner = itemStack.field_77990_d.func_74779_i("Interdit");
      list.add(EnumChatFormatting.RED + owner);
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-stockages#7.-les-backpacks";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */