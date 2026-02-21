package fr.paladium.palamod.modules.smeltery.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.core.pojo.objectives.types.SmeltObjective;
import fr.paladium.palajobs.core.quest.types.FurnaceCraftQuest;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.achievements.types.HammerDuplicateAchievement;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.tutorial.common.event.HammerSmeltEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class ToolHandler {
  protected static ThreadLocal<Boolean> captureDrops = new ThreadLocal<Boolean>() {
      protected Boolean initialValue() {
        return Boolean.valueOf(false);
      }
    };
  
  protected static ThreadLocal<List<ItemStack>> capturedDrops = new ThreadLocal<List<ItemStack>>() {
      protected List<ItemStack> initialValue() {
        return new ArrayList<>();
      }
    };
  
  public static Block[] minerals = new Block[] { 
      PWorld.FINDIUM_ORE, Blocks.field_150365_q, Blocks.field_150450_ax, Blocks.field_150482_ag, Blocks.field_150412_bA, Blocks.field_150449_bY, Blocks.field_150366_p, Blocks.field_150369_x, PWorld.PALADIUM_ORE, PWorld.TITANE_ORE, 
      PWorld.AMETHYST_ORE, Blocks.field_150352_o, Blocks.field_150439_ay, PWorld.TRIXIUM_ORE };
  
  public static void removeBlocksInIteration(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean smelt, int fortune, boolean dispose, boolean obsidian) {
    float blockHardness = (block == null) ? 1.0F : block.func_149712_f(world, x, y, z);
    for (int x1 = xs; x1 < xe; x1++) {
      for (int y1 = ys; y1 < ye; y1++) {
        for (int z1 = zs; z1 < ze; z1++) {
          if ((world.func_147439_a(x1 + x, y1 + y, z1 + z).isToolEffective("pickaxe", 3) || world.func_147439_a(x1 + x, y1 + y, z1 + z) instanceof net.minecraft.block.BlockRedstoneOre || (obsidian && world.func_147439_a(x1 + x, y1 + y, z1 + z) instanceof net.minecraft.block.BlockObsidian)) && EventUtils.canInteract(player, x1 + x, y1 + y, z1 + z)) {
            boolean root = (x1 == 0 && y1 == 0 && z1 == 0);
            removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, world.func_147439_a(x1 + x, y1 + y, z1 + z), materialsListing, smelt, fortune, blockHardness, dispose, obsidian, root);
          } 
        } 
      } 
    } 
  }
  
  public static void removeBlocksInIterationWithShovel(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean smelt, int fortune, boolean dispose, boolean obsidian) {
    float blockHardness = (block == null) ? 1.0F : block.func_149712_f(world, x, y, z);
    for (int x1 = xs; x1 < xe; x1++) {
      for (int y1 = ys; y1 < ye; y1++) {
        for (int z1 = zs; z1 < ze; z1++) {
          if (EventUtils.canInteract(player, x1 + x, y1 + y, z1 + z)) {
            boolean root = (x1 == 0 && y1 == 0 && z1 == 0);
            removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, world.func_147439_a(x1 + x, y1 + y, z1 + z), materialsListing, smelt, fortune, blockHardness, dispose, obsidian, root);
          } 
        } 
      } 
    } 
  }
  
  public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean smelt, int fortune, float blockHardness, boolean dispose, boolean obsidian, boolean root) {
    removeBlockWithDrops(player, stack, world, x, y, z, bx, by, bz, block, materialsListing, smelt, fortune, blockHardness, dispose, true, obsidian, root);
  }
  
  public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean smelt, int fortune, float blockHardness, boolean dispose, boolean particles, boolean obsidian, boolean root) {
    if (!world.func_72899_e(x, y, z))
      return; 
    Block blk = world.func_147439_a(x, y, z);
    int meta = world.func_72805_g(x, y, z);
    if ((block != null && blk != block) || blockHardness < blk.func_149712_f(world, x, y, z) || blk.func_149712_f(world, x, y, z) < 0.0F)
      return; 
    boolean flag = false;
    if (!world.field_72995_K && blk != null && !blk.isAir((IBlockAccess)world, x, y, z) && blk.func_149737_a(player, world, x, y, z) > 0.0F) {
      if (!blk.canHarvestBlock(player, meta) || !isRightMaterial(blk, materialsListing) || (blk == Blocks.field_150343_Z && !obsidian) || (blk == BlocksRegister.HARDENED_OBSIDIAN && !obsidian))
        return; 
      if (!player.field_71075_bZ.field_75098_d) {
        int localMeta = world.func_72805_g(x, y, z);
        blk.func_149681_a(world, x, y, z, localMeta, player);
        if (blk.removedByPlayer(world, player, x, y, z, true)) {
          if (!root && MinecraftForge.EVENT_BUS.post((Event)new BlockEvent.BreakEvent(x, y, z, world, blk, meta, player)))
            return; 
          if (smelt && fortune > 0 && 
            stack.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.tools.ItemPaladiumHammer)
            HammerDuplicateAchievement.performCheck(player, blk); 
          blk.func_149664_b(world, x, y, z, localMeta);
        } 
        if (smelt) {
          ItemStack result = null;
          if (blk.getDrops(world, x, y, z, blk.func_149643_k(world, x, y, z), fortune) != null && !blk.getDrops(world, x, y, z, blk.func_149643_k(world, x, y, z), fortune).isEmpty())
            result = FurnaceRecipes.func_77602_a().func_151395_a(blk.getDrops(world, x, y, z, blk.func_149643_k(world, x, y, z), fortune).get(0)); 
          if (result == null) {
            blk.func_149697_b(world, x, y, z, localMeta, fortune);
          } else {
            flag = true;
            ItemStack resultCopy = result.func_77946_l();
            float count = 1.0F;
            if (fortune > 0 && isMineral(block))
              count = (1 + (fortune - world.field_73012_v.nextInt(fortune)) / 2); 
            if (count <= 0.0F)
              count = 1.0F; 
            resultCopy.field_77994_a = (int)count;
            FMLCommonHandler.instance().firePlayerSmeltedEvent(player, resultCopy);
            EntityItem entityItem = new EntityItem(world, x, y, z, resultCopy);
            world.func_72838_d((Entity)entityItem);
            FurnaceCraftQuest.performCheck(player, resultCopy.func_77946_l(), (int)count);
            SmeltObjective.performCheck(player, resultCopy.func_77946_l());
            MinecraftForge.EVENT_BUS.post((Event)new HammerSmeltEvent(player, resultCopy.func_77946_l()));
          } 
        } else {
          blk.func_149697_b(world, x, y, z, localMeta, fortune);
        } 
      } else {
        int localMeta = world.func_72805_g(x, y, z);
        blk.func_149697_b(world, x, y, z, localMeta, fortune);
      } 
      int xpDrop = blk.getExpDrop((IBlockAccess)world, blk.func_149643_k(world, x, y, z), fortune);
      EntityXPOrb xp = new EntityXPOrb(world, x, y, z, xpDrop);
      if (xpDrop > 0)
        world.func_72838_d((Entity)xp); 
    } else {
      world.func_147468_f(x, y, z);
    } 
    world.func_72926_e(2001, x, y, z, Block.func_149682_b(blk) + (meta << 12));
    world.func_147468_f(x, y, z);
    if (flag) {
      world.func_72869_a("flame", x + 0.5D + 0.5D * world.field_73012_v.nextDouble(), y - 0.1D, z + 0.5D * world.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", x + 0.5D + 0.5D * world.field_73012_v.nextDouble(), y - 0.1D, z + 0.5D * world.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
      world.func_72869_a("smoke", x + 0.5D + 0.5D * world.field_73012_v.nextDouble(), y - 0.1D, z + 0.5D * world.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
    } 
  }
  
  public static boolean isMineral(Block block) {
    for (Block b : minerals) {
      if (b == block)
        return true; 
    } 
    return false;
  }
  
  public static boolean isRightMaterial(Block block, Material[] materialsListing) {
    Material material = block.func_149688_o();
    for (Material mat : materialsListing) {
      if (material == mat)
        return true; 
    } 
    return false;
  }
  
  public static boolean isRightBlock(Block block, Block[] list) {
    for (Block blk : list) {
      if (block == blk)
        return false; 
    } 
    return true;
  }
  
  public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
    float f = 1.0F;
    float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * 1.0F;
    float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * 1.0F;
    double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * 1.0D;
    double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * 1.0D;
    if (!world.field_72995_K && player instanceof EntityPlayer)
      d1 += ((EntityPlayer)player).eyeHeight; 
    double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * 1.0D;
    Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
    float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
    float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
    float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
    float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
    float f7 = f4 * f5;
    float f8 = f3 * f5;
    double d3 = range;
    Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
    return world.func_72901_a(vec3, vec31, par3);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\utils\ToolHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */