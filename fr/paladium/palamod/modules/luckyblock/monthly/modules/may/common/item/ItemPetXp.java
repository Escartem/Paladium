package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.item;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPetXp extends ItemFood implements ITooltipInformations {
  public static final String SMALL_NAME = "pet_xp_small";
  
  public static final String MEDIUM_NAME = "pet_xp_medium";
  
  public static final String BIG_NAME = "pet_xp_big";
  
  public static final int SMALL_XP_AMOUNT = 2500;
  
  public static final int MEDIUM_XP_AMOUNT = 5000;
  
  public static final int BIG_XP_AMOUNT = 10000;
  
  private final int xpAmount;
  
  public ItemPetXp(String name, int xpAmount) {
    super(20, 20.0F, false);
    func_77848_i();
    func_77655_b(name);
    func_111206_d("palamod:" + name);
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    this.xpAmount = xpAmount;
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    PetPlayer petPlayer = PetPlayer.get(player);
    if (petPlayer == null || !(player instanceof net.minecraft.entity.player.EntityPlayerMP))
      return item; 
    if (petPlayer.has()) {
      if (petPlayer.getLevel() == 100) {
        MonthlyUtils.sendMessage(player, new String[] { "§aVotre familier est déjà au niveau maximum." });
        return item;
      } 
      petPlayer.addExperience(player, this.xpAmount);
      item.field_77994_a--;
      player.func_71024_bL().func_151686_a(this, item);
      world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
      func_77849_c(item, world, player);
      MonthlyUtils.sendMessage(player, new String[] { "§aVotre familier vient d'obtenir " + this.xpAmount + " xp !" });
    } else {
      MonthlyUtils.sendMessage(player, new String[] { "§cVous n'avez pas de familier." });
    } 
    return item;
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Consomme cette potion pour donner " + this.xpAmount + " xp à votre familier.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\item\ItemPetXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */