package fr.paladium.palamod.modules.paladium.common.items.eventcommu;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.client.model.HalloweenHatModel;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class HalloweenHat extends ItemArmor {
  @SideOnly(Side.CLIENT)
  public HalloweenHatModel model;
  
  public HalloweenHat() {
    super(PArmorMaterial.paladium, 0, 0);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("halloweenhat");
    func_111206_d("palamod:halloweenhat");
    func_77625_d(1);
    func_77656_e(256);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/halloween_hat.png";
  }
  
  @SideOnly(Side.CLIENT)
  public void initModel() {
    if (this.model == null)
      this.model = new HalloweenHatModel(); 
  }
  
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
    initModel();
    return (ModelBiped)new HalloweenHatModel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\eventcommu\HalloweenHat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */