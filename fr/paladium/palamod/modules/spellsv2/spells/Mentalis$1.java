package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseMentalis;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      String ghostUuid = FastUUID.toString((Entity)ghost);
      Thread.sleep(500L);
      PSpellsV2.network
        .sendTo((IMessage)new PacketClientUseMentalis(true, ghostUuid), player);
      PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming(10 * tier, Mentalis.this.getId()), player);
      Thread.sleep((10000 * tier));
      if (ghost.func_70089_S())
        ghost.func_70106_y(); 
      if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (
        (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())))
        .contains(player.func_110124_au())) {
        ServerManager.removeMentalis(player.func_110124_au());
        ServerManager.removeFreeze(player);
        PSpellsV2.network
          .sendTo((IMessage)new PacketClientActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid, false), player);
        ServerManager.removeActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid);
        PSpellsV2.network.sendTo((IMessage)new PacketClientUseMentalis(false, ghostUuid), player);
      } 
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Mentalis$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */