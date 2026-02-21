package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWarStar extends ItemFood implements ITooltipInformations {
  private static final String NAME = "war_star";
  
  private static final String DESCRIPTION = "Quand elle est consommée par un joueur, il devient invincible pendant 5 secondes. Il inflige 1 cœur de dégât par seconde à chaque joueur présent dans un rayon de 2 cubes autour de lui";
  
  public ItemWarStar() {
    super(20, 20.0F, false);
    func_77655_b("war_star");
    func_111206_d("palamod:war_star");
    func_77637_a(PLuckyBlock.TAB);
    func_77848_i();
    this.field_77777_bU = 1;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Quand elle est consommée par un joueur, il devient invincible pendant 5 secondes. Il inflige 1 cœur de dégât par seconde à chaque joueur présent dans un rayon de 2 cubes autour de lui");
  }
  
  protected void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
    super.func_77849_c(stack, world, player);
    if (!player.field_70170_p.field_72995_K) {
      player.func_70690_d(new PotionEffect(PotionsRegister.WAR_STAR.getPotionId(), MonthlyUtils.translateSecondsToTicks(5), 0));
      MarchPlayer marchPlayer = MarchPlayer.get(player);
      if (marchPlayer != null) {
        marchPlayer.setWarStarTimestamp(System.currentTimeMillis() + 5000L);
        marchPlayer.sync();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemWarStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */