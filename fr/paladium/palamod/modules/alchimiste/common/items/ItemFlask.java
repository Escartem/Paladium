package fr.paladium.palamod.modules.alchimiste.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.ItemAlchemist;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemFlask extends ItemAlchemist implements ITooltipWiki {
  public String name;
  
  private IIcon[] field_150920_d;
  
  public ItemFlask() {
    this.name = "flask";
    func_77655_b(this.name);
    func_111206_d("palamod:alchimiste/" + this.name);
    func_77625_d(16);
    func_77627_a(true);
  }
  
  public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> list) {
    for (int i = 0; i < 7; i++)
      list.add(new ItemStack(p_150895_1_, 1, i)); 
  }
  
  public int func_77647_b(int p_77647_1_) {
    return p_77647_1_;
  }
  
  public IIcon func_77617_a(int meta) {
    return this.field_150920_d[meta];
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    int factor = stack.func_77960_j();
    list.add(EnumChatFormatting.YELLOW + "Niveau de sève: " + EnumChatFormatting.GOLD + (int)(factor / 6.0D * 100.0D) + "%");
    list.add(EnumChatFormatting.YELLOW + "Sève: " + EnumChatFormatting.GOLD + ((stack
        .func_77942_o() && stack.func_77978_p().func_74764_b("seveType")) ? stack
        .func_77978_p().func_74779_i("seveType") : "Aucune"));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister p_94581_1_) {
    this.field_150920_d = new IIcon[7];
    for (int i = 0; i < 7; i++)
      this.field_150920_d[i] = p_94581_1_.func_94245_a(func_111208_A() + i); 
  }
  
  public String func_77667_c(ItemStack p_77667_1_) {
    int i = MathHelper.func_76125_a(p_77667_1_.func_77960_j(), 0, 15);
    return func_77658_a() + i;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#2.-les-objets-divers";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\items\ItemFlask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */