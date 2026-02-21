package fr.paladium.palavoicechat.server.voip;

import fr.paladium.palavoicechat.common.voip.SocketMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class IncomingPacketHandler extends SimpleChannelInboundHandler<DatagramPacket> {
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    IoNettyVoIPServer.getInstance().setCtx(ctx);
  }
  
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    IoNettyVoIPServer.getInstance().getPlayerUpdateThread().close();
  }
  
  protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
    ByteBuf buf = (ByteBuf)msg.content();
    SocketMessage.readPacket(ctx, msg, buf).handleServer();
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.flush();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\voip\IncomingPacketHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */