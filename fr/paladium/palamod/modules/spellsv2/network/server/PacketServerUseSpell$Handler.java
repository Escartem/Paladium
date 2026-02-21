package fr.paladium.palamod.modules.spellsv2.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.achievements.types.SpellActionAchievement;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.config.ConfigManager;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSetDelay;
import fr.paladium.palamod.modules.spellsv2.utils.SpellManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palapass.common.quest.palamod.SpellUseQuest;
import fr.paladium.worldguardflagplus.WorldGuardFlagPlus;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class Handler implements IMessageHandler<PacketServerUseSpell, IMessage> {
  public IMessage onMessage(PacketServerUseSpell message, MessageContext ctx) {
    long date = TimeUtil.now();
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    Spells spell = Spells.values()[message.spell];
    try {
      Set<?> blockedSpells = WorldGuardUtils.getStates(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, WorldGuardFlagPlus.BLOCKED_SPELL);
      if (blockedSpells != null)
        for (Object blockedSpell : blockedSpells) {
          String blockedSpellStr = blockedSpell.toString();
          if ("ALL".equalsIgnoreCase(blockedSpellStr) || blockedSpell
            .toString().equalsIgnoreCase(spell.toString())) {
            player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCe sort est désactivé dans cette région."));
            return null;
          } 
        }  
    } catch (NoClassDefFoundError noClassDefFoundError) {}
    if (!SpellManager.hasSpell((EntityPlayer)player, spell))
      return null; 
    if (ConfigManager.hasKey("server", "lock." + message.spell) && 
      ConfigManager.getBoolean("server", "lock." + message.spell))
      return null; 
    if (Spells.INERTIUM.equals(spell)) {
      if (!player.field_70122_E) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez être sur le sol pour utiliser ce sort."));
        return null;
      } 
      if (!player.func_130014_f_().func_147437_c((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v)) {
        PlayerUtils.sendActionBar(player, "Vous ne pouvez remplacer un block");
        return null;
      } 
      if (!EventUtils.canInteract((EntityPlayer)player, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v)) {
        PlayerUtils.sendActionBar(player, "Vous ne pouvez pas poser un block ici");
        return null;
      } 
    } 
    if (Spells.BATUM.equals(spell)) {
      int d = 0;
      double x = player.field_70165_t;
      double y = player.field_70163_u;
      double z = player.field_70161_v;
      Block block = Blocks.field_150350_a;
      while (d < 10 && block == Blocks.field_150350_a) {
        double ox = (-MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F));
        double oz = (MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F));
        double oy = -MathHelper.func_76126_a(player.field_70125_A / 180.0F * 3.1415927F);
        block = player.field_70170_p.func_147439_a((int)(x + ox * d), (int)(y + oy * d), (int)(z + oz * d));
        if (block != Blocks.field_150350_a) {
          x += ox * d;
          y = y + oy * d + 1.0D;
          z += oz * d;
        } 
        d++;
      } 
      boolean canSpawn = true;
      for (int i = -2; i < 2; i++) {
        for (int j = -2; j < 2; j++) {
          if (!player.field_70170_p.func_147437_c((int)x + i, (int)y, (int)z + j))
            canSpawn = false; 
        } 
      } 
      if (!canSpawn)
        return null; 
    } 
    int tier = SpellManager.getTier((EntityPlayer)player, spell);
    Map<Integer, Long> playerMap = SpellManager.getSpellDelay((EntityPlayer)player);
    if (playerMap.containsKey(Integer.valueOf(message.spell))) {
      ZonedDateTime lastUse = TimeUtil.fromLong(((Long)playerMap.get(Integer.valueOf(message.spell))).longValue());
      if (lastUse.plusMinutes(spell.getSpell().getCooldown()).isBefore(TimeUtil.nowZoned()) && 
        spell.getSpell().getLevel() <= SpellManager.getLevel((EntityPlayer)player)) {
        SpellUseQuest.trigger((EntityPlayer)player, 1);
        SpellActionAchievement.performCheck((EntityPlayer)player, 2);
        spell.getSpell().perform(player, tier);
        SpellManager.addSpellDelay((EntityPlayer)player, message.spell, Long.valueOf(date));
        PSpellsV2.network.sendTo((IMessage)new PacketClientSetDelay(message.spell, date), player);
      } 
    } else if (spell.getSpell().getLevel() <= SpellManager.getLevel((EntityPlayer)player)) {
      spell.getSpell().perform(player, tier);
      SpellManager.addSpellDelay((EntityPlayer)player, message.spell, Long.valueOf(date));
      PSpellsV2.network.sendTo((IMessage)new PacketClientSetDelay(message.spell, date), player);
      SpellUseQuest.trigger((EntityPlayer)player, 1);
      SpellActionAchievement.performCheck((EntityPlayer)player, 2);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\server\PacketServerUseSpell$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */