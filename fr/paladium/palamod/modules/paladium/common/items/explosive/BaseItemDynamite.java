package fr.paladium.palamod.modules.paladium.common.items.explosive;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palajobs.core.quest.types.ActionQuest;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import fr.paladium.tutorial.common.DynamiteUseEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BaseItemDynamite extends BaseItem implements ITooltipWiki {
  public BaseItemDynamite(String unlocalizedName, String textures) {
    super(textures);
    func_77655_b(unlocalizedName);
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (CommonModule.getInstance().getConfig().getServerType() == ServerType.FARMLAND || (!world.field_72995_K && WorldGuardUtils.isItemEffectBlocked(world, entityplayer.field_70165_t, entityplayer.field_70163_u, entityplayer.field_70161_v, Item.func_150891_b(itemstack.func_77973_b()))))
      return itemstack; 
    if (entityplayer.field_71071_by.func_146026_a((Item)this)) {
      world.func_72956_a((Entity)entityplayer, "game.tnt.primed", 1.0F, 1.0F / (Item.field_77697_d.nextFloat() * 0.4F + 0.8F));
      if (!world.field_72995_K) {
        ActionQuest.performCheck(entityplayer, "DYNAMITE_USAGE", 1);
        UseItemAchievement.performCheck(entityplayer, new ItemStack((Item)ItemsRegister.DYNAMITE), "DYNAMITE_USAGE", 1);
        world.func_72838_d((Entity)new DynamiteEntity(world, (EntityLivingBase)entityplayer, 40 + Item.field_77697_d.nextInt(10), DynamiteEntity.DEFAULT));
        StatsEep statsEep = StatsEep.get((Entity)entityplayer);
        statsEep.increaseDynamiteUsed();
        if (!world.field_72995_K)
          MinecraftForge.EVENT_BUS.post((Event)new DynamiteUseEvent(entityplayer, itemstack)); 
      } 
    } 
    return itemstack;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77629_n_() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77662_d() {
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#3.-les-dynamites";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\explosive\BaseItemDynamite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */