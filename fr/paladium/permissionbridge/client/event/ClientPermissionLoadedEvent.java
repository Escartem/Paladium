package fr.paladium.permissionbridge.client.event;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.permissionbridge.common.data.PermissionListData;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import lombok.NonNull;

public class ClientPermissionLoadedEvent extends Event {
  private final PermissionListData permissionListData;
  
  public PermissionListData getPermissionListData() {
    return this.permissionListData;
  }
  
  public ClientPermissionLoadedEvent(@NonNull PermissionListData permissionListData) {
    if (permissionListData == null)
      throw new NullPointerException("permissionListData is marked non-null but is null"); 
    this.permissionListData = permissionListData;
  }
  
  public PermissionManager getPermissionManager() {
    return PermissionManager.inst();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\client\event\ClientPermissionLoadedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */