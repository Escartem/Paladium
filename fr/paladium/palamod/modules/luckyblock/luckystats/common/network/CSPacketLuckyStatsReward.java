package fr.paladium.palamod.modules.luckyblock.luckystats.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data.PlayerLuckyStats;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.LuckyStatsError;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsRewardLevel;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CSPacketLuckyStatsReward implements IMessage {
  private int type;
  
  private int state;
  
  public CSPacketLuckyStatsReward() {}
  
  public CSPacketLuckyStatsReward(int type, int state) {
    this.type = type;
    this.state = state;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.type = buf.readInt();
    this.state = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.type);
    buf.writeInt(this.state);
  }
  
  public static class Handler implements IMessageHandler<CSPacketLuckyStatsReward, IMessage> {
    public IMessage onMessage(CSPacketLuckyStatsReward message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if ((LuckyType.values()).length > message.type) {
        LuckyType luckyType = LuckyType.values()[message.type];
        (new PlayerLuckyStats((EntityPlayer)player)).load(stats -> {
              if (((Integer)((List<Integer>)stats.getRewards().get(luckyType)).get(message.state)).intValue() == 0) {
                if ((stats.getStats().keySet().stream().filter(()).toArray()).length >= Math.floor((LuckyEvents.valuesEvents(luckyType).size() / 100.0F * ((message.state + 1) * 25)))) {
                  AtomicReference<LuckyStatsRewardLevel> reward = new AtomicReference<>(LuckyStatsRewardManager.getRewards(luckyType, message.state));
                  for (LuckyStatsRewardLevel r : LuckyStatsRewardManager.getRewards().get(luckyType)) {
                    if (r.getLevel() != message.state)
                      continue; 
                    reward.set(r);
                  } 
                  if (reward.get() == null) {
                    (new Notification(Notification.NotificationType.ERROR, "Impossible de trouver cette récompense", "luckystats")).send(player);
                    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cImpossible de trouver cette récompense."));
                    return;
                  } 
                  if (!((LuckyStatsRewardLevel)reward.get()).isValid((EntityPlayer)player)) {
                    (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas assez de place dans votre inventaire", "luckystats")).send(player);
                    player.func_145747_a((IChatComponent)new ChatComponentText(LuckyStatsError.REWARD_ERROR.getDefaultError()));
                    return;
                  } 
                  stats.setReward(luckyType, message.state, 1);
                  stats.save((), ());
                } 
              } else {
                (new Notification(Notification.NotificationType.SUCCESS, "La récompense a déjà été récupérée", "luckystats")).send(player);
              } 
            }error -> {
              String err = error.getDefaultError();
              if (error == LuckyStatsError.LOADING) {
                (new Notification(Notification.NotificationType.SUCCESS, "La récompense a déjà été récupérée", "luckystats")).send(player);
                err = "§8[§6Paladium§8] §cLa récompense a déjà été récupérée.";
              } 
              player.func_145747_a((IChatComponent)new ChatComponentText(err));
            });
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\common\network\CSPacketLuckyStatsReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */