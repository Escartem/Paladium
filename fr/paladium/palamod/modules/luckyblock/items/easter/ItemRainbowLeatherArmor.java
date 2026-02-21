package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemRepairableArmor;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemRainbowLeatherArmor extends ItemRepairableArmor {
  private static final List<String> TEXTURES = Arrays.asList(new String[] { "minecraft:textures/models/armor/leather_layer_", "minecraft:textures/models/armor/chainmail_layer_", "minecraft:textures/models/armor/iron_layer_", "minecraft:textures/models/armor/gold_layer_", "minecraft:textures/models/armor/diamond_layer_", "palamod:textures/models/amethyst_armor_", "palamod:textures/models/titane_armor_", "palamod:textures/models/paladium_armor_", "palamod:textures/models/paladium_green_armor_", "palamod:textures/models/endium_armor_" });
  
  public ItemRainbowLeatherArmor(int type, String texture, PotionEffect[] effects, int[] maxRepair) {
    super(PArmorMaterial.paladium, type, texture, "paladium_armor", ItemsRegister.RAINBOW_LEATHER, effects, maxRepair);
    func_77655_b(texture);
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    int textureIndex = entity.field_70173_aa / 10 % TEXTURES.size();
    String selectedTexture = TEXTURES.get(textureIndex);
    String layer = (slot == 2) ? "2" : "1";
    return selectedTexture + layer + ".png";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemRainbowLeatherArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */