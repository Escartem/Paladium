package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets.CSJumpPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.NovemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.packets.CSOpenInventoryPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;

public class TickEventHandler {
  @SubscribeEvent
  public void onGuiOpen(GuiOpenEvent event) {
    if (event.gui == null)
      return; 
    if (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory || event.gui instanceof fr.paladium.zephyrui.internal.mod.bridge.gui.client.GuiScreenBridge)
      NovemberCommonModule.INSTANCE.getNetwork().sendToServer((IMessage)new CSOpenInventoryPacket()); 
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    if (event.phase != TickEvent.Phase.END || minecraft.field_71439_g == null)
      return; 
    if (minecraft.field_71474_y.field_74314_A.func_151470_d() && minecraft.field_71439_g.field_70154_o instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityDutchBoat)
      PalaMod.network.sendToServer((IMessage)new CSJumpPacket()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\events\TickEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */