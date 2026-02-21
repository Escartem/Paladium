package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;

public class SpamTpaHere extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    try {
      List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
      List<EntityPlayerMP> filteredPlayers = (List<EntityPlayerMP>)players.stream().filter(mp -> (player != null && !mp.func_110124_au().equals(player.func_110124_au()))).collect(Collectors.toList());
      filteredPlayers.forEach(playerRecever -> {
            player.func_145747_a((IChatComponent)new ChatComponentText("/tpahere " + playerRecever.getDisplayName()));
            player.func_145747_a((IChatComponent)new ChatComponentText("§6Vous avez envoyé une demande de téleportation sur vous à §e" + playerRecever.getDisplayName()));
            Bukkit.getPlayer(player.func_70005_c_()).performCommand("tpahere " + playerRecever.getDisplayName());
          });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public String getName() {
    return "Tp All";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "tpall";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SpamTpaHere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */