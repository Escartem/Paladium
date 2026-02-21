package fr.paladium.palamod.mixins.item;

import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.palapass.Palapass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemFood.class})
public class IMixinItemFood extends Item {
  @Inject(method = {"onEaten"}, at = {@At("HEAD")})
  public void onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_, CallbackInfoReturnable<Boolean> callback) {
    if (p_77654_1_.func_77969_a(new ItemStack(Items.field_151106_aX))) {
      JobsBridge.performActionQuest(p_77654_3_, "EAT_COOKIE", 1);
    } else if (p_77654_1_.func_77969_a(new ItemStack(Items.field_151078_bh))) {
      JobsBridge.performActionQuest(p_77654_3_, "EAT_ROTTEN_FLESH", 1);
    } 
    if (p_77654_3_ instanceof net.minecraft.entity.player.EntityPlayerMP && p_77654_1_ != null) {
      Palapass.processItemUse(p_77654_3_, p_77654_1_, 1);
      if (PFactions.instance != null && PFactions.instance.getImpl() != null)
        PFactions.instance.getImpl().onItemUse(p_77654_3_, p_77654_1_, 1); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\item\IMixinItemFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */