package fr.paladium.palamod.mixins.network;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.events.AbstractCommandEvent;
import fr.paladium.palamod.events.EditSignEvent;
import fr.paladium.palamod.modules.atutorial.TutorialBridge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({NetHandlerPlayServer.class})
public abstract class IMixinNetHandlerPlayServer implements INetHandlerPlayServer {
  @Shadow
  private EntityPlayerMP field_147369_b;
  
  @Inject(method = {"processUpdateSign"}, at = {@At("RETURN")}, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
  public void processUpdateSign(C12PacketUpdateSign p_147343_1_, CallbackInfo ci, WorldServer worldserver) {
    int x = p_147343_1_.func_149588_c();
    int y = p_147343_1_.func_149586_d();
    int z = p_147343_1_.func_149585_e();
    MinecraftForge.EVENT_BUS.post((Event)new EditSignEvent(this.field_147369_b, x, y, z, p_147343_1_.func_149589_f()));
  }
  
  @Inject(method = {"processChatMessage"}, at = {@At("HEAD")}, cancellable = true)
  public void processChatMessage(C01PacketChatMessage packet, CallbackInfo ci) {
    String s = packet.func_149439_c();
    s = StringUtils.normalizeSpace(s);
    if (!s.startsWith("/"))
      return; 
    AbstractCommandEvent event = new AbstractCommandEvent((EntityPlayer)this.field_147369_b, s);
    MinecraftForge.EVENT_BUS.post((Event)event);
    TutorialBridge.callCommandEvent((EntityPlayer)this.field_147369_b, s);
    if (event.isCanceled())
      ci.cancel(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\network\IMixinNetHandlerPlayServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */