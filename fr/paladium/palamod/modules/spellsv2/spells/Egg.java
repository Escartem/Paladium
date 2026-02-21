package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Egg implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    final String uuid = FastUUID.toString((Entity)player);
    ServerManager.addActiveSpell(getId(), uuid);
    PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming(30, getId()), player);
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(30000L);
              ServerManager.removeActiveSpell(Egg.this.getId(), uuid);
            } catch (Exception exception) {}
          }
        })).start();
  }
  
  public int getId() {
    return 13;
  }
  
  public String getName() {
    return "Oeuforie";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 3;
  }
  
  public int getCooldown() {
    return 5;
  }
  
  public int getLevel() {
    return 45;
  }
  
  public String[] getDescription() {
    return new String[] { "Une fois que le sort est activé, il est actif pendant 30 secondes. Si le joueur descend à 1 coeur ou moins, il se transformera en oeuf et ne pourra plus bouger (plus rien faire du tout). Le joueur reprend sa forme normale au bout de 1 minute ou si l’oeuf n’a plus de vie. Les nombres de points de vie de l’oeuf est de 50", "Une fois que le sort est activé, il est actif pendant 30 secondes. Si le joueur descend à 1 coeur ou moins, il se transformera en oeuf et ne pourra plus bouger (plus rien faire du tout). Le joueur reprend sa forme normale au bout de 1 minute ou si l’oeuf n’a plus de vie. Les nombres de points de vie de l’oeuf est de 100", "Une fois que le sort est activé, il est actif pendant 30 secondes. Si le joueur descend à 1 coeur ou moins, il se transformera en oeuf et ne pourra plus bouger (plus rien faire du tout). Le joueur reprend sa forme normale au bout de 1 minute ou si l’oeuf n’a plus de vie. Les nombres de points de vie de l’oeuf est de 200" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Egg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */