package fr.paladium.palamod.modules.back2future.core.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.ModEnchantments;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.blocks.CoarseDirt;
import fr.paladium.palamod.modules.back2future.entities.EntityEndermite;
import fr.paladium.palamod.modules.back2future.entities.EntityNewSnowGolem;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.back2future.entities.EntityTippedArrow;
import fr.paladium.palamod.modules.back2future.entities.EntityZombieVillager;
import fr.paladium.palamod.modules.back2future.entities.ai.EntityAIOpenCustomDoor;
import fr.paladium.palamod.modules.back2future.entities.player.MBExtendedPlayer;
import fr.paladium.palamod.modules.back2future.inventory.ContainerEnchantment;
import fr.paladium.palamod.modules.back2future.items.TippedArrow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ServerEventHandler {
  public static final ServerEventHandler INSTANCE = new ServerEventHandler();
  
  private Integer playerLoggedInCooldown = null;
  
  @SubscribeEvent
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    if (Back2Future.enablePlayerSkinOverlay)
      this.playerLoggedInCooldown = Integer.valueOf(20); 
  }
  
  @SubscribeEvent
  public void livingUpdate(LivingEvent.LivingUpdateEvent event) {
    ModEnchantments.onLivingUpdate(event.entityLiving);
    if (Back2Future.enableVillagerZombies && 
      !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving
      .getClass() == EntityZombie.class) {
      EntityZombie zombie = (EntityZombie)event.entityLiving;
      if (zombie.func_82231_m()) {
        EntityZombieVillager villagerZombie = new EntityZombieVillager(zombie.field_70170_p);
        villagerZombie.func_82149_j((Entity)zombie);
        villagerZombie.func_110161_a(null);
        villagerZombie.field_70170_p.func_72838_d((Entity)villagerZombie);
        zombie.func_70106_y();
      } 
    } 
    if (Back2Future.enableShearableGolems && 
      !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving
      .getClass() == EntitySnowman.class) {
      EntityNewSnowGolem golen = new EntityNewSnowGolem(event.entityLiving.field_70170_p);
      golen.func_82149_j((Entity)event.entityLiving);
      golen.func_110161_a(null);
      if (event.entityLiving instanceof EntityLiving) {
        EntityLiving entityLiving = (EntityLiving)event.entityLiving;
        if (entityLiving.func_94056_bM())
          golen.func_94058_c(entityLiving.func_94057_bL()); 
      } 
      golen.field_70170_p.func_72838_d((Entity)golen);
      event.entityLiving.func_70106_y();
    } 
  }
  
  @SubscribeEvent
  public void livingAttack(LivingAttackEvent event) {
    if (event.source instanceof EntityDamageSourceIndirect) {
      EntityDamageSourceIndirect dmgSrc = (EntityDamageSourceIndirect)event.source;
      if (dmgSrc.func_76364_f() instanceof EntityTippedArrow) {
        EntityTippedArrow tippedArrow = (EntityTippedArrow)dmgSrc.func_76364_f();
        if (!tippedArrow.field_70170_p.field_72995_K)
          event.entityLiving.func_70690_d(tippedArrow.getEffect()); 
      } 
    } 
  }
  
  @SubscribeEvent
  public void arrowNock(ArrowNockEvent event) {
    if (event.result == null)
      return; 
    InventoryPlayer inventoryPlayer = event.entityPlayer.field_71071_by;
    for (int i = 0; i < inventoryPlayer.func_70302_i_(); i++) {
      ItemStack stack = inventoryPlayer.func_70301_a(i);
      if (stack != null && stack.field_77994_a > 0) {
        if (stack.func_77973_b() == Items.field_151032_g)
          return; 
        if (stack.func_77973_b() == ModItems.tipped_arrow) {
          event.setCanceled(true);
          event.entityPlayer.func_71008_a(event.result, event.result
              .func_77973_b().func_77626_a(event.result));
          return;
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void arrowLoose(ArrowLooseEvent event) {
    if (event.bow == null)
      return; 
    InventoryPlayer inventoryPlayer = event.entityPlayer.field_71071_by;
    for (int i = 0; i < inventoryPlayer.func_70302_i_(); i++) {
      ItemStack arrow = inventoryPlayer.func_70301_a(i);
      if (arrow != null && arrow.field_77994_a > 0 && arrow.func_77973_b() == ModItems.tipped_arrow) {
        float charge = event.charge / 20.0F;
        charge = (charge * charge + charge * 2.0F) / 3.0F;
        if (charge < 0.1D)
          return; 
        if (charge > 1.0F)
          charge = 1.0F; 
        EntityTippedArrow arrowEntity = new EntityTippedArrow(event.entityPlayer.field_70170_p, (EntityLivingBase)event.entityPlayer, charge * 2.0F);
        arrowEntity.setEffect(TippedArrow.getEffect(arrow));
        if (charge == 1.0F)
          arrowEntity.func_70243_d(true); 
        int power = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, event.bow);
        if (power > 0)
          arrowEntity.func_70239_b(arrowEntity.func_70242_d() + power * 0.5D + 0.5D); 
        int punch = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, event.bow);
        if (punch > 0)
          arrowEntity.func_70240_a(punch); 
        if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, event.bow) > 0)
          arrowEntity.func_70015_d(100); 
        event.bow.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
        event.entityPlayer.field_70170_p.func_72956_a((Entity)event.entityPlayer, "random.bow", 1.0F, 1.0F / (event.entityPlayer.field_70170_p.field_73012_v
            .nextFloat() * 0.4F + 1.2F) + charge * 0.5F);
        if (!event.entityPlayer.field_71075_bZ.field_75098_d && --arrow.field_77994_a <= 0)
          event.entityPlayer.field_71071_by.func_70299_a(i, null); 
        if (!event.entityPlayer.field_70170_p.field_72995_K)
          event.entityPlayer.field_70170_p.func_72838_d((Entity)arrowEntity); 
        event.setCanceled(true);
        return;
      } 
    } 
  }
  
  @SubscribeEvent
  public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event) {
    if (!Back2Future.enableEnchants)
      return; 
    try {
      File file = event.getPlayerFile("palamod");
      if (!file.exists()) {
        file.createNewFile();
        return;
      } 
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = br.readLine();
      if (line != null) {
        int seed = Integer.parseInt(line);
        ContainerEnchantment.seeds.put(event.playerUUID, Integer.valueOf(seed));
        br.close();
      } 
    } catch (Exception exception) {}
  }
  
  @SubscribeEvent
  public void onPlayerSaveFromFileEvent(PlayerEvent.SaveToFile event) {
    if (!Back2Future.enableEnchants)
      return; 
    try {
      File file = event.getPlayerFile("palamod");
      if (!file.exists()) {
        file.createNewFile();
        return;
      } 
      Integer seed = (Integer)ContainerEnchantment.seeds.get(event.playerUUID);
      if (seed != null) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(seed.toString());
        bw.close();
      } 
    } catch (IOException iOException) {}
  }
  
  @SubscribeEvent
  public void harvestEvent(BlockEvent.HarvestDropsEvent event) {
    if (Back2Future.enableSilkTouchingMushrooms && event.isSilkTouching)
      if (event.block == Blocks.field_150420_aW) {
        event.drops.clear();
        event.drops.add(new ItemStack(ModBlocks.brown_mushroom_block));
      } else if (event.block == Blocks.field_150419_aX) {
        event.drops.clear();
        event.drops.add(new ItemStack(ModBlocks.red_mushroom_block));
      }  
    if (Back2Future.enableSticksFromDeadBushes && 
      event.block == Blocks.field_150330_I) {
      boolean isShears = (event.harvester != null && event.harvester.func_71045_bC() != null && event.harvester.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemShears);
      if (event.harvester == null || event.harvester.func_71045_bC() == null || !isShears)
        for (int i = 0; i < event.world.field_73012_v.nextInt(3); i++)
          event.drops.add(new ItemStack(Items.field_151055_y));  
    } 
    if (Back2Future.enableShearableCobwebs && 
      event.block == Blocks.field_150321_G && event.harvester != null) {
      ItemStack stack = event.harvester.func_71045_bC();
      if (stack != null && stack.func_77973_b() instanceof net.minecraft.item.ItemShears) {
        event.drops.clear();
        event.drops.add(new ItemStack(Blocks.field_150321_G));
      } 
    } 
  }
  
  @SubscribeEvent
  public void onHoeUseEvent(UseHoeEvent event) {
    CoarseDirt.onHoeEvent(event);
  }
  
  @SubscribeEvent
  public void onEntityConstructing(EntityEvent.EntityConstructing event) {
    try {
      if (event.entity instanceof EntityPlayer && 
        MBExtendedPlayer.get((EntityPlayer)event.entity) == null)
        MBExtendedPlayer.register((EntityPlayer)event.entity); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void dropEvent(LivingDropsEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K)
      return; 
    if (Back2Future.enableSkullDrop)
      dropHead(event.entityLiving, event.source, event.lootingLevel, event.drops); 
    Random rand = event.entityLiving.field_70170_p.field_73012_v;
    if (Back2Future.enableMutton && event.entityLiving instanceof net.minecraft.entity.passive.EntitySheep) {
      int amount = rand.nextInt(3) + 1 + rand.nextInt(1 + event.lootingLevel);
      for (int i = 0; i < amount; i++) {
        if (event.entityLiving.func_70027_ad()) {
          addDrop(new ItemStack(ModItems.cooked_mutton), event.entityLiving, event.drops);
        } else {
          addDrop(new ItemStack(ModItems.raw_mutton), event.entityLiving, event.drops);
        } 
      } 
    } 
  }
  
  private void dropHead(EntityLivingBase entity, DamageSource source, int looting, List<EntityItem> drops) {
    if (source.func_94541_c() && source instanceof EntityDamageSource) {
      Entity entityi = ((EntityDamageSource)source).func_76346_g();
      if (entityi != null && entityi instanceof EntityCreeper) {
        int meta = getHeadMetadata(entity);
        if (meta >= 0)
          addDrop(new ItemStack(Items.field_151144_bL, 1, meta), entity, drops); 
      } 
    } 
  }
  
  private void addDrop(ItemStack stack, EntityLivingBase entity, List<EntityItem> list) {
    if (stack.field_77994_a <= 0)
      return; 
    EntityItem entityItem = new EntityItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, stack);
    entityItem.field_145804_b = 10;
    list.add(entityItem);
  }
  
  private boolean isPoweredCreeper(DamageSource source) {
    if (source.func_94541_c() && source instanceof EntityDamageSource) {
      Entity entity = ((EntityDamageSource)source).func_76346_g();
      if (entity != null && entity instanceof EntityCreeper)
        return ((EntityCreeper)entity).func_70830_n(); 
    } 
    return false;
  }
  
  private int getHeadMetadata(EntityLivingBase entity) {
    if (entity.getClass() == EntityZombie.class)
      return 2; 
    if (entity.getClass() == EntitySkeleton.class && ((EntitySkeleton)entity)
      .func_82202_m() == 0)
      return 0; 
    if (entity.getClass() == EntityCreeper.class)
      return 4; 
    return -1;
  }
  
  @SubscribeEvent
  public void teleportEvent(EnderTeleportEvent event) {
    if (Back2Future.enableEndermite) {
      EntityLivingBase entity = event.entityLiving;
      if (entity instanceof net.minecraft.entity.player.EntityPlayerMP && 
        entity.func_70681_au().nextFloat() < 0.05F && entity.field_70170_p
        .func_82736_K().func_82766_b("doMobSpawning")) {
        EntityEndermite entityendermite = new EntityEndermite(entity.field_70170_p);
        entityendermite.setSpawnedByPlayer(true);
        entityendermite.func_70012_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
        entity.field_70170_p.func_72838_d((Entity)entityendermite);
      } 
    } 
  }
  
  @SubscribeEvent
  public void spawnEvent(EntityJoinWorldEvent event) {
    if (event.entity instanceof EntityPig) {
      EntityPig pig = (EntityPig)event.entity;
      if (Back2Future.enableBeetroot)
        pig.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAITempt((EntityCreature)pig, 1.2D, ModItems.beetroot, false)); 
    } else if (event.entity instanceof EntityChicken) {
      EntityChicken chicken = (EntityChicken)event.entity;
      if (Back2Future.enableBeetroot)
        chicken.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)chicken, 1.0D, ModItems.beetroot_seeds, false)); 
    } else if (event.entity instanceof EntityWolf) {
      EntityWolf wolf = (EntityWolf)event.entity;
      if (Back2Future.enableRabbit)
        wolf.field_70715_bh.func_75776_a(4, (EntityAIBase)new EntityAITargetNonTamed((EntityTameable)wolf, EntityRabbit.class, 200, false)); 
    } else if (event.entity instanceof EntityVillager) {
      EntityVillager villager = (EntityVillager)event.entity;
      for (Object obj : villager.field_70714_bg.field_75782_a) {
        EntityAITasks.EntityAITaskEntry entry = (EntityAITasks.EntityAITaskEntry)obj;
        if (entry.field_75733_a instanceof net.minecraft.entity.ai.EntityAIOpenDoor) {
          villager.field_70714_bg.func_85156_a(entry.field_75733_a);
          villager.field_70714_bg.func_75776_a(entry.field_75731_b, (EntityAIBase)new EntityAIOpenCustomDoor((EntityLiving)villager, true));
          break;
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void interactEntityEvent(EntityInteractEvent event) {
    ItemStack stack = event.entityPlayer.func_71045_bC();
    if (stack == null)
      return; 
    if (!(event.target instanceof EntityAnimal))
      return; 
    EntityAnimal animal = (EntityAnimal)event.target;
    if (!animal.func_70631_g_()) {
      if (animal instanceof EntityPig) {
        if (stack.func_77973_b() == ModItems.beetroot && Back2Future.enableBeetroot)
          setAnimalInLove(animal, event.entityPlayer, stack); 
      } else if (animal instanceof EntityChicken && 
        stack.func_77973_b() == ModItems.beetroot_seeds && Back2Future.enableBeetroot) {
        setAnimalInLove(animal, event.entityPlayer, stack);
      } 
    } else if (Back2Future.enableBabyGrowthBoost && isFoodItem(animal, stack)) {
      feedBaby(animal, event.entityPlayer, stack);
    } 
  }
  
  private void setAnimalInLove(EntityAnimal animal, EntityPlayer player, ItemStack stack) {
    if (!animal.func_70880_s()) {
      animal.func_146082_f(player);
      if (!player.field_71075_bZ.field_75098_d && 
        --stack.field_77994_a <= 0)
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
    } 
  }
  
  private void feedBaby(EntityAnimal animal, EntityPlayer player, ItemStack stack) {
    int currentAge = animal.func_70874_b();
    int age = (int)(-currentAge * 0.1F);
    animal.func_70873_a(currentAge + age);
    player.func_71038_i();
    Random itemRand = animal.field_70170_p.field_73012_v;
    for (int i = 0; i < 3; i++) {
      double d0 = itemRand.nextGaussian() * 0.02D;
      double d1 = itemRand.nextGaussian() * 0.02D;
      double d2 = itemRand.nextGaussian() * 0.02D;
      animal.field_70170_p.func_72869_a("happyVillager", animal.field_70165_t + itemRand.nextFloat() * 0.5D, animal.field_70163_u + 0.5D + itemRand
          .nextFloat() * 0.5D, animal.field_70161_v + itemRand.nextFloat() * 0.5D, d0, d1, d2);
    } 
    if (!player.field_71075_bZ.field_75098_d && 
      --stack.field_77994_a <= 0)
      player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
  }
  
  private boolean isFoodItem(EntityAnimal animal, ItemStack food) {
    if (animal.func_70877_b(food))
      return true; 
    if (animal instanceof EntityPig && food.func_77973_b() == ModItems.beetroot && Back2Future.enableBeetroot)
      return true; 
    if (animal instanceof EntityChicken && food.func_77973_b() == ModItems.beetroot_seeds && Back2Future.enableBeetroot)
      return true; 
    return false;
  }
  
  @SubscribeEvent
  public void entityHurtEvent(LivingHurtEvent event) {}
  
  @SubscribeEvent
  public void entityStruckByLightning(EntityStruckByLightningEvent event) {
    if (Back2Future.enableVillagerTurnsIntoWitch && event.entity instanceof EntityVillager) {
      EntityVillager villager = (EntityVillager)event.entity;
      if (!villager.field_70170_p.field_72995_K) {
        EntityWitch witch = new EntityWitch(villager.field_70170_p);
        witch.func_82149_j((Entity)villager);
        witch.func_110161_a(null);
        villager.field_70170_p.func_72838_d((Entity)witch);
        villager.func_70106_y();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\core\handlers\ServerEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */