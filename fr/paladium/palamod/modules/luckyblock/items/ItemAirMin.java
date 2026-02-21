package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAirMin extends ItemArmor implements ITooltipWiki {
  public ItemAirMin() {
    super(ItemArmor.ArmorMaterial.IRON, 0, 3);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("airMin_Boots");
    func_111206_d("palamod:airMin");
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/air_min_boots.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    return false;
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    super.onArmorTick(world, player, itemStack);
    if (player.field_71071_by.func_70440_f(0).func_77973_b() == ItemsRegister.AIRMIN_BOOTS && 
      !world.field_72995_K && !WorldGuardUtils.isItemEffectBlocked((Entity)player, itemStack))
      player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 60, 2)); 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemAirMin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */