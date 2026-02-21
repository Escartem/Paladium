package fr.paladium.palamod.modules.pillage.common.blocks.effects;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.pillage.common.effects.EntityTNTEffectPrimed;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import net.minecraft.block.BlockTNT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TNTEffect extends BlockTNT implements ITooltipWiki {
  public ObjectEffect effect;
  
  public TNTEffect(ObjectEffect effect) {
    func_149663_c(effect.getName());
    func_149658_d("palamod:pillage/effects/" + effect.getName());
    func_149711_c(0.0F);
    func_149672_a(field_149779_h);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    this.effect = effect;
  }
  
  public void func_150114_a(World world, int x, int y, int z, int metadata, EntityLivingBase placer) {
    if (!world.field_72995_K && (
      metadata & 0x1) == 1) {
      EntityTNTEffectPrimed entitytntprimed = new EntityTNTEffectPrimed(world, x + 0.5D, y + 0.5D, z + 0.5D, placer, this);
      world.func_72838_d((Entity)entitytntprimed);
      world.func_72956_a((Entity)entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
    } 
  }
  
  public void func_149723_a(World world, int x, int y, int z, Explosion explosion) {
    if (!world.field_72995_K) {
      EntityTNTEffectPrimed entitytntprimed = new EntityTNTEffectPrimed(world, x + 0.5D, y + 0.5D, z + 0.5D, explosion.func_94613_c(), this);
      entitytntprimed
        .field_70516_a = world.field_73012_v.nextInt(entitytntprimed.field_70516_a / 4) + entitytntprimed.field_70516_a / 8;
      world.func_72838_d((Entity)entitytntprimed);
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#4.-les-tnt-et-eponges";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\effects\TNTEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */