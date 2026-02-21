package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network.SCPacketItemAncientArmorTeleportationEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class ItemAncientArmorTeleportationEffectListener {
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onAttack(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.TELEPORTATION) || player.func_70681_au().nextFloat() > (ItemAncientArmor.isFull(player) ? 0.2F : 0.1F))
      return; 
    event.setCanceled(true);
    PalaMod.getNetHandler().sendToAllAround((IMessage)new SCPacketItemAncientArmorTeleportationEffect(player.func_145782_y()), new NetworkRegistry.TargetPoint(player.field_71093_bK, player.field_70165_t, player.field_70163_u, player.field_70161_v, 16.0D));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorTeleportationEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */