package fr.paladium.palapass.common.network.packet.server;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palashop.server.pb.PBManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketPalapassBuyPremium extends ForgePacket {
  @PacketData
  private String rewardUUID;
  
  public CSPacketPalapassBuyPremium() {}
  
  public CSPacketPalapassBuyPremium(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
  
  public void processServer(EntityPlayerMP player) {
    PalapassPlayer playerData = PalapassPlayer.get((EntityPlayer)player);
    if (playerData.isPremium()) {
      PalapassTranslateEnum.ALREADY_OWN_PREMIUM.notificationOrDefault("Vous possédez déjà le Palapass Premium.", player);
      reply(Boolean.valueOf(false));
      return;
    } 
    PBManager.buy(UUIDUtils.toString((Entity)player), 9500L, "Buy Palapass Premium").thenAccept(result -> {
          if (result.booleanValue()) {
            playerData.addPremiumMonth();
            playerData.sync();
            PalapassTranslateEnum.UNLOCKED_PREMIUM_PASS.notificationOrDefault("Vous venez de débloquer le pass premium.", player);
            PalaOracleEvent destroyedPbEvent = new PalaOracleEvent(player.func_110124_au().toString(), "[SHOP] Destroyed PBs");
            destroyedPbEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
            destroyedPbEvent.addField("amount", Integer.valueOf(9500));
            destroyedPbEvent.addField("reason", "BUY");
            destroyedPbEvent.capture();
            PalaOracleEvent buyItemEvent = new PalaOracleEvent(player.func_110124_au().toString(), "[SHOP] Item Buyed");
            buyItemEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
            buyItemEvent.addField("item-id", "palapass-premium");
            buyItemEvent.addField("item-name", "Palapass Premium");
            buyItemEvent.addField("item-price", Integer.valueOf(9500));
            buyItemEvent.capture();
          } 
          reply(result);
        }).exceptionally(e -> {
          e.printStackTrace();
          reply(Boolean.valueOf(false));
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\server\CSPacketPalapassBuyPremium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */