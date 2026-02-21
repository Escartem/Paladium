package fr.paladium.palamod.modules.luckyblock.monthly.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class DialogEventHandler {
  @SubscribeEvent
  public void onInteractWithNPC(EntityInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!(event.target instanceof EntityLivingBase))
      return; 
    EntityLivingBase npc = (EntityLivingBase)event.target;
    for (AbstractDialogManager manager : PLuckyBlock.instance.getDialogManagers()) {
      if (manager.canDisplay(npc)) {
        manager.sendDialog((EntityPlayerMP)player, (Entity)npc);
        break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\events\DialogEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */