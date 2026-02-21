package fr.paladium.palamod.modules.smeltery.items.weapons;

import com.google.common.collect.Multimap;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemPaladiumBroadsword extends ItemBroadsword implements ITooltipWiki {
  public ItemPaladiumBroadsword() {
    super(9.0F, 3000);
    func_77655_b("broadsword.paladium");
    func_111206_d("palamod:broadsword/paladium");
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    int[] modifiers = UpgradeHelper.getUpgradeAmmount(stack);
    if (modifiers == null)
      return; 
    int filled = stack.field_77990_d.func_74762_e("modifiersammount");
    int max = stack.field_77990_d.func_74762_e("modifiersmax") + UpgradeHelper.getModifier(stack, 7) * 2;
    String head = " ";
    if (filled == max) {
      for (int i : modifiers) {
        if (i != 7)
          head = head + UpgradeHelper.getUpgradeColor(i) + "⚫ "; 
      } 
    } else {
      if (modifiers.length != 0)
        for (int j : modifiers) {
          if (j != 7)
            head = head + UpgradeHelper.getUpgradeColor(j) + "⚫ "; 
        }  
      for (int i = 0; i < max - filled; i++)
        head = head + "§r⚪ "; 
    } 
    list.add(head);
    ArrayList<Integer> Ilist = new ArrayList<>();
    for (int i : modifiers) {
      if (i != 7 && !Ilist.contains(Integer.valueOf(i))) {
        int lvl = UpgradeHelper.getModifier(stack, i);
        String lvlDisplay = "";
        if (lvl > 1)
          lvlDisplay = " " + lvl; 
        list.add(UpgradeHelper.getUpgradeName(i) + lvlDisplay);
        Ilist.add(Integer.valueOf(i));
      } 
    } 
  }
  
  public Multimap func_111205_h() {
    Multimap multimap = super.func_111205_h();
    multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", this.damageBase, 0));
    return multimap;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#3.-les-armes-en-paladium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\weapons\ItemPaladiumBroadsword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */