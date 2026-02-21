package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseMentalis;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Mentalis implements Spell {
  public void perform(final EntityPlayerMP player, final int tier) {
    final String uuid = FastUUID.toString((Entity)player);
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    float yaw = player.field_70177_z;
    float pitch = player.field_70125_A;
    if (ServerManager.getMentalis().containsKey(player.func_110124_au()))
      return; 
    final EntityGhost ghost = new EntityGhost(player.func_130014_f_(), player);
    ghost.func_94058_c(player.func_70005_c_());
    ghost.func_70012_b(x, y, z, yaw, pitch);
    ghost.func_70606_j((tier == 1) ? 1.0F : ((tier == 2) ? 5.0F : 20.0F));
    player.func_130014_f_().func_72838_d((Entity)ghost);
    ServerManager.addMentalis(player.func_110124_au(), ghost);
    ServerManager.addActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid);
    PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid, true), player);
    ServerManager.addFreeze(player, x, y, z);
    (new Thread(new Runnable() {
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
        })).start();
  }
  
  public int getId() {
    return 3;
  }
  
  public String getName() {
    return "Mentalis";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 5;
  }
  
  public int getCooldown() {
    return 10;
  }
  
  public int getLevel() {
    return 70;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de quitter son corp et de contrôler une “âme” de soi en noclip. Cette âme peut être visible par les autres joueurs et tuée (modèle de fantôme). Notamment pratique pour voir si il y a du stuff dans les bc. Pas utilisable dans les claims WG et en dehors de la map.    Niveau 1: Transportation 10s Ame 1 pt de vie", "Permet de quitter son corp et de contrôler une “âme” de soi en noclip. Cette âme peut être visible par les autres joueurs et tuée (modèle de fantôme). Notamment pratique pour voir si il y a du stuff dans les bc. Pas utilisable dans les claims WG et en dehors de la map.    Niveau 2: Transportation 20s Ame 5 pt de vie", "Permet de quitter son corp et de contrôler une “âme” de soi en noclip. Cette âme peut être visible par les autres joueurs et tuée (modèle de fantôme). Notamment pratique pour voir si il y a du stuff dans les bc. Pas utilisable dans les claims WG et en dehors de la map.    Niveau 3: Transportation: 30s Ame 20pt de vie" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Mentalis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */