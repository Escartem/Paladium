package fr.paladium.palamod.modules.miner.dimminer.client.render;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.zephyrui.lib.color.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class BlockEndiumCaveRenderer extends TileEntitySpecialRenderer {
  private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new SynchronousQueue<>());
  
  public static List<Consumer<Float>> chestUSPCache;
  
  public static long lastChestUSPUpdate;
  
  private RenderBlocks blockRenderer;
  
  private Color color;
  
  public void func_147496_a(World world) {
    this.blockRenderer = new RenderBlocks((IBlockAccess)world);
    this.color = Color.decode("#2537FB");
  }
  
  public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float ticks) {
    if (MinecraftForgeClient.getRenderPass() == 1 || !(tileEntity instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityEndiumCaveBlock))
      return; 
    World world = tileEntity.func_145831_w();
    int blockX = tileEntity.field_145851_c;
    int blockY = tileEntity.field_145848_d;
    int blockZ = tileEntity.field_145849_e;
    Block block = tileEntity.func_145838_q();
    renderBlock(Minecraft.func_71410_x(), this.blockRenderer, tileEntity, world, x, y, z, block, blockX, blockY, blockZ, ticks);
  }
  
  private void renderBlock(Minecraft mc, RenderBlocks blockRenderer, TileEntity tile, World world, double x, double y, double z, Block block, int blockX, int blockY, int blockZ, float ticks) {
    EntityLivingBase entityLivingBase = mc.field_71451_h;
    if (chestUSPCache == null || System.currentTimeMillis() - lastChestUSPUpdate > 2000L) {
      lastChestUSPUpdate = System.currentTimeMillis();
      if (chestUSPCache == null)
        chestUSPCache = new CopyOnWriteArrayList<>(); 
      if (world.field_147482_g != null && !world.field_147482_g.isEmpty())
        EXECUTOR.execute(() -> {
              List<Consumer<Float>> tempCache = new ArrayList<>();
              int chunkX = blockX >> 4;
              int chunkZ = blockZ >> 4;
              int radius = mc.field_71474_y.field_151451_c;
              for (int ox = -radius; ox <= radius; ox++) {
                for (int oz = -radius; oz <= radius; oz++) {
                  int cx = chunkX + ox;
                  int cz = chunkZ + oz;
                  Chunk chunk = world.func_72964_e(cx, cz);
                  if (chunk != null)
                    for (Object tileEntityObj : chunk.field_150816_i.values()) {
                      if (!(tileEntityObj instanceof net.minecraft.inventory.IInventory))
                        continue; 
                      TileEntity tileEntity = (TileEntity)tileEntityObj;
                      int fx = tileEntity.field_145851_c;
                      int fy = tileEntity.field_145848_d;
                      int fz = tileEntity.field_145849_e;
                      tempCache.add(());
                    }  
                } 
              } 
              chestUSPCache.clear();
              chestUSPCache.addAll(tempCache);
            }); 
    } 
    if (chestUSPCache == null || chestUSPCache.isEmpty())
      return; 
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glLineWidth(2.0F);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glDisable(3553);
    GL11.glDisable(2929);
    GL11.glDisable(2884);
    GL11.glDisable(2896);
    for (Consumer<Float> element : chestUSPCache)
      element.accept(Float.valueOf(ticks)); 
    GL11.glEnable(2896);
    GL11.glEnable(2884);
    GL11.glEnable(2929);
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  private void drawBlockOutline(Entity viewer, int x, int y, int z, Color color, float ticks) {
    if (viewer == null || color == null || (Minecraft.func_71410_x()).field_71474_y.field_74320_O != 0)
      return; 
    Vec3 playerPos = Vec3.func_72443_a(viewer.field_70165_t, viewer.field_70163_u + viewer.func_70047_e(), viewer.field_70161_v);
    Vec3 blockPos = Vec3.func_72443_a(x + 0.5D, y + 0.5D, z + 0.5D);
    MovingObjectPosition result = viewer.field_70170_p.func_72933_a(playerPos, blockPos);
    if (result == null || result.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK || viewer.field_70170_p.func_147439_a(result.field_72311_b, result.field_72312_c, result.field_72309_d) != BlocksRegister.ENDIUM_CAVE_BLOCK)
      return; 
    float offset = 0.002F;
    Block block = viewer.field_70170_p.func_147439_a(x, y, z);
    if (block == null || block.isAir((IBlockAccess)viewer.field_70170_p, x, y, z) || !block.hasTileEntity(0))
      return; 
    double d0 = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * ticks;
    double d1 = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * ticks;
    double d2 = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * ticks;
    block.func_149719_a((IBlockAccess)viewer.field_70170_p, x, y, z);
    AxisAlignedBB box = block.func_149633_g(viewer.field_70170_p, x, y, z).func_72314_b(0.004000000189989805D, 0.004000000189989805D, 0.004000000189989805D).func_72325_c(-d0, -d1, -d2);
    RenderGlobal.func_147590_a(box, color.getRGB());
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78369_a(color.r, color.g, color.b, 0.05F);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72334_f);
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\render\BlockEndiumCaveRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */