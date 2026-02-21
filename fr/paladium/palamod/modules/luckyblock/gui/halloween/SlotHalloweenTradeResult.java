package fr.paladium.palamod.modules.luckyblock.gui.halloween;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class SlotHalloweenTradeResult extends Slot {
  private final EntityPlayer player;
  
  private int amountCrafted;
  
  public SlotHalloweenTradeResult(EntityPlayer player, IInventory inventory, int id, int x, int y) {
    super(inventory, id, x, y);
    this.player = player;
  }
  
  public boolean func_75214_a(ItemStack item) {
    return false;
  }
  
  public ItemStack func_75209_a(int amount) {
    if (func_75216_d())
      this.amountCrafted++; 
    return super.func_75209_a(amount);
  }
  
  protected void func_75210_a(ItemStack item, int amount) {
    this.amountCrafted++;
    func_75208_c(item);
  }
  
  protected void func_75208_c(ItemStack item) {
    if (this.player != null && this.field_75224_c instanceof InventoryHalloweenTrade) {
      PlayerLuckyBlockProperties.get(this.player).setHalloweenTradeLimit(PlayerLuckyBlockProperties.get(this.player).getHalloweenTradeLimit() + this.amountCrafted);
      InventoryHalloweenTrade trade = (InventoryHalloweenTrade)this.field_75224_c;
      int limit = PlayerLuckyBlockProperties.get(this.player).getHalloweenTradeLimit();
      trade.tradeAmount = limit;
    } 
    this.amountCrafted = 0;
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack item) {
    ItemStack itemstack1 = this.field_75224_c.func_70301_a(0);
    if (!(this.field_75224_c instanceof InventoryHalloweenTrade))
      return; 
    func_75208_c(item);
    InventoryHalloweenTrade trade = (InventoryHalloweenTrade)this.field_75224_c;
    if (itemstack1 != null) {
      this.field_75224_c.func_70298_a(0, trade.input.field_77994_a);
      if (itemstack1.func_77973_b().hasContainerItem(itemstack1)) {
        ItemStack itemstack2 = itemstack1.func_77973_b().getContainerItem(itemstack1);
        if (itemstack2 != null && itemstack2.func_77984_f() && itemstack2.func_77960_j() > itemstack2.func_77958_k()) {
          MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(player, itemstack2));
          return;
        } 
        if (!itemstack1.func_77973_b().func_77630_h(itemstack1) || !player.field_71071_by.func_70441_a(itemstack2))
          if (this.field_75224_c.func_70301_a(0) == null) {
            this.field_75224_c.func_70299_a(0, itemstack2);
          } else {
            player.func_71019_a(itemstack2, false);
          }  
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\SlotHalloweenTradeResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */