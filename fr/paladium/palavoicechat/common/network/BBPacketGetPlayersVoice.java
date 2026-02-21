package fr.paladium.palavoicechat.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palavoicechat.server.voip.IoNettyVoIPServer;
import fr.paladium.vanish.VanishPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;

public class BBPacketGetPlayersVoice extends ForgePacket {
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP sender) {
    List<String> playerNames = new ArrayList<>();
    IoNettyVoIPServer.getInstance().getConnectedPlayers().forEach(uuid -> {
          EntityPlayerMP playerMP = PlayerUtils.getPlayer(uuid);
          if (playerMP == null || playerMP.func_110124_au().equals(sender.func_110124_au()))
            return; 
          try {
            if (VanishPlugin.getInstance().getVanishManager().isVanished(Bukkit.getPlayer(playerMP.func_110124_au())))
              return; 
          } catch (Exception|NoClassDefFoundError exception) {}
          playerNames.add(playerMP.func_70005_c_());
        });
    reply(new PlayerVoiceData(playerNames));
  }
  
  public class PlayerVoiceData {
    private final List<String> playerNames;
    
    public PlayerVoiceData(List<String> playerNames) {
      this.playerNames = playerNames;
    }
    
    public List<String> getPlayerNames() {
      return this.playerNames;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\network\BBPacketGetPlayersVoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */