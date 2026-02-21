package fr.paladium.palamod.modules.hunter.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAmulet extends Item implements ITooltipWiki {
  private Type type;
  
  public Type getType() {
    return this.type;
  }
  
  public ItemAmulet(Type type) {
    this.type = type;
    func_77625_d(1);
    func_77655_b(type.toString().toLowerCase() + "_amulet");
    func_111206_d("palamod:" + type.toString().toLowerCase() + "_amulet");
    if (this.type == Type.SUMMER) {
      func_77637_a(PLuckyBlock.TAB);
      return;
    } 
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer entity, List<String> lores, boolean flag) {
    lores.add("§eCette amulette se combine");
    lores.add("avec un plastron en §6paladium §7ou §1endium");
    lores.add(" ");
    lores.add("§8> §7" + ((ItemAmulet)item.func_77973_b()).type.description);
    super.func_77624_a(item, entity, lores, flag);
  }
  
  public enum Type {
    HUNTER("Fait apparaître une créature liée à l’un des 4 métiers (XP récupérable à partir du niveau 35 du métier lié au boss)."),
    VOYANCE("Permet de cacher le pseudo du joueur qui la porte à travers les murs."),
    DISCRETION("§cCette amulette est désactivée."),
    COMBAT("§cCette amulette est désactivée."),
    MAGMA("Permet d'obtenir un effet de résistance au feu constant."),
    DAEMON("Joue un son démoniaque lors d'un kill."),
    SUMMER("Enflamme les joueurs à proximité.");
    
    private final String description;
    
    Type(String description) {
      this.description = description;
    }
  }
  
  public String getUrl(ItemStack arg0) {
    if (this.type == Type.DAEMON)
      return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.-lucky-block-halloween"; 
    if (this.type == Type.HUNTER || this.type == Type.MAGMA)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter"; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemAmulet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */