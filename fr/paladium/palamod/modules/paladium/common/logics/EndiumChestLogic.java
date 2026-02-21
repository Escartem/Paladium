package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.utils.IRepairable;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EndiumChestLogic extends ModdedChestLogic {
  public EndiumChestLogic() {
    super(9, 4);
    this.colors = new Color[] { new Color(37, 85, 255), new Color(17, 0, 214) };
  }
  
  public void repairArmor(ItemStack item, ItemStack ring) {
    if (item != null && item.func_77973_b() instanceof IRepairable && item.func_77960_j() > 0 && (
      (IRepairable)item.func_77973_b()).isEndium())
      ((IRepairable)item.func_77973_b()).repair(item, ring); 
    if (item != null && item.func_77973_b() instanceof IRepairable && item.func_77960_j() > 0 && (
      (IRepairable)item.func_77973_b()).isEndium())
      ((IRepairable)item.func_77973_b()).repair(item, ring); 
  }
  
  public String func_145825_b() {
    return "endium chest";
  }
  
  @SideOnly(Side.CLIENT)
  public Color[] getFontColors() {
    return this.colors;
  }
  
  public boolean func_94041_b(int slot, ItemStack item) {
    return (slot < 108 || item.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.BaseItemRingEndium);
  }
  
  public Resource getSlotTexture() {
    return Resource.of(new ResourceLocation("palamod", "textures/gui/container/moddedchest/slotEndium.png"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\EndiumChestLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */