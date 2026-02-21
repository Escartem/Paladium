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

public class ItemPetSnack extends ItemFood implements ITooltipInformations {
  private static final String NAME = "pet_snack";
  
  public ItemPetSnack() {
    super(20, 20.0F, false);
    func_77848_i();
    func_77655_b("pet_snack");
    func_111206_d("palamod:pet_snack");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    PetPlayer petPlayer = PetPlayer.get(player);
    if (petPlayer == null || !(player instanceof net.minecraft.entity.player.EntityPlayerMP))
      return item; 
    if (petPlayer.has()) {
      if (petPlayer.getHappiness() == petPlayer.getMaxHappiness()) {
        MonthlyUtils.sendMessage(player, new String[] { "§aVotre familier est déjà au maximum de bonheur possible." });
        return item;
      } 
      petPlayer.setHappiness(player, petPlayer.getMaxHappiness());
      item.field_77994_a--;
      player.func_71024_bL().func_151686_a(this, item);
      world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
      func_77849_c(item, world, player);
      MonthlyUtils.sendMessage(player, new String[] { "§aLe bohneur de votre familier est au maximum !" });
    } else {
      MonthlyUtils.sendMessage(player, new String[] { "§cVous n'avez pas de familier." });
    } 
    return item;
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Consomme ce biscuit pour mettre le bonheur de ton familier au maximum pendant les prochaines 48h00");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\item\ItemPetSnack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */