package fr.paladium.palamod.modules.spellsv2.spells;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.blocks.ModBlocks;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.tile.TileEntityInertiumBlock;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;

public class Inertium implements Spell {
  public void perform(final EntityPlayerMP player, final int tier) {
    final String uuid = FastUUID.toString((Entity)player);
    BlockPos pos = new BlockPos((Entity)player);
    BlockSnapshot snapshot = BlockSnapshot.getBlockSnapshot(player.field_70170_p, pos.getX(), pos.getY(), pos.getZ());
    boolean can = ForgeEventFactory.onPlayerBlockPlace((EntityPlayer)player, snapshot, ForgeDirection.getOrientation(1)).isCanceled();
    if (can)
      return; 
    Block block = Blocks.field_150348_b;
    try {
      block = Block.func_149634_a(player.func_71045_bC().func_77973_b());
      if (block == null || block instanceof net.minecraft.block.BlockAir || block instanceof fr.paladium.palamod.modules.spellsv2.blocks.BlockInertium)
        block = Blocks.field_150348_b; 
    } catch (Exception exception) {}
    if (block.hasTileEntity(0))
      return; 
    EventUtils.spawnParticle(player.field_70170_p, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 100, 0.10000000149011612D);
    player.func_70634_a(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
    ServerManager.addActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid);
    ServerManager.addFreeze(player, pos.getX(), pos.getY(), pos.getZ());
    PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid, true));
    player.func_130014_f_().func_147449_b(pos.getX(), pos.getY(), pos.getZ(), ModBlocks.inertiumBlock);
    TileEntityInertiumBlock te = (TileEntityInertiumBlock)player.func_130014_f_().func_147438_o(pos.getX(), pos.getY(), pos.getZ());
    te.setInfo(new ItemStack(block));
    (new Thread(new Runnable() {
          public void run() {
            try {
              PSpellsV2.network.sendTo((IMessage)new PacketClientSpellTiming((tier == 1) ? 10 : ((tier == 2) ? 30 : 60), Inertium.this
                    .getId()), player);
              Thread.sleep((tier == 1) ? 10000L : ((tier == 2) ? 30000L : 60000L));
              PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM
                    .getSpell().getId(), uuid, false));
              player.func_130014_f_().func_147468_f(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(0)).intValue(), (
                  (Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(1)).intValue(), (
                  (Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(2)).intValue());
              EventUtils.spawnParticle(player.field_70170_p, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 100, 0.10000000149011612D);
              ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid);
              ServerManager.removeFreeze(player);
            } catch (Exception exception) {}
          }
        })).start();
  }
  
  public int getId() {
    return 2;
  }
  
  public String getName() {
    return "Inertium";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 3;
  }
  
  public int getCooldown() {
    return 10;
  }
  
  public int getLevel() {
    return 40;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de se transformer en le bloc qu’on tient à la main. Si le joueur ne tient pas de bloc il sera transformé en un bloc de stone. A noter que le joueur ne pourra plus bouger, et que le bloc ne sera pas solide (on passe à travers). Si un autre joueur le tappe, il se transformera en joueur.    Niveau 1: Transformation 10s", "Permet de se transformer en le bloc qu’on tient à la main. Si le joueur ne tient pas de bloc il sera transformé en un bloc de stone. A noter que le joueur ne pourra plus bouger, et que le bloc ne sera pas solide (on passe à travers). Si un autre joueur le tappe, il se transformera en joueur.    Niveau 2: Transformation 30s", "Permet de se transformer en le bloc qu’on tient à la main. Si le joueur ne tient pas de bloc il sera transformé en un bloc de stone. A noter que le joueur ne pourra plus bouger, et que le bloc ne sera pas solide (on passe à travers). Si un autre joueur le tappe, il se transformera en joueur.    Niveau 3: Transformation 1min" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Inertium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */