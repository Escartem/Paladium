package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palamod.modules.hunter.items.ItemAmulet;
import fr.paladium.palamod.modules.hunter.utils.AmuletHelper;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;

public class SummerAmuletEventHandler {
  private static final float BOX_RADIUS = 3.0F;
  
  @SubscribeEvent
  public void onUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K || !(event.entityLiving instanceof EntityPlayerMP) || event.entityLiving.field_70173_aa % MonthlyUtils.translateSecondsToTicks(3) != 0 || !Server.is(new ServerType[] { ServerType.FACTION }))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
    if (!AmuletHelper.hasAmulet((EntityPlayer)player, ItemAmulet.Type.SUMMER))
      return; 
    for (EntityPlayerMP targetMP : getNearbyPlayers(player))
      targetMP.func_70015_d(MonthlyUtils.translateSecondsToTicks(3)); 
  }
  
  private List<EntityPlayerMP> getNearbyPlayers(EntityPlayerMP playerMP) {
    List<EntityPlayerMP> nearbyPlayers = playerMP.field_70170_p.func_72872_a(EntityPlayerMP.class, playerMP.field_70121_D
        
        .func_72314_b(3.0D, 3.0D, 3.0D));
    nearbyPlayers.removeIf(player -> player.func_70005_c_().equals(playerMP.func_70005_c_()));
    PlayerController controller = PlayerController.getInstance();
    Player player = controller.getLoadedPlayer(playerMP.func_70005_c_());
    if (player == null || !player.hasFaction())
      return nearbyPlayers; 
    IFaction faction = player.getFaction();
    for (IFactionPlayer onlinePlayer : faction.getMemberEntity().getOnlinePlayers()) {
      EntityPlayerMP targetPlayer = onlinePlayer.getPlayer().getFactionPlayer().getPlayer().getPlayer();
      nearbyPlayers.removeIf(entityPlayerMP -> entityPlayerMP.func_70005_c_().equals(targetPlayer.func_70005_c_()));
    } 
    return nearbyPlayers;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\events\SummerAmuletEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */