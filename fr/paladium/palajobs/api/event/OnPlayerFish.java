package fr.paladium.palajobs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Cancelable
public class OnPlayerFish extends Event {
  public final EntityPlayer player;
  
  public final ItemStack reward;
  
  public OnPlayerFish(EntityPlayer player, ItemStack reward) {
    this.player = player;
    this.reward = reward;
  }
  
  public static OnPlayerFish fireEvent(EntityPlayer player, ItemStack reward) {
    OnPlayerFish event = new OnPlayerFish(player, reward);
    MinecraftForge.EVENT_BUS.post(event);
    return event;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\OnPlayerFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */