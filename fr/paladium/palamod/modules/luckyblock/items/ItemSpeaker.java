package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.TimeUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpeaker extends Item implements ITooltipWiki {
  public ItemSpeaker() {
    this.field_77777_bU = 1;
    func_77656_e(25);
    func_77655_b("speaker");
    func_111206_d("palamod:speaker");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    return item;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
    list.add("§eAvec l'item en main:");
    list.add("§aVotre prochain message est diffusé sur tous les serveurs.");
    if (!stack.func_77942_o() || !stack.func_77978_p().func_74764_b("LAST_USE")) {
      list.add("§aEnvoyez un message avec l'item en main pour le diffuser");
    } else {
      long lastUse = stack.func_77978_p().func_74763_f("LAST_USE");
      long diff = (System.currentTimeMillis() - lastUse) / 1000L;
      if (diff < 3600L) {
        String formattedTime = TimeUtils.getFullTime("§b", "§e", 3600L - diff);
        list.add("§aVous devez attendre " + formattedTime + " §aavant de pouvoir diffuser un nouveau message");
      } else {
        list.add("§aEnvoyez un message avec l'item en main pour le diffuser");
      } 
    } 
    list.add("§7§m---------------------------.");
    list.add("§aDurabilité: " + (func_77612_l() - getDamage(stack)) + "/25");
    super.func_77624_a(stack, player, list, advanced);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.1.-luckystats-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemSpeaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */