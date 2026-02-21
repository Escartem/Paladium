package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDyeingMachine extends TileEntity implements IInventory, ISidedInventory {
  public ItemStack[] items = new ItemStack[3];
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    NBTTagList nbttaglist = compound.func_150295_c("Items", 10);
    this.items = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte b0 = nbttagcompound1.func_74771_c("Slot");
      if (b0 >= 0 && b0 < this.items.length)
        this.items[b0] = ItemStack.func_77949_a(nbttagcompound1); 
    } 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.items.length; i++) {
      if (this.items[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.items[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      } 
    } 
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
  }
  
  public int func_70302_i_() {
    return 3;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.items[slot];
  }
  
  public ItemStack func_70298_a(int slot, int count) {
    if (slot == 2)
      for (int i = 0; i < 2; i++) {
        if (this.items[i] != null) {
          (this.items[i]).field_77994_a -= count;
          if ((this.items[i]).field_77994_a == 0)
            this.items[i] = null; 
        } 
      }  
    if (slot < 2)
      if (this.items[0] == null || this.items[1] == null) {
        this.items[2] = null;
      } else if (this.items[1].func_77973_b() != Items.field_151100_aR) {
        this.items[2] = null;
      }  
    if (this.items[slot] != null) {
      if ((this.items[slot]).field_77994_a <= count) {
        ItemStack itemStack = this.items[slot].func_77946_l();
        this.items[slot] = null;
        func_70296_d();
        return itemStack;
      } 
      ItemStack itemstack = this.items[slot].func_77979_a(count).func_77946_l();
      if ((this.items[slot]).field_77994_a == 0)
        this.items[slot] = null; 
      func_70296_d();
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack func_70304_b(int slot) {
    if (this.items[slot] != null) {
      ItemStack itemstack = this.items[slot];
      this.items[slot] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void func_145845_h() {
    if (this.items[0] != null && this.items[1] != null && 
      this.items[1].func_77973_b() == Items.field_151100_aR) {
      ItemStack result = this.items[0].func_77946_l();
      int stackSize = Math.min((this.items[0]).field_77994_a, (this.items[1]).field_77994_a);
      result.field_77994_a = stackSize;
      NBTTagCompound compound = new NBTTagCompound();
      if (result.func_77942_o())
        compound = result.func_77978_p(); 
      compound.func_74768_a("palamod_colored", ItemDye.field_150922_c[this.items[1]
            .func_77960_j()]);
      result.func_77982_d(compound);
      this.items[2] = result;
    } 
    if (this.items[0] == null || this.items[1] == null) {
      this.items[2] = null;
    } else if (this.items[1].func_77973_b() != Items.field_151100_aR) {
      this.items[2] = null;
    } 
    super.func_145845_h();
  }
  
  public void func_70299_a(int slot, ItemStack item) {
    this.items[slot] = item;
    if (item != null && item.field_77994_a > func_70297_j_())
      item.field_77994_a = func_70297_j_(); 
    func_70296_d();
  }
  
  public String func_145825_b() {
    return "Dyeing Machine";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && par1EntityPlayer
      .func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) < 64.0D);
  }
  
  public boolean func_94041_b(int slot, ItemStack item) {
    return !(slot == 2);
  }
  
  public int[] func_94128_d(int slot) {
    return null;
  }
  
  public boolean func_102007_a(int slot, ItemStack item, int p_102007_3_) {
    return false;
  }
  
  public boolean func_102008_b(int slot, ItemStack item, int p_102008_3_) {
    return false;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound tag = new NBTTagCompound();
    func_145841_b(tag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, tag);
  }
  
  public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {
    this.items[2] = null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityDyeingMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */