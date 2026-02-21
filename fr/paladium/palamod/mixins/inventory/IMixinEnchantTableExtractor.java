package fr.paladium.palamod.mixins.inventory;

import fr.paladium.palamod.modules.achievements.types.EnchantItemAchievement;
import fr.paladium.palamod.modules.apet.utils.PetBridge;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.palapass.Palapass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ContainerEnchantment.class})
public abstract class IMixinEnchantTableExtractor extends Container {
  @Shadow
  public IInventory field_75168_e;
  
  @Shadow
  public int[] field_75167_g;
  
  @Inject(method = {"enchantItem"}, at = {@At("HEAD")})
  public void enchantItem(EntityPlayer p_75140_1_, int p_75140_2_, CallbackInfoReturnable<Boolean> callback) {
    ItemStack is = this.field_75168_e.func_70301_a(0);
    if (p_75140_1_ instanceof net.minecraft.entity.player.EntityPlayerMP && is != null) {
      Palapass.processItemEnchant(p_75140_1_, is);
      EnchantItemAchievement.performCheck(p_75140_1_, is);
      if (PFactions.instance != null && PFactions.instance.getImpl() != null)
        PFactions.instance.getImpl().onItemEnchant(p_75140_1_, is); 
      int cost = this.field_75167_g[p_75140_2_];
      PetBridge.callEnchantItemEvent(p_75140_1_, is, cost);
    } 
  }
  
  @Overwrite
  public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(p_82846_2_);
    if (slot != null && slot.func_75216_d()) {
      if (slot.func_75211_c().func_77973_b() instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.itemblocks.ItemBlockExtractor)
        return null; 
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (p_82846_2_ == 0) {
        if (!func_75135_a(itemstack1, 1, 37, true))
          return null; 
      } else {
        if (((Slot)this.field_75151_b.get(0)).func_75216_d() || 
          !((Slot)this.field_75151_b.get(0)).func_75214_a(itemstack1))
          return null; 
        if (itemstack1.func_77942_o() && itemstack1.field_77994_a == 1) {
          ((Slot)this.field_75151_b.get(0)).func_75215_d(itemstack1.func_77946_l());
          itemstack1.field_77994_a = 0;
        } else if (itemstack1.field_77994_a >= 1) {
          ((Slot)this.field_75151_b.get(0))
            .func_75215_d(new ItemStack(itemstack1.func_77973_b(), 1, itemstack1.func_77960_j()));
          itemstack1.field_77994_a--;
        } 
      } 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75218_e();
      } 
      if (itemstack1.field_77994_a == itemstack.field_77994_a)
        return null; 
      slot.func_82870_a(p_82846_1_, itemstack1);
    } 
    return itemstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\inventory\IMixinEnchantTableExtractor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */