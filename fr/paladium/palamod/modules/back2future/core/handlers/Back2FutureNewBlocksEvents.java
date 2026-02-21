package fr.paladium.palamod.modules.back2future.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class Back2FutureNewBlocksEvents {
  private static final Map<Block, Block> replacements;
  
  static {
    Map<Block, Block> tempMap = new HashMap<>();
    if (Back2Future.enableBrewingStands)
      tempMap.put(Blocks.field_150382_bo, ModBlocks.brewing_stand); 
    if (Back2Future.enableColourfulBeacons)
      tempMap.put(Blocks.field_150461_bJ, ModBlocks.beacon); 
    if (Back2Future.enableInvertedDaylightSensor)
      tempMap.put(Blocks.field_150453_bW, ModBlocks.daylight_sensor); 
    replacements = Collections.unmodifiableMap(tempMap);
  }
  
  @SubscribeEvent
  public void onBlockPlaced(BlockEvent.PlaceEvent event) {
    if (!event.world.field_72995_K) {
      Block placedBlock = event.block;
      Block replacement = replacements.get(placedBlock);
      if (replacement != null && ((IConfigurable)replacement).isEnabled())
        replaceBlock(event.world, event.x, event.y, event.z, replacement); 
    } 
  }
  
  private void replaceBlock(World world, int x, int y, int z, Block replacement) {
    TileEntity tile = world.func_147438_o(x, y, z);
    NBTTagCompound nbt = new NBTTagCompound();
    if (tile != null) {
      tile.func_145841_b(nbt);
      world.func_147475_p(x, y, z);
    } 
    world.func_147449_b(x, y, z, replacement);
    TileEntity newTile = world.func_147438_o(x, y, z);
    if (newTile != null)
      newTile.func_145839_a(nbt); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\core\handlers\Back2FutureNewBlocksEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */