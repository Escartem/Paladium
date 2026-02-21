package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.Vec3i;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class NetherEnabled extends ALuckyEvent {
  public static Map<Vec3i, Block> blocks = new HashMap<>();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Map<Vec3i, Block> place = new HashMap<>();
    place.put(new Vec3i(x, y, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x + 1, y, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x + 2, y + 1, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x + 2, y + 2, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x + 2, y + 3, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x - 1, y + 1, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x - 1, y + 2, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x - 1, y + 3, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x, y + 4, z), Blocks.field_150343_Z);
    place.put(new Vec3i(x + 1, y + 4, z), Blocks.field_150343_Z);
    for (int i = 1; i < 4; i++) {
      place.put(new Vec3i(x, y + i, z), BlocksRegister.FAKE_PORTAL);
      place.put(new Vec3i(x + 1, y + i, z), BlocksRegister.FAKE_PORTAL);
    } 
    Iterator<Map.Entry<Vec3i, Block>> iterator = place.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Vec3i, Block> entry = iterator.next();
      Vec3i vec = entry.getKey();
      if (!tryUpdateBlock(player, player.field_70170_p, vec.getX(), vec.getY(), vec.getZ(), entry
          .getValue()))
        iterator.remove(); 
    } 
    blocks.putAll(place);
  }
  
  public boolean tryUpdateBlock(EntityPlayerMP player, World w, int x, int y, int z, Block b) {
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return false; 
    if (b == Blocks.field_150357_h)
      return false; 
    w.func_147449_b(x, y, z, b);
    return true;
  }
  
  public String getName() {
    return "Nether Activé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "nether_active";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\NetherEnabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */