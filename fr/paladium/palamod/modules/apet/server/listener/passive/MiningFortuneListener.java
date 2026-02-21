package fr.paladium.palamod.modules.apet.server.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.blocks.BaseBlockCrops;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class MiningFortuneListener {
  private static final int VANILLA_MAX_STAGE = 7;
  
  private final HashMap<Block, Item> blocks = new HashMap<>();
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    World world = event.world;
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    Block block = event.block;
    if (event.isCanceled())
      return; 
    if (!isMature(world, event.x, event.y, event.z, block))
      return; 
    Item item = get(block);
    if (item == null)
      return; 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.MINING_FORTUNE.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    double random = Math.random() * 100.0D;
    if (random > value)
      return; 
    if (!EventUtils.canInteract((EntityPlayer)player, event.x, event.y, event.z))
      return; 
    ItemUtils.spawnItemInWorld(world, event.x, event.y, event.z, new ItemStack(item));
  }
  
  private boolean isMature(World world, int x, int y, int z, Block block) {
    if (block instanceof net.minecraft.block.BlockCrops || block instanceof net.minecraft.block.BlockStem) {
      int meta = world.func_72805_g(x, y, z);
      return (meta == 7);
    } 
    if (block instanceof BaseBlockCrops) {
      BaseBlockCrops crops = (BaseBlockCrops)block;
      int meta = world.func_72805_g(x, y, z);
      return (meta == crops.getStageMax() - 1);
    } 
    return false;
  }
  
  public Item get(Block block) {
    if (this.blocks.isEmpty())
      initBlocks(); 
    return this.blocks.get(block);
  }
  
  public void initBlocks() {
    this.blocks.put(Blocks.field_150464_aj, Items.field_151014_N);
    this.blocks.put(Blocks.field_150469_bN, Items.field_151174_bG);
    this.blocks.put(Blocks.field_150459_bM, Items.field_151172_bF);
    this.blocks.put(Blocks.field_150393_bb, Items.field_151080_bb);
    this.blocks.put(Blocks.field_150394_bc, Items.field_151081_bc);
    this.blocks.put(BlocksRegister.CROPS_CHERVIL, ItemsRegister.SEED_CHERVIL);
    this.blocks.put(BlocksRegister.CROPS_KIWANO, ItemsRegister.SEED_KIWANO);
    this.blocks.put(BlocksRegister.CROPS_EGGPLANT, ItemsRegister.SEED_EGGPLANT);
    this.blocks.put(BlocksRegister.CROPS_ORANGEBLUE, ItemsRegister.SEED_ORANGEBLUE);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\listener\passive\MiningFortuneListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */