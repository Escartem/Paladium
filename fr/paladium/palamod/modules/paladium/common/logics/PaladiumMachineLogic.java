package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.paladium.common.crafting.PaladiumMachineRecipies;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.palapass.common.quest.palamod.PalamachineCraftQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class PaladiumMachineLogic extends TileEntity implements ISidedInventory {
  private ItemStack[] content = new ItemStack[6];
  
  private int workingTime = 0;
  
  private int timeNeeded = 200;
  
  private EntityPlayer player;
  
  public ItemStack func_70298_a(int slotIndex, int amount) {
    if (this.content[slotIndex] != null) {
      if ((this.content[slotIndex]).field_77994_a <= amount) {
        ItemStack itemStack = this.content[slotIndex];
        this.content[slotIndex] = null;
        func_70296_d();
        return itemStack;
      } 
      ItemStack itemstack = this.content[slotIndex].func_77979_a(amount);
      if ((this.content[slotIndex]).field_77994_a == 0)
        this.content[slotIndex] = null; 
      func_70296_d();
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack func_70304_b(int slotIndex) {
    if (this.content[slotIndex] != null) {
      ItemStack itemstack = this.content[slotIndex];
      this.content[slotIndex] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void func_70299_a(int slotIndex, ItemStack stack) {
    this.content[slotIndex] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    func_70296_d();
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    return !(slot == 5);
  }
  
  public boolean isBurning() {
    return (this.workingTime > 0);
  }
  
  public void func_145845_h() {
    if (isBurning() && canSmelt())
      this.workingTime++; 
    if (canSmelt() && !isBurning())
      this.workingTime = 1; 
    if (canSmelt() && this.workingTime == this.timeNeeded) {
      smeltItem();
      this.workingTime = 0;
    } 
    if (!canSmelt())
      this.workingTime = 0; 
  }
  
  private boolean canSmelt() {
    if (this.content[0] == null || this.content[1] == null || this.content[2] == null || this.content[3] == null || this.content[4] == null)
      return false; 
    ItemStack itemstack = PaladiumMachineRecipies.getSmeltingResult(new ItemStack[] { this.content[0], this.content[1], this.content[2], this.content[3], this.content[4] });
    if (this.player == null || !JobsBridge.canUseCraft(this.player, itemstack, false) || itemstack == null)
      return false; 
    if (this.content[0].func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemStickBase && 
      this.content[0].func_77978_p() != null && 
      this.content[0].func_77978_p().func_74764_b("repair") && 
      this.content[0].func_77978_p().func_74762_e("repair") >= 5)
      return false; 
    if (this.content[5] == null)
      return true; 
    int result = (this.content[5]).field_77994_a + itemstack.field_77994_a;
    return (result <= func_70297_j_() && result <= this.content[5].func_77976_d());
  }
  
  public void smeltItem() {
    ItemStack itemstack = PaladiumMachineRecipies.getSmeltingResult(new ItemStack[] { this.content[0], this.content[1], this.content[2], this.content[3], this.content[4] });
    if (this.content[5] == null) {
      int refill;
      NBTTagCompound nbtTagCompound;
      ItemStack itemResult = itemstack.func_77946_l();
      if (this.content[0].func_77978_p() != null && this.content[0].func_77978_p().func_74764_b("refill")) {
        refill = this.content[0].func_77978_p().func_74762_e("refill") + 1;
      } else {
        refill = 1;
      } 
      if (itemResult.func_77978_p() != null) {
        nbtTagCompound = itemResult.func_77978_p();
      } else {
        nbtTagCompound = new NBTTagCompound();
      } 
      nbtTagCompound.func_74768_a("refill", refill);
      itemResult.func_77982_d(nbtTagCompound);
      if (this.content[0].func_77986_q() != null)
        itemResult.func_77978_p().func_74782_a("ench", (NBTBase)this.content[0].func_77986_q()); 
      if (this.content[0] != null && this.content[0].func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemStickBase) {
        int repair = 0;
        if (this.content[0] != null && this.content[0].func_77978_p() != null && this.content[0].func_77978_p().func_74764_b("repair")) {
          repair = this.content[0].func_77978_p().func_74762_e("repair");
          repair++;
        } else {
          repair = 0;
        } 
        if (repair <= 5) {
          itemResult.func_77978_p().func_74768_a("repair", repair);
          this.content[5] = itemResult;
        } 
      } else if (itemstack != null) {
        if (this.player instanceof net.minecraft.entity.player.EntityPlayerMP) {
          PalamachineCraftQuest.trigger(this.player, itemstack, 1);
          if (PFactions.instance != null && PFactions.instance.getImpl() != null)
            PFactions.instance.getImpl().onItemCraftPalamachine(this.player, itemstack); 
        } 
        this.content[5] = itemResult;
        this.content[5].func_77973_b().func_77622_d(this.content[5], this.field_145850_b, this.player);
      } 
    } else if (this.content[5].func_77973_b() == itemstack.func_77973_b()) {
      (this.content[5]).field_77994_a += itemstack.field_77994_a;
    } 
    for (int itr = 0; itr < 5; itr++) {
      ItemStack itemStack = this.content[0];
      if (itemStack != null && itemStack.func_77973_b() != null)
        if (itemStack.func_77973_b() == ItemsRegister.PALADIUM_INGOT) {
          PPalaDynamique.instance.addDestroyed("PALA_MACHINE", 1.0D);
        } else if (itemStack.func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM)) {
          PPalaDynamique.instance.addDestroyed("PALA_MACHINE", 9.0D);
        }  
    } 
    (this.content[0]).field_77994_a--;
    (this.content[1]).field_77994_a--;
    (this.content[2]).field_77994_a--;
    (this.content[3]).field_77994_a--;
    (this.content[4]).field_77994_a--;
    if ((this.content[0]).field_77994_a <= 0)
      this.content[0] = null; 
    if ((this.content[1]).field_77994_a <= 0)
      this.content[1] = null; 
    if ((this.content[2]).field_77994_a <= 0)
      this.content[2] = null; 
    if ((this.content[3]).field_77994_a <= 0)
      this.content[3] = null; 
    if ((this.content[4]).field_77994_a <= 0)
      this.content[4] = null; 
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public void setPlayer(EntityPlayer player) {
    this.player = player;
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  @SideOnly(Side.CLIENT)
  public int getCookProgress() {
    return this.workingTime * 16 / this.timeNeeded;
  }
  
  @SideOnly(Side.CLIENT)
  public float getCookFloatProgress() {
    return this.workingTime / this.timeNeeded;
  }
  
  public int[] func_94128_d(int side) {
    return new int[0];
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.content[slot];
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public String func_145825_b() {
    return "tile.PaladiumMachine";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public int func_70302_i_() {
    return this.content.length;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    NBTTagList nbttaglist = compound.func_150295_c("Items", 10);
    this.content = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
      if (j >= 0 && j < this.content.length)
        this.content[j] = ItemStack.func_77949_a(nbttagcompound1); 
    } 
    this.workingTime = compound.func_74765_d("workingTime");
    this.timeNeeded = compound.func_74765_d("workingTimeNeeded");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.content.length; i++) {
      if (this.content[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.content[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      } 
    } 
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
    compound.func_74777_a("workingTime", (short)this.workingTime);
    compound.func_74777_a("workingTimeNeeded", (short)this.timeNeeded);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\PaladiumMachineLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */