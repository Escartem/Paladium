package fr.paladium.palamod.modules.luckyblock.init;

import fr.paladium.palamod.modules.paladium.common.items.armors.BaseItemArmor;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

final class null extends BaseItemArmor {
  null(ItemArmor.ArmorMaterial material, int type, String texture, String model, Item item_repair) {
    super(material, type, texture, model, item_repair);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/" + this.model + "_1.png";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\init\Items$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */