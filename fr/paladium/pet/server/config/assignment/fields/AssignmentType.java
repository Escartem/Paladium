package fr.paladium.pet.server.config.assignment.fields;

public enum AssignmentType {
  WATER, ANGELIC_WATER, LIGHT, DARK, ITEM, WALK, SLEEP, CONNECTION, DAILY_JOB, DAILY_PALAPASS;
  
  public boolean isTimedType() {
    return (this == LIGHT || this == DARK || this == SLEEP || this == CONNECTION || this == WATER || this == ANGELIC_WATER);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\assignment\fields\AssignmentType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */