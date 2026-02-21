package fr.paladium.palamod.modules.paladium.dummy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class DummyEventHandler {
  @SubscribeEvent
  public void onPlayerLeftClick(LivingAttackEvent event) {
    if (event.source == null || event.entity == null || !(event.entity instanceof EntityDummy))
      return; 
    if (!event.source.field_76373_n.equals("player"))
      return; 
    if (!(event.source.func_76346_g() instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.source.func_76346_g();
    if (player.func_70093_af() && player.func_71045_bC() == null)
      ((EntityDummy)event.entity).dismantle(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\DummyEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */