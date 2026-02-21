package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseAphone;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming(300, Aphone.this.getId()), player);
      Thread.sleep(300000L);
      PSpellsV2.network.sendToAll((IMessage)new PacketClientUseAphone(x, y, z, tier, false));
      PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.APHONE.getSpell().getId(), uuid, false));
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Aphone$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */