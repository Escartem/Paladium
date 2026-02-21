package fr.paladium.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.ServerType;
import fr.paladium.common.network.SCPacketCommonConfig;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class CommonEventHandler {
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
    if (!(e.player instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.player;
    try {
      if (!BukkitUtils.hasPermission(player.func_110124_au(), "paladium.join"))
        player.field_71135_a.func_147360_c("§8[§6Paladium§8] §cCe serveur est hors ligne."); 
    } catch (NoClassDefFoundError|Exception e2) {
      e2.printStackTrace();
    } 
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent e) {
    if (!(e.entity instanceof EntityPlayerMP) || e.world.field_72995_K)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.entity;
    CommonModule.networkWrapper.sendTo((IMessage)new SCPacketCommonConfig(), player);
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onRenderLobbyOverlay(RenderGameOverlayEvent.Pre e) {
    if (CommonModule.getInstance().getConfig().getServerType() != ServerType.LOBBY)
      return; 
    if ((Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75098_d)
      return; 
    if (e.type != RenderGameOverlayEvent.ElementType.HEALTH && e.type != RenderGameOverlayEvent.ElementType.EXPERIENCE && e.type != RenderGameOverlayEvent.ElementType.FOOD)
      return; 
    e.setCanceled(true);
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post e) {
    if (e.gui instanceof net.minecraft.client.gui.GuiOptions)
      for (Object buttonObj : e.buttonList) {
        GuiButton button = (GuiButton)buttonObj;
        if (button.field_146127_k == 8675309) {
          button.field_146124_l = false;
          break;
        } 
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\CommonEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */