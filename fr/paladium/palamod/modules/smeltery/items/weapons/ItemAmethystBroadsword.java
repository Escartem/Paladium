package fr.paladium.palamod.modules.smeltery.items.weapons;

import com.google.common.collect.Multimap;
import fr.paladium.common.utils.ITooltipWiki;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAmethystBroadsword extends ItemBroadsword implements ITooltipWiki {
  public ItemAmethystBroadsword() {
    super(7.7F, 800);
    func_77655_b("broadsword.amethyst");
    func_111206_d("palamod:broadsword/amethyst");
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
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#1.-les-armes-en-amethyste";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\weapons\ItemAmethystBroadsword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */