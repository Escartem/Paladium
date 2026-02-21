package fr.paladium.palamod.modules.paladium.common.blocks.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockSulfuricWater extends BlockFluidClassic implements IAsgardBlockFluidBase {
  @SideOnly(Side.CLIENT)
  private IIcon stillIcon;
  
  @SideOnly(Side.CLIENT)
  private IIcon flowingIcon;
  
  public BlockSulfuricWater(Fluid fluid, Material material) {
    super(fluid, material);
    func_149711_c(100.0F);
    func_149663_c("sulfuricwater");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return (side == 0 || side == 1) ? this.stillIcon : this.flowingIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.stillIcon = register.func_94245_a("palamod:fluids/sulfuricwater_still");
    this.flowingIcon = register.func_94245_a("palamod:fluids/sulfuricwater_flowing");
  }
  
  public int canDisplacePre(Block block, IBlockAccess world, int x, int y, int z) {
    if (block.func_149688_o().func_76224_d())
      return -1; 
    return 0;
  }
  
  public int displaceIfPossiblePre(Block block, World world, int x, int y, int z) {
    if (block.func_149688_o().func_76224_d())
      return -1; 
    return 0;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    super.func_149670_a(world, x, y, z, entity);
    if (entity instanceof EntityLivingBase) {
      boolean reducedPowerHelmet = false;
      if (entity instanceof EntityPlayer) {
        EntityPlayer entityPlayer = (EntityPlayer)entity;
        String playerUuid = FastUUID.toString((Entity)entityPlayer);
        if (LegendaryStone.UUID_PROTECT_POWER.containsKey(playerUuid)) {
          if (((Long)LegendaryStone.UUID_PROTECT_POWER.get(playerUuid)).longValue() + 300000L >= System.currentTimeMillis())
            return; 
          LegendaryStone.UUID_PROTECT_POWER.remove(playerUuid);
        } 
        ItemStack helmet = entityPlayer.field_71071_by.field_70460_b[3];
        if (helmet != null && helmet.func_77973_b() != null && EnchantmentHelper.func_77506_a(EnchantMod.fakeWaterProtection.field_77352_x, helmet) > 0 && helmet.func_77958_k() > 0) {
          if (!entityPlayer.field_70170_p.field_72995_K) {
            long lastFakeWaterProtection = entityPlayer.getEntityData().func_74764_b("lastFakeWaterProtection") ? entityPlayer.getEntityData().func_74763_f("lastFakeWaterProtection") : 0L;
            if (lastFakeWaterProtection + 1000L < System.currentTimeMillis()) {
              helmet.func_77972_a(66, (EntityLivingBase)entityPlayer);
              if (helmet.field_77994_a <= 0)
                entityPlayer.field_71071_by.field_70460_b[3] = null; 
              entityPlayer.getEntityData().func_74772_a("lastFakeWaterProtection", System.currentTimeMillis());
            } 
          } 
          return;
        } 
        if (entityPlayer.field_71071_by != null && helmet != null) {
          reducedPowerHelmet = (helmet.func_77973_b() == ItemsRegister.POWER_HELMET);
          if (reducedPowerHelmet)
            reducedPowerHelmet = (helmet.func_77958_k() - helmet.func_77960_j() > 1); 
        } 
        if (reducedPowerHelmet && !world.field_72995_K && entityPlayer.field_70172_ad <= entityPlayer.field_70771_an / 2.0F)
          helmet.func_77972_a(1, (EntityLivingBase)entityPlayer); 
      } 
      EntityLivingBase livingBase = (EntityLivingBase)entity;
      livingBase.func_70097_a((new DamageSource("sulfuric_water")).func_76348_h(), reducedPowerHelmet ? 1.0F : 2.0F);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\fluids\BlockSulfuricWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */