package fr.paladium.palamod.mixins.entity;

import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.paladium.utils.IEntity;
import fr.paladium.palamod.util.FastUUID;
import java.util.UUID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Entity.class})
public class IMixinEntity implements IEntity {
  @Shadow
  private UUID field_96093_i;
  
  private String strUUID;
  
  private String strUUIDWithoutDashes;
  
  @Redirect(method = {"moveEntity"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isSneaking()Z"))
  public boolean isSneaking(Entity entity, double motionX, double motionY, double motionZ) {
    if (!(entity instanceof EntityPlayer))
      return entity.func_70093_af(); 
    EntityPlayer player = (EntityPlayer)entity;
    ItemStack boots = player.field_71071_by.func_70440_f(0);
    if (boots != null && EnchantmentHelper.func_77506_a(EnchantMod.noFall.field_77352_x, boots) > 0)
      return true; 
    return entity.func_70093_af();
  }
  
  public String getStringUUID() {
    if (this.field_96093_i == null)
      return null; 
    if (this.strUUID == null)
      this.strUUID = FastUUID.toString(this.field_96093_i); 
    return this.strUUID;
  }
  
  public String getStringUUIDWithoutDashes() {
    if (this.field_96093_i == null)
      return null; 
    if (this.strUUIDWithoutDashes == null)
      this.strUUIDWithoutDashes = FastUUID.toString(this.field_96093_i).replace("-", ""); 
    return this.strUUIDWithoutDashes;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\entity\IMixinEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */