package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming((tier == 1) ? 10 : ((tier == 2) ? 30 : 60), Inertium.this
            .getId()), player);
      Thread.sleep((tier == 1) ? 10000L : ((tier == 2) ? 30000L : 60000L));
      PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM
            .getSpell().getId(), uuid, false));
      player.func_130014_f_().func_147468_f(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(0)).intValue(), (
          (Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(1)).intValue(), (
          (Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(2)).intValue());
      EventUtils.spawnParticle(player.field_70170_p, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 100, 0.10000000149011612D);
      ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid);
      ServerManager.removeFreeze(player);
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Inertium$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */