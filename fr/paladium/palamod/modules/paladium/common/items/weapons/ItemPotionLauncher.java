package fr.paladium.palamod.modules.paladium.common.items.weapons;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palajobs.core.quest.types.ActionQuest;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntityPotionGun;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntitySplashPotion;
import fr.paladium.palapass.common.quest.misc.ItemUseQuest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPotionLauncher extends Item implements ITooltipWiki {
  public static List<Integer> idBanned = new ArrayList<>();
  
  public ItemPotionLauncher() {
    func_77625_d(1);
    func_77655_b("potion_launcher");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_111206_d("palamod:weapons/potion_launcher");
    func_77656_e(256);
    idBanned = IntStream.of(PConfigs.potionBanned).boxed().collect((Collector)Collectors.toList());
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (WorldGuardUtils.isItemEffectBlocked((Entity)player, stack))
      return stack; 
    if (checkForPotion(player.field_71071_by) != -1) {
      int potionSlot = checkForPotion(player.field_71071_by);
      ItemStack potionStack = player.field_71071_by.func_70301_a(potionSlot);
      if (potionStack != null && (potionStack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.potion.ItemSplashPotion || ItemPotion.func_77831_g(potionStack.func_77960_j()))) {
        world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (Item.field_77697_d.nextFloat() * 0.4F + 0.8F));
        potionStack.field_77994_a--;
        if (!world.field_72995_K && player instanceof net.minecraft.entity.player.EntityPlayerMP) {
          ItemUseQuest.trigger(player, stack, 1);
          if (PFactions.instance != null && PFactions.instance.getImpl() != null)
            PFactions.instance.getImpl().onItemUse(player, stack, 1); 
        } 
        ActionQuest.performCheck(player, "POTION_LAUNCHER", 1);
        if (!world.field_72995_K)
          if (potionStack.func_77973_b() instanceof ItemPotion) {
            world.func_72838_d((Entity)new EntityPotionGun(world, (EntityLivingBase)player, potionStack));
          } else {
            world.func_72838_d((Entity)new EntitySplashPotion(world, player, potionStack.func_77960_j(), true));
          }  
        if (potionStack.field_77994_a <= 0)
          potionStack = null; 
        player.field_71071_by.func_70299_a(potionSlot, potionStack);
      } 
    } 
    return stack;
  }
  
  public boolean func_77662_d() {
    return true;
  }
  
  private int checkForPotion(InventoryPlayer inventory) {
    int i = 0;
    for (i = 0; i < inventory.func_70302_i_(); ) {
      if (inventory.func_70301_a(i) == null || (!(inventory.func_70301_a(i).func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.potion.ItemSplashPotion) && (!(inventory.func_70301_a(i).func_77973_b() instanceof ItemPotion) || !ItemPotion.func_77831_g(inventory.func_70301_a(i).func_77960_j()))) || (
        inventory.func_70301_a(i).func_77973_b() instanceof ItemPotion && ((ItemPotion)inventory.func_70301_a(i).func_77973_b()).func_77832_l(inventory.func_70301_a(i)) != null && ((ItemPotion)inventory.func_70301_a(i).func_77973_b()).func_77832_l(inventory.func_70301_a(i)).stream().anyMatch(effect -> idBanned.contains(Integer.valueOf(((PotionEffect)effect).func_76456_a()))))) {
        i++;
        continue;
      } 
      return i;
    } 
    return -1;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#2.-les-potions";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\weapons\ItemPotionLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */