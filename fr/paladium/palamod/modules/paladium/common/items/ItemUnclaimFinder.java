package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.achievements.types.UseUnclaimFinderAchievement;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUnclaimFinder extends BaseItem implements ITooltipWiki {
  protected String field_77774_bZ;
  
  private int typeId = 0;
  
  public int getTypeId() {
    return this.typeId;
  }
  
  public ItemUnclaimFinder(String unlocalizedName, int type) {
    super(unlocalizedName);
    this.field_77774_bZ = unlocalizedName;
    func_77655_b(unlocalizedName);
    this.typeId = type;
    func_77656_e(180000);
    func_77625_d(1);
  }
  
  public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
    super.func_77663_a(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    if (!p_77663_2_.field_72995_K && p_77663_5_ && p_77663_3_ instanceof EntityPlayer && 
      p_77663_2_.func_72820_D() % 20L == 0L) {
      p_77663_1_.func_77972_a(20, (EntityLivingBase)p_77663_3_);
      UseUnclaimFinderAchievement.performCheck((EntityPlayer)p_77663_3_);
      StatsEep statsEep = StatsEep.get(p_77663_3_);
      statsEep.increaseUnclaimFinderUse();
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#1.-les-unclaim-finders";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemUnclaimFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */