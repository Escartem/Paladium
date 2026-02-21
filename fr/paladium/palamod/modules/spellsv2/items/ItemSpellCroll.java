package fr.paladium.palamod.modules.spellsv2.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpdateClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.SpellManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemSpellCroll extends Item implements ITooltipWiki {
  public ItemSpellCroll() {
    func_77637_a(CreativeTabs.field_78027_g);
    func_77625_d(16);
    func_77655_b("spell_scroll");
    func_111206_d("palamod:spell_scroll");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      item.field_77994_a--;
      SpellManager.setPoints(player, SpellManager.getPoints(player) + 1.0D);
      if (player instanceof EntityPlayerMP) {
        int level = 0;
        level = SpellManager.getLevel(player);
        if (level < 0)
          level = 0; 
        PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(
              SpellManager.getPoints(player), level), (EntityPlayerMP)player);
      } 
      player.func_145747_a((IChatComponent)new ChatComponentText("§eVous avez gagné 1 point de sort."));
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\items\ItemSpellCroll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */