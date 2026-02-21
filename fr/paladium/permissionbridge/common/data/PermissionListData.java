package fr.paladium.permissionbridge.common.data;

import java.util.List;

public class PermissionListData {
  private final List<String> permissions;
  
  public static PermissionListData of(List<String> permissions) {
    return new PermissionListData(permissions);
  }
  
  private PermissionListData(List<String> permissions) {
    this.permissions = permissions;
  }
  
  public List<String> getPermissions() {
    return this.permissions;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\data\PermissionListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */