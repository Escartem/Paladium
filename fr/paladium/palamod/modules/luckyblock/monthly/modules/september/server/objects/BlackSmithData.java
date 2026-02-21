package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.PracticeMakesPerfectEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.Interval;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class BlackSmithData {
  public void setPlayerUniqueId(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
  }
  
  public void setCurrentWave(int currentWave) {
    this.currentWave = currentWave;
  }
  
  public void setWaveExpirationMillis(long waveExpirationMillis) {
    this.waveExpirationMillis = waveExpirationMillis;
  }
  
  public void setWaveStarted(boolean waveStarted) {
    this.waveStarted = waveStarted;
  }
  
  public void setChestLocation(DoubleLocation chestLocation) {
    this.chestLocation = chestLocation;
  }
  
  public void setCraftedItems(List<ItemStack> craftedItems) {
    this.craftedItems = craftedItems;
  }
  
  public static final Interval WAVE_INTERVAL = new Interval(1, 3);
  
  public static final String WAVE_START_MESSAGE = "&6Étape %d/%d : &aCommencée &eVous avez %d secondes pour crafter les items de l'étape.";
  
  public static final String CRAFTED_ITEM_MESSAGE = "&aVous avez crafté un item de l'étape %d.";
  
  public static final String SUCCESS_MESSAGE = "&6Étape %d/%d : &avalidée";
  
  private UUID playerUniqueId;
  
  private int currentWave;
  
  private long waveExpirationMillis;
  
  private boolean waveStarted;
  
  private DoubleLocation chestLocation;
  
  private List<ItemStack> craftedItems;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getCurrentWave() {
    return this.currentWave;
  }
  
  public long getWaveExpirationMillis() {
    return this.waveExpirationMillis;
  }
  
  public boolean isWaveStarted() {
    return this.waveStarted;
  }
  
  public DoubleLocation getChestLocation() {
    return this.chestLocation;
  }
  
  public List<ItemStack> getCraftedItems() {
    return this.craftedItems;
  }
  
  public BlackSmithData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.currentWave = WAVE_INTERVAL.getMin();
    this.waveStarted = false;
    this.waveExpirationMillis = -1L;
    this.chestLocation = null;
    this.craftedItems = new ArrayList<>();
  }
  
  public boolean spawnAndFillChest(EntityPlayerMP player) {
    if (this.waveStarted || this.currentWave > WAVE_INTERVAL.getMax())
      return false; 
    BlackSmithStep step = PracticeMakesPerfectEvent.INSTANCE.getStep(this.currentWave);
    if (step == null)
      return false; 
    DoubleLocation chestLocation = new DoubleLocation((Entity)player);
    World world = player.func_130014_f_();
    int x = chestLocation.getBlockX();
    int y = chestLocation.getBlockY();
    int z = chestLocation.getBlockZ();
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return false; 
    this.craftedItems.clear();
    this.chestLocation = chestLocation;
    world.func_147449_b(x, y, z, Blocks.field_150462_ai);
    world.func_147449_b(x, y + 1, z, Blocks.field_150350_a);
    world.func_147449_b(x, y + 1, z, (Block)Blocks.field_150486_ae);
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity instanceof TileEntityChest)
      step.fillChest((TileEntityChest)tileEntity); 
    return true;
  }
  
  public boolean startWave(EntityPlayerMP player) {
    if (this.currentWave > WAVE_INTERVAL.getMax())
      return false; 
    BlackSmithStep step = PracticeMakesPerfectEvent.INSTANCE.getStep(this.currentWave);
    if (step == null)
      return false; 
    this.waveStarted = true;
    this.waveExpirationMillis = System.currentTimeMillis() + step.getDurationMillis();
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&6Étape %d/%d : &aCommencée &eVous avez %d secondes pour crafter les items de l'étape.", new Object[] { Integer.valueOf(this.currentWave), Integer.valueOf(WAVE_INTERVAL.getMax()), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(step.getDurationMillis())) }) });
    MonthlyUtils.sendMessage((EntityPlayer)player, step.getMessages());
    return true;
  }
  
  public boolean finishWave(EntityPlayer player, BlackSmithStep step) {
    if (!this.waveStarted)
      return false; 
    if (step == null)
      return false; 
    this.waveStarted = false;
    this.waveExpirationMillis = -1L;
    this.currentWave++;
    this.craftedItems.clear();
    this.chestLocation = null;
    PracticeMakesPerfectEvent.INSTANCE.cleanBlacklistedItems(player, this);
    step.giveRewards(player);
    if (this.currentWave > WAVE_INTERVAL.getMax()) {
      finishGame(player, step);
      return true;
    } 
    MonthlyUtils.sendMessage(player, new String[] { String.format("&6Étape %d/%d : &avalidée", new Object[] { Integer.valueOf(this.currentWave - 1), Integer.valueOf(WAVE_INTERVAL.getMax()) }), "&eEffectue la commande &6/forgestep &epour continuer l’épreuve du forgeron!" });
    return true;
  }
  
  public void finishGame(EntityPlayer player, BlackSmithStep step) {
    PracticeMakesPerfectEvent.INSTANCE.win(player, this);
  }
  
  public boolean isExpired(long now) {
    if (!this.waveStarted)
      return false; 
    if (this.waveExpirationMillis == -1L)
      return false; 
    return (now >= this.waveExpirationMillis);
  }
  
  public boolean isCorrectCraft(EntityPlayer player, BlackSmithStep step, ItemStack stack) {
    if (stack == null)
      return false; 
    if (step == null)
      return false; 
    return step.isCorrectCraft(stack);
  }
  
  public boolean contains(ItemStack stack) {
    for (ItemStack itemStack : this.craftedItems) {
      if (itemStack.func_77969_a(stack))
        return true; 
    } 
    return false;
  }
  
  public void onCraft(EntityPlayer player, ItemStack stack) {
    BlackSmithStep step = PracticeMakesPerfectEvent.INSTANCE.getStep(this.currentWave);
    if (step == null)
      return; 
    if (!isCorrectCraft(player, step, stack))
      return; 
    if (contains(stack))
      return; 
    this.craftedItems.add(stack);
    MonthlyUtils.sendMessage(player, new String[] { String.format("&aVous avez crafté un item de l'étape %d.", new Object[] { Integer.valueOf(this.currentWave) }) });
    if (this.craftedItems.size() == step.getCraftList().size())
      finishWave(player, step); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\BlackSmithData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */