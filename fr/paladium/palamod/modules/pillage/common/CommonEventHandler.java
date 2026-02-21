package fr.paladium.palamod.modules.pillage.common;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.ObsiEffect;
import fr.paladium.palamod.modules.pillage.common.entities.EntitySpongeSheep;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palawither.common.utils.WitherUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class CommonEventHandler {
  AxisAlignedBB checkingRange = AxisAlignedBB.func_72330_a(1.5D, 1.5D, 1.5D, 1.5D, 1.5D, 1.5D);
  
  @SubscribeEvent
  public void onInteract(PlayerInteractEvent e) {
    if (e.world.field_72995_K || e.action != PlayerInteractEvent.Action.RIGHT_CLICK_AIR || !e.entityPlayer.func_70093_af())
      return; 
    ItemStack item = e.entityPlayer.func_70694_bm();
    if (item == null || item.func_77973_b() != Item.func_150898_a(BlocksRegister.OBSI_SLIME))
      return; 
    NBTTagCompound tag = new NBTTagCompound();
    if (item.func_77942_o())
      tag = item.func_77978_p(); 
    int meta = 0;
    if (tag.func_74764_b("obsiMeta"))
      meta = tag.func_74762_e("obsiMeta"); 
    meta++;
    if (meta > 5)
      meta = 0; 
    tag.func_74768_a("obsiMeta", meta);
    item.func_77982_d(tag);
    String metaName = item.func_77973_b().func_77653_i(item);
    if (meta == 0) {
      metaName = metaName + " [SOUTH]";
    } else if (meta == 1) {
      metaName = metaName + " [WEST]";
    } else if (meta == 2) {
      metaName = metaName + " [NORTH]";
    } else if (meta == 3) {
      metaName = metaName + " [EAST]";
    } else if (meta == 4) {
      metaName = metaName + " [DOWN]";
    } else if (meta == 5) {
      metaName = metaName + " [UP]";
    } 
    item.func_151001_c(metaName);
    e.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.entityLiving.field_70153_n != null && 
      event.entityLiving.field_70153_n instanceof EntityPlayer && event.entityLiving instanceof EntityWither) {
      EntityWither wither = (EntityWither)event.entity;
      EntityPlayer player = (EntityPlayer)event.entityLiving.field_70153_n;
      if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b().equals(PPRegisterer.PPItems.WITHER_FISHINGROD) && JobsBridge.canUseCraft(player, new ItemStack(PPRegisterer.PPItems.WITHER_FISHINGROD), false)) {
        wither.field_70177_z = player.field_70177_z;
        wither.field_70125_A = player.field_70125_A;
        wither.func_70784_b(null);
        wither.func_70624_b(null);
        wither.func_82211_c(0, 0);
        wither.field_70759_as = player.field_70759_as;
        wither.func_70612_e(0.0F, 5.0F);
        if (wither.field_70159_w > 1.5D)
          wither.field_70159_w = 1.5D; 
        if (wither.field_70179_y > 1.5D)
          wither.field_70179_y = 1.5D; 
        if (player.field_70125_A < 0.0F && wither.field_70163_u < 256.0D) {
          wither.field_70181_x = (0.0055555557F * Math.abs(player.field_70125_A));
          if (wither.field_70181_x > 0.5D)
            wither.field_70181_x = 0.5D; 
        } else if (player.field_70125_A < 0.0F) {
          wither.field_70181_x = (-0.0055555557F * Math.abs(player.field_70125_A));
          if (wither.field_70181_x < -0.5D)
            wither.field_70181_x = -0.5D; 
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onBlockBreak(BlockEvent.BreakEvent event) {
    if (event.block instanceof ObsiEffect && !(event.getPlayer()).field_71075_bZ.field_75098_d) {
      ObsiEffect obsiEffect = (ObsiEffect)event.block;
      obsiEffect
        
        .shouldApplyEffect = ((event.getPlayer().func_70694_bm() == null || !event.getPlayer().func_70694_bm().func_77973_b().equals(PPRegisterer.PPItems.OBSIDIAN_PICKAXE)) && !EnchantmentHelper.func_77502_d((EntityLivingBase)event.getPlayer()));
    } 
  }
  
  @SubscribeEvent
  public void onRightClick(EntityInteractEvent event) {
    if (!event.entityPlayer.field_70170_p.field_72995_K) {
      ItemStack heldItemStack = event.entityPlayer.func_70694_bm();
      if (heldItemStack != null) {
        if (event.target instanceof EntitySheep) {
          EntitySheep sheep = (EntitySheep)event.target;
          if (!sheep.func_70892_o() && !sheep.func_70631_g_() && 
            heldItemStack.func_77973_b().equals(Items.field_151115_aP)) {
            heldItemStack.field_77994_a--;
            EntitySpongeSheep spongeSheep = new EntitySpongeSheep(event.entityPlayer.field_70170_p);
            spongeSheep.func_70080_a(sheep.field_70165_t, sheep.field_70163_u, sheep.field_70161_v, sheep.field_70177_z, sheep.field_70125_A);
            sheep.func_70107_b(0.0D, -5.0D, 0.0D);
            sheep.func_70106_y();
            event.entityPlayer.field_70170_p.func_72838_d((Entity)spongeSheep);
          } 
        } 
        if (event.target instanceof EntityPlayer && 
          heldItemStack.func_77973_b().equals(PPRegisterer.PPItems.GLUE)) {
          ((EntityPlayer)event.target)
            .func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 20, 1, true));
          heldItemStack.field_77994_a--;
        } 
      } 
      if (event.target instanceof EntityMob) {
        if (heldItemStack != null && 
          heldItemStack.func_77973_b().equals(PPRegisterer.PPItems.AGRO_STONE)) {
          ((EntityMob)event.target).func_70784_b((Entity)event.entityPlayer);
          ((EntityMob)event.target).func_70624_b((EntityLivingBase)event.entityPlayer);
          ((EntityMob)event.target).func_70604_c((EntityLivingBase)event.entityPlayer);
          heldItemStack.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
          UseItemAchievement.performCheck(event.entityPlayer, heldItemStack, "AGRO_STONE");
        } 
        if ((event.target instanceof EntityWither || event.target instanceof fr.paladium.palawither.common.entity.EntityBabyWither) && heldItemStack != null && 
          heldItemStack.func_77973_b() != null && 
          heldItemStack.func_77973_b().equals(PPRegisterer.PPItems.WITHER_SADDLE) && JobsBridge.canUseCraft(event.entityPlayer, new ItemStack(PPRegisterer.PPItems.WITHER_SADDLE), false)) {
          heldItemStack.field_77994_a--;
          event.entityPlayer.func_70078_a(event.target);
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    if (event.source.func_76364_f() instanceof net.minecraft.entity.projectile.EntityArrow && event.source.func_76364_f().getEntityData().func_74764_b("MidLife") && 
      WitherUtils.isWither(event.entity) && !(event.entity instanceof fr.paladium.palawither.common.entity.targetable.EntityPredatorWither)) {
      EntityMob wither = (EntityMob)event.entity;
      wither.func_70606_j(wither.func_110138_aP() / 2.0F);
    } 
  }
  
  @SubscribeEvent
  public void onLivingDead(LivingDeathEvent event) {
    if (event.entity instanceof net.minecraft.entity.monster.EntitySkeleton && event.source.func_76364_f() instanceof net.minecraft.entity.monster.EntitySkeleton) {
      EntityItem item = new EntityItem(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, new ItemStack(Items.field_151144_bL, 1));
      event.entity.field_70170_p.func_72838_d((Entity)item);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\CommonEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */