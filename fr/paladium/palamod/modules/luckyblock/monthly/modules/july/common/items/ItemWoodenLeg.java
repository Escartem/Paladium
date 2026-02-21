package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items.ModelWoodenLeg;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemWoodenLeg extends ItemArmor {
  public static final String NAME = "wooden_leg";
  
  public ItemWoodenLeg() {
    super(PArmorMaterial.titane, 0, 2);
    func_77655_b("wooden_leg");
    func_111206_d("palamod:wooden_leg");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
    return (ModelBiped)new ModelWoodenLeg();
  }
  
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/items/wooden_leg.png";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemWoodenLeg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */