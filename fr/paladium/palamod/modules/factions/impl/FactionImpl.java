package fr.paladium.palamod.modules.factions.impl;

import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionClaim;
import fr.paladium.factions.api.faction.permission.FactionPermission;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.factions.core.modules.onyoumark.listeners.OymListenerCraft;
import fr.paladium.factions.core.modules.onyoumark.listeners.OymListenerCraftPalamachine;
import fr.paladium.factions.core.modules.onyoumark.listeners.OymListenerEnchant;
import fr.paladium.factions.core.modules.onyoumark.listeners.OymListenerGrinder;
import fr.paladium.factions.core.modules.onyoumark.listeners.OymListenerSmelt;
import fr.paladium.factions.core.modules.onyoumark.listeners.OymListenerUseItem;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.modules.factions.dto.leveling.FactionXpChangeReason;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.modules.factions.rabbit.RabbitService;
import fr.paladium.palamod.modules.factions.rabbit.packets.PlayerAddEloPacket;
import fr.paladium.palamod.modules.factions.rabbit.packets.PlayerAddXpPacket;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class FactionImpl implements IFactionImpl {
  private final RabbitService service;
  
  public FactionImpl(RabbitService service) {
    this.service = service;
  }
  
  public void addElo(UUID playerUuid, long amount) {
    (new PlayerAddEloPacket(playerUuid, amount)).send(this.service);
  }
  
  public void addXp(UUID playerUuid, long amount, FactionXpChangeReason reason) {
    (new PlayerAddXpPacket(playerUuid, amount, reason)).send(this.service);
  }
  
  public Tristate hasPermission(EntityPlayer player, String permission, int x, int z) {
    try {
      if (player.field_70170_p.field_72995_K || !(player instanceof EntityPlayerMP))
        return Tristate.UNKNOWN; 
      EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
      Chunk chunk = player.field_70170_p.func_72938_d(x, z);
      IFactionClaim claim = ClaimController.getInstance().getClaim(chunk.field_76635_g, chunk.field_76647_h);
      if (claim == null || claim.isDefaultClaim())
        return Tristate.UNKNOWN; 
      Player player1 = PlayerController.getInstance().getLoadedPlayer(entityPlayerMP.func_110124_au());
      if (player1 == null)
        return Tristate.UNKNOWN; 
      if (player1.isAdminMode())
        return Tristate.TRUE; 
      IFaction fc = FactionController.getInstance().getExistingFactionSync(claim.getFactionUuid());
      if (fc == null)
        return Tristate.UNKNOWN; 
      FactionPermission perm = FactionPermission.getFromName(permission);
      if (perm == null)
        return Tristate.UNKNOWN; 
      if (!claim.hasPermission((IPlayer)player1, perm))
        return Tristate.FALSE; 
      return Tristate.TRUE;
    } catch (Exception e) {
      return Tristate.UNKNOWN;
    } 
  }
  
  public boolean isFactionClaim(World world, int blockX, int blockZ) {
    try {
      Chunk chunk = world.func_72938_d(blockX, blockZ);
      IFactionClaim claim = ClaimController.getInstance().getClaim(chunk.field_76635_g, chunk.field_76647_h);
      if (claim == null || claim.isDefaultClaim() || claim.getFactionUuid() == null || FactionController.getInstance().isBaseFaction(claim.getFactionUuid()))
        return false; 
      IFaction fc = FactionController.getInstance().getExistingFactionSync(claim.getFactionUuid());
      return (fc != null);
    } catch (Exception e) {
      return false;
    } 
  }
  
  public void onItemCraft(EntityPlayer player, ItemStack stack) {
    try {
      OymListenerCraft.onCraft(player, stack);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void onItemSmelt(EntityPlayer player, ItemStack stack) {
    try {
      OymListenerSmelt.onSmelt(player, stack);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void onItemCraftPalamachine(EntityPlayer player, ItemStack stack) {
    try {
      OymListenerCraftPalamachine.onCraft(player, stack);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void onItemEnchant(EntityPlayer player, ItemStack stack) {
    try {
      OymListenerEnchant.onEnchant(player, stack);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void onGrinderCraft(EntityPlayer player, ItemStack stack) {
    try {
      OymListenerGrinder.onCraft(player, stack);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void onGrinderSmelt(EntityPlayer player, ItemStack stack) {
    try {
      OymListenerGrinder.onSmelt(player, stack);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void onItemUse(EntityPlayer player, ItemStack stack, int amount) {
    try {
      OymListenerUseItem.onUse(player, stack, amount);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\impl\FactionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */