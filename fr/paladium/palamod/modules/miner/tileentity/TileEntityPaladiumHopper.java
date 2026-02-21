package fr.paladium.palamod.modules.miner.tileentity;

import fr.paladium.betternei.client.util.ItemUtils;
import fr.paladium.helios.utils.Vec3i;
import fr.paladium.palamod.modules.miner.PMiner;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;

public class TileEntityPaladiumHopper extends TileEntity {
  private final List<Vec3i> positions = new ArrayList<>();
  
  private int tickCount = 0;
  
  public void func_145845_h() {
    this.tickCount++;
    if (this.field_145850_b.field_72995_K || this.tickCount % PMiner.HOPPER_RATE != 0 || !BlockHopper.func_149917_c(func_145832_p()))
      return; 
    this.positions.clear();
    IInventory upperTile = getUpperInventory();
    if (upperTile == null)
      return; 
    IInventory pointedTile = getPointedInventory(this.positions);
    if (pointedTile == null)
      return; 
    int upperSlot = getAvailableSlotWithItem(upperTile, 0);
    if (upperSlot == -1 || upperTile.func_70301_a(upperSlot) == null)
      return; 
    int pointedSlot = getAvailablePointedSlot(pointedTile, Facing.field_71588_a[BlockHopper.func_149918_b(func_145832_p())], upperTile.func_70301_a(upperSlot).func_77946_l());
    if (pointedSlot == -1)
      return; 
    int toMove = Math.min((upperTile.func_70301_a(upperSlot)).field_77994_a, upperTile.func_70301_a(upperSlot).func_77976_d());
    if (pointedTile.func_70301_a(pointedSlot) == null) {
      ItemStack stack = upperTile.func_70301_a(upperSlot).func_77946_l();
      stack.field_77994_a = toMove;
      pointedTile.func_70299_a(pointedSlot, stack);
      upperTile.func_70298_a(upperSlot, toMove);
    } else {
      while (pointedSlot != -1 && toMove > 0) {
        if (pointedTile.func_70301_a(pointedSlot) == null) {
          ItemStack itemStack = upperTile.func_70301_a(upperSlot).func_77946_l();
          itemStack.field_77994_a = toMove;
          pointedTile.func_70299_a(pointedSlot, itemStack);
          upperTile.func_70298_a(upperSlot, toMove);
          toMove = 0;
          continue;
        } 
        ItemStack stack = pointedTile.func_70301_a(pointedSlot).func_77946_l();
        int moved = Math.min(stack.func_77976_d() - stack.field_77994_a, toMove);
        stack.field_77994_a += moved;
        pointedTile.func_70299_a(pointedSlot, stack);
        pointedSlot = getAvailablePointedSlot(pointedTile, Facing.field_71588_a[BlockHopper.func_149918_b(func_145832_p())], upperTile.func_70301_a(upperSlot).func_77946_l());
        toMove -= moved;
        upperTile.func_70298_a(upperSlot, moved);
      } 
    } 
    upperTile.func_70296_d();
    pointedTile.func_70296_d();
  }
  
  private int getAvailablePointedSlot(IInventory inventory, int side, ItemStack stack) {
    if (inventory instanceof ISidedInventory && side > -1) {
      ISidedInventory isidedinventory = (ISidedInventory)inventory;
      int[] aSide = isidedinventory.func_94128_d(side);
      for (int i = 0; i < aSide.length; i++) {
        ItemStack stackInSlot = isidedinventory.func_70301_a(aSide[i]);
        if (stackInSlot == null || (stackInSlot.field_77994_a < stackInSlot
          .func_77976_d() && 
          ItemUtils.isEquals(stackInSlot, stack, true) && 
          ItemStack.func_77970_a(stackInSlot, stack)))
          if (canInsert(inventory, stack, aSide[i], side))
            return aSide[i];  
      } 
    } else {
      int invSize = inventory.func_70302_i_();
      for (int i = 0; i < invSize; i++) {
        ItemStack stackInSlot = inventory.func_70301_a(i);
        if (stackInSlot == null || (stackInSlot.field_77994_a < stackInSlot
          .func_77976_d() && 
          ItemUtils.isEquals(stackInSlot, stack, true) && 
          ItemStack.func_77970_a(stackInSlot, stack)))
          if (canInsert(inventory, stack, invSize, side))
            return i;  
      } 
    } 
    return -1;
  }
  
  private int getAvailableSlotWithItem(IInventory inventory, int side) {
    if (inventory instanceof ISidedInventory && side > -1) {
      ISidedInventory isidedinventory = (ISidedInventory)inventory;
      int[] aSide = isidedinventory.func_94128_d(side);
      for (int i = 0; i < aSide.length; i++) {
        if (isidedinventory.func_70301_a(aSide[i]) != null && canExtract(inventory, isidedinventory.func_70301_a(aSide[i]), aSide[i], side))
          return aSide[i]; 
      } 
    } else {
      int invSize = inventory.func_70302_i_();
      for (int i = 0; i < invSize; i++) {
        if (inventory.func_70301_a(i) != null && canExtract(inventory, inventory.func_70301_a(i), i, side))
          return i; 
      } 
    } 
    return -1;
  }
  
  private IInventory getUpperInventory() {
    IInventory iinventory = null;
    TileEntity tileentity = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    if (tileentity instanceof IInventory) {
      iinventory = (IInventory)tileentity;
      if (iinventory instanceof net.minecraft.tileentity.TileEntityChest) {
        Block block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        if (block instanceof BlockChest)
          iinventory = ((BlockChest)block).func_149951_m(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e); 
      } 
    } 
    return iinventory;
  }
  
  public IInventory getPointedInventory(List<Vec3i> positions) {
    int i = BlockHopper.func_149918_b(func_145832_p());
    Vec3i vec = new Vec3i(this.field_145851_c + Facing.field_71586_b[i], this.field_145848_d + Facing.field_71587_c[i], this.field_145849_e + Facing.field_71585_d[i]);
    TileEntity tile = this.field_145850_b.func_147438_o(vec.getX(), vec.getY(), vec.getZ());
    if (positions.contains(vec))
      return null; 
    if (tile instanceof TileEntityPaladiumHopper) {
      positions.add(vec);
      return ((TileEntityPaladiumHopper)tile).getPointedInventory(positions);
    } 
    if (tile instanceof IInventory)
      return (IInventory)tile; 
    return null;
  }
  
  private boolean canInsert(IInventory inv, ItemStack item, int slot, int side) {
    return !inv.func_94041_b(slot, item) ? false : ((!(inv instanceof ISidedInventory) || ((ISidedInventory)inv).func_102007_a(slot, item, side)));
  }
  
  private boolean canExtract(IInventory inv, ItemStack item, int slot, int side) {
    return (!(inv instanceof ISidedInventory) || ((ISidedInventory)inv).func_102008_b(slot, item, side));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\tileentity\TileEntityPaladiumHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */