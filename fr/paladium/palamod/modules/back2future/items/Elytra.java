package fr.paladium.palamod.modules.back2future.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.client.model.ModelElytra;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.EnumHelper;

public class Elytra extends ItemArmor implements IConfigurable {
  @SideOnly(Side.CLIENT)
  private IIcon broken;
  
  public Elytra() {
    super(EnumHelper.addArmorMaterial("elytra", 27, new int[] { 0, 0, 0, 0 }, 0), 0, 1);
    func_77656_e(432);
    func_77625_d(1);
    func_111206_d("elytra");
    func_77655_b(Utils.getUnlocalisedName("elytra"));
    func_77637_a(Back2Future.enableElytra ? Back2Future.creativeTab : null);
  }
  
  public boolean func_82789_a(ItemStack stack, ItemStack material) {
    return (ItemArmor.ArmorMaterial.CLOTH.func_151685_b() == material.func_77973_b());
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "textures/entity/elytra.png";
  }
  
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
    return (ModelBiped)new ModelElytra();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int meta) {
    return (meta >= func_77612_l()) ? this.broken : super.func_77617_a(meta);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister reg) {
    super.func_94581_a(reg);
    this.broken = reg.func_94245_a("broken_elytra");
  }
  
  public boolean isEnabled() {
    return Back2Future.enableElytra;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\Elytra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */