package fr.paladium.palamod.modules.smeltery.items.weapons;

import com.google.common.collect.Multimap;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAmethystFastsword extends ItemFastsword implements ITooltipWiki {
  public ItemAmethystFastsword() {
    super(6.0F, 400);
    func_77655_b("fastsword.amethyst");
    func_111206_d("palamod:fastsword/amethyst");
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\weapons\ItemAmethystFastsword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */