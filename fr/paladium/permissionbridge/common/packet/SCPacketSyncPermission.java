package fr.paladium.permissionbridge.common.packet;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.permissionbridge.client.event.ClientPermissionLoadedEvent;
import fr.paladium.permissionbridge.common.data.PermissionListData;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import net.minecraftforge.common.MinecraftForge;

public class SCPacketSyncPermission extends ForgePacket {
  @PacketData
  private PermissionListData permissionListData;
  
  public SCPacketSyncPermission() {}
  
  public SCPacketSyncPermission(PermissionListData permissionListData) {
    this.permissionListData = permissionListData;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    PermissionManager.inst().set(this.permissionListData.getPermissions());
    MinecraftForge.EVENT_BUS.post((Event)new ClientPermissionLoadedEvent(this.permissionListData));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\packet\SCPacketSyncPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */