package fr.paladium.palamod.modules.ajobs.common.items;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.utils.MultiplierUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemJobBottle extends Item {
  public static final double MAX_XP = 1000.0D;
  
  private final JobType job;
  
  public ItemJobBottle(JobType job) {
    func_77655_b("job_bottle_" + job.toString().toLowerCase());
    func_111206_d("palamod:job_bottle/" + job.toString().toLowerCase());
    func_77625_d(64);
    this.job = job;
  }
  
  public static ItemJobBottle get(JobType job) {
    Item item = (job == JobType.MINER) ? ItemsRegister.JOB_BOTTLE_MINER : ((job == JobType.FARMER) ? ItemsRegister.JOB_BOTTLE_FARMER : ((job == JobType.HUNTER) ? ItemsRegister.JOB_BOTTLE_HUNTER : ItemsRegister.JOB_BOTTLE_ALCHEMIST));
    return (ItemJobBottle)item;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    double xp = getXp(item);
    if (world.field_72995_K)
      return item; 
    if (player.func_70093_af()) {
      xp *= item.field_77994_a;
      item.field_77994_a = 0;
    } else {
      item.field_77994_a--;
    } 
    if (MultiplierUtils.getDoubleXpMultiplier(player).getValue() > 0.0D)
      xp = (int)(xp / 2.0D); 
    if (player.func_82165_m(PotionsRegister.JOB_TEN_MULTIPLIER.getPotionId()))
      xp = (int)(xp / 10.0D); 
    JobsPlayer.get((Entity)player).addXp(this.job, ObjectiveType.XP_BOTTLER, xp, player);
    return item;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    list.add("");
    list.add("Clic droit pour gagner " + String.format("%.2f", new Object[] { Double.valueOf(getXp(item)) }) + " xp dans le métier " + this.job.getPrefix() + TTT.format(player, this.job.getName(), new Object[0]));
  }
  
  public ItemStack create(double xp) {
    ItemStack item = new ItemStack(this);
    setXp(item, xp);
    return item;
  }
  
  public double getXp(ItemStack item) {
    if (item == null || !item.func_77942_o() || !item.func_77978_p().func_74764_b("xp_bottler_xp"))
      return 0.0D; 
    return item.func_77978_p().func_74769_h("xp_bottler_xp");
  }
  
  public double setXp(ItemStack item, double xp) {
    if (item == null)
      return 0.0D; 
    if (!item.func_77942_o())
      item.func_77982_d(new NBTTagCompound()); 
    double newXp = Math.min(xp, 1000.0D);
    item.func_77978_p().func_74780_a("xp_bottler_xp", newXp);
    return newXp;
  }
  
  public double addXp(ItemStack item, double xp) {
    if (item == null)
      return xp; 
    double currentXp = getXp(item);
    double newXp = Math.min(currentXp + xp, 1000.0D);
    setXp(item, newXp);
    return newXp - currentXp;
  }
  
  public JobType getJob() {
    return this.job;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\items\ItemJobBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */