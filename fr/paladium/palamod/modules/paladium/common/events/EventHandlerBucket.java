package fr.paladium.palamod.modules.paladium.common.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class EventHandlerBucket {
  public static Map<Block, Item> buckets = new HashMap<>();
  
  @SubscribeEvent
  public void onBucketFill(FillBucketEvent event) {
    ItemStack result = fillCustomBucket(event.world, event.target, event.entityPlayer);
    if (result == null)
      return; 
    event.result = result;
    event.setResult(Event.Result.ALLOW);
  }
  
  private ItemStack fillCustomBucket(World world, MovingObjectPosition pos, EntityPlayer player) {
    Block block = world.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
    if (!player.func_146099_a(block))
      return null; 
    Item bucket = buckets.get(block);
    if (bucket != null && world.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d) == 0) {
      world.func_147468_f(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
      return new ItemStack(bucket);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\EventHandlerBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */