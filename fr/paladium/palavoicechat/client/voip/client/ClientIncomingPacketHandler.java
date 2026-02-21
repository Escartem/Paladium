package fr.paladium.palavoicechat.client.voip.client;

import fr.paladium.palavoicechat.common.voip.SocketMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class ClientIncomingPacketHandler extends SimpleChannelInboundHandler<DatagramPacket> {
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    IoNettyVoIPClient.getInstance().setCtx(ctx);
  }
  
  protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
    ByteBuf buf = (ByteBuf)msg.content();
    SocketMessage.readPacket(ctx, msg, buf).handle();
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\client\ClientIncomingPacketHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */