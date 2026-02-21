package fr.paladium.palaautomation.common.tile.impl;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.manager.StructureManager;
import fr.paladium.blueprint.common.tile.IBluePrintTile;
import fr.paladium.blueprint.common.utils.BlockPos;
import fr.paladium.blueprint.common.utils.BluePrintOutput;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import fr.paladium.palaautomation.common.tile.util.AutoCrafterRecipe;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.container.impl.TileEntityForgeInventory;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.JobsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityCrafter extends TileEntityForgeInventory implements IPipeMachine, ISidedInventory, IBluePrintTile {
  public static final String INVENTORY_NAME = "Crafter";
  
  public static final int INVENTORY_SIZE = 1;
  
  public static final long CRAFT_DELAY = 100L;
  
  private static final String NBT_FACING = "facing";
  
  private static final String NBT_ENABLED = "enabled";
  
  private static final String NBT_JOB_TYPE = "job_%s";
  
  private long lastCraftMillis;
  
  private long networkCheckIntervalMs;
  
  private final HashMap<JobType, Integer> jobTypes;
  
  private PipeItemData itemData;
  
  private EnumFacing structureFacing;
  
  private EnumPipeFacing facing;
  
  private boolean enabled;
  
  private transient ATileEntityPipe closestPipe;
  
  public long getLastCraftMillis() {
    return this.lastCraftMillis;
  }
  
  public long getNetworkCheckIntervalMs() {
    return this.networkCheckIntervalMs;
  }
  
  public HashMap<JobType, Integer> getJobTypes() {
    return this.jobTypes;
  }
  
  public EnumFacing getStructureFacing() {
    return this.structureFacing;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public ATileEntityPipe getClosestPipe() {
    return this.closestPipe;
  }
  
  public TileEntityCrafter() {
    super("Crafter", 1);
    this.facing = EnumPipeFacing.NORTH;
    this.structureFacing = EnumFacing.NORTH;
    this.lastCraftMillis = System.currentTimeMillis();
    this.networkCheckIntervalMs = 0L;
    this.jobTypes = new HashMap<>();
    this.enabled = true;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    PipeItemData.toNBT(compound, this.itemData);
    compound.func_74768_a("facing", this.facing.ordinal());
    compound.func_74757_a("enabled", this.enabled);
    for (JobType jobType : JobType.values()) {
      compound.func_74768_a(String.format("job_%s", new Object[] { jobType.name() }), ((Integer)this.jobTypes.getOrDefault(jobType, Integer.valueOf(0))).intValue());
    } 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.itemData = PipeItemData.fromNBT(compound);
    this.facing = EnumPipeFacing.values()[compound.func_74762_e("facing")];
    this.enabled = compound.func_74767_n("enabled");
    for (JobType jobType : JobType.values()) {
      this.jobTypes.put(jobType, Integer.valueOf(compound.func_74762_e(String.format("job_%s", new Object[] { jobType.name() }))));
    } 
  }
  
  public ATileEntityPipe findClosest() {
    if (this.closestPipe != null) {
      Block block = this.field_145850_b.func_147439_a(this.closestPipe.field_145851_c, this.closestPipe.field_145848_d, this.closestPipe.field_145849_e);
      if (block instanceof fr.paladium.palaautomation.common.block.pipe.impl.ABlockPipe)
        return this.closestPipe; 
      this.closestPipe = null;
    } 
    ATileEntityPipe closestPipe = findClosestPipeByIterating();
    if (closestPipe != null)
      this.closestPipe = closestPipe; 
    return this.closestPipe;
  }
  
  private void transitIntoPipe() {
    if (this.itemData == null)
      return; 
    ATileEntityPipe pipe = findClosest();
    if (pipe == null || !pipe.receiveFromMachine(this))
      return; 
    if (!this.itemData.decrement())
      this.itemData = null; 
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    checkNetWork();
    transitIntoPipe();
    long now = System.currentTimeMillis();
    if (!canCraft(now))
      return; 
    ABluePrint bluePrint = getBluePrint();
    if (bluePrint == null)
      return; 
    BluePrintOutput output = verifyStructure();
    if (!output.isValid())
      return; 
    List<TileEntityReceiver> receivers = getReceivers(output);
    if (receivers.isEmpty())
      return; 
    AutoCrafterRecipe recipe = new AutoCrafterRecipe(this, receivers);
    if (recipe.getOutputItem() == null)
      return; 
    PipeItemData recipeData = recipe.getOutputItem();
    if (recipeData == null || (this.itemData != null && !this.itemData.equals(recipeData)))
      return; 
    if (this.itemData == null) {
      this.itemData = recipeData;
    } else if (!increment(recipeData)) {
      ItemUtils.spawnItemInWorld(this.field_145850_b, this.field_145851_c, this.field_145848_d + 0.5D, this.field_145849_e, recipeData.toSingleItemStack());
    } 
    recipe.decrementReceivers();
    onPipeItemDataChanged();
  }
  
  public AutoCrafterRecipe findRecipe() {
    ABluePrint bluePrint = getBluePrint();
    if (bluePrint == null)
      return null; 
    BluePrintOutput output = verifyStructure();
    if (!output.isValid())
      return null; 
    List<TileEntityReceiver> receivers = getReceivers(output);
    if (receivers.isEmpty())
      return null; 
    return new AutoCrafterRecipe(this, receivers);
  }
  
  private boolean canCraft(long now) {
    if (!this.enabled)
      return false; 
    boolean canCraft = (now - this.lastCraftMillis >= 100L);
    if (canCraft)
      this.lastCraftMillis = now; 
    return canCraft;
  }
  
  public BluePrintOutput verifyStructure() {
    ABluePrint bluePrint = StructureManager.getInstance().getBluePrint(getLinkedBluePrintId());
    if (bluePrint == null)
      return BluePrintOutput.INVALID; 
    if (this.facing != null) {
      BluePrintOutput output = bluePrint.verifyFromBlockAndCoords(this.field_145850_b, func_145838_q(), this.structureFacing, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      if (output.isValid())
        return output; 
    } 
    for (EnumFacing vanillaFacing : EnumFacing.values()) {
      if (vanillaFacing != this.structureFacing) {
        BluePrintOutput output = bluePrint.verifyFromBlockAndCoords(this.field_145850_b, func_145838_q(), vanillaFacing, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (output.isValid()) {
          this.structureFacing = vanillaFacing;
          return output;
        } 
      } 
    } 
    return BluePrintOutput.INVALID;
  }
  
  private ABluePrint getBluePrint() {
    return StructureManager.getInstance().getBluePrint(getLinkedBluePrintId());
  }
  
  public List<TileEntityReceiver> getReceivers(BluePrintOutput output) {
    if (!output.isValid())
      return new ArrayList<>(); 
    List<BlockPos> blocks = (List<BlockPos>)output.getBlocks().stream().filter(block -> block.getBlock() instanceof fr.paladium.palaautomation.common.block.BlockReceiver).sorted((a, b) -> (a.getY() == b.getY()) ? 0 : (b.getY() - a.getY())).collect(Collectors.toList());
    return (List<TileEntityReceiver>)blocks.stream()
      .map(block -> (TileEntityReceiver)this.field_145850_b.func_147438_o(block.getX(), block.getY(), block.getZ()))
      .collect(Collectors.toList());
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public TileEntity getTileEntity() {
    return (TileEntity)this;
  }
  
  public boolean setItemData(PipeItemData data, boolean fromPipe) {
    if (data == null) {
      this.itemData = null;
      return true;
    } 
    if (this.itemData != null)
      return false; 
    this.itemData = fromPipe ? data.copy(1) : data.copy();
    return true;
  }
  
  public PipeItemData getItemData() {
    return this.itemData;
  }
  
  public EnumPipeFacing getFacing() {
    return this.facing;
  }
  
  public void setFacing(EnumPipeFacing facing) {
    this.facing = facing;
  }
  
  public boolean receiveFromPipe(PipeItemData stack) {
    return false;
  }
  
  public long networkCheckIntervalMs() {
    return ATileEntityPipe.CHECK_INTERVAL_MS;
  }
  
  public long lastNetWorkCheckMs() {
    return this.networkCheckIntervalMs;
  }
  
  public void setLastNetWorkCheckMs(long lastNetWorkCheckMs) {
    this.networkCheckIntervalMs = lastNetWorkCheckMs;
  }
  
  public ATileEntityPipe getCheckPipe() {
    return findClosestPipeByIterating();
  }
  
  public boolean canGiveToPipe() {
    return true;
  }
  
  public int[] func_94128_d(int data) {
    return new int[0];
  }
  
  public boolean func_102007_a(int slot, ItemStack stack, int side) {
    return false;
  }
  
  public boolean func_102008_b(int slot, ItemStack stack, int side) {
    return false;
  }
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    return false;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {}
  
  public boolean func_70300_a(EntityPlayer player) {
    return PipeUtils.isUseableByPlayer((TileEntity)this, player);
  }
  
  public EnumFacing getDirection() {
    return this.structureFacing;
  }
  
  public String getLinkedBluePrintId() {
    return "auto_crafter";
  }
  
  public boolean shouldCheckSideFromPipe() {
    return false;
  }
  
  public boolean canCraft(ItemStack itemStack) {
    if (itemStack == null)
      return false; 
    List<IBlackListedItem> listedItems = JobsManager.getInstance().getBlackListedCrafts();
    for (IBlackListedItem blacklisted : listedItems) {
      if (!JobsUtils.isItemEqual(blacklisted.getStack(), itemStack))
        continue; 
      int level = ((Integer)this.jobTypes.getOrDefault(blacklisted.getType(), Integer.valueOf(0))).intValue();
      return (level >= blacklisted.getRequiredLevel());
    } 
    return true;
  }
  
  public void onCrafterOpened(EntityPlayerMP player) {
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    if (jobsPlayer == null)
      return; 
    for (JobType jobType : JobType.values())
      this.jobTypes.put(jobType, Integer.valueOf(jobsPlayer.getLevel(jobType))); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\impl\TileEntityCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */