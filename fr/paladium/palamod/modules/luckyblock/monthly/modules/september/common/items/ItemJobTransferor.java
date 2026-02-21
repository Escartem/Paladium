package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.JobTransferorHomeUI;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemJobTransferor extends Item implements ITooltipInformations {
  public static final String NAME = "job_transferor";
  
  public static final String DESCRIPTION = "Permet de transférer 10000 d’expérience d’un métier à un autre. Cooldown de 24h";
  
  public static final String[] CONDITIONS = new String[] { "&cLe transfert n'a pas été effectué car vous ne respectez pas les conditions suivantes :", "&7- &eAvoir au minimum 10001 expériences dans le métier importé", "&7- &eAvoir plus de 10000 expériences manquantes dans le métier exporté" };
  
  public static final int DURABILITY = 10;
  
  public static final long COOLDOWN = TimeUnit.HOURS.toMillis(24L);
  
  public static final int EXPERIENCE_AMOUNT = 10000;
  
  public static final String TRANSFER_SUCCESS = "&aLe transfert a été effectué avec succès !";
  
  public static final String COOLDOWN_MESSAGE = "&cVous devez attendre %s avant de pouvoir utiliser cet item !";
  
  public static final String NBT_COOLDOWN_FIELD = "cooldown";
  
  public ItemJobTransferor() {
    func_77655_b("job_transferor");
    func_111206_d("palamod:job_transferor");
    func_77637_a(PLuckyBlock.TAB);
    func_77656_e(10);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      openUI(); 
    return stack;
  }
  
  public void apply(EntityPlayerMP playerMP, ItemStack stack, JobType firstJob, JobType secondJob) {
    initNbt(stack);
    NBTTagCompound compound = stack.func_77978_p();
    long now = System.currentTimeMillis();
    if (isOnCooldown(stack, compound, (EntityPlayer)playerMP))
      return; 
    if (!transfer(playerMP, firstJob, secondJob)) {
      MonthlyUtils.sendMessage((EntityPlayer)playerMP, CONDITIONS);
      return;
    } 
    MonthlyUtils.sendMessage((EntityPlayer)playerMP, new String[] { "&aLe transfert a été effectué avec succès !" });
    compound.func_74772_a("cooldown", now + COOLDOWN);
    MonthlyUtils.damageCurrentItem((EntityPlayer)playerMP, stack, 1);
  }
  
  public boolean transfer(EntityPlayerMP player, JobType firstJob, JobType secondJob) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null || firstJob == null || secondJob == null || firstJob == secondJob)
      return false; 
    JobRegistry registry = JobRegistry.getInstance();
    AbstractJob first = getByType(registry, firstJob);
    AbstractJob second = getByType(registry, secondJob);
    if (first == null || second == null)
      return false; 
    JobAdvancement firstAdvancement = new JobAdvancement(data, first);
    JobAdvancement secondAdvancement = new JobAdvancement(data, second);
    double afterValue = firstAdvancement.getExperience() - 10000.0D;
    if (afterValue <= 0.0D)
      return false; 
    afterValue = secondAdvancement.getExperience() + 10000.0D;
    if (afterValue >= secondAdvancement.getNextLevelExperience())
      return false; 
    data.setExperience(first.getType(), data.getExperience(first.getType()) - 10000.0D);
    data.setExperience(second.getType(), data.getExperience(second.getType()) + 10000.0D);
    return true;
  }
  
  public AbstractJob getByType(JobRegistry registry, JobType type) {
    if (type == null)
      return null; 
    for (AbstractJob job : registry.getJobs()) {
      if (job.getType() == type)
        return job; 
    } 
    return null;
  }
  
  public void initNbt(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null) {
      compound = new NBTTagCompound();
      stack.func_77982_d(compound);
    } 
    if (!compound.func_74764_b("cooldown"))
      compound.func_74772_a("cooldown", 0L); 
  }
  
  private boolean isOnCooldown(ItemStack stack, NBTTagCompound compound, EntityPlayer player) {
    if (!compound.func_74764_b("cooldown"))
      return true; 
    long cooldown = compound.func_74763_f("cooldown");
    long now = System.currentTimeMillis();
    if (cooldown > now) {
      MonthlyUtils.sendMessage(player, new String[] { String.format("&cVous devez attendre %s avant de pouvoir utiliser cet item !", new Object[] { DurationConverter.fromMillisToString(cooldown - now) }) });
      return true;
    } 
    return false;
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void openUI() {
    MonthlyUtils.openUI((GuiScreen)new JobTransferorHomeUI());
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Permet de transférer 10000 d’expérience d’un métier à un autre. Cooldown de 24h");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemJobTransferor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */