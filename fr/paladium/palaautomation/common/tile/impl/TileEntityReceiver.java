package fr.paladium.palaautomation.common.tile.impl;

import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
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

public class TileEntityReceiver extends TileEntityForgeInventory implements IPipeMachine, ISidedInventory {
  public static final String INVENTORY_NAME = "Receiver";
  
  public static final int INVENTORY_SIZE = 1;
  
  private PipeItemData itemData;
  
  private EnumPipeFacing facing;
  
  public TileEntityReceiver() {
    super("Receiver", 1);
    this.facing = EnumPipeFacing.NORTH;
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
  
  public boolean receiveFromPipe(PipeItemData data) {
    if (setItemData(data, true)) {
      onPipeItemDataChanged();
      return true;
    } 
    if (this.itemData.increment(data)) {
      onPipeItemDataChanged();
      return true;
    } 
    return false;
  }
  
  public long networkCheckIntervalMs() {
    return 0L;
  }
  
  public long lastNetWorkCheckMs() {
    return 0L;
  }
  
  public void setLastNetWorkCheckMs(long lastNetWorkCheckMs) {}
  
  public ATileEntityPipe getCheckPipe() {
    return null;
  }
  
  public boolean canGiveToPipe() {
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
    return true;
  }
  
  public boolean func_102008_b(int slot, ItemStack stack, int side) {
    return false;
  }
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    return PipeUtils.isValidForMachine(this, slot, stack);
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    if (PipeUtils.setInventorySlotContents(this, slot, stack))
      onPipeItemDataChanged(); 
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return PipeUtils.isUseableByPlayer((TileEntity)this, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\impl\TileEntityReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */