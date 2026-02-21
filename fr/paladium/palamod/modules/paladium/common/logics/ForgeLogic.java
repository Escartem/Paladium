package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockForge;
import fr.paladium.palamod.modules.paladium.utils.EnumAllowItemsForge;
import fr.paladium.tutorial.common.event.PalaForgeCraftEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.MinecraftForge;

public class ForgeLogic extends PaladiumTileInventory implements ISidedInventory {
  private int work = 0;
  
  private int burn = 0;
  
  private int burnMax = 100;
  
  public ForgeLogic() {
    super("tile.Forge", 3);
  }
  
  public int getWork() {
    return this.work;
  }
  
  public float getWorkProgress() {
    return this.work / 100.0F;
  }
  
  public float getBurnProgress() {
    return this.burn / this.burnMax;
  }
  
  public void setWork(int work) {
    this.work = work;
  }
  
  public int getBurn() {
    return this.burn;
  }
  
  public void setBurn(int burn) {
    this.burn = burn;
  }
  
  public int getBurnMax() {
    return this.burnMax;
  }
  
  public void setBurnMax(int burnMax) {
    this.burnMax = burnMax;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("work", this.work);
    compound.func_74768_a("burn", this.burn);
    compound.func_74768_a("burnMax", this.burnMax);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.work = compound.func_74762_e("work");
    this.burn = compound.func_74762_e("burn");
    this.burnMax = compound.func_74762_e("burnMax");
  }
  
  private void callEvent(ItemStack stack) {
    if (this.field_145850_b.field_72995_K)
      return; 
    EntityPlayer player = MonthlyUtils.getClosest(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    if (player == null)
      return; 
    MinecraftForge.EVENT_BUS.post((Event)new PalaForgeCraftEvent(player, stack));
  }
  
  public void func_145845_h() {
    boolean flag = (this.work > 0);
    if (this.burn <= 0) {
      if ((func_70301_a(0) != null && EnumAllowItemsForge.containItem(func_70301_a(0).func_77973_b()).booleanValue()) || this.work != 0)
        if (TileEntityFurnace.func_145954_b(func_70301_a(2))) {
          this.burnMax = TileEntityFurnace.func_145952_a(func_70301_a(2));
          this.burn = TileEntityFurnace.func_145952_a(func_70301_a(2));
          func_70298_a(2, 1);
          if (!this.field_145850_b.field_72995_K && 
            flag != ((this.work > 0)))
            BlockForge.updateBlockState((this.work > 0), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e); 
        } else {
          this.work = 0;
        }  
    } else {
      if (func_70301_a(0) != null && EnumAllowItemsForge.containItem(func_70301_a(0).func_77973_b()).booleanValue() && (
        func_70301_a(1) == null || (EnumAllowItemsForge.valuesOf(func_70301_a(0).func_77973_b())
        .getItemDrop().equals(func_70301_a(1).func_77973_b()) && 
        EnumAllowItemsForge.valuesOf(func_70301_a(0).func_77973_b()).getMaxDrop() + 
        (func_70301_a(1)).field_77994_a <= func_70301_a(1).func_77976_d()))) {
        if (this.work == 100) {
          int i = func_70301_a(0).func_77958_k() - func_70301_a(0).func_77960_j();
          double pDamageC = (i * 100);
          double pDamageC1 = pDamageC / func_70301_a(0).func_77958_k();
          int pDamage = (int)pDamageC1;
          EnumAllowItemsForge enumAllowItemsForge = EnumAllowItemsForge.valuesOf(func_70301_a(0).func_77973_b());
          double dropD = (enumAllowItemsForge.getMaxDrop() * pDamage / 100);
          int drop = (int)dropD;
          func_70298_a(0, 1);
          if (func_70301_a(1) == null) {
            if (drop <= 0) {
              func_70299_a(1, null);
            } else {
              ItemStack itemStack = new ItemStack(enumAllowItemsForge.getItemDrop(), drop);
              func_70299_a(1, itemStack);
              callEvent(itemStack);
            } 
          } else {
            ItemStack itemStack = new ItemStack(func_70301_a(1).func_77973_b(), (func_70301_a(1)).field_77994_a + drop, func_70301_a(1).func_77960_j());
            func_70299_a(1, itemStack);
          } 
          this.work = 0;
        } else {
          int i = func_70301_a(0).func_77958_k() - func_70301_a(0).func_77960_j();
          double pDamageC = (i * 100);
          double pDamageC1 = pDamageC / func_70301_a(0).func_77958_k();
          int pDamage = (int)pDamageC1;
          EnumAllowItemsForge enumAllowItemsForge = EnumAllowItemsForge.valuesOf(func_70301_a(0).func_77973_b());
          double dropD = (enumAllowItemsForge.getMaxDrop() * pDamage / 100);
          int drop = (int)dropD;
          if (drop > 0)
            this.work++; 
        } 
      } else {
        this.work = 0;
      } 
      this.burn--;
      if (this.burn <= 0 && 
        !this.field_145850_b.field_72995_K && 
        flag != ((this.work > 0)))
        BlockForge.updateBlockState((this.work > 0), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e); 
    } 
  }
  
  public int[] func_94128_d(int side) {
    return new int[0];
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\ForgeLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */