package fr.paladium.pet.server.skill.listener.active;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.skill.breakspeed.SCGlobalBreakSpeedPacket;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.pet.server.skill.handler.impl.active.UnbreakablePickaxeSkill;
import fr.paladium.pet.server.skill.handler.impl.active.data.PickaxeData;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class BreakSpeedListener {
  public static final long GLOBAL_DURATION = TimeUnit.SECONDS.toMillis(1L);
  
  public static BreakSpeedListener INSTANCE;
  
  private final HashMap<UUID, PickaxeData> dataMap;
  
  public BreakSpeedListener() {
    INSTANCE = this;
    this.dataMap = new HashMap<>();
  }
  
  @SubscribeEvent
  public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    Block block = event.block;
    UUID uniqueId = player.func_110124_au();
    if (block.equals(Blocks.field_150343_Z) && 
      this.dataMap.containsKey(uniqueId)) {
      PickaxeData data = this.dataMap.get(uniqueId);
      if (isExpired(data.getExpirationMillis())) {
        this.dataMap.remove(uniqueId);
      } else {
        event.newSpeed = event.originalSpeed * (float)data.getValue();
        return;
      } 
    } 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.GOOD_PICKAXE.getResponse(pet);
    double value = response.getPersonalValue(pet);
    double multiplier = 1.0D + response.getValueAsPercent(value);
    if (!response.has(value))
      return; 
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new SCGlobalBreakSpeedPacket(multiplier, GLOBAL_DURATION), player);
    event.newSpeed = event.originalSpeed * (float)multiplier;
  }
  
  private boolean isExpired(long duration) {
    return (System.currentTimeMillis() >= duration);
  }
  
  public void put(EntityPlayerMP player, double value) {
    PickaxeData data = new PickaxeData(value, System.currentTimeMillis() + UnbreakablePickaxeSkill.DURATION);
    this.dataMap.put(player.func_110124_au(), data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\active\BreakSpeedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */