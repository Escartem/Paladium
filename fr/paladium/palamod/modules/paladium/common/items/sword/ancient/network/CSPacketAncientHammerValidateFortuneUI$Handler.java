package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect.AncientHammerFortuneEffectListener;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

@PacketHandler
public class Handler implements IMessageHandler<CSPacketAncientHammerValidateFortuneUI, IMessage> {
  public IMessage onMessage(CSPacketAncientHammerValidateFortuneUI message, MessageContext ctx) {
    UUID uuid = (ctx.getServerHandler()).field_147369_b.func_110124_au();
    if (CSPacketAncientHammerValidateFortuneUI.access$000(message)) {
      if (AncientHammerFortuneEffectListener.AFFECTED_FORTUNE_PLAYERS.containsKey(uuid)) {
        (new Notification(Notification.NotificationType.SUCCESS, "Vous avez réussi le défi, vous gardez votre money", "PALADIUM")).send((ctx.getServerHandler()).field_147369_b);
        UUID killerUUID = (UUID)AncientHammerFortuneEffectListener.AFFECTED_FORTUNE_PLAYERS.get(uuid);
        EntityPlayerMP killerPlayer = PlayerUtils.getPlayer(killerUUID);
        if (killerPlayer != null) {
          SoundUtils.ANVIL_BREAK.playSound(killerPlayer, killerPlayer.field_70165_t, killerPlayer.field_70163_u, killerPlayer.field_70161_v, 1.5F, 0.5F);
          (new Notification(Notification.NotificationType.ERROR, "Votre cible a réussi le défi, vous gagnez 0 $", "PALADIUM")).send(killerPlayer);
        } 
        AncientHammerFortuneEffectListener.AFFECTED_FORTUNE_PLAYERS.remove(uuid);
      } 
      return null;
    } 
    AncientHammerFortuneEffectListener manager = (AncientHammerFortuneEffectListener)ItemAncientHammer.EFFECT_MAP.get(LegendaryStone.Effect.FORTUNE);
    manager.playEffect(uuid);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\network\CSPacketAncientHammerValidateFortuneUI$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */