package fr.paladium.palamod.modules.miner.dimminer.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.miner.blocks.BlockMiner;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class BlockEndiumNuggetOre extends BlockMiner {
  public BlockEndiumNuggetOre(String name) {
    super(name);
    func_149752_b(6000000.0F);
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public void func_149664_b(World world, int x, int y, int z, int meta) {
    if (!world.field_72995_K)
      return; 
    world.func_72980_b(x, y, z, "random.explode", 1.0F, 1.0F, false);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return ItemsRegister.ENDIUM_NUGGET;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    ret.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
    return ret;
  }
  
  public boolean func_149700_E() {
    return false;
  }
  
  public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    return false;
  }
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    if (event.world.field_72995_K || event.block != this || event.getPlayer() == null || (event.getPlayer()).field_71075_bZ.field_75098_d)
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)event.getPlayer());
    if (data.getLevel(JobType.MINER) < 20) {
      event.setCanceled(true);
      event.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être niveau 20 pour casser ce bloc."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\BlockEndiumNuggetOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */