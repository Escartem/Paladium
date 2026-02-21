package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class MarchPlayer extends ExtendedEntityProperties {
  public void setHasSeenAlien(boolean hasSeenAlien) {
    this.hasSeenAlien = hasSeenAlien;
  }
  
  public void setWarStarTimestamp(long warStarTimestamp) {
    this.warStarTimestamp = warStarTimestamp;
  }
  
  public void setHasUpgradedItems(boolean hasUpgradedItems) {
    this.hasUpgradedItems = hasUpgradedItems;
  }
  
  public void setHasUpgradedBell(boolean hasUpgradedBell) {
    this.hasUpgradedBell = hasUpgradedBell;
  }
  
  public void setHasStoneHearth(boolean hasStoneHearth) {
    this.hasStoneHearth = hasStoneHearth;
  }
  
  public void setFishExpirationMillis(long fishExpirationMillis) {
    this.fishExpirationMillis = fishExpirationMillis;
  }
  
  public void setLastFishMillis(long lastFishMillis) {
    this.lastFishMillis = lastFishMillis;
  }
  
  public boolean isHasSeenAlien() {
    return this.hasSeenAlien;
  }
  
  public long getWarStarTimestamp() {
    return this.warStarTimestamp;
  }
  
  public boolean isHasUpgradedItems() {
    return this.hasUpgradedItems;
  }
  
  public boolean isHasUpgradedBell() {
    return this.hasUpgradedBell;
  }
  
  public boolean isHasStoneHearth() {
    return this.hasStoneHearth;
  }
  
  public long getFishExpirationMillis() {
    return this.fishExpirationMillis;
  }
  
  public long getLastFishMillis() {
    return this.lastFishMillis;
  }
  
  private boolean hasSeenAlien = false;
  
  private long warStarTimestamp = 0L;
  
  private boolean hasUpgradedItems = false;
  
  private boolean hasUpgradedBell = false;
  
  private boolean hasStoneHearth = false;
  
  private long lastFishMillis = 0L;
  
  private long fishExpirationMillis = 0L;
  
  public static final String TAG_NAME = "MarchPlayer";
  
  public static final String PROP_NAME = "palamod_MarchPlayer";
  
  private static final String TAG_HAS_SEEN_ALIEN = "hasSeenAlien";
  
  private static final String WAR_STAR_TIMESTAMP = "warStarTimestamp";
  
  private static final String TAG_HAS_UPGRADED_ITEMS = "hasUpgradedItems";
  
  private static final String TAG_HAS_UPGRADED_BELL = "hasUpgradedBell";
  
  private static final String TAG_HAS_STONE_HEARTH = "hasStoneHearth";
  
  private static final String TAG_LAST_FISH_MILLIS = "lastFishMillis";
  
  private static final String TAG_FISH_EXPIRATION_MILLIS = "fishExpirationMillis";
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74757_a("hasSeenAlien", this.hasSeenAlien);
    nbt.func_74772_a("warStarTimestamp", this.warStarTimestamp);
    nbt.func_74757_a("hasUpgradedItems", this.hasUpgradedItems);
    nbt.func_74757_a("hasUpgradedBell", this.hasUpgradedBell);
    nbt.func_74757_a("hasStoneHearth", this.hasStoneHearth);
    nbt.func_74772_a("lastFishMillis", this.lastFishMillis);
    nbt.func_74772_a("fishExpirationMillis", this.fishExpirationMillis);
    compound.func_74782_a("MarchPlayer", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = compound.func_74775_l("MarchPlayer");
    if (nbt == null)
      return; 
    this.hasSeenAlien = nbt.func_74767_n("hasSeenAlien");
    this.warStarTimestamp = nbt.func_74763_f("warStarTimestamp");
    this.hasUpgradedItems = nbt.func_74767_n("hasUpgradedItems");
    this.hasUpgradedBell = nbt.func_74767_n("hasUpgradedBell");
    this.hasStoneHearth = nbt.func_74767_n("hasStoneHearth");
    this.lastFishMillis = nbt.func_74763_f("lastFishMillis");
    this.fishExpirationMillis = nbt.func_74763_f("fishExpirationMillis");
  }
  
  public static MarchPlayer get(EntityPlayer player) {
    return (MarchPlayer)player.getExtendedProperties("palamod_MarchPlayer");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\data\MarchPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */