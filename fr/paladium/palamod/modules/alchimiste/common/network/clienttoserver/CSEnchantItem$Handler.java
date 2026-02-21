package fr.paladium.palamod.modules.alchimiste.common.network.clienttoserver;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.alchimiste.common.container.ContainerEnchantment;
import fr.paladium.palamod.modules.alchimiste.common.enchant.EnchantmentBase;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.alchimiste.common.network.servertoclient.SCSyncExperience;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGCraftCache;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGCraftData;
import fr.paladium.palarpg.module.equipment.common.playerdata.RPGCraftPlayerData;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Handler implements IMessageHandler<CSEnchantItem, IMessage> {
  public IMessage onMessage(CSEnchantItem message, MessageContext ctx) {
    int i = message.index;
    if (i > EnchantMod.getEnchants().size())
      i = 0; 
    Enchantment ef = Enchantment.field_77331_b[message.index];
    if (!(ef instanceof EnchantmentBase))
      return null; 
    EnchantmentBase enchant = (EnchantmentBase)ef;
    if (!enchant.isEnabled())
      return null; 
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    JobsPlayer jobs = JobsPlayer.get((Entity)entityPlayerMP);
    int pFarmerLevel = jobs.getLevel(JobType.FARMER);
    int pMinerLevel = jobs.getLevel(JobType.MINER);
    int pHunterLevel = jobs.getLevel(JobType.HUNTER);
    int pAlchemistLevel = jobs.getLevel(JobType.ALCHEMIST);
    if (((EntityPlayer)entityPlayerMP).field_71068_ca < 30) {
      entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez avoir au moins 30 niveaux d'expérience pour enchanter un objet"));
      return null;
    } 
    if (pFarmerLevel < enchant.farmerLevel || pMinerLevel < enchant.mineurLevel || pHunterLevel < enchant.hunterLevel || pAlchemistLevel < enchant.alchemistLevel) {
      entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous n'avez pas le niveau requis pour cet enchantement"));
      if (enchant.farmerLevel > 0)
        entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Farmer " + enchant.farmerLevel + " §8[" + ((pFarmerLevel >= enchant.farmerLevel) ? "§a✔" : "§c✘") + "§8]")); 
      if (enchant.mineurLevel > 0)
        entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Miner " + enchant.mineurLevel + " §8[" + ((pMinerLevel >= enchant.mineurLevel) ? "§a✔" : "§c✘") + "§8]")); 
      if (enchant.hunterLevel > 0)
        entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Hunter " + enchant.hunterLevel + " §8[" + ((pHunterLevel >= enchant.hunterLevel) ? "§a✔" : "§c✘") + "§8]")); 
      if (enchant.alchemistLevel > 0)
        entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Alchemist " + enchant.alchemistLevel + " §8[" + ((pAlchemistLevel >= enchant.alchemistLevel) ? "§a✔" : "§c✘") + "§8]")); 
      return null;
    } 
    ItemStack itemStack = new ItemStack((Item)Items.field_151134_bR);
    Items.field_151134_bR.func_92115_a(itemStack, new EnchantmentData((Enchantment)enchant, 1));
    RPGCraftData craftData = RPGCraftCache.getRPGCraftData(itemStack);
    if (craftData != null) {
      if (craftData.getRequiredLevel() > 0) {
        RPGProfilePlayerData playerProfileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entityPlayerMP, "profile");
        if (playerProfileData != null && playerProfileData.getLevel() < craftData.getRequiredLevel()) {
          (new Notification(Notification.NotificationType.ERROR, "Vous devez être niveau " + craftData.getRequiredLevel() + " RPG pour crafter cet item", "RPG")).send(entityPlayerMP);
          return null;
        } 
      } 
      if (craftData.isRequiredParchment()) {
        RPGCraftPlayerData playerCraftData = (RPGCraftPlayerData)RPGPlayer.getData((Entity)entityPlayerMP, "craft");
        if (playerCraftData != null && !playerCraftData.isCraftUnlocked(itemStack)) {
          (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas débloqué ce craft avec un parchemin de craft", "RPG")).send(entityPlayerMP);
          return null;
        } 
      } 
    } 
    if (((EntityPlayer)entityPlayerMP).field_71070_bA instanceof ContainerEnchantment) {
      ContainerEnchantment con = (ContainerEnchantment)((EntityPlayer)entityPlayerMP).field_71070_bA;
      if (con.func_75139_a(0) != null && con.func_75139_a(0).func_75211_c() != null) {
        ItemStack stack = con.func_75139_a(0).func_75211_c().func_77946_l();
        if (!enchant.func_92089_a(stack)) {
          entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet enchantement ne peut pas être appliqué sur cet objet !"));
          return null;
        } 
        Map<Integer, Integer> enchants = EnchantmentHelper.func_82781_a(stack);
        if (enchants.get(Integer.valueOf(enchant.field_77352_x)) == null) {
          enchants.put(Integer.valueOf(enchant.field_77352_x), Integer.valueOf(1));
          EnchantmentHelper.func_82782_a(enchants, stack);
          (con.func_75139_a(0)).field_75224_c.func_70299_a(0, stack);
          ((EntityPlayer)entityPlayerMP).field_71068_ca -= 30;
          return (IMessage)new SCSyncExperience(((EntityPlayer)entityPlayerMP).field_71106_cc, ((EntityPlayer)entityPlayerMP).field_71068_ca, ((EntityPlayer)entityPlayerMP).field_71067_cb);
        } 
        if (enchants.containsKey(Integer.valueOf(enchant.field_77352_x))) {
          int level = ((Integer)enchants.get(Integer.valueOf(enchant.field_77352_x))).intValue();
          if (enchant.func_77325_b() <= level) {
            entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCet enchantement est déjà au niveau maximum"));
            return (IMessage)new SCSyncExperience(((EntityPlayer)entityPlayerMP).field_71106_cc, ((EntityPlayer)entityPlayerMP).field_71068_ca, ((EntityPlayer)entityPlayerMP).field_71067_cb);
          } 
          level++;
          enchants.remove(Integer.valueOf(enchant.field_77352_x));
          enchants.put(Integer.valueOf(enchant.field_77352_x), Integer.valueOf(level));
        } 
        EnchantmentHelper.func_82782_a(enchants, stack);
        (con.func_75139_a(0)).field_75224_c.func_70299_a(0, stack);
        ((EntityPlayer)entityPlayerMP).field_71068_ca -= 30;
        return (IMessage)new SCSyncExperience(((EntityPlayer)entityPlayerMP).field_71106_cc, ((EntityPlayer)entityPlayerMP).field_71068_ca, ((EntityPlayer)entityPlayerMP).field_71067_cb);
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\network\clienttoserver\CSEnchantItem$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */