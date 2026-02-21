package fr.paladium.pet.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.client.data.ClientBreakSpeedData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ClientBreakSpeedListener {
  public static ClientBreakSpeedListener INSTANCE;
  
  @SubscribeEvent
  public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
    Block block = event.block;
    long now = System.currentTimeMillis();
    ClientBreakSpeedData data = ClientBreakSpeedData.get();
    if (block.equals(Blocks.field_150343_Z) && !data.isExpired(now, data.getObsidianExpirationMillis())) {
      event.newSpeed = event.originalSpeed * (float)data.getObsidianBreakSpeed();
      return;
    } 
    if (!data.isExpired(now, data.getGlobalExpirationMillis()))
      event.newSpeed = event.originalSpeed * (float)data.getGlobalBreakSpeed(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\listener\ClientBreakSpeedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */