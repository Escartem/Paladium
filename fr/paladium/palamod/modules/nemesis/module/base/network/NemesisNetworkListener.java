package fr.paladium.palamod.modules.nemesis.module.base.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import java.util.Optional;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

public class NemesisNetworkListener {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
    ChannelPipeline pipeline = event.manager.channel().pipeline();
    if (pipeline.get("nemesis_network_handler") == null)
      pipeline.addBefore("packet_handler", "nemesis_network_handler", (ChannelHandler)new ChannelInboundHandlerAdapter() {
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
              if (msg instanceof S3FPacketCustomPayload) {
                S3FPacketCustomPayload packet = (S3FPacketCustomPayload)msg;
                String channel = packet.func_149169_c();
                if (channel != null && channel.startsWith("Nemesis|")) {
                  Optional<NemesisNetwork> optionalNetwork = NemesisNetwork.getNetwork(channel.substring(8));
                  if (optionalNetwork.isPresent()) {
                    NemesisNetwork network = optionalNetwork.get();
                    byte[] data = packet.func_149168_d();
                    if (data == null || data.length == 0)
                      return; 
                    ByteBuf buf = Unpooled.wrappedBuffer(data).copy();
                    int packetId = buf.readUnsignedByte();
                    network.getPacket(packetId).ifPresent(nemesisPacket -> {
                          try {
                            nemesisPacket.read(buf);
                            nemesisPacket.processClient();
                          } catch (Throwable t) {
                            t.printStackTrace();
                          } 
                        });
                    return;
                  } 
                } 
              } 
              super.channelRead(ctx, msg);
            }
          }); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\base\network\NemesisNetworkListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */