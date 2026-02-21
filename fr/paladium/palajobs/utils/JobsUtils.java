package fr.paladium.palajobs.utils;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.network.packet.server.SCPacketNotification;
import fr.paladium.palajobs.core.network.packet.server.SCPacketQuest;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class JobsUtils {
  public static void damageCurrentItem(EntityPlayer player, ItemStack stack, int damage) {
    if (!stack.func_77973_b().func_77645_m())
      return; 
    stack.func_77964_b(stack.func_77960_j() + damage);
    if (stack.func_77960_j() == stack.func_77958_k()) {
      World world = player.field_70170_p;
      world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
      stack.field_77994_a--;
      if (stack.field_77994_a <= 0) {
        player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
        MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(player, stack));
      } 
    } 
  }
  
  public static void sendJobTitle(JobsPlayer data, EntityPlayer player, JobType type) {
    double experience = data.getExperience(type);
    int level = JobExpUtils.getLevel(experience);
    MinecraftTitle title = new MinecraftTitle(type.getPrefix() + TTT.format(player, type.getName(), new Object[0]), TTT.format(player, "message.jobs.levelup", new Object[] { Integer.valueOf(level) }), 1000L, TimeUnit.SECONDS.toMillis(3L), 1000L);
    ModuleTitle.getInstance().sendTitle(title, (EntityPlayerMP)player);
  }
  
  public static void sendQuestNotification(AbstractQuest quest, int advancement, EntityPlayer player) {
    if (player instanceof EntityPlayerMP)
      PalaJobs.proxy.getNetwork().sendTo((IMessage)new SCPacketQuest(quest.getId(), advancement), (EntityPlayerMP)player); 
  }
  
  public static void sendJobNotification(EntityPlayer player, JobType type, int level, double xp, double multiplier) {
    if (player instanceof EntityPlayerMP)
      PalaJobs.proxy.getNetwork().sendTo((IMessage)new SCPacketNotification(type.getName(), level, xp, multiplier), (EntityPlayerMP)player); 
  }
  
  public static boolean isItemEqual(ItemStack stack1, ItemStack stack2) {
    if (stack1 == null || stack2 == null || stack1.func_77973_b() == null || stack2.func_77973_b() == null)
      return false; 
    if (stack1.func_77973_b() != stack2.func_77973_b() || (!stack1.func_77984_f() && !stack2.func_77984_f() && stack1.func_77960_j() != stack2.func_77960_j()))
      return false; 
    return true;
  }
  
  public static boolean canPlace(EntityPlayer player, int x, int y, int z) {
    BlockSnapshot snapshot = new BlockSnapshot(player.field_70170_p, x, y, z, Blocks.field_150347_e, 0);
    if (player.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150357_h)
      return false; 
    return 
      !ForgeEventFactory.onPlayerBlockPlace(player, snapshot, ForgeDirection.getOrientation(0)).isCanceled();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\JobsUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */