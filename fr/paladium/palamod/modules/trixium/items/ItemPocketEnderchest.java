package fr.paladium.palamod.modules.trixium.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.bukkit.Bukkit;

public class ItemPocketEnderchest extends Item implements ITooltipWiki {
  public ItemPocketEnderchest() {
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b("pocket_enderchest");
    func_111206_d("palamod:pocket_enderchest");
  }
  
  @SideOnly(Side.SERVER)
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return super.func_77659_a(item, world, player); 
    try {
      if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(player.func_110124_au()))) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet item n'est pas utilisable en combat."));
        return super.func_77659_a(item, world, player);
      } 
    } catch (NoClassDefFoundError|Exception e) {
      System.out.println("[Paladium][Error] Unable to check combat");
    } 
    player.func_71007_a((IInventory)player.func_71005_bN());
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/concours-trixium/objets-exclusifs";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\items\ItemPocketEnderchest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */