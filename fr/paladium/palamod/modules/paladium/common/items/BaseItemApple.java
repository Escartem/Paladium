package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BaseItemApple extends ItemFood implements ITooltipWiki {
  private final PotionEffect[] effects;
  
  public BaseItemApple(int healAmount, float saturationModifier, float absorptionAmount, PotionEffect[] effects, String texture) {
    super(healAmount, saturationModifier, false);
    this.effects = effects;
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77625_d(16);
    func_77848_i();
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77636_d(ItemStack p_77636_1_) {
    return true;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (WorldGuardUtils.isItemEffectBlocked((Entity)player, stack))
      return stack; 
    return super.func_77659_a(stack, world, player);
  }
  
  protected void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
    super.func_77849_c(stack, world, player);
    if (!world.field_72995_K)
      for (PotionEffect effect : this.effects) {
        PotionEffect effectR = new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c(), effect.func_82720_e());
        player.func_70690_d(effectR);
      }  
  }
  
  public String getUrl(ItemStack item) {
    if (item.func_77973_b() == ItemsRegister.SPEED_APPLE)
      return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium"; 
    if (item.func_77973_b() == ItemsRegister.PALADIUM_APPLE)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#4.-autre"; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\BaseItemApple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */