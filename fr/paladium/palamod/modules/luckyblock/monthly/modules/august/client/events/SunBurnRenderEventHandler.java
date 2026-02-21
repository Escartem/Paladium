package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public class SunBurnRenderEventHandler {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
    if (MonthlyUtils.hasPotionEffect((EntityLivingBase)event.entityPlayer, (Potion)PLuckyBlock.SUNBURN))
      GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F); 
    EntityPlayer player = event.entityPlayer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\events\SunBurnRenderEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */