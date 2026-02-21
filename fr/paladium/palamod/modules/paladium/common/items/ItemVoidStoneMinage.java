package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemVoidStoneMinage extends BaseItem implements ITooltipWiki {
  private List<ItemStack> filter = new ArrayList<>();
  
  public ItemVoidStoneMinage(String texture) {
    super(texture);
    func_77625_d(1);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    super.func_77624_a(stack, player, list, b);
    if (!GuiScreen.func_146272_n()) {
      list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("tooltip.show"));
    } else {
      list.add(EnumChatFormatting.GREEN + 
          StatCollector.func_74838_a(func_77658_a() + ".details.1"));
      list.add(EnumChatFormatting.RED + 
          StatCollector.func_74838_a(func_77658_a() + ".details.2"));
    } 
    list.add(EnumChatFormatting.AQUA + "Contient " + EnumChatFormatting.GOLD + getStone(stack) + " pierres");
  }
  
  public void addFilter(Block block) {
    this.filter.add(new ItemStack(block));
  }
  
  public void addFilter(Block block, int meta) {
    this.filter.add(new ItemStack(block, 1, meta));
  }
  
  public void check(TickEvent.PlayerTickEvent event, ItemStack voidstone, ItemStack itemstack, int slot, EntityPlayer player) {
    if (!JobsBridge.canUseCraft(player, new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE)))
      return; 
    for (ItemStack s : this.filter) {
      if (s.func_77969_a(itemstack) && s.func_77960_j() == itemstack.func_77960_j()) {
        if (itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150347_e) || itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150348_b))
          setStone(voidstone, getStone(voidstone) + itemstack.field_77994_a); 
        UseItemAchievement.performCheck(player, voidstone, "VOID_STONE_MINAGE", itemstack.field_77994_a);
        player.field_71071_by.field_70462_a[slot] = null;
      } 
    } 
  }
  
  public void setStone(ItemStack item, int stone) {
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    compound.func_74768_a("stone", stone);
    item.func_77982_d(compound);
  }
  
  public int getStone(ItemStack item) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("stone"))
      return item.func_77978_p().func_74762_e("stone"); 
    return 0;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemVoidStoneMinage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */