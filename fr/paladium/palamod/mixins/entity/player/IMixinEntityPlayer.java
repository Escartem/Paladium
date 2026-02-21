package fr.paladium.palamod.mixins.entity.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityPlayer.class})
public abstract class IMixinEntityPlayer extends EntityLivingBase {
  @Shadow
  public EntityFishHook field_71104_cf;
  
  public IMixinEntityPlayer(World p_i1594_1_) {
    super(p_i1594_1_);
  }
  
  @SideOnly(Side.CLIENT)
  @Inject(method = {"getItemIcon"}, at = {@At("HEAD")}, cancellable = true)
  public void getItemIcon(ItemStack p_70620_1_, int p_70620_2_, CallbackInfoReturnable<IIcon> ci) {
    if (p_70620_1_.func_77973_b() == JobsBridge.getPaladiumFishingRod() && this.field_71104_cf != null) {
      ci.setReturnValue(JobsBridge.getPaladiumFishingRodCastIcon());
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\entity\player\IMixinEntityPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */