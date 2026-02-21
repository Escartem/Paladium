package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.data.ItemAncientArmorPlayerData;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network.SCPacketItemAncientArmorInvisibilityEffect;
import fr.paladium.palarpg.PalaRPGMod;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemAncientArmorInvisibilityEffectListener {
  private static final Map<String, Long> INVULNERABILITY_MAP = new HashMap<>();
  
  @SubscribeEvent
  public void onInvulnerabilityDamage(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!INVULNERABILITY_MAP.containsKey(player.func_70005_c_()))
      return; 
    long time = ((Long)INVULNERABILITY_MAP.get(player.func_70005_c_())).longValue();
    if (System.currentTimeMillis() > time) {
      INVULNERABILITY_MAP.remove(player.func_70005_c_());
      return;
    } 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onInvulnerabilityAttack(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!INVULNERABILITY_MAP.containsKey(player.func_70005_c_()))
      return; 
    long time = ((Long)INVULNERABILITY_MAP.get(player.func_70005_c_())).longValue();
    if (System.currentTimeMillis() > time) {
      INVULNERABILITY_MAP.remove(player.func_70005_c_());
      return;
    } 
    event.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onDeath(LivingDeathEvent event) {
    if (!(event.entity instanceof EntityPlayer) || PalaRPGMod.proxy.isDungeonWorld())
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.INVISIBILITY) || ItemAncientArmorPlayerData.get(player).isInvisibilityOnCooldown())
      return; 
    event.setCanceled(true);
    player.func_70606_j(player.func_110138_aP());
    PalaMod.getNetHandler().sendToAllAround((IMessage)new SCPacketItemAncientArmorInvisibilityEffect(player.field_70165_t, player.field_70163_u, player.field_70161_v), new NetworkRegistry.TargetPoint(player.field_71093_bK, player.field_70165_t, player.field_70163_u, player.field_70161_v, 16.0D));
    ItemAncientArmorPlayerData.get(player).setInvisibility(ItemAncientArmor.isFull(player) ? 20000L : 10000L, 3600000L);
    INVULNERABILITY_MAP.put(player.func_70005_c_(), Long.valueOf(System.currentTimeMillis() + 3000L));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorInvisibilityEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */