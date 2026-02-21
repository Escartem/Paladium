package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WowBG extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.func_130014_f_();
    for (int ox = -1; ox < 2; ox++) {
      for (int oz = -1; oz < 2; oz++) {
        if (world instanceof WorldServer) {
          WorldServer server = (WorldServer)world;
          server.func_147487_a("cloud", (x + ox), y, (z + oz), 100, 0.0D, 0.0D, 0.0D, 0.0D);
        } 
        world.func_147449_b(x + ox, y, z + oz, (Block)BlocksRegister.ENDIUM_LUCKY_BLOCK);
        TileEntityLuckyBlock tileEntityLuckyBlock = (TileEntityLuckyBlock)world.func_147438_o(x + ox, y, z + oz);
        tileEntityLuckyBlock.setVersion(1);
      } 
    } 
    if (world instanceof WorldServer) {
      WorldServer server = (WorldServer)world;
      server.func_147487_a("heart", x, (y + 2), z, 1000, 0.0D, 0.0D, 0.0D, 1.0D);
    } 
    world.func_147449_b(x, y + 1, z, (Block)BlocksRegister.ENDIUM_LUCKY_BLOCK);
    TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(x, y + 1, z);
    te.setVersion(1);
  }
  
  public String getName() {
    return "Quelle dinguerie !";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "endiumluckyblock";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WowBG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */