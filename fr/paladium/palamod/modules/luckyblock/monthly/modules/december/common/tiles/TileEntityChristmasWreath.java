package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.data.WreathData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityChristmasWreath extends TileEntity {
  public void setTick(int tick) {
    this.tick = tick;
  }
  
  public void setDurability(int durability) {
    this.durability = durability;
  }
  
  public void setNextUseMillis(long nextUseMillis) {
    this.nextUseMillis = nextUseMillis;
  }
  
  public void setAxisAlignedBB(AxisAlignedBB axisAlignedBB) {
    this.axisAlignedBB = axisAlignedBB;
  }
  
  public static final HashSet<WreathData> WREATHS = new HashSet<>();
  
  private static final int RESET_TICK = 0;
  
  private static final int MAX_TICK = 20;
  
  public static final int RADIUS = 50;
  
  public static final int MAX_DURABILITY = 100;
  
  private static final long COOLDOWN = TimeUnit.MINUTES.toMillis(30L);
  
  public static final String DURABILITY_TAG = "durability";
  
  public static final String NEXT_USE_MILLIS_TAG = "nextUseMillis";
  
  public int getTick() {
    return this.tick;
  }
  
  public int getDurability() {
    return this.durability;
  }
  
  public long getNextUseMillis() {
    return this.nextUseMillis;
  }
  
  private int tick = 0;
  
  private int durability = 100;
  
  private long nextUseMillis = 0L;
  
  private AxisAlignedBB axisAlignedBB;
  
  public void func_145845_h() {
    super.func_145845_h();
    this.tick++;
    if (this.field_145850_b.field_72995_K || this.tick < 20)
      return; 
    Location location = new Location(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    this.tick = 0;
    add(location, this);
  }
  
  public void add(Location location, TileEntityChristmasWreath wreath) {
    for (WreathData wreathData : WREATHS) {
      if (wreathData.getLocation().contains(location))
        return; 
    } 
    WREATHS.add(new WreathData(location, wreath));
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.durability = compound.func_74762_e("durability");
    this.nextUseMillis = compound.func_74763_f("nextUseMillis");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("durability", this.durability);
    compound.func_74772_a("nextUseMillis", this.nextUseMillis);
  }
  
  public boolean use() {
    if (isOnCooldown())
      return false; 
    List<EntityPlayerMP> players = getPlayersAround();
    for (EntityPlayerMP player : players) {
      if (!decrementDurability())
        return true; 
      player.func_70690_d(new PotionEffect(PotionsRegister.INVINCIBILITY
            .getPotionId(), 
            MonthlyUtils.translateSecondsToTicks(15), 0));
    } 
    this.nextUseMillis = System.currentTimeMillis() + COOLDOWN;
    return true;
  }
  
  public boolean decrementDurability() {
    this.durability--;
    if (this.durability <= 0) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      return false;
    } 
    return true;
  }
  
  public boolean isOnCooldown() {
    return (this.nextUseMillis > System.currentTimeMillis());
  }
  
  private List<EntityPlayerMP> getPlayersAround() {
    AxisAlignedBB axisAlignedBB = getAxisAlignedBB();
    return this.field_145850_b.func_72872_a(EntityPlayerMP.class, axisAlignedBB);
  }
  
  private AxisAlignedBB getAxisAlignedBB() {
    if (this.axisAlignedBB == null)
      this.axisAlignedBB = AxisAlignedBB.func_72330_a((this.field_145851_c - 50), (this.field_145848_d - 50), (this.field_145849_e - 50), (this.field_145851_c + 50), (this.field_145848_d + 50), (this.field_145849_e + 50)); 
    return this.axisAlignedBB;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\tiles\TileEntityChristmasWreath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */