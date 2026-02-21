package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Observatus implements Spell {
  public void perform(final EntityPlayerMP player, int tier) {
    final String uuid = FastUUID.toString((Entity)player);
    ServerManager.addActiveSpell(Spells.OBSERVATUS.getSpell().getId(), uuid);
    PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.OBSERVATUS.getSpell().getId(), uuid, true), player);
    (new Thread(new Runnable() {
          public void run() {
            try {
              PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming(3, Observatus.this.getId()), player);
              Thread.sleep(3000L);
              ServerManager.removeActiveSpell(Spells.OBSERVATUS.getSpell().getId(), uuid);
              PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.OBSERVATUS.getSpell().getId(), uuid, false), player);
            } catch (Exception exception) {}
          }
        })).start();
  }
  
  public int getId() {
    return 8;
  }
  
  public String getName() {
    return "Observatus";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 2;
  }
  
  public int getCooldown() {
    return 10;
  }
  
  public int getLevel() {
    return 10;
  }
  
  public String[] getDescription() {
    return new String[] { "Une fois le sort activé, effet de wallhack dans un rayon de X blocs pendant 3 secondes.    Niveau 1: X = 16 blocs", "Une fois le sort activé, effet de wallhack dans un rayon de X blocs pendant 3 secondes.    Niveau 2: X = 32 blocs", "Une fois le sort activé, effet de wallhack dans un rayon de X blocs pendant 3 secondes.    Niveau 3: X =  toute la render distance" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Observatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */