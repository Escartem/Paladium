package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.paladium.network.PacketStickBaseCooldown;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Potions;
import fr.paladium.palapass.common.quest.misc.ItemUseQuest;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemStickBase extends BaseItem implements ITooltipWiki {
  public static final int HEAL = 0;
  
  public static final int SPEED = 1;
  
  public static final int STRENGHT = 2;
  
  public static final int JUMP = 3;
  
  public static final int GOD = 4;
  
  public static final int DAMAGE = 5;
  
  public static final int HYPERJUMP = 6;
  
  public static final int TELEPORT = 7;
  
  public boolean earthquake = false;
  
  public int count = 8;
  
  public HashMap<Integer, Integer> limiteType = new HashMap<>();
  
  public int cooldown;
  
  int type;
  
  double[] color;
  
  public ItemStickBase(int durability, String name, String texture, int cooldown, int type, double[] color) {
    super(texture);
    func_77625_d(1);
    func_77656_e(durability);
    func_77655_b(name);
    this.type = type;
    this.cooldown = cooldown;
    this.color = color;
    this.limiteType.put(Integer.valueOf(0), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(1), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(2), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(3), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(4), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(5), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(6), Integer.valueOf(5));
    this.limiteType.put(Integer.valueOf(7), Integer.valueOf(5));
  }
  
  public static ItemStack getItem(int type) {
    if (type == 0)
      return new ItemStack((Item)ItemsRegister.STICK_HEAL); 
    if (type == 1)
      return new ItemStack((Item)ItemsRegister.STICK_SPEED); 
    if (type == 2)
      return new ItemStack((Item)ItemsRegister.STICK_STRENGHT); 
    if (type == 3)
      return new ItemStack((Item)ItemsRegister.STICK_JUMP); 
    if (type == 4)
      return new ItemStack((Item)ItemsRegister.STICK_GOD); 
    if (type == 5)
      return new ItemStack((Item)ItemsRegister.STICK_DAMAGE); 
    if (type == 6)
      return new ItemStack((Item)ItemsRegister.STICK_HYPERJUMP); 
    if (type == 7)
      return new ItemStack((Item)ItemsRegister.STICK_TELEPORT); 
    return null;
  }
  
  public boolean func_77662_d() {
    return true;
  }
  
  public void func_77663_a(ItemStack item, World world, Entity player, int slotIndex, boolean inHand) {
    super.func_77663_a(item, world, player, slotIndex, inHand);
    if (player instanceof EntityPlayer && 
      this.type == 5 && 
      this.earthquake)
      if (this.count < 16) {
        earthquake((EntityPlayer)player);
        this.count++;
      } else {
        this.earthquake = false;
        this.count = 8;
      }  
  }
  
  public int getType() {
    return this.type;
  }
  
  public boolean hasEffect(ItemStack item, int pass) {
    if (!Constants.Environment.SERVER.equals(Constants.MOD_ENV))
      try {
        return 
          
          (Duration.between(TimeUtil.nowZoned(), TimeUtil.fromLong((Minecraft.func_71410_x()).field_71439_g.getEntityData().func_74763_f("timer-" + getType()))).getSeconds() <= 0L);
      } catch (Exception exception) {} 
    return true;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (WorldGuardUtils.isItemEffectBlocked(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, Item.func_150891_b(item.func_77973_b())))
      return item; 
    if (player.getEntityData() != null && !player.getEntityData().func_74764_b("timer-" + getType()))
      player.getEntityData().func_74772_a("timer-" + getType(), TimeUtil.now()); 
    if (!world.field_72995_K)
      if (Duration.between(TimeUtil.nowZoned(), 
          TimeUtil.fromLong(player.getEntityData().func_74763_f("timer-" + getType())))
        .getSeconds() <= 0L) {
        item.func_77972_a(1, (EntityLivingBase)player);
        ItemUseQuest.trigger(player, item, 1);
        if (PFactions.instance != null && PFactions.instance.getImpl() != null)
          PFactions.instance.getImpl().onItemUse(player, item, 1); 
        getTypeEffect(player);
        long dateLong = TimeUtil.nowZoned().plusSeconds(this.cooldown).toEpochSecond();
        player.getEntityData().func_74772_a("timer-" + getType(), dateLong);
        PalaMod.getNetwork().sendTo((IMessage)new PacketStickBaseCooldown(getType(), this.cooldown, this.color), (EntityPlayerMP)player);
      } else {
        Duration duration = Duration.between(TimeUtil.nowZoned(), 
            TimeUtil.fromLong(player.getEntityData().func_74763_f("timer-" + getType())));
        if (duration.getSeconds() > this.cooldown) {
          item.func_77972_a(1, (EntityLivingBase)player);
          ItemUseQuest.trigger(player, item, 1);
          if (PFactions.instance != null && PFactions.instance.getImpl() != null)
            PFactions.instance.getImpl().onItemUse(player, item, 1); 
          getTypeEffect(player);
          long dateLong = TimeUtil.nowZoned().plusSeconds(this.cooldown).toEpochSecond();
          player.getEntityData().func_74772_a("timer-" + getType(), dateLong);
          PalaMod.getNetwork().sendTo((IMessage)new PacketStickBaseCooldown(getType(), this.cooldown, this.color), (EntityPlayerMP)player);
          for (int i = 0; i < 200; i++)
            world.func_72869_a("reddust", player.field_70165_t + world.field_73012_v.nextDouble() * 2.0D - 1.0D, player.field_70163_u + world.field_73012_v
                .nextDouble() * 2.0D - 2.0D, player.field_70161_v + world.field_73012_v
                .nextDouble() * 2.0D - 1.0D, this.color[0], this.color[1], this.color[2]); 
        } else {
          player.func_146105_b((IChatComponent)new ChatComponentTranslation(EnumChatFormatting.RED + "Tu dois attendre que le baton se recharge " + duration
                
                .getSeconds() + " secondes !", new Object[0]));
        } 
      }  
    return super.func_77659_a(item, world, player);
  }
  
  public void getTypeEffect(EntityPlayer player) {
    AxisAlignedBB scanAbove;
    List<?> players;
    World world;
    switch (this.type) {
      case 0:
        player.func_70691_i(6.0F);
        return;
      case 1:
        player.func_82170_o(Potion.field_76424_c.field_76415_H);
        player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 70, 6));
        return;
      case 2:
        player.func_82170_o(Potion.field_76420_g.field_76415_H);
        player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 70, 2));
        return;
      case 3:
        if (player.func_70644_a((Potion)PRegister_Potions.potionGravity)) {
          PotionEffect pEffect = player.func_70660_b((Potion)PRegister_Potions.potionGravity);
          int ampli = pEffect.func_76458_c();
          float reduced = (float)((ampli == 1) ? 0.25D : ((ampli == 2) ? 0.35D : 0.5D));
          player.field_70181_x += 1.0D * reduced;
        } else {
          player.field_70181_x++;
        } 
        ((EntityPlayerMP)player).field_71135_a
          .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
        return;
      case 4:
        player.func_82170_o(Potion.field_76428_l.field_76415_H);
        player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 80, 5));
        player.func_110149_m(6.0F);
        player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 30, 1));
        player.func_82170_o(Potion.field_76424_c.field_76415_H);
        player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 80, 8));
        player.func_82170_o(Potion.field_76420_g.field_76415_H);
        player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 100, 2));
        return;
      case 5:
        scanAbove = AxisAlignedBB.func_72330_a(player.field_70165_t - 3.0D, player.field_70163_u - 3.0D, player.field_70161_v - 3.0D, player.field_70165_t + 3.0D, player.field_70163_u + 3.0D, player.field_70161_v + 3.0D);
        players = player.func_130014_f_().func_72872_a(EntityLivingBase.class, scanAbove);
        for (Object obj : players) {
          if (obj instanceof EntityLivingBase) {
            EntityLivingBase pl = (EntityLivingBase)obj;
            if (pl.func_110124_au() == player.func_110124_au())
              continue; 
            LivingHurtEvent event = new LivingHurtEvent(pl, DamageSource.func_76365_a(player), 0.0F);
            MinecraftForge.EVENT_BUS.post((Event)event);
            if (event.isCanceled())
              continue; 
            pl.field_70160_al = true;
            float str = 0.70000005F;
            pl.field_70159_w += (pl.field_70165_t - player.field_70165_t > 0.0D) ? 0.7000000476837158D : -0.7000000476837158D;
            pl.field_70181_x += 0.7000000476837158D;
            pl.field_70179_y += (pl.field_70161_v - player.field_70161_v > 0.0D) ? 0.7000000476837158D : -0.7000000476837158D;
            if (obj instanceof EntityPlayerMP && 
              pl.func_110124_au() != player.func_110124_au()) {
              EntityPlayerMP p = (EntityPlayerMP)pl;
              p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)pl));
            } 
          } 
        } 
        this.earthquake = true;
        return;
      case 6:
        if (player.func_70644_a((Potion)PRegister_Potions.potionGravity)) {
          PotionEffect pEffect = player.func_70660_b((Potion)PRegister_Potions.potionGravity);
          int ampli = pEffect.func_76458_c();
          float reduced = (float)((ampli == 1) ? 0.25D : ((ampli == 2) ? 0.35D : 0.5D));
          player.field_70181_x += 2.365D * reduced;
        } else {
          player.field_70181_x += 2.365D;
        } 
        ((EntityPlayerMP)player).field_71135_a
          .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
        return;
      case 7:
        world = player.func_130014_f_();
        if (!world.field_72995_K)
          world.func_72838_d((Entity)new EntityEnderPearl(world, (EntityLivingBase)player)); 
        break;
    } 
  }
  
  public void earthquake(EntityPlayer player) {
    for (float i = 0.5F; i < 3.0F; i++) {
      double x = (this.count - 6) * Math.cos(Math.toRadians((i * 6.0F + player.field_70177_z + 90.0F))) + player.field_70165_t;
      double z = (this.count - 6) * Math.sin(Math.toRadians((i * 6.0F + player.field_70177_z + 90.0F))) + player.field_70161_v;
      BlockPos pos = new BlockPos(x, player.field_70163_u - 1.0D, z);
      EntityCustomFallingBlock falling = new EntityCustomFallingBlock(player.func_130014_f_(), (Entity)player, x, player.field_70163_u - 1.0D, z, 0.20000000298023224D, i * 6.0F + player.field_70177_z + 90.0F, pos, 2);
      if (!(player.func_130014_f_()).field_72995_K)
        player.func_130014_f_().func_72838_d((Entity)falling); 
    } 
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    int repair = 0;
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("repair"))
      repair = item.func_77978_p().func_74762_e("repair"); 
    list.add("§7Réparation: " + repair + " / 5");
    super.func_77624_a(item, p_77624_2_, list, p_77624_4_);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#1.-les-sticks";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemStickBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */