package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.SpellObj;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import io.netty.buffer.ByteBuf;

public class PacketClientUpgradeSpell implements IMessage {
  public int id;
  
  public int tier = -1;
  
  public boolean unlock;
  
  public PacketClientUpgradeSpell() {}
  
  public PacketClientUpgradeSpell(int id) {
    this.id = id;
  }
  
  public PacketClientUpgradeSpell(int id, int tier, boolean unlock) {
    this.id = id;
    this.tier = tier;
    this.unlock = unlock;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readInt();
    this.tier = buf.readInt();
    this.unlock = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.id);
    buf.writeInt(this.tier);
    buf.writeBoolean(this.unlock);
  }
  
  public static class Handler implements IMessageHandler<PacketClientUpgradeSpell, IMessage> {
    public IMessage onMessage(PacketClientUpgradeSpell message, MessageContext ctx) {
      Spells spell = Spells.values()[message.id];
      SpellObj sp = null;
      for (SpellObj spellObj : ClientManager.getSpells()) {
        if (spellObj.getSpell().getId() == spell.getSpell().getId())
          sp = spellObj; 
      } 
      if (sp == null) {
        sp = new SpellObj(spell.getSpell(), 0, false);
        ClientManager.getSpells().add(sp);
      } 
      sp = ClientManager.getSpell(spell);
      if (sp != null)
        if (message.tier == -1) {
          if (!sp.isUnlock()) {
            sp.setUnlock(true);
            ClientManager.setCurrentSpell(sp.getSpell().getId());
          } else {
            sp.setTier(sp.getTier() + 1);
            if (sp.getTier() > sp.getSpell().getMaxTiers())
              sp.setTier(sp.getSpell().getMaxTiers()); 
          } 
        } else {
          sp.setUnlock(message.unlock);
          sp.setTier(message.tier);
        }  
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUpgradeSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */