package fr.paladium.palamod.modules.paladium.common.container;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palajobs.core.pojo.objectives.types.CobbleBreakerObjective;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.achievements.types.CobbleBreakerAchievement;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.paladium.common.logics.TileCobbleBreaker;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerCobbleBreaker extends PaladiumContainer {
  private TileCobbleBreaker tileCobbleBreaker;
  
  private ItemStack clicked;
  
  private EntityPlayer player;
  
  public ContainerCobbleBreaker(SimpleGHandler.GuiHandlerData data) {
    this((TileCobbleBreaker)data.getTileEntity(TileCobbleBreaker.class), data.getPlayer());
  }
  
  public ContainerCobbleBreaker(TileCobbleBreaker tile, final EntityPlayer player) {
    this.tileCobbleBreaker = tile;
    this.tileCobbleBreaker.func_70295_k_();
    this.player = player;
    func_75146_a(new Slot((IInventory)tile, 0, 91, 10) {
          public boolean func_75214_a(@Nullable ItemStack stack) {
            if (stack.func_77973_b().equals(Item.func_150898_a(Blocks.field_150347_e)) || stack
              .func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage)
              return true; 
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 1, 39, 52) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
              CobbleBreakerObjective.performCheck(player, 
                  (ContainerCobbleBreaker.this.clicked == null) ? item : ContainerCobbleBreaker.this.clicked);
              CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
            } 
          }
          
          public boolean func_75214_a(@Nullable ItemStack stack) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 2, 60, 52) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
              CobbleBreakerObjective.performCheck(player, 
                  (ContainerCobbleBreaker.this.clicked == null) ? item : ContainerCobbleBreaker.this.clicked);
              CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
            } 
          }
          
          public boolean func_75214_a(@Nullable ItemStack stack) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 3, 79, 52) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
              CobbleBreakerObjective.performCheck(player, 
                  (ContainerCobbleBreaker.this.clicked == null) ? item : ContainerCobbleBreaker.this.clicked);
              CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
            } 
          }
          
          public boolean func_75214_a(@Nullable ItemStack stack) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 4, 99, 52) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
              CobbleBreakerObjective.performCheck(player, 
                  (ContainerCobbleBreaker.this.clicked == null) ? item : ContainerCobbleBreaker.this.clicked);
              CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
            } 
          }
          
          public boolean func_75214_a(@Nullable ItemStack stack) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 5, 119, 52) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
              CobbleBreakerObjective.performCheck(player, 
                  (ContainerCobbleBreaker.this.clicked == null) ? item : ContainerCobbleBreaker.this.clicked);
              CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
            } 
          }
          
          public boolean func_75214_a(@Nullable ItemStack stack) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 6, 139, 52) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
              CobbleBreakerObjective.performCheck(player, 
                  (ContainerCobbleBreaker.this.clicked == null) ? item : ContainerCobbleBreaker.this.clicked);
              CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
            } 
          }
          
          public boolean func_75214_a(@Nullable ItemStack stack) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 7, 161, 20) {
          public boolean func_75214_a(@Nullable ItemStack stack) {
            if (JobsBridge.canUseCraft(player, stack) && 
              stack.func_77973_b() instanceof fr.paladium.palamod.modules.miner.item.ItemCobbleBreakerUpgrade)
              return true; 
            return false;
          }
          
          public int func_75219_a() {
            return 1;
          }
        });
    bingPInventory(this.player.field_71071_by);
  }
  
  public ItemStack func_75144_a(int slotID, int mouseButton, int clickType, EntityPlayer player) {
    if (slotID >= 0) {
      Slot slot = this.field_75151_b.get(slotID);
      if (slotID >= 1 && slotID <= 7 && 
        slot.func_75211_c() != null) {
        this.clicked = slot.func_75211_c().func_77946_l();
        if (clickType == 0 && mouseButton == 1) {
          this.clicked.field_77994_a = Math.round(this.clicked.field_77994_a / 2.0F);
        } else if (clickType == 4 && mouseButton == 0) {
          this.clicked.field_77994_a = 1;
        } 
      } 
    } 
    return super.func_75144_a(slotID, mouseButton, clickType, player);
  }
  
  private void bingPInventory(InventoryPlayer inventoryPlayer) {
    int l1 = 7;
    for (int l = 9; l <= 17; l++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, l, l1, 83));
      l1 += 20;
    } 
    int a1 = 7;
    for (int a = 18; a <= 26; a++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, a, a1, 105));
      a1 += 20;
    } 
    int b1 = 7;
    for (int b = 27; b <= 35; b++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, b, b1, 127));
      b1 += 20;
    } 
    int c1 = 7;
    for (int c = 0; c <= 8; c++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, c, c1, 150));
      c1 += 20;
    } 
  }
  
  public void onShiftClick(EntityPlayer player, int slotIndex, Slot slot, int defaultStackSize, ItemStack itemStack, IInventory target) {
    if (!player.field_70170_p.field_72995_K) {
      int stackSize = defaultStackSize - (slot.func_75211_c()).field_77994_a;
      ItemStack realItem = itemStack.func_77946_l();
      realItem.field_77994_a = stackSize;
      if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
        CobbleBreakerObjective.performCheck(player, realItem);
        CobbleBreakerAchievement.performCheck(player, (realItem.field_77994_a == 0) ? 1 : realItem.field_77994_a);
      } 
    } 
  }
  
  public void func_75134_a(EntityPlayer playerIn) {
    super.func_75134_a(playerIn);
    this.tileCobbleBreaker.func_70305_f();
  }
  
  public boolean func_75145_c(EntityPlayer playerIn) {
    return this.tileCobbleBreaker.func_70300_a(playerIn);
  }
  
  public int getPlayerInventoryPosition() {
    return 8;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ContainerCobbleBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */