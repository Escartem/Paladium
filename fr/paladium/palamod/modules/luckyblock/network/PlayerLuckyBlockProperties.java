package fr.paladium.palamod.modules.luckyblock.network;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerLuckyBlockProperties implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_LB";
  
  private EntityPlayer entity;
  
  public void setEntity(EntityPlayer entity) {
    this.entity = entity;
  }
  
  public void setCurrentChance(int currentChance) {
    this.currentChance = currentChance;
  }
  
  public void setSuperman(long superman) {
    this.superman = superman;
  }
  
  public void setLoadingLuckyStats(boolean loadingLuckyStats) {
    this.loadingLuckyStats = loadingLuckyStats;
  }
  
  public void setLuckyStatsRefund(List<Instant> luckyStatsRefund) {
    this.luckyStatsRefund = luckyStatsRefund;
  }
  
  public void setLastHalloweenTradeUUID(String lastHalloweenTradeUUID) {
    this.lastHalloweenTradeUUID = lastHalloweenTradeUUID;
  }
  
  public void setHalloweenTradeLimit(int halloweenTradeLimit) {
    this.halloweenTradeLimit = halloweenTradeLimit;
  }
  
  public void setHasBuyHalloweenCosmetic(boolean hasBuyHalloweenCosmetic) {
    this.hasBuyHalloweenCosmetic = hasBuyHalloweenCosmetic;
  }
  
  public void setLaborDayActive(boolean laborDayActive) {
    this.laborDayActive = laborDayActive;
  }
  
  public void setLaborDayDate(long laborDayDate) {
    this.laborDayDate = laborDayDate;
  }
  
  public void setEndHungerActive(boolean endHungerActive) {
    this.endHungerActive = endHungerActive;
  }
  
  public void setEndHungerDate(long endHungerDate) {
    this.endHungerDate = endHungerDate;
  }
  
  public void setNotShabbyActive(boolean notShabbyActive) {
    this.notShabbyActive = notShabbyActive;
  }
  
  public void setNotShabbyNumber(int notShabbyNumber) {
    this.notShabbyNumber = notShabbyNumber;
  }
  
  public void setNotShabbyStartDate(long notShabbyStartDate) {
    this.notShabbyStartDate = notShabbyStartDate;
  }
  
  public void setNotShabbyMalusActive(boolean notShabbyMalusActive) {
    this.notShabbyMalusActive = notShabbyMalusActive;
  }
  
  public void setNotShabbyMalusStartDate(long notShabbyMalusStartDate) {
    this.notShabbyMalusStartDate = notShabbyMalusStartDate;
  }
  
  public void setCreepyStartDate(long creepyStartDate) {
    this.creepyStartDate = creepyStartDate;
  }
  
  public void setCreepySpawnDate(long creepySpawnDate) {
    this.creepySpawnDate = creepySpawnDate;
  }
  
  public void setBlacksmithDate(long blacksmithDate) {
    this.blacksmithDate = blacksmithDate;
  }
  
  public void setBlacksmithStep(int blacksmithStep) {
    this.blacksmithStep = blacksmithStep;
  }
  
  public void setBlacksmithSwordTooBigPoint(int blacksmithSwordTooBigPoint) {
    this.blacksmithSwordTooBigPoint = blacksmithSwordTooBigPoint;
  }
  
  public void setBlacksmithIronSkullHammerPoint(int blacksmithIronSkullHammerPoint) {
    this.blacksmithIronSkullHammerPoint = blacksmithIronSkullHammerPoint;
  }
  
  public void setBlacksmithRunicAxePoint(int blacksmithRunicAxePoint) {
    this.blacksmithRunicAxePoint = blacksmithRunicAxePoint;
  }
  
  public void setBlacksmithEnergeticBowSwordPoint(int blacksmithEnergeticBowSwordPoint) {
    this.blacksmithEnergeticBowSwordPoint = blacksmithEnergeticBowSwordPoint;
  }
  
  public void setBlacksmithSitarPoint(int blacksmithSitarPoint) {
    this.blacksmithSitarPoint = blacksmithSitarPoint;
  }
  
  public void setBlacksmithSwordGuitarApocalypsePoint(int blacksmithSwordGuitarApocalypsePoint) {
    this.blacksmithSwordGuitarApocalypsePoint = blacksmithSwordGuitarApocalypsePoint;
  }
  
  public void setBlacksmithSwordPistolPoint(int blacksmithSwordPistolPoint) {
    this.blacksmithSwordPistolPoint = blacksmithSwordPistolPoint;
  }
  
  public void setBlacksmithBlunderbussPoint(int blacksmithBlunderbussPoint) {
    this.blacksmithBlunderbussPoint = blacksmithBlunderbussPoint;
  }
  
  public void setQuackDate(long quackDate) {
    this.quackDate = quackDate;
  }
  
  public void setChildTrumpetStartDate(long childTrumpetStartDate) {
    this.childTrumpetStartDate = childTrumpetStartDate;
  }
  
  public void setChildTrumpetSpawnDate(long childTrumpetSpawnDate) {
    this.childTrumpetSpawnDate = childTrumpetSpawnDate;
  }
  
  public void setAdmindanseAllowed(boolean admindanseAllowed) {
    this.admindanseAllowed = admindanseAllowed;
  }
  
  public void setPirateChestOpened(boolean pirateChestOpened) {
    this.pirateChestOpened = pirateChestOpened;
  }
  
  public void setStormExpirationMillis(long stormExpirationMillis) {
    this.stormExpirationMillis = stormExpirationMillis;
  }
  
  public void setSummerBodyExpirationMillis(long summerBodyExpirationMillis) {
    this.summerBodyExpirationMillis = summerBodyExpirationMillis;
  }
  
  public void setOwnCrab(boolean ownCrab) {
    this.ownCrab = ownCrab;
  }
  
  public void setAugustChestOpened(boolean augustChestOpened) {
    this.augustChestOpened = augustChestOpened;
  }
  
  public void setSummerTeleportation(boolean summerTeleportation) {
    this.summerTeleportation = summerTeleportation;
  }
  
  public void setMonkExpirationMillis(long monkExpirationMillis) {
    this.monkExpirationMillis = monkExpirationMillis;
  }
  
  public void setMonkType(int monkType) {
    this.monkType = monkType;
  }
  
  public void setPrime(boolean prime) {
    this.prime = prime;
  }
  
  public void setLuckyExperienceStarted(boolean luckyExperienceStarted) {
    this.luckyExperienceStarted = luckyExperienceStarted;
  }
  
  public void setLuckyExperienceAmount(int luckyExperienceAmount) {
    this.luckyExperienceAmount = luckyExperienceAmount;
  }
  
  public void setOwnTaupiko(boolean ownTaupiko) {
    this.ownTaupiko = ownTaupiko;
  }
  
  public void setOwnHolidayCommand(boolean ownHolidayCommand) {
    this.ownHolidayCommand = ownHolidayCommand;
  }
  
  public void setOwnSlayer(boolean ownSlayer) {
    this.ownSlayer = ownSlayer;
  }
  
  public EntityPlayer getEntity() {
    return this.entity;
  }
  
  private int currentChance = 1;
  
  public int getCurrentChance() {
    return this.currentChance;
  }
  
  private long superman = 0L;
  
  public long getSuperman() {
    return this.superman;
  }
  
  private boolean loadingLuckyStats = false;
  
  public boolean isLoadingLuckyStats() {
    return this.loadingLuckyStats;
  }
  
  private List<Instant> luckyStatsRefund = new ArrayList<>();
  
  private String lastHalloweenTradeUUID;
  
  public List<Instant> getLuckyStatsRefund() {
    return this.luckyStatsRefund;
  }
  
  public String getLastHalloweenTradeUUID() {
    return this.lastHalloweenTradeUUID;
  }
  
  private int halloweenTradeLimit = 0;
  
  public int getHalloweenTradeLimit() {
    return this.halloweenTradeLimit;
  }
  
  private boolean hasBuyHalloweenCosmetic = false;
  
  public boolean isHasBuyHalloweenCosmetic() {
    return this.hasBuyHalloweenCosmetic;
  }
  
  private boolean laborDayActive = false;
  
  public boolean isLaborDayActive() {
    return this.laborDayActive;
  }
  
  private long laborDayDate = 0L;
  
  public long getLaborDayDate() {
    return this.laborDayDate;
  }
  
  private boolean endHungerActive = false;
  
  public boolean isEndHungerActive() {
    return this.endHungerActive;
  }
  
  private long endHungerDate = 0L;
  
  public long getEndHungerDate() {
    return this.endHungerDate;
  }
  
  private boolean notShabbyActive = false;
  
  public boolean isNotShabbyActive() {
    return this.notShabbyActive;
  }
  
  private int notShabbyNumber = 0;
  
  public int getNotShabbyNumber() {
    return this.notShabbyNumber;
  }
  
  private long notShabbyStartDate = 0L;
  
  public long getNotShabbyStartDate() {
    return this.notShabbyStartDate;
  }
  
  private boolean notShabbyMalusActive = false;
  
  public boolean isNotShabbyMalusActive() {
    return this.notShabbyMalusActive;
  }
  
  private long notShabbyMalusStartDate = 0L;
  
  public long getNotShabbyMalusStartDate() {
    return this.notShabbyMalusStartDate;
  }
  
  private long creepyStartDate = 0L;
  
  public long getCreepyStartDate() {
    return this.creepyStartDate;
  }
  
  private long creepySpawnDate = 0L;
  
  public long getCreepySpawnDate() {
    return this.creepySpawnDate;
  }
  
  private long blacksmithDate = 0L;
  
  public long getBlacksmithDate() {
    return this.blacksmithDate;
  }
  
  private int blacksmithStep = 1;
  
  public int getBlacksmithStep() {
    return this.blacksmithStep;
  }
  
  private int blacksmithSwordTooBigPoint = 0;
  
  public int getBlacksmithSwordTooBigPoint() {
    return this.blacksmithSwordTooBigPoint;
  }
  
  private int blacksmithIronSkullHammerPoint = 0;
  
  public int getBlacksmithIronSkullHammerPoint() {
    return this.blacksmithIronSkullHammerPoint;
  }
  
  private int blacksmithRunicAxePoint = 0;
  
  public int getBlacksmithRunicAxePoint() {
    return this.blacksmithRunicAxePoint;
  }
  
  private int blacksmithEnergeticBowSwordPoint = 0;
  
  public int getBlacksmithEnergeticBowSwordPoint() {
    return this.blacksmithEnergeticBowSwordPoint;
  }
  
  private int blacksmithSitarPoint = 0;
  
  public int getBlacksmithSitarPoint() {
    return this.blacksmithSitarPoint;
  }
  
  private int blacksmithSwordGuitarApocalypsePoint = 0;
  
  public int getBlacksmithSwordGuitarApocalypsePoint() {
    return this.blacksmithSwordGuitarApocalypsePoint;
  }
  
  private int blacksmithSwordPistolPoint = 0;
  
  public int getBlacksmithSwordPistolPoint() {
    return this.blacksmithSwordPistolPoint;
  }
  
  private int blacksmithBlunderbussPoint = 0;
  
  public int getBlacksmithBlunderbussPoint() {
    return this.blacksmithBlunderbussPoint;
  }
  
  private long quackDate = 0L;
  
  public long getQuackDate() {
    return this.quackDate;
  }
  
  private long childTrumpetStartDate = 0L;
  
  public long getChildTrumpetStartDate() {
    return this.childTrumpetStartDate;
  }
  
  private long childTrumpetSpawnDate = 0L;
  
  public long getChildTrumpetSpawnDate() {
    return this.childTrumpetSpawnDate;
  }
  
  private boolean admindanseAllowed = true;
  
  public boolean isAdmindanseAllowed() {
    return this.admindanseAllowed;
  }
  
  private boolean pirateChestOpened = false;
  
  private long stormExpirationMillis;
  
  private long summerBodyExpirationMillis;
  
  private boolean ownCrab;
  
  private boolean augustChestOpened;
  
  private boolean summerTeleportation;
  
  private long monkExpirationMillis;
  
  private int monkType;
  
  private boolean prime;
  
  private boolean luckyExperienceStarted;
  
  private int luckyExperienceAmount;
  
  private boolean ownTaupiko;
  
  private boolean ownHolidayCommand;
  
  private boolean ownSlayer;
  
  public boolean isPirateChestOpened() {
    return this.pirateChestOpened;
  }
  
  public long getStormExpirationMillis() {
    return this.stormExpirationMillis;
  }
  
  public long getSummerBodyExpirationMillis() {
    return this.summerBodyExpirationMillis;
  }
  
  public boolean isOwnCrab() {
    return this.ownCrab;
  }
  
  public boolean isAugustChestOpened() {
    return this.augustChestOpened;
  }
  
  public boolean isSummerTeleportation() {
    return this.summerTeleportation;
  }
  
  public long getMonkExpirationMillis() {
    return this.monkExpirationMillis;
  }
  
  public int getMonkType() {
    return this.monkType;
  }
  
  public boolean isPrime() {
    return this.prime;
  }
  
  public boolean isLuckyExperienceStarted() {
    return this.luckyExperienceStarted;
  }
  
  public int getLuckyExperienceAmount() {
    return this.luckyExperienceAmount;
  }
  
  public boolean isOwnTaupiko() {
    return this.ownTaupiko;
  }
  
  public boolean isOwnHolidayCommand() {
    return this.ownHolidayCommand;
  }
  
  public boolean isOwnSlayer() {
    return this.ownSlayer;
  }
  
  public PlayerLuckyBlockProperties(EntityPlayer player) {
    this.entity = player;
    for (int i = 0; i < 4; i++)
      this.luckyStatsRefund.add(null); 
    this.stormExpirationMillis = 0L;
    this.summerBodyExpirationMillis = 0L;
    this.ownCrab = false;
    this.augustChestOpened = false;
    this.summerTeleportation = false;
    this.monkExpirationMillis = 0L;
    this.monkType = 0;
    this.prime = false;
    this.luckyExperienceStarted = false;
    this.luckyExperienceAmount = 0;
    this.ownTaupiko = false;
    this.ownHolidayCommand = false;
    this.ownSlayer = false;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74768_a("currentChance", this.currentChance);
    compound.func_74772_a("superman", this.superman);
    compound.func_74757_a("loadingLuckyStats", this.loadingLuckyStats);
    NBTTagList luckyStatsRefund = new NBTTagList();
    for (int i = 0; i < 4; i++) {
      if (this.luckyStatsRefund.size() > i) {
        if (this.luckyStatsRefund.get(i) != null) {
          luckyStatsRefund.func_74742_a((NBTBase)new NBTTagLong(((Instant)this.luckyStatsRefund.get(i)).toEpochMilli()));
        } else {
          luckyStatsRefund.func_74742_a((NBTBase)new NBTTagLong(-1L));
        } 
      } else {
        luckyStatsRefund.func_74742_a((NBTBase)new NBTTagLong(-1L));
      } 
    } 
    compound.func_74782_a("luckyStatsRefund", (NBTBase)luckyStatsRefund);
    if (this.lastHalloweenTradeUUID != null) {
      compound.func_74778_a("lastHalloweenTradeUUID", this.lastHalloweenTradeUUID);
      compound.func_74768_a("halloweenTradeLimit", this.halloweenTradeLimit);
    } 
    compound.func_74757_a("hasBuyHalloweenCosmetic", this.hasBuyHalloweenCosmetic);
    compound.func_74757_a("laborDayActive", this.laborDayActive);
    compound.func_74772_a("laborDayDate", this.laborDayDate);
    compound.func_74757_a("endHungerActive", this.endHungerActive);
    compound.func_74772_a("endHungerDate", this.endHungerDate);
    compound.func_74757_a("notShabbyActive", this.notShabbyActive);
    compound.func_74768_a("notShabbyNumber", this.notShabbyNumber);
    compound.func_74772_a("notShabbyStartDate", this.notShabbyStartDate);
    compound.func_74757_a("notShabbyMalusActive", this.notShabbyMalusActive);
    compound.func_74772_a("notShabbyMalusStartDate", this.notShabbyMalusStartDate);
    compound.func_74772_a("creepyStartDate", this.creepyStartDate);
    compound.func_74772_a("creepySpawnDate", this.creepySpawnDate);
    compound.func_74772_a("blacksmithDate", this.blacksmithDate);
    compound.func_74768_a("blacksmithStep", this.blacksmithStep);
    compound.func_74768_a("blacksmithSwordTooBigPoint", this.blacksmithSwordTooBigPoint);
    compound.func_74768_a("blacksmithIronSkullHammerPoint", this.blacksmithIronSkullHammerPoint);
    compound.func_74768_a("blacksmithRunicAxePoint", this.blacksmithRunicAxePoint);
    compound.func_74768_a("blacksmithEnergeticBowSwordPoint", this.blacksmithEnergeticBowSwordPoint);
    compound.func_74768_a("blacksmithSitarPoint", this.blacksmithSitarPoint);
    compound.func_74768_a("blacksmithSwordGuitarApocalypsePoint", this.blacksmithSwordGuitarApocalypsePoint);
    compound.func_74768_a("blacksmithSwordPistolPoint", this.blacksmithSwordPistolPoint);
    compound.func_74768_a("blacksmithBlunderbussPoint", this.blacksmithBlunderbussPoint);
    compound.func_74772_a("quackDate", this.quackDate);
    compound.func_74772_a("childTrumpetStartDate", this.childTrumpetStartDate);
    compound.func_74772_a("childTrumpetSpawnDate", this.childTrumpetSpawnDate);
    compound.func_74757_a("admindanseAllowed", this.admindanseAllowed);
    compound.func_74757_a("pirateChestOpened", this.pirateChestOpened);
    compound.func_74772_a("stormExpirationMillis", this.stormExpirationMillis);
    compound.func_74772_a("summerBodyExpirationMillis", this.summerBodyExpirationMillis);
    compound.func_74757_a("ownAnimal", this.ownCrab);
    compound.func_74757_a("augustChestOpened", this.augustChestOpened);
    compound.func_74757_a("summerTeleportation", this.summerTeleportation);
    compound.func_74772_a("monkExpirationMillis", this.monkExpirationMillis);
    compound.func_74768_a("monkType", this.monkType);
    compound.func_74757_a("prime", this.prime);
    compound.func_74757_a("luckyExperienceStarted", this.luckyExperienceStarted);
    compound.func_74768_a("luckyExperienceAmount", this.luckyExperienceAmount);
    compound.func_74757_a("ownTaupiko", this.ownTaupiko);
    compound.func_74757_a("ownHolidayCommand", this.ownHolidayCommand);
    compound.func_74757_a("ownSlaver", this.ownSlayer);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("currentChance"))
      this.currentChance = compound.func_74762_e("currentChance"); 
    if (compound.func_74764_b("superman"))
      this.superman = compound.func_74763_f("superman"); 
    if (compound.func_74764_b("loadingLuckyStats"))
      this.loadingLuckyStats = compound.func_74767_n("loadingLuckyStats"); 
    if (compound.func_74764_b("luckyStatsRefund")) {
      NBTTagList list = compound.func_150295_c("luckyStatsRefund", 4);
      for (int i = 0; i < 4; i++) {
        if (list.func_150307_f(i).equalsIgnoreCase("-1L")) {
          this.luckyStatsRefund.add(null);
        } else {
          long l = Long.valueOf(list.func_150307_f(i).replaceAll("L", "")).longValue();
          this.luckyStatsRefund.add(Instant.ofEpochMilli(l));
        } 
      } 
    } 
    if (compound.func_74764_b("lastHalloweenTradeUUID")) {
      this.lastHalloweenTradeUUID = compound.func_74779_i("lastHalloweenTradeUUID");
      this.halloweenTradeLimit = compound.func_74762_e("halloweenTradeLimit");
    } 
    if (compound.func_74764_b("hasBuyHalloweenCosmetic"))
      this.hasBuyHalloweenCosmetic = compound.func_74767_n("hasBuyHalloweenCosmetic"); 
    if (compound.func_74764_b("laborDayActive"))
      this.laborDayActive = compound.func_74767_n("laborDayActive"); 
    if (compound.func_74764_b("laborDayDate"))
      this.laborDayDate = compound.func_74763_f("laborDayDate"); 
    if (compound.func_74764_b("endHungerActive"))
      this.endHungerActive = compound.func_74767_n("endHungerActive"); 
    if (compound.func_74764_b("endHungerDate"))
      this.endHungerDate = compound.func_74763_f("endHungerDate"); 
    if (compound.func_74764_b("notShabbyActive"))
      this.notShabbyActive = compound.func_74767_n("notShabbyActive"); 
    if (compound.func_74764_b("notShabbyNumber"))
      this.notShabbyNumber = compound.func_74762_e("notShabbyNumber"); 
    if (compound.func_74764_b("notShabbyStartDate"))
      this.notShabbyStartDate = compound.func_74763_f("notShabbyStartDate"); 
    if (compound.func_74764_b("notShabbyMalusActive"))
      this.notShabbyMalusActive = compound.func_74767_n("notShabbyMalusActive"); 
    if (compound.func_74764_b("notShabbyMalusStartDate"))
      this.notShabbyMalusStartDate = compound.func_74763_f("notShabbyMalusStartDate"); 
    if (compound.func_74764_b("creepyStartDate"))
      this.creepyStartDate = compound.func_74763_f("creepyStartDate"); 
    if (compound.func_74764_b("creepySpawnDate"))
      this.creepySpawnDate = compound.func_74763_f("creepySpawnDate"); 
    if (compound.func_74764_b("blacksmithDate"))
      this.blacksmithDate = compound.func_74763_f("blacksmithDate"); 
    if (compound.func_74764_b("blacksmithStep"))
      this.blacksmithStep = compound.func_74762_e("blacksmithStep"); 
    if (compound.func_74764_b("blacksmithSwordTooBigPoint"))
      this.blacksmithSwordTooBigPoint = compound.func_74762_e("blacksmithSwordTooBigPoint"); 
    if (compound.func_74764_b("blacksmithIronSkullHammerPoint"))
      this.blacksmithIronSkullHammerPoint = compound.func_74762_e("blacksmithIronSkullHammerPoint"); 
    if (compound.func_74764_b("blacksmithRunicAxePoint"))
      this.blacksmithRunicAxePoint = compound.func_74762_e("blacksmithRunicAxePoint"); 
    if (compound.func_74764_b("blacksmithEnergeticBowSwordPoint"))
      this.blacksmithEnergeticBowSwordPoint = compound.func_74762_e("blacksmithEnergeticBowSwordPoint"); 
    if (compound.func_74764_b("blacksmithSitarPoint"))
      this.blacksmithSitarPoint = compound.func_74762_e("blacksmithSitarPoint"); 
    if (compound.func_74764_b("blacksmithSwordGuitarApocalypsePoint"))
      this.blacksmithSwordGuitarApocalypsePoint = compound.func_74762_e("blacksmithSwordGuitarApocalypsePoint"); 
    if (compound.func_74764_b("blacksmithSwordPistolPoint"))
      this.blacksmithSwordPistolPoint = compound.func_74762_e("blacksmithSwordPistolPoint"); 
    if (compound.func_74764_b("blacksmithBlunderbussPoint"))
      this.blacksmithBlunderbussPoint = compound.func_74762_e("blacksmithBlunderbussPoint"); 
    if (compound.func_74764_b("quackDate"))
      this.quackDate = compound.func_74763_f("quackDate"); 
    if (compound.func_74764_b("childTrumpetStartDate"))
      this.childTrumpetStartDate = compound.func_74763_f("childTrumpetStartDate"); 
    if (compound.func_74764_b("childTrumpetSpawnDate"))
      this.childTrumpetSpawnDate = compound.func_74763_f("childTrumpetSpawnDate"); 
    if (compound.func_74764_b("admindanseAllowed"))
      this.admindanseAllowed = compound.func_74767_n("admindanseAllowed"); 
    if (compound.func_74764_b("pirateChestOpened"))
      this.pirateChestOpened = compound.func_74767_n("pirateChestOpened"); 
    if (compound.func_74764_b("stormExpirationMillis"))
      this.stormExpirationMillis = compound.func_74763_f("stormExpirationMillis"); 
    if (compound.func_74764_b("summerBodyExpirationMillis"))
      this.summerBodyExpirationMillis = compound.func_74763_f("summerBodyExpirationMillis"); 
    if (compound.func_74764_b("ownAnimal"))
      this.ownCrab = compound.func_74767_n("ownAnimal"); 
    if (compound.func_74764_b("augustChestOpened"))
      this.augustChestOpened = compound.func_74767_n("augustChestOpened"); 
    if (compound.func_74764_b("summerTeleportation"))
      this.summerTeleportation = compound.func_74767_n("summerTeleportation"); 
    if (compound.func_74764_b("monkExpirationMillis"))
      this.monkExpirationMillis = compound.func_74763_f("monkExpirationMillis"); 
    if (compound.func_74764_b("monkType"))
      this.monkType = compound.func_74762_e("monkType"); 
    if (compound.func_74764_b("prime"))
      this.prime = compound.func_74767_n("prime"); 
    if (compound.func_74764_b("luckyExperienceStarted"))
      this.luckyExperienceStarted = compound.func_74767_n("luckyExperienceStarted"); 
    if (compound.func_74764_b("luckyExperienceAmount"))
      this.luckyExperienceAmount = compound.func_74762_e("luckyExperienceAmount"); 
    if (compound.func_74764_b("ownTaupiko"))
      this.ownTaupiko = compound.func_74767_n("ownTaupiko"); 
    if (compound.func_74764_b("ownHolidayCommand"))
      this.ownHolidayCommand = compound.func_74767_n("ownHolidayCommand"); 
    if (compound.func_74764_b("ownSlaver"))
      this.ownSlayer = compound.func_74767_n("ownSlaver"); 
  }
  
  public void init(Entity entity, World world) {
    this.entity = (EntityPlayer)entity;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("palamod_LB", new PlayerLuckyBlockProperties(player));
  }
  
  public static final PlayerLuckyBlockProperties get(EntityPlayer player) {
    return (PlayerLuckyBlockProperties)player.getExtendedProperties("palamod_LB");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PlayerLuckyBlockProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */