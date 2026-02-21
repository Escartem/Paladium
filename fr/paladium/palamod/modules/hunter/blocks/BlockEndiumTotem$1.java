package fr.paladium.palamod.modules.hunter.blocks;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

class null implements Runnable {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockEndiumTotem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */