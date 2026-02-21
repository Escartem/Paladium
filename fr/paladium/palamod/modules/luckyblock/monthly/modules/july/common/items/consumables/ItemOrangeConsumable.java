package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.consumables;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.ScorbutEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrangeConsumable extends ItemFood implements ITooltipInformations {
  public static final String DESCRIPTION = " Riche en vitamines. Permet de soigner le scorbut";
  
  public static final String NAME = "orange_consumable";
  
  private static final String HEAL_MESSAGE = "&aVous avez été soigné du &dscorbut&a.";
  
  public ItemOrangeConsumable() {
    super(4, 1.2F, false);
    func_77655_b("orange_consumable");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:orange_consumable");
    func_77625_d(64);
    func_77848_i();
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return item; 
    if (ScorbutEvent.INSTANCE.heal(player))
      MonthlyUtils.sendMessage(player, new String[] { "&aVous avez été soigné du &dscorbut&a." }); 
    item.func_77972_a(1, (EntityLivingBase)player);
    world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
    player.func_71024_bL().func_151686_a(this, item);
    return item;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription(" Riche en vitamines. Permet de soigner le scorbut");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\consumables\ItemOrangeConsumable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */