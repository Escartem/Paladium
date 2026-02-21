package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.time.Duration;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemLaserGun extends Item implements ITooltipInformations {
  public static final String NAME = "laser_gun";
  
  private final String DESCRIPTION = "Peut être activé avec un clic droit. Les blocs touchés par le laser sont instantanément détruits. Peut être réparé en étant combiné avec une Pile galactique ou en endium.";
  
  public static final String TEXTURE = "palamod:laser_gun";
  
  private static final int RELOAD = 500;
  
  private static final int DURABILITY = 750;
  
  private static final long RELOADING_TIME = 1800000L;
  
  public ItemLaserGun() {
    func_77655_b("laser_gun");
    func_111206_d("palamod:laser_gun");
    func_77637_a(PLuckyBlock.TAB);
    func_77656_e(750);
    this.field_77777_bU = 1;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Peut être activé avec un clic droit. Les blocs touchés par le laser sont instantanément détruits. Peut être réparé en étant combiné avec une Pile galactique ou en endium.");
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    Vec3 look = player.func_70040_Z();
    Vec3 start = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
    Vec3 end = start.func_72441_c(look.field_72450_a * 33.0D, look.field_72448_b * 33.0D, look.field_72449_c * 33.0D);
    MovingObjectPosition mop = world.func_72933_a(start, end);
    if (mop != null)
      end = Vec3.func_72443_a(mop.field_72307_f.field_72450_a, mop.field_72307_f.field_72448_b - player.func_70047_e(), mop.field_72307_f.field_72449_c); 
    if (!world.field_72995_K && 
      mop != null) {
      int x = mop.field_72311_b;
      int y = mop.field_72312_c;
      int z = mop.field_72309_d;
      boolean spawnParticle = true;
      boolean endium = false;
      if (!world.func_147437_c(x, y, z) && 
        EventUtils.canInteract(player, x, y, z)) {
        if (itemStack.func_77942_o()) {
          NBTTagCompound nbt = itemStack.func_77978_p();
          if (!nbt.func_74764_b("durability"))
            nbt.func_74768_a("durability", 499); 
          int durability = nbt.func_74762_e("durability");
          if (itemStack.func_77960_j() + 1 == 750) {
            spawnParticle = false;
            MonthlyUtils.sendMessage(player, new String[] { "§eVotre pistolet est cassé, réparez le." });
          } else if (durability <= 0) {
            spawnParticle = false;
            durability = 0;
            long delay = nbt.func_74763_f("reloading") - System.currentTimeMillis();
            if (delay < 0L) {
              MonthlyUtils.sendMessage(player, new String[] { "§aRecharge effectuée." });
              durability = 500;
              nbt.func_74768_a("durability", durability);
              itemStack.func_77982_d(nbt);
            } else {
              Duration d = Duration.ofMillis(delay);
              MonthlyUtils.sendMessage(player, new String[] { "§eVotre pistolet laser est en charge." });
              MonthlyUtils.sendMessage(player, new String[] { "§eTemps restant : §6" + d.toMinutes() + "mn " + (d.getSeconds() % 60L) + "s" });
            } 
          } else {
            durability--;
            nbt.func_74768_a("durability", durability);
            if (durability <= 0)
              nbt.func_74772_a("reloading", System.currentTimeMillis() + 1800000L); 
            if (nbt.func_74764_b("endium"))
              endium = nbt.func_74767_n("endium"); 
            if (endium) {
              int startX = x - 1;
              int startY = y;
              int startZ = z - 1;
              for (int dx = 0; dx < 3; dx++) {
                for (int dy = 0; dy < 3; dy++) {
                  for (int dz = 0; dz < 3; dz++) {
                    int blockX = startX + dx;
                    int blockY = startY + dy;
                    int blockZ = startZ + dz;
                    if (!world.func_147437_c(blockX, blockY, blockZ) && 
                      EventUtils.canInteract(player, new DoubleLocation(blockX, blockY, blockZ)))
                      world.func_147468_f(blockX, blockY, blockZ); 
                  } 
                } 
              } 
            } else if (!world.func_147437_c(x, y, z)) {
              world.func_147468_f(x, y, z);
            } 
            MonthlyUtils.spawnParticle(world, "largeexplode", end.field_72450_a, end.field_72448_b, end.field_72449_c, 1, 5.0D);
            itemStack.func_77982_d(nbt);
            if (!endium)
              itemStack.func_77972_a(1, (EntityLivingBase)player); 
          } 
        } else {
          NBTTagCompound nbt = new NBTTagCompound();
          nbt.func_74768_a("durability", 499);
          itemStack.func_77982_d(nbt);
        } 
        if (spawnParticle && 
          !world.field_72995_K) {
          int steps = 100;
          for (int i = 0; i < steps; i++) {
            double progress = i / steps;
            double px = player.field_70165_t + (end.field_72450_a - player.field_70165_t) * progress;
            double py = player.field_70163_u + player.func_70047_e() + (end.field_72448_b - player.field_70163_u) * progress;
            double pz = player.field_70161_v + (end.field_72449_c - player.field_70161_v) * progress;
            MonthlyUtils.spawnParticle(world, "reddust", px, py, pz, 1, 5.0D);
          } 
        } 
      } 
    } 
    return itemStack;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    if (item.func_77942_o()) {
      NBTTagCompound tagCompound = item.func_77978_p();
      int durability = tagCompound.func_74762_e("durability");
      list.add("§aRecharge: " + durability + "/" + 'Ǵ');
      if (tagCompound.func_74764_b("endium"))
        list.add("§dAmélioration en Endium"); 
    } else {
      list.add("§aRecharge: 500/500");
    } 
    super.func_77624_a(item, player, list, flag);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemLaserGun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */