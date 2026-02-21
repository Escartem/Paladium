package fr.paladium.palamod.modules.apet.server.listener.active;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.apet.server.skill.handler.LuckyMinerSkill;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class LuckyMinerListener {
  private final HashMap<Block, Block> blocks = new HashMap<>();
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    World world = event.world;
    UUID uniqueId = player.func_110124_au();
    Block block = event.block;
    if (!(block instanceof net.minecraft.block.BlockOre))
      return; 
    if (!LuckyMinerSkill.LUCKY_MINERS.containsKey(uniqueId))
      return; 
    Block target = get(block);
    if (target == null)
      return; 
    double random = Math.random() * 100.0D;
    double value = ((Double)LuckyMinerSkill.LUCKY_MINERS.get(uniqueId)).doubleValue();
    LuckyMinerSkill.LUCKY_MINERS.remove(uniqueId);
    if (random > value) {
      PetTranslateEnum.MESSAGE_LUCKY_MINER_FAILED.message((ICommandSender)player);
      return;
    } 
    event.setCanceled(true);
    world.func_147449_b(event.x, event.y, event.z, target);
    PetTranslateEnum.MESSAGE_LUCKY_MINER_SUCCESS.message((ICommandSender)player);
  }
  
  private Block get(Block block) {
    if (this.blocks.isEmpty())
      init(); 
    return this.blocks.get(block);
  }
  
  private void init() {
    this.blocks.put(Blocks.field_150365_q, Blocks.field_150366_p);
    this.blocks.put(Blocks.field_150366_p, Blocks.field_150352_o);
    this.blocks.put(Blocks.field_150352_o, Blocks.field_150482_ag);
    this.blocks.put(Blocks.field_150482_ag, Blocks.field_150412_bA);
    this.blocks.put(PWorld.AMETHYST_ORE, PWorld.TITANE_ORE);
    this.blocks.put(PWorld.TITANE_ORE, PWorld.PALADIUM_ORE);
    this.blocks.put(PWorld.PALADIUM_ORE, PWorld.PALADIUM_GREEN_ORE);
    this.blocks.put(PWorld.PALADIUM_GREEN_ORE, PWorld.FINDIUM_ORE);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\listener\active\LuckyMinerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */