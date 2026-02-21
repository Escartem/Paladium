package fr.paladium.palaforgeutils.lib.tools;

import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.NonNull;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class HastebinUtils {
  public static void postHaste(@NonNull ICommandSender sender, @NonNull String text) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    try {
      String response;
      if (text.length() >= 300000)
        text = text.substring(0, 300000); 
      byte[] postData = text.getBytes(StandardCharsets.UTF_8);
      int postDataLength = postData.length;
      URL url = new URL(PalaForgeConfigManager.getInstance().getHasteInternalURL() + "documents");
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setDoOutput(true);
      conn.setInstanceFollowRedirects(false);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("User-Agent", "Hastebin Java Api");
      conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
      conn.setUseCaches(false);
      try {
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        response = reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return;
      } 
      if (response != null && response.contains("\"key\""))
        response = response.substring(response.indexOf(":") + 2, response.length() - 2); 
      if (response != null) {
        String hasteUrl = PalaForgeConfigManager.getInstance().getHastePublicURL() + response;
        ChatComponentText chatComponentText1 = new ChatComponentText("§8[§6Paladium§8] §eLien du paste: §7" + hasteUrl);
        ChatComponentText chatComponentText2 = new ChatComponentText("§bCliquez pour ouvrir le lien");
        ChatStyle style = (new ChatStyle()).func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, hasteUrl)).func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2));
        chatComponentText1.func_150255_a(style);
        sender.func_145747_a((IChatComponent)chatComponentText1);
      } else {
        sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue. #1"));
      } 
    } catch (Exception e) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue. #2"));
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\tools\HastebinUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */