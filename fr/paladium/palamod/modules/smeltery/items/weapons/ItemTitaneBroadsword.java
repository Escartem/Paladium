package fr.paladium.palamod.modules.smeltery.items.weapons;

import com.google.common.collect.Multimap;
import fr.paladium.common.utils.ITooltipWiki;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemTitaneBroadsword extends ItemBroadsword implements ITooltipWiki {
  public ItemTitaneBroadsword() {
    super(8.2F, 20);
    func_77655_b("broadsword.titane");
    func_111206_d("palamod:broadsword/titane");
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    list.add("§cDamages: " + getDamages(stack));
  }
  
  public Multimap func_111205_h() {
    Multimap multimap = super.func_111205_h();
    multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", this.damageBase, 0));
    return multimap;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#2.-les-armes-en-titane";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\weapons\ItemTitaneBroadsword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */