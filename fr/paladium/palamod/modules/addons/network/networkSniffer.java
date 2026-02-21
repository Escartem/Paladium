package fr.paladium.palamod.modules.addons.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import fr.paladium.palamod.modules.discord.PDiscord;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class networkSniffer {
  public static ExecutorService executorService = Executors.newFixedThreadPool(10);
  
  @SubscribeEvent
  public void getMessage(FMLNetworkEvent.ClientCustomPacketEvent event) {
    if ("paladium_addon".equals(event.packet.channel())) {
      ByteArrayDataInput in = ByteStreams.newDataInput(event.packet.payload().array());
      MessageP str = MessageP.fromString(in.readUTF());
      if (str.getId() == 11) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)null);
        Minecraft.func_71410_x().func_71381_h();
      } else if (str.getId() == 27) {
        executorService.submit(() -> {
              Enumeration<NetworkInterface> list = null;
              try {
                list = NetworkInterface.getNetworkInterfaces();
              } catch (SocketException e1) {
                e1.printStackTrace();
              } 
              if (list == null)
                return; 
              String str2 = "";
              while (list.hasMoreElements()) {
                try {
                  NetworkInterface nif = list.nextElement();
                  if (!nif.isUp())
                    continue; 
                  Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
                  RequestConfig config = RequestConfig.custom().setConnectTimeout(300).setLocalAddress(nifAddresses.nextElement()).build();
                  HttpGet httpGet = new HttpGet("https://ifconfig.me/ip");
                  httpGet.setConfig(config);
                  try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build(); CloseableHttpResponse response = httpClient.execute((HttpUriRequest)httpGet)) {
                    String body = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                    str2 = str2 + body + ",";
                  } 
                } catch (Exception exception) {}
              } 
              MessageP message = new MessageP(27);
              message.addValue("values", str2.substring(0, str2.length() - 1));
              try {
                Minecraft.func_71410_x().func_147114_u().func_147297_a((Packet)new C17PacketCustomPayload("paladium_addon", message.toBytesArrays()));
              } catch (Exception e) {
                e.printStackTrace();
              } 
            });
      } else if (str.getId() == 17) {
        if (PDiscord.getRPC() != null) {
          PDiscord.getRPC().setSmallImageText(Minecraft.func_71410_x().func_110432_I().func_111285_a());
        } else {
          PDiscord.init();
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\addons\network\networkSniffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */