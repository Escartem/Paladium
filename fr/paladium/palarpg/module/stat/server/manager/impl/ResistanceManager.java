package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ResistanceManager {
  public float getResistance(EntityLivingBase entity) {
    return getResistance(entity, RPGElementType.NEUTRAL);
  }
  
  public float getResistance(EntityLivingBase entity, RPGElementType elementType) {
    RPGStatPlayerData entityStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (entityStats == null)
      return 0.0F; 
    float resistance = ((Number)entityStats.getResistance().getValue()).floatValue();
    if (elementType != RPGElementType.NEUTRAL)
      resistance += getRPGTypeResistance(elementType, entityStats); 
    return resistance;
  }
  
  public float getRPGTypeResistance(RPGElementType elementType, RPGStatPlayerData entityStats) {
    switch (elementType) {
      case TALIKUS:
        return ((Number)entityStats.getTalikusResistance().getValue()).floatValue();
      case MANELIOS:
        return ((Number)entityStats.getManeliosResistance().getValue()).floatValue();
      case VITALYS:
        return ((Number)entityStats.getVitalysResistance().getValue()).floatValue();
      case NIMBRIA:
        return ((Number)entityStats.getNimbriaResistance().getValue()).floatValue();
      case FRIGORIA:
        return ((Number)entityStats.getFrigoriaResistance().getValue()).floatValue();
      case NEUTRAL:
        return ((Number)entityStats.getResistance().getValue()).floatValue();
    } 
    return 0.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\ResistanceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */