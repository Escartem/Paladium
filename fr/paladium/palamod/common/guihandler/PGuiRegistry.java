package fr.paladium.palamod.common.guihandler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.paladium.palaforgeutils.lib.guihandler.CustomGuiHandler;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.alchimiste.client.ui.UIEnchant;
import fr.paladium.palamod.modules.alchimiste.client.ui.UIReadableEnchant;
import fr.paladium.palamod.modules.chisel.inventory.ChiselInventory;
import fr.paladium.palamod.modules.chisel.ui.UIChisel;
import fr.paladium.palamod.modules.hunter.command.BackpackCommand;
import fr.paladium.palamod.modules.hunter.gui.containers.AdminInventoryBackpack;
import fr.paladium.palamod.modules.hunter.gui.containers.ContainerBackPack;
import fr.paladium.palamod.modules.hunter.gui.containers.InventoryBackPack;
import fr.paladium.palamod.modules.hunter.items.ItemHunterBackpack;
import fr.paladium.palamod.modules.hunter.networks.PlayerBackPackProperties;
import fr.paladium.palamod.modules.hunter.ui.UIBackPack;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui.UISuperCraftingTableContainer;
import fr.paladium.palamod.modules.luckyblock.gui.GuiChatDetector;
import fr.paladium.palamod.modules.luckyblock.gui.GuiDyeingMachine;
import fr.paladium.palamod.modules.luckyblock.gui.GuiOneMillionPlayer;
import fr.paladium.palamod.modules.luckyblock.gui.GuiStringTrap;
import fr.paladium.palamod.modules.luckyblock.gui.GuiVoyant;
import fr.paladium.palamod.modules.luckyblock.gui.GuiWishCard;
import fr.paladium.palamod.modules.luckyblock.gui.UILuckyBlock;
import fr.paladium.palamod.modules.luckyblock.gui.christmas.ContainerChristmasSantaNoescrocTrade;
import fr.paladium.palamod.modules.luckyblock.gui.christmas.UIChristmasSantaNoescrocTrade;
import fr.paladium.palamod.modules.luckyblock.gui.halloween.UIHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.gui.halloween.UIHalloweenTradeClose;
import fr.paladium.palamod.modules.luckyblock.gui.june.UIAlarmClock;
import fr.paladium.palamod.modules.luckyblock.gui.luckypass.UILuckyPass;
import fr.paladium.palamod.modules.luckyblock.gui.may.UIBookseller;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.containers.GuiCrabCobbleBreaker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityCrabCobbleBreaker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.gui.GuiCorruptedEnchantment;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityChatDetector;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDyeingMachine;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOff;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import fr.paladium.palamod.modules.luckyblock.ui.UIBruteforcer;
import fr.paladium.palamod.modules.luckyblock.ui.UIChunkAnalyser;
import fr.paladium.palamod.modules.luckyblock.ui.UISafeChestLocked;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBoxSendAdminMail;
import fr.paladium.palamod.modules.mailbox.common.containers.MailboxSendAdminMailInventory;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityAutoCrafter;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityDrawBridge;
import fr.paladium.palamod.modules.miner.ui.UIAutoCrafter;
import fr.paladium.palamod.modules.miner.ui.UIDrawBridge;
import fr.paladium.palamod.modules.paladium.client.gui.GuiOnlineDetector;
import fr.paladium.palamod.modules.paladium.client.ui.UIAlchemyCreatorArrow;
import fr.paladium.palamod.modules.paladium.client.ui.UIAlchemyCreatorPotion;
import fr.paladium.palamod.modules.paladium.client.ui.UIBowMachine;
import fr.paladium.palamod.modules.paladium.client.ui.UIChestExplorer;
import fr.paladium.palamod.modules.paladium.client.ui.UICobbleBreaker;
import fr.paladium.palamod.modules.paladium.client.ui.UICrusher;
import fr.paladium.palamod.modules.paladium.client.ui.UIFlowerFarm;
import fr.paladium.palamod.modules.paladium.client.ui.UIModdedChest;
import fr.paladium.palamod.modules.paladium.client.ui.UIPalaForge;
import fr.paladium.palamod.modules.paladium.client.ui.UIPalaFurnace;
import fr.paladium.palamod.modules.paladium.client.ui.UIPalaMachine;
import fr.paladium.palamod.modules.paladium.client.ui.UIScanStick;
import fr.paladium.palamod.modules.paladium.client.ui.UISkin;
import fr.paladium.palamod.modules.paladium.client.ui.UIVoidStone;
import fr.paladium.palamod.modules.paladium.common.container.ContainerEmpty;
import fr.paladium.palamod.modules.paladium.common.inventory.ChestExplorerInventory;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import fr.paladium.palamod.modules.paladium.common.logics.BowMachineLogic;
import fr.paladium.palamod.modules.paladium.common.logics.ForgeLogic;
import fr.paladium.palamod.modules.paladium.common.logics.ModdedChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumFurnaceLogic;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumMachineLogic;
import fr.paladium.palamod.modules.paladium.common.logics.TileCobbleBreaker;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityOnlineDetector;
import fr.paladium.palamod.modules.shop.client.ui.UIShop;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;
import fr.paladium.palamod.modules.smeltery.ui.UiGrinder;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumCache;
import fr.paladium.palamod.modules.trixium.gui.container.UITrixiumContainer;
import fr.paladium.palavanillagui.client.ui.anvil.UIAnvil;
import fr.paladium.palavanillagui.client.ui.chest.UIChest;
import fr.paladium.palavanillagui.common.container.ContainerAnvil;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class PGuiRegistry {
  private static CustomGuiHandler guiHandler;
  
  public static int GUI_PALADIUM_MACHINE;
  
  public static int GUI_VOIDSTONE;
  
  public static int GUI_CHESTEXPLORER;
  
  public static int GUI_MODDED_CHEST;
  
  public static int GUI_ALCHEMY_CREATOR_POTION;
  
  public static int GUI_ALCHEMY_CREATOR_ARROW;
  
  public static int GUI_PALADIUM_FURNACE;
  
  public static int GUI_BOW_MACHINE;
  
  public static int GUI_ONLINE_DETECTOR;
  
  public static int GUI_FORGE;
  
  public static int GUI_COBBLEBREAKER;
  
  public static int GUI_CRUSHER;
  
  public static int GUI_BACKPACK;
  
  public static int GUI_ADMIN_BACKPACK;
  
  public static int GUI_GRINDER;
  
  public static int GUI_CHISEL_ID;
  
  public static int GUI_ENCHANTMENT_TABLE;
  
  public static int GUI_PALADIUM_ARMOR;
  
  public static int GUI_LUCKY_BLOCK;
  
  public static int GUI_CHUNK_ANALYSER;
  
  public static int GUI_DYEING_MACHINE;
  
  public static int GUI_LUCKY_PASS;
  
  public static int GUI_STICK_MODERATION;
  
  public static int GUI_DRAWBRIDGE;
  
  public static int GUI_ANVIL;
  
  public static int GUI_AUTOCRAFTER;
  
  public static int GUI_TRIXIUM;
  
  public static int GUI_SHOP;
  
  public static int GUI_SAFE_CHEST;
  
  public static int GUI_SAFE_CHEST_LOCKED;
  
  public static int GUI_BRUTEFORCER;
  
  public static int GUI_WISH_CARD;
  
  public static int GUI_MAILB;
  
  public static int GUI_MAILB_WRITE_ADMIN;
  
  public static int GUI_ONE_MILLION_PLAYER;
  
  public static int GUI_LUCKY_PASS_BYPASS;
  
  public static int GUI_VOYANT;
  
  public static int GUI_CHAT_DETECTOR;
  
  public static int GUI_SUPER_CRAFTING_TABLE;
  
  public static int GUI_STRING_TRAP;
  
  public static int GUI_HALLOWEEN_CLOSE;
  
  public static int GUI_HALLOWEEN_TRADE;
  
  public static int GUI_CHRISTMAS_SANTA_NOESCROC_TRADE;
  
  public static int GUI_BOOKSELLER;
  
  public static int GUI_ALARMCLOCK;
  
  public static int GUI_CRAB_COBBLEBREAKER;
  
  public static int GUI_CORRUPTED_ENCHANTMENT_TABLE;
  
  public static int GUI_READABLE_ENCHANTMENT_TABLE;
  
  public static int GUI_FLOWER_FARM;
  
  public static void init() {
    guiHandler = new CustomGuiHandler();
    NetworkRegistry.INSTANCE.registerGuiHandler(PalaMod.instance, (IGuiHandler)guiHandler);
    register();
  }
  
  private static void register() {
    GUI_PALADIUM_MACHINE = register(fr.paladium.palamod.modules.paladium.common.container.PaladiumMachineContainer::new, data -> ZUI.open((UI)new UIPalaMachine(data.<PaladiumMachineLogic>getTileEntity(PaladiumMachineLogic.class))));
    GUI_VOIDSTONE = register(fr.paladium.palamod.modules.paladium.common.container.VoidStoneContainer::new, data -> ZUI.open((UI)new UIVoidStone()));
    GUI_CHESTEXPLORER = register(fr.paladium.palamod.modules.paladium.common.container.ChestExplorerContainer::new, data -> ZUI.open((UI)new UIChestExplorer(data.getTileEntity(TileEntity.class), new ChestExplorerInventory())));
    GUI_MODDED_CHEST = register(fr.paladium.palamod.modules.paladium.common.container.ModdedChestContainer::new, data -> ZUI.open((UI)new UIModdedChest(data.<ModdedChestLogic>getTileEntity(ModdedChestLogic.class))));
    GUI_ALCHEMY_CREATOR_POTION = register(fr.paladium.palamod.modules.paladium.common.container.AlchemyCreatorPotionContainer::new, data -> ZUI.open((UI)new UIAlchemyCreatorPotion(data.<AlchemyCreatorLogic>getTileEntity(AlchemyCreatorLogic.class))));
    GUI_ALCHEMY_CREATOR_ARROW = register(fr.paladium.palamod.modules.paladium.common.container.AlchemyCreatorArrowContainer::new, data -> ZUI.open((UI)new UIAlchemyCreatorArrow(data.<AlchemyCreatorLogic>getTileEntity(AlchemyCreatorLogic.class))));
    GUI_PALADIUM_FURNACE = register(fr.paladium.palamod.modules.paladium.common.container.PaladiumFurnaceContainer::new, data -> ZUI.open((UI)new UIPalaFurnace(data.<PaladiumFurnaceLogic>getTileEntity(PaladiumFurnaceLogic.class))));
    GUI_BOW_MACHINE = register(fr.paladium.palamod.modules.paladium.common.container.BowMachineContainer::new, data -> ZUI.open((UI)new UIBowMachine(data.<BowMachineLogic>getTileEntity(BowMachineLogic.class))));
    GUI_ONLINE_DETECTOR = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new GuiOnlineDetector(data.<TileEntityOnlineDetector>getTileEntity(TileEntityOnlineDetector.class)));
    GUI_FORGE = register(fr.paladium.palamod.modules.paladium.common.container.ForgeContainer::new, data -> ZUI.open((UI)new UIPalaForge(data.<ForgeLogic>getTileEntity(ForgeLogic.class))));
    GUI_COBBLEBREAKER = register(fr.paladium.palamod.modules.paladium.common.container.ContainerCobbleBreaker::new, data -> ZUI.open((UI)new UICobbleBreaker(data.<TileCobbleBreaker>getTileEntity(TileCobbleBreaker.class), data.getPlayer())));
    GUI_CRUSHER = register(fr.paladium.palamod.modules.paladium.common.container.CrusherContainer::new, data -> ZUI.open((UI)new UICrusher(data.<TileCrusher>getTileEntity(TileCrusher.class))));
    GUI_BACKPACK = register(data -> {
          EntityPlayer player = data.getPlayer();
          int size = 0;
          String inventoryName = "backpack.endium";
          if (player.func_70694_bm() != null) {
            if (player.func_70694_bm().func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSuitcase) {
              size = 27;
              inventoryName = "suitcase";
            } else if (player.func_70694_bm().func_77973_b() instanceof ItemHunterBackpack) {
              inventoryName = ItemHunterBackpack.getUnlocalizedContainerTitle(player.func_70694_bm());
              if (player.func_70694_bm().func_77960_j() == 0) {
                size = 9;
              } else if (player.func_70694_bm().func_77960_j() == 1) {
                size = 27;
              } else if (player.func_70694_bm().func_77960_j() == 2) {
                size = 54;
              } else if (player.func_70694_bm().func_77960_j() == 3) {
                size = 81;
              } 
            } 
            return (Container)new ContainerBackPack(data.getInventory(), new InventoryBackPack(size, player, inventoryName));
          } 
          if (size == 0 && PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "palamod.command.backpack")) {
            Optional<IJobsPlayer> optJobplayer = PalaJobsAPI.inst().getJobsPlayer(player);
            if (!optJobplayer.isPresent()) {
              size = 9;
            } else {
              int farmerLevel = ((IJobsPlayer)optJobplayer.get()).getLevel(JobType.FARMER);
              size = (farmerLevel < 6) ? 9 : ((farmerLevel < 11) ? 27 : ((farmerLevel < 15) ? 54 : 81));
              inventoryName = (farmerLevel < 6) ? "backpack.amethyste" : ((farmerLevel < 11) ? "backpack.titane" : ((farmerLevel < 15) ? "backpack.paladium" : "backpack.endium"));
            } 
            return (Container)new ContainerBackPack(data.getInventory(), new InventoryBackPack(size, player, inventoryName));
          } 
          return null;
        }data -> {
          EntityPlayer player = data.getPlayer();
          int size = 0;
          String inventoryName = "backpack.endium";
          if (player.func_70694_bm() != null) {
            if (player.func_70694_bm().func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSuitcase) {
              size = 27;
              inventoryName = "suitcase";
            } else if (player.func_70694_bm().func_77973_b() instanceof ItemHunterBackpack) {
              inventoryName = ItemHunterBackpack.getUnlocalizedContainerTitle(player.func_70694_bm());
              if (player.func_70694_bm().func_77960_j() == 0) {
                size = 9;
              } else if (player.func_70694_bm().func_77960_j() == 1) {
                size = 27;
              } else if (player.func_70694_bm().func_77960_j() == 2) {
                size = 54;
              } else if (player.func_70694_bm().func_77960_j() == 3) {
                size = 81;
              } 
            } 
            return ZUI.open((UI)new UIBackPack(player.field_71071_by, new InventoryBackPack(size, player, inventoryName)));
          } 
          if (size == 0 && PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "palamod.command.backpack")) {
            Optional<IJobsPlayer> optJobplayer = PalaJobsAPI.inst().getJobsPlayer(player);
            if (!optJobplayer.isPresent()) {
              size = 9;
            } else {
              int farmerLevel = ((IJobsPlayer)optJobplayer.get()).getLevel(JobType.FARMER);
              size = (farmerLevel < 6) ? 9 : ((farmerLevel < 11) ? 27 : ((farmerLevel < 15) ? 54 : 81));
              inventoryName = (farmerLevel < 6) ? "backpack.amethyste" : ((farmerLevel < 11) ? "backpack.titane" : ((farmerLevel < 15) ? "backpack.paladium" : "backpack.endium"));
            } 
            return ZUI.open((UI)new UIBackPack(player.field_71071_by, new InventoryBackPack(size, player, inventoryName)));
          } 
          return null;
        });
    GUI_ADMIN_BACKPACK = register(data -> {
          if (!PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)data.getPlayer()), "palamod.command.backpack.other"))
            return null; 
          EntityPlayerMP entityPlayerMP = BackpackCommand.getBackpackTarget(data.getPlayer().func_110124_au());
          if (entityPlayerMP != null) {
            ItemStack[] content = PlayerBackPackProperties.get((EntityPlayer)entityPlayerMP).getContent();
            ItemStack[] copiedContent = new ItemStack[content.length];
            for (int i = 0; i < content.length; i++)
              copiedContent[i] = (content[i] == null) ? null : content[i].func_77946_l(); 
            return (Container)new ContainerBackPack(data.getInventory(), (InventoryBackPack)new AdminInventoryBackpack(81, data.getPlayer(), copiedContent));
          } 
          return null;
        }data -> {
          EntityPlayer player = data.getPlayer();
          int size = 81;
          String inventoryName = "backpack.endium";
          return !PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "palamod.command.backpack.other") ? null : ZUI.open((UI)new UIBackPack(player.field_71071_by, (InventoryBackPack)new AdminInventoryBackpack(81, player, "backpack.endium")));
        });
    GUI_GRINDER = register(fr.paladium.palamod.modules.smeltery.inventory.GrinderContainer::new, data -> ZUI.open((UI)new UiGrinder(data.<GrinderLogic>getTileEntity(GrinderLogic.class))));
    GUI_CHISEL_ID = register(fr.paladium.palamod.modules.chisel.inventory.ChiselContainer::new, data -> ZUI.open((UI)new UIChisel(new ChiselInventory(null))));
    GUI_ENCHANTMENT_TABLE = register(fr.paladium.palamod.modules.alchimiste.common.container.ContainerEnchantment::new, data -> ZUI.open((UI)new UIEnchant()));
    GUI_PALADIUM_ARMOR = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> ZUI.open((UI)new UISkin()));
    GUI_LUCKY_BLOCK = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new UILuckyBlock(data.getX(), data.getY(), data.getZ()));
    GUI_CHUNK_ANALYSER = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> ZUI.open((UI)new UIChunkAnalyser()));
    GUI_DYEING_MACHINE = register(fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerDyeingMachine::new, data -> new GuiDyeingMachine(data.getInventory(), data.<TileEntityDyeingMachine>getTileEntity(TileEntityDyeingMachine.class)));
    GUI_LUCKY_PASS = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new UILuckyPass());
    GUI_STICK_MODERATION = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> ZUI.open((UI)new UIScanStick()));
    GUI_DRAWBRIDGE = register(fr.paladium.palamod.modules.miner.container.ContainerDrawBridge::new, data -> ZUI.open((UI)new UIDrawBridge(data.<TileEntityDrawBridge>getTileEntity(TileEntityDrawBridge.class))));
    GUI_ANVIL = register(data -> new ContainerAnvil(data.getInventory(), data.getWorld(), data.getX(), data.getY(), data.getZ()), data -> ZUI.open((UI)new UIAnvil(new ContainerAnvil(data.getInventory(), data.getWorld(), data.getX(), data.getY(), data.getZ()))));
    GUI_AUTOCRAFTER = register(fr.paladium.palamod.modules.miner.container.ContainerAutoCrafter::new, data -> ZUI.open((UI)new UIAutoCrafter(data.<TileEntityAutoCrafter>getTileEntity(TileEntityAutoCrafter.class))));
    GUI_TRIXIUM = register(fr.paladium.palamod.modules.trixium.gui.container.ContainerTrixium::new, data -> new UITrixiumContainer(UITrixiumCache.trixium, UITrixiumCache.rewards, UITrixiumCache.scroll, UITrixiumCache.scrollTarget));
    GUI_SHOP = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> ZUI.open((UI)new UIShop()));
    GUI_SAFE_CHEST = register(fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerSafeChest::new, data -> ZUI.open((UI)new UIChest((Container)new ContainerChest((IInventory)data.getInventory(), (IInventory)data.getTileEntity(TileEntitySafeChest.class)))));
    GUI_SAFE_CHEST_LOCKED = register(fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerSafeChestLocked::new, data -> ZUI.open((UI)new UISafeChestLocked(data.<TileEntitySafeChest>getTileEntity(TileEntitySafeChest.class), data.getPlayer())));
    GUI_BRUTEFORCER = register(fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerBruteforcer::new, data -> ZUI.open((UI)new UIBruteforcer(data.<TileEntityBruteforcer>getTileEntity(TileEntityBruteforcer.class))));
    GUI_WISH_CARD = register(fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerWishCard::new, data -> new GuiWishCard(data.getInventory()));
    GUI_MAILB = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> ZUI.open((UI)new ZUIMailBox()));
    GUI_MAILB_WRITE_ADMIN = register(fr.paladium.palamod.modules.mailbox.common.containers.MailboxSendAdminMailContainer::new, data -> ZUI.open((UI)new ZUIMailBoxSendAdminMail(new MailboxSendAdminMailInventory())));
    GUI_ONE_MILLION_PLAYER = register(data -> {
          PlayerLuckyPassProperties.get(data.getPlayer()).setHasRemoveMoneyNow(true);
          return null;
        }data -> new GuiOneMillionPlayer());
    GUI_LUCKY_PASS_BYPASS = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new UILuckyPass());
    GUI_VOYANT = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new GuiVoyant(data.getPlayer()));
    GUI_CHAT_DETECTOR = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new GuiChatDetector(data.<TileEntityChatDetector>getTileEntity(TileEntityChatDetector.class)));
    GUI_SUPER_CRAFTING_TABLE = register(fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ContainerSuperCraftingTable::new, data -> new UISuperCraftingTableContainer(null, data.getInventory(), data.getWorld(), data.getX(), data.getY(), data.getZ()));
    GUI_STRING_TRAP = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new GuiStringTrap(data.<TileEntityStringTrapOff>getTileEntity(TileEntityStringTrapOff.class)));
    GUI_HALLOWEEN_CLOSE = register(data -> new ContainerEmpty(), data -> new UIHalloweenTradeClose());
    GUI_HALLOWEEN_TRADE = register(fr.paladium.palamod.modules.luckyblock.gui.halloween.ContainerHalloweenTrade::new, data -> new UIHalloweenTrade(data.getPlayer(), data.getWorld(), data.getX(), data.getY(), data.getZ(), ClientProxy.configHalloween));
    GUI_CHRISTMAS_SANTA_NOESCROC_TRADE = register(data -> new ContainerChristmasSantaNoescrocTrade(), data -> new UIChristmasSantaNoescrocTrade(data.getPlayer(), (Minecraft.func_71410_x()).field_71462_r, "Père Noescroc", (Container)new ContainerChristmasSantaNoescrocTrade()));
    GUI_BOOKSELLER = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new UIBookseller());
    GUI_ALARMCLOCK = register((Function<SimpleGHandler.GuiHandlerData, Container>)null, data -> new UIAlarmClock(data.<TileEntityAlarmClock>getTileEntity(TileEntityAlarmClock.class)));
    GUI_CRAB_COBBLEBREAKER = register(fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.containers.ContainerCrabCobbleBreaker::new, data -> new GuiCrabCobbleBreaker(data.<TileEntityCrabCobbleBreaker>getTileEntity(TileEntityCrabCobbleBreaker.class), data.getInventory(), data.getPlayer()));
    GUI_CORRUPTED_ENCHANTMENT_TABLE = register(fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.containers.ContainerCorruptedEnchantment::new, data -> new GuiCorruptedEnchantment(data.getInventory(), data.getWorld(), data.getX(), data.getY(), data.getZ()));
    GUI_READABLE_ENCHANTMENT_TABLE = register(fr.paladium.palamod.modules.alchimiste.common.container.ContainerReadableEnchantment::new, data -> ZUI.open((UI)new UIReadableEnchant(data)));
    GUI_FLOWER_FARM = register(fr.paladium.palamod.modules.paladium.common.container.FlowerFarmContainer::new, data -> ZUI.open((UI)new UIFlowerFarm(data)));
  }
  
  public static int register(GHandler handler) {
    return guiHandler.registerHandler(handler);
  }
  
  public static int register(Function<SimpleGHandler.GuiHandlerData, Container> server, Function<SimpleGHandler.GuiHandlerData, Object> client) {
    return guiHandler.registerHandler(new SimpleGHandler(server, client));
  }
  
  public static int register(Supplier<Container> server, Supplier<Object> client) {
    return guiHandler.registerHandler(new SimpleGHandler(server, client));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\guihandler\PGuiRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */