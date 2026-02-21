package fr.paladium.palamod.modules.hunter.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityEndiumTotem;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockEndiumTotem extends Block implements ITooltipWiki {
  public BlockEndiumTotem() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.HUNTER_TAB);
    func_149663_c("endium_totem");
    func_149658_d("palamod:endium_totem");
    func_149752_b(1000.0F);
    func_149711_c(5.0F);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, this, 20);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEndiumTotem();
  }
  
  public boolean func_149727_a(final World world, final int x, final int y, final int z, final EntityPlayer player, int hitX, float hitY, float hitZ, float p_149727_9_) {
    TileEntityEndiumTotem te = (TileEntityEndiumTotem)world.func_147438_o(x, y, z);
    if (te != null && !world.field_72995_K && 
      te.hasEndium) {
      te.hasEndium = false;
      world.func_147468_f(x, y, z);
      (new Thread(new Runnable() {
            public void run() {
              Random r = world.field_73012_v;
              double i;
              for (i = 0.0D; i < 1.0D; i += 0.05D) {
                try {
                  Thread.sleep(100L);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + (r.nextDouble() * 2.0D - 1.0D) / 8.0D, y + 0.5D + i, z + 0.5D + (r
                      .nextDouble() * 2.0D - 1.0D) / 8.0D, 10, 0.01D);
                } catch (Exception exception) {}
              } 
              for (i = 0.0D; i < 3.0D; i += 0.1D) {
                try {
                  Thread.sleep(20L);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + i, (y + 1), z + 0.5D + i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + i, (y + 1), z + 0.5D - i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D - i, (y + 1), z + 0.5D + i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D - i, (y + 1), z + 0.5D - i, 10, 0.01D);
                } catch (Exception exception) {}
              } 
              world.func_72876_a((Entity)player, x, y, z, 0.0F, false);
              try {
                Thread.sleep(200L);
              } catch (Exception exception) {}
              for (i = 0.0D; i < 3.0D; i += 0.1D) {
                try {
                  Thread.sleep(20L);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + i, (y + 1), z + 0.5D, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D - i, (y + 1), z + 0.5D, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D, (y + 1), z + 0.5D + i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D, (y + 1), z + 0.5D - i, 10, 0.01D);
                } catch (Exception exception) {}
              } 
              world.func_72876_a((Entity)player, x, y, z, 0.0F, false);
              try {
                Thread.sleep(200L);
              } catch (Exception exception) {}
              for (i = 0.0D; i < 3.0D; i += 0.1D) {
                try {
                  Thread.sleep(20L);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + i, (y + 1), z + 0.5D + i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + i, (y + 1), z + 0.5D - i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D - i, (y + 1), z + 0.5D + i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D - i, (y + 1), z + 0.5D - i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D + i, (y + 1), z + 0.5D, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D - i, (y + 1), z + 0.5D, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D, (y + 1), z + 0.5D + i, 10, 0.01D);
                  EventUtils.spawnParticle(world, "flame", x + 0.5D, (y + 1), z + 0.5D - i, 10, 0.01D);
                } catch (Exception exception) {}
              } 
              AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a((x - 10), (y - 10), (z - 10), (x + 10), (y + 10), (z + 10));
              List players = world.func_72872_a(EntityLivingBase.class, scanAbove);
              for (Object obj : players) {
                if (obj instanceof EntityLivingBase) {
                  EntityLivingBase pl = (EntityLivingBase)obj;
                  ((WorldServer)world).func_72942_c((Entity)new EntityLightningBolt(world, x, y, z));
                  world.func_72876_a((Entity)pl, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, 0.0F, false);
                  pl.field_70160_al = true;
                  float str = 5.0F;
                  pl.field_70159_w += (pl.field_70165_t - x > 0.0D) ? str : -str;
                  pl.field_70181_x += str;
                  pl.field_70179_y += (pl.field_70161_v - z > 0.0D) ? str : -str;
                  if (obj instanceof EntityPlayerMP) {
                    EntityPlayerMP p = (EntityPlayerMP)pl;
                    p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)pl));
                  } 
                } 
              } 
              try {
                world.func_147449_b(x, y + 1, z, (Block)Blocks.field_150486_ae);
                TileEntityChest teChest = (TileEntityChest)world.func_147438_o(x, y + 1, z);
                teChest.func_70299_a(13, new ItemStack(ItemsRegister.ENDIUM_NUGGET));
              } catch (Exception exception) {}
            }
          })).start();
    } 
    return super.func_149727_a(world, x, y, z, player, hitX, hitY, hitZ, p_149727_9_);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    for (int ox = x - 2; ox < x + 2; ox++) {
      setPerlinpinpinActive(world, ox, y, z + 5, false);
      setPerlinpinpinActive(world, ox, y, z - 5, false);
    } 
    for (int oz = z - 2; oz < z + 2; oz++) {
      setPerlinpinpinActive(world, x + 5, y, oz, false);
      setPerlinpinpinActive(world, x - 5, y, oz, false);
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random) {
    if (!world.field_72995_K) {
      TileEntityEndiumTotem te = (TileEntityEndiumTotem)world.func_147438_o(x, y, z);
      boolean active = isStructureValid(world, x, y, z);
      te.setActive(active);
      for (int j = x - 2; j <= x + 2; j++) {
        setPerlinpinpinActive(world, j, y, z + 5, te.isActive());
        setPerlinpinpinActive(world, j, y, z - 5, te.isActive());
      } 
      for (int i = z - 2; i <= z + 2; i++) {
        setPerlinpinpinActive(world, x + 5, y, i, te.isActive());
        setPerlinpinpinActive(world, x - 5, y, i, te.isActive());
      } 
      int ox;
      for (ox = x - 4; ox <= x - 2; ox++) {
        setPerlinpinpinActive(world, ox, y, z + 2, te.isActive());
        setPerlinpinpinActive(world, ox, y, z - 2, te.isActive());
      } 
      for (ox = x + 2; ox <= x + 4; ox++) {
        setPerlinpinpinActive(world, ox, y, z + 2, te.isActive());
        setPerlinpinpinActive(world, ox, y, z - 2, te.isActive());
      } 
      int oz;
      for (oz = z - 4; oz <= z - 2; oz++) {
        setPerlinpinpinActive(world, x + 2, y, oz, te.isActive());
        setPerlinpinpinActive(world, x - 2, y, oz, te.isActive());
      } 
      for (oz = z + 2; oz <= z + 4; oz++) {
        setPerlinpinpinActive(world, x + 2, y, oz, te.isActive());
        setPerlinpinpinActive(world, x - 2, y, oz, te.isActive());
      } 
      if (te.isActive()) {
        te.addPlayer(getPlayerOfHotel(world, x - 3, y, z));
        te.addPlayer(getPlayerOfHotel(world, x + 3, y, z));
        te.addPlayer(getPlayerOfHotel(world, x, y, z + 3));
        te.addPlayer(getPlayerOfHotel(world, x, y, z - 3));
      } else {
        te.getPlayers().clear();
      } 
      world.func_147464_a(x, y, z, this, 20);
    } 
    super.func_149674_a(world, x, y, z, random);
  }
  
  public boolean isStructureValid(World world, int x, int y, int z) {
    if (world.field_72995_K)
      return false; 
    boolean valid = true;
    if (!isValidHotel(world, x - 3, y, z))
      valid = false; 
    if (!isValidHotel(world, x + 3, y, z))
      valid = false; 
    if (!isValidHotel(world, x, y, z - 3))
      valid = false; 
    if (!isValidHotel(world, x, y, z + 3))
      valid = false; 
    for (int j = x - 2; j <= x + 2; j++) {
      if (world.func_147439_a(j, y, z + 5) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
      if (world.func_147439_a(j, y, z - 5) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
    } 
    for (int i = z - 2; i <= z + 2; i++) {
      if (world.func_147439_a(x + 5, y, i) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
      if (world.func_147439_a(x - 5, y, i) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
    } 
    int ox;
    for (ox = x - 4; ox <= x - 2; ox++) {
      if (world.func_147439_a(ox, y, z + 2) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
      if (world.func_147439_a(ox, y, z - 2) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
    } 
    for (ox = x + 2; ox <= x + 4; ox++) {
      if (world.func_147439_a(ox, y, z + 2) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
      if (world.func_147439_a(ox, y, z - 2) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
    } 
    int oz;
    for (oz = z - 4; oz <= z - 2; oz++) {
      if (world.func_147439_a(x + 2, y, oz) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
      if (world.func_147439_a(x - 2, y, oz) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
    } 
    for (oz = z + 2; oz <= z + 4; oz++) {
      if (world.func_147439_a(x + 2, y, oz) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
      if (world.func_147439_a(x - 2, y, oz) != BlocksRegister.PERLINPINPIN_POWER) {
        valid = false;
        return valid;
      } 
    } 
    return valid;
  }
  
  private boolean isValidHotel(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) instanceof BlockSacrificeHotel)
      return ((BlockSacrificeHotel)world.func_147439_a(x, y, z)).isValid(world, x, y, z); 
    return false;
  }
  
  private EntityPlayer getPlayerOfHotel(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) instanceof BlockSacrificeHotel && (
      (BlockSacrificeHotel)world.func_147439_a(x, y, z)).getPlayers(world, x, y, z).size() > 0)
      return ((BlockSacrificeHotel)world.func_147439_a(x, y, z)).getPlayers(world, x, y, z).get(0); 
    return null;
  }
  
  private void setPerlinpinpinActive(World world, int x, int y, int z, boolean active) {
    if (world.func_147439_a(x, y, z) instanceof BlockPerlinpinpinPower) {
      BlockPerlinpinpinPower block = (BlockPerlinpinpinPower)world.func_147439_a(x, y, z);
      int meta = world.func_72805_g(x, y, z);
      if (active) {
        if (meta < 3)
          world.func_72921_c(x, y, z, meta + 3, 2); 
      } else if (meta >= 3) {
        world.func_72921_c(x, y, z, meta - 3, 2);
      } 
    } 
  }
  
  private void setHotelActive(World world, int x, int y, int z, boolean active) {
    if (world.func_147439_a(x, y, z) instanceof BlockSacrificeHotel)
      world.func_72921_c(x, y, z, active ? 1 : 0, 2); 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockEndiumTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */