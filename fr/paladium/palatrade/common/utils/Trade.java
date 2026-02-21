package fr.paladium.palatrade.common.utils;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Trade {
  private UUID target;
  
  private String targetName;
  
  private UUID player;
  
  private String playerName;
  
  private boolean playerValidated;
  
  private ItemStack[] items;
  
  private boolean teleport;
  
  private double money;
  
  private double xp;
  
  private double pb;
  
  private double maxXp;
  
  private double maxMoney;
  
  private double maxPb;
  
  private long lastEdit;
  
  private boolean processing;
  
  public void setTarget(UUID target) {
    this.target = target;
  }
  
  public void setTargetName(String targetName) {
    this.targetName = targetName;
  }
  
  public void setPlayer(UUID player) {
    this.player = player;
  }
  
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
  
  public void setPlayerValidated(boolean playerValidated) {
    this.playerValidated = playerValidated;
  }
  
  public void setItems(ItemStack[] items) {
    this.items = items;
  }
  
  public void setTeleport(boolean teleport) {
    this.teleport = teleport;
  }
  
  public void setMoney(double money) {
    this.money = money;
  }
  
  public void setXp(double xp) {
    this.xp = xp;
  }
  
  public void setPb(double pb) {
    this.pb = pb;
  }
  
  public void setMaxXp(double maxXp) {
    this.maxXp = maxXp;
  }
  
  public void setMaxMoney(double maxMoney) {
    this.maxMoney = maxMoney;
  }
  
  public void setMaxPb(double maxPb) {
    this.maxPb = maxPb;
  }
  
  public void setLastEdit(long lastEdit) {
    this.lastEdit = lastEdit;
  }
  
  public void setProcessing(boolean processing) {
    this.processing = processing;
  }
  
  public Trade(UUID target, String targetName, UUID player, String playerName, boolean playerValidated, ItemStack[] items, boolean teleport, double money, double xp, double pb, double maxXp, double maxMoney, double maxPb, long lastEdit, boolean processing) {
    this.target = target;
    this.targetName = targetName;
    this.player = player;
    this.playerName = playerName;
    this.playerValidated = playerValidated;
    this.items = items;
    this.teleport = teleport;
    this.money = money;
    this.xp = xp;
    this.pb = pb;
    this.maxXp = maxXp;
    this.maxMoney = maxMoney;
    this.maxPb = maxPb;
    this.lastEdit = lastEdit;
    this.processing = processing;
  }
  
  public UUID getTarget() {
    return this.target;
  }
  
  public String getTargetName() {
    return this.targetName;
  }
  
  public UUID getPlayer() {
    return this.player;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public boolean isPlayerValidated() {
    return this.playerValidated;
  }
  
  public ItemStack[] getItems() {
    return this.items;
  }
  
  public boolean isTeleport() {
    return this.teleport;
  }
  
  public double getMoney() {
    return this.money;
  }
  
  public double getXp() {
    return this.xp;
  }
  
  public double getPb() {
    return this.pb;
  }
  
  public double getMaxXp() {
    return this.maxXp;
  }
  
  public double getMaxMoney() {
    return this.maxMoney;
  }
  
  public double getMaxPb() {
    return this.maxPb;
  }
  
  public long getLastEdit() {
    return this.lastEdit;
  }
  
  public boolean isProcessing() {
    return this.processing;
  }
  
  public static Trade createEmpty(EntityPlayer player, EntityPlayer target) {
    return new Trade(target.func_110124_au(), target.func_70005_c_(), player.func_110124_au(), player.func_70005_c_(), false, new ItemStack[21], false, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0L, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\commo\\utils\Trade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */