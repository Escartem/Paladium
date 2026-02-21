package fr.paladium.palamod.modules.shop.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.KeysRegister;
import net.minecraft.client.Minecraft;

public class EventsManager {
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onKeyInput(TickEvent.ClientTickEvent e) {
    if (e.phase == TickEvent.Phase.END && Minecraft.func_71410_x() != null && 
      KeysRegister.KEY_SHOP.func_151468_f() && (Minecraft.func_71410_x()).field_71439_g != null)
      (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/shop"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */