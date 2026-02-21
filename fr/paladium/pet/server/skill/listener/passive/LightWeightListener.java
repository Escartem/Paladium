package fr.paladium.pet.server.skill.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.combattag.CombatTag;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.bukkit.Bukkit;

public class LightWeightListener {
  @SubscribeEvent
  public void onFall(LivingHurtEvent event) {
    if (!event.source.equals(DamageSource.field_76379_h) || !(event.entityLiving instanceof EntityPlayerMP))
      return; 
    try {
      if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(event.entity.func_110124_au())))
        return; 
    } catch (Exception|NoClassDefFoundError exception) {}
    EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.LIGHTWEIGHT.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    double percent = response.getValueAsPercent(value);
    float decrease = (float)(event.ammount * percent);
    event.ammount -= decrease;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\passive\LightWeightListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */