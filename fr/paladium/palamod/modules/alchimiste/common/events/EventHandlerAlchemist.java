package fr.paladium.palamod.modules.alchimiste.common.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftObjective;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.alchimiste.common.potions.PotionLevitation;
import fr.paladium.palamod.modules.alchimiste.common.utils.WSEnchantUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.common.items.ItemUnclaimFinder;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.tutorial.common.event.CraftLightPotionEvent;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventHandlerAlchemist {
  @SubscribeEvent
  public void playerTick(TickEvent.PlayerTickEvent e) {
    if (!TickEvent.Phase.START.equals(e.phase) || e.player.field_70170_p.field_72995_K || System.currentTimeMillis() % 100L == 0L || !e.player.field_70170_p.func_72896_J())
      return; 
    if (e.player.field_71071_by.func_70440_f(3) == null || (e.player.field_71071_by.func_70440_f(3).func_77973_b() != Items.field_151028_Y && e.player.field_71071_by.func_70440_f(3).func_77973_b() != Items.field_151169_ag) || e.player.field_71071_by.func_70448_g() == null)
      return; 
    if (e.player.field_71071_by.func_70448_g().func_77973_b() != Items.field_151069_bo || !EventUtils.canInteract(e.player, e.player.field_70165_t, e.player.field_70163_u, e.player.field_70161_v))
      return; 
    int rdm = e.player.field_70170_p.field_73012_v.nextInt(800);
    if (rdm == 300) {
      int x = MathHelper.func_76128_c(e.player.field_70165_t);
      int y = MathHelper.func_76128_c(e.player.field_70163_u);
      int z = MathHelper.func_76128_c(e.player.field_70161_v);
      EntityLightningBolt light = new EntityLightningBolt(e.player.field_70170_p, x, (y + 20), z);
      e.player.field_70170_p.func_72838_d((Entity)light);
      if (!e.player.field_70170_p.field_72995_K && e.player.field_71071_by.func_70448_g() != null) {
        (e.player.field_71071_by.func_70448_g()).field_77994_a--;
        if ((e.player.field_71071_by.func_70448_g()).field_77994_a <= 0)
          e.player.func_70062_b(0, null); 
        e.player.field_71071_by.field_70459_e = true;
        ItemStack stack = new ItemStack((Item)ItemsRegister.LIGHTNING_POTION, 1);
        e.player.func_71019_a(stack, false);
        CraftObjective.performCheck(e.player, stack);
        MinecraftForge.EVENT_BUS.post((Event)new CraftLightPotionEvent(e.player));
      } 
      e.player.field_71071_by.func_70296_d();
    } 
  }
  
  @SubscribeEvent
  public void playerTickEvent(TickEvent.PlayerTickEvent e) {
    if (PotionLevitation.potionLevitation == null)
      return; 
    if (!e.player.func_70613_aW() && 
      e.player.func_70644_a((Potion)PotionLevitation.potionLevitation))
      e.player.field_70181_x = 0.5D; 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void FarmerDropHandler(BlockEvent.BreakEvent event) {
    if (!event.isCanceled() && event.getResult() != Event.Result.DENY && event.getPlayer() != null && event.world.func_147439_a(event.x, event.y, event.z) != null && event.world.func_147439_a(event.x, event.y, event.z).equals(Blocks.field_150329_H)) {
      int random1 = (int)(Math.random() * 10000.0D);
      int random2 = (int)(Math.random() * 10000.0D);
      int random3 = (int)(Math.random() * 10000.0D);
      int random4 = (int)(Math.random() * 10000.0D);
      int random5 = (int)(Math.random() * 10000.0D);
      if (random1 <= 2000 && 
        JobsBridge.canUseCraft(event.getPlayer(), new ItemStack((Block)BlocksRegister.CROPS_EGGPLANT), false)) {
        event.world.func_72838_d((Entity)new EntityItem(event.world, event.x, event.y, event.z, new ItemStack((Item)ItemsRegister.SEED_EGGPLANT, 1, 0)));
        UseItemAchievement.performCheck(event.getPlayer(), event.getPlayer().func_71045_bC(), "OBTAIN_SEEDS_EGGPLANT");
      } 
      if (random2 <= 2000 && 
        JobsBridge.canUseCraft(event.getPlayer(), new ItemStack((Block)BlocksRegister.CROPS_CHERVIL), false)) {
        event.world.func_72838_d((Entity)new EntityItem(event.world, event.x, event.y, event.z, new ItemStack((Item)ItemsRegister.SEED_CHERVIL, 1, 0)));
        UseItemAchievement.performCheck(event.getPlayer(), event.getPlayer().func_71045_bC(), "OBTAIN_SEEDS_CHERVIL");
      } 
      if (random3 <= 25 && 
        JobsBridge.canUseCraft(event.getPlayer(), new ItemStack((Block)BlocksRegister.CROPS_KIWANO), false)) {
        event.world.func_72838_d((Entity)new EntityItem(event.world, event.x, event.y, event.z, new ItemStack((Item)ItemsRegister.SEED_KIWANO, 1, 0)));
        UseItemAchievement.performCheck(event.getPlayer(), event.getPlayer().func_71045_bC(), "OBTAIN_SEEDS_KIWANO");
      } 
      if (random5 <= 1000)
        event.world.func_72838_d((Entity)new EntityItem(event.world, event.x, event.y, event.z, new ItemStack((Item)ItemsRegister.SEED_ICE, 3, 0))); 
      try {
        if (random4 <= (BukkitUtils.hasPermission(event.getPlayer().func_110124_au(), "palamod.boostseedorangeblue") ? 7 : 5) && 
          JobsBridge.canUseCraft(event.getPlayer(), new ItemStack((Block)BlocksRegister.CROPS_ORANGEBLUE), false)) {
          event.world.func_72838_d((Entity)new EntityItem(event.world, event.x, event.y, event.z, new ItemStack((Item)ItemsRegister.SEED_ORANGEBLUE, 1, 0)));
          UseItemAchievement.performCheck(event.getPlayer(), event.getPlayer().func_71045_bC(), "OBTAIN_SEEDS_ORANGEBLUE");
        } 
      } catch (Exception e) {
        if (random4 <= 5 && 
          JobsBridge.canUseCraft(event.getPlayer(), new ItemStack((Block)BlocksRegister.CROPS_ORANGEBLUE), false)) {
          event.world.func_72838_d((Entity)new EntityItem(event.world, event.x, event.y, event.z, new ItemStack((Item)ItemsRegister.SEED_ORANGEBLUE, 1, 0)));
          UseItemAchievement.performCheck(event.getPlayer(), event.getPlayer().func_71045_bC(), "OBTAIN_SEEDS_ORANGEBLUE");
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void blockBreakedEvent(BlockEvent.BreakEvent e) {
    if (e.getPlayer() == null || e.block != Blocks.field_150329_H || (e.getPlayer()).field_71071_by.func_70448_g() == null)
      return; 
    ItemStack stack = (e.getPlayer()).field_71071_by.func_70448_g();
    if ((stack.func_77973_b() != PSRegister_Items.FASTSWORD_AMETHYST && stack.func_77973_b() != PSRegister_Items.FASTSWORD_TITANE && stack.func_77973_b() != PSRegister_Items.FASTSWORD_PALADIUM) || !WSEnchantUtils.hasEnchantment(stack, EnchantMod.grassBreaker.field_77352_x))
      return; 
    int grassBreakerLevel = EnchantmentHelper.func_77506_a(EnchantMod.grassBreaker.field_77352_x, stack);
    grassBreakerLevel++;
    for (ItemStack drop : e.block.getDrops(e.world, e.x, e.y, e.z, e.blockMetadata, 1)) {
      drop.field_77994_a += (e.getPlayer()).field_70170_p.field_73012_v.nextInt(grassBreakerLevel);
      EntityItem entityitem = e.getPlayer().func_71019_a(drop, false);
      entityitem.field_145804_b = 0;
      entityitem.func_145797_a(e.getPlayer().func_70005_c_());
    } 
  }
  
  @SubscribeEvent
  public void respawn(PlayerEvent.PlayerRespawnEvent e) {
    if (e.player.func_110143_aJ() == Double.NaN)
      e.player.func_70606_j((e.player.func_110138_aP() != Float.NaN) ? e.player.func_110138_aP() : 20.0F); 
  }
  
  @SubscribeEvent
  public void anvilUpdateEvent(AnvilUpdateEvent e) {
    ItemStack left = e.left;
    ItemStack right = e.right;
    if (left != null && right != null && ((
      left.func_77973_b() instanceof ItemArmor && ((ItemArmor)left.func_77973_b()).field_77881_a == 0) || left.func_77973_b().equals(ItemsRegister.TRAVEL_SLIMYHELMET)) && 
      Arrays.<ItemUnclaimFinder>asList(new ItemUnclaimFinder[] { ItemsRegister.UNCLAIMFINDER, ItemsRegister.UNCLAIMFINDER_BLEU, ItemsRegister.UNCLAIMFINDER_ORANGE, ItemsRegister.UNCLAIMFINDER_ROUGE }).contains(right.func_77973_b()) && 
      WSEnchantUtils.hasEnchantment(left, EnchantMod.unclaimHelmet.field_77352_x)) {
      NBTTagCompound tag = left.func_77942_o() ? (NBTTagCompound)left.func_77978_p().func_74737_b() : new NBTTagCompound();
      if (tag.func_74764_b("alchemistUnclaimType"))
        return; 
      tag.func_74778_a("alchemistUnclaimType", right.func_77977_a().substring(5));
      e.output = left.func_77946_l();
      e.output.func_77982_d(tag);
      e.cost = 15;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\events\EventHandlerAlchemist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */