package fr.paladium.palatrade.common.container;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palatrade.common.container.inventory.InventoryTrade;
import fr.paladium.palatrade.common.network.SCUnvalidateTargetTradePacket;
import fr.paladium.palatrade.common.network.SCUnvalidateTradePacket;
import fr.paladium.palatrade.common.network.SCUpdateItemsTradePacket;
import fr.paladium.palatrade.common.utils.Trade;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.server.manager.TradeManager;
import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrade extends PaladiumContainer {
  private boolean validated;
  
  private InventoryTrade tradeInv;
  
  public boolean isValidated() {
    return this.validated;
  }
  
  public InventoryTrade getTradeInv() {
    return this.tradeInv;
  }
  
  public ContainerTrade(IInventory playerInv, InventoryTrade tradeInv) {
    this.validated = false;
    this.tradeInv = tradeInv;
    int i;
    for (i = 0; i < 21; i++) {
      func_75146_a(new Slot((IInventory)tradeInv, i, 0, 0) {
            public boolean func_75214_a(ItemStack stack) {
              return !ContainerTrade.this.validated;
            }
            
            public boolean func_82869_a(EntityPlayer player) {
              return !ContainerTrade.this.validated;
            }
          });
    } 
    for (i = 21; i < 42; i++) {
      func_75146_a(new Slot((IInventory)tradeInv, i, 0, 0) {
            public boolean func_75214_a(ItemStack stack) {
              return false;
            }
            
            public boolean func_82869_a(EntityPlayer player) {
              return false;
            }
          });
    } 
    for (int y = 0; y < 3; y++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot(playerInv, j + y * 9 + 9, 0, 0)); 
    } 
    for (int x = 0; x < 9; x++)
      func_75146_a(new Slot(playerInv, x, 0, 0)); 
  }
  
  public ItemStack func_75144_a(int slotId, int dragType, int clickType, EntityPlayer player) {
    if (slotId >= 21 && slotId < 42)
      return null; 
    ItemStack result = super.func_75144_a(slotId, dragType, clickType, player);
    if (!player.field_70170_p.field_72995_K) {
      Trade trade = TradeManager.getInstance().getTrade(player);
      EntityPlayer target = TradeManager.getInstance().getTradePlayer(player);
      Trade tradeTarget = TradeManager.getInstance().getTrade(target);
      if (trade != null && tradeTarget != null && target instanceof EntityPlayerMP) {
        if (trade.isPlayerValidated())
          return result; 
        tradeTarget.setLastEdit(System.currentTimeMillis());
        if (tradeTarget.isPlayerValidated()) {
          tradeTarget.setPlayerValidated(false);
          ((ContainerTrade)target.field_71070_bA).setValidated(false);
          OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTargetTradePacket(), (EntityPlayerMP)player);
          OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Le trade a été modifié", 50, false), (EntityPlayerMP)target);
        } 
        trade.setItems((ItemStack[])Arrays.<Object>copyOfRange((Object[])this.tradeInv.getContent().clone(), 0, 21));
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUpdateItemsTradePacket(trade.getItems()), (EntityPlayerMP)target);
      } 
    } 
    return result;
  }
  
  public int getPlayerInventoryPosition() {
    return 42;
  }
  
  public void setValidated(boolean validated) {
    this.validated = validated;
    this.tradeInv.setValidated(validated);
  }
  
  public void func_75134_a(EntityPlayer player) {
    Trade trade = TradeManager.getInstance().getTrade(player);
    InventoryPlayer inventoryplayer = player.field_71071_by;
    if (trade == null || !trade.isProcessing())
      for (int i = 0; i < 21; i++) {
        ItemStack item = this.tradeInv.func_70304_b(i);
        if (item != null)
          if (!inventoryplayer.func_70441_a(item))
            player.func_71019_a(item, false);  
      }  
    if (inventoryplayer.func_70445_o() != null) {
      if (!inventoryplayer.func_70441_a(inventoryplayer.func_70445_o()))
        player.func_71019_a(inventoryplayer.func_70445_o(), false); 
      inventoryplayer.func_70437_b((ItemStack)null);
    } 
    player.field_71071_by.func_70296_d();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\container\ContainerTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */