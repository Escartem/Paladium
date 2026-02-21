package fr.paladium.palamod.modules.spellsv2.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.java.shortcut.Shortcut;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.palamod.api.KeysRegister;
import fr.paladium.palamod.modules.design.modules.omniscience.ModuleOmniscience;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.entity.EntityEgg;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.gui.GuiFrozen;
import fr.paladium.palamod.modules.spellsv2.gui.GuiSelectSpells;
import fr.paladium.palamod.modules.spellsv2.gui.GuiSpellTiming;
import fr.paladium.palamod.modules.spellsv2.gui.GuiSpells;
import fr.paladium.palamod.modules.spellsv2.network.PlayerSpellsProperties;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientOpenFakeInventory;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpdateClientManager;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpgradeSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseMentalis;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerCancelInertium;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerCancelMentalis;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerMoveEntityGhost;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerUseSpell;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.CustomMovementInput;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.SpellManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class EventsManager {
  private GuiSelectSpells guiSelectSpells;
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
    ClientManager.getActiveSpells().clear();
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onKeyInput(TickEvent.ClientTickEvent e) {
    if (e.phase == TickEvent.Phase.END) {
      if ((Minecraft.func_71410_x()).field_71439_g != null && 
        !((Minecraft.func_71410_x()).field_71439_g.field_71158_b instanceof CustomMovementInput)) {
        ClientProxy.customMovementInput = new CustomMovementInput((Minecraft.func_71410_x()).field_71439_g.field_71158_b);
        (Minecraft.func_71410_x()).field_71439_g.field_71158_b = (MovementInput)ClientProxy.customMovementInput;
      } 
      if (KeysRegister.KEY_SPELL_SELECT.func_151468_f()) {
        ClientManager.setInSelectGui(!ClientManager.isInSelectGui());
        KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74313_G.func_151463_i(), false);
        KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74312_F.func_151463_i(), false);
      } else if (KeysRegister.KEY_SPELL_USE.func_151468_f()) {
        if (ClientManager.getCurrentSpell() >= 0)
          PSpellsV2.network.sendToServer((IMessage)new PacketServerUseSpell(ClientManager.getCurrentSpell(), ClientManager.getSpell(Spells.values()[ClientManager.getCurrentSpell()]).getTier())); 
      } else if (KeysRegister.KEY_SPELL_MENU.func_151468_f()) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiSpells());
      } else if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (Minecraft.func_71410_x()).field_71439_g != null && (
        (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId()))).contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
        if (Shortcut.isKeyDown((Minecraft.func_71410_x()).field_71474_y.field_74351_w.func_151463_i()))
          PSpellsV2.network.sendToServer((IMessage)new PacketServerMoveEntityGhost(0.2D, 0.0D, 0.0D)); 
        if (Shortcut.isKeyDown((Minecraft.func_71410_x()).field_71474_y.field_74368_y.func_151463_i()))
          PSpellsV2.network.sendToServer((IMessage)new PacketServerMoveEntityGhost(-0.2D, 0.0D, 0.0D)); 
        if (Shortcut.isKeyDown((Minecraft.func_71410_x()).field_71474_y.field_74370_x.func_151463_i()))
          PSpellsV2.network.sendToServer((IMessage)new PacketServerMoveEntityGhost(0.0D, 0.0D, 0.2D)); 
        if (Shortcut.isKeyDown((Minecraft.func_71410_x()).field_71474_y.field_74366_z.func_151463_i()))
          PSpellsV2.network.sendToServer((IMessage)new PacketServerMoveEntityGhost(0.0D, 0.0D, -0.2D)); 
        if (Shortcut.isKeyDown((Minecraft.func_71410_x()).field_71474_y.field_74314_A.func_151463_i()))
          PSpellsV2.network.sendToServer((IMessage)new PacketServerMoveEntityGhost(0.0D, 0.2D, 0.0D)); 
        if (Shortcut.isKeyDown((Minecraft.func_71410_x()).field_71474_y.field_74311_E.func_151463_i()))
          PSpellsV2.network.sendToServer((IMessage)new PacketServerMoveEntityGhost(0.0D, -0.2D, 0.0D)); 
      } 
      if (Keyboard.isKeyDown(1) && ClientManager.isInSelectGui())
        ClientManager.setInSelectGui(false); 
      if (KeysRegister.KEY_SPELL_EXIT.func_151468_f()) {
        if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
          (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId()))).contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
          ClientManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), FastUUID.toString((Entity)(Minecraft.func_71410_x()).field_71439_g));
          ClientProxy.spellStarting.remove(Spells.INERTIUM);
          PSpellsV2.network.sendToServer((IMessage)new PacketServerCancelInertium());
        } 
        if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (
          (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId()))).contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
          ClientProxy.spellStarting.remove(Spells.MENTALIS);
          PSpellsV2.network.sendToServer((IMessage)new PacketServerCancelMentalis());
        } 
      } 
      if (Keyboard.isKeyDown(42) && ClientManager.getCurrentSpell() != -1) {
        if (ClientManager.getSpell(Spells.values()[ClientManager.getCurrentSpell()]).isUnlock())
          for (int k = 0; k < 3; k++) {
            if (KeysRegister.KEY_SPELL_USES[k].func_151468_f()) {
              if (ClientManager.getSpellsMacro().isEmpty())
                for (int i = 0; i < 3; i++)
                  ClientManager.getSpellsMacro().add(Integer.valueOf(-1));  
              ClientManager.getSpellsMacro().set(k, Integer.valueOf(ClientManager.getCurrentSpell()));
              ClientManager.saveMacro();
              (Minecraft.func_71410_x()).field_71439_g
                .func_145747_a((IChatComponent)new ChatComponentText("§eVous avez défini §b" + 
                    Spells.values()[ClientManager.getCurrentSpell()].getSpell().getName() + " §ecomme sort numéro " + (k + 1)));
            } 
          }  
      } else {
        for (int k = 0; k < 3; k++) {
          if (KeysRegister.KEY_SPELL_USES[k].func_151468_f())
            if (ClientManager.getSpellsMacro().size() > k && ((Integer)ClientManager.getSpellsMacro().get(k)).intValue() >= 0 && ((Integer)ClientManager.getSpellsMacro().get(k)).intValue() < (Spells.values()).length) {
              PSpellsV2.network
                .sendToServer((IMessage)new PacketServerUseSpell(((Integer)ClientManager.getSpellsMacro().get(k)).intValue(), ClientManager.getSpell(Spells.values()[((Integer)ClientManager.getSpellsMacro().get(k)).intValue()]).getTier()));
            } else {
              (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§cAucun sort défini : §7SHIFT + " + (
                    (KeysRegister.KEY_SPELL_USES[k].func_151463_i() >= 0) ? 
                    Keyboard.getKeyName(KeysRegister.KEY_SPELL_USES[k].func_151463_i()) : "clic souris") + " pour la définir au sort actif."));
            }  
        } 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onRenderPlayer(RenderPlayerEvent.Pre e) {
    if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
      (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId()))).contains(e.entityPlayer.func_110124_au()))
      e.setCanceled(true); 
    if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.OMNISCIENCE.getSpell().getId())) != null && e.entityPlayer instanceof EntityPlayerMP && (
      (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.OMNISCIENCE.getSpell().getId()))).contains(e.entityPlayer.func_110124_au()))
      PlayerUtils.sendActionBar((EntityPlayerMP)e.entityPlayer, "§eFaites clic droit sur un joueur pour voir son inventaire"); 
  }
  
  @SubscribeEvent
  public void onUpdate(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
      if (ServerManager.getMentalis().containsKey(player.func_110124_au())) {
        EntityGhost ghost = (EntityGhost)ServerManager.getMentalis().get(player.func_110124_au());
        ghost.field_70177_z = player.field_70177_z;
        ghost.field_70125_A = player.field_70125_A;
      } 
    } 
  }
  
  @SubscribeEvent
  public void onEntityDeath(LivingDeathEvent e) {
    if (e.entityLiving instanceof EntityGhost) {
      EntityGhost ghost = (EntityGhost)e.entityLiving;
      if (ghost.field_70170_p.field_72995_K)
        return; 
      EntityPlayerMP player = ((EntityGhost)e.entityLiving).getPlayer();
      if (player != null) {
        String uuid = FastUUID.toString((Entity)player);
        if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (
          (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId()))).contains(player.func_110124_au())) {
          ServerManager.removeMentalis(player.func_110124_au());
          ServerManager.removeFreeze(player);
          PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid, false), player);
          ServerManager.removeActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid);
          PSpellsV2.network.sendTo((IMessage)new PacketClientUseMentalis(false, FastUUID.toString((Entity)ghost)), player);
        } 
      } else {
        System.err.println("[Spells][Mentalis] Unable to found a valid EntityPlayer for " + ghost);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onDamage(LivingHurtEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
      String playerUuid = FastUUID.toString((Entity)player);
      if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
        (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId()))).contains(player.func_110124_au())) {
        PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM.getSpell().getId(), playerUuid, false));
        player.func_130014_f_().func_147468_f(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(0)).intValue(), ((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(1)).intValue(), ((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(2)).intValue());
        EventUtils.spawnParticle(player.field_70170_p, "smoke", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 100, 0.10000000149011612D);
        ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), playerUuid);
        ServerManager.removeFreeze(player);
      } 
      if (e.source.func_94541_c() && 
        ServerManager.getActiveSpells().get(Integer.valueOf(Spells.EXPLOSIF.getSpell().getId())) != null && (
        (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.EXPLOSIF.getSpell().getId()))).contains(player.func_110124_au()))
        e.setCanceled(true); 
      if (ServerManager.getMentalis().containsKey(player.func_110124_au())) {
        EntityGhost ghost = (EntityGhost)ServerManager.getMentalis().get(player.func_110124_au());
        if (ghost.func_70089_S())
          ghost.func_70106_y(); 
        ServerManager.removeMentalis(player.func_110124_au());
        ServerManager.removeFreeze(player);
        PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.MENTALIS.getSpell().getId(), playerUuid, false), player);
        ServerManager.removeActiveSpell(Spells.MENTALIS.getSpell().getId(), playerUuid);
        PSpellsV2.network.sendTo((IMessage)new PacketClientUseMentalis(false, FastUUID.toString((Entity)ghost)), player);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onUpdateWithInertium(LivingEvent.LivingUpdateEvent e) {
    if (e.entity instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entity;
      if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
        (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId()))).contains(player.func_110124_au())) {
        if (ServerManager.getFreeze().get(player) == null) {
          ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), FastUUID.toString((Entity)player));
          return;
        } 
        double distX = Math.abs(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(0)).intValue() - player.field_70165_t);
        double distY = Math.abs(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(1)).intValue() - player.field_70163_u);
        double distZ = Math.abs(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(2)).intValue() - player.field_70161_v);
        if (distX > 1.0D || distY > 1.0D || distZ > 1.0D) {
          PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM.getSpell().getId(), FastUUID.toString((Entity)player), false));
          player.func_130014_f_().func_147468_f(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(0)).intValue(), ((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(1)).intValue(), ((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(2)).intValue());
          EventUtils.spawnParticle(player.field_70170_p, "smoke", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 100, 0.10000000149011612D);
          ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), FastUUID.toString((Entity)player));
          ServerManager.removeFreeze(player);
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof EntityPlayer && PlayerSpellsProperties.get((EntityPlayer)e.entity) == null)
      PlayerSpellsProperties.register((EntityPlayer)e.entity); 
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onClientJoin(FMLNetworkEvent.ClientConnectedToServerEvent e) {
    ClientProxy.spellEnding.clear();
    ClientProxy.spellStarting.clear();
  }
  
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent e) {
    if (e.entity instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entity;
      double points = SpellManager.getPoints((EntityPlayer)player);
      int level = 0;
      level = SpellManager.getLevel((EntityPlayer)player);
      if (level < 0)
        level = 0; 
      if (points == 0.0D && !SpellManager.hasLogin((EntityPlayer)player)) {
        points = level;
        SpellManager.setPoints((EntityPlayer)player, points);
        SpellManager.setHasLogin((EntityPlayer)player, true);
      } 
      PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(points, level), player);
      for (Spells spell : Spells.values()) {
        boolean unlock = SpellManager.isUnlock((EntityPlayer)player, spell);
        int tier = SpellManager.getTier((EntityPlayer)player, spell);
        if (tier > 0) {
          SpellManager.setUnlock((EntityPlayer)player, spell, true);
          unlock = true;
        } 
        PSpellsV2.network.sendTo((IMessage)new PacketClientUpgradeSpell(spell.getSpell().getId(), tier, unlock), player);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onInteractEntity(EntityInteractEvent e) {
    if (e.entityPlayer instanceof EntityPlayerMP && e.target instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityPlayer;
      EntityPlayerMP target = (EntityPlayerMP)e.target;
      if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.OMNISCIENCE.getSpell().getId())) == null)
        return; 
      if (((List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.OMNISCIENCE.getSpell().getId()))).contains(player.func_110124_au())) {
        if (MonthlyUtils.hasInvulnerabilityEffect((EntityLivingBase)target))
          return; 
        PSpellsV2.network.sendTo((IMessage)new PacketClientOpenFakeInventory(target.func_70005_c_(), target.field_71071_by.field_70462_a), player);
        ServerManager.removeActiveSpell(Spells.OMNISCIENCE.getSpell().getId(), FastUUID.toString((Entity)player));
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPreRenderOverlay(RenderGameOverlayEvent.Pre e) {
    if (e.type == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
      if (ClientManager.getCurrentSpell() >= 0) {
        Spells current = Spells.values()[ClientManager.getCurrentSpell()];
        if (ClientProxy.spellStarting.containsKey(current)) {
          Duration duration = Duration.between(LocalDateTime.ofEpochSecond(LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, ZoneOffset.ofTotalSeconds(0)), (Temporal)ClientProxy.spellEnding.get(current));
          if (duration.getSeconds() > 0L) {
            new GuiSpellTiming(Minecraft.func_71410_x());
            e.setCanceled(true);
          } 
        } 
      } 
      if (ClientProxy.frozen)
        new GuiFrozen(Minecraft.func_71410_x()); 
    } 
    if (e.type == RenderGameOverlayEvent.ElementType.HOTBAR || e.type == RenderGameOverlayEvent.ElementType.CHAT || e.type == RenderGameOverlayEvent.ElementType.FOOD || e.type == RenderGameOverlayEvent.ElementType.HEALTH || e.type == RenderGameOverlayEvent.ElementType.HEALTHMOUNT)
      e.setCanceled(!ClientProxy.showHotbar); 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(receiveCanceled = true)
  public void onPostRenderOverlay(RenderGameOverlayEvent.Post e) {
    if (e.type == RenderGameOverlayEvent.ElementType.ALL) {
      if (ClientManager.getOmniscience() != null) {
        Map.Entry<String, ItemStack[]> entry = ClientManager.getOmniscience().entrySet().iterator().next();
        String name = entry.getKey();
        ItemStack[] inv = entry.getValue();
        (ModuleOmniscience.getInstance()).inv = inv;
        (ModuleOmniscience.getInstance()).player = name;
      } else {
        (ModuleOmniscience.getInstance()).player = null;
      } 
      if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
        (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId()))).contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
        String s = "§f§b" + Keyboard.getKeyName(KeysRegister.KEY_SPELL_EXIT.func_151463_i()) + " §epour sortir du bloc";
        (Minecraft.func_71410_x()).field_71466_p.func_78276_b(s, e.resolution
            .func_78326_a() / 2 - 
            (Minecraft.func_71410_x()).field_71466_p.func_78256_a(s) / 2, e.resolution
            .func_78328_b() - 70, 1);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
      } 
      if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (
        (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())))
        .contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
        String s = "§f§b" + Keyboard.getKeyName(KeysRegister.KEY_SPELL_EXIT.func_151463_i()) + " §epour revenir à votre corps";
        (Minecraft.func_71410_x()).field_71466_p.func_78276_b(s, e.resolution
            .func_78326_a() / 2 - 
            (Minecraft.func_71410_x()).field_71466_p.func_78256_a(s) / 2, e.resolution
            .func_78328_b() - 80, 1);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
      } 
      if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (
        (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())))
        .contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au()) && 
        ClientManager.getMentalis()
        .containsKey((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
        String s = "§eDistance de votre corps : §b§o" + (int)(Minecraft.func_71410_x()).field_71439_g.func_70032_d(
            (Entity)ClientManager.getMentalis().get((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) + "m";
        (Minecraft.func_71410_x()).field_71466_p.func_78276_b(s, e.resolution
            .func_78326_a() / 2 - 
            (Minecraft.func_71410_x()).field_71466_p.func_78256_a(s) / 2, 
            (int)(e.resolution.func_78328_b() / 10.0F * 8.0F), 1);
      } 
      if (ClientManager.isInSelectGui())
        this.guiSelectSpells = new GuiSelectSpells(Minecraft.func_71410_x()); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void handleScroll(MouseEvent e) {
    if (this.guiSelectSpells != null && ClientManager.isInSelectGui() && e.button == -1) {
      e.setCanceled(true);
      this.guiSelectSpells.handleMouseInput();
    } else if (this.guiSelectSpells != null && ClientManager.isInSelectGui() && e.button == 0) {
      e.setCanceled(true);
      ClientManager.setInSelectGui(false);
      KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74313_G.func_151463_i(), false);
      KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74312_F.func_151463_i(), false);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void renderLivingPre(RenderLivingEvent.Pre e) {
    if (MonthlyUtils.hasInvulnerabilityEffect(e.entity))
      return; 
    if (e.entity.func_70032_d((Entity)(Minecraft.func_71410_x()).field_71439_g) < ((ClientManager.getSpell(Spells.OBSERVATUS).getTier() == 1) ? 16 : 32) && ClientManager.getSpell(Spells.OBSERVATUS).getTier() < Spells.OBSERVATUS.getSpell().getMaxTiers()) {
      if (ClientManager.getActiveSpells().get(Integer.valueOf(Spells.OBSERVATUS.getSpell().getId())) != null && (
        (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.OBSERVATUS.getSpell().getId()))).contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au()))
        GL11.glDisable(2929); 
    } else if (ClientManager.getSpell(Spells.OBSERVATUS).getTier() == Spells.OBSERVATUS.getSpell().getMaxTiers() && ClientManager.getActiveSpells().get(Integer.valueOf(Spells.OBSERVATUS.getSpell().getId())) != null && (
      (List)ClientManager.getActiveSpells().get(Integer.valueOf(Spells.OBSERVATUS.getSpell().getId()))).contains((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
      GL11.glDisable(2929);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void renderEntity(RenderLivingEvent.Post e) {
    if (GL11.glIsEnabled(2929))
      return; 
    GL11.glEnable(2929);
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onSound(PlaySoundEvent17 e) {
    if ((Minecraft.func_71410_x()).field_71439_g != null) {
      double x = (Minecraft.func_71410_x()).field_71439_g.field_70165_t;
      double y = (Minecraft.func_71410_x()).field_71439_g.field_70163_u;
      double z = (Minecraft.func_71410_x()).field_71439_g.field_70161_v;
      for (Map<List<Double>, Integer> muted : (Iterable<Map<List<Double>, Integer>>)ClientManager.getMuted()) {
        for (List<Double> coord : muted.keySet()) {
          int dist = 5 + ((Integer)muted.get(coord)).intValue() * 5;
          if (Math.abs(x - ((Double)coord.get(0)).doubleValue()) < dist && Math.abs(y - ((Double)coord.get(1)).doubleValue()) < dist && Math.abs(z - ((Double)coord.get(2)).doubleValue()) < dist) {
            e.manager.field_148622_c.func_147683_b(e.sound);
            e.result = null;
            (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72869_a("note", x, y - 1.0D, z, 0.0D, 0.0D, 0.0D);
          } 
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      PlayerSpellsProperties.get(e.original).saveNBTData(compound);
      PlayerSpellsProperties.get(e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onDisconnect(PlayerEvent.PlayerLoggedOutEvent e) {
    String uuid = FastUUID.toString((Entity)e.player);
    if (ServerManager.getMentalis().containsKey(e.player.func_110124_au())) {
      EntityGhost ghost = (EntityGhost)ServerManager.getMentalis().get(e.player.func_110124_au());
      if (ghost.func_70089_S())
        ghost.func_70106_y(); 
      ServerManager.removeMentalis(e.player.func_110124_au());
      ServerManager.removeFreeze((EntityPlayerMP)e.player);
      PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid, false), (EntityPlayerMP)e.player);
      ServerManager.removeActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid);
      PSpellsV2.network.sendTo((IMessage)new PacketClientUseMentalis(false, FastUUID.toString((Entity)ghost)), (EntityPlayerMP)e.player);
    } 
    if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
      (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId()))).contains(e.player.func_110124_au())) {
      PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid, false));
      if (e.player instanceof EntityPlayerMP) {
        EntityPlayerMP mp = (EntityPlayerMP)e.player;
        if (ServerManager.getFreeze().containsKey(mp) && e.player.func_130014_f_() != null)
          e.player.func_130014_f_().func_147468_f(((Integer)((List<Integer>)ServerManager.getFreeze().get(mp)).get(0)).intValue(), ((Integer)((List<Integer>)ServerManager.getFreeze().get(mp)).get(1)).intValue(), ((Integer)((List<Integer>)ServerManager.getFreeze().get(mp)).get(2)).intValue()); 
      } 
      EventUtils.spawnParticle(e.player.field_70170_p, "smoke", (int)e.player.field_70165_t, (int)e.player.field_70163_u, (int)e.player.field_70161_v, 100, 0.10000000149011612D);
      ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), FastUUID.toString((Entity)e.player));
      ServerManager.removeFreeze((EntityPlayerMP)e.player);
    } 
  }
  
  @SubscribeEvent
  public void onPlayerEarnJobXp(OnPlayerLevelUp e) {
    for (int i = 0; i < e.levelAfter - e.levelBefore; i++) {
      SpellManager.setPoints(e.player, SpellManager.getPoints(e.player) + 1.0D);
      if (e.player instanceof EntityPlayerMP) {
        int level = 0;
        level = SpellManager.getLevel(e.player);
        if (level < 0)
          level = 0; 
        PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(SpellManager.getPoints(e.player), level), (EntityPlayerMP)e.player);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onEggDeath(LivingDeathEvent e) {
    if (e.entityLiving instanceof EntityEgg) {
      EntityEgg egg = (EntityEgg)e.entityLiving;
      EntityPlayerMP player = (EntityPlayerMP)egg.getOwner();
      if (player != null) {
        ServerManager.removeActiveSpell(Spells.EGG.getSpell().getId(), FastUUID.toString((Entity)player));
        ((EntityEgg)ServerManager.getEggs().get(player)).func_70106_y();
        ServerManager.removeFreeze(player);
        ServerManager.getEggs().remove(player);
        EventUtils.spawnParticle(player.field_70170_p, "instantSpell", player.field_70165_t, player.field_70163_u, player.field_70161_v, 200, 0.3D);
        player.func_82142_c(false);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onEggKnock(LivingAttackEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
      if (ServerManager.isSpellActive(Spells.EGG.getSpell().getId(), player) && ServerManager.getEggs().get(player) == null) {
        float nextHealth = player.func_110143_aJ();
        nextHealth -= e.ammount;
        if (nextHealth <= 0.0F) {
          e.setCanceled(true);
          player.func_70606_j(0.5F);
          int tier = SpellManager.getTier((EntityPlayer)player, Spells.EGG);
          int h = (tier == 3) ? 200 : ((tier == 2) ? 100 : 50);
          EntityEgg egg = new EntityEgg(player.field_70170_p);
          egg.func_70634_a(player.field_70165_t, player.field_70163_u, player.field_70161_v);
          egg.setOwner((EntityPlayer)player);
          egg.func_70606_j(h);
          player.func_82142_c(true);
          player.field_70170_p.func_72838_d((Entity)egg);
          ServerManager.getEggs().put(player, egg);
          ServerManager.addFreeze(player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
        } 
      } 
      if (ServerManager.isSpellActive(Spells.EGG.getSpell().getId(), player) && ServerManager.getEggs().get(player) != null && !player.field_70170_p.field_72995_K) {
        e.setCanceled(true);
        float h = ((EntityEgg)ServerManager.getEggs().get(player)).func_110143_aJ();
        h -= e.ammount;
        ((EntityEgg)ServerManager.getEggs().get(player)).func_70606_j(h);
        if (h < 0.0F) {
          ServerManager.removeActiveSpell(Spells.EGG.getSpell().getId(), FastUUID.toString((Entity)player));
          ((EntityEgg)ServerManager.getEggs().get(player)).func_70106_y();
          ServerManager.removeFreeze(player);
          ServerManager.getEggs().remove(player);
          EventUtils.spawnParticle(player.field_70170_p, "instantSpell", player.field_70165_t, player.field_70163_u, player.field_70161_v, 200, 0.3D);
        } else {
          EventUtils.spawnParticle(player.field_70170_p, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10, 0.2D);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */