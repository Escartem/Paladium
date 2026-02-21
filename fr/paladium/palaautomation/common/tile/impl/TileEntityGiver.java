package fr.paladium.palaautomation.common.tile.impl;

import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.ITileFacing;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.container.impl.TileEntityForgeInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGiver extends TileEntityForgeInventory implements IPipeMachine, ISidedInventory, ITileFacing {
  public static final int INVENTORY_SIZE = 1;
  
  public static final String INVENTORY_NAME = "Giver";
  
  private PipeItemData itemData;
  
  private EnumPipeFacing facing;
  
  private long networkCheckIntervalMs;
  
  public long getNetworkCheckIntervalMs() {
    return this.networkCheckIntervalMs;
  }
  
  public TileEntityGiver() {
    super("Giver", 1);
    this.facing = EnumPipeFacing.NORTH;
    this.networkCheckIntervalMs = 0L;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    PipeItemData.toNBT(compound, this.itemData);
    EnumPipeFacing.writeToNBT(this.facing, compound);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.itemData = PipeItemData.fromNBT(compound);
    this.facing = EnumPipeFacing.fromNBT(compound);
  }
  
  private String coordOuput() {
    return this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    checkNetWork();
    PipeItemData data = this.itemData;
    if (data == null)
      return; 
    ATileEntityPipe pipe = findClosestPipe();
    if (pipe == null)
      return; 
    if (!pipe.receiveFromMachine(this))
      return; 
    if (!this.itemData.decrement())
      this.itemData = null; 
    onPipeItemDataChanged();
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
    boolean ret = PipeUtils.isValidForMachine(this, slot, stack);
    if (!ret)
      return false; 
    return true;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    if (PipeUtils.setInventorySlotContents(this, slot, stack))
      onPipeItemDataChanged(); 
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return PipeUtils.isUseableByPlayer((TileEntity)this, player);
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
    return findClosestPipe();
  }
  
  public boolean canGiveToPipe() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\impl\TileEntityGiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */