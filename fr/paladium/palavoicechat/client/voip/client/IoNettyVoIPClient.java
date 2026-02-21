package fr.paladium.palavoicechat.client.voip.client;

import fr.paladium.palavoicechat.client.voip.audio.AudioChannel;
import fr.paladium.palavoicechat.client.voip.audio.TalkCache;
import fr.paladium.palavoicechat.client.voip.micro.MicThread;
import fr.paladium.palavoicechat.common.network.BBAuthenticatePacket;
import fr.paladium.palavoicechat.common.voip.SocketMessage;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.wgflag.VoiceChatFlagsUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class IoNettyVoIPClient {
  private static IoNettyVoIPClient INSTANCE;
  
  private final Map<UUID, AudioChannel> audioChannels;
  
  private final TalkCache talkCache;
  
  private volatile InetSocketAddress serverAddress;
  
  private volatile UUID secret;
  
  private volatile boolean authenticating;
  
  private volatile boolean authenticated;
  
  private ChannelHandlerContext ctx;
  
  private boolean muted;
  
  private boolean testingMic;
  
  private MicThread micThread;
  
  public Map<UUID, AudioChannel> getAudioChannels() {
    return this.audioChannels;
  }
  
  public TalkCache getTalkCache() {
    return this.talkCache;
  }
  
  public InetSocketAddress getServerAddress() {
    return this.serverAddress;
  }
  
  public UUID getSecret() {
    return this.secret;
  }
  
  public boolean isAuthenticating() {
    return this.authenticating;
  }
  
  public void setAuthenticating(boolean authenticating) {
    this.authenticating = authenticating;
  }
  
  public boolean isAuthenticated() {
    return this.authenticated;
  }
  
  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }
  
  public ChannelHandlerContext getCtx() {
    return this.ctx;
  }
  
  public void setCtx(ChannelHandlerContext ctx) {
    this.ctx = ctx;
  }
  
  public void setMuted(boolean muted) {
    this.muted = muted;
  }
  
  public boolean isTestingMic() {
    return this.testingMic;
  }
  
  public void setTestingMic(boolean testingMic) {
    this.testingMic = testingMic;
  }
  
  public MicThread getMicThread() {
    return this.micThread;
  }
  
  public IoNettyVoIPClient() {
    this.talkCache = new TalkCache();
    this.audioChannels = new HashMap<>();
    (new Thread(() -> {
          System.out.println("Démarrage du client VOIPClientForClient");
          NioEventLoopGroup group = new NioEventLoopGroup();
          try {
            Bootstrap bootstrap = (Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)group)).channelFactory(io.netty.channel.socket.nio.NioDatagramChannel::new)).option(ChannelOption.SO_RCVBUF, Integer.valueOf(65535))).option(ChannelOption.SO_SNDBUF, Integer.valueOf(65535))).handler((ChannelHandler)new ChannelInitializer<DatagramChannel>() {
                  protected void initChannel(DatagramChannel ch) {
                    ch.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new ClientIncomingPacketHandler() });
                  }
                });
            ChannelFuture future = bootstrap.bind(0).sync();
            future.channel().closeFuture().sync();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            group.shutdownGracefully();
          } 
        }"VoIPClientForClient")).start();
  }
  
  public static IoNettyVoIPClient getInstance() {
    return (INSTANCE == null) ? (INSTANCE = new IoNettyVoIPClient()) : INSTANCE;
  }
  
  public void authenticate(String serverIp, int serverPort, UUID secret) {
    if (this.authenticating)
      return; 
    if (this.authenticated && this.serverAddress != null && this.serverAddress.getHostString().equals(serverIp) && this.serverAddress.getPort() == serverPort)
      return; 
    this.authenticating = true;
    this.authenticated = false;
    this.serverAddress = new InetSocketAddress(serverIp, serverPort);
    this.secret = secret;
    (new AuthThread()).start();
  }
  
  public void startMicThread() {
    try {
      this.micThread = new MicThread();
      this.micThread.start();
    } catch (Exception e) {
      System.out.println("Mic unavailable");
      e.printStackTrace();
    } 
  }
  
  public void sendPacket(Packet<?> packet) {
    this.ctx.writeAndFlush(new DatagramPacket(SocketMessage.write(packet, this.secret), this.serverAddress));
  }
  
  public void reloadDataLines() {
    System.out.println("Reloading data lines");
    if (this.micThread != null) {
      System.out.println("Restarting microphone thread");
      this.micThread.close();
      this.micThread = null;
      startMicThread();
    } 
    System.out.println("Clearing audio channels");
    this.audioChannels.forEach((uuid, audioChannel) -> audioChannel.closeAndKill());
    this.audioChannels.clear();
  }
  
  public boolean isMuted() {
    if (this.muted)
      return true; 
    return !VoiceChatFlagsUtils.canUserSpeak((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
  }
  
  public boolean isMutedRaw() {
    return this.muted;
  }
  
  public boolean isConnected() {
    return (this.authenticated && this.serverAddress != null && this.secret != null);
  }
  
  public void connect() {
    (new BBAuthenticatePacket()).send();
  }
  
  public void disconnect() {
    setAuthenticating(false);
    setAuthenticated(false);
    this.serverAddress = null;
    this.secret = null;
    if (this.micThread != null) {
      this.micThread.close();
      this.micThread = null;
    } 
    this.audioChannels.forEach((uuid, audioChannel) -> audioChannel.closeAndKill());
    this.audioChannels.clear();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\client\IoNettyVoIPClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */