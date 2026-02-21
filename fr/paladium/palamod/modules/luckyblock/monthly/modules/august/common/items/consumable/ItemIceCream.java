package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.IceCreamType;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemIceCream extends ItemFood {
  public static final String NAME = "ice_cream";
  
  public static final String FIRST_FLAVOR_NBT_FIELD = "firstFlavor";
  
  public static final String SECOND_FLAVOR_NBT_FIELD = "secondFlavor";
  
  private static final String NO_FLAVOR_MESSAGE = "&ccet glace ne contient aucun parfum !";
  
  public ItemIceCream(String name) {
    super(4, 1.2F, false);
    func_77655_b(name);
    func_111206_d("palamod:" + name);
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77848_i();
  }
  
  public ItemIceCream() {
    this("ice_cream");
  }
  
  public ItemStack func_77654_b(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return eat(itemStack, world, player); 
    NBTTagCompound compound = itemStack.func_77978_p();
    if (compound == null) {
      MonthlyUtils.sendMessage(player, new String[] { "&ccet glace ne contient aucun parfum !" });
      return eat(itemStack, world, player);
    } 
    List<PotionEffect> potionEffects = getPotionEffects(compound);
    if (potionEffects.isEmpty()) {
      MonthlyUtils.sendMessage(player, new String[] { "&ccet glace ne contient aucun parfum !" });
      return eat(itemStack, world, player);
    } 
    for (PotionEffect potionEffect : potionEffects)
      player.func_70690_d(potionEffect); 
    return eat(itemStack, world, player);
  }
  
  private ItemStack eat(ItemStack itemStack, World world, EntityPlayer player) {
    MonthlyUtils.decrementCurrentItem(player, itemStack);
    world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
    player.func_71024_bL().func_151686_a(this, itemStack);
    return itemStack;
  }
  
  private List<PotionEffect> getPotionEffects(NBTTagCompound compound) {
    List<PotionEffect> effects = new ArrayList<>();
    if (compound == null)
      return effects; 
    PotionEffect firstFlavor = getPotionEffect(compound, "firstFlavor");
    PotionEffect secondFlavor = getPotionEffect(compound, "secondFlavor");
    if (firstFlavor != null)
      effects.add(firstFlavor); 
    if (secondFlavor != null)
      effects.add(secondFlavor); 
    return effects;
  }
  
  private PotionEffect getPotionEffect(NBTTagCompound compound, String nbtName) {
    if (compound.func_74764_b(nbtName))
      return IceCreamType.getPotionEffect(compound.func_74779_i(nbtName)); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\consumable\ItemIceCream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */