package fr.paladium.permissionbridge.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class PermissionBridgeClientListener {
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if ((Minecraft.func_71410_x()).field_71439_g == null || !event.entity.func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au()))
      return; 
    PermissionManager.inst().getPermissions().clear();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\client\listener\PermissionBridgeClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */