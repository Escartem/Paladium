package fr.paladium.palamod.modules.trixium.api;

import fr.paladium.factions.api.entity.IEmblemEntity;
import fr.paladium.lib.apollon.utils.Color;

public class TrixiumFactionProfile {
  public String factionUUID;
  
  public long amountTrixium;
  
  public int rank;
  
  public String factionName;
  
  public boolean exists;
  
  public Color backgroundColor;
  
  public Color foregroundColor;
  
  public Color borderColor;
  
  public Color iconColor;
  
  public Color iconBorderColor;
  
  public int backgroundId;
  
  public int foregroundId;
  
  public int iconId;
  
  public void setEmblem(IEmblemEntity emblem) {
    this.backgroundColor = emblem.getBackgroundColor();
    this.foregroundColor = emblem.getForegroundColor();
    this.borderColor = emblem.getBorderColor();
    this.iconColor = emblem.getIconColor();
    this.iconBorderColor = emblem.getIconBorderColor();
    this.backgroundId = emblem.getBackgroundId();
    this.foregroundId = emblem.getForegroundId();
    this.iconId = emblem.getIconId();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\api\TrixiumFactionProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */