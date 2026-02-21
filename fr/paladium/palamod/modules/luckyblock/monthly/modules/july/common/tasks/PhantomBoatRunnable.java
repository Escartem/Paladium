package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks;

import fr.paladium.factions.server.utils.PlayerUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PhantomBoatEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palaschematic.utils.BlockData;
import fr.paladium.palaschematic.utils.BlockPos;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class PhantomBoatRunnable extends TimerTask {
  private static final int MAX_SKELETONS = 5;
  
  private static final int MAX_WITHER_SKELETONS = 2;
  
  private static final String SKELETON_KILLED_MESSAGE = "&eVous avez tué un squelette ! &7(%s/%s)";
  
  public static final String SKELETON_NAME_PREFIX = "§6Matelot";
  
  public static final String KING_SKELETON_NAME_PREFIX = "§cRoi wither squelette";
  
  private final Location location;
  
  private final List<UUID> entityList;
  
  private final UUID playerUniqueId;
  
  private int entityKillCount;
  
  private final long expirationMillis;
  
  private Schematic schematic;
  
  public PhantomBoatRunnable(EntityPlayer player, Location location, Schematic schematic) {
    this.location = location;
    this.playerUniqueId = player.func_110124_au();
    this.schematic = schematic;
    this.entityList = new ArrayList<>();
    this.entityKillCount = 0;
    this.expirationMillis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2L);
    spawnEntities();
  }
  
  public void run() {
    if (isExpired()) {
      stop(false);
      return;
    } 
    if (this.entityKillCount >= getTotalCount())
      stop(true); 
  }
  
  private boolean isExpired() {
    return (System.currentTimeMillis() >= this.expirationMillis);
  }
  
  private void spawnEntities() {
    World world = this.location.getWorld();
    spawnSkeletons(world);
    spawnWitherSkeletons(world);
    spawnKingWither(world);
  }
  
  private void spawnSkeletons(World world) {
    for (int i = 0; i < 5; i++) {
      EntitySkeleton skeleton = new EntitySkeleton(world);
      skeleton.func_70107_b(this.location.getX(), this.location.getY(), this.location.getZ());
      skeleton.func_94058_c("§6Matelot");
      skeleton.func_94061_f(true);
      skeleton.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 2400, 0));
      world.func_72838_d((Entity)skeleton);
      this.entityList.add(skeleton.func_110124_au());
    } 
  }
  
  private void spawnWitherSkeletons(World world) {
    for (int i = 0; i < 2; i++) {
      EntitySkeleton skeleton = new EntitySkeleton(world);
      skeleton.func_70107_b(this.location.getX(), this.location.getY(), this.location.getZ());
      skeleton.func_94058_c("§6Matelot");
      skeleton.func_94061_f(true);
      skeleton.func_82201_a(1);
      world.func_72838_d((Entity)skeleton);
      this.entityList.add(skeleton.func_110124_au());
    } 
  }
  
  private void spawnKingWither(World world) {
    EntitySkeleton skeleton = new EntitySkeleton(world);
    skeleton.func_70107_b(this.location.getX(), this.location.getY(), this.location.getZ());
    skeleton.func_94058_c("§cRoi wither squelette");
    skeleton.func_94061_f(true);
    skeleton.func_82201_a(1);
    skeleton.func_94058_c("§cRoi wither squelette");
    skeleton.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    skeleton.func_70606_j(100.0F);
    ItemStack sword = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    sword.func_77966_a(Enchantment.field_77338_j, 4);
    skeleton.func_70062_b(0, sword);
    world.func_72838_d((Entity)skeleton);
    this.entityList.add(skeleton.func_110124_au());
  }
  
  private EntityPlayer getPlayer() {
    return (EntityPlayer)PlayerUtils.getPlayer(this.playerUniqueId);
  }
  
  public void incrementKillCount() {
    this.entityKillCount++;
    EntityPlayer player = getPlayer();
    if (player == null)
      return; 
    MonthlyUtils.sendMessage(player, new String[] { String.format("&eVous avez tué un squelette ! &7(%s/%s)", new Object[] { Integer.valueOf(this.entityKillCount), Integer.valueOf(getTotalCount()) }) });
  }
  
  public boolean contains(UUID uniqueId) {
    return this.entityList.contains(uniqueId);
  }
  
  public int getTotalCount() {
    return 8;
  }
  
  public void stop(boolean win) {
    cancel();
    EntityPlayer player = getPlayer();
    if (player != null)
      MonthlyUtils.stopHeliosEvent((EntityPlayerMP)player, PhantomBoatEvent.class); 
    if (win) {
      if (player == null)
        return; 
      InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.GHOSTLY_SWORD));
      InventoryUtils.giveOrDropitems(player, new ItemStack(Items.field_151103_aS, 16));
      InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.TOOTH_SKELETON_SKULL));
    } 
    if (this.schematic == null)
      return; 
    World world = this.location.getWorld();
    this.schematic.getBlocks().forEach(data -> {
          BlockPos pos = data.getPos();
          int x = this.location.getBlockX() + pos.getX();
          int y = this.location.getBlockY() + pos.getY();
          int z = this.location.getBlockZ() + pos.getZ();
          this.location.getWorld().func_147468_f(x, y, z);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tasks\PhantomBoatRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */