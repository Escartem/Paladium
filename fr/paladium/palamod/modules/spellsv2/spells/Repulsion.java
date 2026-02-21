package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;

public class Repulsion implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    int offset = 1 + tier;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(x - offset, y - offset, z - offset, x + offset, y + offset, z + offset);
    EventUtils.spawnParticle(player.field_70170_p, "cloud", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 150, 0.5D);
    EventUtils.spawnParticle(player.field_70170_p, "smoke", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 50, 0.10000000149011612D);
    List players = player.func_130014_f_().func_72872_a(EntityLivingBase.class, scanAbove);
    for (Object obj : players) {
      if (obj instanceof EntityLivingBase && !(obj instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase)) {
        EntityLivingBase pl = (EntityLivingBase)obj;
        pl.field_70160_al = true;
        float str = 0.1F + tier * 0.3F;
        pl.field_70159_w += (pl.field_70165_t - player.field_70165_t > 0.0D) ? str : -str;
        pl.field_70181_x += str;
        pl.field_70179_y += (pl.field_70161_v - player.field_70161_v > 0.0D) ? str : -str;
        if (obj instanceof EntityPlayerMP && 
          (EntityPlayerMP)pl != player) {
          EntityPlayerMP p = (EntityPlayerMP)pl;
          p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)pl));
        } 
      } 
    } 
  }
  
  public int getId() {
    return 4;
  }
  
  public String getName() {
    return "Repulsion";
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
    return 20;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de repousser tous les joueurs alentours. A noter que ce sort n’est pas utilisable en safe zone.    Niveau 1: Repousse dans un rayon de 2 blocs 3 blocs plus loins", "Permet de repousser tous les joueurs alentours. A noter que ce sort n’est pas utilisable en safe zone.    Niveau 2: Repousse dans un rayon de 3 blocs 4 blocs plus loins", "Permet de repousser tous les joueurs alentours. A noter que ce sort n’est pas utilisable en safe zone.    Niveau 3: Repousse dans un rayon de 4 blocs 5 blocs plus loins" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Repulsion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */