package fr.paladium.palamixins.mixin.common.fml;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.handshake.FMLHandshakeMessage;
import cpw.mods.fml.common.network.handshake.HandshakeMessageHandler;
import cpw.mods.fml.common.network.handshake.NetworkDispatcher;
import fr.paladium.palamixins.accessor.common.fml.NetworkDispatcherAccessor;
import fr.paladium.palamixins.event.common.fml.FMLModListEvent;
import io.netty.channel.ChannelHandlerContext;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({HandshakeMessageHandler.class})
public abstract class MixinHandshakeMessageHandler {
  @Inject(method = {"channelRead0(Lio/netty/channel/ChannelHandlerContext;Lcpw/mods/fml/common/network/handshake/FMLHandshakeMessage;)V"}, at = {@At("HEAD")}, remap = false)
  private void channelRead0$ModList(ChannelHandlerContext ctx, FMLHandshakeMessage msg, CallbackInfo ci) {
    if (!(msg instanceof FMLHandshakeMessage.ModList))
      return; 
    FMLHandshakeMessage.ModList modList = (FMLHandshakeMessage.ModList)msg;
    NetworkDispatcher dispatcher = (NetworkDispatcher)ctx.channel().attr(NetworkDispatcher.FML_DISPATCHER).get();
    if (dispatcher instanceof NetworkDispatcherAccessor) {
      NetworkDispatcherAccessor accessor = (NetworkDispatcherAccessor)dispatcher;
      MinecraftForge.EVENT_BUS.post((Event)new FMLModListEvent(accessor.getPlayer(), modList));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\fml\MixinHandshakeMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */