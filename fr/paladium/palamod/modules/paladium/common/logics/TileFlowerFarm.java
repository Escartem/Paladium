package fr.paladium.palamod.modules.paladium.common.logics;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockFlowerFarm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import software.bernie.geckolib3.block.tile.TileEntityBlockModelProps;

public class TileFlowerFarm extends TileEntityBlockModelProps implements ISidedInventory {
  private int work;
  
  private int alchemistLvl;
  
  private final int[][] firstFloorMetaPattern;
  
  private final Block[][] firstFloorPattern;
  
  private final Block[][] secondFloorExpectedBlocksPattern;
  
  private final int[][] secondFloorExpectedMetaPattern;
  
  private final List<BiomeGenBase.FlowerEntry> flowers;
  
  public void setWork(int work) {
    this.work = work;
  }
  
  public void setAlchemistLvl(int alchemistLvl) {
    this.alchemistLvl = alchemistLvl;
  }
  
  public TileFlowerFarm() {
    super("farm_flower", 10);
    this.work = 0;
    this.alchemistLvl = 0;
    this.firstFloorMetaPattern = new int[][] { { 0, 2, 0 }, { 2, 0, 2 }, { 0, 2, 0 } };
    this.firstFloorPattern = new Block[][] { { (Block)Blocks.field_150349_c, Blocks.field_150346_d, (Block)Blocks.field_150349_c }, { Blocks.field_150346_d, (Block)Blocks.field_150349_c, Blocks.field_150346_d }, { (Block)Blocks.field_150349_c, Blocks.field_150346_d, (Block)Blocks.field_150349_c } };
    this.secondFloorExpectedBlocksPattern = new Block[][] { { (Block)BlocksRegister.BUSH_XP_BERRY, (Block)Blocks.field_150328_O, (Block)Blocks.field_150327_N }, { (Block)Blocks.field_150328_O, BlocksRegister.FLOWER_FARM, (Block)Blocks.field_150328_O }, { (Block)Blocks.field_150328_O, BlocksRegister.FLOWER_MINERAL, (Block)Blocks.field_150438_bZ } };
    this.secondFloorExpectedMetaPattern = new int[][] { { 0, 6, 0 }, { 1, 0, 2 }, { 0, 0, 0 } };
    this.flowers = new ArrayList<>();
    registerFlowers();
  }
  
  public int getWork() {
    return this.work;
  }
  
  public int getAlchemistLvl() {
    return this.alchemistLvl;
  }
  
  public int[][] getFirstFloorMetaPattern() {
    return this.firstFloorMetaPattern;
  }
  
  public Block[][] getFirstFloorPattern() {
    return this.firstFloorPattern;
  }
  
  public Block[][] getSecondFloorExpectedBlocksPattern() {
    return this.secondFloorExpectedBlocksPattern;
  }
  
  public int[][] getSecondFloorExpectedMetaPattern() {
    return this.secondFloorExpectedMetaPattern;
  }
  
  public List<BiomeGenBase.FlowerEntry> getFlowers() {
    return this.flowers;
  }
  
  private void registerFlowers() {
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 4, 3));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 5, 3));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 6, 3));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 7, 3));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 0, 20));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 3, 20));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150328_O, 8, 20));
    this.flowers.add(new BiomeGenBase.FlowerEntry((Block)Blocks.field_150327_N, 0, 30));
  }
  
  public boolean canCraft(TileCrusher.EnumCrusherResult extractItem) {
    return true;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("work", this.work);
    compound.func_74768_a("alchemistLvl", this.alchemistLvl);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.work = compound.func_74762_e("work");
    this.alchemistLvl = compound.func_74762_e("alchemistLvl");
  }
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    if (slot == 0)
      return (stack != null && stack.func_77969_a(BlockFlowerFarm.FLOWER_FARM_FUEL)); 
    return super.func_94041_b(slot, stack);
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public float getScaledWork() {
    return this.work / 100.0F;
  }
  
  public void func_145845_h() {
    int x = this.field_145851_c;
    int y = this.field_145848_d;
    int z = this.field_145849_e;
    World world = this.field_145850_b;
    AtomicBoolean isFull = new AtomicBoolean(true);
    ItemStack fuel = func_70301_a(0);
    if (fuel == null || !fuel.func_77969_a(BlockFlowerFarm.FLOWER_FARM_FUEL)) {
      this.work = 100;
      return;
    } 
    for (int i = 1; i <= 9; i++) {
      ItemStack stack = func_70301_a(i);
      if (stack == null || stack.field_77994_a < 64)
        isFull.set(false); 
    } 
    boolean isFloorGood = (checkFirstFloor(world, x, y - 1, z) && validateSecondFloorPattern(world, x, y, z));
    if (isFull.get() || !isFloorGood) {
      this.work = 100;
      return;
    } 
    if (isFloorGood)
      if (this.work == 0) {
        func_70298_a(0, 1);
        this.work = 100;
        if (!world.field_72995_K) {
          List<ItemStack> drops = generateDrops(this.alchemistLvl, 3);
          int[] slots = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
          for (ItemStack drop : drops) {
            boolean added = false;
            for (int slotId : slots) {
              ItemStack currentItem = func_70301_a(slotId);
              if (currentItem == null) {
                func_70299_a(slotId, drop);
                added = true;
                break;
              } 
              if (currentItem.func_77969_a(drop) && currentItem.func_77960_j() == drop.func_77960_j()) {
                int newStackSize = currentItem.field_77994_a + drop.field_77994_a;
                int maxStackSize = currentItem.func_77976_d();
                if (newStackSize <= maxStackSize) {
                  currentItem.field_77994_a = newStackSize;
                  added = true;
                  break;
                } 
                drop.field_77994_a = newStackSize - maxStackSize;
                currentItem.field_77994_a = maxStackSize;
              } 
            } 
            if (!added)
              for (int slotId : slots) {
                if (func_70301_a(slotId) == null) {
                  func_70299_a(slotId, drop);
                  break;
                } 
              }  
          } 
          for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++)
              spawnBoneMealParticles(world, x + j - 1, y, z + k - 1); 
          } 
        } 
      } else if (this.work > 0) {
        this.work--;
      }  
  }
  
  public void spawnBoneMealParticles(World world, int x, int y, int z) {
    Random random = world.field_73012_v;
    for (int i = 0; i < 5; i++) {
      double offsetX = random.nextDouble();
      double offsetY = random.nextDouble() * 0.5D + 0.5D;
      double offsetZ = random.nextDouble();
      double particleX = x + offsetX;
      double particleY = y + offsetY;
      double particleZ = z + offsetZ;
      if (world instanceof WorldServer) {
        WorldServer worldServer = (WorldServer)world;
        worldServer.func_147487_a("happyVillager", particleX, particleY, particleZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
  
  public List<ItemStack> generateDrops(int alchemistLvl, int grassAndFlowersRange) {
    List<ItemStack> drops = new ArrayList<>();
    Random random = this.field_145850_b.field_73012_v;
    for (int i = 0; i < 2; i++)
      drops.add(new ItemStack((random.nextInt(2) == 0) ? (Block)Blocks.field_150328_O : (Block)Blocks.field_150327_N)); 
    drops.add(new ItemStack(Items.field_151014_N, 2));
    return drops;
  }
  
  private boolean checkFirstFloor(World world, int x, int y, int z) {
    return checkPatternInAllRotations(world, x, y, z, this.firstFloorPattern, this.firstFloorMetaPattern);
  }
  
  private boolean validateSecondFloorPattern(World world, int x, int y, int z) {
    Map<String, Integer> foundBlocks = new HashMap<>();
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Block block = world.func_147439_a(x + i - 1, y, z + j - 1);
        int metadata = world.func_72805_g(x + i - 1, y, z + j - 1);
        if (block.equals(BlocksRegister.BUSH_XP_BERRY) || block.equals(BlocksRegister.FLOWER_FARM) || block.equals(Blocks.field_150438_bZ))
          metadata = 0; 
        String blockKey = block.func_149739_a() + ":" + metadata;
        foundBlocks.put(blockKey, Integer.valueOf(((Integer)foundBlocks.getOrDefault(blockKey, Integer.valueOf(0))).intValue() + 1));
      } 
    } 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Block expectedBlock = this.secondFloorExpectedBlocksPattern[i][j];
        int expectedMeta = this.secondFloorExpectedMetaPattern[i][j];
        String expectedBlockKey = expectedBlock.func_149739_a() + ":" + expectedMeta;
        if (!foundBlocks.containsKey(expectedBlockKey) || ((Integer)foundBlocks.get(expectedBlockKey)).intValue() == 0)
          return false; 
        foundBlocks.put(expectedBlockKey, Integer.valueOf(((Integer)foundBlocks.get(expectedBlockKey)).intValue() - 1));
      } 
    } 
    return true;
  }
  
  private boolean checkPatternInAllRotations(World world, int x, int y, int z, Block[][] blocksPattern, int[][] metaPattern) {
    for (int rotation = 0; rotation < 4; rotation++) {
      if (checkPattern(world, x, y, z, blocksPattern, metaPattern))
        return true; 
      blocksPattern = rotatePattern(blocksPattern);
      metaPattern = rotatePattern(metaPattern);
    } 
    return false;
  }
  
  private boolean checkPattern(World world, int x, int y, int z, Block[][] blocksPattern, int[][] metaPattern) {
    boolean trash = false;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Block block = world.func_147439_a(x + i - 1, y, z + j - 1);
        int metadata = world.func_72805_g(x + i - 1, y, z + j - 1);
        if (!block.equals(blocksPattern[i][j]) || metadata != metaPattern[i][j])
          trash = true; 
      } 
    } 
    if (trash)
      return false; 
    return true;
  }
  
  private Block[][] rotatePattern(Block[][] pattern) {
    Block[][] rotatedPattern = new Block[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++)
        rotatedPattern[i][j] = pattern[3 - j - 1][i]; 
    } 
    return rotatedPattern;
  }
  
  private int[][] rotatePattern(int[][] pattern) {
    int[][] rotatedPattern = new int[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++)
        rotatedPattern[i][j] = pattern[3 - j - 1][i]; 
    } 
    return rotatedPattern;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public int[] func_94128_d(int side) {
    return new int[] { 0 };
  }
  
  public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
    return true;
  }
  
  public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileFlowerFarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */