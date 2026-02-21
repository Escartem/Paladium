package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.managers;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class AugustTreasureChestManager {
  private static final String CHEST_ALREADY_OPENED_MESSAGE = "&cVous avez déjà ouvert ce coffre !";
  
  private static final String CHEST_OPENED_MESSAGE = "&aVous avez ouvert le coffre de pirate!";
  
  private static AugustTreasureChestManager instance;
  
  private AugustConfigManager configManager;
  
  private ItemStack[] rewards;
  
  public AugustTreasureChestManager() {
    instance = this;
    this.configManager = AugustConfigManager.getInstance();
    this.rewards = new ItemStack[] { new ItemStack(BlocksRegister.BLOCK_TITANE, 8), new ItemStack(BlocksRegister.BLOCK_PALADIUM, 5), new ItemStack(Blocks.field_150340_R, 15), new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 2) };
  }
  
  public boolean canOpenTreasureChest(EntityPlayerMP player, PlayerLuckyBlockProperties properties, DoubleLocation location) {
    if (properties == null)
      return false; 
    DoubleLocation chestLocation = getTreasureChestLocation();
    if (chestLocation == null)
      return false; 
    if (!isSameBlockLocation(location, chestLocation))
      return false; 
    return true;
  }
  
  public boolean openChest(EntityPlayerMP player, DoubleLocation location) {
    if (!isOnCorrectServer())
      return false; 
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (!canOpenTreasureChest(player, properties, location))
      return false; 
    if (hasAlreadyOpenedTreasureChest(properties)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous avez déjà ouvert ce coffre !" });
      return true;
    } 
    for (ItemStack stack : this.rewards)
      ItemUtils.spawnItemAtEntity((Entity)player, stack.func_77946_l()); 
    properties.setAugustChestOpened(true);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez ouvert le coffre de pirate!" });
    SoundUtils.CHEST_OPEN.playSound(player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    return true;
  }
  
  public boolean hasAlreadyOpenedTreasureChest(PlayerLuckyBlockProperties properties) {
    return properties.isAugustChestOpened();
  }
  
  public boolean isOnCorrectServer() {
    String currentServerName = CommonModule.getInstance().getConfig().getServerName();
    return currentServerName.equalsIgnoreCase("EVENT");
  }
  
  public DoubleLocation getTreasureChestLocation() {
    return this.configManager.getConfig().getAugustChestLocation();
  }
  
  private boolean isSameBlockLocation(DoubleLocation first, DoubleLocation second) {
    return (first.getBlockX() == second.getBlockX() && first.getBlockY() == second.getBlockY() && first.getBlockZ() == second.getBlockZ());
  }
  
  public static AugustTreasureChestManager getInstance() {
    if (instance == null)
      instance = new AugustTreasureChestManager(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\managers\AugustTreasureChestManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */