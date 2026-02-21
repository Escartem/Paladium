package fr.paladium.pet.common.event.global;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class PetStatChangeEvent extends Event {
  private final EntityPlayer player;
  
  private final UpdateStatType type;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public UpdateStatType getType() {
    return this.type;
  }
  
  public PetStatChangeEvent(EntityPlayer player, UpdateStatType type) {
    this.player = player;
    this.type = type;
  }
  
  public static void call(EntityPlayer player, UpdateStatType type) {
    PetStatChangeEvent event = new PetStatChangeEvent(player, type);
    MinecraftForge.EVENT_BUS.post(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\global\PetStatChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */