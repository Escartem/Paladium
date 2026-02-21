package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBruteforcer extends TileEntity implements IInventory, ISidedInventory {
  public ItemStack[] content = new ItemStack[54];
  
  public boolean openning;
  
  public boolean closing;
  
  public float prevLid;
  
  public float lid;
  
  public AbstractTileEntitySafeChest validChest;
  
  public boolean started;
  
  public long ticks;
  
  public long ticksWorking;
  
  public long ticksWorkingMax = -1L;
  
  public boolean found;
  
  public int func_70302_i_() {
    return this.content.length;
  }
  
  public void start() {
    this.started = true;
    func_70296_d();
  }
  
  @SideOnly(Side.CLIENT)
  public float getWorkProgress() {
    return (this.ticksWorkingMax > 0L) ? ((float)this.ticks / (float)this.ticksWorkingMax) : 0.0F;
  }
  
  @SideOnly(Side.CLIENT)
  public float getFindiumProgress() {
    return (float)this.ticks / 200.0F;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.content[slot];
  }
  
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
  
  private boolean isValidTileEntity(TileEntity tile) {
    return (tile == null) ? false : (tile instanceof AbstractTileEntitySafeChest);
  }
  
  public AbstractTileEntitySafeChest getValidSafeChest() {
    TileEntity tile = null;
    for (int x = this.field_145851_c - 1; x <= this.field_145851_c + 1; x++) {
      tile = func_145831_w().func_147438_o(x, this.field_145848_d, this.field_145849_e);
      if (isValidTileEntity(tile))
        return (AbstractTileEntitySafeChest)tile; 
    } 
    for (int y = this.field_145848_d - 1; y <= this.field_145848_d + 1; y++) {
      tile = func_145831_w().func_147438_o(this.field_145851_c, y, this.field_145849_e);
      if (isValidTileEntity(tile))
        return (AbstractTileEntitySafeChest)tile; 
    } 
    for (int z = this.field_145849_e - 1; z <= this.field_145849_e + 1; z++) {
      tile = func_145831_w().func_147438_o(this.field_145851_c, this.field_145848_d, z);
      if (isValidTileEntity(tile))
        return (AbstractTileEntitySafeChest)tile; 
    } 
    return null;
  }
  
  private void resetChest() {
    this.started = false;
    this.found = false;
    this.ticks = 0L;
    this.ticksWorking = 0L;
  }
  
  public void func_145845_h() {
    AbstractTileEntitySafeChest tile = getValidSafeChest();
    if (tile == null) {
      resetChest();
      super.func_145845_h();
      return;
    } 
    if (this.validChest == null || this.validChest != tile) {
      resetChest();
      if (!(func_145831_w()).field_72995_K) {
        long rMax = (long)Math.pow(10.0D, tile.getCodeMaxLength()) / 2L * 20L;
        this.ticksWorkingMax = (long)(Math.random() * rMax);
      } 
      this.validChest = tile;
      super.func_145845_h();
      return;
    } 
    if (!this.started)
      this.ticks = 0L; 
    if (this.started && !this.found) {
      ItemStack stack = func_70301_a(0);
      if (stack == null || stack.func_77973_b() != ItemsRegister.FINDIUM || stack.field_77994_a <= 0) {
        this.started = false;
      } else {
        this.ticks++;
        if (this.ticks >= 200L) {
          func_70298_a(0, 1);
          if (stack.field_77994_a == 0)
            this.started = false; 
          this.ticks = 0L;
        } 
        if (this.started && !(func_145831_w()).field_72995_K)
          if (this.ticksWorking >= this.ticksWorkingMax && this.ticksWorkingMax >= 0L) {
            this.found = true;
            this.started = false;
            func_70296_d();
          } else {
            this.ticksWorking++;
          }  
      } 
    } 
    super.func_145845_h();
  }
  
  public void func_70296_d() {
    super.func_70296_d();
    func_145831_w().func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
  
  public String func_145825_b() {
    return "tile.Bruteforcer";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public void func_70295_k_() {
    this.openning = true;
    this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v
        .nextFloat() * 0.1F + 0.9F);
  }
  
  public void func_70305_f() {
    this.closing = true;
    this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v
        .nextFloat() * 0.1F + 0.9F);
  }
  
  public boolean func_94041_b(int slot, ItemStack item) {
    return true;
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
    compound.func_74772_a("TicksWorking", this.ticksWorking);
    compound.func_74772_a("TicksMax", this.ticksWorkingMax);
    compound.func_74772_a("Ticks", this.ticks);
    compound.func_74757_a("Found", this.found);
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
    compound.func_74757_a("Started", this.started);
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
    this.ticksWorking = compound.func_74763_f("TicksWorking");
    this.ticksWorkingMax = compound.func_74763_f("TicksMax");
    this.started = compound.func_74767_n("Started");
    this.found = compound.func_74767_n("Found");
    this.ticks = compound.func_74763_f("Ticks");
  }
  
  public int[] func_94128_d(int side) {
    return new int[0];
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    return true;
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return true;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound tag = new NBTTagCompound();
    func_145841_b(tag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, tag);
  }
  
  public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityBruteforcer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */