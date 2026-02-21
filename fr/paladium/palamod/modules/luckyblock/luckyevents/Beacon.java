package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.WorldServer;

public class Beacon extends ALuckyEvent {
  private Block block;
  
  private String name;
  
  private int id;
  
  private int rarity;
  
  private String texture;
  
  public Beacon(Block block, String name, int id, int rarity, String texture) {
    this.block = block;
    this.name = name;
    this.id = id;
    this.rarity = rarity;
    this.texture = texture;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    WorldServer worldServer = (WorldServer)player.func_130014_f_();
    EventUtils.spawnProtectedAnimatedStructure(player.field_70170_p, x, y, z, 7, 1, 7, this.block, "heart", 50, (Entity)player, player);
    EventUtils.spawnProtectedAnimatedStructure(player.field_70170_p, x, y + 1, z, 5, 1, 5, this.block, "heart", 50, (Entity)player, player);
    EventUtils.spawnProtectedAnimatedStructure(player.field_70170_p, x, y + 2, z, 3, 1, 3, this.block, "heart", 50, (Entity)player, player);
    if (!EventUtils.canInteract((EntityPlayer)player, x, y + 3, z))
      return; 
    player.func_130014_f_().func_147449_b(x, y + 3, z, (Block)Blocks.field_150461_bJ);
    EntityLightningBolt bolt = new EntityLightningBolt(player.func_130014_f_(), x, y, z);
    worldServer.func_72942_c((Entity)bolt);
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return this.rarity;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Beacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */