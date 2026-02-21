package fr.paladium.palamod.modules.paladium.common.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

public class ItemPaladiumGreenSword extends BaseItemSword {
  private IIcon elucidatorIcon;
  
  public ItemPaladiumGreenSword(Item.ToolMaterial material, String texture, Item item_repair) {
    super(material, texture, item_repair);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iiconRegister) {
    this.field_77791_bV = iiconRegister.func_94245_a(func_111208_A());
    this.elucidatorIcon = iiconRegister.func_94245_a("palamod:elucidator");
  }
  
  public IIcon func_77650_f(ItemStack itemStack) {
    String name = itemStack.func_82833_r().toLowerCase().trim();
    return name.equals("chaika9 elucidator") ? this.elucidatorIcon : super.func_77650_f(itemStack);
  }
  
  public IIcon getIcon(ItemStack stack, int pass) {
    return func_77650_f(stack);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    super.func_77624_a(stack, player, list, b);
    String name = stack.func_82833_r().toLowerCase().trim();
    if (name.equals("chaika9 elucidator")) {
      list.add(EnumChatFormatting.RED + "Elucidator");
      list.add(EnumChatFormatting.DARK_GRAY + "C'est l'une des meilleures \"Magic Sword\"");
      list.add(EnumChatFormatting.DARK_GRAY + "qu'on puisse avoir en drop.");
      if (stack.func_77948_v())
        list.add(""); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\weapons\ItemPaladiumGreenSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */