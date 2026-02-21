package fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector;

import java.util.function.Consumer;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;

public class ServerConnector {
  public static ServerSession connect(ServerData serverData, Consumer<String> callback) {
    ServerAddress serveraddress = ServerAddress.func_78860_a(serverData.field_78845_b);
    return connect(serveraddress.func_78861_a(), serveraddress.func_78864_b(), callback);
  }
  
  public static ServerSession connect(String host, int port, Consumer<String> callback) {
    ServerSession session = new ServerSession(host, port, callback);
    session.connect();
    return session;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\mainmen\\utils\connector\ServerConnector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */