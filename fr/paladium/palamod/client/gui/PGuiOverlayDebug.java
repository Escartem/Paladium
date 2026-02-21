package fr.paladium.palamod.client.gui;

import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.palamod.modules.pillage.common.blocks.tileentities.TECoordinateJammer;
import fr.paladium.palamod.util.MyHashSet;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.ClientCommandHandler;
import org.lwjgl.opengl.GL11;

public class PGuiOverlayDebug {
  private final Minecraft mc;
  
  private final FontRenderer fontRenderer;
  
  private static List<DebugBlockLayer> blockslayers = new ArrayList<>();
  
  private int lastX;
  
  private int lastZ;
  
  private TECoordinateJammer coordinateJammer;
  
  private Chunk lastChunk;
  
  private int lastChunkX;
  
  private int lastChunkZ;
  
  public PGuiOverlayDebug(Minecraft mc) {
    this.mc = mc;
    this.fontRenderer = mc.field_71466_p;
  }
  
  public void renderDebugInfo(ScaledResolution scaledresolution) {
    renderDebugInfoLeft();
    renderDebugInfoRight(scaledresolution);
  }
  
  protected void renderDebugInfoLeft() {
    List<Debug> list = getDebugInfoLeft();
    for (int m = 0; m < list.size(); m++) {
      Debug msg = list.get(m);
      if (msg != null) {
        int len = this.fontRenderer.func_78256_a(msg.text);
        Gui.func_73734_a(1, m * 10, len + 5, 10 + m * 10, msg.background.getRGB());
        this.fontRenderer.func_78261_a(msg.text, 2, 1 + m * 10, msg.font.getRGB());
      } 
    } 
  }
  
  public static MyHashSet<String> to() {
    return new MyHashSet(ClientCommandHandler.instance.func_71555_a().keySet());
  }
  
  protected void renderDebugInfoRight(ScaledResolution scaledresolution) {
    List<Debug> list = getDebugInfoRight();
    int width = scaledresolution.func_78326_a();
    for (int m = 0; m < list.size(); m++) {
      Debug msg = list.get(m);
      if (msg != null) {
        int len = this.fontRenderer.func_78256_a(msg.text);
        Gui.func_73734_a(width - len - 5, m * 10, width - 1, 10 + m * 10, msg.background.getRGB());
        this.fontRenderer.func_78261_a(msg.text, width - len - 2, 1 + m * 10, msg.font.getRGB());
      } 
    } 
  }
  
  protected List<Debug> getDebugInfoLeft() {
    List<Debug> list = new ArrayList<>();
    int x = MathHelper.func_76128_c(this.mc.field_71439_g.field_70165_t);
    int y = MathHelper.func_76128_c(this.mc.field_71439_g.field_70163_u);
    int z = MathHelper.func_76128_c(this.mc.field_71439_g.field_70161_v);
    list.add(new Debug("Paladium (" + Constants.MOD_ENV.toString().toLowerCase() + ")"));
    list.add(new Debug(this.mc.field_71426_K));
    list.add(null);
    if (shouldUpdateCoordinates()) {
      updateLastCoordinates();
      updateIfCoordinateJammerIsPresent();
    } 
    if (this.lastChunk == null || this.mc.field_71439_g.field_70173_aa % 20 == 0 || this.lastChunkX != this.mc.field_71439_g.field_70176_ah || this.lastChunkZ != this.mc.field_71439_g.field_70164_aj) {
      this.lastChunkX = this.mc.field_71439_g.field_70176_ah;
      this.lastChunkZ = this.mc.field_71439_g.field_70164_aj;
      this.lastChunk = this.mc.field_71441_e.func_72938_d(x, z);
      this.coordinateJammer = null;
      for (Object o : this.mc.field_71441_e.field_147482_g) {
        if (o instanceof TECoordinateJammer) {
          TECoordinateJammer te = (TECoordinateJammer)o;
          if (te.isPlayerInRange((EntityPlayer)this.mc.field_71439_g)) {
            this.lastX = MathHelper.func_76128_c(this.mc.field_71439_g.field_70165_t / 2.0D) + te.getFakeX();
            this.lastZ = MathHelper.func_76128_c(this.mc.field_71439_g.field_70161_v / 2.0D) + te.getFakeZ();
            this.coordinateJammer = te;
            break;
          } 
        } 
      } 
    } 
    list.add(new Debug(String.format("XYZ: %d / %d / %d", new Object[] { Integer.valueOf(this.lastX), Integer.valueOf(y), Integer.valueOf(this.lastZ) })));
    list.add(new Debug(String.format("Direction: %s", new Object[] { Direction.field_82373_c[MathHelper.func_76128_c((this.mc.field_71439_g.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3] })));
    list.add(null);
    if (this.mc.field_71441_e != null) {
      Chunk chunk = this.lastChunk;
      if (this.mc.field_71441_e.func_72899_e(x, y, z) && !chunk.func_76621_g()) {
        int dimension = this.mc.field_71439_g.field_71093_bK;
        int biomeID = (chunk.func_76591_a(x & 0xF, z & 0xF, this.mc.field_71441_e.func_72959_q())).field_76756_M;
        String biomeName = (chunk.func_76591_a(x & 0xF, z & 0xF, this.mc.field_71441_e.func_72959_q())).field_76791_y;
        List<DebugBlockLayer> ores = new ArrayList<>();
        for (DebugBlockLayer block : blockslayers) {
          if (y >= block.minY && y <= block.maxY)
            for (int dim : block.dimensions) {
              if (dimension == dim)
                if (block.biomes != null) {
                  for (int biome : block.biomes) {
                    if (biome == biomeID)
                      ores.add(block); 
                  } 
                } else {
                  ores.add(block);
                }  
            }  
        } 
        Collections.reverse(ores);
        for (DebugBlockLayer ore : ores)
          list.add(new Debug("> " + EnumChatFormatting.GRAY + ore.prefix + ore.block.func_149732_F())); 
        if (ores.size() != 0)
          list.add(null); 
        list.add(new Debug(String.format("Biome: %s (%d)", new Object[] { biomeName, Integer.valueOf(biomeID) })));
      } 
    } 
    return list;
  }
  
  private boolean shouldUpdateCoordinates() {
    return (this.mc.field_71439_g.field_70173_aa % 20 == 0 || this.mc.field_71439_g.field_70165_t != this.mc.field_71439_g.field_70142_S || this.mc.field_71439_g.field_70163_u != this.mc.field_71439_g.field_70137_T || this.mc.field_71439_g.field_70161_v != this.mc.field_71439_g.field_70136_U);
  }
  
  private void updateLastCoordinates() {
    this.lastX = MathHelper.func_76128_c(this.mc.field_71439_g.field_70165_t);
    this.lastZ = MathHelper.func_76128_c(this.mc.field_71439_g.field_70161_v);
    if (PMiner.proxy.isMinerDimension()) {
      DimMinerPlayer data = DimMinerPlayer.get();
      if (data != null && data.getSection() != null) {
        this.lastX -= data.getSection().getCenterX();
        this.lastZ -= data.getSection().getCenterZ();
      } 
    } 
  }
  
  private void updateIfCoordinateJammerIsPresent() {
    for (Object o : this.mc.field_71441_e.field_147482_g) {
      if (o instanceof TECoordinateJammer) {
        TECoordinateJammer te = (TECoordinateJammer)o;
        if (te.isPlayerInRange((EntityPlayer)this.mc.field_71439_g)) {
          this.lastX = MathHelper.func_76128_c(this.mc.field_71439_g.field_70165_t / 2.0D) + te.getFakeX();
          this.lastZ = MathHelper.func_76128_c(this.mc.field_71439_g.field_70161_v / 2.0D) + te.getFakeZ();
          break;
        } 
      } 
    } 
  }
  
  protected List<Debug> getDebugInfoRight() {
    long max = Runtime.getRuntime().maxMemory();
    long total = Runtime.getRuntime().totalMemory();
    long free = Runtime.getRuntime().freeMemory();
    long used = total - free;
    int x = MathHelper.func_76128_c(this.mc.field_71439_g.field_70165_t);
    int y = MathHelper.func_76128_c(this.mc.field_71439_g.field_70163_u);
    int z = MathHelper.func_76128_c(this.mc.field_71439_g.field_70161_v);
    List<Debug> list = new ArrayList<>();
    list.add(new Debug(String.format("Java: %s", new Object[] { System.getProperty("java.version") })));
    list.add(new Debug("Mem: " + (used * 100L / max) + "% " + (used / 1024L / 1024L) + "/" + (max / 1024L / 1024L) + "MB"));
    list.add(new Debug("Allocated: " + (total * 100L / max) + "% " + (total / 1024L / 1024L) + "MB"));
    list.add(new Debug("" + GL11.glGetString(7937)));
    if (this.mc.field_71441_e != null) {
      Chunk chunk = this.lastChunk;
      if (this.mc.field_71441_e.func_72899_e(x, y, z) && !chunk.func_76621_g()) {
        list.add(null);
        int dimension = this.mc.field_71439_g.field_71093_bK;
        int light = chunk.func_76614_a(EnumSkyBlock.Block, x & 0xF, y, z & 0xF);
        if (this.mc.field_71476_x != null && this.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
          int blockX = this.mc.field_71476_x.field_72311_b;
          int blockY = this.mc.field_71476_x.field_72312_c;
          int blockZ = this.mc.field_71476_x.field_72309_d;
          if (this.coordinateJammer != null && this.coordinateJammer.isPlayerInRange((EntityPlayer)this.mc.field_71439_g)) {
            blockX = this.mc.field_71476_x.field_72311_b / 2 + this.coordinateJammer.getFakeX();
            blockZ = this.mc.field_71476_x.field_72309_d / 2 + this.coordinateJammer.getFakeZ();
          } 
          Block block = this.mc.field_71441_e.func_147439_a(blockX, blockY, blockZ);
          int blockId = Block.func_149682_b(block);
          int blockMeta = block.func_149643_k((World)this.mc.field_71441_e, blockX, blockY, blockZ);
          list.add(new Debug((blockMeta == 0) ? String.format("Block: %d (%d %d %d)", new Object[] { Integer.valueOf(blockId), Integer.valueOf(blockX), Integer.valueOf(blockY), Integer.valueOf(blockZ) }) : String.format("Block: %d:%d (%d %d %d)", new Object[] { Integer.valueOf(blockId), Integer.valueOf(blockMeta), Integer.valueOf(blockX), Integer.valueOf(blockY), Integer.valueOf(blockZ) })));
        } 
        if (dimension == 0)
          if (light < 8) {
            list.add(new Debug(String.format("Light: " + EnumChatFormatting.RED + "%d", new Object[] { Integer.valueOf(light) })));
          } else {
            list.add(new Debug(String.format("Light: %d", new Object[] { Integer.valueOf(light) })));
          }  
      } 
    } 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (optionalDungeonWorld.isPresent()) {
      DungeonWorld dungeonWorld = optionalDungeonWorld.get();
      if (dungeonWorld != null) {
        list.add(null);
        list.add(new Debug("Owner: " + ((dungeonWorld.getOwner() != null) ? dungeonWorld.getOwner().getName() : "null")));
        list.add(new Debug("Dungeon: " + ((dungeonWorld.getDungeon() != null) ? dungeonWorld.getDungeon().getId() : "null")));
        list.add(new Debug("Level: " + (dungeonWorld.getLevel() + 1)));
        if (dungeonWorld.getActiveRoomId() != null && !dungeonWorld.getActiveRoomId().isEmpty())
          list.add(new Debug("Room: " + dungeonWorld.getActiveRoomId())); 
      } 
    } 
    return list;
  }
  
  public static void addBlockLayer(DebugBlockLayer blocklayer) {
    blockslayers.add(blocklayer);
  }
  
  public static void clearBlockLayer() {
    blockslayers.clear();
  }
  
  private static class Debug {
    private final String text;
    
    private final Color background = new Color(0, 0, 0, 80);
    
    private final Color font = new Color(255, 255, 255);
    
    public Debug(String text) {
      this.text = text;
    }
  }
  
  public static class DebugBlockLayer {
    private final Block block;
    
    private String prefix = "";
    
    private int[] dimensions = new int[] { 0 };
    
    private int[] biomes = null;
    
    private int minY = 2;
    
    private int maxY = 64;
    
    public DebugBlockLayer(Block block) {
      this.block = block;
    }
    
    public void setPrefix(String prefix) {
      this.prefix = prefix;
    }
    
    public void setDimensions(int... dimensions) {
      this.dimensions = dimensions;
    }
    
    public void setBiome(int... biomes) {
      this.biomes = biomes;
    }
    
    public void setLayer(int minY, int maxY) {
      this.minY = minY;
      this.maxY = maxY;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\gui\PGuiOverlayDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */