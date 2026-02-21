package fr.paladium.palamod.modules.luckyblock.entity.may;

import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.TestForce;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityStrengthStand extends EntityArmourStand {
  private double damageInflicted = 0.0D;
  
  private int duration = 30;
  
  private Long lastTime;
  
  private EntityPlayer lastDamager;
  
  public void setDuration(int duration) {
    this.duration = duration;
  }
  
  private List<StrengthStandReward> rewardList = new ArrayList<>();
  
  public EntityStrengthStand(World world) {
    super(world);
    func_94061_f(true);
    updateNameTag();
  }
  
  public void updateNameTag() {
    func_94058_c("§b§f" + this.duration + " sec - " + String.valueOf((int)Math.round(this.damageInflicted)) + "§4♥");
  }
  
  public void func_70030_z() {
    super.func_70030_z();
    if (!this.field_70170_p.field_72995_K) {
      if (this.lastTime == null)
        this.lastTime = Long.valueOf(System.currentTimeMillis()); 
      if (System.currentTimeMillis() - this.lastTime.longValue() >= 1000L) {
        this.lastTime = Long.valueOf(System.currentTimeMillis());
        this.duration--;
        updateNameTag();
      } 
      if (this.duration <= 0) {
        ItemStack reward = getReward();
        if (reward != null)
          PlayerUtils.dropItemStack(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, reward); 
        func_70106_y();
      } 
    } 
  }
  
  public void func_70645_a(DamageSource source) {
    super.func_70645_a(source);
    if (this.lastDamager != null && !this.field_70170_p.field_72995_K)
      MonthlyUtils.stopHeliosEvent((EntityPlayerMP)this.lastDamager, TestForce.class); 
  }
  
  public void addReward(StrengthStandReward reward) {
    this.rewardList.add(reward);
  }
  
  private ItemStack getReward() {
    for (StrengthStandReward reward : this.rewardList) {
      if (this.damageInflicted >= reward.getMinDamage()) {
        if (reward.maxDamage == null)
          return reward.getReward(); 
        if (this.damageInflicted <= reward.maxDamage.doubleValue())
          return reward.getReward(); 
      } 
    } 
    return null;
  }
  
  public boolean func_70097_a(DamageSource source, float amount) {
    if (!this.field_70170_p.field_72995_K && 
      source.func_76364_f() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)source.func_76364_f();
      if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemSword) {
        this.lastDamager = player;
        this.damageInflicted += amount;
        updateNameTag();
      } 
    } 
    return false;
  }
  
  public static class StrengthStandReward {
    private double minDamage;
    
    private Double maxDamage;
    
    private ItemStack reward;
    
    public StrengthStandReward(double minDamage, Double maxDamage, ItemStack reward) {
      this.minDamage = minDamage;
      this.maxDamage = maxDamage;
      this.reward = reward;
    }
    
    public double getMinDamage() {
      return this.minDamage;
    }
    
    public void setMinDamage(double minDamage) {
      this.minDamage = minDamage;
    }
    
    public Double getMaxDamage() {
      return this.maxDamage;
    }
    
    public void setMaxDamage(Double maxDamage) {
      this.maxDamage = maxDamage;
    }
    
    public ItemStack getReward() {
      return this.reward;
    }
    
    public void setReward(ItemStack reward) {
      this.reward = reward;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\may\EntityStrengthStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */