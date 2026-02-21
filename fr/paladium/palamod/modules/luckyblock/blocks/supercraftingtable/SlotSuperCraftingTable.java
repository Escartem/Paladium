package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySuperCraftingTable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class SlotSuperCraftingTable extends Slot {
  private final IInventory craftMatrix;
  
  private EntityPlayer thePlayer;
  
  private int amountCrafted;
  
  private World worldObj;
  
  private int posX;
  
  private int posY;
  
  private int posZ;
  
  public SlotSuperCraftingTable(EntityPlayer p_i1823_1_, IInventory p_i1823_2_, IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_, int p_i1823_6_, World worldObj, int posX, int posY, int posZ) {
    super(p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_);
    this.thePlayer = p_i1823_1_;
    this.craftMatrix = p_i1823_2_;
    this.worldObj = worldObj;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
  }
  
  public SlotSuperCraftingTable(IInventory result, int id, int x, int y, EntityPlayer player, IInventory craftMatrix, World worldObj, int posX, int posY, int posZ, double size) {
    super(result, id, x, y);
    this.thePlayer = player;
    this.craftMatrix = craftMatrix;
    this.worldObj = worldObj;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
  }
  
  public boolean func_75214_a(ItemStack p_75214_1_) {
    return false;
  }
  
  public ItemStack func_75209_a(int p_75209_1_) {
    if (func_75216_d())
      this.amountCrafted += Math.min(p_75209_1_, (func_75211_c()).field_77994_a); 
    return super.func_75209_a(p_75209_1_);
  }
  
  protected void func_75210_a(ItemStack p_75210_1_, int p_75210_2_) {
    this.amountCrafted += p_75210_2_;
    func_75208_c(p_75210_1_);
  }
  
  protected void func_75208_c(ItemStack p_75208_1_) {
    p_75208_1_.func_77980_a(this.thePlayer.field_70170_p, this.thePlayer, this.amountCrafted);
    this.amountCrafted = 0;
  }
  
  public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
    FMLCommonHandler.instance().firePlayerCraftingEvent(p_82870_1_, p_82870_2_, this.craftMatrix);
    func_75208_c(p_82870_2_);
    Block block = this.worldObj.func_147439_a(this.posX, this.posY, this.posZ);
    if (block instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockSuperCraftingTable && this.worldObj
      .func_147438_o(this.posX, this.posY, this.posZ) instanceof TileEntitySuperCraftingTable) {
      TileEntitySuperCraftingTable tileEntitySuperCraftingTable = (TileEntitySuperCraftingTable)this.worldObj.func_147438_o(this.posX, this.posY, this.posZ);
      tileEntitySuperCraftingTable.setDurability(tileEntitySuperCraftingTable.getDurability() + 1);
      if (tileEntitySuperCraftingTable.getDurability() >= 15) {
        this.worldObj.func_147449_b(this.posX, this.posY, this.posZ, Blocks.field_150350_a);
        p_82870_1_.func_71053_j();
      } 
    } 
    for (int i = 0; i < this.craftMatrix.func_70302_i_(); i++) {
      ItemStack itemstack1 = this.craftMatrix.func_70301_a(i);
      if (itemstack1 != null) {
        this.craftMatrix.func_70298_a(i, 1);
        if (itemstack1.func_77973_b().hasContainerItem(itemstack1)) {
          ItemStack itemstack2 = itemstack1.func_77973_b().getContainerItem(itemstack1);
          if (itemstack2 != null && itemstack2.func_77984_f() && itemstack2
            .func_77960_j() > itemstack2.func_77958_k()) {
            MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(this.thePlayer, itemstack2));
          } else if (!itemstack1.func_77973_b().func_77630_h(itemstack1) || 
            !this.thePlayer.field_71071_by.func_70441_a(itemstack2)) {
            if (this.craftMatrix.func_70301_a(i) == null) {
              this.craftMatrix.func_70299_a(i, itemstack2);
            } else {
              this.thePlayer.func_71019_a(itemstack2, false);
            } 
          } 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtable\SlotSuperCraftingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */