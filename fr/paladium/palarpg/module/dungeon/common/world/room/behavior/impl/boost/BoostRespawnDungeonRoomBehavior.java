package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palarpg.module.dungeon.common.network.death.BBPacketRPGDungeonDeathRespawnTeam;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGItemsCache;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import java.util.Random;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BoostRespawnDungeonRoomBehavior extends DungeonRoomBehavior {
  private final Item item;
  
  public BoostRespawnDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.item = RPGItemsCache.getAllItems().stream().filter(item -> ((Boolean)item.getTag("respawn").orElse(Boolean.valueOf(false))).booleanValue()).findAny().orElse(null);
  }
  
  public void onPaste(@NonNull Block block, @NonNull BlockPos position) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
  }
  
  public void onLoad() {}
  
  public void onReset() {}
  
  public void onRemove() {}
  
  public void onJoin() {
    if (getRoom().isFinished())
      return; 
    DungeonRoom room = getRoom();
    DungeonWorld world = room.getWorld();
    room.setFinished(true);
    if (world.getOnlinePlayers().size() > 1) {
      if (world.getAlivePlayers().size() == world.getOnlinePlayers().size() && this.item != null) {
        EntityPlayer player = world.getOnlinePlayers().get((new Random()).nextInt(world.getOnlinePlayers().size()));
        InventoryUtils.giveOrDropitems(player, new ItemStack(this.item));
        for (EntityPlayer onlinePlayer : world.getOnlinePlayers()) {
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §d§lVotre §d§léquipe §d§lavez §d§lreçu §d§lun §d§ltotem §d§l!"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Le §7joueur §b" + player.func_70005_c_() + " §7a §7reçu §bx1 " + ((IRPGItem)IRPGItem.get(this.item).get()).getItemData().getTranslation("en") + "§7."));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
        } 
      } else {
        for (DungeonPlayer dungeonPlayer : world.getPlayers()) {
          if (dungeonPlayer.isAlive())
            continue; 
          EntityPlayer onlinePlayer = dungeonPlayer.getOnlinePlayer().orElse(null);
          if (onlinePlayer == null)
            continue; 
          room.teleport(onlinePlayer);
          dungeonPlayer.respawn();
          (new BBPacketRPGDungeonDeathRespawnTeam()).send((EntityPlayerMP)onlinePlayer);
        } 
        for (EntityPlayer onlinePlayer : world.getOnlinePlayers()) {
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §d§lVotre §d§léquipe §d§la §d§lété §d§lréanimée §d§l!"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Vous §7êtes §7entré §7dans §7une §7salle §7de §7réapparition."));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
          onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
        } 
      } 
    } else if (!world.getOnlinePlayers().isEmpty()) {
      EntityPlayer player = world.getOnlinePlayers().get(0);
      InventoryUtils.giveOrDropitems(player, new ItemStack(this.item));
      player.func_145747_a((IChatComponent)new ChatComponentText(""));
      player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §d§lVous §d§lavez §d§lreçu §d§lun §d§ltotem §d§l!"));
      player.func_145747_a((IChatComponent)new ChatComponentText(""));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Vous §7avez §7reçu §bx1 " + ((IRPGItem)IRPGItem.get(this.item).get()).getItemData().getTranslation("en") + "§7."));
      player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
    } 
  }
  
  public void onLeave() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\boost\BoostRespawnDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */