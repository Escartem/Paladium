package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemSwordPistol extends BaseItemSword implements ITooltipInformations {
  public static final String NAME = "sword_pistol";
  
  public static final String DESCRIPTION = "La combinaison ultime de tout ce que les pirates font de mieux !";
  
  private static final Random RANDOM = new Random();
  
  public ItemSwordPistol() {
    super(PToolMaterial.paladium, ItemsRegister.PALADIUM_INGOT);
    func_77655_b("sword_pistol");
    func_111206_d("palamod:sword_pistol");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public boolean func_77644_a(ItemStack stack, EntityLivingBase first, EntityLivingBase second) {
    int value = RANDOM.nextInt(100);
    if (value <= 10)
      MonthlyUtils.playSoundAround("sword_pistol", second.field_70170_p, (int)second.field_70165_t, (int)second.field_70163_u, (int)second.field_70161_v, 24); 
    stack.func_77972_a(1, second);
    return true;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("La combinaison ultime de tout ce que les pirates font de mieux !");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemSwordPistol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */