package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemTalismanCalm extends Item implements ITooltipWiki {
  public ItemTalismanCalm() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77655_b("talisman_calm");
    func_111206_d("palamod:talisman_calm");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (world.field_72995_K) {
      ClientProxy.calm = !ClientProxy.calm;
      player.func_145747_a((IChatComponent)new ChatComponentText("§eVotre Talisman de Calme est " + (ClientProxy.calm ? "§aactivé" : "§cdésactivé") + "."));
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemTalismanCalm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */