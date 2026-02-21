package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseAphone;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Aphone implements Spell {
  public void perform(final EntityPlayerMP player, final int tier) {
    final String uuid = FastUUID.toString((Entity)player);
    final double x = player.field_70165_t;
    final double y = player.field_70163_u;
    final double z = player.field_70161_v;
    EventUtils.spawnParticle(player.field_70170_p, "spell", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 100, 0.10000000149011612D);
    PSpellsV2.network.sendToAll((IMessage)new PacketClientUseAphone(x, y, z, tier, true));
    PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.APHONE.getSpell().getId(), uuid, true));
    PlayerUtils.sendActionBar(player, "§aLe sort aphone a été activé");
    (new Thread(new Runnable() {
          public void run() {
            try {
              PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming(300, Aphone.this.getId()), player);
              Thread.sleep(300000L);
              PSpellsV2.network.sendToAll((IMessage)new PacketClientUseAphone(x, y, z, tier, false));
              PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.APHONE.getSpell().getId(), uuid, false));
            } catch (Exception exception) {}
          }
        })).start();
  }
  
  public int getId() {
    return 9;
  }
  
  public String getName() {
    return "Aphone";
  }
  
  public int getMaxTiers() {
    return 2;
  }
  
  public int getCost() {
    return 2;
  }
  
  public int getCooldown() {
    return 60;
  }
  
  public int getLevel() {
    return 10;
  }
  
  public String[] getDescription() {
    return new String[] { "Une fois activé, tous les bruits générés par le joueurs sont muté (cassage de bloc, etc…) Niveau 2, ce sont tous les sons dans un rayon de 10 blocs du joueurs qui sont mutés. Ce sort à effet pendant 5 minutes", "Une fois activé, tous les bruits générés par le joueurs sont muté (cassage de bloc, etc…) Niveau 2, ce sont tous les sons dans un rayon de 10 blocs du joueurs qui sont mutés. Ce sort à effet pendant 5 minutes" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Aphone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */