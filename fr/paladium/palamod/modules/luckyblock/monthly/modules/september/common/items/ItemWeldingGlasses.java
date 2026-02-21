package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWeldingGlasses extends ItemArmor implements ITooltipInformations {
  public static final String NAME = "welding_glasses";
  
  public static final String DESCRIPTION = "Immunise aux dégâts du feu et de la lave. Permet de voir à travers la lave comme si elle n’existait pas";
  
  public ItemWeldingGlasses() {
    super(PArmorMaterial.titane, 0, 0);
    func_77655_b("welding_glasses");
    func_111206_d("palamod:welding_glasses");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    if (world.field_72995_K)
      return; 
    if (!MonthlyUtils.hasPotionEffect((EntityLivingBase)player, Potion.field_76426_n))
      player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 1, 0, false)); 
    if (player.func_70027_ad())
      player.func_70066_B(); 
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/welding_glasses.png";
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Immunise aux dégâts du feu et de la lave. Permet de voir à travers la lave comme si elle n’existait pas");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemWeldingGlasses.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */