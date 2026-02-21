package fr.paladium.palavoicechat.server.api.pojo;

import java.util.UUID;

public class PlayerPunishment {
  public UUID target;
  
  public UUID author;
  
  public String startTime;
  
  public String endTime;
  
  public boolean permanent;
  
  public boolean active;
  
  public boolean hidden;
  
  public PunishmentType punishmentType;
  
  public UUID forgivenBy;
  
  public boolean customReason;
  
  public String reason;
  
  public String forgiveReason;
  
  public String messageReportId;
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\api\pojo\PlayerPunishment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */