package fr.paladium.palamod.modules.paladium.utils;

public enum StaffAlertTypes {
  CHEST_UNLOOTABLE("chest_unlootable");
  
  private String permission;
  
  StaffAlertTypes(String permission) {
    this.permission = permission;
  }
  
  public String getPermission() {
    return this.permission;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\StaffAlertTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */