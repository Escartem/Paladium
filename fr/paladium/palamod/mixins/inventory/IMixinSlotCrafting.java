package fr.paladium.palamod.mixins.inventory;

import fr.paladium.palamod.modules.achievements.AchievementsManager;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.atutorial.TutorialBridge;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.palapass.Palapass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({SlotCrafting.class})
public abstract class IMixinSlotCrafting extends Slot {
  @Shadow
  private EntityPlayer field_75238_b;
  
  @Shadow
  private int field_75237_g;
  
  public IMixinSlotCrafting(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
    super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
  }
  
  @Overwrite
  protected void func_75208_c(ItemStack p_75208_1_) {
    ItemStack p_82870_2_ = p_75208_1_.func_77946_l();
    p_82870_2_.field_77994_a = this.field_75237_g;
    TutorialBridge.callCraftEvent(this.field_75238_b, p_82870_2_);
    JobsBridge.performItemCraftObjective(this.field_75238_b, p_82870_2_);
    JobsBridge.performItemCraftQuest(this.field_75238_b, p_82870_2_);
    JobsBridge.performItemCraftRequirement(this.field_75238_b, p_82870_2_);
    AchievementsManager.performCraftAchievementCheck(this.field_75238_b, p_82870_2_, (p_82870_2_.field_77994_a > 0) ? p_82870_2_.field_77994_a : 1);
    if (p_82870_2_.field_77994_a > 0)
      Palapass.processItemCraft(this.field_75238_b, p_82870_2_, p_82870_2_.field_77994_a); 
    if (PFactions.instance != null && PFactions.instance.getImpl() != null)
      PFactions.instance.getImpl().onItemCraft(this.field_75238_b, p_82870_2_); 
    p_75208_1_.func_77980_a(this.field_75238_b.field_70170_p, this.field_75238_b, this.field_75237_g);
    this.field_75237_g = 0;
    if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150462_ai))
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76017_h, 1); 
    if (p_75208_1_.func_77973_b() instanceof ItemPickaxe)
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76018_i, 1); 
    if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150460_al))
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76015_j, 1); 
    if (p_75208_1_.func_77973_b() instanceof net.minecraft.item.ItemHoe)
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76013_l, 1); 
    if (p_75208_1_.func_77973_b() == Items.field_151025_P)
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76014_m, 1); 
    if (p_75208_1_.func_77973_b() == Items.field_151105_aU)
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76011_n, 1); 
    if (p_75208_1_.func_77973_b() instanceof ItemPickaxe && ((ItemPickaxe)p_75208_1_
      .func_77973_b()).func_150913_i() != Item.ToolMaterial.WOOD)
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76012_o, 1); 
    if (p_75208_1_.func_77973_b() instanceof net.minecraft.item.ItemSword)
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76024_r, 1); 
    if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150381_bn))
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_75998_D, 1); 
    if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150342_X))
      this.field_75238_b.func_71064_a((StatBase)AchievementList.field_76000_F, 1); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\inventory\IMixinSlotCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */