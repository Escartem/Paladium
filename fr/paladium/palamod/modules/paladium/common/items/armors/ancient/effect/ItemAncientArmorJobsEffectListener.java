package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.OnPlayerPreEarnXp;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import net.minecraft.entity.player.EntityPlayer;

public class ItemAncientArmorJobsEffectListener {
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onEarnExp(OnPlayerPreEarnXp event) {
    EntityPlayer player = event.player;
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.JOBS))
      return; 
    event.xpearn *= ItemAncientArmor.isFull(player) ? 1.5D : 1.25D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorJobsEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */