package fr.paladium.palamod.modules.luckyblock.events;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.helios.module.vanish.ModuleVanish;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palajobs.api.event.OnPlayerEarnXp;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.events.EditSignEvent;
import fr.paladium.palamod.modules.back2future.core.utils.math.Vector3d;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.LuckyBlockManager;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBalloon;
import fr.paladium.palamod.modules.luckyblock.entity.EntityGrappin;
import fr.paladium.palamod.modules.luckyblock.entity.EntityLuckyPainting;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityCustomCreeper;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.gui.GuiFishBowl;
import fr.paladium.palamod.modules.luckyblock.gui.june.BuskerDialogManager;
import fr.paladium.palamod.modules.luckyblock.gui.luckypass.UILuckyPass;
import fr.paladium.palamod.modules.luckyblock.gui.luckystats.GuiTalismanCalm;
import fr.paladium.palamod.modules.luckyblock.gui.may.BlacksmithDialogManager;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.hud.vision.VisionnedPlayers;
import fr.paladium.palamod.modules.luckyblock.items.ItemMeter;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontDrinkThat;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontDrinkThatBad;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Enigme;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Kezaco;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MinedTerrain;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.NiGuemeu;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.EnfantTrompette;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.Creepy;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.FeteTravail;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data.PlayerLuckyStats;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.LuckyStatsError;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PacketPotionExplose;
import fr.paladium.palamod.modules.luckyblock.network.PacketPumpkinBlur;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseJetpack;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.network.SCPacketUpdateHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.renders.CustomPlayerRender;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTombe;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.modules.paladium.common.items.boost.PlayerBoostEProperties;
import fr.paladium.palamod.modules.paladium.common.items.boost.hud.ModuleBoost;
import fr.paladium.palamod.modules.paladium.common.items.boost.packet.PlayerBoostEPropertiesSync;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import fr.paladium.palamod.modules.spellsv2.gui.buttons.GuiTexturedButton;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.PlayerUtil;
import java.awt.Color;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import noppes.npcs.entity.EntityNPCInterface;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.util.Vector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class EventsManager {
  public static Map<String, NiGuemeu.A> currentQuests = new ConcurrentHashMap<>();
  
  private int tick;
  
  @SubscribeEvent
  public void onEditSign(final EditSignEvent event) {
    String answer = String.join(" ", (CharSequence[])event.text);
    if (currentQuests.containsKey(event.player.func_110124_au().toString())) {
      NiGuemeu.Quest quest = ((NiGuemeu.A)currentQuests.get(event.player.func_110124_au().toString())).questToRespond;
      if (answer.toLowerCase().replace('ê', 'e').replace('è', 'e').replace('é', 'e')
        .contains(quest.answer.toLowerCase())) {
        CresusManager.getInstance().depositPlayerAsync(event.player.func_110124_au(), 10000.0D, "Enigme -> 10000$", new CresusCallback<CresusResponse>() {
              public void onSuccess(CresusResponse arg0) {
                event.player
                  .func_146105_b((IChatComponent)new ChatComponentText("Bien joué ! Voilà 10000$."));
              }
              
              public void onFail(CresusResponse arg0, Throwable arg1) {}
            });
      } else {
        CresusManager.getInstance().withdrawPlayerAsync(event.player.func_110124_au(), 5000.0D, "Enigme -> -5000$", new CresusCallback<CresusResponse>() {
              public void onSuccess(CresusResponse arg0) {
                event.player.func_146105_b((IChatComponent)new ChatComponentText("Malheuresement, tu as tors ! -5000$."));
              }
              
              public void onFail(CresusResponse arg0, Throwable arg1) {}
            });
      } 
      currentQuests.remove(event.player.func_110124_au().toString());
    } 
  }
  
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    Iterator<String> iter = currentQuests.keySet().iterator();
    while (iter.hasNext()) {
      String uuid = iter.next();
      NiGuemeu.A q = currentQuests.get(uuid);
      if (System.currentTimeMillis() > q.maxTime)
        currentQuests.remove(uuid); 
    } 
  }
  
  @SubscribeEvent
  public void onDamageLava(LivingHurtEvent e) {
    if (!(e.entityLiving instanceof EntityPlayer))
      return; 
    if (!DamageSource.field_76371_c.equals(e.source))
      return; 
    EntityPlayer player = (EntityPlayer)e.entityLiving;
    int x = (int)player.field_70165_t;
    int z = (int)player.field_70161_v;
    boolean cancel = false;
    for (int cx = x - 2; cx < x + 2; cx++) {
      for (int cz = z - 2; cz < z + 2; cz++) {
        if (player.field_70170_p.func_147439_a(cx, (int)player.field_70163_u, cz) == BlocksRegister.FAKE_LAVA) {
          cancel = true;
          break;
        } 
      } 
    } 
    if (cancel) {
      player.func_70015_d(0);
      e.setCanceled(true);
    } 
  }
  
  @SubscribeEvent
  public void onDamageAnvilMoney(LivingHurtEvent e) {
    if (!(e.entityLiving instanceof EntityPlayer))
      return; 
    if (e.source.func_76355_l() != "fallingBlock")
      return; 
    if (!e.entityLiving.func_70644_a((Potion)PLuckyBlock.ANVIL_MONEY))
      return; 
    if (!e.entity.field_70170_p.field_72995_K)
      depositEconomy((EntityPlayer)e.entityLiving); 
  }
  
  private void depositEconomy(final EntityPlayer player) {
    CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), 1000.0D, "Luckyblock -> anvil_money", new CresusCallback<CresusResponse>() {
          public void onFail(CresusResponse response, Throwable error) {
            PlayerUtils.sendMessage(player, "§cUne erreur est survenue");
          }
          
          public void onSuccess(CresusResponse response) {
            PlayerUtils.sendMessage(player, "§aVous venez de gagner §e1000$");
          }
        });
  }
  
  @SubscribeEvent
  public void onDamageStalactites(LivingHurtEvent e) {
    if (!(e.entityLiving instanceof EntityPlayer))
      return; 
    if (!DamageSource.field_82729_p.equals(e.source))
      return; 
    if (!e.entityLiving.func_70644_a((Potion)PLuckyBlock.STALACTITES))
      return; 
    e.entityLiving.func_70097_a(e.source, 2.0F);
  }
  
  @SubscribeEvent
  public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_82165_m(PLuckyBlock.ICE_GROUND.field_76415_H) && e.entityLiving instanceof EntityPlayer)
      if (e.entityLiving.field_70122_E) {
        float f = 1.47F;
        e.entityLiving.field_70159_w *= 1.4700000286102295D;
        e.entityLiving.field_70179_y *= 1.4700000286102295D;
      }  
  }
  
  @SubscribeEvent
  public void onEntityLivingUseItem(PlayerUseItemEvent.Finish e) {
    if (!(e.entityPlayer instanceof EntityPlayerMP))
      return; 
    ItemStack stack = e.item;
    if (stack == null)
      return; 
    if (!stack.func_77942_o())
      return; 
    NBTTagCompound compound = stack.func_77978_p();
    if (!compound.func_74764_b("luckyeffect"))
      return; 
    if (compound.func_74779_i("luckyeffect").equals("bad")) {
      for (Potion potion : DontDrinkThatBad.types)
        e.entityPlayer.func_70690_d(new PotionEffect(potion.func_76396_c(), 4800, 0)); 
      return;
    } 
    for (Potion potion : DontDrinkThat.types)
      e.entityPlayer.func_70690_d(new PotionEffect(potion.func_76396_c(), 4800, 0)); 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onSound(PlaySoundEvent17 e) {
    if (e.name == null)
      return; 
    if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    if (!(Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.FART))
      return; 
    if (!e.name.startsWith("step.") || e.name.contains("fart"))
      return; 
    e
      .result = (ISound)PositionedSoundRecord.func_147673_a(new ResourceLocation("palamod", "step_fart"));
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onOpenGui(final GuiScreenEvent.InitGuiEvent.Post e) {
    if (e.gui instanceof net.minecraft.client.gui.inventory.GuiInventory) {
      if (ClientProxy.spooky > System.currentTimeMillis())
        Minecraft.func_71410_x().func_147118_V().func_147682_a(
            (ISound)PositionedSoundRecord.func_147673_a(new ResourceLocation("palamod", "spooky"))); 
      int offset = 0;
      if (Loader.isModLoaded("NotEnoughItems"))
        try {
          Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
          Object hidden = c.getMethod("isHidden", new Class[0]).invoke(null, new Object[0]);
          Object enabled = c.getMethod("isEnabled", new Class[0]).invoke(null, new Object[0]);
          if (hidden != null && hidden instanceof Boolean && enabled != null && enabled instanceof Boolean)
            if (!((Boolean)hidden).booleanValue() && ((Boolean)enabled).booleanValue())
              offset = 20;  
        } catch (Exception e1) {
          e1.printStackTrace();
        }  
      GuiTexturedButton luckyBtn = new GuiTexturedButton(99, e.gui.field_146294_l / 2 - 4 - offset, e.gui.field_146295_m / 2 - 20, 50, 17, new ResourceLocation("palamod", "textures/gui/LuckyPass/lucky.png"), new ResourceLocation("palamod", "textures/gui/LuckyPass/luckyh.png"), new ResourceLocation("palamod", "textures/gui/LuckyPass/luckyd.png")) {
          public void func_146118_a(int x, int y) {
            e.gui.field_146297_k.func_147108_a((GuiScreen)new UILuckyPass());
            super.func_146118_a(x, y);
          }
        };
      EntityClientPlayerMP entityClientPlayerMP = e.gui.field_146297_k.field_71439_g;
      long date = PlayerLuckyPassProperties.get((EntityPlayer)entityClientPlayerMP).getDate();
      long now = TimeUtil.now();
      if (date != 0L && 24L - 
        Duration.between(TimeUtil.fromLong(date), TimeUtil.fromLong(now)).toHours() > 0L) {
        luckyBtn.field_146124_l = false;
        luckyBtn
          .field_146126_j = "Vous pourrez effectuer un prochain lancer dans " + (24L - Duration.between(TimeUtil.fromLong(date), TimeUtil.fromLong(now)).toHours()) + " heures";
      } 
      e.buttonList.add(luckyBtn);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onKeyInput(TickEvent.ClientTickEvent e) {
    if (e.phase == TickEvent.Phase.END && 
      (Minecraft.func_71410_x()).field_71439_g != null)
      if ((Minecraft.func_71410_x()).field_71474_y.field_74314_A.func_151468_f()) {
        if ((Minecraft.func_71410_x()).field_71439_g.func_82169_q(2) == null)
          return; 
        if ((Minecraft.func_71410_x()).field_71439_g.func_82169_q(2).func_77973_b() == ItemsRegister.JETPACK)
          PalaMod.getNetwork().sendToServer((IMessage)new PacketUseJetpack()); 
      } else if ((Minecraft.func_71410_x()).field_71439_g.field_70143_R == 0.0F) {
        if ((Minecraft.func_71410_x()).field_71439_g.func_82169_q(2) == null)
          return; 
        if ((Minecraft.func_71410_x()).field_71439_g.func_82169_q(2)
          .func_77973_b() == ItemsRegister.JETPACK)
          PalaMod.getNetwork().sendToServer((IMessage)new PacketUseJetpack(true)); 
      }  
  }
  
  @SubscribeEvent
  public void onInteract(PlayerInteractEvent e) {
    if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && e.entityPlayer.func_70093_af() && !e.world.field_72995_K) {
      if (e.entityPlayer.func_71045_bC() == null)
        return; 
      if (e.entityPlayer.func_71045_bC().func_77973_b() != 
        Item.func_150898_a(BlocksRegister.WIRELESS_LEVER))
        return; 
      e.setCanceled(e.isCancelable());
      NBTTagCompound compound = new NBTTagCompound();
      if (e.entityPlayer.func_71045_bC().func_77942_o())
        compound = e.entityPlayer.func_71045_bC().func_77978_p(); 
      compound.func_74768_a("x", e.x);
      compound.func_74768_a("y", e.y);
      compound.func_74768_a("z", e.z);
      e.entityPlayer.func_71045_bC().func_77982_d(compound);
      e.entityPlayer.func_145747_a((IChatComponent)new ChatComponentText("§eWireless Lever lié en §b" + e.x + " " + e.y + " " + e.z + "."));
    } 
  }
  
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      PlayerLuckyBlockProperties.get(e.original).saveNBTData(compound);
      PlayerLuckyBlockProperties.get(e.entityPlayer).loadNBTData(compound);
      compound = new NBTTagCompound();
      PlayerLuckyPassProperties.get(e.original).saveNBTData(compound);
      PlayerLuckyPassProperties.get(e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onRenderWorld(RenderWorldEvent.Pre e) {
    if ((Minecraft.func_71410_x()).field_71439_g.func_82165_m(PLuckyBlock.NO_TEXTURE.field_76415_H))
      e.renderBlocks
        
        .field_147840_d = (IIcon)((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110575_b)).func_110572_b("missingno"); 
  }
  
  @SubscribeEvent
  public void onConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof EntityPlayer && 
      
      PlayerLuckyBlockProperties.get((EntityPlayer)e.entity) == null)
      PlayerLuckyBlockProperties.register((EntityPlayer)e.entity); 
    if (e.entity instanceof EntityPlayer && 
      PlayerLuckyPassProperties.get((EntityPlayer)e.entity) == null)
      PlayerLuckyPassProperties.register((EntityPlayer)e.entity); 
  }
  
  @SubscribeEvent
  public void entityJoinWorld(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityPlayer && !e.world.field_72995_K) {
      PlayerLuckyBlockProperties luckyBlockProperties = PlayerLuckyBlockProperties.get((EntityPlayer)e.entity);
      if (luckyBlockProperties != null)
        luckyBlockProperties.setLoadingLuckyStats(false); 
      (new PlayerLuckyStats((EntityPlayer)e.entity)).load(t -> t.notifyPlayer(null), t -> ((EntityPlayer)e.entity).func_145747_a((IChatComponent)new ChatComponentText(t.getDefaultError())));
      PlayerLuckyPassProperties data = PlayerLuckyPassProperties.get((EntityPlayer)e.entity);
      try {
        data.setHasLuckyPass(BukkitUtils.hasPermission(((EntityPlayer)e.entity).func_110124_au(), "palamod.luckypass"));
      } catch (Exception|NoClassDefFoundError e2) {
        data.setHasLuckyPass(false);
      } 
      data.playerStartedTracking((EntityPlayer)e.entity);
      if (data != null)
        data.entitySpawned(); 
      PlayerBoostEPropertiesSync senderPacket = new PlayerBoostEPropertiesSync(PlayerBoostEProperties.get((EntityPlayer)e.entity));
      ModuleBoost.getInstance().getNetwork().sendTo((IMessage)senderPacket, (EntityPlayerMP)e.entity);
    } 
  }
  
  @SubscribeEvent
  public void entityConstructBoost(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof EntityPlayer && 
      e.entity.getExtendedProperties("palamod_BOOST") == null)
      e.entity.registerExtendedProperties("palamod_BOOST", (IExtendedEntityProperties)new PlayerBoostEProperties((EntityPlayer)e.entity)); 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void breakSpeed(PlayerInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    PlayerBoostEProperties boost = PlayerBoostEProperties.get(player);
    if (player != null && player instanceof EntityPlayerMP && event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && 
      player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() == ItemsRegister.PALADIUM_PICKAXE && 
      boost.getBoostMinerFou() > System.currentTimeMillis() && (
      event.world.func_147439_a(event.x, event.y, event.z).isToolEffective("pickaxe", 3) || event.world
      .func_147439_a(event.x, event.y, event.z) instanceof net.minecraft.block.BlockRedstoneOre || event.world
      .func_147439_a(event.x, event.y, event.z) instanceof net.minecraft.block.BlockObsidian) && 
      EventUtils.canInteract(player, event.x, event.y, event.z) && event.world.func_147439_a(event.x, event.y, event.z).func_149712_f(event.world, event.x, event.y, event.z) != -1.0F) {
      event.setCanceled(true);
      Block blk = event.world.func_147439_a(event.x, event.y, event.z);
      int meta = event.world.func_72805_g(event.x, event.y, event.z);
      if (!event.world.field_72995_K && blk != null && !blk.isAir((IBlockAccess)event.world, event.x, event.y, event.z)) {
        if (!blk.canHarvestBlock(player, meta))
          return; 
        if (!player.field_71075_bZ.field_75098_d) {
          int localMeta = event.world.func_72805_g(event.x, event.y, event.z);
          blk.func_149681_a(event.world, event.x, event.y, event.z, localMeta, player);
          if (blk.removedByPlayer(event.world, player, event.x, event.y, event.z, true)) {
            if (MinecraftForge.EVENT_BUS.post((Event)new BlockEvent.BreakEvent(event.x, event.y, event.z, event.world, blk, meta, player)))
              return; 
            blk.func_149664_b(event.world, event.x, event.y, event.z, localMeta);
          } 
          blk.func_149697_b(event.world, event.x, event.y, event.z, localMeta, 0);
        } else {
          int localMeta = event.world.func_72805_g(event.x, event.y, event.z);
          blk.func_149697_b(event.world, event.x, event.y, event.z, localMeta, 0);
        } 
        int xpDrop = blk.getExpDrop((IBlockAccess)event.world, blk.func_149643_k(event.world, event.x, event.y, event.z), 0);
        EntityXPOrb xp = new EntityXPOrb(event.world, event.x, event.y, event.z, xpDrop);
        if (xpDrop > 0)
          event.world.func_72838_d((Entity)xp); 
      } else {
        event.world.func_147468_f(event.x, event.y, event.z);
      } 
      event.world.func_72926_e(2001, event.x, event.y, event.z, Block.func_149682_b(blk) + (meta << 12));
      event.world.func_147468_f(event.x, event.y, event.z);
    } 
  }
  
  @SubscribeEvent
  public void onClonePlayerBoost(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      PlayerBoostEProperties.get(e.original).saveNBTData(compound);
      PlayerBoostEProperties.get(e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SubscribeEvent
  public void playerStartedTracking(PlayerEvent.StartTracking e) {
    if (e.target instanceof EntityPlayer) {
      PlayerLuckyPassProperties data = PlayerLuckyPassProperties.get((EntityPlayer)e.target);
      if (data != null)
        data.playerStartedTracking(e.entityPlayer); 
    } 
  }
  
  @SubscribeEvent
  public void onBodyGuardMustProtect(LivingHurtEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
      if (e.source.func_76364_f() != null && 
        UsersManager.getBodyGuard().keySet().contains(player))
        for (EntityIronGolem golem : UsersManager.getBodyGuard().get(player)) {
          golem.func_70784_b(e.source.func_76364_f());
          if (e.source.func_76364_f() instanceof EntityLivingBase) {
            golem.func_70624_b((EntityLivingBase)e.source.func_76364_f());
            golem.func_70604_c((EntityLivingBase)e.source.func_76364_f());
          } 
        }  
    } 
  }
  
  @SubscribeEvent
  public void onBodyGuardMustAttack(LivingHurtEvent e) {
    if (e.source.func_76364_f() != null && 
      e.source.func_76364_f() instanceof EntityPlayerMP && !(e.entityLiving instanceof EntityIronGolem)) {
      EntityPlayerMP player = (EntityPlayerMP)e.source.func_76364_f();
      if (UsersManager.getBodyGuard().containsKey(player))
        for (EntityIronGolem golem : UsersManager.getBodyGuard().get(player)) {
          golem.func_70784_b((Entity)e.entityLiving);
          golem.func_70624_b(e.entityLiving);
          golem.func_70604_c(e.entityLiving);
          golem.func_70778_a(new PathEntity(new PathPoint[] { new PathPoint((int)e.entityLiving.field_70165_t, (int)e.entityLiving.field_70163_u, (int)e.entityLiving.field_70161_v) }));
        }  
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onRenderFishBowl(RenderGameOverlayEvent e) {
    if (e.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS && 
      (Minecraft.func_71410_x()).field_71439_g.func_82169_q(3) != null && 
      (Minecraft.func_71410_x()).field_71439_g.func_82169_q(3).func_77973_b() != null && 
      (Minecraft.func_71410_x()).field_71439_g.func_82169_q(3).func_77973_b() == 
      Item.func_150898_a(BlocksRegister.FISH_BOWL))
      new GuiFishBowl(Minecraft.func_71410_x()); 
    if (e.type == RenderGameOverlayEvent.ElementType.EXPERIENCE && 
      ClientProxy.calm)
      new GuiTalismanCalm(); 
  }
  
  private static void drawTransparentRect(int par0, int par1, int par2, int par3, int par4) {
    if (par0 < par2) {
      int j1 = par0;
      par0 = par2;
      par2 = j1;
    } 
    if (par1 < par3) {
      int j1 = par1;
      par1 = par3;
      par3 = j1;
    } 
    float f = (par4 >> 24 & 0xFF) / 255.0F;
    float f1 = (par4 >> 16 & 0xFF) / 255.0F;
    float f2 = (par4 >> 8 & 0xFF) / 255.0F;
    float f3 = (par4 & 0xFF) / 255.0F;
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(f1, f2, f3, f);
    GL11.glBegin(7);
    GL11.glVertex2f(par0, par3);
    GL11.glVertex2f(par2, par3);
    GL11.glVertex2f(par2, par1);
    GL11.glVertex2f(par0, par1);
    GL11.glEnd();
    GL11.glDisable(3042);
    GL11.glLoadIdentity();
  }
  
  @SubscribeEvent
  public void onPiggyRodeo(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
      if (UsersManager.getPiggyRodeo().containsKey(player))
        if (!player.func_70115_ae() && ((EntityPig)UsersManager.getPiggyRodeo().get(player)).func_70089_S()) {
          player.func_70078_a((Entity)UsersManager.getPiggyRodeo().get(player));
        } else if (!((EntityPig)UsersManager.getPiggyRodeo().get(player)).func_70089_S() && player.func_70115_ae()) {
          player.func_110145_l(player.field_70154_o);
          UsersManager.getPiggyRodeo().remove(player);
        }  
    } 
  }
  
  @SubscribeEvent
  public void onFireManUpdate(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.FIRE_MAN) && 
      e.entityLiving.field_70170_p.field_72995_K)
      e.entityLiving.field_70170_p.func_72869_a("flame", e.entityLiving.field_70165_t, e.entityLiving.field_70163_u + (e.entityLiving.field_70170_p.field_73012_v
          .nextFloat() * 3.0F) - 1.0D, e.entityLiving.field_70161_v, 0.01D, 0.01D, 0.01D); 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onSuperManServerUpdate(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityPlayer) {
      EntityPlayer entityPlayer = (EntityPlayer)e.entity;
      if (!entityPlayer.func_70644_a((Potion)PLuckyBlock.SUPER_MAN) && 
        !entityPlayer.field_71075_bZ.field_75098_d)
        try {
          if (!BukkitUtils.hasPermission(entityPlayer.func_110124_au(), "palacore.cmd.fly")) {
            entityPlayer.field_71075_bZ.field_75101_c = false;
            entityPlayer.field_71075_bZ.field_75100_b = false;
            entityPlayer.func_71016_p();
          } 
        } catch (Exception e2) {
          entityPlayer.field_71075_bZ.field_75101_c = false;
          entityPlayer.field_71075_bZ.field_75100_b = false;
          entityPlayer.func_71016_p();
        }  
    } 
  }
  
  @SubscribeEvent
  public void onHellYeahUpdate(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.HELLYEAH)) {
      int var24 = MathHelper.func_76128_c((e.entityLiving.field_70177_z * 8.0F / 360.0F) + 0.5D) & 0x7;
      int xadd = 0;
      int zadd = 0;
      switch (var24) {
        case 0:
          zadd--;
          break;
        case 1:
          zadd--;
          xadd++;
          break;
        case 2:
          xadd++;
          break;
        case 3:
          zadd++;
          xadd++;
          break;
        case 4:
          zadd++;
          break;
        case 5:
          zadd++;
          xadd--;
          break;
        case 6:
          xadd--;
          break;
        case 7:
          zadd--;
          xadd--;
          break;
      } 
      if (!e.entityLiving.field_70170_p.field_72995_K && 
        !PlayerUtil.canPlace((EntityPlayerMP)e.entityLiving, 
          MathHelper.func_76128_c(e.entityLiving.field_70142_S) + xadd, 
          MathHelper.func_76128_c(e.entityLiving.field_70137_T), 
          MathHelper.func_76128_c(e.entityLiving.field_70136_U) + zadd, 0))
        if (e.entityLiving.field_70170_p.func_147437_c(
            MathHelper.func_76128_c(e.entityLiving.field_70142_S) + xadd, 
            MathHelper.func_76128_c(e.entityLiving.field_70137_T), 
            MathHelper.func_76128_c(e.entityLiving.field_70136_U) + zadd) && 
          !e.entityLiving.field_70170_p.func_147437_c(
            MathHelper.func_76128_c(e.entityLiving.field_70142_S) + xadd, 
            MathHelper.func_76128_c(e.entityLiving.field_70137_T) - 1, 
            MathHelper.func_76128_c(e.entityLiving.field_70136_U) + zadd))
          e.entityLiving.field_70170_p.func_147449_b(
              MathHelper.func_76128_c(e.entityLiving.field_70142_S) + xadd, 
              MathHelper.func_76128_c(e.entityLiving.field_70137_T), 
              MathHelper.func_76128_c(e.entityLiving.field_70136_U) + zadd, (Block)Blocks.field_150480_ab);  
    } 
  }
  
  @SubscribeEvent
  public void onFastSwordClientGlitch(LivingEvent.LivingUpdateEvent e) {
    if (e.entity instanceof EntityPlayer && e.entity.field_70170_p.field_72995_K) {
      EntityPlayer p = (EntityPlayer)e.entity;
      if (p.func_70694_bm() != null && !(p.func_70694_bm().func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.weapons.ItemFastsword) && p
        .func_82165_m(Potion.field_76422_e.func_76396_c()) && 
        p.func_70660_b(Potion.field_76422_e).func_76458_c() == 3)
        p.func_82170_o(Potion.field_76422_e.func_76396_c()); 
    } 
  }
  
  @SubscribeEvent
  public void onSuperManUpdate(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.SUPER_MAN)) {
      EntityPlayer entityPlayer = (EntityPlayer)e.entityLiving;
      if (entityPlayer.field_70163_u < 0.0D || entityPlayer.field_70163_u > 256.0D) {
        entityPlayer.func_82170_o(PLuckyBlock.SUPER_MAN.field_76415_H);
        entityPlayer.field_71075_bZ.field_75101_c = false;
        entityPlayer.field_71075_bZ.field_75100_b = false;
        entityPlayer.func_71016_p();
      } 
      if (entityPlayer.func_70660_b((Potion)PLuckyBlock.SUPER_MAN).func_76459_b() < 80) {
        entityPlayer.func_82170_o(PLuckyBlock.SUPER_MAN.field_76415_H);
        entityPlayer.field_71075_bZ.field_75101_c = false;
        entityPlayer.field_71075_bZ.field_75100_b = false;
        entityPlayer.func_71016_p();
      } else {
        List players = entityPlayer.field_70170_p.func_72872_a(EntityPlayer.class, 
            AxisAlignedBB.func_72330_a(entityPlayer.field_70165_t - 20.0D, entityPlayer.field_70163_u - 20.0D, entityPlayer.field_70161_v - 20.0D, entityPlayer.field_70165_t + 20.0D, entityPlayer.field_70163_u + 20.0D, entityPlayer.field_70161_v + 20.0D));
        if (players.size() > 1) {
          entityPlayer.func_82170_o(PLuckyBlock.SUPER_MAN.field_76415_H);
          entityPlayer.field_71075_bZ.field_75101_c = false;
          entityPlayer.field_71075_bZ.field_75100_b = false;
          entityPlayer.func_71016_p();
        } else {
          entityPlayer.field_71075_bZ.field_75101_c = true;
          entityPlayer.field_71075_bZ.field_75100_b = true;
          entityPlayer.func_71016_p();
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderCape(RenderPlayerEvent.Specials.Pre event) {
    ItemStack stack = event.entityPlayer.func_82169_q(2);
    if (((AbstractClientPlayer)event.entityPlayer).func_110303_q() != null && ((AbstractClientPlayer)event.entityPlayer)
      .func_110303_q()
      .equals(new ResourceLocation(ItemsRegister.GHOSTCAPE
          .getArmorTexture(stack, (Entity)event.entityPlayer, 1, "cape"))))
      ((AbstractClientPlayer)event.entityPlayer).func_152121_a(MinecraftProfileTexture.Type.CAPE, null); 
    if (stack != null && 
      stack.func_77973_b().equals(ItemsRegister.GHOSTCAPE))
      ((AbstractClientPlayer)event.entityPlayer).func_152121_a(MinecraftProfileTexture.Type.CAPE, new ResourceLocation(ItemsRegister.GHOSTCAPE
            .getArmorTexture(stack, (Entity)event.entityPlayer, 1, "cape"))); 
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderSkinPre(RenderPlayerEvent.Pre event) {
    if (PaladiumPlayer.get((Entity)event.entityPlayer).getSkinLast() > System.currentTimeMillis() && 
      PaladiumPlayer.get((Entity)event.entityPlayer).isNeedUpdateSkin())
      ClientProxy.loadSkin(PaladiumPlayer.get((Entity)event.entityPlayer).getSkinID(), MinecraftProfileTexture.Type.SKIN, (SkinManager.SkinAvailableCallback)event.entityPlayer); 
    if (event.entityPlayer instanceof EntityPlayer && 
      event.entityPlayer.func_70644_a((Potion)PLuckyBlock.MARIO_MUSHROOM)) {
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glTranslated(0.0D, 0.8D, 0.0D);
    } 
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderSkinPost(RenderPlayerEvent.Post event) {
    if (PaladiumPlayer.get((Entity)event.entityPlayer).getSkinLast() < System.currentTimeMillis() && 
      PaladiumPlayer.get((Entity)event.entityPlayer).isNeedUpdateSkin()) {
      Minecraft.func_71410_x().func_152342_ad().func_152790_a(((AbstractClientPlayer)event.entityPlayer)
          .func_146103_bH(), (SkinManager.SkinAvailableCallback)event.entityPlayer, true);
      PaladiumPlayer.get((Entity)event.entityPlayer).setNeedUpdateSkin(false);
    } 
  }
  
  @SubscribeEvent
  public void onSuperManDamaged(LivingHurtEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.SUPER_MAN) && e.entityLiving instanceof EntityPlayer) {
      EntityPlayer entityPlayer = (EntityPlayer)e.entityLiving;
      entityPlayer.func_82170_o(PLuckyBlock.SUPER_MAN.field_76415_H);
      entityPlayer.field_71075_bZ.field_75101_c = false;
      entityPlayer.field_71075_bZ.field_75100_b = false;
      entityPlayer.field_70143_R = 0.0F;
      entityPlayer.func_71016_p();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onSuperManDamagedClient(LivingHurtEvent e) {
    if (e.entityLiving.field_70170_p.field_72995_K)
      if (e.entityLiving.func_70005_c_()
        .equalsIgnoreCase((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
        if (ClientProxy.calm)
          ClientProxy.calm = false; 
      } else if (e.entityLiving instanceof EntityPlayer && 
        e.source.func_76364_f() instanceof EntityPlayer && 
        e.source.func_76364_f().func_70005_c_()
        .equalsIgnoreCase((Minecraft.func_71410_x()).field_71439_g.func_70005_c_()) && 
        e.entityLiving.field_70170_p.field_72995_K && 
        ClientProxy.calm) {
        ClientProxy.calm = false;
      }  
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onTombeBreak(BlockEvent.BreakEvent e) {
    if (e.block == BlocksRegister.TOMBE && 
      e.world.func_147438_o(e.x, e.y, e.z) != null && e.world
      .func_147438_o(e.x, e.y, e.z) instanceof TileEntityTombe) {
      TileEntityTombe tile = (TileEntityTombe)e.world.func_147438_o(e.x, e.y, e.z);
      if (tile != null && tile.getInventory() != null)
        for (ItemStack is : tile.getInventory()) {
          if (is != null)
            e.getPlayer().func_70099_a(is, 0.5F); 
        }  
    } 
  }
  
  @SubscribeEvent
  public void onBreakPalaRelou(BlockEvent.BreakEvent e) {
    Random r = e.world.field_73012_v;
    boolean isBlockValid = (e.block == BlocksRegister.BLOCK_FAKE_PALADIUM || e.block == BlocksRegister.BLOCK_FAKE_GPALADIUM);
    if (isBlockValid && e.blockMetadata < 15) {
      e.setCanceled(true);
      if (e.world instanceof WorldServer) {
        WorldServer world = (WorldServer)e.world;
        int x = e.x - 21 + r.nextInt(42);
        int z = e.z - 21 + r.nextInt(42);
        int y = world.func_72976_f(x, z);
        int c = 0;
        while (!EventUtils.canInteract(e.getPlayer(), x, y, z) && c < 50) {
          x = e.x - 21 + r.nextInt(42);
          z = e.z - 21 + r.nextInt(42);
          y = world.func_72976_f(x, z);
          c++;
        } 
        if (c >= 50) {
          x = e.x;
          z = e.z;
          y = e.y;
        } 
        world.func_147468_f(e.x, e.y, e.z);
        world.func_147449_b(x, y, z, (e.block == BlocksRegister.BLOCK_FAKE_PALADIUM) ? BlocksRegister.BLOCK_FAKE_PALADIUM : BlocksRegister.BLOCK_FAKE_GPALADIUM);
        world.func_72921_c(x, y, z, e.blockMetadata + 1, 0);
        Vector p1 = new Vector(e.x, e.y, e.z);
        Vector p2 = new Vector(x, y, z);
        Vector vector = p2.clone().subtract(p1).normalize().multiply(1);
        double length = 0.0D;
        for (; length < Math.sqrt((new BlockPos(e.x, e.y, e.z)).distanceSq(x, y, z)); p1
          .add(vector)) {
          EventUtils.spawnParticle(e.world, "reddust", p1.getX(), p1.getY(), p1.getZ(), 100, 0.0D);
          length++;
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onBlockBreak(BlockEvent.BreakEvent event) {
    Block b = null;
    Vec3 vc = null;
    for (Map.Entry<Vec3, Block> entry : (Iterable<Map.Entry<Vec3, Block>>)MinedTerrain.blockList.entrySet()) {
      Vec3 vectest = entry.getKey();
      if ((int)vectest.field_72450_a == event.x && (int)vectest.field_72448_b == event.y && (int)vectest.field_72449_c == event.z) {
        vc = entry.getKey();
        b = entry.getValue();
        break;
      } 
    } 
    if (b == null)
      return; 
    if (b != event.block)
      return; 
    MinedTerrain.blockList.remove(vc);
    EntityPlayer player = event.getPlayer();
    ItemStack itemStack = player.field_71071_by.func_70448_g();
    if (EnchantmentHelper.func_77506_a(Enchantment.SILK_TOUCH.getId(), itemStack) == 0)
      player.field_70170_p.func_72876_a(null, vc.field_72450_a, vc.field_72448_b, vc.field_72449_c, 15.0F, true); 
  }
  
  @SubscribeEvent
  public void EntityInteractEvent(EntityInteractEvent event) {
    Entity target = event.target;
    EntityPlayer player = event.entityPlayer;
    if (player == null || player.field_70170_p.field_72995_K)
      return; 
    if (player.field_71071_by == null || player.field_71071_by.func_70448_g() == null || player.field_71071_by
      .func_70448_g().func_77973_b() != ItemsRegister.VISION_HELMET)
      return; 
    if (!(target instanceof EntityPlayer))
      return; 
    EntityPlayer targetP = (EntityPlayer)target;
    Iterator<VisionnedPlayers> i = LuckyBlockManager.getInstance().getVisionHelmetData().iterator();
    while (i.hasNext()) {
      VisionnedPlayers v = i.next();
      if (v.getSender().equalsIgnoreCase(player.func_110124_au().toString()) || v
        .getVisionned().equalsIgnoreCase(player.func_110124_au().toString())) {
        if (v.isLinked()) {
          player.func_146105_b((IChatComponent)new ChatComponentText("§eVous venez de perdre la vision avec §c" + v
                .getVisionnedName()));
        } else {
          player.func_146105_b((IChatComponent)new ChatComponentText("§eVotre demande de vision a expirée"));
        } 
        i.remove();
      } 
    } 
    LuckyBlockManager.getInstance().getVisionHelmetData()
      .add(new VisionnedPlayers(player.func_110124_au().toString(), targetP.func_110124_au().toString(), targetP
          .func_70005_c_()));
    targetP.func_146105_b((IChatComponent)new ChatComponentText("§eLe joueur §a" + player
          .func_70005_c_() + " §evous propose la vision commune."));
    ChatComponentText chatComponentText = new ChatComponentText("§e/vision accept §7pour accepter la requête");
    ChatStyle style = (new ChatStyle()).func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vision accept"));
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§eCliquez pour accepter.")));
    chatComponentText.func_150255_a(style);
    targetP.func_146105_b((IChatComponent)chatComponentText);
    player.func_146105_b((IChatComponent)new ChatComponentText("§aVous avez demandé la vision à §7" + targetP
          .func_70005_c_()));
  }
  
  @SubscribeEvent
  public void playerInteractEvent(PlayerInteractEvent event) {
    if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && 
      !event.entityPlayer.func_70093_af()) {
      TileEntity t = event.world.func_147438_o(event.x, event.y, event.z);
      if (t instanceof net.minecraft.tileentity.TileEntityChest)
        for (int[] coords : UsersManager.getTraps()) {
          if (coords[0] == event.x && coords[1] == event.y && coords[2] == event.z) {
            UsersManager.getTraps().remove(new int[] { event.x, event.y, event.z });
            event.entityPlayer.field_70170_p.func_147468_f(event.x, event.y, event.z);
            event.entityPlayer.field_70170_p.func_72838_d((Entity)new EntityTNTPrimed(event.entityPlayer.field_70170_p, event.x, event.y, event.z, (EntityLivingBase)event.entityPlayer));
          } 
        }  
    } 
  }
  
  @SubscribeEvent
  public void onRespawn(final PlayerEvent.PlayerRespawnEvent e) {
    PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get(e.player);
    if (properties.isHasTombeDeath()) {
      e.player.func_145747_a((IChatComponent)new ChatComponentText("§eVous venez d'obtenir une clef de tombe !"));
      ItemStack isTombe = new ItemStack(ItemsRegister.KEY_TOMBE);
      e.player.field_71071_by.func_70441_a(isTombe);
      properties.setHasTombeDeath(false);
    } 
    if (properties.getPositionRespawn() != null) {
      final BlockPos pos = properties.getPositionRespawn();
      if (e.player.field_70170_p.func_147439_a(pos.getX(), pos.getY(), pos
          .getZ()) == BlocksRegister.SLEEPING_BAG) {
        e.player.func_146105_b((IChatComponent)new ChatComponentText("§eVous allez être envoyé à votre sac de couchage..."));
        PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
              public void run() {
                e.player.func_70634_a(pos.getX(), pos.getY(), pos.getZ());
                e.player.func_146105_b((IChatComponent)new ChatComponentText("§eVous avez respawn à votre sac de couchage !"));
              }
            }10L);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onPlayerDeath(LivingDeathEvent e) {
    if (e.entityLiving instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)e.entityLiving;
      if (player.func_70644_a((Potion)PLuckyBlock.TOMBE)) {
        int x = (int)player.field_70165_t;
        int z = (int)player.field_70161_v;
        int y = player.field_70170_p.func_72976_f(x, z);
        Block b = player.field_70170_p.func_147439_a(x, y, z);
        if (!EventUtils.canInteract(player, x, y, z))
          return; 
        if (b == Blocks.field_150357_h)
          return; 
        player.field_70170_p.func_147449_b(x, y, z, BlocksRegister.TOMBE);
        TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileEntityTombe) {
          TileEntityTombe tombe = (TileEntityTombe)tile;
          tombe.setOwner(player.func_70005_c_());
          List<ItemStack> arrlist = new ArrayList<>();
          Collections.addAll(arrlist, player.field_71071_by.field_70462_a);
          Collections.addAll(arrlist, player.field_71071_by.field_70460_b);
          tombe.setInventory(arrlist.<ItemStack>toArray(tombe.getInventory()));
          player.field_71071_by.field_70462_a = new ItemStack[player.field_71071_by.field_70462_a.length];
          player.field_71071_by.field_70460_b = new ItemStack[player.field_71071_by.field_70460_b.length];
        } 
        PlayerLuckyPassProperties.get(player).setHasTombeDeath(true);
        PlayerLuckyPassProperties.get(player).setPositionDeath(new BlockPos(e.entity));
      } 
    } 
  }
  
  @SubscribeEvent
  public void onBoomMobs(LivingHurtEvent e) {
    if (!(e.entityLiving instanceof EntityPlayer) && !(e.entityLiving instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase) && 
      e.source.func_76364_f() != null && 
      e.source.func_76364_f() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)e.source.func_76364_f();
      if (player.func_70644_a((Potion)PLuckyBlock.BOOM_MOBS))
        e.entityLiving.func_70091_d(0.0D, (30 + (new Random()).nextInt(30)), 0.0D); 
    } 
  }
  
  @SubscribeEvent
  public void onGrosRelouUpdate(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.GROS_RELOU) && e.entityLiving instanceof EntityPlayer && e.entityLiving.field_70122_E)
      if (e.entityLiving.field_70170_p.field_73012_v.nextInt(10) == 5)
        ((EntityPlayer)e.entityLiving).func_70664_aZ();  
  }
  
  @SubscribeEvent
  public void onPlayerJoin(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entity;
      if (player.field_71068_ca == 0 && player.field_71106_cc == 0.0F && player.field_71067_cb == 0) {
        player.field_71068_ca = 0;
        player.field_71106_cc = 1.0F;
        player.field_71067_cb = 1;
      } 
      if (!e.entity.field_70170_p.field_72995_K && UsersManager.getKeepPumpkinBlur().contains(FastUUID.toString((Entity)player)))
        PalaMod.getNetwork().sendTo((IMessage)new PacketPumpkinBlur(true), player); 
    } 
  }
  
  @SubscribeEvent
  public void onWalkInMusic(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.WALK_IN_MUSIC) && e.entityLiving.field_70122_E && 
      e.entityLiving.field_70170_p.field_72995_K)
      e.entityLiving.field_70170_p.func_72869_a("note", e.entityLiving.field_70165_t, e.entityLiving.field_70163_u - 1.0D, e.entityLiving.field_70161_v, 0.01D, 0.01D, 0.01D); 
  }
  
  @SubscribeEvent
  public void onMagnetic(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving == null)
      return; 
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.MAGNETIC) && 
      e.entityLiving instanceof EntityPlayer && 
      e.entityLiving.field_70170_p.field_72995_K) {
      EntityPlayer player = (EntityPlayer)e.entityLiving;
      Collection<Entity> entitys = player.field_70170_p.func_72872_a(Entity.class, 
          AxisAlignedBB.func_72330_a(player.field_70165_t - 5.0D, player.field_70163_u - 5.0D, player.field_70161_v - 5.0D, player.field_70165_t + 5.0D, player.field_70163_u + 5.0D, player.field_70161_v + 5.0D));
      float str = 0.2F;
      entitys.stream().filter(entity -> (entity != player)).forEach(entity -> {
            entity.field_70159_w += (entity.field_70165_t - player.field_70165_t > 0.0D) ? -0.20000000298023224D : 0.20000000298023224D;
            entity.field_70181_x += (entity.field_70163_u - player.field_70163_u > 0.0D) ? -0.20000000298023224D : 0.20000000298023224D;
            entity.field_70179_y += (entity.field_70161_v - player.field_70161_v > 0.0D) ? -0.20000000298023224D : 0.20000000298023224D;
          });
    } 
  }
  
  @SubscribeEvent
  public void onDamageInvincible(LivingHurtEvent e) {
    if (e.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)e.entity;
      if (UsersManager.getInvincibles().contains(player))
        e.setCanceled(true); 
    } 
  }
  
  @SubscribeEvent
  public void onDamagePricklySheep(LivingHurtEvent e) {
    if (e.entityLiving instanceof fr.paladium.palamod.modules.luckyblock.entity.EntityPricklySheep) {
      if (e.source.func_76355_l() != "explosion")
        e.setCanceled(true); 
      if (e.source.func_76364_f() != null)
        e.source.func_76364_f().func_70097_a(e.source, e.ammount); 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onChat(ServerChatEvent e) {
    if (Enigme.handleChat(e.player, e.message)) {
      e.setCanceled(true);
      return;
    } 
    if (Kezaco.kezaco.contains(e.player.func_110124_au())) {
      String txt = e.component.func_150254_d();
      String newTxt = "§k";
      char lastChar = Character.MIN_VALUE;
      for (char c : txt.toCharArray()) {
        newTxt = newTxt + c;
        if (lastChar == '§')
          newTxt = newTxt + "§k"; 
        lastChar = c;
      } 
      e.component = new ChatComponentTranslation("%s", new Object[] { newTxt });
      List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
      players.stream().forEach(player -> player.func_145747_a((IChatComponent)new ChatComponentText("§eHoula, on comprend rien à ce que tu racontes.")));
      Kezaco.kezaco.remove(e.player.func_110124_au());
    } 
    if (UsersManager.getKillOrClear().contains(e.player))
      if (e.message.contains("clear")) {
        e.setCanceled(true);
        for (int i = 0; i < e.player.field_71071_by.field_70462_a.length; i++) {
          ItemStack item = e.player.field_71071_by.field_70462_a[i];
          if (item != null && 
            item.func_77973_b() != Item.func_150898_a((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK) && item
            .func_77973_b() != Item.func_150898_a((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK))
            e.player.field_71071_by.field_70462_a[i] = null; 
        } 
        UsersManager.getKillOrClear().remove(e.player);
      } else if (e.message.contains("kill")) {
        e.setCanceled(true);
        e.player.func_70606_j(0.0F);
        e.player.func_70106_y();
        UsersManager.getKillOrClear().remove(e.player);
      }  
    String playerId = FastUUID.toString((Entity)e.player);
    if (UsersManager.getDigicodeInit().containsKey(playerId)) {
      e.setCanceled(true);
      TileEntityDigicode digicode = (TileEntityDigicode)UsersManager.getDigicodeInit().get(playerId);
      if (!digicode.isInit()) {
        Integer value = null;
        try {
          value = Integer.valueOf(e.message);
        } catch (Exception exception) {}
        if (value != null && value.intValue() >= 1 && value.intValue() <= 9) {
          digicode.setCode(value.intValue());
        } else {
          digicode.removeNotInit();
        } 
      } 
      UsersManager.getDigicodeInit().remove(playerId);
    } 
  }
  
  @SubscribeEvent
  public void onEatSoup(PlayerUseItemEvent.Finish e) {
    if (e.entityPlayer instanceof EntityPlayerMP) {
      EntityPlayerMP p = (EntityPlayerMP)e.entityPlayer;
      if (UsersManager.getBol().contains(p)) {
        e.entityPlayer.func_70062_b(4, new ItemStack(Blocks.field_150423_aK));
        UsersManager.getBol().remove(p);
        UsersManager.getKeepPumpkinBlur().add(FastUUID.toString((Entity)p));
        PalaMod.getNetwork().sendTo((IMessage)new PacketPumpkinBlur(true), p);
      } 
    } 
  }
  
  private final ResourceLocation pumpkinblur = new ResourceLocation("textures/misc/pumpkinblur.png");
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void renderPumpkinBlur(RenderGameOverlayEvent event) {
    if (event.type == RenderGameOverlayEvent.ElementType.ALL && ClientProxy.pumpkinBlur) {
      ScaledResolution res = event.resolution;
      GuiUtils.drawImageTransparent(0.0D, 0.0D, this.pumpkinblur, (res.func_78326_a() / 100.0F * 100.0F), (res.func_78328_b() / 100.0F * 100.0F));
    } 
  }
  
  @SubscribeEvent
  public void onTriforce(final PlayerInteractEvent e) {
    if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && 
      e.world.func_147439_a(e.x, e.y, e.z) == BlocksRegister.ENDIUM_CHEST && e.world
      .func_72805_g(e.x, e.y, e.z) == 1 && 
      e.world instanceof WorldServer) {
      WorldServer worldServer = (WorldServer)e.world;
      e.entityLiving.func_70097_a(DamageSource.field_76376_m, Float.MAX_VALUE);
      for (int i = 0; i < 50; i++) {
        EntityLightningBolt bolt = new EntityLightningBolt(e.world, e.x, e.y, e.z);
        worldServer.func_72942_c((Entity)bolt);
      } 
      (new Thread(new Runnable() {
            public void run() {
              try {
                Thread.sleep(20000L);
                e.world.func_147449_b(e.x, e.y, e.z, Blocks.field_150350_a);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } 
            }
          })).start();
    } 
  }
  
  @SubscribeEvent
  public void onRainbowArmor(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
      for (int i = 0; i < 4; i++) {
        if (player.func_82169_q(i) != null) {
          if (player.func_82169_q(i).func_77973_b() instanceof ItemArmor) {
            ItemArmor armor = (ItemArmor)player.func_82169_q(i).func_77973_b();
            if (armor.func_82812_d() == ItemArmor.ArmorMaterial.CLOTH && 
              player.func_82169_q(i).func_77942_o() && 
              player.func_82169_q(i).func_77978_p().func_74764_b("rainbow") && 
              player.func_82169_q(i).func_77978_p().func_74767_n("rainbow")) {
              NBTTagCompound display = new NBTTagCompound();
              if (player.func_82169_q(i).func_77978_p().func_74764_b("display"))
                display = player.func_82169_q(i).func_77978_p().func_74775_l("display"); 
              display.func_74768_a("color", Color.HSBtoRGB(
                    (float)(System.currentTimeMillis() % 3000L) / 3000.0F, 0.8F, 0.8F));
            } 
          } 
          player.field_71071_by.field_70459_e = true;
        } 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void render(RenderWorldLastEvent event) {
    EntityClientPlayerMP player = (Minecraft.func_71410_x()).field_71439_g;
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    EntityGrappin g = null;
    for (Object o : (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72910_y()) {
      if (o instanceof EntityGrappin) {
        EntityGrappin og = (EntityGrappin)o;
        if (og.field_70250_c.func_70005_c_()
          .equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_()))
          g = (EntityGrappin)o; 
      } 
    } 
    if (ClientSeedEvent.event != null)
      ClientSeedEvent.event.tick(); 
    if (g != null) {
      Vector3d p1 = new Vector3d(x, y, z);
      Vector3d p2 = new Vector3d(g.field_70165_t, g.field_70163_u, g.field_70161_v);
      GL11.glPushMatrix();
      GL11.glTranslated(-x, -y, -z);
      GL11.glLineWidth(6.0F);
      GL11.glColor3ub((byte)-1, (byte)0, (byte)0);
      GL11.glBegin(1);
      GL11.glVertex3d(p1.x, p1.y, p1.z);
      GL11.glVertex3d(p2.x, p2.y, p2.z);
      GL11.glEnd();
      GL11.glPopMatrix();
    } 
    if (ItemMeter.locFirst.containsKey(player.func_110124_au())) {
      Vec3 vec = (Vec3)ItemMeter.locFirst.get(player.func_110124_au());
      Vector3d p2 = new Vector3d(vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
      double lookX = player.field_70165_t + (player.func_70040_Z()).field_72450_a;
      double lookY = player.field_70163_u + (player.func_70040_Z()).field_72448_b;
      double lookZ = player.field_70161_v + (player.func_70040_Z()).field_72449_c;
      Vector3d p1 = new Vector3d(lookX, lookY, lookZ);
      GL11.glPushMatrix();
      GL11.glTranslated(-x, -y, -z);
      GL11.glLineWidth(6.0F);
      GL11.glColor3ub((byte)-1, (byte)0, (byte)0);
      GL11.glBegin(1);
      GL11.glVertex3d(p1.x, p1.y, p1.z);
      GL11.glVertex3d(p2.x, p2.y, p2.z);
      GL11.glEnd();
      GL11.glPopMatrix();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void renderBalloon(RenderWorldLastEvent event) {
    for (Object o : (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72910_y()) {
      if (o instanceof EntityBalloon) {
        EntityBalloon og = (EntityBalloon)o;
        if (og.getOwner() != null) {
          double x = (og.getOwner()).field_70165_t;
          double y = (og.getOwner()).field_70163_u;
          double z = (og.getOwner()).field_70161_v;
          Vector3d p1 = new Vector3d(x, y, z);
          Vector3d p2 = new Vector3d(og.field_70165_t, og.field_70163_u + 1.0D, og.field_70161_v);
          GL11.glPushMatrix();
          GL11.glTranslated(-(Minecraft.func_71410_x()).field_71439_g.field_70165_t, 
              -(Minecraft.func_71410_x()).field_71439_g.field_70163_u, -(Minecraft.func_71410_x()).field_71439_g.field_70161_v);
          GL11.glLineWidth(6.0F);
          GL11.glColor3ub((byte)-1, (byte)0, (byte)0);
          GL11.glBegin(1);
          GL11.glVertex3d(p1.x, p1.y, p1.z);
          GL11.glVertex3d(p2.x, p2.y, p2.z);
          GL11.glEnd();
          GL11.glPopMatrix();
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void playerKillPlayer(LivingDeathEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer victim = (EntityPlayer)event.entity;
    if (event.source.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)event.source.func_76346_g();
      if (player.field_71071_by.func_70448_g() != null && player.field_71071_by
        .func_70448_g().func_77973_b() == ItemsRegister.FAUX) {
        ItemStack is = player.field_71071_by.func_70448_g();
        NBTTagCompound compound = is.field_77990_d;
        int dura = 15;
        if (compound.func_74764_b("dura"))
          dura = compound.func_74762_e("dura"); 
        compound.func_74768_a("dura", dura);
        compound.func_74778_a("soul", victim.func_70005_c_());
        compound.func_74778_a("soulUUID", victim.func_110124_au().toString());
        player.field_71071_by.func_70448_g().func_77982_d(compound);
        if (!player.field_70170_p.field_72995_K)
          return; 
        player.func_146105_b((IChatComponent)new ChatComponentText("§aVous venez d'absorber l'âme de §e" + victim
              .func_70005_c_()));
      } 
    } 
  }
  
  @SubscribeEvent
  public void LivingDeath(LivingDeathEvent e) {
    if (e.entityLiving.getEntityData() != null && 
      e.entityLiving.getEntityData().func_74764_b("drop_count")) {
      int d = e.entityLiving.getEntityData().func_74762_e("drop_count");
      PlayerUtils.dropItemStack((Entity)e.entityLiving, e.entityLiving.field_70165_t, e.entityLiving.field_70163_u, e.entityLiving.field_70161_v, new ItemStack(ItemsRegister.PALADIUM_INGOT, d));
    } 
  }
  
  private static final ResourceLocation cowTextures = new ResourceLocation("textures/entity/cow/cow.png");
  
  private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");
  
  private static final ResourceLocation steveTexture = new ResourceLocation("textures/entity/steve.png");
  
  private EntityRenderer renderer;
  
  private EntityRenderer prevRenderer;
  
  private boolean hasPigStuff(EntityPlayer p) {
    if (p == null)
      return false; 
    ItemStack[] armor = p.field_71071_by.field_70460_b;
    if (armor == null)
      return false; 
    if (armor[0] == null || armor[0].func_77973_b() != ItemsRegister.PIG_BOOTS)
      return false; 
    if (armor[1] == null || armor[1].func_77973_b() != ItemsRegister.PIG_LEGGINGS)
      return false; 
    if (armor[2] == null || armor[2].func_77973_b() != ItemsRegister.PIG_CHESTPLATE)
      return false; 
    if (armor[3] == null || armor[3].func_77973_b() != ItemsRegister.PIG_HELMET)
      return false; 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onRenderLiving(RenderPlayerEvent.Specials.Pre event) {
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.SOLITAIRE) && event.entity != 
      (Minecraft.func_71410_x()).field_71439_g)
      event.setCanceled(true); 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onRenderPlayer(RenderPlayerEvent.Pre e) {
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.SOLITAIRE) && e.entityPlayer != 
      (Minecraft.func_71410_x()).field_71439_g) {
      e.setCanceled(true);
      return;
    } 
    if (ClientProxy.calm) {
      e.setCanceled(true);
      return;
    } 
    EntityPlayer p = e.entityPlayer;
    if (p.func_70644_a((Potion)PLuckyBlock.GHOST)) {
      e.renderer.field_77109_a.field_78115_e.field_78807_k = true;
      e.renderer.field_77109_a.field_78113_g.field_78807_k = true;
      e.renderer.field_77109_a.field_78112_f.field_78807_k = true;
      e.renderer.field_77109_a.field_78124_i.field_78807_k = true;
      e.renderer.field_77109_a.field_78123_h.field_78807_k = true;
    } else {
      e.renderer.field_77109_a.field_78115_e.field_78807_k = false;
      e.renderer.field_77109_a.field_78113_g.field_78807_k = false;
      e.renderer.field_77109_a.field_78112_f.field_78807_k = false;
      e.renderer.field_77109_a.field_78124_i.field_78807_k = false;
      e.renderer.field_77109_a.field_78123_h.field_78807_k = false;
    } 
    if (p.func_70644_a((Potion)PLuckyBlock.RAYMAN)) {
      e.renderer.field_77109_a.field_78115_e.field_78807_k = true;
    } else if (!p.func_70644_a((Potion)PLuckyBlock.GHOST)) {
      e.renderer.field_77109_a.field_78115_e.field_78807_k = false;
    } 
    if (p.func_70644_a((Potion)PLuckyBlock.HIDE_HEAD)) {
      e.renderer.field_77109_a.field_78116_c.field_78807_k = true;
      e.renderer.field_77109_a.field_78114_d.field_78807_k = true;
    } else if (!p.func_70644_a((Potion)PLuckyBlock.HIDE_HEAD)) {
      e.renderer.field_77109_a.field_78116_c.field_78807_k = false;
      e.renderer.field_77109_a.field_78114_d.field_78807_k = false;
    } 
    if (hasPigStuff(p)) {
      ModelPig pig = new ModelPig();
      e.setCanceled(true);
      GL11.glPushMatrix();
      GL11.glTranslated(p.field_70165_t - (Minecraft.func_71410_x()).field_71439_g.field_70165_t, p.field_70163_u - 
          (Minecraft.func_71410_x()).field_71439_g.field_70163_u + (
          (p.func_110124_au() == (Minecraft.func_71410_x()).field_71439_g.func_110124_au()) ? -0.3D : 1.3D), p.field_70161_v - 
          (Minecraft.func_71410_x()).field_71439_g.field_70161_v);
      GL11.glRotated(-p.field_70177_z, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(pigTextures);
      float ticks = p.field_70173_aa + e.partialRenderTick;
      float rotationPitch = p.field_70127_C + (p.field_70125_A - p.field_70127_C) * e.partialRenderTick;
      float f6 = p.field_70722_aY + (p.field_70721_aZ - p.field_70722_aY) * e.partialRenderTick;
      float f7 = p.field_70754_ba - p.field_70721_aZ * (1.0F - e.partialRenderTick);
      pig.field_78091_s = false;
      if (f6 > 1.0F)
        f6 = 1.0F; 
      pig.func_78088_a((Entity)p, f7, f6, ticks, 0.0F, rotationPitch, 0.06F);
      GL11.glPopMatrix();
    } 
    if (p.func_70644_a((Potion)PLuckyBlock.STEVE)) {
      AbstractClientPlayer ab = (AbstractClientPlayer)p;
      (Minecraft.func_71410_x()).field_71446_o.func_110577_a(AbstractClientPlayer.field_110314_b);
    } 
    if (p.func_70644_a((Potion)PLuckyBlock.COW_PLAYER)) {
      ModelCow cow = new ModelCow();
      e.setCanceled(true);
      GL11.glPushMatrix();
      GL11.glTranslated(p.field_70165_t - (Minecraft.func_71410_x()).field_71439_g.field_70165_t, p.field_70163_u - 
          (Minecraft.func_71410_x()).field_71439_g.field_70163_u + (
          (p.func_110124_au() == (Minecraft.func_71410_x()).field_71439_g.func_110124_au()) ? -0.3D : 1.3D), p.field_70161_v - 
          (Minecraft.func_71410_x()).field_71439_g.field_70161_v);
      GL11.glRotated(-p.field_70177_z, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(cowTextures);
      float ticks = p.field_70173_aa + e.partialRenderTick;
      float rotationPitch = p.field_70127_C + (p.field_70125_A - p.field_70127_C) * e.partialRenderTick;
      float f6 = p.field_70722_aY + (p.field_70721_aZ - p.field_70722_aY) * e.partialRenderTick;
      float f7 = p.field_70754_ba - p.field_70721_aZ * (1.0F - e.partialRenderTick);
      cow.field_78091_s = false;
      if (f6 > 1.0F)
        f6 = 1.0F; 
      cow.func_78088_a((Entity)p, f7, f6, ticks, 0.0F, rotationPitch, 0.06F);
      GL11.glPopMatrix();
    } 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.ROTATE) && p
      .func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
      GL11.glPushMatrix();
      GL11.glTranslated(0.0D, -1.5D, 0.0D);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onRenderPlayer(RenderPlayerEvent.Post e) {
    EntityPlayer p = e.entityPlayer;
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.ROTATE) && p
      .func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au()))
      GL11.glPopMatrix(); 
  }
  
  @SubscribeEvent
  public void onLuckyPaintingBreak(PlayerInteractEvent e) {
    if (e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
      List list = e.entityPlayer.field_70170_p.func_72872_a(EntityLuckyPainting.class, 
          AxisAlignedBB.func_72330_a((e.x - 1), (e.y - 1), (e.z - 1), (e.x + 1), (e.y + 1), (e.z + 1)));
      for (Object obj : list) {
        if (obj instanceof EntityLuckyPainting) {
          EntityLuckyPainting entity = (EntityLuckyPainting)obj;
          entity.func_70106_y();
        } 
      } 
    } 
  }
  
  private final ResourceLocation greenFilter = new ResourceLocation("palamod", "textures/gui/LuckyBlock/greenfilter.png");
  
  private final ResourceLocation flouFilter = new ResourceLocation("palamod", "textures/gui/LuckyBlock/floufilter.png");
  
  private final ResourceLocation catBackground = new ResourceLocation("palamod", "textures/gui/LuckyBlock/cat.png");
  
  private final ResourceLocation pinkFilter = new ResourceLocation("palamod", "textures/gui/LuckyBlock/pinkfilter.png");
  
  private final ResourceLocation darkKnightHelmetFilter = new ResourceLocation("palamod", "textures/gui/LuckyBlock/darkknighthelmetfilter.png");
  
  @SubscribeEvent
  public void onLogin(PlayerEvent.PlayerLoggedInEvent e) {
    if (e.player == null)
      return; 
    e.player.func_82170_o(PLuckyBlock.GREEN.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.CAT.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.FLOU.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.STEVE.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.VIEW_TWO_PERSON.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.TREE.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.H4CK3D.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.FLIP.field_76415_H);
    e.player.func_82170_o(PotionsRegister.PINK_EFFECT.getPotionId());
    if (!e.player.field_70170_p.field_72995_K && e.player instanceof EntityPlayerMP) {
      String playerId = FastUUID.toString(e.player.func_110124_au());
      if (UsersManager.getKeepPumpkinBlur().contains(playerId)) {
        PalaMod.getNetwork().sendTo((IMessage)new PacketPumpkinBlur(false), (EntityPlayerMP)e.player);
        UsersManager.getKeepPumpkinBlur().remove(playerId);
      } 
    } 
    if (e.player instanceof EntityPlayerMP && 
      PLuckyBlock.instance.getHalloweenConfig() != null) {
      if (PlayerLuckyBlockProperties.get(e.player).getLastHalloweenTradeUUID() == null && PLuckyBlock.instance.getHalloweenConfig().getCurrentTrade() != null) {
        PlayerLuckyBlockProperties.get(e.player).setLastHalloweenTradeUUID(PLuckyBlock.instance.getHalloweenConfig().getCurrentTrade().getUuid());
        PlayerLuckyBlockProperties.get(e.player).setHalloweenTradeLimit(0);
      } 
      if (PlayerLuckyBlockProperties.get(e.player).getLastHalloweenTradeUUID() != null && PLuckyBlock.instance.getHalloweenConfig().getCurrentTrade() != null && !PlayerLuckyBlockProperties.get(e.player).getLastHalloweenTradeUUID().equals(PLuckyBlock.instance.getHalloweenConfig().getCurrentTrade().getUuid())) {
        PlayerLuckyBlockProperties.get(e.player).setLastHalloweenTradeUUID(PLuckyBlock.instance.getHalloweenConfig().getCurrentTrade().getUuid());
        PlayerLuckyBlockProperties.get(e.player).setHalloweenTradeLimit(0);
      } 
      PalaMod.getNetHandler().sendTo((IMessage)new SCPacketUpdateHalloweenTrade(PLuckyBlock.instance.getHalloweenConfig(), PlayerLuckyBlockProperties.get(e.player).getHalloweenTradeLimit()), (EntityPlayerMP)e.player);
    } 
    ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(), (EntityPlayerMP)e.player);
    if (PlayerLuckyBlockProperties.get(e.player).isLaborDayActive()) {
      long time = System.currentTimeMillis() - PlayerLuckyBlockProperties.get(e.player).getLaborDayDate();
      if (time < 1800000L)
        ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(FeteTravail.class.getName(), 1800000L, PlayerLuckyBlockProperties.get(e.player).getLaborDayDate()), (EntityPlayerMP)e.player); 
    } 
  }
  
  @SubscribeEvent
  public void onDisconnect(PlayerEvent.PlayerLoggedOutEvent e) {
    if (e.player == null)
      return; 
    e.player.func_82170_o(PLuckyBlock.GREEN.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.CAT.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.FLOU.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.STEVE.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.VIEW_TWO_PERSON.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.TREE.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.H4CK3D.field_76415_H);
    e.player.func_82170_o(PLuckyBlock.FLIP.field_76415_H);
    e.player.func_82170_o(PotionsRegister.PINK_EFFECT.getPotionId());
    String playerId = FastUUID.toString((Entity)e.player);
    if (UsersManager.getAlmostCancelFallDamagePlayer().contains(playerId))
      UsersManager.getAlmostCancelFallDamagePlayer().remove(playerId); 
    if (UsersManager.getCancelFallDamagePlayer().contains(playerId))
      UsersManager.getCancelFallDamagePlayer().remove(playerId); 
    if (UsersManager.getDigicodeInit().containsKey(playerId))
      UsersManager.getDigicodeInit().remove(playerId); 
  }
  
  @SubscribeEvent
  public void onDisconnectWithVision(PlayerEvent.PlayerLoggedOutEvent e) {
    if (e.player == null)
      return; 
    if (LuckyBlockManager.getInstance() != null && LuckyBlockManager.getInstance().getVisionHelmetData() != null) {
      Iterator<VisionnedPlayers> i = LuckyBlockManager.getInstance().getVisionHelmetData().iterator();
      while (i.hasNext()) {
        VisionnedPlayers v = i.next();
        if (v != null && v.getSender() != null && v.getVisionned() != null && (v
          .getSender().equalsIgnoreCase(e.player.func_110124_au().toString()) || v
          .getVisionned().equalsIgnoreCase(e.player.func_110124_au().toString())))
          i.remove(); 
      } 
    } 
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void drawGreenFilter(RenderGameOverlayEvent.Post event) {
    drawFilter(event);
  }
  
  @SideOnly(Side.CLIENT)
  private void drawFilter(RenderGameOverlayEvent.Post event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    if (minecraft == null)
      return; 
    EntityClientPlayerMP entity = minecraft.field_71439_g;
    if (!event.type.equals(RenderGameOverlayEvent.ElementType.ALL))
      return; 
    ScaledResolution res = event.resolution;
    if (entity.func_70644_a((Potion)PLuckyBlock.GREEN))
      GuiUtils.drawImageTransparent(0.0D, 0.0D, this.greenFilter, res.func_78326_a(), res.func_78328_b()); 
    if (entity.func_70644_a((Potion)PLuckyBlock.FLOU))
      GuiUtils.drawImageTransparent(0.0D, 0.0D, this.flouFilter, res.func_78326_a(), res.func_78328_b()); 
    if (entity.func_82165_m(PotionsRegister.PINK_EFFECT.getPotionId()))
      GuiUtils.drawImageTransparent(0.0D, 0.0D, this.pinkFilter, res.func_78326_a(), res.func_78328_b()); 
    if (entity.func_70644_a((Potion)PLuckyBlock.CAT)) {
      int dimX = res.func_78326_a() / 2;
      int dimY = (int)(dimX * 1.161217D);
      int posX = res.func_78326_a() / 2 - dimX / 2;
      int posY = res.func_78328_b() / 2 - dimY / 2;
      GuiUtils.drawImageTransparent(posX, posY, this.catBackground, dimX, dimY);
    } 
    ItemStack helmetArmor = entity.func_82169_q(3);
    if (minecraft.field_71474_y.field_74320_O == 0 && helmetArmor != null && helmetArmor.func_77973_b().equals(ItemsRegister.DARK_KNIGHT_HELMET)) {
      GuiUtils.drawImageTransparent(0.0D, 0.0D, this.darkKnightHelmetFilter, res.func_78326_a(), res.func_78328_b());
      if (System.currentTimeMillis() - ClientProxy.lastBreath > 5000L) {
        ClientProxy.lastBreath = System.currentTimeMillis();
        ClientProxy.playSound("vader_breathing");
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerTick(TickEvent.RenderTickEvent e) {
    if ((Minecraft.func_71410_x()).field_71439_g != null) {
      BlockPos pos = new BlockPos((Minecraft.func_71410_x()).field_71439_g.field_70165_t, (Minecraft.func_71410_x()).field_71439_g.field_70163_u, (Minecraft.func_71410_x()).field_71439_g.field_70161_v);
      if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.COMMANDO) && 
        (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147437_c(pos.getX(), pos.getY(), pos
          .getZ()))
        if ((Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147437_c(pos.getX(), pos.getY() - 1, pos
            .getZ())) {
          if (this.renderer == null)
            this.renderer = (EntityRenderer)new CustomPlayerRender(Minecraft.func_71410_x()); 
          if ((Minecraft.func_71410_x()).field_71460_t != this.renderer) {
            this.prevRenderer = (Minecraft.func_71410_x()).field_71460_t;
            (Minecraft.func_71410_x()).field_71460_t = this.renderer;
          } 
          return;
        }  
      if (this.prevRenderer != null && (Minecraft.func_71410_x()).field_71460_t != this.prevRenderer)
        (Minecraft.func_71410_x()).field_71460_t = this.prevRenderer; 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onCommand(CommandEvent e) {
    if (e.sender instanceof EntityPlayerMP && 
      ServerManager.getLabyrinthe().contains(e.sender)) {
      e.sender.func_145747_a((IChatComponent)new ChatComponentText("§cPuisse le sort vous être favorable, mais ici dans ce labyrinthe, la triche n'est point autorisée !"));
      e.setCanceled(true);
    } 
  }
  
  @SubscribeEvent
  public void onDeath(LivingDeathEvent e) {
    if (e.entity instanceof EntityPlayerMP) {
      EntityPlayerMP p = (EntityPlayerMP)e.entity;
      if (ServerManager.getLabyrinthe().contains(p))
        ServerManager.getLabyrinthe().remove(p); 
      String playerId = FastUUID.toString((Entity)p);
      if (!e.entity.field_70170_p.field_72995_K && UsersManager.getKeepPumpkinBlur().contains(playerId)) {
        PalaMod.getNetwork().sendTo((IMessage)new PacketPumpkinBlur(false), p);
        UsersManager.getKeepPumpkinBlur().remove(playerId);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onPlaceSleepingBag(BlockEvent.PlaceEvent e) {
    if (e.block == BlocksRegister.SLEEPING_BAG && !e.world.field_72995_K) {
      PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get(e.player);
      properties.setPositionRespawn(new BlockPos(e.x, e.y, e.z));
      e.player.func_146105_b((IChatComponent)new ChatComponentText("§eVotre point de respawn est désormais ici !"));
    } 
  }
  
  @SubscribeEvent
  public void onSurvivingAttack(AttackEntityEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP && 
      e.entityLiving.func_82165_m(PLuckyBlock.SURVIVING.field_76415_H))
      e.setCanceled(true); 
    if (e.target instanceof EntityPlayerMP && (
      (EntityPlayerMP)e.target).func_82165_m(PLuckyBlock.SURVIVING.field_76415_H))
      e.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onSurvivingDamage(LivingHurtEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP && 
      e.entityLiving.func_82165_m(PLuckyBlock.SURVIVING.field_76415_H))
      e.setCanceled(true); 
    if (e.source.func_76364_f() instanceof EntityPlayerMP && (
      (EntityPlayerMP)e.source.func_76364_f())
      .func_82165_m(PLuckyBlock.SURVIVING.field_76415_H))
      e.setCanceled(true); 
  }
  
  private boolean isUsingGadgeto(TickEvent.PlayerTickEvent event) {
    ItemStack stack;
    Item it;
    return ((stack = event.player.func_70694_bm()) != null && (((
      it = stack.func_77973_b()) != null && stack.func_77973_b() instanceof net.minecraft.item.ItemSpade) || it instanceof net.minecraft.item.ItemPickaxe || it instanceof net.minecraft.item.ItemHoe || it instanceof net.minecraft.item.ItemAxe) && stack
      
      .func_77942_o() && stack.field_77990_d.func_74764_b("gadgeto") && stack.field_77990_d
      .func_74767_n("gadgeto"));
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent e) {
    if (e.player.func_82165_m(PLuckyBlock.SURVIVING.field_76415_H))
      e.player.func_70107_b(e.player.field_70142_S, e.player.field_70137_T, e.player.field_70136_U); 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerTickGadgetoClient(TickEvent.PlayerTickEvent e) {
    if (!e.player.field_70170_p.field_72995_K)
      return; 
    if (isUsingGadgeto(e)) {
      if (ClientProxy.getClientPlayer() == e.player)
        ClientProxy.playerAddedReachDistance = 1.0F; 
    } else {
      resetBlockReachClient(e.player);
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerTickGadgetoServer(TickEvent.PlayerTickEvent e) {
    if (e.player.field_70170_p.field_72995_K)
      return; 
    if (isUsingGadgeto(e)) {
      ((EntityPlayerMP)e.player).field_71134_c.setBlockReachDistance(6.0D);
    } else {
      resetBlockReachServer(e.player);
    } 
  }
  
  @SideOnly(Side.SERVER)
  private void resetBlockReachServer(EntityPlayer player) {
    if (!player.field_70170_p.field_72995_K)
      ((EntityPlayerMP)player).field_71134_c.setBlockReachDistance(5.0D); 
  }
  
  @SideOnly(Side.CLIENT)
  private void resetBlockReachClient(EntityPlayer player) {
    if (ClientProxy.getClientPlayer() == player)
      ClientProxy.playerAddedReachDistance = 0.0F; 
  }
  
  @SubscribeEvent
  public void onTreeMove(TickEvent.PlayerTickEvent e) {
    if (e.player.func_82165_m(PLuckyBlock.TREE.field_76415_H)) {
      EntityPlayer player = e.player;
      int x = (int)player.field_70165_t;
      int z = (int)player.field_70161_v;
      int y = player.field_70170_p.func_72976_f(x, z);
      Block b = player.field_70170_p.func_147439_a(x, y, z);
      if (!EventUtils.canInteract(player, x, y, z))
        return; 
      if (b == Blocks.field_150357_h)
        return; 
      if (e.side == Side.SERVER && 
        WorldGuardUtils.checkFlag(player.field_70170_p, x, y, z, DefaultFlag.ENDERDRAGON_BLOCK_DAMAGE, true))
        return; 
      player.field_70170_p.func_147468_f(x, y, z);
      player.field_70170_p.func_147449_b(x, y, z, Blocks.field_150345_g);
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onRedstoneChatMessage(ServerChatEvent e) {
    if (!e.isCanceled() && !e.message.startsWith("/"))
      LuckyBlockManager.getInstance().addChatMessage(e.message); 
  }
  
  @SubscribeEvent
  public void onConsumerCredit(ServerChatEvent e) {
    final EntityPlayerMP player = e.player;
    if (UsersManager.getConsumerCredit().contains(entityPlayerMP)) {
      e.setCanceled(true);
      try {
        final int credit = Integer.parseInt(e.message);
        if (credit > 10000) {
          PlayerUtils.sendMessage((EntityPlayer)entityPlayerMP, "§cVous ne pouvez pas demander plus que 10.000€");
          return;
        } 
        CresusManager.getInstance().hasAsync(entityPlayerMP.func_110124_au(), credit, new CresusCallback<Boolean>() {
              public void onSuccess(Boolean arg0) {
                if (arg0.booleanValue() == true) {
                  Random r = player.field_70170_p.field_73012_v;
                  if (r.nextBoolean()) {
                    CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), credit, "onConsumerCredit", new CresusCallback<CresusResponse>() {
                          public void onSuccess(CresusResponse arg0) {
                            PlayerUtils.sendMessage(player, "§aVoilà votre virement");
                            UsersManager.getConsumerCredit().remove(player);
                          }
                          
                          public void onFail(CresusResponse arg0, Throwable arg1) {}
                        });
                  } else {
                    CresusManager.getInstance().withdrawPlayerAsync(player.func_110124_au(), credit, "onConsumerCredit", new CresusCallback<CresusResponse>() {
                          public void onSuccess(CresusResponse arg0) {
                            PlayerUtils.sendMessage(player, "§cLes intérêts de la banque étaient un peu trop élevés, ils vous ont débités");
                            UsersManager.getConsumerCredit().remove(player);
                          }
                          
                          public void onFail(CresusResponse arg0, Throwable arg1) {}
                        });
                  } 
                } else {
                  PlayerUtils.sendMessage(player, "§cVous n'avez pas assez d'argent.");
                } 
              }
              
              public void onFail(Boolean arg0, Throwable arg1) {
                PlayerUtils.sendMessage(player, "§cVous n'avez pas assez d'argent.");
              }
            });
      } catch (Exception ex) {
        PlayerUtils.sendMessage((EntityPlayer)entityPlayerMP, "Vous devez entrer un nombre");
      } 
    } 
  }
  
  @SubscribeEvent
  public void onExtremeCold(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving.func_82165_m(PLuckyBlock.EXTREME_COLD.field_76415_H) && e.entityLiving instanceof EntityPlayer && !e.entityLiving.field_70170_p.field_72995_K) {
      BlockPos pos = new BlockPos((Entity)e.entityLiving);
      EntityPlayer player = (EntityPlayer)e.entityLiving;
      if (EventUtils.canInteract(player, pos.getX(), pos.getY(), pos.getZ()) && player.field_70170_p
        .func_147439_a(pos.getX(), pos.getY() - 1, pos.getZ()) != Blocks.field_150350_a && player.field_70170_p
        .func_147439_a(pos.getX(), pos.getY(), pos.getZ()) == Blocks.field_150350_a)
        player.field_70170_p.func_147449_b(pos.getX(), pos.getY(), pos.getZ(), Blocks.field_150431_aC); 
    } 
  }
  
  @SubscribeEvent
  public void onCraft(AnvilUpdateEvent e) {
    ItemStack left = e.left;
    ItemStack right = e.right;
    if (left == null || right == null)
      return; 
    if (left.func_77973_b() == ItemsRegister.LUCKY_SWORD && 
      right.func_77973_b() == Item.func_150898_a((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK)) {
      left.func_82841_c(1);
      ItemStack output = left.func_77946_l();
      output.func_77964_b(output.func_77960_j() - right.field_77994_a);
      if (output.func_77960_j() < 0)
        e.setCanceled(true); 
      e.output = output;
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onKeyPress(InputEvent.KeyInputEvent e) {
    if ((Minecraft.func_71410_x()).field_71439_g.func_82165_m(PLuckyBlock.VIEW_TWO_PERSON.field_76415_H) && Keyboard.isKeyDown(63))
      (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1; 
    if ((Minecraft.func_71410_x()).field_71439_g.func_82165_m(PLuckyBlock.EXPLOSE.field_76415_H) && Keyboard.isKeyDown(42))
      PalaMod.getNetwork().sendToServer((IMessage)new PacketPotionExplose()); 
  }
  
  @SubscribeEvent
  public void onCancelChangView(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving == null)
      return; 
    if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.VIEW_TWO_PERSON) && 
      e.entityLiving instanceof EntityPlayer && 
      e.entityLiving.field_70170_p.field_72995_K)
      (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1; 
  }
  
  @SubscribeEvent
  public void serverTick(TickEvent.ServerTickEvent event) {
    if (LuckyBlockManager.getInstance() != null && this.tick++ == 20) {
      this.tick = 0;
      Runnable run = new Runnable() {
          public void run() {
            LuckyBlockManager.getInstance().updateVisionnedPlayers();
          }
        };
      LuckyBlockManager.executorService.submit(run);
    } 
  }
  
  @SubscribeEvent
  public void onRemoveHeartUpdate(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving instanceof EntityPlayer) {
      EntityPlayer entityPlayer = (EntityPlayer)e.entityLiving;
      if (e.entityLiving.func_70644_a((Potion)PLuckyBlock.REMOVE_HEART)) {
        if (entityPlayer.func_70660_b((Potion)PLuckyBlock.REMOVE_HEART).func_76459_b() > 10) {
          effectPotionRemoveHeart(entityPlayer);
        } else if (entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a)
          .func_111125_b() != 20.0D) {
          entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
          entityPlayer.func_82170_o(PLuckyBlock.REMOVE_HEART.field_76415_H);
          entityPlayer.func_71016_p();
        } 
      } else if (entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a)
        .func_111125_b() != 20.0D) {
        entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        entityPlayer.func_71016_p();
      } 
    } 
  }
  
  private void effectPotionRemoveHeart(EntityPlayer entityPlayer) {
    if (entityPlayer.func_70660_b((Potion)PLuckyBlock.REMOVE_HEART).func_76458_c() == 0) {
      if (entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() != 18.0D) {
        entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(18.0D);
        entityPlayer.func_71016_p();
      } 
    } else if (entityPlayer.func_70660_b((Potion)PLuckyBlock.REMOVE_HEART).func_76458_c() == 1 && 
      entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() != 16.0D) {
      entityPlayer.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(16.0D);
      entityPlayer.func_71016_p();
    } 
  }
  
  @SubscribeEvent
  public void onPlayerEat(PlayerUseItemEvent.Finish event) {
    Item actionItem = event.item.func_77973_b();
    if (actionItem instanceof net.minecraft.item.ItemFood) {
      String playerId = FastUUID.toString((Entity)event.entityPlayer);
      if (actionItem == ItemsRegister.CHOCOLATE_EGG) {
        int currentValue = 0;
        if (UsersManager.getChocolateEggEaten().containsKey(playerId))
          currentValue = ((Integer)UsersManager.getChocolateEggEaten().get(playerId)).intValue(); 
        currentValue++;
        if (currentValue >= 3) {
          event.entityPlayer.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 1200, 1));
          currentValue = 0;
        } 
        UsersManager.getChocolateEggEaten().put(playerId, Integer.valueOf(currentValue));
      } else {
        UsersManager.getChocolateEggEaten().put(playerId, Integer.valueOf(0));
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerTickClient(TickEvent.PlayerTickEvent e) {
    ItemStack stack = e.player.func_70694_bm();
    if (stack != null && stack.func_77978_p() != null && stack.func_77978_p().func_74764_b("TrucQuiCloche")) {
      NBTTagCompound tag = stack.func_77978_p();
      long lastDing = 0L;
      if (!tag.func_74764_b("TrucQuiClocheTime")) {
        lastDing = System.currentTimeMillis();
        tag.func_74772_a("TrucQuiClocheTime", lastDing);
        lastDing -= 3000L;
      } else {
        lastDing = tag.func_74763_f("TrucQuiClocheTime");
      } 
      if (System.currentTimeMillis() - lastDing > 2000L) {
        ClientProxy.playSound("bell");
        tag.func_74772_a("TrucQuiClocheTime", System.currentTimeMillis());
        stack.func_77982_d(tag);
      } 
    } 
    if (e.player.func_70644_a((Potion)PLuckyBlock.GANGNAM_STYLE))
      if (e.player.func_70660_b((Potion)PLuckyBlock.GANGNAM_STYLE).func_76459_b() < 2) {
        (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1;
      } else {
        (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 2;
      }  
  }
  
  private final ResourceLocation h4ck3dBackground = new ResourceLocation("palamod", "textures/gui/LuckyBlock/easter/h4ck3d.png");
  
  private final ResourceLocation h4ck3dBackground2 = new ResourceLocation("palamod", "textures/gui/LuckyBlock/easter/h4ck3d2.png");
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void renderH4ck(RenderGameOverlayEvent event) {
    EntityClientPlayerMP entity = (Minecraft.func_71410_x()).field_71439_g;
    if (entity.func_70644_a((Potion)PLuckyBlock.H4CK3D))
      if (event.isCancelable() && event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
        event.setCanceled(true);
      } else if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
        ScaledResolution res = event.resolution;
        int duration = entity.func_70660_b((Potion)PLuckyBlock.H4CK3D).func_76459_b();
        if (duration > 60)
          if (duration % 10 > 8) {
            GuiUtils.drawImageTransparent(0.0D, 0.0D, this.h4ck3dBackground2, (res.func_78326_a() / 100.0F * 100.0F), (res.func_78328_b() / 100.0F * 100.0F));
          } else {
            GuiUtils.drawImageTransparent(0.0D, 0.0D, this.h4ck3dBackground, (res.func_78326_a() / 100.0F * 100.0F), (res.func_78328_b() / 100.0F * 100.0F));
          }  
      }  
  }
  
  @SubscribeEvent
  public void onPlayerEarnXp(OnPlayerEarnXp event) {
    if (PlayerLuckyBlockProperties.get(event.player).isLaborDayActive()) {
      PlayerLuckyBlockProperties.get(event.player).setLaborDayActive(false);
      ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(false, FeteTravail.class.getName()), (EntityPlayerMP)event.player);
      MonthlyUtils.sendMessage(event.player, new String[] { "§aVous avez échoué 'La fête du travail' !" });
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerTickServer(TickEvent.PlayerTickEvent e) {
    EntityPlayerMP player = (EntityPlayerMP)e.player;
    if (PlayerLuckyBlockProperties.get(e.player).isLaborDayActive()) {
      long l = System.currentTimeMillis() - PlayerLuckyBlockProperties.get(e.player).getLaborDayDate();
      if (l >= 1800000L) {
        PlayerLuckyBlockProperties.get(e.player).setLaborDayActive(false);
        ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(false, FeteTravail.class.getName()), player);
        PlayerUtils.dropItemStack((Entity)e.player, e.player.field_70165_t, e.player.field_70163_u, e.player.field_70161_v, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION));
        MonthlyUtils.sendMessage(e.player, new String[] { "§aVous avez réussi 'La fête du travail' et gagné une potion d'XP !" });
      } 
    } 
    long time = System.currentTimeMillis() - PlayerLuckyBlockProperties.get(e.player).getCreepyStartDate();
    if (time < 300000L)
      if (PlayerLuckyBlockProperties.get(e.player).getCreepySpawnDate() <= System.currentTimeMillis()) {
        EntityCustomCreeper entity = new EntityCustomCreeper(player.field_70170_p, false, true, 0.6F);
        entity.func_94058_c("§b");
        entity.func_70634_a(player.field_70165_t, player.field_70163_u, player.field_70161_v);
        player.field_70170_p.func_72838_d((Entity)entity);
        Creepy.generateNextSpawnDate(player);
      }  
    time = System.currentTimeMillis() - PlayerLuckyBlockProperties.get(e.player).getQuackDate();
    if (time < 1800000L && 
      !player.func_82165_m(PLuckyBlock.QUACK.field_76415_H)) {
      int tickDuration = (int)((1800000L - time) / 50L);
      player.func_70690_d(new PotionEffect(PLuckyBlock.QUACK.field_76415_H, tickDuration));
    } 
    if (player.func_82165_m(PLuckyBlock.FIRE_WALK.field_76415_H))
      if (!LuckyBlockUtils.isInCombat((EntityPlayer)player)) {
        Block posBlock = player.field_70170_p.func_147439_a((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        if (posBlock == Blocks.field_150350_a && EventUtils.canInteract((EntityPlayer)player, player.field_70165_t, player.field_70163_u - 1.0D, player.field_70161_v) && EventUtils.canInteract((EntityPlayer)player, player.field_70165_t, player.field_70163_u, player.field_70161_v))
          player.field_70170_p.func_147449_b((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, (Block)Blocks.field_150480_ab); 
      } else if (player.func_82165_m(Potion.field_76428_l.func_76396_c())) {
        player.func_82170_o(Potion.field_76428_l.func_76396_c());
        player.func_82170_o(PLuckyBlock.FIRE_WALK.field_76415_H);
      }  
    time = System.currentTimeMillis() - PlayerLuckyBlockProperties.get(e.player).getChildTrumpetStartDate();
    if (time < 300000L)
      if (PlayerLuckyBlockProperties.get(e.player).getChildTrumpetSpawnDate() <= System.currentTimeMillis()) {
        Vec3 pos = EventUtils.getBlockInFront((EntityPlayer)player, 2.0D);
        EntityNPC entity = new EntityNPC(player.field_70170_p, "palamod:textures/entity/npc/child_trumpet.png", pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 11000L, 11000L, new ItemStack(ItemsRegister.TRUMPET), true);
        player.field_70170_p.func_72838_d((Entity)entity);
        PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("trumpet"), new NetworkRegistry.TargetPoint(player.field_70170_p.field_73011_w.field_76574_g, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 24.0D));
        EnfantTrompette.generateNextSpawnDate(player);
      }  
    if (player.func_82165_m(PLuckyBlock.POUET_POUET.field_76415_H) && 
      player.func_70660_b((Potion)PLuckyBlock.POUET_POUET).func_76459_b() % 100 == 0)
      PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("pouet_pouet"), player); 
    if (player.func_82165_m(PLuckyBlock.RADIO.field_76415_H)) {
      boolean isInCombat = LuckyBlockUtils.isInCombat((EntityPlayer)player);
      if (player.func_70660_b((Potion)PLuckyBlock.RADIO).func_76459_b() % 10 == 0 && !isInCombat && !player.field_71075_bZ.field_75098_d && !ModuleVanish.getInstance().isVanished()) {
        Random rand = player.func_70681_au();
        double newX = player.field_70165_t + (rand.nextDouble() * 2.0D - 1.0D) * 1.0D * 0.5D;
        double newY = player.field_70163_u;
        double newZ = player.field_70161_v + (rand.nextDouble() * 2.0D - 1.0D) * 1.0D * 0.5D;
        if (player.field_70170_p.func_147437_c((int)newX, (int)newY, (int)newZ) && player.field_70170_p.func_147437_c((int)newX, (int)newY + 1, (int)newZ) && player.field_70170_p.func_147445_c((int)newX, (int)newY - 1, (int)newZ, false))
          player.func_70634_a(newX, newY, newZ); 
      } 
      if (player.func_70660_b((Potion)PLuckyBlock.RADIO).func_76459_b() % 100 == 0 && isInCombat) {
        PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound(null), player);
        player.func_82170_o(PLuckyBlock.RADIO.field_76415_H);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onDamage(LivingHurtEvent event) {
    if (event.entityLiving instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)event.entityLiving;
      String playerId = FastUUID.toString((Entity)player);
      if (DamageSource.field_76379_h == event.source && UsersManager.getAlmostCancelFallDamagePlayer().contains(playerId)) {
        UsersManager.getAlmostCancelFallDamagePlayer().remove(playerId);
        event.setCanceled(true);
        player.func_70606_j(1.0F);
      } 
      if (DamageSource.field_76379_h == event.source && UsersManager.getCancelFallDamagePlayer().contains(playerId)) {
        UsersManager.getCancelFallDamagePlayer().remove(playerId);
        event.setCanceled(true);
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onBlockBreakServer(BlockEvent.BreakEvent event) {
    ServerType serverType = CommonModule.getInstance().getConfig().getServerType();
    if (serverType != ServerType.MINAGE || !PlayerLuckyBlockProperties.get(event.getPlayer()).isNotShabbyActive() || event.block == PWorld.PALADIUM_ORE);
  }
  
  @SubscribeEvent
  public void onInteractWithNPC(EntityInteractEvent e) {
    if (e.target instanceof EntityNPCInterface) {
      EntityNPCInterface val = (EntityNPCInterface)e.target;
      if (val.display.name.contains("Armildir") && !e.entityPlayer.field_70170_p.field_72995_K) {
        EntityPlayerMP playerMP = (EntityPlayerMP)e.entityPlayer;
        (new BlacksmithDialogManager()).startDialog(playerMP);
        e.setCanceled(true);
      } else if (val.display.name.contains("MusicienAmbulant") && !e.entityPlayer.field_70170_p.field_72995_K) {
        EntityPlayerMP playerMP = (EntityPlayerMP)e.entityPlayer;
        (new BuskerDialogManager()).startDialog(playerMP);
        e.setCanceled(true);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onAttackEntity(AttackEntityEvent e) {
    if (e.entityPlayer instanceof EntityPlayerMP && e.target instanceof EntityPlayerMP) {
      EntityPlayerMP target = (EntityPlayerMP)e.target;
      ItemStack item = e.entityPlayer.func_70694_bm();
      if (item != null && item.func_77978_p() != null && item.func_77978_p().func_74764_b("PouetPouet") && 
        System.currentTimeMillis() - item.func_77978_p().func_74763_f("PouetPouetTime") > 900000L) {
        e.setCanceled(true);
        PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("pouet_pouet"), (EntityPlayerMP)e.entityPlayer);
        item.func_77978_p().func_74772_a("PouetPouetTime", System.currentTimeMillis());
        target.func_70690_d(new PotionEffect(PLuckyBlock.POUET_POUET.field_76415_H, 420));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */