package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming(3, Observatus.this.getId()), player);
      Thread.sleep(3000L);
      ServerManager.removeActiveSpell(Spells.OBSERVATUS.getSpell().getId(), uuid);
      PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.OBSERVATUS.getSpell().getId(), uuid, false), player);
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Observatus$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */