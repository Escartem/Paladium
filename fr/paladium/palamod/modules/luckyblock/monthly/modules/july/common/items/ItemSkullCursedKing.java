package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemSkullCursedKing extends ItemKingSkull implements ITooltipInformations {
  public static final String NAME = "skull_cursed_king";
  
  public static final String DESCRIPTION = "Creuse une zone de 4x4 autour de lui. Consomme 32 poudres d'os par utilisation. Fonctionne aussi sur la bedrock";
  
  public static int REQUIRED_POWDER_AMOUNT = 32;
  
  public static int DURABILITY = 5;
  
  public ItemSkullCursedKing() {
    super(DURABILITY, REQUIRED_POWDER_AMOUNT, "skull_cursed_king", "Creuse une zone de 4x4 autour de lui. Consomme 32 poudres d'os par utilisation. Fonctionne aussi sur la bedrock");
  }
  
  protected Cuboid getCuboid(EntityPlayer player) {
    return new Cuboid(new Location(player.field_70170_p, player.field_70165_t - 1.0D, player.field_70163_u - 1.0D, player.field_70161_v - 1.0D), new Location(player.field_70170_p, player.field_70165_t + 2.0D, player.field_70163_u + 2.0D, player.field_70161_v + 2.0D));
  }
  
  protected boolean canUseHere(Cuboid cuboid, EntityPlayer player) {
    if (LuckyBlockUtils.isInCombat(player))
      return false; 
    for (Location location : cuboid) {
      if (location.getY() < 1.0D)
        return false; 
      if (!EventUtils.canInteractUnsafe(player, location))
        return false; 
    } 
    return true;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Creuse une zone de 4x4 autour de lui. Consomme 32 poudres d'os par utilisation. Fonctionne aussi sur la bedrock");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemSkullCursedKing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */