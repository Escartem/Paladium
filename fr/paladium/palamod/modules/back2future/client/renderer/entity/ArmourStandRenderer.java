package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.client.model.ModelArmorStand;
import fr.paladium.palamod.modules.back2future.client.model.ModelArmorStandArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class ArmourStandRenderer extends RenderBiped {
  private static final ResourceLocation TEXTURE_ARMOUR_STAND = new ResourceLocation("textures/entity/armorstand/wood.png");
  
  public ArmourStandRenderer() {
    super((ModelBiped)new ModelArmorStand(), 0.0F);
    this.field_77071_a = (ModelBiped)this.field_77045_g;
    this.field_82423_g = (ModelBiped)new ModelArmorStandArmor(1.0F);
    this.field_82425_h = (ModelBiped)new ModelArmorStandArmor(0.5F);
  }
  
  protected void func_82421_b() {
    this.field_82423_g = (ModelBiped)new ModelArmorStandArmor(1.0F);
    this.field_82425_h = (ModelBiped)new ModelArmorStandArmor(0.5F);
  }
  
  protected void func_77043_a(EntityLivingBase entity, float x, float y, float z) {
    OpenGLHelper.rotate(180.0F - y, 0.0F, 1.0F, 0.0F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return TEXTURE_ARMOUR_STAND;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\ArmourStandRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */