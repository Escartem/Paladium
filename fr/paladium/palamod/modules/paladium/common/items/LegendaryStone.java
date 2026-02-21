package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.common.eep.LegendaryStoneEEP;
import fr.paladium.palamod.modules.paladium.network.PacketLegendaryEffect;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LegendaryStone extends BaseItem implements ITooltipWiki {
  protected static final int minX = -25000;
  
  protected static final int maxX = 25000;
  
  protected static final int minZ = -25000;
  
  protected static final int maxZ = 25000;
  
  private final Effect effect;
  
  public enum Effect {
    RANDOM(0, "RANDOM", ItemsRegister.LEGENDARYSTONE_RANDOM, 999, 1.0F, 1.0F, 1.0F),
    TELEPORTATION(1, "TELEPORTATION", ItemsRegister.LEGENDARYSTONE_TELEPORTATION, 13, 0.5F, 0.88F, 0.7F),
    INVISIBILITY(2, "INVISIBILITY", ItemsRegister.LEGENDARYSTONE_INVISIBILITY, 9, 0.8F, 0.8F, 0.8F),
    FORTUNE(3, "FORTUNE", ItemsRegister.LEGENDARYSTONE_FORTUNE, 10, 1.0F, 0.88F, 0.03F),
    POWER(4, "POWER", ItemsRegister.LEGENDARYSTONE_POWER, 12, 0.8F, 0.08F, 0.2F),
    JOBS(5, "JOBS", ItemsRegister.LEGENDARYSTONE_JOBS, 14, 0.3F, 0.7F, 0.2F),
    CHAOS(6, "CHAOS", ItemsRegister.LEGENDARYSTONE_CHAOS, 11, 0.5F, 0.08F, 1.0F);
    
    Effect(int id, String displayName, Item item, int type, float red, float green, float blue) {
      this.id = id;
      this.displayName = displayName;
      this.item = item;
      this.type = type;
      this.red = red;
      this.green = green;
      this.blue = blue;
    }
    
    private final int id;
    
    private final String displayName;
    
    private final Item item;
    
    private final int type;
    
    private final float red;
    
    private final float green;
    
    private final float blue;
    
    public int getId() {
      return this.id;
    }
    
    public int getType() {
      return this.type;
    }
    
    public float getRed() {
      return this.red;
    }
    
    public float getGreen() {
      return this.green;
    }
    
    public float getBlue() {
      return this.blue;
    }
    
    public String getDisplayName() {
      return "LEGENDARYSTONE_" + this.displayName;
    }
    
    public Item getItem() {
      switch (this) {
        case RANDOM:
          return ItemsRegister.LEGENDARYSTONE_RANDOM;
        case TELEPORTATION:
          return ItemsRegister.LEGENDARYSTONE_TELEPORTATION;
        case INVISIBILITY:
          return ItemsRegister.LEGENDARYSTONE_INVISIBILITY;
        case FORTUNE:
          return ItemsRegister.LEGENDARYSTONE_FORTUNE;
        case POWER:
          return ItemsRegister.LEGENDARYSTONE_POWER;
        case JOBS:
          return ItemsRegister.LEGENDARYSTONE_JOBS;
        case CHAOS:
          return ItemsRegister.LEGENDARYSTONE_CHAOS;
      } 
      return this.item;
    }
  }
  
  public static HashMap<String, Long> UUID_PROTECT_POWER = new HashMap<>();
  
  public LegendaryStone(Effect effect) {
    super("legendaryStone/" + effect.getDisplayName());
    this.effect = effect;
    func_77655_b(effect.getDisplayName());
    func_77625_d(1);
  }
  
  private long applyPetSkill(EntityPlayer player, long cooldownTime) {
    PetPlayer pet = PetPlayer.get(player);
    PassiveResponse response = PassiveSkillEnum.LEGENDARY_EXPERT.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return cooldownTime; 
    long minutes = (long)(60.0D * value);
    if (minutes <= 0L)
      return cooldownTime; 
    return cooldownTime - TimeUnit.MINUTES.toMillis(minutes);
  }
  
  @SideOnly(Side.CLIENT)
  private long applyClientPetSkill(long cooldownTime) {
    double value = PetClientProxy.getInstance().getSkillValue(PassiveSkillEnum.LEGENDARY_EXPERT);
    if (value <= 0.0D)
      return cooldownTime; 
    long minutes = (long)(60.0D * value);
    if (minutes <= 0L)
      return cooldownTime; 
    return cooldownTime - TimeUnit.MINUTES.toMillis(minutes);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
    int effectId;
    ItemStack itemGive;
    Random r;
    double posX, posZ, posY, x, y, z, offset;
    AxisAlignedBB scanAbove;
    int count, fortuneId;
    ItemStack stack;
    EntityItem entity;
    int jobs, xp;
    Thread t;
    if (world.field_72995_K || WorldGuardUtils.isItemEffectBlocked((Entity)entityPlayer, itemStack))
      return itemStack; 
    long itemCooldown = 86400000L;
    String lastUseKey = "LAST_USE";
    if (itemStack.func_77973_b() instanceof ItemEndiumGauntlet) {
      int[] modifiers = UpgradeHelper.getUpgradeAmmount(itemStack);
      if (modifiers != null && 
        modifiers.length == 6)
        itemCooldown /= 2L; 
      lastUseKey = this.effect.getDisplayName() + "_LAST_USE";
      if (!itemStack.func_77978_p().func_74764_b(lastUseKey))
        itemStack.func_77978_p().func_74772_a(lastUseKey, 0L); 
    } 
    long playerCooldown = applyPetSkill(entityPlayer, (this.effect == Effect.TELEPORTATION || this.effect == Effect.CHAOS) ? 600000L : 3600000L);
    itemCooldown = applyPetSkill(entityPlayer, itemCooldown);
    long playerTime = getTime(entityPlayer);
    long itemTime = (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b(lastUseKey)) ? itemStack.func_77978_p().func_74763_f(lastUseKey) : 0L;
    if (playerTime + playerCooldown > System.currentTimeMillis()) {
      entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu dois encore attendre " + DurationConverter.fromMillisToString(playerTime + playerCooldown - System.currentTimeMillis()) + " avant de pouvoir l'utiliser ce type de legendary stone."));
      return itemStack;
    } 
    if (itemTime + itemCooldown > System.currentTimeMillis()) {
      entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu dois encore attendre " + DurationConverter.fromMillisToString(itemTime + itemCooldown - System.currentTimeMillis()) + " avant de pouvoir utiliser cette legendary stone."));
      return itemStack;
    } 
    UseItemAchievement.performCheck(entityPlayer, itemStack, itemStack.func_77977_a());
    LegendaryStoneEEP eep = LegendaryStoneEEP.get((Entity)entityPlayer);
    if (eep != null)
      eep.increaseUse(this.effect.name()); 
    switch (this.effect.getId()) {
      case 0:
        effectId = (int)(Math.random() * 6.0D + 1.0D);
        switch (effectId) {
          case 1:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_TELEPORTATION);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.TELEPORTATION.red, Effect.TELEPORTATION.green, Effect.TELEPORTATION.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Téléportation, mais à quoi cela sert-il ?"));
            break;
          case 2:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_INVISIBILITY);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.INVISIBILITY.red, Effect.INVISIBILITY.green, Effect.INVISIBILITY.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone d'Invisibilité, mais à quoi cela sert-il ?"));
            break;
          case 3:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_FORTUNE);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.FORTUNE.red, Effect.FORTUNE.green, Effect.FORTUNE.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Richesse, mais à quoi cela sert-il ?"));
            break;
          case 4:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_POWER);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.POWER.red, Effect.POWER.green, Effect.POWER.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reu un super item, une LengendaryStone de Puissance, mais à quoi cela sert-il ?"));
            break;
          case 5:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_JOBS);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.JOBS.red, Effect.JOBS.green, Effect.JOBS.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Métier, mais à quoi cela sert-il ?"));
            break;
          case 6:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_CHAOS);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.CHAOS.red, Effect.CHAOS.green, Effect.CHAOS.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Chaos, mais à quoi cela sert-il ?"));
            break;
          default:
            itemGive = new ItemStack(Items.field_151044_h);
            itemGive.func_151001_c("§4ERROR PLZ CONTACT ADMINISTRATOR");
            break;
        } 
        itemStack.field_77994_a--;
        if (itemGive != null)
          FMLServerScheduler.getInstance().add(new Runnable[] { () -> InventoryUtils.giveOrDropitems(entityPlayer, itemGive) }); 
        return itemStack;
      case 1:
        r = world.field_73012_v;
        posX = (r.nextInt(50000) + -25000);
        posZ = (r.nextInt(50000) + -25000);
        posY = getIntTopBlock(world, (int)posX, (int)posZ);
        try {
          int tryCount = 0;
          while (!EventUtils.canInteract(entityPlayer, (int)posX, (int)posY, (int)posZ)) {
            if (tryCount >= 10) {
              entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une chose étrange est arrivée, la téléportation n'a pas fonctionné !"));
              // Byte code: goto -> 1923
            } 
            posX = (r.nextInt(50000) + -25000);
            posZ = (r.nextInt(50000) + -25000);
            posY = getIntTopBlock(world, (int)posX, (int)posZ);
            tryCount++;
          } 
        } catch (Exception exception) {}
        try {
          Player player = Bukkit.getPlayer(entityPlayer.func_110124_au());
          if (player != null && player.isOnline()) {
            World bukkitWorld = player.getWorld();
            Location location = new Location(bukkitWorld, posX, posY + 2.0D, posZ);
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
          } 
        } catch (Exception e) {
          entityPlayer.func_70634_a(posX, posY + 2.0D, posZ);
        } 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.TELEPORTATION.red, Effect.TELEPORTATION.green, Effect.TELEPORTATION.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une chose étrange est arrivée, tu as été téléporté(e) à un autre endroit !"));
        break;
      case 2:
        x = entityPlayer.field_70165_t;
        y = entityPlayer.field_70163_u;
        z = entityPlayer.field_70161_v;
        offset = 3.0D;
        scanAbove = AxisAlignedBB.func_72330_a(x - 3.0D, y - 3.0D, z - 3.0D, x + 3.0D, y + 3.0D, z + 3.0D);
        for (Object playerObj : entityPlayer.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove)) {
          if (!(playerObj instanceof EntityPlayer))
            continue; 
          EntityPlayer player = (EntityPlayer)playerObj;
          player.func_70690_d(new PotionEffect(Potion.field_76441_p.func_76396_c(), 2400, 1, false));
          player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une potion trange est tombé sur toi, tu es maintenant invisible pendant 2 minutes !"));
        } 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.INVISIBILITY.red, Effect.INVISIBILITY.green, Effect.INVISIBILITY.blue), (EntityPlayerMP)entityPlayer);
        break;
      case 3:
        count = (int)(Math.random() * 24.0D + 2.0D);
        fortuneId = (int)(Math.random() * 100.0D + 1.0D);
        if (fortuneId <= 15) {
          stack = new ItemStack(Blocks.field_150402_ci, count);
        } else if (fortuneId <= 30) {
          stack = new ItemStack(Blocks.field_150339_S, count);
        } else if (fortuneId <= 45) {
          stack = new ItemStack(Blocks.field_150340_R, count);
        } else if (fortuneId <= 60) {
          stack = new ItemStack(Blocks.field_150484_ah, count);
        } else if (fortuneId <= 75) {
          stack = new ItemStack(BlocksRegister.BLOCK_AMETHYST, count);
        } else if (fortuneId <= 90) {
          stack = new ItemStack(BlocksRegister.BLOCK_TITANE, count);
        } else {
          stack = new ItemStack(BlocksRegister.BLOCK_PALADIUM, count);
          PPalaDynamique.instance.addGenerated("LEGENDARY_STONE", count);
        } 
        entity = entityPlayer.func_71019_a(stack, false);
        if (entity != null)
          entity.field_145804_b = 0; 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.FORTUNE.red, Effect.FORTUNE.green, Effect.FORTUNE.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7La fortune est tombée sur toi !"));
        break;
      case 4:
        UUID_PROTECT_POWER.put(FastUUID.toString((Entity)entityPlayer), Long.valueOf(System.currentTimeMillis()));
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.POWER.red, Effect.POWER.green, Effect.POWER.blue, this.effect), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un grand pouvoir, Tu es immunisé de la Sulfuric water pendant 5 Min."));
        break;
      case 5:
        jobs = (int)(Math.random() * 4.0D);
        xp = (int)(Math.random() * 15001.0D + 5000.0D);
        if ((JobType.values()).length >= jobs) {
          JobType aJob = JobType.values()[jobs];
          JobsPlayer jobsPlayer = JobsPlayer.get((Entity)entityPlayer);
          jobsPlayer.addXp(aJob, ObjectiveType.FISH, xp, entityPlayer);
        } else {
          entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une erreur s'est produite, contacte un administrateur."));
        } 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.JOBS.red, Effect.JOBS.green, Effect.JOBS.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as bien travaillé et tu as gagné beaucoup d'expérience. Bravo !"));
        break;
      case 6:
        t = new Thread(() -> {
              Random rand = world.field_73012_v;
              for (Object Oplayer : entityPlayer.field_70170_p.field_73010_i) {
                EntityPlayer player = (EntityPlayer)Oplayer;
                if (!player.func_110124_au().equals(entityPlayer.func_110124_au())) {
                  Float d = Float.valueOf(player.func_70032_d((Entity)entityPlayer));
                  if (d.floatValue() <= 10.0F)
                    for (int i = 1; i <= 20; i++) {
                      int slotA = rand.nextInt(36);
                      int slotB = rand.nextInt(36);
                      ItemStack itemA = player.field_71071_by.func_70301_a(slotA);
                      ItemStack itemB = player.field_71071_by.func_70301_a(slotB);
                      player.field_71071_by.func_70299_a(slotA, itemB);
                      player.field_71071_by.func_70299_a(slotB, itemA);
                    }  
                } 
              } 
            });
        t.start();
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.CHAOS.red, Effect.CHAOS.green, Effect.CHAOS.blue, this.effect), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu viens de lancer le chaos autour de toi !"));
        break;
    } 
    if (this.effect.getId() == 6 || this.effect.getId() == 1)
      entityPlayer.getEntityData().func_74772_a("legendary_" + this.effect, System.currentTimeMillis()); 
    NBTTagCompound nbtTagCompound = itemStack.func_77978_p();
    nbtTagCompound.func_82580_o(lastUseKey);
    nbtTagCompound.func_74772_a(lastUseKey, System.currentTimeMillis());
    itemStack.func_77982_d(nbtTagCompound);
    return itemStack;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77636_d(ItemStack itemStack) {
    long playerCooldown = (this.effect == Effect.TELEPORTATION) ? 600000L : 3600000L;
    long itemCooldown = 86400000L;
    long playerTime = getTime((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    long itemTime = (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b("LAST_USE")) ? itemStack.func_77978_p().func_74763_f("LAST_USE") : 0L;
    return (playerTime + playerCooldown <= System.currentTimeMillis() && itemTime + 86400000L <= System.currentTimeMillis());
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack itemStack, EntityPlayer player, List<String> list, boolean hand) {
    super.func_77624_a(itemStack, player, list, hand);
    if (Effect.RANDOM.equals(this.effect)) {
      list.add("§7Surprise, Que va-t-il y avoir dedans ? ");
    } else {
      long playerCooldown = (this.effect == Effect.TELEPORTATION) ? applyClientPetSkill(600000L) : applyClientPetSkill(3600000L);
      long itemCooldown = applyClientPetSkill(86400000L);
      long playerTime = getTime(player);
      long itemTime = (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b("LAST_USE")) ? itemStack.func_77978_p().func_74763_f("LAST_USE") : 0L;
      if (playerTime + playerCooldown <= System.currentTimeMillis() && itemTime + itemCooldown <= System.currentTimeMillis()) {
        list.add("§7Pret a l'utilisation !");
      } else {
        Long rTime = Long.valueOf(playerTime + playerCooldown - System.currentTimeMillis());
        Long rTime2 = Long.valueOf(itemTime + itemCooldown - System.currentTimeMillis());
        if (rTime.longValue() > 0L) {
          list.add("§7Tu dois encore attendre " + DurationConverter.fromMillisToString(rTime.longValue()));
          list.add("§7avant de pouvoir l'utiliser ce type de legendary stone.");
        } 
        if (rTime2.longValue() > 0L) {
          if (rTime.longValue() > 0L)
            list.add(""); 
          list.add("§7Tu dois encore attendre " + DurationConverter.fromMillisToString(rTime2.longValue()));
          list.add("§7avant de pouvoir utiliser cette legendary stone.");
        } 
      } 
    } 
  }
  
  private long getTime(EntityPlayer player) {
    String name = "legendary_" + this.effect;
    return player.getEntityData().func_74764_b(name) ? player.getEntityData().func_74763_f(name) : 0L;
  }
  
  public void func_77663_a(ItemStack itemStack, World world, Entity entity, int slot, boolean inHand) {
    super.func_77663_a(itemStack, world, entity, slot, inHand);
    if ((!itemStack.func_77942_o() || !itemStack.func_77978_p().func_74764_b("SECURITY")) && !world.field_72995_K) {
      NBTTagCompound nbtSecu = new NBTTagCompound();
      NBTTagCompound nbtSecuItem = new NBTTagCompound();
      nbtSecuItem.func_74778_a("SECU_ITEM_UUID", FastUUID.toString(UUID.randomUUID()));
      nbtSecuItem.func_74772_a("SECU_ITEM_CREATE", System.currentTimeMillis());
      NBTTagCompound nbtSecuPlayer = new NBTTagCompound();
      nbtSecuPlayer.func_74778_a("UUID", FastUUID.toString(entity));
      nbtSecuPlayer.func_74757_a("HAND", inHand);
      nbtSecuPlayer.func_74768_a("SLOT", slot);
      nbtSecuPlayer.func_74778_a("CLASS", entity.getClass().getName());
      try {
        NBTTagCompound nbtPos = new NBTTagCompound();
        nbtPos.func_74780_a("X", entity.field_70165_t);
        nbtPos.func_74780_a("Y", entity.field_70163_u);
        nbtPos.func_74780_a("Z", entity.field_70161_v);
        nbtPos.func_74778_a("WORLD", InetAddress.getLocalHost().getHostAddress());
        nbtSecuPlayer.func_74782_a("POS", (NBTBase)nbtPos);
        nbtSecu.func_74782_a("ITEM", (NBTBase)nbtSecuItem);
        nbtSecu.func_74782_a("PLAYER", (NBTBase)nbtSecuPlayer);
        nbtSecu.func_74778_a("VM_NAME", InetAddress.getLocalHost().getHostAddress());
      } catch (UnknownHostException e) {
        System.out.println("Error nameVM message: " + e.getMessage());
      } 
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      if (itemStack.func_77942_o())
        nbtTagCompound = itemStack.func_77978_p(); 
      nbtTagCompound.func_74782_a("SECURITY", (NBTBase)nbtSecu);
      nbtTagCompound.func_74772_a("LAST_USE", 0L);
      itemStack.func_77982_d(nbtTagCompound);
    } 
  }
  
  public static int getIntTopBlock(World world, int x, int y) {
    int k;
    for (k = 63; !world.func_147437_c(x, k + 1, y) && k < 256; k++);
    return k;
  }
  
  public Effect getEffect() {
    return this.effect;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-legendary-stones#1.-quest-ce-quune-legendary-stone-et-comment-en-obtenir";
  }
  
  public static void performEffects(int id, EntityPlayer entityPlayer, World world) {
    int effectId;
    ItemStack itemGive;
    EntityItem entityitem;
    Random r;
    double posX;
    double posZ;
    double posY;
    int count;
    int fortuneId;
    ItemStack stack;
    EntityItem entity;
    int jobs;
    int xp;
    Thread t;
    switch (id) {
      case 0:
        effectId = (int)(Math.random() * 6.0D + 1.0D);
        switch (effectId) {
          case 1:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_TELEPORTATION);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.TELEPORTATION.red, Effect.TELEPORTATION.green, Effect.TELEPORTATION.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Téléportation, mais à quoi cela sert-il ?"));
            break;
          case 2:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_INVISIBILITY);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.INVISIBILITY.red, Effect.INVISIBILITY.green, Effect.INVISIBILITY.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone d'Invisibilité, mais à quoi cela sert-il ?"));
            break;
          case 3:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_FORTUNE);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.FORTUNE.red, Effect.FORTUNE.green, Effect.FORTUNE.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Richesse, mais à quoi cela sert-il ?"));
            break;
          case 4:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_POWER);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.POWER.red, Effect.POWER.green, Effect.POWER.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Puissance, mais à quoi cela sert-il ?"));
            break;
          case 5:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_JOBS);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.JOBS.red, Effect.JOBS.green, Effect.JOBS.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Métier, mais à quoi cela sert-il ?"));
            break;
          case 6:
            itemGive = new ItemStack(ItemsRegister.LEGENDARYSTONE_CHAOS);
            PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.CHAOS.red, Effect.CHAOS.green, Effect.CHAOS.blue), (EntityPlayerMP)entityPlayer);
            entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un super item, une LengendaryStone de Chaos, mais à quoi cela sert-il ?"));
            break;
          default:
            itemGive = new ItemStack(Items.field_151044_h);
            itemGive.func_151001_c("§4ERROR PLZ CONTACT ADMINISTRATOR");
            break;
        } 
        entityitem = entityPlayer.func_71019_a(itemGive, false);
        if (entityitem != null)
          entityitem.field_145804_b = 0; 
      case 1:
        r = world.field_73012_v;
        posX = (r.nextInt(50000) + -25000);
        posZ = (r.nextInt(50000) + -25000);
        posY = getIntTopBlock(world, (int)posX, (int)posZ);
        try {
          while (!EventUtils.canInteract(entityPlayer, (int)posX, (int)posY, (int)posZ)) {
            posX = (r.nextInt(50000) + -25000);
            posZ = (r.nextInt(50000) + -25000);
            posY = getIntTopBlock(world, (int)posX, (int)posZ);
          } 
        } catch (Exception exception) {}
        try {
          Player player = Bukkit.getPlayer(entityPlayer.func_110124_au());
          if (player != null && player.isOnline()) {
            World bukkitWorld = player.getWorld();
            Location location = new Location(bukkitWorld, posX, posY + 2.0D, posZ);
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
          } 
        } catch (Exception e) {
          entityPlayer.func_70634_a(posX, posY + 2.0D, posZ);
        } 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.TELEPORTATION.red, Effect.TELEPORTATION.green, Effect.TELEPORTATION.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une chose étrange est arrivée, tu as été téléporté(e) à un autre endroit !"));
        break;
      case 2:
        entityPlayer
          .func_70690_d(new PotionEffect(Potion.field_76441_p.func_76396_c(), 1200, 1, false));
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.INVISIBILITY.red, Effect.INVISIBILITY.green, Effect.INVISIBILITY.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as été touché(e) par une mystérieuse potion... Tu es maintenant invisible pendant 2 minutes !"));
        break;
      case 3:
        count = (int)(Math.random() * 24.0D + 2.0D);
        fortuneId = (int)(Math.random() * 100.0D + 1.0D);
        if (fortuneId <= 15) {
          stack = new ItemStack(Blocks.field_150402_ci, count);
        } else if (fortuneId > 15 && fortuneId <= 30) {
          stack = new ItemStack(Blocks.field_150339_S, count);
        } else if (fortuneId > 30 && fortuneId <= 45) {
          stack = new ItemStack(Blocks.field_150340_R, count);
        } else if (fortuneId > 45 && fortuneId <= 60) {
          stack = new ItemStack(Blocks.field_150484_ah, count);
        } else if (fortuneId > 60 && fortuneId <= 75) {
          stack = new ItemStack(BlocksRegister.BLOCK_AMETHYST, count);
        } else if (fortuneId > 75 && fortuneId <= 90) {
          stack = new ItemStack(BlocksRegister.BLOCK_TITANE, count);
        } else {
          stack = new ItemStack(BlocksRegister.BLOCK_PALADIUM, count);
          PPalaDynamique.instance.addGenerated("LEGENDARY_STONE", count);
        } 
        entity = entityPlayer.func_71019_a(stack, false);
        if (entity != null)
          entity.field_145804_b = 0; 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.FORTUNE.red, Effect.FORTUNE.green, Effect.FORTUNE.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une fortune est tombée sur toi :o"));
        break;
      case 4:
        UUID_PROTECT_POWER.put(FastUUID.toString((Entity)entityPlayer), Long.valueOf(System.currentTimeMillis()));
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.POWER.red, Effect.POWER.green, Effect.POWER.blue, Effect.POWER), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as reçu un grand pouvoir. Tu es immunisé(e) à la sulfuric water pendant 5 minutes !"));
        break;
      case 5:
        jobs = (int)(Math.random() * 4.0D);
        xp = (int)(Math.random() * 15001.0D + 5000.0D);
        if ((JobType.values()).length >= jobs) {
          JobType aJob = JobType.values()[jobs];
          JobsPlayer jobsPlayer = JobsPlayer.get((Entity)entityPlayer);
          jobsPlayer.addXp(aJob, ObjectiveType.FISH, xp, entityPlayer);
        } else {
          entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Une erreur s'est produite, contacte un administrateur."));
        } 
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.JOBS.red, Effect.JOBS.green, Effect.JOBS.blue), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu as beaucoup travaillé. Tu as gagné beaucoup d'xp, Bravo !"));
        break;
      case 6:
        t = new Thread(() -> {
              Random rand = world.field_73012_v;
              for (Object Oplayer : entityPlayer.field_70170_p.field_73010_i) {
                EntityPlayer player = (EntityPlayer)Oplayer;
                if (!player.func_110124_au().equals(entityPlayer.func_110124_au())) {
                  Float d = Float.valueOf(player.func_70032_d((Entity)entityPlayer));
                  if (d.floatValue() <= 10.0F)
                    for (int i = 1; i <= 20; i++) {
                      int slotA = rand.nextInt(36);
                      int slotB = rand.nextInt(36);
                      ItemStack itemA = player.field_71071_by.func_70301_a(slotA);
                      ItemStack itemB = player.field_71071_by.func_70301_a(slotB);
                      player.field_71071_by.func_70299_a(slotA, itemB);
                      player.field_71071_by.func_70299_a(slotB, itemA);
                    }  
                } 
              } 
            });
        t.start();
        PalaMod.getNetwork().sendTo((IMessage)new PacketLegendaryEffect(Effect.CHAOS.red, Effect.CHAOS.green, Effect.CHAOS.blue, Effect.CHAOS), (EntityPlayerMP)entityPlayer);
        entityPlayer.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §7Tu viens de lancer le chaos autour de toi !"));
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\LegendaryStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */