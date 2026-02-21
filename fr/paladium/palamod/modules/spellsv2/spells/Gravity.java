package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.spellsv2.entity.GravityProjectile;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class Gravity implements Spell {
  public void perform(EntityPlayerMP playerEntity, int tier) {
    playerEntity.field_70170_p
      .func_72838_d((Entity)new GravityProjectile(playerEntity.field_70170_p, (EntityLivingBase)playerEntity, 5.0F, tier));
  }
  
  public int getId() {
    return 10;
  }
  
  public String getName() {
    return "Gravity";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 5;
  }
  
  public int getCooldown() {
    return 3;
  }
  
  public int getLevel() {
    return 25;
  }
  
  public String[] getDescription() {
    return new String[] { "Lance un projectile, si celui-ci touche un joueur, le joueur sera affecté par Gravity, tous les jump(stick) sont réduits de 25% et la chute en hanglider est augmentée de 30%", "Lance un projectile, si celui-ci touche un joueur, le joueur sera affecté par Gravity, tous les jump(stick) sont réduits de 35% et la chute en hanglider est augmentée de 35%", "Lance un projectile, si celui-ci touche un joueur, le joueur sera affecté par Gravity, tous les jump(stick) sont réduits de 50% et la chute en hanglider est augmentée de 45%" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Gravity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */