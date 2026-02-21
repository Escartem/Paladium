package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemPoulpoGun extends Item implements ITooltipWiki {
  public ItemPoulpoGun() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("poulpogun");
    func_111206_d("palamod:poulpogun");
    func_77625_d(1);
    func_77656_e(64);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      if (!EventUtils.canInteract(player, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v)) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cVous ne pouvez pas lancer de poulpe ici."));
        return item;
      } 
      final EntitySquid squid = new EntitySquid(world);
      final EntitySnowball snow = new EntitySnowball(world, (EntityLivingBase)player);
      item.func_77972_a(1, (EntityLivingBase)player);
      snow.func_82142_c(true);
      world.func_72838_d((Entity)snow);
      squid.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
      squid.func_70606_j(100.0F);
      squid.func_70107_b(snow.field_70165_t, snow.field_70163_u + 2.0D, snow.field_70161_v);
      world.func_72838_d((Entity)squid);
      final LuckyTask task = new LuckyTask();
      task
        
        .id = PaladiumScheduler.INSTANCE.runTaskTimer(new Runnable() {
            public void run() {
              if (snow.field_70128_L) {
                PaladiumScheduler.INSTANCE.cancelTask(task.id);
                return;
              } 
              squid.func_70107_b(snow.field_70165_t, snow.field_70163_u - 5.0D, snow.field_70161_v);
            }
          }1L, 1L).getTaskId();
    } 
    return item;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List list, boolean flag) {
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemPoulpoGun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */