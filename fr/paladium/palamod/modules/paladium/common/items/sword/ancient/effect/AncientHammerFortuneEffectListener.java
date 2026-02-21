package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.modules.paladium.client.ui.UIAncientHammerFortuneEffect;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import fr.paladium.zephyrui.internal.mod.utils.command.uuid.UUIDUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AncientHammerFortuneEffectListener {
  public static final Map<UUID, UUID> AFFECTED_FORTUNE_PLAYERS = new HashMap<>();
  
  @SubscribeEvent
  public void onPlayerKill(LivingDeathEvent event) {
    if (!(event.source.func_76364_f() instanceof EntityPlayerMP) || !(event.entity instanceof EntityPlayerMP) || event.entity.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP killer = (EntityPlayerMP)event.source.func_76364_f();
    EntityPlayerMP killed = (EntityPlayerMP)event.entity;
    if (killer == null || killed == null)
      return; 
    ItemAncientHammerPlayerData killerData = ItemAncientHammerPlayerData.get((EntityPlayer)killer);
    if (killerData == null || !killerData.isFortuneEffectActive())
      return; 
    SoundUtils.ANVIL_USE.playSound(killer, killer.field_70165_t, killer.field_70163_u, killer.field_70161_v, 1.5F, 1.0F);
    (new Notification(Notification.NotificationType.SUCCESS, "Vous avez appliqué l'effet de Fortune sur " + killed.func_70005_c_(), "PALADIUM")).send(killer);
    AFFECTED_FORTUNE_PLAYERS.put(killed.func_110124_au(), killer.func_110124_au());
  }
  
  @SubscribeEvent
  public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
    if (event.player.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    UUID playerUUID = player.func_110124_au();
    if (AFFECTED_FORTUNE_PLAYERS.containsKey(playerUUID))
      ZUIServer.open(UIAncientHammerFortuneEffect.class, player); 
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.side == Side.SERVER)
      return; 
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get(event.player);
    if (pData == null || !pData.isFortuneEffectActive())
      return; 
    double time = event.player.field_70173_aa * 0.15D;
    double radius = 0.8D;
    double baseY = event.player.field_70121_D.field_72338_b + 0.5D;
    for (int i = 0; i < 3; i++) {
      double offset = 2.0943951023931953D * i;
      double heightOffset = Math.sin(time * 2.0D + offset) * 0.3D;
      double x = event.player.field_70165_t + Math.cos(time + offset) * 0.8D;
      double y = baseY + heightOffset;
      double z = event.player.field_70161_v + Math.sin(time + offset) * 0.8D;
      event.player.field_70170_p.func_72869_a("reddust", x, y, z, 1.0D, 1.0D, 0.0D);
    } 
  }
  
  @SubscribeEvent
  public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
    if (event.player.field_70170_p.field_72995_K)
      return; 
    UUID uuid = event.player.func_110124_au();
    if (AFFECTED_FORTUNE_PLAYERS.containsKey(uuid))
      playEffect(uuid); 
  }
  
  public void playEffect(UUID playerUUID) {
    EntityPlayerMP player = PlayerUtils.getPlayer(playerUUID);
    if (player == null)
      return; 
    UUID killerUUID = AFFECTED_FORTUNE_PLAYERS.get(playerUUID);
    if (killerUUID == null)
      return; 
    AFFECTED_FORTUNE_PLAYERS.remove(playerUUID);
    (new Thread(() -> {
          double playerMoney = CresusManager.getInstance().getBalance(playerUUID);
          if (playerMoney <= 0.0D)
            return; 
          double removedMoney = Math.min(30000.0D, playerMoney * 0.01D);
          CresusResponse removeResponse = CresusManager.getInstance().withdrawPlayer(playerUUID, removedMoney, "ANCIENT HAMMER FORTUNE EFFECT - KILLED BY " + UUIDUtils.toString(killerUUID));
          if (removeResponse.type != CresusResponse.ResponseType.SUCCESS)
            return; 
          (new Notification(Notification.NotificationType.ERROR, "Vous avez raté le défi, vous perdez " + String.format("%.2f", new Object[] { Double.valueOf(removedMoney) }) + " $", "PALADIUM")).send(player);
          CresusResponse addedResponse = CresusManager.getInstance().depositPlayer(killerUUID, removedMoney, "ANCIENT HAMMER FORTUNE EFFECT - KILL ON " + UUIDUtils.toString(playerUUID));
          if (addedResponse.type != CresusResponse.ResponseType.SUCCESS)
            return; 
          EntityPlayerMP killer = PlayerUtils.getPlayer(killerUUID);
          if (killer == null)
            return; 
          (new Notification(Notification.NotificationType.SUCCESS, "Votre cible a raté le défi, vous gagnez " + String.format("%.2f", new Object[] { Double.valueOf(removedMoney) }) + "$", "PALADIUM")).send(killer);
          SoundUtils.LEVEL_UP.playSound(killer, killer.field_70165_t, killer.field_70163_u, killer.field_70161_v, 1.5F, 1.0F);
        }"AncientHammerFortuneEffectListener/playEffect"))
      
      .start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\effect\AncientHammerFortuneEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */