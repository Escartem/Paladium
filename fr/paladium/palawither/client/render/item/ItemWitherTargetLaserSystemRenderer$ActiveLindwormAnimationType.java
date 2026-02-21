package fr.paladium.palawither.client.render.item;

import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.item.target.ItemWitherTargetLaserSystem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

class ActiveLindwormAnimationType extends LindwormAnimationType {
  public ActiveLindwormAnimationType() {
    super("active", 1, true, entity -> {
          if (!(entity instanceof EntityPlayer))
            return false; 
          EntityPlayer player = (EntityPlayer)entity;
          ItemStack heldItem = player.func_70694_bm();
          return (heldItem == null || heldItem.func_77973_b() != ItemsRegister.WITHER_TARGET_LASER_SYSTEM) ? false : ItemWitherTargetLaserSystem.isActive(heldItem);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\item\ItemWitherTargetLaserSystemRenderer$ActiveLindwormAnimationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */