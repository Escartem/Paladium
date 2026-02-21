package fr.paladium.palavoicechat.server.voip;

import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palavoicechat.common.network.SCVoiceServerUnavailablePacket;
import fr.paladium.palavoicechat.common.voip.SocketMessage;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.voip.packet.impl.PlayerConnectionPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.PlayerDisconnectionPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.UpdatePositionPacket;
import fr.paladium.palavoicechat.common.wgflag.VoiceChatFlagsUtils;
import fr.paladium.palavoicechat.server.ServerProxy;
import fr.paladium.palavoicechat.server.manager.KeepAliveManager;
import fr.paladium.palavoicechat.utils.PlayerLocation;
import io.netty.bootstrap.Bootstrap;
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
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class IoNettyVoIPServer {
  private static IoNettyVoIPServer INSTANCE;
  
  private final List<UUID> connectedPlayers = new ArrayList<>();
  
  private PlayerUpdateThread playerUpdateThread;
  
  private ChannelHandlerContext ctx;
  
  private final InetSocketAddress serverAddress;
  
  public List<UUID> getConnectedPlayers() {
    return this.connectedPlayers;
  }
  
  public PlayerUpdateThread getPlayerUpdateThread() {
    return this.playerUpdateThread;
  }
  
  public void setCtx(ChannelHandlerContext ctx) {
    this.ctx = ctx;
  }
  
  private long lastKeepAlive = 0L;
  
  public void setLastKeepAlive(long lastKeepAlive) {
    this.lastKeepAlive = lastKeepAlive;
  }
  
  protected IoNettyVoIPServer() {
    INSTANCE = this;
    this.serverAddress = new InetSocketAddress(ServerProxy.serverConfig.getVoipServerIp(), ServerProxy.serverConfig.getVoipServerPort());
    (new Thread(() -> {
          NioEventLoopGroup group = new NioEventLoopGroup();
          try {
            Bootstrap bootstrap = (Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)group)).channel(NioDatagramChannel.class)).option(ChannelOption.SO_RCVBUF, Integer.valueOf(65535))).option(ChannelOption.SO_SNDBUF, Integer.valueOf(65535))).handler((ChannelHandler)new ChannelInitializer<DatagramChannel>() {
                  protected void initChannel(DatagramChannel ch) {
                    ch.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new IncomingPacketHandler() });
                  }
                });
            ChannelFuture future = bootstrap.bind(0).sync();
            future.channel().closeFuture().sync();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            group.shutdownGracefully();
          } 
        }"VoIPClientForServer")).start();
    startPlayerThread();
    (new KeepAliveThread()).start();
  }
  
  public static IoNettyVoIPServer getInstance() {
    return (INSTANCE == null) ? (INSTANCE = new IoNettyVoIPServer()) : INSTANCE;
  }
  
  public void sendPacket(Packet<?> packet) {
    this.ctx.writeAndFlush(new DatagramPacket(SocketMessage.writeServer(packet), this.serverAddress));
  }
  
  public void connectPlayer(EntityPlayerMP player) {
    if (!KeepAliveManager.getInstance().isAlive()) {
      (new SCVoiceServerUnavailablePacket()).send(player);
      return;
    } 
    if (!this.connectedPlayers.contains(player.func_110124_au())) {
      this.connectedPlayers.add(player.func_110124_au());
      sendPacket((Packet<?>)new PlayerConnectionPacket(player.func_110124_au()));
      System.out.println("[PalaVoiceChat] Connecting client " + player.getDisplayName() + " to voip chat");
    } 
  }
  
  public void disconnectPlayer(EntityPlayerMP player) {
    if (this.connectedPlayers.contains(player.func_110124_au())) {
      this.connectedPlayers.remove(player.func_110124_au());
      sendPacket((Packet<?>)new PlayerDisconnectionPacket(player.func_110124_au()));
      System.out.println("[PalaVoiceChat] Disconnecting player " + player.getDisplayName() + " from voip chat");
    } 
  }
  
  public void restartPlayerUpdateThread() {
    stopPlayerThread();
    startPlayerThread();
  }
  
  public void stopPlayerThread() {
    if (this.playerUpdateThread != null && this.playerUpdateThread.isRunning())
      this.playerUpdateThread.close(); 
  }
  
  public void startPlayerThread() {
    this.playerUpdateThread = new PlayerUpdateThread();
    this.playerUpdateThread.start();
  }
  
  public boolean isPlayerConnected(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return this.connectedPlayers.contains(player.func_110124_au());
  }
  
  public boolean isPlayerConnected(@NonNull UUID playerUUID) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    return this.connectedPlayers.contains(playerUUID);
  }
  
  public class KeepAliveThread extends Thread {
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException interruptedException) {}
        KeepAliveManager.getInstance().sendKeepAlive();
      } 
    }
  }
  
  public class PlayerUpdateThread extends Thread {
    public void setRunning(boolean running) {
      this.running = running;
    }
    
    public boolean running = true;
    
    public boolean isRunning() {
      return this.running;
    }
    
    public void run() {
      while (this.running) {
        try {
          Thread.sleep(Math.max(50L, ServerProxy.serverConfig.getPlayerPositionRefreshRate()));
        } catch (InterruptedException interruptedException) {}
        if (!KeepAliveManager.getInstance().isAlive())
          continue; 
        List<PlayerLocation> locations = new ArrayList<>();
        PlayerSelector.ALL().apply(player -> {
              if (IoNettyVoIPServer.this.connectedPlayers.contains(player.func_110124_au())) {
                PlayerLocation loc = new PlayerLocation(player.func_110124_au(), player.func_70005_c_(), Server.getHostName(), player.field_70170_p.field_73011_w.field_76574_g, (float)player.field_70165_t, (float)player.field_70163_u, (float)player.field_70161_v, VoiceChatFlagsUtils.canUserSpeak((EntityPlayer)player));
                locations.add(loc);
              } 
            });
        if (locations.isEmpty())
          continue; 
        int maxPerPacket = Math.max(1, ServerProxy.serverConfig.getMaxLocationUpdatePerPacket());
        for (int i = 0; i < locations.size(); i += maxPerPacket) {
          List<PlayerLocation> batch = locations.subList(i, Math.min(i + maxPerPacket, locations.size()));
          IoNettyVoIPServer.this.sendPacket((Packet<?>)new UpdatePositionPacket(batch));
        } 
      } 
    }
    
    public void close() {
      this.running = false;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\voip\IoNettyVoIPServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */