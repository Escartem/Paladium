package fr.paladium.palamod.modules.spellsv2.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpdateClientManager;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpgradeSpell;
import fr.paladium.palamod.modules.spellsv2.utils.SpellManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketServerUpgradeSpell implements IMessage {
  public int id;
  
  public PacketServerUpgradeSpell() {}
  
  public PacketServerUpgradeSpell(int id) {
    this.id = id;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.id);
  }
  
  public static class Handler implements IMessageHandler<PacketServerUpgradeSpell, IMessage> {
    public IMessage onMessage(PacketServerUpgradeSpell message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      double points = SpellManager.getPoints((EntityPlayer)player);
      Spells spell = Spells.values()[message.id];
      if (points >= spell.getSpell().getCost()) {
        if (!SpellManager.hasSpell((EntityPlayer)player, spell)) {
          SpellManager.setUnlock((EntityPlayer)player, spell, true);
          SpellManager.setTier((EntityPlayer)player, spell, 1);
        } else if (!SpellManager.isUnlock((EntityPlayer)player, spell)) {
          SpellManager.setUnlock((EntityPlayer)player, spell, true);
          SpellManager.setTier((EntityPlayer)player, spell, 1);
        } else {
          int tier = SpellManager.getTier((EntityPlayer)player, spell);
          SpellManager.setTier((EntityPlayer)player, spell, 
              (tier + 1 > spell.getSpell().getMaxTiers()) ? spell.getSpell().getMaxTiers() : (tier + 1));
        } 
        SpellManager.setPoints((EntityPlayer)player, points - spell.getSpell().getCost());
        points = SpellManager.getPoints((EntityPlayer)player);
        int level = 0;
        level = SpellManager.getLevel((EntityPlayer)player);
        if (level < 0)
          level = 0; 
        PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(points, level), player);
        PSpellsV2.network.sendTo((IMessage)new PacketClientUpgradeSpell(message.id, 
              SpellManager.getTier((EntityPlayer)player, spell), true), player);
        return null;
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\server\PacketServerUpgradeSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */