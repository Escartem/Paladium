package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.factions.api.callback.DataCallback;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.factions.api.type.FactionRelationshipType;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.factions.core.player.FactionPlayer;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.vanish.VanishPlugin;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.bukkit.Bukkit;

public class ItemAncientArmorFullListener {
  private static final Set<UUID> FLYING_PLAYERS = new HashSet<>();
  
  @SubscribeEvent
  public void onFallDamage(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!ItemAncientArmor.isFull(player) || event.source != DamageSource.field_76379_h)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    final EntityPlayer player = event.player;
    if (!ItemAncientArmor.isFull(player)) {
      if (FLYING_PLAYERS.contains(player.func_110124_au()) && player.field_71075_bZ.field_75101_c) {
        player.field_71075_bZ.field_75101_c = false;
        player.field_71075_bZ.field_75100_b = false;
        ((EntityPlayerMP)player).func_71016_p();
        FLYING_PLAYERS.remove(player.func_110124_au());
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car vous n'avez plus votre armure antique complète."));
      } 
      return;
    } 
    final UUID playerUuid = player.func_110124_au();
    if (!FLYING_PLAYERS.contains(playerUuid) && player.field_71075_bZ.field_75101_c)
      return; 
    Player player1 = PlayerController.getInstance().getLoadedPlayer(player.func_110124_au());
    if (player1 == null) {
      if (FLYING_PLAYERS.contains(playerUuid) && player.field_71075_bZ.field_75101_c) {
        player.field_71075_bZ.field_75101_c = false;
        player.field_71075_bZ.field_75100_b = false;
        ((EntityPlayerMP)player).func_71016_p();
        FLYING_PLAYERS.remove(playerUuid);
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car vous n'avez plus de faction."));
      } 
      return;
    } 
    final FactionPlayer factionPlayer = player1.getFactionPlayer();
    if (factionPlayer == null || factionPlayer.getFaction() == null) {
      if (FLYING_PLAYERS.contains(playerUuid) && player.field_71075_bZ.field_75101_c) {
        player.field_71075_bZ.field_75101_c = false;
        player.field_71075_bZ.field_75100_b = false;
        ((EntityPlayerMP)player).func_71016_p();
        FLYING_PLAYERS.remove(playerUuid);
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car vous n'avez plus de faction."));
      } 
      return;
    } 
    int chunkX = (int)player.field_70165_t >> 4;
    int chunkZ = (int)player.field_70161_v >> 4;
    FactionController.getInstance().getFaction(ClaimController.getInstance().getClaim(chunkX, chunkZ).getFactionUuid(), new DataCallback<IFaction>() {
          public void onCallback(IFaction faction) {
            if (ItemAncientArmorFullListener.FLYING_PLAYERS.contains(playerUuid) && (faction == null || !factionPlayer.getFaction().getUuid().equals(faction.getUuid()) || ItemAncientArmorFullListener.this.hasEnemiesNearby(player))) {
              player.field_71075_bZ.field_75101_c = false;
              player.field_71075_bZ.field_75100_b = false;
              ((EntityPlayerMP)player).func_71016_p();
              ItemAncientArmorFullListener.FLYING_PLAYERS.remove(playerUuid);
              if (faction == null || !factionPlayer.getFaction().getUuid().equals(faction.getUuid())) {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car vous avez quitté le territoire de votre faction."));
              } else {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car des ennemis sont à proximité."));
              } 
            } else if (faction != null && factionPlayer.getFaction().getUuid().equals(faction.getUuid()) && !ItemAncientArmorFullListener.this.hasEnemiesNearby(player) && !ItemAncientArmorFullListener.FLYING_PLAYERS.contains(playerUuid)) {
              player.field_71075_bZ.field_75101_c = true;
              ((EntityPlayerMP)player).func_71016_p();
              ItemAncientArmorFullListener.FLYING_PLAYERS.add(playerUuid);
              player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §aVous pouvez désormais voler grâce à votre armure antique."));
            } 
          }
        });
  }
  
  private boolean hasEnemiesNearby(EntityPlayer player) {
    List<EntityPlayer> players = player.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(player.field_70165_t - 20.0D, player.field_70163_u - 20.0D, player.field_70161_v - 20.0D, player.field_70165_t + 20.0D, player.field_70163_u + 20.0D, player.field_70161_v + 20.0D));
    if (players.isEmpty())
      return false; 
    try {
      players.removeIf(p -> VanishPlugin.getInstance().getVanishManager().isVanished(Bukkit.getPlayer(p.func_110124_au())));
    } catch (Exception|NoClassDefFoundError exception) {}
    Player player1 = PlayerController.getInstance().getLoadedPlayer(player.func_110124_au());
    if (player1 == null)
      return false; 
    FactionPlayer factionPlayer = player1.getFactionPlayer();
    if (factionPlayer == null || factionPlayer.getFaction() == null)
      return false; 
    for (EntityPlayer nearbyPlayer : players) {
      if (nearbyPlayer.func_110124_au().equals(player.func_110124_au()))
        continue; 
      Player player2 = PlayerController.getInstance().getLoadedPlayer(nearbyPlayer.func_110124_au());
      if (player2 == null)
        continue; 
      FactionPlayer factionPlayer1 = player2.getFactionPlayer();
      if (factionPlayer1 == null || factionPlayer1.getFaction() == null || factionPlayer.getFaction().getUuid().equals(factionPlayer1.getFaction().getUuid()))
        continue; 
      if (factionPlayer.getFaction().getRelationshipEntity().hasRelationship(factionPlayer1.getFaction(), FactionRelationshipType.ENEMY))
        return true; 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorFullListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */