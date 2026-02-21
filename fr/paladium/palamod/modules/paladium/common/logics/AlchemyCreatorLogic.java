package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.crafting.AlchemyCreatorArrowRecipies;
import fr.paladium.palamod.modules.paladium.common.crafting.AlchemyCreatorPotionRecipies;
import fr.paladium.tutorial.common.event.AlchemyCreatorCraftEvent;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class AlchemyCreatorLogic extends PaladiumTileInventory implements ISidedInventory {
  private final int[] workingTime;
  
  private final int[] timeNeeded;
  
  public static int POTION;
  
  public static int ARROW;
  
  public static HashMap<String, AlchemyCreatorLogic> oppenedGui = new HashMap<>();
  
  public AlchemyCreatorLogic() {
    super("tile.AlchemyCreator", 9);
    this.workingTime = new int[2];
    this.timeNeeded = new int[2];
    this.timeNeeded[0] = 200;
    this.timeNeeded[1] = 20;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return !(this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) instanceof AlchemyCreatorLogic) ? false : (
      
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("workingTimePotion", this.workingTime[0]);
    compound.func_74768_a("workingTimeArrow", this.timeNeeded[1]);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.workingTime[0] = compound.func_74762_e("workingTimePotion");
    this.timeNeeded[1] = compound.func_74762_e("workingTimeArrow");
  }
  
  public void func_145845_h() {
    if (!canSmeltPotion())
      this.workingTime[0] = 0; 
    if (isBurningPotion() && canSmeltPotion())
      this.workingTime[0] = this.workingTime[0] + 1; 
    if (canSmeltPotion() && !isBurningPotion())
      this.workingTime[0] = 1; 
    if (canSmeltPotion() && this.workingTime[0] == this.timeNeeded[0]) {
      smeltItemPotion();
      this.workingTime[0] = 0;
    } 
    if (!canSmeltArrow())
      this.workingTime[1] = 0; 
    if (isBurningArrow() && canSmeltArrow())
      this.workingTime[1] = this.workingTime[1] + 1; 
    if (canSmeltArrow() && !isBurningArrow())
      this.workingTime[1] = 1; 
    if (canSmeltArrow() && this.workingTime[1] == this.timeNeeded[1]) {
      smeltItemArrow();
      this.workingTime[1] = 0;
    } 
  }
  
  private boolean canSmeltPotion() {
    if (getContent()[0] == null || getContent()[1] == null || getContent()[2] == null || 
      getContent()[3] == null)
      return false; 
    ItemStack itemstack = AlchemyCreatorPotionRecipies.getManager().getSmeltingResult(new ItemStack[] { getContent()[0], getContent()[1], getContent()[2], getContent()[3] });
    if (itemstack == null)
      return false; 
    return true;
  }
  
  private boolean canSmeltArrow() {
    if (getContent()[4] == null || getContent()[5] == null || getContent()[6] == null || 
      getContent()[7] == null)
      return false; 
    ItemStack itemstack = AlchemyCreatorArrowRecipies.getManager().getSmeltingResult(new ItemStack[] { getContent()[4], getContent()[5], getContent()[6], getContent()[7] });
    if (itemstack == null || (getContent()[8] != null && 
      (getContent()[8]).field_77994_a > func_70297_j_() - itemstack.field_77994_a))
      return false; 
    if (getContent()[8] != null && itemstack.func_77973_b() != getContent()[8].func_77973_b())
      return false; 
    if ((getContent()[7]).field_77994_a < itemstack.field_77994_a)
      return false; 
    return true;
  }
  
  public boolean isBurningPotion() {
    return (this.workingTime[0] > 0);
  }
  
  public boolean isBurningArrow() {
    return (this.workingTime[1] > 0);
  }
  
  public void smeltItemPotion() {
    if (canSmeltPotion()) {
      ItemStack itemstack = AlchemyCreatorPotionRecipies.getManager().getSmeltingResult(new ItemStack[] { getContent()[0], getContent()[1], getContent()[2], getContent()[3] });
      callEvent(itemstack);
      getContent()[3] = itemstack.func_77946_l();
      (getContent()[0]).field_77994_a--;
      (getContent()[1]).field_77994_a--;
      (getContent()[2]).field_77994_a--;
      if ((getContent()[0]).field_77994_a <= 0)
        getContent()[0] = null; 
      if ((getContent()[1]).field_77994_a <= 0)
        getContent()[1] = null; 
      if ((getContent()[2]).field_77994_a <= 0)
        getContent()[2] = null; 
      if ((getContent()[3]).field_77994_a <= 0)
        getContent()[3] = null; 
    } 
  }
  
  public void smeltItemArrow() {
    if (canSmeltArrow()) {
      ItemStack itemstack = AlchemyCreatorArrowRecipies.getManager().getSmeltingResult(new ItemStack[] { getContent()[4], getContent()[5], getContent()[6], getContent()[7] });
      callEvent(itemstack);
      if (getContent()[8] == null) {
        getContent()[8] = itemstack.func_77946_l();
      } else if ((getContent()[8]).field_77994_a > 0) {
        (getContent()[8]).field_77994_a += itemstack.field_77994_a;
      } 
      (getContent()[4]).field_77994_a--;
      (getContent()[5]).field_77994_a--;
      (getContent()[6]).field_77994_a--;
      (getContent()[7]).field_77994_a -= itemstack.field_77994_a;
      if ((getContent()[4]).field_77994_a <= 0)
        getContent()[4] = null; 
      if ((getContent()[5]).field_77994_a <= 0)
        getContent()[5] = null; 
      if ((getContent()[6]).field_77994_a <= 0)
        getContent()[6] = null; 
      if ((getContent()[7]).field_77994_a <= 0)
        getContent()[7] = null; 
    } 
  }
  
  public void callEvent(ItemStack result) {
    if (this.field_145850_b.field_72995_K || result == null)
      return; 
    MinecraftForge.EVENT_BUS.post((Event)new AlchemyCreatorCraftEvent(MonthlyUtils.getClosest(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e), result.func_77946_l()));
  }
  
  @SideOnly(Side.CLIENT)
  public int getCookProgressPotion() {
    return this.workingTime[0];
  }
  
  @SideOnly(Side.CLIENT)
  public int getCookProgressArrow() {
    return this.workingTime[1];
  }
  
  @SideOnly(Side.CLIENT)
  public float getScaledCookProgressPotion() {
    return this.workingTime[0] / this.timeNeeded[0];
  }
  
  @SideOnly(Side.CLIENT)
  public float getScaledCookProgressArrow() {
    return this.workingTime[1] / this.timeNeeded[1];
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\AlchemyCreatorLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */