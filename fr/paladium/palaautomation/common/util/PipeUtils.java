package fr.paladium.palaautomation.common.util;

import fr.paladium.palaautomation.common.registry.AutomationBlockRegistry;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.ITileFacing;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import fr.paladium.palaforgeutils.lib.container.impl.TileEntityForgeInventory;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PipeUtils {
  public static final Set<Block> COMPATIBLE_BLOCKS = new HashSet<>();
  
  public static final Set<EnumPipeFacing> DIRECTIONS_PRIORITIES = new HashSet<>();
  
  static {
    COMPATIBLE_BLOCKS.add(AutomationBlockRegistry.PIPE_PALADIUM);
    COMPATIBLE_BLOCKS.add(AutomationBlockRegistry.PIPE_PALADIUM_ENDIUM);
    COMPATIBLE_BLOCKS.add(AutomationBlockRegistry.PIPE_PALADIUM_GREEN);
    COMPATIBLE_BLOCKS.add(AutomationBlockRegistry.CRAFTER);
    COMPATIBLE_BLOCKS.add(AutomationBlockRegistry.GIVER);
    COMPATIBLE_BLOCKS.add(AutomationBlockRegistry.RECEIVER);
    DIRECTIONS_PRIORITIES.add(EnumPipeFacing.NORTH);
    DIRECTIONS_PRIORITIES.add(EnumPipeFacing.SOUTH);
    DIRECTIONS_PRIORITIES.add(EnumPipeFacing.WEST);
    DIRECTIONS_PRIORITIES.add(EnumPipeFacing.EAST);
    DIRECTIONS_PRIORITIES.add(EnumPipeFacing.UP);
    DIRECTIONS_PRIORITIES.add(EnumPipeFacing.DOWN);
  }
  
  public static void withdrawItem(EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof IPipeMachine))
      return; 
    withdrawItem((IPipeMachine)tileEntity, player);
  }
  
  public static void withdrawItem(IPipeMachine machine, EntityPlayer player) {
    PipeItemData itemData = machine.getItemData();
    if (itemData == null || itemData.isEmpty())
      return; 
    ItemStack stack = itemData.toMaxSizeItemStack();
    int size = stack.field_77994_a;
    if (!itemData.decrement(size))
      machine.setItemData(null, false); 
    InventoryUtils.giveOrDropitems(player, stack);
    machine.onPipeItemDataChanged();
  }
  
  public static void debugPipeMachine(ICommandSender sender, World world, int x, int y, int z) {
    if (world.field_72995_K || !ForgeEnv.isDev())
      return; 
    ChatUtils.sendColoredMessage(sender, new String[] { "--- Pipe Machine Debug ---" });
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof IPipeMachine))
      return; 
    IPipeMachine machine = (IPipeMachine)tileEntity;
    PipeItemData data = machine.getItemData();
    ChatUtils.sendColoredMessage(sender, new String[] { "Machine: " + machine.getClass().getSimpleName() });
    ChatUtils.sendColoredMessage(sender, new String[] { "Facing: " + machine.getFacing() });
    if (data == null) {
      ChatUtils.sendColoredMessage(sender, new String[] { "Data is null" });
      return;
    } 
    ChatUtils.sendColoredMessage(sender, new String[] { ("Item: " + data.getItem() == null) ? "null" : data.getItem().func_77658_a() });
    ChatUtils.sendColoredMessage(sender, new String[] { "Amount: " + data.getCount() });
    ChatUtils.sendColoredMessage(sender, new String[] { "NBT: " + data.getNbt() });
    ChatUtils.sendColoredMessage(sender, new String[] { "Metadata: " + data.getMeta() });
  }
  
  public static boolean setInventorySlotContents(IPipeMachine machine, int slot, ItemStack stack) {
    if (!(machine.getTileEntity() instanceof TileEntityForgeInventory))
      return false; 
    TileEntityForgeInventory inventory = (TileEntityForgeInventory)machine.getTileEntity();
    if (stack == null || stack.func_77973_b() == null)
      return false; 
    PipeItemData data = PipeItemData.of(stack);
    if (data == null)
      return false; 
    if (machine.getItemData() == null) {
      machine.setItemData(data, false);
      return true;
    } 
    machine.getItemData().increment(data, data.getCount());
    for (ItemStack itemStack : inventory.getContent()) {
      if (itemStack != null)
        itemStack = null; 
    } 
    inventory.func_70296_d();
    return true;
  }
  
  public static boolean isValidForMachine(IPipeMachine machine, int slot, ItemStack stack) {
    TileEntity tile = machine.getTileEntity();
    if (!(tile instanceof TileEntityForgeInventory))
      return false; 
    if (stack == null || stack.func_77973_b() == null)
      return false; 
    PipeItemData data = machine.getItemData();
    if (data == null)
      return true; 
    if (!data.isSimilar(stack))
      return false; 
    return data.canIncrement(stack.field_77994_a);
  }
  
  public static ITileFacing setFacing(EntityPlayer player, World world, int x, int y, int z, EnumPipeFacing facing) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof ITileFacing))
      return null; 
    ITileFacing machine = (ITileFacing)tileEntity;
    machine.setFacing(facing);
    if (!world.field_72995_K)
      machine.updateOnRadius(); 
    return machine;
  }
  
  public static ITileFacing tryRotate(EntityPlayerMP player, World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof ITileFacing))
      return null; 
    ITileFacing machine = (ITileFacing)tileEntity;
    if (player.func_70093_af()) {
      machine.rotate();
      machine.updateOnRadius();
      if (machine instanceof ATileEntityPipe) {
        ATileEntityPipe pipe = (ATileEntityPipe)machine;
        pipe.error(PipeError.NO_ERROR);
      } 
      return machine;
    } 
    return null;
  }
  
  public static void onBreakPipeMachine(World world, int x, int y, int z, Block block, int metadata) {
    if (world.field_72995_K)
      return; 
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof IPipeMachine))
      return; 
    IPipeMachine machine = (IPipeMachine)tileEntity;
    PipeItemData data = machine.getItemData();
    if (data == null)
      return; 
    List<ItemStack> stacks = data.toItemStacks();
    for (ItemStack stack : stacks)
      ItemUtils.spawnItemInWorld(world, x + 0.5D, y + 0.5D, z + 0.5D, stack); 
  }
  
  public static boolean isUseableByPlayer(double x, double y, double z, EntityPlayer player) {
    if (player == null)
      return false; 
    return (player.func_70092_e(x + 0.5D, y + 0.5D, z + 0.5D) <= 64.0D);
  }
  
  public static boolean isUseableByPlayer(TileEntity tile, EntityPlayer player) {
    if (tile == null || tile.func_145831_w() == null || player == null)
      return false; 
    TileEntity newInstance = tile.func_145831_w().func_147438_o(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    if (newInstance == null)
      return false; 
    return (tile.func_145831_w().func_147438_o(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e).equals(tile) && player
      .func_70092_e(tile.field_145851_c + 0.5D, tile.field_145848_d + 0.5D, tile.field_145849_e + 0.5D) <= 64.0D);
  }
  
  public static void bindPlayerInventory(ForgeContainer container, EntityPlayer player) {
    int delta = 40;
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        container.func_75146_a(new Slot((IInventory)player.field_71071_by, j + i * 9 + 9, 104 + j * 67 - 40, 483 + i * 67 - 40)); 
    } 
    for (i = 0; i < 9; i++)
      container.func_75146_a(new Slot((IInventory)player.field_71071_by, i, 104 + i * 67 - 40, 658)); 
  }
  
  public static Block findNextBlock(ATileEntityPipe pipe) {
    EnumPipeFacing facing = pipe.getFacing();
    if (facing == null)
      return null; 
    return pipe.func_145831_w().func_147439_a(pipe.field_145851_c + facing.getFrontOffsetX(), pipe.field_145848_d + facing.getFrontOffsetY(), pipe.field_145849_e + facing.getFrontOffsetZ());
  }
  
  public static ATileEntityPipe findNextPipe(ATileEntityPipe pipe, World world, int x, int y, int z) {
    EnumPipeFacing facing = pipe.getFacing();
    if (facing == null)
      return null; 
    return getPipe(world, x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing.getFrontOffsetZ());
  }
  
  public static ATileEntityPipe getPipe(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity instanceof ATileEntityPipe)
      return (ATileEntityPipe)tileEntity; 
    return null;
  }
  
  public static ATileEntityPipe findClosestPipeByIterating(TileEntity machine, EnumPipeFacing... excludeDirections) {
    if (machine == null)
      return null; 
    HashSet<EnumPipeFacing> exclude = new HashSet<>();
    if (excludeDirections != null && excludeDirections.length > 0)
      exclude.addAll(Arrays.asList(excludeDirections)); 
    for (EnumPipeFacing facing : DIRECTIONS_PRIORITIES) {
      if (exclude.contains(facing))
        continue; 
      TileEntity tile = machine.func_145831_w().func_147438_o(machine.field_145851_c + facing
          .getFrontOffsetX(), machine.field_145848_d + facing
          .getFrontOffsetY(), machine.field_145849_e + facing
          .getFrontOffsetZ());
      if (!(tile instanceof ATileEntityPipe))
        continue; 
      return (ATileEntityPipe)tile;
    } 
    return null;
  }
  
  public static Set<EnumPipeFacing> getPossibleChildDirections(EnumPipeFacing exclude) {
    Set<EnumPipeFacing> directions = new HashSet<>();
    for (EnumPipeFacing facing : DIRECTIONS_PRIORITIES) {
      if (facing != exclude)
        directions.add(facing); 
    } 
    return directions;
  }
  
  public static EnumPipeFacing nextDirection(EnumPipeFacing direction) {
    for (EnumPipeFacing facing : DIRECTIONS_PRIORITIES) {
      if (facing == direction)
        return facing; 
    } 
    return null;
  }
  
  public boolean isCompatible(Block block) {
    return COMPATIBLE_BLOCKS.contains(block);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\commo\\util\PipeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */