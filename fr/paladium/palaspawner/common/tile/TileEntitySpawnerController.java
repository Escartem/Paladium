package fr.paladium.palaspawner.common.tile;

import fr.paladium.blueprint.common.tile.IBluePrintTile;
import fr.paladium.blueprint.common.utils.BlockPos;
import fr.paladium.palaforgeutils.lib.container.impl.TileEntityForgeInventory;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palaspawner.common.entity.EntitySpawner;
import fr.paladium.palaspawner.common.item.upgrade.AItemSpawnerUpgrade;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.registry.SpawnerUpgradeRegistry;
import fr.paladium.palaspawner.common.spawner.ControllerState;
import fr.paladium.palaspawner.common.spawner.MobData;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.spawner.blueprint.SpawnerData;
import fr.paladium.palaspawner.common.spawner.blueprint.output.SpawnerBluePrintOutput;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import fr.paladium.palaspawner.common.spawner.upgrade.SpawnerUpgrade;
import fr.paladium.palaspawner.common.utils.ContainerUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StringUtils;

public class TileEntitySpawnerController extends TileEntityForgeInventory implements IBluePrintTile, ISidedInventory {
  public static final String INVENTORY_NAME = "spawner_controller";
  
  public static final int INVENTORY_SIZE = 4;
  
  private static final String TAG_CONTROLLER_STATE = "controllerState";
  
  private static final String TAG_LINKED_BLUEPRINT_ID = "linkedBlueprintId";
  
  private static final String TAG_VALID = "valid";
  
  private static final long VERIFICATION_INTERVAL_MS = 1000L;
  
  private final SpawnerManager manager;
  
  private String linkedBlueprintId;
  
  private EnumFacing direction;
  
  private long lastVerificationMs;
  
  private Map<Tier, Long> spawnMap;
  
  private EntitySpawner linkedEntity;
  
  private List<MobData> mobDataList;
  
  private String selectedEntityType;
  
  private Map<JobType, Integer> jobDataMap;
  
  public void setLinkedBlueprintId(String linkedBlueprintId) {
    this.linkedBlueprintId = linkedBlueprintId;
  }
  
  public void setDirection(EnumFacing direction) {
    this.direction = direction;
  }
  
  public void setLastVerificationMs(long lastVerificationMs) {
    this.lastVerificationMs = lastVerificationMs;
  }
  
  public void setSpawnMap(Map<Tier, Long> spawnMap) {
    this.spawnMap = spawnMap;
  }
  
  public void setLinkedEntity(EntitySpawner linkedEntity) {
    this.linkedEntity = linkedEntity;
  }
  
  public void setMobDataList(List<MobData> mobDataList) {
    this.mobDataList = mobDataList;
  }
  
  public void setSelectedEntityType(String selectedEntityType) {
    this.selectedEntityType = selectedEntityType;
  }
  
  public void setJobDataMap(Map<JobType, Integer> jobDataMap) {
    this.jobDataMap = jobDataMap;
  }
  
  public void setValid(int valid) {
    this.valid = valid;
  }
  
  public void setControllerState(ControllerState controllerState) {
    this.controllerState = controllerState;
  }
  
  public SpawnerManager getManager() {
    return this.manager;
  }
  
  public long getLastVerificationMs() {
    return this.lastVerificationMs;
  }
  
  public Map<Tier, Long> getSpawnMap() {
    return this.spawnMap;
  }
  
  public EntitySpawner getLinkedEntity() {
    return this.linkedEntity;
  }
  
  public List<MobData> getMobDataList() {
    return this.mobDataList;
  }
  
  public String getSelectedEntityType() {
    return this.selectedEntityType;
  }
  
  public Map<JobType, Integer> getJobDataMap() {
    return this.jobDataMap;
  }
  
  private int valid = -1;
  
  private ControllerState controllerState;
  
  public int getValid() {
    return this.valid;
  }
  
  public ControllerState getControllerState() {
    return this.controllerState;
  }
  
  public TileEntitySpawnerController() {
    super("spawner_controller", 4);
    this.linkedBlueprintId = null;
    this.lastVerificationMs = 0L;
    this.manager = SpawnerManager.getInstance();
    this.spawnMap = new HashMap<>();
    this.controllerState = ControllerState.MANUAL;
    this.direction = EnumFacing.NORTH;
    this.mobDataList = new ArrayList<>();
    this.jobDataMap = new HashMap<>();
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    long now = System.currentTimeMillis();
    if (!canVerify(now))
      return; 
    TileEntitySpawnerController first = (this.field_145850_b.func_72938_d(this.field_145851_c, this.field_145849_e)).field_150816_i.values().stream().filter(TileEntitySpawnerController.class::isInstance).map(tile -> (TileEntitySpawnerController)tile).findFirst().orElse(null);
    this.valid = (first == this) ? 1 : 0;
    if (this.valid != 1)
      return; 
    if (this.spawnMap == null)
      this.spawnMap = new HashMap<>(); 
    if (this.spawnMap.isEmpty())
      for (Tier tier : Tier.values())
        this.spawnMap.put(tier, Long.valueOf(now));  
    List<SpawnerUpgrade> upgrades = getUpgrades();
    removeInvalidSlots(upgrades);
    SpawnerBluePrintOutput output = this.manager.getBluePrintFromTile(this.field_145850_b, this.linkedBlueprintId, this.direction, this);
    if (!output.isValid())
      return; 
    ASpawnerBluePrint bluePrint = output.getBluePrint();
    if (bluePrint == null)
      return; 
    checkLinkedEntity();
    List<SpawnerData> dataList = bluePrint.getSpawnersFromController(this.field_145850_b, output);
    Set<Tier> spawnList = new HashSet<>();
    for (SpawnerData data : dataList) {
      if (applySpawnerData(output, data, now, upgrades))
        spawnList.add(data.getTier()); 
    } 
    for (Tier tier : spawnList)
      this.spawnMap.put(tier, Long.valueOf(now)); 
  }
  
  public SpawnerBluePrintOutput getOutput() {
    return this.manager.getBluePrintFromTile(this.field_145850_b, this.linkedBlueprintId, this.direction, this);
  }
  
  public void removeInvalidSlots(List<SpawnerUpgrade> upgrades) {
    for (int slot = 0; slot < func_70302_i_(); slot++) {
      ItemStack stack = func_70301_a(slot);
      if (stack != null && stack.func_77973_b() != null) {
        SpawnerUpgrade upgrade = getUpgrade(stack);
        boolean hasMoreUpgrade = (getUpgradeCount(SpawnerUpgradeRegistry.MORE, upgrades) > 0);
        int upgradeCount = getUpgradeCount(upgrade, upgrades);
        if (!hasMoreUpgrade && upgradeCount > 1) {
          ItemUtils.spawnItemInWorld(this.field_145850_b, this.field_145851_c, this.field_145848_d + 0.5D, this.field_145849_e, stack);
          func_70299_a(slot, null);
        } else if (slot > 1 && !hasMoreUpgrade) {
          ItemUtils.spawnItemInWorld(this.field_145850_b, this.field_145851_c, this.field_145848_d + 0.5D, this.field_145849_e, stack);
          func_70299_a(slot, null);
        } 
      } 
    } 
  }
  
  public boolean isUpgradeValidForSlot(ItemStack stack, int slotId) {
    if (stack == null || stack.func_77973_b() == null)
      return false; 
    SpawnerUpgrade upgrade = getUpgrade(stack);
    if (upgrade == null || !upgrade.hasRequirements(getJobDataMap()))
      return false; 
    boolean hasMoreUpgrade = (getUpgradeCount(SpawnerUpgradeRegistry.MORE) > 0);
    boolean alreadyContainsUpgrade = (getUpgradeCount(upgrade) > 0);
    if ((hasMoreUpgrade && upgrade.getId().equals(SpawnerUpgradeRegistry.MORE.getId())) || (alreadyContainsUpgrade && !hasMoreUpgrade))
      return false; 
    if (slotId > 1 && !hasMoreUpgrade)
      return false; 
    return true;
  }
  
  private SpawnerUpgrade getUpgrade(ItemStack stack) {
    if (stack == null || stack.func_77973_b() == null || !(stack.func_77973_b() instanceof AItemSpawnerUpgrade))
      return null; 
    return ((AItemSpawnerUpgrade)stack.func_77973_b()).getUpgrade();
  }
  
  private boolean applySpawnerData(SpawnerBluePrintOutput output, SpawnerData data, long now, List<SpawnerUpgrade> upgrades) {
    if (StringUtils.func_151246_b(data.getEntityId()) || !canSpawn(data.getTier(), now, getUpgradeCount(SpawnerUpgradeRegistry.SPEED, upgrades)))
      return false; 
    int spawnCount = data.getTier().getSpawnAmount(this.field_145850_b.field_73012_v) * data.getAmount();
    if (spawnCount <= 0)
      return false; 
    int slimeUpgradeCount = getUpgradeCount(SpawnerUpgradeRegistry.SLIME, upgrades);
    if (slimeUpgradeCount > 0)
      for (int i = 0; i < spawnCount; i++) {
        double random = this.field_145850_b.field_73012_v.nextDouble() * 100.0D;
        if (random <= 0.11D * slimeUpgradeCount)
          if (this.controllerState == ControllerState.AUTOMATIC) {
            handleAutomaticMod(output, "Slime", 1);
          } else if (this.controllerState == ControllerState.MANUAL) {
            addOrIncrementEntity("Slime", 1);
          }  
      }  
    String entityId = data.getEntityId();
    if (this.controllerState == ControllerState.AUTOMATIC)
      return handleAutomaticMod(output, entityId, spawnCount); 
    if (this.controllerState == ControllerState.MANUAL) {
      addOrIncrementEntity(entityId, spawnCount);
      updateEntityName();
    } 
    return true;
  }
  
  public void decrementCurrentSpawnedMobs() {
    if (this.mobDataList.isEmpty())
      return; 
    MobData data = getSelectedMobData();
    if (data == null)
      return; 
    data.decrement();
    if (data.getAmount() <= 0) {
      MobData nextData = nextOrPreviousMobData(true);
      this.mobDataList.remove(data);
      if (this.mobDataList.isEmpty()) {
        killLinkedEntity();
      } else {
        this.selectedEntityType = nextData.getEntityType();
      } 
    } 
    updateEntityName();
  }
  
  private void updateEntityName() {
    if (this.linkedEntity == null || this.linkedEntity.field_70128_L || this.mobDataList.isEmpty())
      return; 
    MobData data = getSelectedMobData();
    this.linkedEntity.setTypeAndHologram(data.getEntityType(), data.getAmount());
  }
  
  private ASpawnerEntityData getEntityData(String entityName) {
    return this.manager.getSpawnerData(entityName);
  }
  
  public boolean dropOrPutIntoInventory(EntityPlayerMP player, String entityId) {
    ASpawnerEntityData data = getEntityData(entityId);
    if (data == null)
      return false; 
    List<ItemStack> stacks = data.getDrops(this.field_145850_b.field_73012_v, player, "Slime".equals(entityId) ? 0 : getUpgradeCount(SpawnerUpgradeRegistry.LOOTING));
    IInventory inventory = getRandomInventory(getAllInventory(getInventoriesLocations()));
    if (inventory == null) {
      stacks.forEach(stack -> InventoryUtils.giveOrDropitems((EntityPlayer)player, stack));
      return true;
    } 
    return putIntoInventory(inventory, stacks);
  }
  
  private List<DoubleLocation> getInventoriesLocations() {
    int y = this.field_145848_d - 2;
    List<DoubleLocation> locations = new ArrayList<>();
    if (this.direction == null)
      return locations; 
    switch (this.direction) {
      case NORTH:
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, (this.field_145849_e + 1)));
        locations.add(new DoubleLocation((this.field_145851_c - 2), y, (this.field_145849_e + 1)));
        locations.add(new DoubleLocation(this.field_145851_c, y, (this.field_145849_e + 1)));
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, (this.field_145849_e + 1)));
        locations.add(new DoubleLocation((this.field_145851_c + 2), y, (this.field_145849_e + 1)));
        break;
      case SOUTH:
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, (this.field_145849_e - 1)));
        locations.add(new DoubleLocation((this.field_145851_c - 2), y, (this.field_145849_e - 1)));
        locations.add(new DoubleLocation(this.field_145851_c, y, (this.field_145849_e - 1)));
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, (this.field_145849_e - 1)));
        locations.add(new DoubleLocation((this.field_145851_c + 2), y, (this.field_145849_e - 1)));
        break;
      case EAST:
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, (this.field_145849_e - 1)));
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, (this.field_145849_e - 2)));
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, this.field_145849_e));
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, (this.field_145849_e + 1)));
        locations.add(new DoubleLocation((this.field_145851_c + 1), y, (this.field_145849_e + 2)));
        break;
      case WEST:
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, (this.field_145849_e - 1)));
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, (this.field_145849_e - 2)));
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, this.field_145849_e));
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, (this.field_145849_e + 1)));
        locations.add(new DoubleLocation((this.field_145851_c - 1), y, (this.field_145849_e + 2)));
        break;
    } 
    return locations;
  }
  
  private List<IInventory> getAllInventory(List<DoubleLocation> locations) {
    List<IInventory> inventories = new ArrayList<>();
    for (DoubleLocation location : locations) {
      TileEntity tile = this.field_145850_b.func_147438_o(location.getBlockX(), location.getBlockY(), location.getBlockZ());
      if (tile instanceof IInventory)
        inventories.add((IInventory)tile); 
    } 
    return inventories;
  }
  
  private IInventory getRandomInventory(List<IInventory> inventories) {
    if (inventories.isEmpty())
      return null; 
    int size = inventories.size();
    int startIndex = this.field_145850_b.field_73012_v.nextInt(size);
    for (int i = 0; i < size; i++) {
      int index = (startIndex + i) % size;
      IInventory inventory = inventories.get(index);
      if (!ContainerUtils.isInventoryFull(inventory))
        return inventory; 
    } 
    return null;
  }
  
  private boolean handleAutomaticMod(SpawnerBluePrintOutput output, String entityId, int count) {
    ASpawnerEntityData data = getEntityData(entityId);
    if (data == null)
      return false; 
    int lootingLevel = getUpgradeCount(SpawnerUpgradeRegistry.LOOTING);
    List<ItemStack> stacks = new ArrayList<>();
    for (int i = 0; i < count; i++)
      stacks.addAll(data.getDrops(this.field_145850_b.field_73012_v, null, lootingLevel)); 
    return putIntoInventory(getRandomInventory(getAllInventory(getInventoriesLocations())), stacks);
  }
  
  private boolean putIntoInventory(IInventory inventory, List<ItemStack> stacks) {
    if (inventory == null || stacks.isEmpty())
      return false; 
    int invSize = inventory.func_70302_i_();
    int invStackLimit = inventory.func_70297_j_();
    for (ItemStack stack : stacks) {
      if (stack == null || stack.func_77973_b() == null)
        continue; 
      int i;
      for (i = 0; i < invSize && stack.field_77994_a > 0; i++) {
        ItemStack slotStack = inventory.func_70301_a(i);
        if (slotStack != null)
          if (slotStack.func_77973_b() == stack.func_77973_b() && ItemStack.func_77970_a(slotStack, stack)) {
            int maxStackSize = Math.min(invStackLimit, slotStack.func_77976_d());
            int spaceAvailable = maxStackSize - slotStack.field_77994_a;
            if (spaceAvailable > 0) {
              int amountToAdd = Math.min(stack.field_77994_a, spaceAvailable);
              slotStack.field_77994_a += amountToAdd;
              stack.field_77994_a -= amountToAdd;
            } 
          }  
      } 
      if (stack.field_77994_a > 0)
        for (i = 0; i < invSize; i++) {
          if (inventory.func_70301_a(i) == null && inventory.func_94041_b(i, stack)) {
            inventory.func_70299_a(i, stack.func_77946_l());
            stack.field_77994_a = 0;
            break;
          } 
        }  
    } 
    return true;
  }
  
  public boolean checkLinkedEntity() {
    if (this.controllerState == ControllerState.MANUAL && (
      this.linkedEntity == null || this.linkedEntity.field_70128_L)) {
      respawnLinkedEntity();
      return false;
    } 
    return true;
  }
  
  public void respawnLinkedEntity() {
    if (this.linkedEntity != null) {
      this.linkedEntity.func_70106_y();
      this.linkedEntity = null;
    } 
    MobData data = this.mobDataList.isEmpty() ? null : this.mobDataList.get(0);
    if (data == null)
      return; 
    this
      
      .linkedEntity = new EntitySpawner(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, BlockPos.of(func_145838_q(), this.field_145851_c, this.field_145848_d, this.field_145849_e));
    this.field_145850_b.func_72838_d((Entity)this.linkedEntity);
    this.linkedEntity.setTypeAndHologram(data.getEntityType(), data.getAmount());
  }
  
  public ControllerState nextState() {
    this.controllerState = this.controllerState.next();
    killLinkedEntity();
    return this.controllerState;
  }
  
  public ControllerState setState(ControllerState state) {
    this.controllerState = state;
    killLinkedEntity();
    return this.controllerState;
  }
  
  public void killLinkedEntity() {
    if (this.linkedEntity != null) {
      this.linkedEntity.func_70106_y();
      this.linkedEntity = null;
    } 
    this.mobDataList.clear();
    this.selectedEntityType = null;
  }
  
  private void addOrIncrementEntity(String entityName, int count) {
    MobData data = getMobData(entityName);
    if (data == null) {
      this.mobDataList.add(MobData.of(entityName, count));
      return;
    } 
    data.increment(count);
  }
  
  private MobData getSelectedMobData() {
    if ((this.selectedEntityType == null || this.mobDataList.size() == 1) && 
      !this.mobDataList.isEmpty()) {
      MobData data = this.mobDataList.get(0);
      this.selectedEntityType = data.getEntityType();
      return data;
    } 
    return getMobData(this.selectedEntityType);
  }
  
  public MobData nextOrPreviousMobData(boolean next) {
    if (this.mobDataList.isEmpty())
      return null; 
    MobData data = getSelectedMobData();
    if (data == null)
      return null; 
    int index = this.mobDataList.indexOf(data);
    if (next) {
      index++;
    } else {
      index--;
    } 
    if (index < 0) {
      index = this.mobDataList.size() - 1;
    } else if (index >= this.mobDataList.size()) {
      index = 0;
    } 
    MobData nextData = this.mobDataList.get(index);
    this.selectedEntityType = nextData.getEntityType();
    if (this.linkedEntity != null && !this.linkedEntity.field_70128_L)
      this.linkedEntity.setTypeAndHologram(nextData.getEntityType(), nextData.getAmount()); 
    return nextData;
  }
  
  private MobData getMobData(String entityName) {
    return this.mobDataList.stream()
      .filter(data -> data.getEntityType().equals(entityName))
      .findFirst()
      .orElse(null);
  }
  
  private boolean canSpawn(Tier tier, long now, int speedUpgradeCount) {
    if (!this.spawnMap.containsKey(tier)) {
      this.spawnMap.put(tier, Long.valueOf(System.currentTimeMillis()));
      return true;
    } 
    long lastSpawn = ((Long)this.spawnMap.get(tier)).longValue();
    long maxTime = tier.getTimeMax();
    if (speedUpgradeCount > 0)
      maxTime -= (long)(maxTime * 0.1D * speedUpgradeCount); 
    if (now - lastSpawn < maxTime)
      return false; 
    return true;
  }
  
  private boolean canVerify(long now) {
    if (now - this.lastVerificationMs < 1000L)
      return false; 
    this.lastVerificationMs = now;
    return true;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    if (this.controllerState != null)
      compound.func_74768_a("controllerState", this.controllerState.ordinal()); 
    if (this.linkedBlueprintId != null)
      compound.func_74778_a("linkedBlueprintId", this.linkedBlueprintId); 
    compound.func_74768_a("valid", this.valid);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (compound.func_74764_b("controllerState"))
      this.controllerState = ControllerState.values()[compound.func_74762_e("controllerState")]; 
    if (compound.func_74764_b("linkedBlueprintId"))
      this.linkedBlueprintId = compound.func_74779_i("linkedBlueprintId"); 
    if (compound.func_74764_b("valid"))
      this.valid = compound.func_74762_e("valid"); 
  }
  
  public int[] func_94128_d(int side) {
    return new int[] { 0 };
  }
  
  public boolean func_102007_a(int p_102007_1_, ItemStack stack, int p_102007_3_) {
    return true;
  }
  
  public boolean func_102008_b(int p_102008_1_, ItemStack stack, int p_102008_3_) {
    return true;
  }
  
  public int getUpgradeCount(SpawnerUpgrade upgrade) {
    return getUpgradeCount(upgrade.getId());
  }
  
  public int getUpgradeCount(String id, List<SpawnerUpgrade> upgrades) {
    return (int)upgrades.stream().filter(u -> u.getId().equals(id)).count();
  }
  
  public int getUpgradeCount(SpawnerUpgrade upgrade, List<SpawnerUpgrade> upgrades) {
    return getUpgradeCount(upgrade.getId(), upgrades);
  }
  
  public int getUpgradeCount(String id) {
    return getUpgradeCount(id, getUpgrades());
  }
  
  public EnumFacing getDirection() {
    return this.direction;
  }
  
  public String getLinkedBluePrintId() {
    return this.linkedBlueprintId;
  }
  
  public void putJobData(IJobsPlayer player) {
    for (JobType value : JobType.values())
      this.jobDataMap.put(value, Integer.valueOf((player == null) ? 1 : player.getLevel(value))); 
  }
  
  public int getLevel(JobType type) {
    return ((Integer)this.jobDataMap.getOrDefault(type, Integer.valueOf(0))).intValue();
  }
  
  public List<SpawnerUpgrade> getUpgrades() {
    List<SpawnerUpgrade> upgrades = new ArrayList<>();
    for (int i = 0; i < func_70302_i_(); i++) {
      ItemStack stack = func_70301_a(i);
      if (stack != null && stack.func_77973_b() != null && stack.func_77973_b() instanceof AItemSpawnerUpgrade) {
        AItemSpawnerUpgrade upgrade = (AItemSpawnerUpgrade)stack.func_77973_b();
        upgrades.add(upgrade.getUpgrade());
      } 
    } 
    return upgrades;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void updateClientSide(EntityPlayerMP self) {
    Packet packet = func_145844_m();
    if (self != null)
      self.field_71135_a.func_147359_a(packet); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\tile\TileEntitySpawnerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */