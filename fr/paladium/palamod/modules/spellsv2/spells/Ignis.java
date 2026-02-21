package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;

public class Ignis implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    int offset = 7;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(x - offset, y - offset, z - offset, x + offset, y + offset, z + offset);
    EventUtils.spawnParticle(player.field_70170_p, "flame", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 250, 0.5D);
    EventUtils.spawnParticle(player.field_70170_p, "lava", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 150, 0.30000001192092896D);
    EventUtils.spawnParticle(player.field_70170_p, "smoke", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 50, 0.10000000149011612D);
    List players = player.func_130014_f_().func_72872_a(EntityLivingBase.class, scanAbove);
    for (Object obj : players) {
      if (!(obj instanceof EntityLivingBase) || 
        obj == player)
        continue; 
      if (MonthlyUtils.hasInvulnerabilityEffect((EntityLivingBase)obj))
        continue; 
      ((EntityLivingBase)obj).func_70015_d(15);
    } 
  }
  
  public int getId() {
    return 11;
  }
  
  public String getName() {
    return "Ignis";
  }
  
  public int getMaxTiers() {
    return 1;
  }
  
  public int getCost() {
    return 4;
  }
  
  public int getCooldown() {
    return 15;
  }
  
  public int getLevel() {
    return 35;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de mettre le feu aux entités autour de vous dans un rayon de 7 blocs" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Ignis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */