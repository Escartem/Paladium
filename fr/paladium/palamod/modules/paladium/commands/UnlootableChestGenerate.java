package fr.paladium.palamod.modules.paladium.commands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class UnlootableChestGenerate extends CommandBase {
  public String func_71517_b() {
    return "unlootablechestgenerate";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/unlootablechestgenerate <id>";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP && args.length == 1) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      World world = player.field_70170_p;
      try {
        URL url = new URL("http://zeldown.com/paladium/api/readUnlootableChest.php");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        Map<String, String> arguments = new HashMap<>();
        arguments.put("id", args[0]);
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet())
          sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + 
              URLEncoder.encode(entry.getValue(), "UTF-8")); 
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
          os.write(out);
        } 
        int responseCode = http.getResponseCode();
        if (responseCode == 200) {
          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
          StringBuffer response = new StringBuffer();
          String inputLine;
          while ((inputLine = in.readLine()) != null)
            response.append(inputLine); 
          in.close();
          if (response.toString().equalsIgnoreCase("not_exist")) {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cAucun rapport n'existe avec l'id §b" + args[0]));
            return;
          } 
          Gson gson = new Gson();
          JsonObject json = (JsonObject)gson.fromJson(response.toString(), JsonObject.class);
          int i = 0;
          String[] blocks = json.get("blocks").getAsString().split(",");
          for (int ox = -5; ox < 6; ox++) {
            for (int oy = -5; oy < 6; oy++) {
              for (int oz = -5; oz < 6; oz++) {
                world.func_147449_b((int)player.field_70165_t + ox, (int)player.field_70163_u + oy, (int)player.field_70161_v + oz, 
                    Block.func_149729_e(Integer.valueOf(blocks[i]).intValue()));
                i++;
              } 
            } 
          } 
          player.func_145747_a((IChatComponent)new ChatComponentText("§aRapport généré"));
          player.func_145747_a((IChatComponent)new ChatComponentText("   §7Auteur -> §b" + json
                .get("author").getAsString()));
          player.func_145747_a((IChatComponent)new ChatComponentText("   §7Date -> §b" + json
                .get("date").getAsString()));
          player.func_145747_a((IChatComponent)new ChatComponentText("   §7Position -> §b" + json
                .get("pos").getAsString()));
          player.func_70107_b(player.field_70165_t, player.field_70163_u + 6.0D, player.field_70161_v);
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cUne erreur est survenue §e#ucg.0000"));
          player.func_145747_a((IChatComponent)new ChatComponentText("   §7-> " + responseCode));
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } else if (sender instanceof EntityPlayerMP && args.length == 0) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      player.func_145747_a((IChatComponent)new ChatComponentText(func_71518_a(sender)));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\UnlootableChestGenerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */