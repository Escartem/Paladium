package fr.paladium.palamod.modules.trixium.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;

public class ItemPlayerSaddle extends Item implements ITooltipWiki {
  public ItemPlayerSaddle() {
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b("player_saddle");
    func_111206_d("palamod:player_saddle");
  }
  
  @SideOnly(Side.SERVER)
  public boolean func_111207_a(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
    if (player.field_70170_p.field_72995_K)
      return false; 
    if (PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
      (new Notification(Notification.NotificationType.WARNING, "L'item " + func_77653_i(item) + " est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send((EntityPlayerMP)player);
      return false;
    } 
    if (!(entity instanceof EntityPlayer)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet item n'est utilisable que sur un joueur."));
      return false;
    } 
    EntityPlayer target = (EntityPlayer)entity;
    if (player.field_70153_n != null)
      player.field_70153_n.func_70078_a(null); 
    if (target.field_70154_o != null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet item n'est pas utilisable sur une personne entrain de chevaucher une entité."));
      return false;
    } 
    if (target.func_110124_au().equals(player.func_110124_au())) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet item n'est pas utilisable sur vous même."));
      return false;
    } 
    try {
      if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(player.func_110124_au()))) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet item n'est pas utilisable en combat."));
        return false;
      } 
      if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(target.func_110124_au()))) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet item n'est pas utilisable sur quelqu'un en combat."));
        return false;
      } 
    } catch (NoClassDefFoundError|Exception e) {
      System.out.println("[Paladium][Error] Unable to check combat");
    } 
    player.func_70078_a((Entity)target);
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/concours-trixium/objets-exclusifs";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\items\ItemPlayerSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */