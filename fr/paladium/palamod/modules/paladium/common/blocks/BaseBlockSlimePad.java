package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.blocks.BaseBlock;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Potions;
import fr.paladium.palamod.modules.paladium.registerer.PRegisterer;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BaseBlockSlimePad extends BaseBlock implements ITooltipWiki {
  public BaseBlockSlimePad(Material material, float hardness, String texture) {
    super(material, hardness, texture);
    func_149676_a(0.125F, 0.0625F, 0.125F, 0.875F, 0.625F, 0.875F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 2);
    func_149672_a(PRegisterer.SLIME_STEP);
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (entity instanceof EntityPlayer && ((EntityPlayer)entity).func_70644_a((Potion)PRegister_Potions.potionFrost))
      return; 
    if (entity.field_70170_p.field_72995_K && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && entity instanceof net.minecraft.client.particle.EntityFX)
      return; 
    if (entity.field_70145_X || entity.field_70128_L || entity instanceof fr.paladium.palamod.modules.back2future.entities.EntityEndermite || entity instanceof fr.paladium.palawither.common.entity.targetable.IWitherCoordinateTargetable)
      return; 
    Optional<IWither> optionalWither = WitherUtils.getWither(entity);
    if (optionalWither.isPresent() && ((IWither)optionalWither.get()).hasUpgrade(ItemsRegister.SLIMY_WITHER_UPGRADE))
      return; 
    if (!world.func_72864_z(x, y, z)) {
      double moveX = 0.0D;
      double moveZ = 0.0D;
      double speed = 0.2D;
      int meta = world.func_72805_g(x, y, z) % 8;
      switch (meta % 8) {
        case 6:
          moveX += 0.2D;
          break;
        case 7:
          moveX += 0.2D;
          moveZ += 0.2D;
          break;
        case 0:
          moveZ += 0.2D;
          break;
        case 1:
          moveZ += 0.2D;
          moveX -= 0.2D;
          break;
        case 2:
          moveX -= 0.2D;
          break;
        case 3:
          moveX -= 0.2D;
          moveZ -= 0.2D;
          break;
        case 4:
          moveZ -= 0.2D;
          break;
        case 5:
          moveZ -= 0.2D;
          moveX += 0.2D;
          break;
      } 
      if (entity instanceof net.minecraft.entity.item.EntityItem)
        entity.field_70163_u += 0.5D; 
      entity.field_70143_R = 0.0F;
      entity.func_70024_g(moveX, 0.2D, moveZ);
      world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), this.field_149762_H.func_150498_e(), this.field_149762_H.func_150497_c() / 2.0F, this.field_149762_H.func_150494_d() * 0.65F);
    } 
  }
  
  public boolean isBlockReplaceable(World world, int x, int y, int z) {
    return false;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    int face = MathHelper.func_76128_c((entity.field_70177_z * 8.0F / 360.0F) + 0.5D) + (entity.func_70093_af() ? 4 : 0) & 0x7;
    int meta = world.func_72805_g(x, y, z) & 0x8;
    world.func_72921_c(x, y, z, face | meta, 2);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public int func_149701_w() {
    return 1;
  }
  
  public static long CD = 0L;
  
  public boolean func_149742_c(World world, int x, int y, int z) {
    if (world.field_72995_K && 
      world.func_147439_a(x, 0, z).equals(BlocksRegister.COLLIDE_INVISIBLE_BLOCK)) {
      if (CD + 10000L > System.currentTimeMillis()) {
        (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§cTu dois attendre que le cooldown du slimepad soit passé..."));
        (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§cIl reste " + (CD + 10000L - System.currentTimeMillis()) + " ms."));
        return false;
      } 
      CD = System.currentTimeMillis();
    } 
    return super.func_149742_c(world, x, y, z);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return (metadata >= 8) ? new ArrayList<>() : super.getDrops(world, x, y, z, metadata, fortune);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#4.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BaseBlockSlimePad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */