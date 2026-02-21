package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class WoodenLegEventHandler {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
    EntityPlayer entity = event.entityPlayer;
    ItemStack stack = entity.func_82169_q(1);
    boolean hideLegs = (stack != null && stack.func_77973_b() == ItemsRegister.WOODEN_LEG);
    if (!hideLegs)
      return; 
    event.renderer.field_77109_a.field_78124_i.field_78807_k = true;
    event.renderer.field_77108_b.field_78124_i.field_78807_k = true;
    event.renderer.field_77111_i.field_78124_i.field_78807_k = true;
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
    event.renderer.field_77109_a.field_78124_i.field_78807_k = false;
    event.renderer.field_77108_b.field_78124_i.field_78807_k = false;
    event.renderer.field_77111_i.field_78124_i.field_78807_k = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\events\WoodenLegEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */