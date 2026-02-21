package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palawither.common.utils.WitherUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBionicSnowball extends EntitySnowball {
  public EntityBionicSnowball(World world) {
    super(world);
  }
  
  public EntityBionicSnowball(World world, EntityLivingBase entity) {
    super(world, entity);
  }
  
  public EntityBionicSnowball(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  protected void func_70184_a(MovingObjectPosition hit) {
    if (hit.field_72308_g != null) {
      if (hit.field_72308_g instanceof EntityLivingBase) {
        boolean isProtected = false;
        EntityLivingBase entity = (EntityLivingBase)hit.field_72308_g;
        if (entity instanceof EntityPlayer) {
          EntityPlayer victim = (EntityPlayer)entity;
          for (ItemStack item : victim.field_71071_by.field_70460_b) {
            if (item != null && item.func_77973_b() instanceof ItemArmor)
              if ((item.func_77973_b() instanceof IRepairable && ((IRepairable)item.func_77973_b()).isEndium()) || ((ItemArmor)item.func_77973_b()).func_82812_d() == PArmorMaterial.ancient) {
                isProtected = true;
                break;
              }  
          } 
          if (victim.func_71045_bC() != null && !isProtected) {
            Item equippedItem = victim.func_71045_bC().func_77973_b();
            if (equippedItem instanceof IRepairable && ((IRepairable)equippedItem).isEndium())
              isProtected = true; 
          } 
        } 
        if (entity instanceof fr.paladium.palajobs.core.entity.boss.AEntityJobBoss || entity instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase || WitherUtils.isWither((Entity)entity))
          isProtected = true; 
        if (isProtected) {
          if (func_85052_h() instanceof EntityPlayer) {
            ((EntityPlayer)func_85052_h()).field_71071_by.func_70441_a(new ItemStack(ItemsRegister.BIONIC_SNOWBALL));
            ((EntityPlayer)func_85052_h()).func_145747_a((IChatComponent)new ChatComponentText("§cIl n’est pas possible de lancer une bionic snowball sur un boss ou un joueur ayant une épée ou du stuff en paldium vert / endium"));
          } 
          func_70106_y();
          return;
        } 
        entity.func_70606_j(1.0F);
      } 
    } else if (func_85052_h() != null && 
      func_85052_h() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)func_85052_h();
      player.field_71071_by.func_70441_a(new ItemStack(ItemsRegister.MOON_PIECE));
      func_70106_y();
    } 
    for (int i = 0; i < 8; i++)
      this.field_70170_p.func_72869_a("snowballpoof", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D); 
    if (!this.field_70170_p.field_72995_K)
      func_70106_y(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityBionicSnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */