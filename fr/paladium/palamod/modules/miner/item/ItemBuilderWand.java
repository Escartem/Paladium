package fr.paladium.palamod.modules.miner.item;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.PlayerUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import org.bukkit.Bukkit;

public class ItemBuilderWand extends ItemMiner implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[4];
  
  public ItemBuilderWand() {
    super("builder_wand");
    func_77625_d(1);
  }
  
  public void func_94581_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:builder_wand_amethyst");
    this.icons[1] = register.func_94245_a("palamod:builder_wand_titane");
    this.icons[2] = register.func_94245_a("palamod:builder_wand_paladium");
    this.icons[3] = register.func_94245_a("palamod:builder_wand_endium");
    super.func_94581_a(register);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
    for (int i = 0; i < 4; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int metadata) {
    return this.icons[metadata];
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && !isAirBlock(world, x, y, z)) {
      try {
        if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(player.func_110124_au()))) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas utiliser cet item en combat."));
          return false;
        } 
      } catch (Exception|NoClassDefFoundError exception) {}
      Block b = world.func_147439_a(x, y, z);
      Item i = Item.func_150898_a(b);
      int meta = world.func_72805_g(x, y, z);
      List<BlockPos> list = getAvailablePos(world, player, item, x, y, z, EnumFacing.values()[side]);
      for (BlockPos pos : list) {
        int blockX = pos.getX();
        int blockY = pos.getY();
        int blockZ = pos.getZ();
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a(blockX, blockY, blockZ, (blockX + 1), (blockY + 1), (blockZ + 1));
        if (!world.func_72872_a(EntityPlayer.class, bb).isEmpty()) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas poser des blocs sur un joueur."));
          return false;
        } 
      } 
      for (BlockPos pos : list) {
        if (pos.getY() >= 255 || pos.getY() < 0)
          continue; 
        ItemStack stack = player.func_71045_bC();
        if (stack == null || stack.func_77942_o() || (
          !player.field_71075_bZ.field_75098_d && 
          !PlayerUtil.consumeInventoryItemWithMetaWithoutNBT(player, i, meta)))
          continue; 
        player.field_71071_by.field_70459_e = true;
        if (EventUtils.canInteract(player, pos.getX(), pos.getY(), pos.getZ()) && b.func_149742_c(world, pos.getX(), pos.getY(), pos.getZ())) {
          BlockEvent.PlaceEvent event = ForgeEventFactory.onPlayerBlockPlace(player, new BlockSnapshot(world, pos.getX(), pos.getY(), pos.getZ(), b, meta), ForgeDirection.NORTH);
          if (event.isCanceled() || (event.hasResult() && event.getResult() == Event.Result.DENY))
            continue; 
          UseItemAchievement.performCheck(player, item, "BUILDER_WAND");
          world.func_147449_b(pos.getX(), pos.getY(), pos.getZ(), b);
          world.func_72921_c(pos.getX(), pos.getY(), pos.getZ(), meta, 2);
        } 
      } 
      return true;
    } 
    return false;
  }
  
  public int getRadius(ItemStack item) {
    return getRadius(item.func_77960_j());
  }
  
  public List<BlockPos> getAvailablePos(World world, EntityPlayer player, ItemStack item, int x, int y, int z, EnumFacing side) {
    List<BlockPos> blocks = new ArrayList<>();
    Block b = world.func_147439_a(x, y, z);
    int radius = getRadius(item.func_77960_j());
    if (side == EnumFacing.UP) {
      for (int i = -radius / 2; i < Math.ceil((radius / 2)); i++) {
        for (int j = -radius / 2; j < Math.ceil((radius / 2)); j++) {
          if (!isAirBlock(world, x + i, y, z + j) && isAirBlock(world, x + i, y + 1, z + j) && world.func_147439_a(x + i, y, z + j) == b)
            blocks.add(new BlockPos(x + i, y + 1, z + j)); 
        } 
      } 
    } else if (side == EnumFacing.DOWN) {
      for (int i = -radius / 2; i < Math.ceil((radius / 2)); i++) {
        for (int j = -radius / 2; j < Math.ceil((radius / 2)); j++) {
          if (!isAirBlock(world, x + i, y, z + j) && isAirBlock(world, x + i, y - 1, z + j) && world.func_147439_a(x + i, y, z + j) == b)
            blocks.add(new BlockPos(x + i, y - 1, z + j)); 
        } 
      } 
    } else if (side == EnumFacing.NORTH) {
      for (int i = -radius / 2; i < Math.ceil((radius / 2)); i++) {
        for (int j = -radius / 2; j < Math.ceil((radius / 2)); j++) {
          if (!isAirBlock(world, x + j, y + i, z) && isAirBlock(world, x + j, y + i, z - 1) && world.func_147439_a(x + j, y + i, z) == b)
            blocks.add(new BlockPos(x + j, y + i, z - 1)); 
        } 
      } 
    } else if (side == EnumFacing.SOUTH) {
      for (int i = -radius / 2; i < Math.ceil((radius / 2)); i++) {
        for (int j = -radius / 2; j < Math.ceil((radius / 2)); j++) {
          if (!isAirBlock(world, x + j, y + i, z) && isAirBlock(world, x + j, y + i, z + 1) && world.func_147439_a(x + j, y + i, z) == b)
            blocks.add(new BlockPos(x + j, y + i, z + 1)); 
        } 
      } 
    } else if (side == EnumFacing.EAST) {
      for (int i = -radius / 2; i < Math.ceil((radius / 2)); i++) {
        for (int j = -radius / 2; j < Math.ceil((radius / 2)); j++) {
          if (!isAirBlock(world, x, y + i, z + j) && isAirBlock(world, x - 1, y + i, z + j) && world.func_147439_a(x, y + i, z + j) == b)
            blocks.add(new BlockPos(x - 1, y + i, z + j)); 
        } 
      } 
    } else if (side == EnumFacing.WEST) {
      for (int i = -radius / 2; i < Math.ceil((radius / 2)); i++) {
        for (int j = -radius / 2; j < Math.ceil((radius / 2)); j++) {
          if (!isAirBlock(world, x, y + i, z + j) && isAirBlock(world, x + 1, y + i, z + j) && world.func_147439_a(x, y + i, z + j) == b)
            blocks.add(new BlockPos(x + 1, y + i, z + j)); 
        } 
      } 
    } 
    return blocks;
  }
  
  private boolean isAirBlock(World world, int x, int y, int z) {
    Block b = world.func_147439_a(x, y, z);
    return (b == Blocks.field_150350_a || b.func_149688_o() == Material.field_151586_h || b.func_149688_o() == Material.field_151587_i);
  }
  
  public int getRadius(int damage) {
    switch (damage) {
      case 0:
        return 6;
      case 1:
        return 9;
      case 2:
        return 11;
      case 3:
        return 20;
    } 
    return 0;
  }
  
  public String func_77653_i(ItemStack item) {
    switch (item.func_77960_j()) {
      case 0:
        return ("" + StatCollector.func_74838_a(func_77657_g(item) + ".amethyst.name")).trim();
      case 1:
        return ("" + StatCollector.func_74838_a(func_77657_g(item) + ".titane.name")).trim();
      case 2:
        return ("" + StatCollector.func_74838_a(func_77657_g(item) + ".paladium.name")).trim();
      case 3:
        return ("" + StatCollector.func_74838_a(func_77657_g(item) + ".endium.name")).trim();
    } 
    return super.func_77653_i(item);
  }
  
  public String func_77667_c(ItemStack stack) {
    return func_77658_a() + "_" + stack.func_77960_j();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#8.-les-outils-de-construction";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemBuilderWand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */