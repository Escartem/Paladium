package fr.paladium.palamod.modules.trixium.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumClaimReward;
import fr.paladium.palamod.modules.trixium.api.TrixiumAPI;
import fr.paladium.palamod.modules.trixium.config.TrixiumRewardConfig;
import fr.paladium.palamod.modules.trixium.manager.TrixiumManager;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import retrofit2.Response;

public class CSPacketTrixiumClaim implements IMessage {
  private String rewardUUID;
  
  public CSPacketTrixiumClaim() {}
  
  public CSPacketTrixiumClaim(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.rewardUUID = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.rewardUUID);
  }
  
  public static class Handler implements IMessageHandler<CSPacketTrixiumClaim, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketTrixiumClaim message, MessageContext ctx) {
      TrixiumAPI.getExecutor().submit(() -> {
            TrixiumRewardConfig reward = TrixiumManager.getReward(message.rewardUUID);
            if (reward == null)
              return; 
            try {
              Response<Void> response = ApiServices.Http.getTrixiumService().claim(FastUUID.toString((Entity)(ctx.getServerHandler()).field_147369_b), new PlayerTrixiumClaimReward(reward.getUuid())).execute();
              if (response.isSuccessful() && TrixiumManager.performReward(reward, (EntityPlayer)(ctx.getServerHandler()).field_147369_b)) {
                (new Notification(Notification.NotificationType.SUCCESS, "Vous venez de récupérer une récompense", "trixium")).send((ctx.getServerHandler()).field_147369_b);
              } else {
                (new Notification(Notification.NotificationType.ERROR, "Impossible de récupérer votre récompense", "trixium")).send((ctx.getServerHandler()).field_147369_b);
              } 
            } catch (IOException e) {
              e.printStackTrace();
              (new Notification(Notification.NotificationType.ERROR, "Impossible de récupérer votre récompense", "trixium")).send((ctx.getServerHandler()).field_147369_b);
            } 
          });
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\CSPacketTrixiumClaim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */