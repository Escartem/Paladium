package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks;

import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.PackYourBagsEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PackYourBagsRunnable extends TimerTask {
  private static final long MAX_DURATION = TimeUnit.MINUTES.toSeconds(1L);
  
  private static final String SUCCESS_MESSAGE = "&eTes parents t'attendent devant la &aporte &e!";
  
  private static final String FAIL_MESSAGE = "&cTes parents sont partis sans toi :(";
  
  private static final String TELEPORT_MESSAGE = "§eTéléportation au §dcamping §edans §c%ss§e...";
  
  private static final String NPC_PHONE = "item.npcPhone";
  
  private static final String NPC_USB_STICK = "item.npcUsbStick";
  
  private static final String NPC_KEY = "item.npcKey";
  
  private UUID playerUniqueId;
  
  private final DoubleLocation spawnLocation;
  
  private final World world;
  
  private Schematic mansionSchematic;
  
  private int timer;
  
  private int teleportTimer;
  
  private boolean has;
  
  public PackYourBagsRunnable(EntityPlayerMP player, World world, DoubleLocation spawnLocation, Schematic mansionSchematic) {
    this.playerUniqueId = player.func_110124_au();
    this.world = world;
    this.spawnLocation = spawnLocation;
    this.mansionSchematic = mansionSchematic;
    this.timer = 0;
    this.teleportTimer = 10;
    this.has = false;
  }
  
  public void run() {
    this.timer++;
    EntityPlayerMP player = getPlayer();
    if (player == null) {
      stop(player);
      return;
    } 
    if (this.timer >= MAX_DURATION) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cTes parents sont partis sans toi :(" });
      stop(player);
      return;
    } 
    if (!this.has && hasItems(player)) {
      this.has = true;
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eTes parents t'attendent devant la &aporte &e!" });
    } 
    if (this.has) {
      this.teleportTimer--;
      ModuleTitle.getInstance().sendTitle(new MinecraftTitle("§6Fais tes valises", 
            
            String.format("§eTéléportation au §dcamping §edans §c%ss§e...", new Object[] { Integer.valueOf(this.teleportTimer) }), 500L, 3000L, 500L), player);
      if (this.teleportTimer == 0) {
        stop(player);
        DoubleLocation newLocation = new DoubleLocation(this.spawnLocation.getBlockX(), 150.0D, this.spawnLocation.getBlockZ());
        if (!EventUtils.canInteract((EntityPlayer)player, newLocation.getBlockX(), newLocation.getBlockY(), newLocation.getBlockZ())) {
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
          return;
        } 
        try {
          SchematicUtils.paste((EntityPlayer)player, 
              SchematicUtils.load("lbaout_camping.schematic"), this.world, newLocation, false);
          this.world.func_147449_b(newLocation.getBlockX(), newLocation.getBlockY(), newLocation.getBlockZ(), (Block)Blocks.field_150349_c);
          TeleportUtils.teleport((EntityPlayer)player, newLocation.getBlockX(), (newLocation.getBlockY() + 2), newLocation.getBlockZ(), player.field_70177_z, player.field_70125_A);
        } catch (Exception e) {
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
          e.printStackTrace();
        } 
      } 
    } 
  }
  
  private void stop(EntityPlayerMP player) {
    if (player != null)
      MonthlyUtils.stopHeliosEvent(player, PackYourBagsEvent.class); 
    if (this.mansionSchematic != null)
      SchematicUtils.clean(this.mansionSchematic, this.world, this.spawnLocation); 
    cancel();
  }
  
  private boolean hasItems(EntityPlayerMP player) {
    ItemStack roller = getItem(player, ItemsRegister.ROLLER);
    ItemStack chestPlate = getItem(player, (Item)Items.field_151030_Z);
    if (roller == null || chestPlate == null)
      return false; 
    ItemStack npcPhone = getItem(player, "item.npcPhone");
    ItemStack npcUsbStick = getItem(player, "item.npcUsbStick");
    ItemStack npcKey = getItem(player, "item.npcKey");
    if (npcPhone == null || npcUsbStick == null || npcKey == null)
      return false; 
    remove(player, new ItemStack[] { roller, chestPlate, npcPhone, npcUsbStick, npcKey });
    player.field_71069_bz.func_75142_b();
    return true;
  }
  
  private void remove(EntityPlayerMP player, ItemStack... items) {
    ItemStack[] mainInventory = player.field_71071_by.field_70462_a;
    for (int i = 0; i < mainInventory.length; i++) {
      for (ItemStack item : items) {
        if (mainInventory[i] != null && mainInventory[i].func_77973_b().equals(item.func_77973_b()))
          mainInventory[i] = null; 
      } 
    } 
  }
  
  private ItemStack getItem(EntityPlayerMP player, Item item) {
    ItemStack[] mainInventory = player.field_71071_by.field_70462_a;
    for (int i = 0; i < mainInventory.length; i++) {
      if (mainInventory[i] != null && mainInventory[i].func_77973_b().equals(item))
        return mainInventory[i]; 
    } 
    return null;
  }
  
  private ItemStack getItem(EntityPlayerMP player, String unlocalizedName) {
    ItemStack[] mainInventory = player.field_71071_by.field_70462_a;
    for (int i = 0; i < mainInventory.length; i++) {
      if (mainInventory[i] != null && mainInventory[i].func_77973_b().func_77658_a().equalsIgnoreCase(unlocalizedName))
        return mainInventory[i]; 
    } 
    return null;
  }
  
  private EntityPlayerMP getPlayer() {
    return MonthlyUtils.getPlayer(this.playerUniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tasks\PackYourBagsRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */