package fr.paladium.palarpg.module.dungeon.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.client.render.block.TileEntityDungeonChestRenderer;
import fr.paladium.palarpg.module.dungeon.client.ui.chest.UIDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.block.template.ABlockDungeon;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.network.chest.SCPacketRPGDungeonChestOpen;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import java.util.Optional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class BlockDungeonChest extends ABlockDungeon implements ITileEntityProvider {
  public BlockDungeonChest() {
    super("dungeon_chest", "chest/" + TileEntityDungeonChest.DungeonChestRarity.COMMON.name().toLowerCase());
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && player instanceof EntityPlayerMP) {
      Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(world);
      if (!optionalDungeonWorld.isPresent())
        return true; 
      TileEntity tileEntity = world.func_147438_o(x, y, z);
      if (!(tileEntity instanceof TileEntityDungeonChest))
        return true; 
      TileEntityDungeonChest chest = (TileEntityDungeonChest)tileEntity;
      if (chest.getRoom() == null) {
        (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg", 5.0F)).send((EntityPlayerMP)player);
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§louvrir §6§lce §6§lcoffre."));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §cCe §ccoffre §cn'est §cpas §cencore §cactif."));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        return true;
      } 
      if (!chest.getRoom().isFinished()) {
        (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg", 5.0F)).send((EntityPlayerMP)player);
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§louvrir §6§lce §6§lcoffre."));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cdevez §cterminer §cla §csalle §cavant §cde §cpouvoir §couvrir §cce §ccoffre."));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        return true;
      } 
      Optional<RPGDungeonPlayerData.RPGDungeonItem[]> optionalRewards = chest.getRewards(UUIDUtils.toString((Entity)player));
      if (!optionalRewards.isPresent()) {
        (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg", 5.0F)).send((EntityPlayerMP)player);
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§louvrir §6§lce §6§lcoffre."));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        player.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cavez §cdéjà §crécupéré §cle §ccontenu."));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
        player.func_145747_a((IChatComponent)new ChatComponentText(""));
        return true;
      } 
      (new SCPacketRPGDungeonChestOpen(new UIDungeonChest.UIDungeonChestData(x, y, z, optionalRewards.get(), chest.getRarity()))).send((EntityPlayerMP)player);
    } 
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityDungeonChest();
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return TileEntityDungeonChestRenderer.RENDER_ID;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\BlockDungeonChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */