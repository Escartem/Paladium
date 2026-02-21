package fr.paladium.palamod.modules.paladium.common.events;

import com.google.common.util.concurrent.AtomicDouble;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.ServerType;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.chronos.server.runnables.PlannedStatued;
import fr.paladium.combattag.CombatTag;
import fr.paladium.common.CommonModule;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.achievements.PalaJobsMinerOreAchievement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.quest.types.ActionQuest;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.events.AbstractCommandEvent;
import fr.paladium.palamod.modules.achievements.types.VisitServerAchievement;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.miner.item.ItemMoulaStone;
import fr.paladium.palamod.modules.paladium.common.items.ItemHangGlider;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage;
import fr.paladium.palamod.modules.paladium.network.SCPacketSyncEvents;
import fr.paladium.palamod.util.DurationConverter;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palapass.common.quest.misc.BlockBreakQuest;
import fr.paladium.palaspawner.common.event.FakeSpawnerEntityKillEvent;
import fr.paladium.tutorial.common.event.DollarStoneDepositEvent;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.lwjgl.opengl.GL11;

public class PlayerHandler {
  private static PlayerHandler instance;
  
  public static PlayerHandler getInstance() {
    return instance;
  }
  
  private final ConcurrentHashMap<UUID, Double> pendingDollarStoneMoney = new ConcurrentHashMap<>();
  
  private final ConcurrentHashMap<UUID, Integer> pendingHangGliderUses = new ConcurrentHashMap<>();
  
  public ConcurrentHashMap<UUID, Integer> getPendingHangGliderUses() {
    return this.pendingHangGliderUses;
  }
  
  public PlayerHandler() {
    instance = this;
  }
  
  @SubscribeEvent
  public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
    EntityPlayer player = event.player;
    UUID playerId = player.func_110124_au();
    this.pendingDollarStoneMoney.remove(playerId);
    this.pendingHangGliderUses.remove(playerId);
  }
  
  @SubscribeEvent
  public void onFall(LivingFallEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    ItemStack item = player.func_70694_bm();
    if (item == null || !(item.func_77973_b() instanceof ItemHangGlider))
      return; 
    long now = System.currentTimeMillis();
    boolean recentlyUsed = (ItemHangGlider.getLastUse(item) + 1000L > now);
    if (!ItemHangGlider.isActive(item) && !recentlyUsed)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onFall(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayer) || !event.source.equals(DamageSource.field_76379_h))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    ItemStack item = player.func_70694_bm();
    if (item == null || !(item.func_77973_b() instanceof ItemHangGlider))
      return; 
    long now = System.currentTimeMillis();
    boolean recentlyUsed = (ItemHangGlider.getLastUse(item) + 1000L > now);
    if (!ItemHangGlider.isActive(item) && !recentlyUsed)
      return; 
    event.setCanceled(true);
    event.ammount = 0.0F;
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onBlockBreak(BlockEvent.BreakEvent event) {
    if (Constants.MOD_ENV == Constants.Environment.DEV)
      return; 
    EntityPlayer player = event.getPlayer();
    if ((player.func_130014_f_()).field_72995_K || event.isCanceled() || event.getResult() == Event.Result.DENY)
      return; 
    if (EnchantmentHelper.func_77502_d((EntityLivingBase)player) || event.block.equals(Blocks.field_150348_b) || event.block.equals(Blocks.field_150346_d) || event.block.equals(Blocks.field_150351_n) || event.block.equals(Blocks.field_150349_c))
      return; 
    World world = event.world;
    Block block = event.block;
    if (block != null) {
      if (PlacedBlockData.isPlaced(world, event.x, event.y, event.z))
        return; 
      if (event.block instanceof net.minecraft.block.BlockOre || event.block instanceof fr.paladium.palamod.modules.world.blocks.BlockOre)
        PalaJobsMinerOreAchievement.performCheck(player, 1); 
      BlockBreakQuest.trigger(player, new ItemStack(event.block, 1, event.blockMetadata), 1);
    } 
  }
  
  @SubscribeEvent
  public void playerTickEvent(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.START && !event.player.field_70170_p.field_72995_K) {
      final EntityPlayer player = event.player;
      if (player.field_71075_bZ.field_75098_d)
        return; 
      ItemStack itemInLastSlot = event.player.field_71071_by.field_70462_a[8];
      if (itemInLastSlot == null || (!(itemInLastSlot.func_77973_b() instanceof ItemVoidStoneMinage) && !(itemInLastSlot.func_77973_b() instanceof ItemMoulaStone)))
        return; 
      AtomicDouble moulaStoneMoney = new AtomicDouble();
      for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
        ItemStack itemstack = player.field_71071_by.field_70462_a[i];
        if (itemstack != null)
          if (itemInLastSlot.func_77973_b() instanceof ItemVoidStoneMinage) {
            if (JobsBridge.canUseCraft(player, new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE), false)) {
              ItemVoidStoneMinage voidstone_minage = (ItemVoidStoneMinage)itemInLastSlot.func_77973_b();
              voidstone_minage.check(event, itemInLastSlot, itemstack, i, player);
            } 
          } else if (itemInLastSlot.func_77973_b() instanceof ItemMoulaStone && 
            JobsBridge.canUseCraft(player, new ItemStack(ItemsRegister.MOULA_STONE), false)) {
            ItemMoulaStone moula_stone = (ItemMoulaStone)itemInLastSlot.func_77973_b();
            moulaStoneMoney.addAndGet(moula_stone.check(event, itemInLastSlot, itemstack, player));
          }  
      } 
      double moulaStoneEffectiveMoney = moulaStoneMoney.get();
      if (this.pendingDollarStoneMoney.containsKey(player.func_110124_au())) {
        double newAmount = ((Double)this.pendingDollarStoneMoney.get(player.func_110124_au())).doubleValue() + moulaStoneEffectiveMoney;
        this.pendingDollarStoneMoney.put(player.func_110124_au(), Double.valueOf(newAmount));
        if (((Double)this.pendingDollarStoneMoney.get(player.func_110124_au())).doubleValue() >= 100.0D) {
          final int winningAmount = ((Double)this.pendingDollarStoneMoney.get(player.func_110124_au())).intValue();
          CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), winningAmount, "DOLLAR_STONE", new CresusCallback<CresusResponse>() {
                public void onSuccess(CresusResponse arg0) {
                  player.func_146105_b((IChatComponent)new ChatComponentText("§aVous venez de récupérer " + winningAmount + "$ avec votre DollarStone."));
                  FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
                          DollarStoneDepositEvent event = new DollarStoneDepositEvent(player, arg0);
                          MinecraftForge.EVENT_BUS.post((Event)event);
                        } });
                }
                
                public void onFail(CresusResponse arg0, Throwable arg1) {
                  player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Dollar Stone§8] §6Impossible de récupérer §e" + winningAmount + "$"));
                }
              });
          this.pendingDollarStoneMoney.put(player.func_110124_au(), Double.valueOf(0.0D));
        } 
      } else {
        this.pendingDollarStoneMoney.put(player.func_110124_au(), Double.valueOf(moulaStoneEffectiveMoney));
      } 
    } 
  }
  
  @SubscribeEvent
  public void onBonemealEvent(BonemealEvent e) {
    if (!e.entity.field_70170_p.field_72995_K && e.block instanceof IGrowable) {
      IGrowable growable = (IGrowable)e.block;
      if (e.entity != null && e.entityPlayer != null && e.entityPlayer.func_70694_bm() != null && growable.func_149851_a(e.world, e.x, e.y, e.z, e.world.field_72995_K))
        ActionQuest.performCheck(e.entityPlayer, "USE_BONEMEAL", 1); 
    } 
  }
  
  @SubscribeEvent
  public void onMilkFillEvent(EntityInteractEvent e) {
    if (!e.entity.field_70170_p.field_72995_K && 
      e.entityPlayer.func_70694_bm() != null && 
      e.entityPlayer.func_70694_bm().func_77973_b() == Items.field_151133_ar && e.target instanceof net.minecraft.entity.passive.EntityCow)
      ActionQuest.performCheck(e.entityPlayer, "MILK_FILL", 1); 
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent e) {
    if (!e.entity.field_70170_p.field_72995_K && 
      e.entity instanceof EntityArrow) {
      EntityArrow arrow = (EntityArrow)e.entity;
      if (arrow != null && arrow.field_70250_c instanceof EntityPlayer) {
        EntityPlayer p = (EntityPlayer)arrow.field_70250_c;
        ActionQuest.performCheck(p, "ARROW_SHOT", 1);
      } 
    } 
  }
  
  @SubscribeEvent
  public void useHoe(UseHoeEvent event) {
    Block block = event.world.func_147439_a(event.x, event.y, event.z);
    if (block == BlocksRegister.connectedDirt || block == Blocks.field_150346_d || block == Blocks.field_150349_c || block == Blocks.field_150458_ak) {
      if (block == Blocks.field_150349_c || block == Blocks.field_150346_d)
        ActionQuest.performCheck(event.entityPlayer, "USE_HOE", 1); 
      if (event.current.func_77973_b().equals(Items.field_151017_I) || event.current
        .func_77973_b().equals(Items.field_151019_K) || event.current
        .func_77973_b().equals(Items.field_151013_M) || event.current
        .func_77973_b().equals(Items.field_151018_J) || event.current
        .func_77973_b().equals(Items.field_151012_L))
        return; 
      event.setResult(Event.Result.ALLOW);
      event.world.func_147449_b(event.x, event.y, event.z, (Block)BlocksRegister.connectedDirtTilled);
      event.world.func_72908_a((event.x + 0.5F), (event.y + 0.5F), (event.z + 0.5F), BlocksRegister.connectedDirtTilled.field_149762_H
          .func_150498_e(), (BlocksRegister.connectedDirtTilled.field_149762_H
          .func_150497_c() + 1.0F) / 2.0F, BlocksRegister.connectedDirtTilled.field_149762_H
          .func_150494_d() * 0.8F);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void render(RenderWorldLastEvent event) {
    if ((Minecraft.func_71410_x()).field_71439_g.field_70145_X) {
      GL11.glDisable(3553);
      GL11.glEnable(32826);
      GL11.glDisable(2896);
      GL11.glLineWidth(5.0F);
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3553);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onLogin(FMLNetworkEvent.ClientConnectedToServerEvent e) {
    String filePath = "optionsof.txt";
    try {
      File options = new File("optionsof.txt");
      if (!options.exists())
        return; 
      Scanner sc = new Scanner(options);
      StringBuffer buffer = new StringBuffer();
      while (sc.hasNextLine())
        buffer.append(sc.nextLine() + System.lineSeparator()); 
      String fileContents = buffer.toString();
      sc.close();
      fileContents = fileContents.replace("ofChunkLoading:1", "ofChunkLoading:0");
      fileContents = fileContents.replace("ofChunkLoading:2", "ofChunkLoading:0");
      fileContents = fileContents.replace("ofChunkLoading:3", "ofChunkLoading:0");
      fileContents = fileContents.replace("ofShowFps:true", "ofShowFps:false");
      FileWriter writer = new FileWriter("optionsof.txt");
      writer.append(fileContents);
      writer.flush();
      writer.close();
      System.out.println("Patching Optifine");
    } catch (Exception e1) {
      System.out.println("Unable to load optionsof.txt");
      e1.printStackTrace();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post e) {
    if (e.gui instanceof net.minecraft.client.gui.GuiVideoSettings) {
      for (Object button : e.buttonList) {
        GuiButton btn = (GuiButton)button;
        if (btn.field_146127_k == 101)
          btn.field_146124_l = false; 
      } 
    } else if ("GuiOtherSettingsOF".equals(e.gui.getClass().getSimpleName())) {
      for (Object button : e.buttonList) {
        GuiButton btn = (GuiButton)button;
        if (btn.field_146127_k == 62)
          btn.field_146124_l = false; 
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onDrop(ItemTossEvent e) {
    if (WorldGuardUtils.getStates(e.entityItem.field_70170_p, e.entityItem.field_70165_t, e.entityItem.field_70163_u, e.entityItem.field_70161_v, DefaultFlag.BLOCKED_CMDS) != null)
      for (Object f : WorldGuardUtils.getStates(e.entityItem.field_70170_p, e.entityItem.field_70165_t, e.entityItem.field_70163_u, e.entityItem.field_70161_v, DefaultFlag.BLOCKED_CMDS)) {
        if ("/drop-secure".equalsIgnoreCase(f.toString())) {
          EntityItem item = e.entityItem;
          ItemStack stack = item.func_92059_d();
          NBTTagCompound compound = new NBTTagCompound();
          if (stack.func_77942_o())
            compound = stack.func_77978_p(); 
          compound.func_74778_a("dropOwner", FastUUID.toString((Entity)e.player));
          stack.func_77982_d(compound);
          item.func_92058_a(stack);
        } 
      }  
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPickup(EntityItemPickupEvent e) {
    if (WorldGuardUtils.getStates(e.item.field_70170_p, e.item.field_70165_t, e.item.field_70163_u, e.item.field_70161_v, DefaultFlag.BLOCKED_CMDS) != null)
      for (Object f : WorldGuardUtils.getStates(e.item.field_70170_p, e.item.field_70165_t, e.item.field_70163_u, e.item.field_70161_v, DefaultFlag.BLOCKED_CMDS)) {
        if ("/pickup-secure".equalsIgnoreCase(f.toString()) && 
          e.item.func_92059_d().func_77942_o() && 
          e.item.func_92059_d().func_77978_p().func_74764_b("dropOwner")) {
          if (!e.item.func_92059_d().func_77978_p().func_74779_i("dropOwner").toLowerCase().replace(" ", "").equalsIgnoreCase(FastUUID.toString((Entity)e.entityPlayer).toLowerCase().replace(" ", ""))) {
            try {
              if (!BukkitUtils.hasPermission(e.entityPlayer.func_110124_au(), "pickup-secure.bypass"))
                e.setCanceled(true); 
            } catch (Exception e2) {
              e.setCanceled(true);
            } 
            continue;
          } 
          e.item.func_92059_d().func_77978_p().func_82580_o("dropOwner");
        } 
      }  
  }
  
  @SubscribeEvent
  public void onLivingDeath(LivingDeathEvent e) {
    if (!e.entity.field_70170_p.field_72995_K && e.entity instanceof EntityWitch && 
      e.source.func_76364_f() instanceof EntityPlayer) {
      JobsPlayer jobsPlayer = JobsPlayer.get(e.source.func_76364_f());
      if (jobsPlayer.getLevel(JobType.HUNTER) < 20)
        return; 
      int random = (int)(Math.random() * 10000.0D);
      if (random <= 25)
        e.entity.func_70099_a(new ItemStack(ItemsRegister.WITHER_DUST), 1.0F); 
    } 
  }
  
  @SubscribeEvent
  public void onLivingDeath(FakeSpawnerEntityKillEvent e) {
    if (!(e.getTileEntitySpawnerController().func_145831_w()).field_72995_K && e.getFakeEntityClass().equals(EntityWitch.class) && 
      e.getKiller() instanceof EntityPlayer) {
      JobsPlayer jobsPlayer = JobsPlayer.get(e.getKiller());
      if (jobsPlayer.getLevel(JobType.HUNTER) < 20)
        return; 
      int random = (int)(Math.random() * 10000.0D);
      if (random <= 25)
        e.getKiller().func_70099_a(new ItemStack(ItemsRegister.WITHER_DUST), 1.0F); 
    } 
  }
  
  @SubscribeEvent
  public void onHurt(LivingHurtEvent e) {
    if (!(e.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)e.entity;
    if (!e.source.func_76352_a() && e.source.func_76364_f() == null)
      return; 
    ItemStack stack = player.func_70694_bm();
    if (stack == null || !(stack.func_77973_b() instanceof ItemHangGlider) || !stack.func_77942_o())
      return; 
    NBTTagCompound nbt = stack.func_77978_p();
    if (nbt.func_74764_b("Uses")) {
      nbt.func_82580_o("Uses");
      nbt.func_74772_a("lastUse", 0L);
    } 
    stack.func_77982_d(nbt);
  }
  
  @SubscribeEvent
  public void onPlayerDeathWithVelocity(LivingDeathEvent e) {
    if (!(e.entity instanceof EntityPlayer))
      return; 
    e.entityLiving.field_70159_w = 0.0D;
    e.entityLiving.field_70181_x = 0.0D;
    e.entityLiving.field_70179_y = 0.0D;
  }
  
  @SubscribeEvent
  public void onLogin(PlayerEvent.PlayerLoggedInEvent e) {
    if (e.player.field_70170_p.field_72995_K || !(e.player instanceof EntityPlayerMP))
      return; 
    VisitServerAchievement.performCheck(e.player);
    ZoneId paladiumZoneId = ZoneId.of("Europe/Paris");
    LocalDateTime nowTimed = LocalDateTime.now(paladiumZoneId);
    Map<String, String> events = new HashMap<>();
    PlannedStatued bossEvent = ChronosManager.getInstance().getRunningPlannedEvent("BOSS");
    if (bossEvent != null)
      events.put("BOSS", bossEvent.getServerName()); 
    if (bossEvent == null) {
      bossEvent = ChronosManager.getInstance().getNextPlannedEvent("BOSS");
      if (bossEvent != null)
        events.put("BOSS", DurationConverter.fromMillisToString(Duration.between(nowTimed, bossEvent.getStartDate()).getSeconds() * 1000L, 1)); 
    } 
    PlannedStatued egghuntEvent = ChronosManager.getInstance().getRunningPlannedEvent("EGGHUNT");
    if (egghuntEvent != null)
      events.put("EGGHUNT", egghuntEvent.getServerName()); 
    if (egghuntEvent == null) {
      egghuntEvent = ChronosManager.getInstance().getNextPlannedEvent("EGGHUNT");
      if (egghuntEvent != null)
        events.put("EGGHUNT", DurationConverter.fromMillisToString(Duration.between(nowTimed, egghuntEvent.getStartDate()).getSeconds() * 1000L, 1)); 
    } 
    PalaMod.getNetHandler().sendTo((IMessage)new SCPacketSyncEvents(events), (EntityPlayerMP)e.player);
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onCommand(AbstractCommandEvent e) {
    String msg = e.command;
    try {
      Player p = Bukkit.getPlayer(e.player.func_110124_au());
      if (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION && CombatTag.getInstance().getManager().isInCombat(p) && !CombatTag.getInstance().getManager().isWhitelistCommand(msg) && !p.hasPermission("combattag.bypass")) {
        e.setCanceled(true);
        p.sendMessage("§8[§cCombatTag§8] §cVous ne pouvez pas utiliser cette commande en combat.");
      } 
    } catch (NoClassDefFoundError|Exception noClassDefFoundError) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\PlayerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */