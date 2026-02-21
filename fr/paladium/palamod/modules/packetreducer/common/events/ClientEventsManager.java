package fr.paladium.palamod.modules.packetreducer.common.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.packetreducer.PPacketReducer;
import fr.paladium.palamod.modules.packetreducer.client.PPacketReducerProxyClient;
import fr.paladium.palamod.modules.packetreducer.common.packets.PReducerPlayersPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;

public class ClientEventsManager {
  private final PPacketReducerProxyClient client = PPacketReducerProxyClient.getInstance();
  
  private Frustrum frustrum;
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderPlayers(RenderLivingEvent.Post e) {
    if (this.frustrum == null || (Minecraft.func_71410_x()).field_71451_h == null)
      return; 
    if (e.entity.field_70158_ak || this.frustrum.func_78546_a(e.entity.field_70121_D) || e.entity.func_70685_l((Entity)(Minecraft.func_71410_x()).field_71451_h))
      this.client.getEntitiesOnScreen().add(Integer.valueOf(e.entity.func_145782_y())); 
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onClientTickEnd(TickEvent.ClientTickEvent e) {
    if (e.type != TickEvent.Type.CLIENT)
      return; 
    if (e.phase == TickEvent.Phase.START) {
      EntityLivingBase cameraEntity = (Minecraft.func_71410_x()).field_71451_h;
      if (cameraEntity != null) {
        double viewX = cameraEntity.field_70142_S;
        double viewY = cameraEntity.field_70137_T;
        double viewZ = cameraEntity.field_70136_U;
        this.frustrum = new Frustrum();
        this.frustrum.func_78547_a(viewX, viewY, viewZ);
      } 
    } else if (e.phase == TickEvent.Phase.END) {
      PPacketReducer.networkWrapper.sendToServer((IMessage)new PReducerPlayersPacket());
      this.client.getEntitiesOnScreen().clear();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\common\events\ClientEventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */