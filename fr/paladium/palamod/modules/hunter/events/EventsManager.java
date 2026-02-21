package fr.paladium.palamod.modules.hunter.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.AchievementUtils;
import fr.paladium.palamod.modules.achievements.types.BreakSpawnerFinderAchievement;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.hunter.PHunter;
import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.hunter.gui.GuiRituelProgress;
import fr.paladium.palamod.modules.hunter.items.ItemAmulet;
import fr.paladium.palamod.modules.hunter.networks.PacketRituel;
import fr.paladium.palamod.modules.hunter.networks.PlayerBackPackProperties;
import fr.paladium.palamod.modules.hunter.networks.PlayerEndiumNameTagProperties;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityEndiumTotem;
import fr.paladium.palamod.modules.hunter.utils.AmuletHelper;
import fr.paladium.palamod.modules.luckyblock.network.PacketSpookySpook;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.PlayerUtil;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.server.skill.handler.impl.active.BlessedExplosionSkill;
import fr.paladium.tutorial.common.event.SpawnerExplodeEvent;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventsManager {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRender(RenderGameOverlayEvent.Pre e) {
    if (e.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS)
      new GuiRituelProgress(Minecraft.func_71410_x()); 
  }
  
  @SubscribeEvent
  public void onZombieSpawn(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityZombie) {
      EntityZombie zombie = (EntityZombie)e.entity;
      zombie.func_98053_h(true);
    } 
  }
  
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      if (e.entity instanceof EntityPlayerMP && !e.entity.field_70170_p.field_72995_K && 
        (EntityPlayerMP)e.entity != null)
        PHunter.network.sendTo((IMessage)new PacketRituel(-1), (EntityPlayerMP)e.entity); 
      NBTTagCompound compound = new NBTTagCompound();
      PlayerBackPackProperties.get(e.original).saveNBTData(compound);
      PlayerBackPackProperties.get(e.entityPlayer).loadNBTData(compound);
      compound = new NBTTagCompound();
      PlayerEndiumNameTagProperties.get(e.original).saveNBTData(compound);
      PlayerEndiumNameTagProperties.get(e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SubscribeEvent
  public void onConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof EntityPlayer && PlayerBackPackProperties.get((EntityPlayer)e.entity) == null)
      PlayerBackPackProperties.register((EntityPlayer)e.entity); 
    if (e.entity instanceof EntityPlayer && PlayerEndiumNameTagProperties.get((EntityPlayer)e.entity) == null)
      PlayerEndiumNameTagProperties.register((EntityPlayer)e.entity); 
  }
  
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityPlayerMP && !e.world.field_72995_K)
      PHunter.network.sendTo((IMessage)new PacketRituel(-1), (EntityPlayerMP)e.entity); 
  }
  
  @SubscribeEvent
  public void onLivingDeath(LivingDeathEvent e) {
    if (e.entityLiving instanceof EntityPlayer && !e.entityLiving.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
      int size = 81;
      EntityPlayer player = (EntityPlayer)e.entityLiving;
      int i = 0;
      for (ItemStack item : PlayerBackPackProperties.get(player).getContent()) {
        if (i < 81) {
          if (item != null)
            PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, item); 
          PlayerBackPackProperties.get(player).getContent()[i] = null;
        } 
        i++;
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onKillEntity(LivingDeathEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K || !(event.source.func_76346_g() instanceof EntityPlayer))
      return; 
    EntityPlayer p = (EntityPlayer)event.source.func_76346_g();
    if (p.func_70694_bm() != null && p.func_70694_bm().func_77973_b() == ItemsRegister.CAPTURE_SWORD) {
      event.setCanceled(true);
      if (!event.entity.field_70128_L)
        event.entity.func_70106_y(); 
    } 
  }
  
  @SubscribeEvent
  public void onLivingDrops(LivingDropsEvent e) {
    if (e.source.func_76346_g() != null && 
      e.source.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)e.source.func_76346_g();
      if (player.func_70694_bm() != null && 
        player.func_70694_bm().func_77973_b() != null && 
        player.func_70694_bm().func_77973_b() == ItemsRegister.CAPTURE_SWORD && 
        player.field_71071_by.func_146028_b(ItemsRegister.CAPTURE_STONE))
        e.setCanceled(e.isCancelable()); 
    } 
  }
  
  @SubscribeEvent
  public void onCraft(AnvilUpdateEvent e) {
    ItemStack left = e.left;
    ItemStack right = e.right;
    if (left == null || right == null)
      return; 
    if ((left.func_77973_b() == ItemsRegister.PALADIUM_CHESTPLATE || left.func_77973_b() == ItemsRegister.ENDIUM_CHESTPLATE || left.func_77973_b() == ItemsRegister.MIXED_ENDIUM_CHESTPLATE || left.func_77973_b() == ItemsRegister.PALADIUM_GREEN_CHESTPLATE) && 
      right.func_77973_b() instanceof ItemAmulet) {
      ItemAmulet amulet = (ItemAmulet)right.func_77973_b();
      ItemStack output = left.func_77946_l();
      NBTTagCompound compound = new NBTTagCompound();
      if (output.func_77942_o())
        compound = output.func_77978_p(); 
      if (hasEnchantment(left, (Enchantment)PHunter.overwhelmed)) {
        int lvl = EnchantmentHelper.func_77506_a(PHunter.overwhelmed.field_77352_x, left);
        NBTTagList amulets = new NBTTagList();
        if (compound.func_74764_b("amulets"))
          amulets = compound.func_150295_c("amulets", 8); 
        boolean hasTag = false;
        for (int i = 0; i < amulets.func_74745_c(); i++) {
          if (amulets.func_150307_f(i).equalsIgnoreCase(amulet.getType().toString()))
            hasTag = true; 
        } 
        if (hasTag)
          e.setCanceled(true); 
        amulets.func_74742_a((NBTBase)new NBTTagString(amulet.getType().toString()));
        if (amulets.func_74745_c() > lvl + 1)
          amulets.func_74744_a(0); 
        compound.func_74782_a("amulets", (NBTBase)amulets);
      } else {
        NBTTagList amulets = new NBTTagList();
        if (compound.func_74764_b("amulets"))
          amulets = compound.func_150295_c("amulets", 8); 
        boolean hasTag = false;
        for (int i = 0; i < amulets.func_74745_c(); i++) {
          if (amulets.func_150307_f(i).equalsIgnoreCase(amulet.getType().toString()))
            hasTag = true; 
        } 
        if (hasTag)
          e.setCanceled(true); 
        amulets.func_74742_a((NBTBase)new NBTTagString(amulet.getType().toString()));
        if (amulets.func_74745_c() > 1)
          amulets.func_74744_a(0); 
        compound.func_74782_a("amulets", (NBTBase)amulets);
      } 
      output.func_77982_d(compound);
      e.output = output;
      e.cost = 5;
    } 
  }
  
  @SubscribeEvent
  public void onKillWithDaemonAmulet(LivingDeathEvent e) {
    if (!(e.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.entity;
    if (e.source == null || !(e.source.func_76364_f() instanceof EntityPlayer))
      return; 
    EntityPlayer killer = (EntityPlayer)e.source.func_76364_f();
    if (!AmuletHelper.hasAmulet(killer, ItemAmulet.Type.DAEMON))
      return; 
    PalaMod.getNetwork().sendTo((IMessage)new PacketSpookySpook(), player);
  }
  
  @SubscribeEvent
  public void onUpdate(LivingEvent.LivingUpdateEvent e) {
    EntityLivingBase entity = e.entityLiving;
    World world = e.entityLiving.field_70170_p;
    if (entity instanceof EntityPlayer && !world.field_72995_K) {
      EntityPlayer player = (EntityPlayer)entity;
      ItemStack chestplate = player.func_82169_q(2);
      if (chestplate == null)
        return; 
      if ((chestplate.func_77973_b() == ItemsRegister.PALADIUM_CHESTPLATE || chestplate.func_77973_b() == ItemsRegister.ENDIUM_CHESTPLATE || chestplate.func_77973_b() == ItemsRegister.PALADIUM_GREEN_CHESTPLATE) && 
        chestplate.func_77942_o() && 
        chestplate.func_77978_p().func_74764_b("amulets")) {
        NBTTagList amulets = chestplate.func_77978_p().func_150295_c("amulets", 8);
        for (int i = 0; i < amulets.func_74745_c(); i++) {
          int c;
          ItemAmulet.Type type = ItemAmulet.Type.valueOf(amulets.func_150307_f(i));
          switch (type) {
            case HUNTER:
              c = world.field_73012_v.nextInt(72000);
              if (c == 25000) {
                EntityFarmerChicken entityFarmerChicken;
                EntityCavernousZombie entityCavernousZombie;
                EntityFlowerMonster entityFlowerMonster;
                EntityMegaCreeper entityMegaCreeper;
                Entity ent = null;
                int er = world.field_73012_v.nextInt(4);
                switch (er) {
                  case 0:
                    entityFarmerChicken = new EntityFarmerChicken(world);
                    break;
                  case 1:
                    entityCavernousZombie = new EntityCavernousZombie(world);
                    break;
                  case 2:
                    entityFlowerMonster = new EntityFlowerMonster(world);
                    break;
                  case 3:
                    entityMegaCreeper = new EntityMegaCreeper(world);
                    break;
                } 
                player.func_145747_a((IChatComponent)new ChatComponentText("§cUn mob de l'amulette Hunter vient d'apparaitre ! Fais attention à toi."));
                entityMegaCreeper.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
                world.func_72838_d((Entity)entityMegaCreeper);
                NBTTagCompound display = new NBTTagCompound();
                if (chestplate.func_77978_p().func_74764_b("display"))
                  display = chestplate.func_77978_p().func_74775_l("display"); 
                NBTTagList lores = new NBTTagList();
                if (display.func_74764_b("Lore"))
                  lores = display.func_150295_c("Lore", 8); 
                int n = 0;
                int p = 0;
                for (int i1 = 0; i1 < lores.func_74745_c(); i1++) {
                  String str = lores.func_150307_f(i1);
                  if (str.contains("Mobs spawnés")) {
                    String nStr = str.replace("§e", "");
                    nStr = nStr.replace(" Mobs spawnés", "");
                    n = Integer.parseInt(nStr);
                    p = i1;
                  } 
                } 
                n++;
                if (n > 1) {
                  lores.func_150304_a(p, (NBTBase)new NBTTagString("§e" + n + " Mobs spawnés"));
                } else {
                  lores.func_74742_a((NBTBase)new NBTTagString("§e" + n + " Mobs spawnés"));
                } 
                display.func_74782_a("Lore", (NBTBase)lores);
                chestplate.func_77978_p().func_74782_a("display", (NBTBase)display);
              } 
              break;
            case MAGMA:
              player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 600, 1));
              break;
          } 
        } 
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onRecuperation(LivingDropsEvent e) {
    EntityLivingBase entity = e.entityLiving;
    if (e.source.func_76364_f() instanceof EntityPlayer && entity instanceof EntityLiving && !((EntityLiving)entity).func_94056_bM()) {
      EntityPlayer player = (EntityPlayer)e.source.func_76364_f();
      if (player.func_70694_bm() != null && !player.field_70170_p.field_72995_K && !e.isCanceled() && 
        hasEnchantment(player.func_70694_bm(), (Enchantment)PHunter.recuperation)) {
        for (EntityItem entityItem : e.drops) {
          ItemStack item = entityItem.func_92059_d();
          if (item != null && 
            !player.field_71071_by.func_70441_a(item.func_77946_l()))
            PlayerUtils.dropItemStack((Entity)player, entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, item.func_77946_l()); 
        } 
        e.drops.clear();
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onRecuperationDig(BlockEvent.HarvestDropsEvent e) {
    if (e.harvester != null) {
      EntityPlayer player = e.harvester;
      if (player.func_70694_bm() != null && !player.field_70170_p.field_72995_K && !e.isCanceled() && 
        hasEnchantment(player.func_70694_bm(), (Enchantment)PHunter.recuperation_dig)) {
        for (ItemStack item : e.drops)
          PlayerUtil.addItemStackToInventory(item, player.field_71071_by); 
        e.drops.clear();
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onXpStealer(LivingHurtEvent e) {
    EntityLivingBase entity = e.entityLiving;
    if (e.source.func_76364_f() instanceof EntityPlayer && entity instanceof EntityPlayer && !entity.field_70170_p.field_72995_K) {
      try {
        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)Bukkit.getPlayer(e.source.func_76364_f().func_110124_au()), (Entity)Bukkit.getPlayer(e.entity.func_110124_au()), EntityDamageEvent.DamageCause.CONTACT, 0);
        Bukkit.getServer().getPluginManager().callEvent((Event)event);
        if (event.isCancelled())
          return; 
      } catch (Exception exception) {}
      EntityPlayer player = (EntityPlayer)e.source.func_76364_f();
      EntityPlayer target = (EntityPlayer)entity;
      if (player.func_70694_bm() != null && e.ammount > 0.0F && !e.isCanceled() && 
        hasEnchantment(player.func_70694_bm(), (Enchantment)PHunter.xp_stealer)) {
        int lvl = EnchantmentHelper.func_77506_a(PHunter.xp_stealer.field_77352_x, player.func_70694_bm());
        int chance = (lvl == 1) ? 2 : ((lvl == 2) ? 4 : ((lvl == 3) ? 7 : 10));
        if (player.field_70170_p.field_73012_v.nextInt(100) < chance) {
          int steal = (lvl == 1) ? 40 : ((lvl == 2) ? 50 : ((lvl == 3) ? 70 : 80));
          steal = Math.min(steal, target.field_71067_cb);
          if (target.field_71067_cb - steal < 0)
            steal = target.field_71067_cb; 
          if (target.field_71106_cc <= 0.0F && target.field_71068_ca <= 0)
            return; 
          decreaseExp(target, steal);
          player.func_71023_q(steal);
        } 
      } 
    } 
  }
  
  public static void decreaseExp(EntityPlayer player, float amount) {
    if (player.field_71067_cb - amount <= 0.0F) {
      player.field_71068_ca = 0;
      player.field_71106_cc = 0.0F;
      player.field_71067_cb = 0;
      return;
    } 
    player.field_71067_cb = (int)(player.field_71067_cb - amount);
    if (player.field_71106_cc * player.func_71050_bK() <= amount) {
      amount -= player.field_71106_cc * player.func_71050_bK();
      player.field_71106_cc = 1.0F;
      player.field_71068_ca--;
    } 
    while (player.func_71050_bK() < amount) {
      amount -= player.func_71050_bK();
      player.field_71068_ca--;
    } 
    player.field_71106_cc -= amount / player.func_71050_bK();
  }
  
  @SubscribeEvent
  public void onHealWithContreBuff(LivingHealEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PHunter.CONTRE_BUFF))
      e.amount -= e.amount / 10.0F * (e.entityLiving.func_70660_b((Potion)PHunter.CONTRE_BUFF).func_76458_c() + 1); 
  }
  
  private boolean hasEnchantment(ItemStack item, Enchantment ench) {
    if (item == null)
      return false; 
    if (item.func_77986_q() != null && 
      EnchantmentHelper.func_82781_a(item).get(Integer.valueOf(ench.field_77352_x)) != null)
      return true; 
    return false;
  }
  
  @SubscribeEvent
  public void onEndiumNameTagEntityDeath(LivingDeathEvent e) {
    if (e.entity.getEntityData() != null && 
      e.entity.getEntityData().func_74764_b("endium_nametag_owner")) {
      String uuid = e.entity.getEntityData().func_74779_i("endium_nametag_owner");
      EntityPlayer owner = getEntityPlayerByUUID(e.entity.field_70170_p, FastUUID.parseUUID(uuid));
      if (owner != null) {
        String entityUuid = FastUUID.toString(e.entity);
        if (PlayerEndiumNameTagProperties.get(owner).getEntities().contains(entityUuid))
          PlayerEndiumNameTagProperties.get(owner).getEntities().remove(entityUuid); 
      } 
    } 
  }
  
  public static EntityPlayer getEntityPlayerByUUID(World world, UUID uuid) {
    for (Object element : world.field_73010_i) {
      EntityPlayer entityplayer = (EntityPlayer)element;
      if (uuid.equals(entityplayer.func_110124_au()))
        return entityplayer; 
    } 
    return null;
  }
  
  @SubscribeEvent
  public void onKill(LivingDeathEvent e) {
    if (e.entityLiving instanceof EntityPlayer && e.source.func_76364_f() != null) {
      EntityPlayer target = (EntityPlayer)e.entityLiving;
      if (e.source.func_76364_f() instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer)e.source.func_76364_f();
        ItemStack skull = null;
        for (int i = 0; i < 9; i++) {
          if (player.field_71071_by.func_70301_a(i) != null && 
            player.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151144_bL && 
            player.field_71071_by.func_70301_a(i).func_77960_j() != 1) {
            skull = player.field_71071_by.func_70301_a(i);
            break;
          } 
        } 
        if (skull != null) {
          skull.func_77964_b(3);
          NBTTagCompound compound = new NBTTagCompound();
          if (skull.func_77942_o())
            compound = skull.func_77978_p(); 
          compound.func_74778_a("SkullOwner", target.func_70005_c_());
          skull.func_77982_d(compound);
        } 
      } 
    } 
  }
  
  private void applySpawnerPetSkill(EntityPlayerMP player, World world, double x, double y, double z) {
    if (player == null || !BlessedExplosionSkill.VALUES.containsKey(player.func_110124_au()))
      return; 
    double random = Math.random() * 100.0D;
    double value = ((Double)BlessedExplosionSkill.VALUES.get(player.func_110124_au())).doubleValue();
    BlessedExplosionSkill.VALUES.remove(player.func_110124_au());
    if (random > value) {
      PetTranslateEnum.MESSAGE_BLESSED_EXPLOSION_FAILED.message((ICommandSender)player);
      return;
    } 
    PetTranslateEnum.MESSAGE_BLESSED_EXPLOSION_SUCCESS.message((ICommandSender)player);
    ItemStack item = new ItemStack(SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER);
    EntityItem entityItem = new EntityItem(world, x, y, z, item);
    entityItem.field_145804_b = 0;
    world.func_72838_d((Entity)entityItem);
  }
  
  @SubscribeEvent
  public void onExplodeCreeper(ExplosionEvent.Detonate e) {
    if (e.explosion.field_77283_e instanceof net.minecraft.entity.monster.EntityCreeper && !e.world.field_72995_K)
      for (ChunkPosition pos : e.getAffectedBlocks()) {
        if (e.world.func_147439_a(pos.field_151329_a, pos.field_151327_b, pos.field_151328_c) instanceof net.minecraft.block.BlockMobSpawner) {
          EntityPlayerMP player = (EntityPlayerMP)e.world.func_72977_a(pos.field_151329_a, pos.field_151327_b, pos.field_151328_c, 10.0D);
          if (player != null && InventoryUtils.haveRequiredItem((EntityPlayer)player, new ItemStack(ItemsRegister.SPAWNER_FINDER)))
            AchievementUtils.performCheck(BreakSpawnerFinderAchievement.class, (EntityPlayer)player, 1); 
          int c = e.world.field_73012_v.nextInt(2);
          if (c == 0) {
            ItemStack item = new ItemStack(SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER);
            MinecraftForge.EVENT_BUS.post((Event)new SpawnerExplodeEvent((EntityPlayer)player, item));
            EntityItem entityItem = new EntityItem(e.world, pos.field_151329_a, pos.field_151327_b, pos.field_151328_c, item);
            entityItem.field_145804_b = 0;
            e.world.func_72838_d((Entity)entityItem);
            applySpawnerPetSkill(player, e.world, pos.field_151329_a, pos.field_151327_b, pos.field_151328_c);
          } 
        } 
      }  
  }
  
  @SubscribeEvent
  public void onLivingDropsDuringRituel(LivingDropsEvent e) {
    if (e.entity instanceof EntityZombie) {
      EntityZombie entity = (EntityZombie)e.entityLiving;
      World world = entity.field_70170_p;
      if (e.source.func_76364_f() != null && e.source.func_76364_f() instanceof EntityPlayer) {
        boolean isValid = true;
        for (int i = 1; i <= 4; i++) {
          if (entity.func_71124_b(i) == null || !entity.func_71124_b(i).func_77973_b().func_77658_a().contains("paladium")) {
            isValid = false;
            break;
          } 
        } 
        if (isValid) {
          TileEntityEndiumTotem te = null;
          for (int ox = -7; ox <= 7; ox++) {
            for (int oz = -7; oz <= 7; oz++) {
              BlockPos pos = new BlockPos(entity.field_70165_t + ox, entity.field_70163_u, entity.field_70161_v + oz);
              if (world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()) != null && 
                world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()) instanceof TileEntityEndiumTotem)
                te = (TileEntityEndiumTotem)world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()); 
            } 
          } 
          if (te != null && 
            te.isActive())
            e.setCanceled(e.isCancelable()); 
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onZombieDeath(LivingDeathEvent e) {
    if (e.entityLiving instanceof EntityZombie) {
      EntityZombie entity = (EntityZombie)e.entityLiving;
      World world = entity.field_70170_p;
      if (e.source.func_76364_f() != null && e.source.func_76364_f() instanceof EntityPlayer) {
        boolean isValid = true;
        for (int i = 1; i <= 4; i++) {
          if (entity.func_71124_b(i) == null || !entity.func_71124_b(i).func_77973_b().func_77658_a().contains("paladium")) {
            isValid = false;
            break;
          } 
        } 
        if (isValid) {
          TileEntityEndiumTotem te = null;
          for (int ox = -7; ox <= 7; ox++) {
            for (int oz = -7; oz <= 7; oz++) {
              BlockPos pos = new BlockPos(entity.field_70165_t + ox, entity.field_70163_u, entity.field_70161_v + oz);
              if (world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()) != null && 
                world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()) instanceof TileEntityEndiumTotem)
                te = (TileEntityEndiumTotem)world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()); 
            } 
          } 
          if (te != null && 
            te.isActive())
            te.setZombies(te.getZombies() + 1); 
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onUpdateNearJellyFish(LivingEvent.LivingUpdateEvent e) {
    if (e.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)e.entity;
      if (player.func_70090_H() && player.field_70170_p.field_73012_v.nextInt(5) == 0) {
        AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(player.field_70165_t - 2.0D, player.field_70163_u - 2.0D, player.field_70161_v - 2.0D, player.field_70165_t + 2.0D, player.field_70163_u + 2.0D, player.field_70161_v + 2.0D);
        List<EntityLivingBase> jellyfish = player.func_130014_f_().func_72872_a(EntityJellyFish.class, scanAbove);
        if (jellyfish.size() > 0)
          player.func_70097_a(DamageSource.func_76358_a(jellyfish.get(0)), 3.0F); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */