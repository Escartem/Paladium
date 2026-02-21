package fr.paladium.palamod.mixins.inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.palapass.Palapass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({SlotFurnace.class})
public abstract class IMixinSlotFurnace extends Slot {
  @Shadow
  private EntityPlayer field_75229_a;
  
  @Shadow
  private int field_75228_b;
  
  private ItemStack last = null;
  
  public IMixinSlotFurnace(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
    super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
  }
  
  public boolean func_82869_a(EntityPlayer p_82869_1_) {
    if (super.func_82869_a(p_82869_1_))
      return true; 
    return false;
  }
  
  @Overwrite
  protected void func_75208_c(ItemStack p_75208_1_) {
    p_75208_1_.func_77980_a(this.field_75229_a.field_70170_p, this.field_75229_a, this.field_75228_b);
    if (!this.field_75229_a.field_70170_p.field_72995_K) {
      int i = this.field_75228_b;
      float f = FurnaceRecipes.func_77602_a().func_151398_b(p_75208_1_);
      if (f == 0.0F) {
        i = 0;
      } else if (f < 1.0F) {
        int j = MathHelper.func_76141_d(i * f);
        if (j < MathHelper.func_76123_f(i * f) && (float)Math.random() < i * f - j)
          j++; 
        i = j;
      } 
      while (i > 0) {
        int j = EntityXPOrb.func_70527_a(i);
        i -= j;
        this.field_75229_a.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_75229_a.field_70170_p, this.field_75229_a.field_70165_t, this.field_75229_a.field_70163_u + 0.5D, this.field_75229_a.field_70161_v + 0.5D, j));
      } 
    } 
    if (this.last == null) {
      this.last = p_75208_1_.func_77946_l();
    } else {
      if (this.last.func_77973_b() == p_75208_1_.func_77973_b() && this.last.field_77994_a == p_75208_1_.field_77994_a) {
        this.last = null;
        return;
      } 
      this.last = null;
    } 
    ItemStack furnace = p_75208_1_.func_77946_l();
    furnace.field_77994_a = this.field_75228_b;
    JobsBridge.performSmeltObjective(this.field_75229_a, furnace);
    JobsBridge.performSmeltQuest(this.field_75229_a, furnace);
    Palapass.processFurnaceCraft(this.field_75229_a, furnace, (furnace.field_77994_a > 0) ? furnace.field_77994_a : 1);
    if (PFactions.instance != null && PFactions.instance.getImpl() != null)
      PFactions.instance.getImpl().onItemSmelt(this.field_75229_a, furnace); 
    FMLCommonHandler.instance().firePlayerSmeltedEvent(this.field_75229_a, furnace);
    this.field_75228_b = 0;
    if (p_75208_1_.func_77973_b() == Items.field_151042_j)
      this.field_75229_a.func_71064_a((StatBase)AchievementList.field_76016_k, 1); 
    if (p_75208_1_.func_77973_b() == Items.field_151101_aQ)
      this.field_75229_a.func_71064_a((StatBase)AchievementList.field_76026_p, 1); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\inventory\IMixinSlotFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */