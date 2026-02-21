package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.gui.GuiFakeCommandBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityFakeCommandBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.world.PWorld;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFakeCommandBlock extends BlockContainer {
  private long lastGive = -1L;
  
  public BlockFakeCommandBlock() {
    super(Material.field_151573_f);
    this.field_149758_A = true;
    func_149663_c("fakecommandblock");
    func_149658_d("command_block");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    EntityPlayerMP entityPlayerMP;
    if (world.field_72995_K)
      return; 
    if (this.lastGive > System.currentTimeMillis())
      return; 
    TileEntity tileentity = world.func_147438_o(x, y, z);
    EntityPlayer player = null;
    double lastdist = Double.MAX_VALUE;
    List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
    for (EntityPlayerMP p : players) {
      double dst = p.func_70011_f(tileentity.field_145851_c, tileentity.field_145848_d, tileentity.field_145849_e);
      if (player == null || dst < lastdist) {
        entityPlayerMP = p;
        lastdist = dst;
      } 
    } 
    if (entityPlayerMP == null || lastdist > 32.0D)
      return; 
    if (tileentity != null && tileentity instanceof TileEntityFakeCommandBlock) {
      this.lastGive = System.currentTimeMillis() + 5000L;
      PlayerUtils.dropItemStack((Entity)entityPlayerMP, ((EntityPlayer)entityPlayerMP).field_70165_t, ((EntityPlayer)entityPlayerMP).field_70163_u, ((EntityPlayer)entityPlayerMP).field_70161_v, new ItemStack(
            Item.func_150898_a(PWorld.GRANITE_BLOCK)));
      world.func_147453_f(x, y, z, (Block)this);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int cx, float cy, float cz, float czz) {
    TileEntityFakeCommandBlock tile = (TileEntityFakeCommandBlock)world.func_147438_o(x, y, z);
    if (tile != null && world.field_72995_K)
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiFakeCommandBlock(tile.getLogic())); 
    return true;
  }
  
  public boolean func_149740_M() {
    return true;
  }
  
  public int func_149738_a(World world) {
    return 1;
  }
  
  public int func_149736_g(World world, int x, int y, int z, int jsp) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    return (tileentity != null && tileentity instanceof TileEntityFakeCommandBlock) ? ((TileEntityFakeCommandBlock)tileentity)
      .getLogic().func_145760_g() : 0;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    TileEntityFakeCommandBlock tile = (TileEntityFakeCommandBlock)world.func_147438_o(x, y, z);
    tile.getLogic().func_145752_a("give @p granite");
    if (item.func_82837_s())
      tile.getLogic().func_145754_b(item.func_82833_r()); 
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (world.field_72995_K)
      return; 
    boolean flag = world.func_72864_z(x, y, z);
    int l = world.func_72805_g(x, y, z);
    boolean flag1 = ((l & 0x1) != 0);
    if (flag && !flag1) {
      world.func_72921_c(x, y, z, l | 0x1, 4);
      world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
    } else if (!flag && flag1) {
      world.func_72921_c(x, y, z, l & 0xFFFFFFFE, 4);
    } 
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityFakeCommandBlock();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFakeCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */