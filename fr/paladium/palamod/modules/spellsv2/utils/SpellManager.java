package fr.paladium.palamod.modules.spellsv2.utils;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.achievements.types.SpellActionAchievement;
import fr.paladium.palamod.modules.spellsv2.network.PlayerSpellsProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class SpellManager {
  public static void setPoints(EntityPlayer player, double points) {
    PlayerSpellsProperties.get(player).setSpellPoints(points);
  }
  
  public static double getPoints(EntityPlayer player) {
    return PlayerSpellsProperties.get(player).getSpellPoints();
  }
  
  public static boolean isUnlock(EntityPlayer player, Spells spell) {
    for (SpellObj spObj : PlayerSpellsProperties.get(player).getSpells()) {
      if (spObj.getSpell().getId() == spell.getSpell().getId())
        return spObj.isUnlock(); 
    } 
    return false;
  }
  
  public static void setUnlock(EntityPlayer player, Spells spell, boolean unlock) {
    List<SpellObj> spells = new ArrayList<>();
    for (Spells sp : Spells.values()) {
      SpellObj spellObj;
      if (hasSpell(player, sp)) {
        spellObj = getSpell(player, sp);
      } else {
        spellObj = new SpellObj(sp.getSpell(), 0, false);
      } 
      if (spellObj.getSpell().getId() == spell.getSpell().getId())
        spellObj.setUnlock(unlock); 
      spells.add(spellObj);
    } 
    SpellActionAchievement.performCheck(player, 1);
    PlayerSpellsProperties.get(player).setSpells(spells);
  }
  
  public static int getTier(EntityPlayer player, Spells spell) {
    if (hasSpell(player, spell))
      return getSpell(player, spell).getTier(); 
    return 0;
  }
  
  public static void setTier(EntityPlayer player, Spells spell, int tier) {
    List<SpellObj> spells = new ArrayList<>();
    for (Spells sp : Spells.values()) {
      SpellObj spellObj;
      if (hasSpell(player, sp)) {
        spellObj = getSpell(player, sp);
      } else {
        spellObj = new SpellObj(sp.getSpell(), 0, false);
      } 
      if (spellObj.getSpell().getId() == spell.getSpell().getId()) {
        spellObj.setUnlock(true);
        spellObj.setTier(tier);
      } 
      spells.add(spellObj);
    } 
    PlayerSpellsProperties.get(player).setSpells(spells);
  }
  
  public static SpellObj getSpell(EntityPlayer player, Spells spell) {
    for (SpellObj sp : getSpells(player)) {
      if (sp.getSpell().getId() == spell.getSpell().getId())
        return sp; 
    } 
    return null;
  }
  
  public static List<SpellObj> getSpells(EntityPlayer player) {
    return PlayerSpellsProperties.get(player).getSpells();
  }
  
  public static boolean hasLogin(EntityPlayer player) {
    return PlayerSpellsProperties.get(player).isHasLogin();
  }
  
  public static void setHasLogin(EntityPlayer player, boolean hasLogin) {
    PlayerSpellsProperties.get(player).setHasLogin(hasLogin);
  }
  
  public static boolean hasSpell(EntityPlayer player, Spells spell) {
    for (SpellObj s : PlayerSpellsProperties.get(player).getSpells()) {
      if (s.getSpell().getId() == spell.getSpell().getId())
        return true; 
    } 
    return false;
  }
  
  public static int getLevel(EntityPlayer player) {
    int level = 0;
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    for (JobType type : JobType.values())
      level += jobsPlayer.getLevel(type); 
    return level;
  }
  
  public static Map<Integer, Long> getSpellDelay(EntityPlayer player) {
    return PlayerSpellsProperties.get(player).getDelays();
  }
  
  public static void addSpellDelay(EntityPlayer player, int spell, Long time) {
    PlayerSpellsProperties.get(player).getDelays().put(Integer.valueOf(spell), time);
  }
  
  public static void removeSpellDelay(EntityPlayer player, int spell) {
    PlayerSpellsProperties.get(player).getDelays().remove(Integer.valueOf(spell));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\SpellManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */