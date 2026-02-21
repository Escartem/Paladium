package fr.paladium.palavoicechat.server.voip;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;

class null extends ChannelInitializer<DatagramChannel> {
  protected void initChannel(DatagramChannel ch) {
    ch.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new IncomingPacketHandler() });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\voip\IoNettyVoIPServer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */