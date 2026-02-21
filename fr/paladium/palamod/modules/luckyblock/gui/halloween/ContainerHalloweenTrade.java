package fr.paladium.palamod.modules.luckyblock.gui.halloween;

import fr.paladium.lib.apollon.container.interfaces.ApollonContainer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.HalloweenTradeConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerHalloweenTrade extends Container implements ApollonContainer {
  private InventoryHalloweenTrade[] inventories;
  
  private World world;
  
  private int posX;
  
  private int posY;
  
  private int posZ;
  
  public InventoryHalloweenTrade[] getInventories() {
    return this.inventories;
  }
  
  public World getWorld() {
    return this.world;
  }
  
  public int getPosX() {
    return this.posX;
  }
  
  public int getPosY() {
    return this.posY;
  }
  
  public int getPosZ() {
    return this.posZ;
  }
  
  public ContainerHalloweenTrade(SimpleGHandler.GuiHandlerData data) {
    this(data.getPlayer(), data.getWorld(), data.getX(), data.getY(), data.getZ(), PLuckyBlock.instance.getHalloweenConfig().getCurrentTrade(), PlayerLuckyBlockProperties.get(data.getPlayer()).getHalloweenTradeLimit());
  }
  
  public ContainerHalloweenTrade(EntityPlayer player, World world, int x, int y, int z, final HalloweenTradeConfig trade, int tradeAmount) {
    this.inventories = new InventoryHalloweenTrade[(trade == null) ? 2 : 3];
    this.world = world;
    this.posX = x;
    this.posY = y;
    this.posZ = z;
    this.inventories[0] = new InventoryHalloweenTrade(this, null, 0, new ItemStack(ItemsRegister.CANDY_PIECE, 64), new ItemStack(ItemsRegister.CANDY));
    func_75146_a(new Slot(this.inventories[0], 0, 0, 0) {
          public boolean func_75214_a(ItemStack item) {
            return item.func_77969_a((ContainerHalloweenTrade.this.inventories[0]).input);
          }
        });
    func_75146_a(new SlotHalloweenTradeResult(null, this.inventories[0], 1, 0, 0));
    this.inventories[1] = new InventoryHalloweenTrade(this, null, 0, new ItemStack(ItemsRegister.CANDY, 10), new ItemStack(ItemsRegister.CANDY_BAG));
    func_75146_a(new Slot(this.inventories[1], 0, 0, 0) {
          public boolean func_75214_a(ItemStack item) {
            return item.func_77969_a((ContainerHalloweenTrade.this.inventories[1]).input);
          }
        });
    func_75146_a(new SlotHalloweenTradeResult(null, this.inventories[1], 1, 0, 0));
    if (trade != null) {
      this.inventories[2] = new InventoryHalloweenTrade(this, trade, tradeAmount, new ItemStack(Item.func_150899_d(trade.getInput().getId()), trade.getInput().getAmount(), trade.getInput().getMeta()), new ItemStack(ItemsRegister.CANDY_PIECE, 2));
      func_75146_a(new Slot(this.inventories[2], 0, 0, 0) {
            public boolean func_75214_a(ItemStack item) {
              return ContainerHalloweenTrade.this.inventories[2].isLocked() ? false : ((trade.getInput().getMeta() == -1) ? ((item.func_77973_b() == (ContainerHalloweenTrade.this.inventories[2]).input.func_77973_b())) : item.func_77969_a((ContainerHalloweenTrade.this.inventories[2]).input));
            }
          });
      func_75146_a(new SlotHalloweenTradeResult(player, this.inventories[2], 1, 0, 0));
    } 
    InventoryPlayer playerInventory = player.field_71071_by;
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)playerInventory, j + i * 9 + 9, 0, 0)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)playerInventory, i, 0, 0)); 
  }
  
  public void func_75130_a(IInventory inventory) {
    if (inventory instanceof InventoryHalloweenTrade) {
      InventoryHalloweenTrade trade = (InventoryHalloweenTrade)inventory;
      ItemStack craftResult = null;
      if (!trade.isLocked() && trade.func_70301_a(0) != null && trade.input != null && ((trade.trade == null) ? trade.input.func_77969_a(trade.func_70301_a(0)) : ((trade.trade.getInput().getMeta() == -1) ? (trade.input.func_77973_b() == trade.func_70301_a(0).func_77973_b()) : trade.input.func_77969_a(trade.func_70301_a(0)))) && trade.input.field_77994_a <= (trade.func_70301_a(0)).field_77994_a)
        craftResult = trade.output.func_77946_l(); 
      trade.func_70299_a(1, craftResult);
    } 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slotID) {
    return null;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (!this.world.field_72995_K)
      for (int i = 0; i < this.inventories.length; i++) {
        IInventory inventory = this.inventories[i];
        ItemStack itemstack = inventory.func_70304_b(0);
        if (itemstack != null)
          player.func_71019_a(itemstack, false); 
      }  
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    if (this.world == null || this.world.func_147439_a(this.posX, this.posY, this.posZ) != BlocksRegister.HALLOWEEN_TRADE || player.func_70011_f(this.posX, this.posY, this.posZ) > 16.0D)
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\ContainerHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */