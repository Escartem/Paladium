package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palamod.modules.paladium.common.crafting.BowMachineRecipies;
import fr.paladium.palamod.modules.paladium.utils.BowHelper;
import fr.paladium.tutorial.common.event.BowUpgradeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class BowMachineLogic extends PaladiumTileInventory implements ISidedInventory {
  public int workedTime;
  
  public int timeNeeded;
  
  public BowMachineLogic() {
    super("tile.BowMachine", 2);
    this.timeNeeded = 200;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (!canSmelt()) {
      this.workedTime = 0;
      return;
    } 
    if (canSmelt() && this.workedTime < this.timeNeeded)
      this.workedTime++; 
    if (canSmelt() && this.workedTime >= this.timeNeeded)
      smeltItem(); 
  }
  
  private boolean canSmelt() {
    if (getContent()[0] == null || getContent()[1] == null)
      return false; 
    if (!BowMachineRecipies.instance.hasRecipie(getContent()[0].func_77973_b()))
      return false; 
    if (!BowHelper.canApply(getContent()[1], BowMachineRecipies.instance
        .getModifier(getContent()[0].func_77973_b())))
      return false; 
    return true;
  }
  
  private EntityPlayer getClosest(int radius) {
    return this.field_145850_b.func_72977_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, radius);
  }
  
  public void smeltItem() {
    if (getContent()[0] == null || getContent()[1] == null)
      return; 
    if (!BowMachineRecipies.instance.hasRecipie(getContent()[0].func_77973_b()))
      return; 
    if (BowHelper.canApply(getContent()[1], BowMachineRecipies.instance
        .getModifier(getContent()[0].func_77973_b()))) {
      BowHelper.applyModifiers(getContent()[1], BowMachineRecipies.instance
          .getModifier(getContent()[0].func_77973_b()));
      MinecraftForge.EVENT_BUS.post((Event)new BowUpgradeEvent(getClosest(100), getContent()[1]));
      (getContent()[0]).field_77994_a--;
      if ((getContent()[0]).field_77994_a <= 0)
        getContent()[0] = null; 
    } 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("workedTime", this.workedTime);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.workedTime = compound.func_74762_e("workedTime");
  }
  
  public boolean isBurning() {
    return (this.workedTime > 0);
  }
  
  @SideOnly(Side.CLIENT)
  public int getCookProgressScaled(int value) {
    return value * this.workedTime / this.timeNeeded;
  }
  
  @SideOnly(Side.CLIENT)
  public float getCookProgression() {
    return this.workedTime / this.timeNeeded;
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\BowMachineLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */