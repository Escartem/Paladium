package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUsePerception;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Perception implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    int offset = (tier == 1) ? 12 : ((tier == 2) ? 24 : 32);
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(x - offset, 0.0D, z - offset, x + offset, 256.0D, z + offset);
    PSpellsV2.network.sendTo((IMessage)new PacketClientUsePerception(tier), player);
    List players = player.func_130014_f_().func_72872_a(EntityPlayerMP.class, scanAbove);
    for (Object obj : players) {
      EntityPlayerMP pl = (EntityPlayerMP)obj;
      if (!pl.equals(player))
        pl.func_145747_a((IChatComponent)new ChatComponentText("§4Attention ! §cVous avez été repéré par un joueur dans les alentours.")); 
    } 
    EventUtils.spawnParticle(player.field_70170_p, "spell", (int)player.field_70165_t, ((int)player.field_70163_u + 1), (int)player.field_70161_v, 250, 0.05000000074505806D);
  }
  
  public int getId() {
    return 6;
  }
  
  public String getName() {
    return "Perception";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 1;
  }
  
  public int getCooldown() {
    return 60;
  }
  
  public int getLevel() {
    return 60;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet d’afficher le nombre de joueurs aux alentours dans un rayon de X blocs de la couche 0 a 256 (utile en cas de pillage furtif). Cependant les joueurs repérés par le sors sont avertis. Ce sort n’est pas utilisable ni en safezone ni en warzone.    Niveau 1: Rayon de 12 blocs", "Permet d’afficher le nombre de joueurs aux alentours dans un rayon de X blocs de la couche 0 a 256 (utile en cas de pillage furtif). Cependant les joueurs repérés par le sors sont avertis. Ce sort n’est pas utilisable ni en safezone ni en warzone.    Niveau 2: Rayon de 24 blocs", "Permet d’afficher le nombre de joueurs aux alentours dans un rayon de X blocs de la couche 0 a 256 (utile en cas de pillage furtif). Cependant les joueurs repérés par le sors sont avertis. Ce sort n’est pas utilisable ni en safezone ni en warzone.    Niveau 3: Rayon de 32 blocs" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Perception.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */