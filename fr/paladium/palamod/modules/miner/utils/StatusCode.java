package fr.paladium.palamod.modules.miner.utils;

public enum StatusCode {
  INVALID_REQUEST(401),
  SUCCESS(200),
  CONFLICT(409),
  NOT_FOUND(404),
  INTERNAL_ERROR(400),
  NOT_ENOUGH_RESOURCES(507),
  COOLDOWN(429);
  
  private final int code;
  
  StatusCode(int code) {
    this.code = code;
  }
  
  public int getCode() {
    return this.code;
  }
  
  public static StatusCode getStatus(int code) {
    for (StatusCode status : values()) {
      if (status.code == code)
        return status; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\utils\StatusCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */