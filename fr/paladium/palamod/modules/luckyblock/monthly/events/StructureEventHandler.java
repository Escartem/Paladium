package fr.paladium.palamod.modules.luckyblock.monthly.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.StructureManager;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;

public class StructureEventHandler {
  public static final String STRUCTURE_MESSAGE = "&cVous ne pouvez pas casser/poser de blocs dans une structure";
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    EntityPlayer player = event.getPlayer();
    if (interactOnStructure(new Location(event.world, event.x, event.y, event.z)))
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onPlaceBlock(BlockEvent.PlaceEvent event) {
    EntityPlayer player = event.player;
    if (interactOnStructure(new Location(event.world, event.x, event.y, event.z)))
      event.setCanceled(true); 
  }
  
  private boolean interactOnStructure(Location location) {
    Optional<AbstractStructure> AbstractStructure = StructureManager.getInstance().getStructureFromCuboidPoint(location);
    if (AbstractStructure.isPresent()) {
      AbstractStructure structure = AbstractStructure.get();
      return !structure.isDestroyable();
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\events\StructureEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */