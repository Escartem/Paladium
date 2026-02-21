package fr.paladium.pet.server.skill.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TickPlayerListener {
  public static final float VANILLA_SPEED = 0.1F;
  
  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.START)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    World world = player.field_70170_p;
    PetPlayer pet = PetPlayer.get(event.player);
    if (pet == null || !pet.has())
      return; 
    performMagnet(player, world, pet);
  }
  
  public void performMagnet(EntityPlayerMP player, World world, PetPlayer pet) {
    PassiveResponse response = PassiveSkillEnum.MAGNET.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    float magnet = 0.05F;
    AxisAlignedBB axis = AxisAlignedBB.func_72330_a(player.field_70165_t - value, player.field_70163_u - value, player.field_70161_v - value, player.field_70165_t + value, player.field_70163_u + value, player.field_70161_v + value);
    List<?> items = world.func_72872_a(Entity.class, axis);
    items.forEach(item -> {
          if (item instanceof EntityItem) {
            EntityItem entityItem = (EntityItem)item;
            entityItem.field_70159_w += (player.field_70165_t - entityItem.field_70165_t > 0.0D) ? magnet : -magnet;
            entityItem.field_70181_x += (player.field_70163_u - entityItem.field_70163_u > 0.0D) ? magnet : -magnet;
            entityItem.field_70179_y += (player.field_70161_v - entityItem.field_70161_v > 0.0D) ? magnet : -magnet;
          } 
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\passive\TickPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */