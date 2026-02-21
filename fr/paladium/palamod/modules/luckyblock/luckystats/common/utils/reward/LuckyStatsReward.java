package fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward;

import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LuckyStatsReward {
  private final ItemStack item;
  
  private final String cmd;
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public String getCmd() {
    return this.cmd;
  }
  
  public LuckyStatsReward(ItemStack item) {
    this.item = item;
    this.cmd = null;
  }
  
  public LuckyStatsReward(Item item) {
    this(new ItemStack(item));
  }
  
  public LuckyStatsReward(Item item, int amount) {
    this(new ItemStack(item, amount));
  }
  
  public LuckyStatsReward(Item item, int amount, int data) {
    this(new ItemStack(item, amount, data));
  }
  
  public LuckyStatsReward(Block block) {
    this(new ItemStack(block));
  }
  
  public LuckyStatsReward(Block block, int amount) {
    this(new ItemStack(block, amount));
  }
  
  public LuckyStatsReward(Block block, int amount, int data) {
    this(new ItemStack(block, amount, data));
  }
  
  public LuckyStatsReward(String cmd) {
    this.cmd = cmd;
    this.item = null;
  }
  
  public LuckyStatsReward(String keyId, int amount) {
    this("lootbox GiveKey " + keyId + " %username% " + amount);
  }
  
  public boolean isValid(EntityPlayer player) {
    if (this.cmd != null)
      return true; 
    int availableSlotCount = 0;
    for (ItemStack item : player.field_71071_by.field_70462_a) {
      if (item == null)
        availableSlotCount++; 
    } 
    return (availableSlotCount > 0);
  }
  
  public void perform(EntityPlayer player) {
    if (this.item != null) {
      ItemStack copy = this.item.func_77946_l();
      if (copy.func_77973_b() instanceof ILuckyStatsOwnedReward) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("owner", ((ILuckyStatsOwnedReward)copy
            
            .func_77973_b()).getValue(player));
        copy.func_77982_d(compound);
      } 
      player.field_71071_by.func_70441_a(copy);
    } else if (this.cmd != null) {
      String mappedCmd = this.cmd.replace("%username", player.func_70005_c_()).replace("%uuid", UUIDUtils.toString((Entity)player));
      ConsoleUtils.executeCommand(mappedCmd);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\commo\\utils\reward\LuckyStatsReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */