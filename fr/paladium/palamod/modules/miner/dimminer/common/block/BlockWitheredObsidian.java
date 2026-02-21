package fr.paladium.palamod.modules.miner.dimminer.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityWitheredObsidian;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class BlockWitheredObsidian extends Block {
  private final IIcon[] icons = new IIcon[14];
  
  public BlockWitheredObsidian(String name) {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(5.0F);
    func_149663_c(name);
    func_149658_d("palamod:miner/" + name + "_0");
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    for (int i = 0; i < 14; i++)
      this.icons[i] = register.func_94245_a("palamod:miner/withered_obsidian_" + i); 
    super.func_149651_a(register);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityWitheredObsidian();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int meta, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (player.func_70694_bm() != null && 
      player.func_70694_bm().func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM)) {
      TileEntityWitheredObsidian tile = (TileEntityWitheredObsidian)world.func_147438_o(x, y, z);
      if (tile != null && 
        tile.getFeed() < 10 && !tile.hasPortal()) {
        if (!world.field_72995_K) {
          if (!player.field_71075_bZ.field_75098_d) {
            (player.func_70694_bm()).field_77994_a--;
            if ((player.func_70694_bm()).field_77994_a <= 0)
              player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
          } 
          tile.setFeed(tile.getFeed() + (ForgeEnv.isIDE() ? 10 : 1));
          tile.func_70296_d();
          world.func_147471_g(x, y, z);
          if (tile.getFeed() >= 10) {
            world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "ambient.weather.thunder", 10.0F, 1.0F);
            world.func_72942_c((Entity)new EntityLightningBolt(world, x, y, z));
          } else {
            world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.eat", 10.0F, 0.5F);
          } 
          if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            playerMP.field_71135_a.func_147359_a(tile.func_145844_m());
          } 
        } 
        return true;
      } 
    } 
    return super.func_149727_a(world, x, y, z, player, meta, p_149727_7_, p_149727_8_, p_149727_9_);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int side, EntityPlayer player) {
    super.func_149681_a(world, x, y, z, side, player);
    if (world.field_72995_K)
      return; 
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() > 0 && !((TileEntityWitheredObsidian)te).isCreated())
      PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(BlocksRegister.BLOCK_PALADIUM, ((TileEntityWitheredObsidian)te).getFeed())); 
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    drops.add(new ItemStack(BlocksRegister.WITHERED_OBSIDIAN));
    return drops;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess iblockaccess, int x, int y, int z, int meta) {
    if (iblockaccess.func_147438_o(x, y, z) instanceof TileEntityWitheredObsidian) {
      TileEntityWitheredObsidian tile = (TileEntityWitheredObsidian)iblockaccess.func_147438_o(x, y, z);
      return this.icons[tile.getFeed()];
    } 
    return super.func_149673_e(iblockaccess, x, y, z, meta);
  }
  
  public int getLightValue(IBlockAccess iblockaccess, int x, int y, int z) {
    if (iblockaccess.func_147438_o(x, y, z) instanceof TileEntityWitheredObsidian) {
      TileEntityWitheredObsidian tile = (TileEntityWitheredObsidian)iblockaccess.func_147438_o(x, y, z);
      return tile.getFeed();
    } 
    return super.getLightValue(iblockaccess, x, y, z);
  }
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    if (event.world.field_72995_K || event.block != this || !PMiner.proxy.isMinerDimension())
      return; 
    if (event.getPlayer() == null || (event.getPlayer()).field_71075_bZ.field_75098_d)
      return; 
    event.setCanceled(true);
    event.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas casser ce bloc dans cette dimension."));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\BlockWitheredObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */