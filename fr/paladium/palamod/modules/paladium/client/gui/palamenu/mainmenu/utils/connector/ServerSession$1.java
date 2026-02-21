package fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector;

import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;

class null extends Thread {
  null(String x0) {
    super(x0);
  }
  
  public void run() {
    UIMainMenu uIMainMenu = new UIMainMenu();
    InetAddress inetaddress = null;
    try {
      if (ServerSession.access$000(ServerSession.this))
        return; 
      inetaddress = InetAddress.getByName(ServerSession.access$100(ServerSession.this));
      ServerSession.access$202(ServerSession.this, NetworkManager.func_150726_a(inetaddress, ServerSession.access$300(ServerSession.this)));
      ServerSession.access$200(ServerSession.this).func_150719_a((INetHandler)new NetHandlerLoginClient(ServerSession.access$200(ServerSession.this), ServerSession.access$400(ServerSession.this), (GuiScreen)uIMainMenu));
      ServerSession.access$200(ServerSession.this).func_150725_a((Packet)new C00Handshake((Constants.MOD_ENV == Constants.Environment.DEV) ? 5 : 871, ServerSession.access$100(ServerSession.this), ServerSession.access$300(ServerSession.this), EnumConnectionState.LOGIN), new io.netty.util.concurrent.GenericFutureListener[0]);
      ServerSession.access$200(ServerSession.this).func_150725_a((Packet)new C00PacketLoginStart(ServerSession.access$400(ServerSession.this).func_110432_I().func_148256_e()), new io.netty.util.concurrent.GenericFutureListener[0]);
      if (ServerSession.access$500(ServerSession.this) != null)
        ServerSession.access$500(ServerSession.this).accept("connexion"); 
    } catch (UnknownHostException unknownhostexception) {
      unknownhostexception.printStackTrace();
      if (ServerSession.access$000(ServerSession.this))
        return; 
      ServerSession.access$602(ServerSession.this, false);
      ServerSession.access$500(ServerSession.this).accept("failed");
    } catch (Exception exception) {
      exception.printStackTrace();
      if (ServerSession.access$000(ServerSession.this))
        return; 
      String s = exception.toString();
      if (inetaddress != null) {
        String s1 = inetaddress.toString() + ":" + ServerSession.access$300(ServerSession.this);
        s = s.replaceAll(s1, "");
      } 
      ServerSession.access$602(ServerSession.this, false);
      ServerSession.access$500(ServerSession.this).accept("failed");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\mainmen\\utils\connector\ServerSession$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */