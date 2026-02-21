package fr.paladium.palamod.modules.paladium.common.entities.ancient;

import fr.paladium.combattag.CombatTag;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;

class PlayerSelector implements IEntitySelector {
  private final boolean checkCombat;
  
  public PlayerSelector(boolean checkCombat) {
    this.checkCombat = checkCombat;
  }
  
  public boolean func_82704_a(Entity entity) {
    if (ForgeEnv.isIDE()) {
      if (entity instanceof EntityPlayerMP) {
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entity;
        return (!entityPlayerMP.field_71075_bZ.field_75098_d && !entityPlayerMP.func_70005_c_().equals(EntityAncientHammerEffect.access$100(EntityAncientHammerEffect.this)));
      } 
      return true;
    } 
    if (!(entity instanceof EntityPlayerMP))
      return false; 
    EntityPlayerMP playerMP = (EntityPlayerMP)entity;
    if (playerMP.field_71075_bZ.field_75098_d)
      return false; 
    if (this.checkCombat)
      try {
        if (!CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(playerMP.func_110124_au())))
          return false; 
      } catch (Exception exception) {} 
    return !playerMP.func_70005_c_().equals(EntityAncientHammerEffect.access$100(EntityAncientHammerEffect.this));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\ancient\EntityAncientHammerEffect$PlayerSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */