package fr.paladium.palacommunityevent.common.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palacommunityevent.PalaCommunityEventMod;
import fr.paladium.palacommunityevent.client.gui.advent.UIAdventCalendar;
import fr.paladium.palacommunityevent.common.CommonProxy;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Keyboard;

public class NPCListener {
  @SubscribeEvent
  public void onInteractWithNPC(EntityInteractEvent e) {
    if (e.target instanceof EntityNPCInterface) {
      EntityNPCInterface val = (EntityNPCInterface)e.target;
      for (CommunityEvent event : PalaCommunityEventManager.getInstance().getCommunityEvents()) {
        if (val.display.name.contains(event.npcName) && !e.entityPlayer.field_70170_p.field_72995_K) {
          EntityPlayer p = e.entityPlayer;
          PalaCommunityEventManager.getInstance().openGui(p, event);
          e.setCanceled(true);
          return;
        } 
      } 
      if (val.display.name.equals("Père Noël")) {
        e.entityPlayer.openGui(PalaCommunityEventMod.getInstance(), CommonProxy.ADVENT_CALENDAR, e.entityPlayer.field_70170_p, 0, 0, 0);
        e.setCanceled(true);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onInteractWithPig(EntityInteractEvent e) {
    if (ForgeEnv.isDev() && e.target instanceof EntityPig) {
      EntityPig val = (EntityPig)e.target;
      if (!val.func_94056_bM())
        return; 
      for (CommunityEvent event : PalaCommunityEventManager.getInstance().getCommunityEvents()) {
        if (val.func_94057_bL().contains(event.npcName) && !e.entityPlayer.field_70170_p.field_72995_K) {
          EntityPlayer p = e.entityPlayer;
          PalaCommunityEventManager.getInstance().openGui(p, event);
          e.setCanceled(true);
          return;
        } 
      } 
      if (val.func_94057_bL().equals("Père Noël")) {
        e.entityPlayer.openGui(PalaCommunityEventMod.getInstance(), CommonProxy.ADVENT_CALENDAR, e.entityPlayer.field_70170_p, 0, 0, 0);
        e.setCanceled(true);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onDebug(TickEvent.ClientTickEvent e) {
    if (!ForgeEnv.isDev())
      return; 
    if (Keyboard.isKeyDown(25) && (Minecraft.func_71410_x()).field_71462_r == null)
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIAdventCalendar()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\listeners\NPCListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */