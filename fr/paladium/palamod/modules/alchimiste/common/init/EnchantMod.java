package fr.paladium.palamod.modules.alchimiste.common.init;

import fr.paladium.palamod.modules.alchimiste.common.enchant.EnchantmentBase;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemFastsword;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantMod {
  public static Map<Enchantment, String> enchants = new HashMap<>();
  
  public static EnchantmentBase grassBreaker;
  
  public static EnchantmentBase guardianProtection;
  
  public static EnchantmentBase guardianBlocker;
  
  public static EnchantmentBase guardianSlayer;
  
  public static EnchantmentBase unclaimHelmet;
  
  public static EnchantmentBase fakeWaterProtection;
  
  public static EnchantmentBase noFall;
  
  public static void registerEnchantment(EnchantmentBase enchantment, String lore) {
    if (enchantment.farmerLevel > 0)
      lore = lore + "<br> <br>§cNiveau " + enchantment.farmerLevel + " farmer requis !"; 
    if (enchantment.hunterLevel > 0)
      lore = lore + "<br> <br>§cNiveau " + enchantment.hunterLevel + " hunter requis !"; 
    if (enchantment.mineurLevel > 0)
      lore = lore + "<br> <br>§cNiveau " + enchantment.mineurLevel + " mineur requis !"; 
    if (enchantment.alchemistLevel > 0)
      lore = lore + "<br> <br>§cNiveau " + enchantment.alchemistLevel + " alchimiste requis !"; 
    enchants.put(enchantment, lore);
  }
  
  public static void register() {
    grassBreaker = (new EnchantmentBase(125, 4, ItemFastsword.class::isInstance, "grassBreaker", 0, 4, 0, 0)).setMaxLevel(3);
    unclaimHelmet = new EnchantmentBase(130, 5, EnumEnchantmentType.armor_head, "unclaimHelmet", 0, 0, 0, 0);
    fakeWaterProtection = new EnchantmentBase(131, 0, item -> (EnumEnchantmentType.armor_head.func_77557_a(item) && item.func_77612_l() > 0), "fakeWaterProtection", 0, 0, 0, 0);
    noFall = new EnchantmentBase(132, 0, EnumEnchantmentType.armor_feet, "noFall", 0, 0, 0, 0);
    enchants.put(grassBreaker, "§6Permet de récupérer le verre<br>§6plus rapidement et donne plus<br>§6de graines sur les hautes herbes<br> <br>§cNiveau 15 farmer requis !<br>§c3 Niveaux maximum");
    enchants.put(unclaimHelmet, "§6Permet de combiner un<br>§6casque en paladium et<br>§6un unclaim finder dans<br>§6une enclume pour<br>§6avoir l'affichage<br> <br>§cNiveau 60 alchimiste requis !<br>§cUn seul niveau maximum");
    enchants.put(fakeWaterProtection, "§6Permet d'endommager l'armure<br>§6a la place du joueur.<br> <br>§cNiveau 10 hunter requis<br>§cUn seul niveau maximum");
    enchants.put(noFall, "§6Empêche le joueur de tomber d'un bloc comme si il était en sneak<br>§cUn seul niveau maximum");
  }
  
  public static Map<Enchantment, String> getEnchants() {
    return enchants;
  }
  
  public static Map<Enchantment, String> getEnchants(ItemStack item) {
    Map<Enchantment, String> ench = new HashMap<>();
    for (Enchantment enchantment : enchants.keySet()) {
      if (enchantment.func_92089_a(item))
        ench.put(enchantment, enchants.get(enchantment)); 
    } 
    return ench;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\EnchantMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */