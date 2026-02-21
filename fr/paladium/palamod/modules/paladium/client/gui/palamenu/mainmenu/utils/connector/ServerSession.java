package fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ServerSession {
  private static final AtomicInteger CONNECTOR = new AtomicInteger();
  
  private final Minecraft mc;
  
  private final String host;
  
  private final int port;
  
  private Consumer<String> callback;
  
  private NetworkManager network;
  
  private boolean cancelled;
  
  private boolean active;
  
  public Minecraft getMc() {
    return this.mc;
  }
  
  public String getHost() {
    return this.host;
  }
  
  public int getPort() {
    return this.port;
  }
  
  public Consumer<String> getCallback() {
    return this.callback;
  }
  
  public NetworkManager getNetwork() {
    return this.network;
  }
  
  public boolean isCancelled() {
    return this.cancelled;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  public ServerSession(String host, int port, Consumer<String> callback) {
    this.mc = Minecraft.func_71410_x();
    this.host = host;
    this.port = port;
    this.callback = callback;
  }
  
  public void connect() {
    this.active = true;
    ObfuscationReflectionHelper.setPrivateValue(FMLClientHandler.class, FMLClientHandler.instance(), new CountDownLatch(1), new String[] { "playClientBlock" });
    clearWorld();
    if (this.callback != null)
      this.callback.accept("authentification"); 
    int connectorId = CONNECTOR.incrementAndGet();
    (new Thread("Server Connector #" + connectorId) {
        public void run() {
          UIMainMenu uIMainMenu = new UIMainMenu();
          InetAddress inetaddress = null;
          try {
            if (ServerSession.this.cancelled)
              return; 
            inetaddress = InetAddress.getByName(ServerSession.this.host);
            ServerSession.this.network = NetworkManager.func_150726_a(inetaddress, ServerSession.this.port);
            ServerSession.this.network.func_150719_a((INetHandler)new NetHandlerLoginClient(ServerSession.this.network, ServerSession.this.mc, (GuiScreen)uIMainMenu));
            ServerSession.this.network.func_150725_a((Packet)new C00Handshake((Constants.MOD_ENV == Constants.Environment.DEV) ? 5 : 871, ServerSession.this.host, ServerSession.this.port, EnumConnectionState.LOGIN), new io.netty.util.concurrent.GenericFutureListener[0]);
            ServerSession.this.network.func_150725_a((Packet)new C00PacketLoginStart(ServerSession.this.mc.func_110432_I().func_148256_e()), new io.netty.util.concurrent.GenericFutureListener[0]);
            if (ServerSession.this.callback != null)
              ServerSession.this.callback.accept("connexion"); 
          } catch (UnknownHostException unknownhostexception) {
            unknownhostexception.printStackTrace();
            if (ServerSession.this.cancelled)
              return; 
            ServerSession.this.active = false;
            ServerSession.this.callback.accept("failed");
          } catch (Exception exception) {
            exception.printStackTrace();
            if (ServerSession.this.cancelled)
              return; 
            String s = exception.toString();
            if (inetaddress != null) {
              String s1 = inetaddress.toString() + ":" + ServerSession.this.port;
              s = s.replaceAll(s1, "");
            } 
            ServerSession.this.active = false;
            ServerSession.this.callback.accept("failed");
          } 
        }
      }).start();
  }
  
  private void clearWorld() {
    NetHandlerPlayClient nethandlerplayclient = this.mc.func_147114_u();
    if (nethandlerplayclient != null)
      nethandlerplayclient.func_147296_c(); 
    if (this.mc.func_71401_C() != null) {
      this.mc.func_71401_C().func_71263_m();
      while (!this.mc.func_71401_C().func_71241_aa()) {
        try {
          Thread.sleep(10L);
        } catch (InterruptedException interruptedException) {}
      } 
    } 
    this.mc.field_71460_t.func_147701_i().func_148249_a();
    this.mc.func_147118_V().func_147690_c();
    this.mc.field_71441_e = null;
    this.mc.func_71359_d().func_75800_d();
  }
  
  public void cancel() {
    this.cancelled = true;
    if (this.network != null)
      this.network.func_150718_a((IChatComponent)new ChatComponentText("Aborted")); 
    this.active = false;
    this.callback.accept("cancelled");
  }
  
  public void update() {
    if (this.network != null && !this.cancelled)
      if (this.network.func_150724_d()) {
        this.network.func_74428_b();
      } else if (this.network.func_150730_f() != null) {
        this.network.func_150729_e().func_147231_a(this.network.func_150730_f());
      }  
  }
  
  public void setCallback(Consumer<String> callback) {
    this.callback = callback;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\mainmen\\utils\connector\ServerSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */