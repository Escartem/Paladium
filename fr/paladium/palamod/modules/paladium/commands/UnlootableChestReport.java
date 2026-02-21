package fr.paladium.palamod.modules.paladium.commands;

import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.json.JSONArray;

public class UnlootableChestReport extends CommandBase {
  public String func_71517_b() {
    return "unlootablechestreport";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/unlootablechestreport [force:true|false] [radius]";
  }
  
  public boolean checkAroundChest(World world, int x, int y, int z) {
    boolean impillable = true;
    for (int oy = -1; oy < 2; oy++) {
      for (int ox = -1; ox < 2; ox++) {
        for (int oz = -1; oz < 2; oz++) {
          if (ox != 0 || oy != 0 || oz != 0) {
            Block b = world.func_147439_a(x + ox, y + oy, z + oz);
            if (b instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockSpike || b == Blocks.field_150353_l || b == Blocks.field_150356_k)
              return true; 
            if (world.func_147437_c(x + ox, y + oy, z + oz) || (b != Blocks.field_150357_h && b != Blocks.field_150467_bQ))
              impillable = false; 
          } 
        } 
      } 
    } 
    return impillable;
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      BlockPos pos = new BlockPos((Entity)player);
      boolean force = false;
      int radius = 3;
      if (args.length == 1) {
        force = Boolean.valueOf(args[0]).booleanValue();
      } else if (args.length == 2) {
        force = Boolean.valueOf(args[0]).booleanValue();
        radius = Integer.valueOf(args[1]).intValue();
      } 
      for (int ox = -radius; ox < radius + 1; ox++) {
        for (int oy = -radius; oy < radius + 1; oy++) {
          for (int oz = -radius; oz < radius + 1; oz++) {
            if (player.field_70170_p.func_147439_a(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz)
              .hasTileEntity(player.field_70170_p.func_72805_g(pos.getX() + ox, pos.getY() + oy, pos
                  .getZ() + oz)) && player.field_70170_p
              .func_147439_a(pos.getX() + ox, pos.getY() + oy, pos
                .getZ() + oz) != Blocks.field_150467_bQ)
              if (args.length == 0) {
                if (checkAroundChest(player.field_70170_p, pos.getX() + ox, pos.getY() + oy, pos
                    .getZ())) {
                  pos = new BlockPos(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz);
                } else {
                  player.func_145747_a((IChatComponent)new ChatComponentText("§cLe bloc §e" + player.field_70170_p
                        .func_147439_a(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz)
                        .func_149732_F() + " §cen §e" + (pos
                        .getX() + ox) + " " + (pos.getY() + oy) + " " + (pos
                        .getZ() + oz) + " §cn'est pas considéré comme impillable"));
                  player.func_145747_a((IChatComponent)new ChatComponentText("   §7->/unlootablechestreport true " + radius + " §epour forcer le rapport"));
                  return;
                } 
              } else if (force) {
                pos = new BlockPos(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz);
              } else {
                player.func_145747_a((IChatComponent)new ChatComponentText("§cLe bloc §e" + player.field_70170_p
                      .func_147439_a(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz)
                      .func_149732_F() + " §cen §e" + (pos
                      .getX() + ox) + " " + (pos.getY() + oy) + " " + (pos
                      .getZ() + oz) + " §cn'est pas considéré comme impillable"));
                player.func_145747_a((IChatComponent)new ChatComponentText("   §7->/unlootablechestreport true " + radius + " §epour forcer le rapport"));
                return;
              }  
          } 
        } 
      } 
      if (player.field_70170_p.func_147439_a(pos.getX(), pos.getY(), pos.getZ())
        .hasTileEntity(player.field_70170_p.func_72805_g(pos.getX(), pos.getY(), pos.getZ()))) {
        try {
          JSONArray jsArray = new JSONArray();
          int[] blocks = new int[1331];
          int i = 0;
          for (int j = -5; j < 6; j++) {
            for (int oy = -5; oy < 6; oy++) {
              for (int oz = -5; oz < 6; oz++) {
                blocks[i] = Block.func_149682_b(player.field_70170_p
                    .func_147439_a(pos.getX() + j, pos.getY() + oy, pos.getZ() + oz));
                jsArray.put(blocks[i]);
                i++;
              } 
            } 
          } 
          String bks = jsArray.toString();
          bks = bks.replaceAll("\\[(.*?)\\]", "$1");
          JsonObject json = new JsonObject();
          json.addProperty("author", player.func_70005_c_());
          json.addProperty("date", 
              LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + 
              LocalDateTime.now().getYear() + " " + LocalDateTime.now().getHour() + ":" + 
              LocalDateTime.now().getMinute());
          json.addProperty("pos", pos.getX() + " " + pos.getY() + " " + pos.getZ());
          json.addProperty("blocks", bks);
          String content = json.toString();
          URL url = new URL("http://zeldown.com/paladium/api/pastebin.php");
          URLConnection con = url.openConnection();
          HttpURLConnection http = (HttpURLConnection)con;
          http.setRequestMethod("POST");
          http.setDoOutput(true);
          Map<String, String> arguments = new HashMap<>();
          arguments.put("content", content);
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
            player.func_145747_a((IChatComponent)new ChatComponentText("§aLe report à été généré, merci d'envoyer le code suivant à §bZeldown#9963"));
            ChatComponentText chatComponentText = new ChatComponentText("   §7-> " + response.toString());
            ChatStyle style = (new ChatStyle()).func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/unlootablechestgenerate " + response
                  .toString()) {
                  public ClickEvent.Action func_150669_a() {
                    return ClickEvent.Action.SUGGEST_COMMAND;
                  }
                });
            style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§aCopier la commande de génération")));
            chatComponentText.func_150255_a(style);
            player.func_145747_a((IChatComponent)chatComponentText);
          } else {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cUne erreur est survenue §e#ucr.0001"));
            player.func_145747_a((IChatComponent)new ChatComponentText("   §7-> " + responseCode));
          } 
        } catch (Exception e) {
          e.printStackTrace();
          player.func_145747_a((IChatComponent)new ChatComponentText("§cUne erreur est survenue §e#ucr.0000"));
        } 
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cAucun conteneur trouvé"));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\UnlootableChestReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */