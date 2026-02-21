package fr.paladium.palamod.modules.miner.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import fr.paladium.palamod.modules.miner.item.ItemBuilderWand;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityVoidStone;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.tutorial.common.event.WitherGenerateItemsEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import org.lwjgl.opengl.GL11;

public class EventsManager {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void drawSelection(DrawBlockHighlightEvent event) {
    EntityPlayer player = event.player;
    ItemStack currentItem = player.func_71045_bC();
    MovingObjectPosition target = event.target;
    if (currentItem == null || !(currentItem.func_77973_b() instanceof ItemBuilderWand) || target.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)
      return; 
    ItemBuilderWand wand = (ItemBuilderWand)currentItem.func_77973_b();
    event.setCanceled(true);
    for (BlockPos pos : wand.getAvailablePos(player.field_70170_p, player, currentItem, target.field_72311_b, target.field_72312_c, target.field_72309_d, EnumFacing.values()[target.field_72310_e]))
      drawSelectionBox(event.context, player, pos, event.subID, event.partialTicks); 
  }
  
  @SideOnly(Side.CLIENT)
  public void drawSelectionBox(RenderGlobal render, EntityPlayer player, BlockPos target, int subID, float ticks) {
    if (subID == 0) {
      int damage = player.func_71045_bC().func_77960_j();
      int color = Color.WHITE.getRGB();
      switch (damage) {
        case 0:
          color = (new Color(153, 0, 153)).getRGB();
          break;
        case 1:
          color = (new Color(96, 96, 96)).getRGB();
          break;
        case 2:
          color = (new Color(204, 102, 0)).getRGB();
          break;
        case 3:
          color = (new Color(0, 0, 204)).getRGB();
          break;
      } 
      GL11.glEnable(3042);
      OpenGlHelper.func_148821_a(770, 771, 1, 0);
      GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
      GL11.glLineWidth(2.0F);
      GL11.glDisable(3553);
      GL11.glDepthMask(false);
      float f1 = 0.002F;
      Block block = player.field_70170_p.func_147439_a(target.getX(), target.getY(), target.getZ());
      block.func_149719_a((IBlockAccess)player.field_70170_p, target.getX(), target.getY(), target.getZ());
      double d0 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * ticks;
      double d1 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * ticks;
      double d2 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * ticks;
      RenderGlobal.func_147590_a(block.func_149633_g(player.field_70170_p, target.getX(), target.getY(), target.getZ()).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-d0, -d1, -d2), color);
      GL11.glDepthMask(true);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
    } 
  }
  
  @SubscribeEvent
  public void onGodVillager(LivingEvent.LivingUpdateEvent e) {
    if (e.entity instanceof EntityPlayer && !e.entity.field_70170_p.field_72995_K) {
      EntityPlayer player = (EntityPlayer)e.entity;
      if (JobsPlayer.get((Entity)player).getLevel(JobType.MINER) >= 19 && 
        player.field_70170_p.field_73012_v.nextInt(500000000) == 0) {
        EntityGodVillager entity = new EntityGodVillager(player.field_70170_p);
        entity.func_70107_b(player.field_70165_t, player.field_70163_u, player.field_70161_v);
        player.field_70170_p.func_72838_d((Entity)entity);
        player.func_85030_a("random.orb", 0.1F, 7.1499996F);
        player.func_85030_a("fireworks.launch1", 0.1F, 7.1499996F);
        EventUtils.spawnParticle(player.field_70170_p, "smoke", entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 1000, 0.5D);
        EventUtils.spawnParticle(player.field_70170_p, "flame", entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 400, 0.1D);
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onMinerVoidStoneUse(PlayerInteractEvent e) {
    if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && e.useItem != Event.Result.DENY && 
      e.entityPlayer.func_70694_bm() != null && !e.entityPlayer.field_70170_p.field_72995_K && (e.entityPlayer.func_70694_bm()).field_77994_a > 0 && 
      e.entityPlayer.func_70694_bm().func_77973_b() == ItemsRegister.VOIDSTONE_MINAGE && (e.entityPlayer.field_71070_bA == null || e.entityPlayer.field_71070_bA instanceof net.minecraft.inventory.ContainerPlayer)) {
      ItemVoidStoneMinage voidstone = (ItemVoidStoneMinage)e.entityPlayer.func_70694_bm().func_77973_b();
      if (e.world.func_147437_c(e.x, e.y + 1, e.z) && EventUtils.canInteract(e.entityPlayer, e.x, e.y, e.z)) {
        e.world.func_147449_b(e.x, e.y + 1, e.z, BlocksRegister.VOID_STONE);
        TileEntityVoidStone tileEntityVoidStone = (TileEntityVoidStone)e.world.func_147438_o(e.x, e.y + 1, e.z);
        if (tileEntityVoidStone != null)
          tileEntityVoidStone.setStone(voidstone.getStone(e.entityPlayer.func_70694_bm())); 
        (e.entityPlayer.func_70694_bm()).field_77994_a--;
        if ((e.entityPlayer.func_70694_bm()).field_77994_a <= 0)
          e.entityPlayer.func_70062_b(0, null); 
        e.entityPlayer.field_71071_by.func_70296_d();
      } 
    } 
  }
  
  @SubscribeEvent
  public void onWitheredPiston(ExplosionEvent.Detonate e) {
    if (!WitherUtils.isWither((Entity)e.explosion.func_94613_c()))
      return; 
    EntityLivingBase entityLivingBase = e.explosion.func_94613_c();
    EntityPlayer closest = ((Entity)entityLivingBase).field_70170_p.func_72890_a((Entity)entityLivingBase, 30.0D);
    if (closest == null || !JobsBridge.canUseCraft(closest, new ItemStack((Block)BlocksRegister.WITHERED_REINFORCED_PISTON), false))
      return; 
    List<EntityItem> entities = new ArrayList<>();
    for (ChunkPosition pos : e.getAffectedBlocks()) {
      if (e.world.func_147439_a(pos.field_151329_a, pos.field_151327_b, pos.field_151328_c) == Blocks.field_150331_J) {
        EntityItem item = new EntityItem(e.world, pos.field_151329_a, pos.field_151327_b, pos.field_151328_c, new ItemStack((Block)BlocksRegister.WITHERED_REINFORCED_PISTON));
        entities.add(item);
      } 
    } 
    if (!e.world.field_72995_K && entityLivingBase != null && entities != null) {
      List<ItemStack> stacks = new ArrayList<>();
      for (EntityItem entity : entities)
        stacks.add(entity.func_92059_d()); 
      MinecraftForge.EVENT_BUS.post((Event)new WitherGenerateItemsEvent(e.world, (int)((Entity)entityLivingBase).field_70165_t, (int)((Entity)entityLivingBase).field_70163_u, (int)((Entity)entityLivingBase).field_70161_v, stacks));
    } 
    PaladiumScheduler.INSTANCE.runTaskLater(() -> entities.forEach(e.world::func_72838_d), 20L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\event\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */