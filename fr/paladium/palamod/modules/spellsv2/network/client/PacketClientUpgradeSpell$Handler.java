package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.SpellObj;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;

public class Handler implements IMessageHandler<PacketClientUpgradeSpell, IMessage> {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUpgradeSpell$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */