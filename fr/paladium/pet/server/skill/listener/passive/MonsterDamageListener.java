package fr.paladium.pet.server.skill.listener.passive;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.config.global.GlobalConfig;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class MonsterDamageListener {
  private static final Cache<EntityLivingBase, String> CLASS_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    EntityLivingBase living = event.entityLiving;
    if (living == null)
      return; 
    String name = (String)CLASS_CACHE.getIfPresent(living);
    if (name == null) {
      name = living.getClass().getSimpleName();
      CLASS_CACHE.put(living, name);
    } 
    GlobalConfig config = GlobalConfig.get();
    if (!config.getWhitelistedDamageEntities().contains(name) || event.source == null || !(event.source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.source.func_76346_g();
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.VITAL_POINT.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    double percentage = response.getValueAsPercent(value);
    float increase = (float)(event.ammount * percentage);
    event.ammount += increase;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\passive\MonsterDamageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */