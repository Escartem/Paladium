package fr.paladium.palamod.modules.luckyblock.items.may;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.renders.models.may.ModelDarkKnightHelmet;
import fr.paladium.palamod.modules.paladium.common.items.armors.BaseItemArmorEffect;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemDarkKnightHelmet extends BaseItemArmorEffect {
  public ItemDarkKnightHelmet() {
    super(PArmorMaterial.paladium, 0, "dark_knight_helmet", "dark_knight_armor", null, new PotionEffect[] { new PotionEffect(Potion.field_76439_r.field_76415_H, 1200, 1, true) });
    func_77655_b("dark_knight_helmet");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
    return (ModelBiped)new ModelDarkKnightHelmet();
  }
  
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/dark_knight_helmet.png";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemDarkKnightHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */