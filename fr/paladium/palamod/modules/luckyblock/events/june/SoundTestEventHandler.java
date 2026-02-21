package fr.paladium.palamod.modules.luckyblock.events.june;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.blocks.june.BlockSoundTest;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.StructureManager;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class SoundTestEventHandler {
  @SubscribeEvent
  public void onBlock(BlockEvent.BreakEvent event) {
    if (interactOnStructure(new Location(event.world, event.x, event.y, event.z)))
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onPlaceBlock(BlockEvent.PlaceEvent event) {
    EntityPlayer player = event.player;
    if (interactOnStructure(new Location(event.world, event.x, event.y, event.z))) {
      event.setCanceled(true);
      return;
    } 
    Block block = event.block;
    if (!block.equals(BlocksRegister.BRIGHT_SOUND_TEST))
      return; 
    ItemStack stack = player.func_71045_bC();
    if (stack == null || !EventUtils.canInteract(player, event.x, event.y, event.z))
      return; 
    BlockSoundTest blockSoundTest = (BlockSoundTest)block;
    Location location = new Location(player.field_70170_p, event.x, event.y, event.z);
    AbstractStructure structure = blockSoundTest.getStructure(stack, location, player);
    if (structure == null)
      return; 
    if (!structure.canSpawn()) {
      PlayerUtils.sendMessage(player, structure.getConditions());
      event.setCanceled(true);
      return;
    } 
    event.world.func_147449_b(event.x, event.y, event.z, Blocks.field_150350_a);
    structure.spawn();
  }
  
  private boolean interactOnStructure(Location location) {
    return StructureManager.getInstance().getStructureFromCuboidPoint(location).isPresent();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\june\SoundTestEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */