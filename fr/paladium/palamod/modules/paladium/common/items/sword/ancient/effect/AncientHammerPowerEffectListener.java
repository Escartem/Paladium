package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.EntityAncientHammerEffect;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager.AncientHammerEffectCamManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class AncientHammerPowerEffectListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onLivingAttack(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayerMP) || !(event.source.func_76364_f() instanceof EntityPlayerMP) || event.entity.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP hurtPlayer = (EntityPlayerMP)event.entity;
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get((EntityPlayer)hurtPlayer);
    float amount = event.ammount;
    if (amount <= 0.0F || pData == null || !pData.isPowerEffectActive())
      return; 
    float healthAmount = hurtPlayer.func_110143_aJ();
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            float nextTickHealthAmount = hurtPlayer.func_110143_aJ();
            if (nextTickHealthAmount < healthAmount) {
              float reducedAmount = healthAmount - nextTickHealthAmount;
              pData.addPowerEffectDamage(reducedAmount);
              hurtPlayer.field_70170_p.func_72908_a(hurtPlayer.field_70165_t, hurtPlayer.field_70163_u, hurtPlayer.field_70161_v, SoundUtils.IRONGOLEM_HIT.getSoundName(), 0.75F, 1.0F);
            } 
          } });
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.side == Side.CLIENT) {
      ItemAncientHammerPlayerData itemAncientHammerPlayerData = ItemAncientHammerPlayerData.get(event.player);
      if (itemAncientHammerPlayerData == null || !itemAncientHammerPlayerData.isPowerEffectActive())
        return; 
      double time = event.player.field_70173_aa * 0.15D;
      double radius = 0.8D;
      double baseY = event.player.field_70121_D.field_72338_b + 0.5D;
      for (int i = 0; i < 3; i++) {
        double offset = 2.0943951023931953D * i;
        double heightOffset = Math.sin(time * 2.0D + offset) * 0.3D;
        double x = event.player.field_70165_t + Math.cos(time + offset) * 0.8D;
        double y = baseY + heightOffset;
        double z = event.player.field_70161_v + Math.sin(time + offset) * 0.8D;
        double red = 1.0D - itemAncientHammerPlayerData.getPowerEffectDamage() / 19.5D;
        event.player.field_70170_p.func_72869_a("mobSpell", x, y, z, red, 0.0D, 0.0D);
      } 
      return;
    } 
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get(event.player);
    if (pData == null || (!pData.isPowerEffectActive() && pData.isPowerEffectPlayed()))
      return; 
    if (pData.getPowerEffect() > 0L && UniversalTimeUtils.now() > pData.getPowerEffect() && !pData.isPowerEffectPlayed()) {
      pData.setPowerEffectPlayed(true);
      EntityAncientHammerEffect entity = new EntityAncientHammerEffect(event.player.field_70170_p, LegendaryStone.Effect.POWER, event.player, event.player.field_70177_z);
      event.player.field_70170_p.func_72838_d((Entity)entity);
      AncientHammerEffectCamManager.start((EntityPlayerMP)event.player, entity);
    } 
  }
  
  @SubscribeEvent
  public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get(event.player);
    if (pData != null && pData.isPowerEffectActive())
      pData.disablePowerEffect(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\effect\AncientHammerPowerEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */