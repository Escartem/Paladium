package fr.paladium.palamod.modules.paladium.common.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.alchimiste.common.utils.WSEnchantUtils;
import fr.paladium.palamod.modules.paladium.utils.CheckTileEntityRunnable;
import fr.paladium.palamod.modules.paladium.utils.IPositionateData;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class ChunkHandler {
  private static ConcurrentHashMap<ChunkPosition, Boolean[]> chunkMap = (ConcurrentHashMap)new ConcurrentHashMap<>();
  
  public static ConcurrentHashMap<ChunkPosition, Boolean[]> getChunkMap() {
    return chunkMap;
  }
  
  private static final ExecutorService executor = Executors.newFixedThreadPool(18);
  
  public static ExecutorService getExecutor() {
    return executor;
  }
  
  @SubscribeEvent
  public void onPreRenderWorld(RenderWorldEvent.Pre e) {
    Minecraft mc = Minecraft.func_71410_x();
    boolean shouldReturn = (mc.field_71439_g.func_70694_bm() == null || !(mc.field_71439_g.func_70694_bm().func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemUnclaimFinder) || !mc.field_71441_e.field_72995_K);
    if (shouldReturn) {
      if (mc.field_71439_g.field_71071_by.func_70440_f(3) == null)
        return; 
      Item item = mc.field_71439_g.field_71071_by.func_70440_f(3).func_77973_b();
      if (!(item instanceof ItemArmor) || !Objects.equals(Integer.valueOf(((ItemArmor)item).field_77881_a), Integer.valueOf(0)))
        return; 
      if (!WSEnchantUtils.hasEnchantment(mc.field_71439_g.field_71071_by.func_70440_f(3), EnchantMod.unclaimHelmet.field_77352_x))
        return; 
      NBTTagCompound tag = mc.field_71439_g.field_71071_by.func_70440_f(3).func_77942_o() ? mc.field_71439_g.field_71071_by.func_70440_f(3).func_77978_p() : new NBTTagCompound();
      if (tag == null)
        return; 
      if (!tag.func_74764_b("alchemistUnclaimType"))
        return; 
    } 
    ChunkCache cache = e.chunkCache;
    if (!(cache instanceof IPositionateData))
      return; 
    IPositionateData positionedData = (IPositionateData)cache;
    ChunkPosition chunkPosition = new ChunkPosition(positionedData.getX(), positionedData.getY(), positionedData.getZ());
    if (!chunkMap.containsKey(chunkPosition)) {
      chunkMap.put(chunkPosition, new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false) });
      getExecutor().submit((Runnable)new CheckTileEntityRunnable(0, 0, chunkPosition));
      getExecutor().submit((Runnable)new CheckTileEntityRunnable(1, 64, chunkPosition));
      getExecutor().submit((Runnable)new CheckTileEntityRunnable(2, 128, chunkPosition));
      getExecutor().submit((Runnable)new CheckTileEntityRunnable(3, 192, chunkPosition));
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onLoadMinecartChest(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityMinecartChest && !e.world.field_72995_K)
      ((EntityMinecartChest)e.entity).func_94095_a(DamageSource.field_76380_i); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\ChunkHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */