package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasSnowGolem;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class SnowArmy extends ALuckyEvent {
  public static final int radius = 2;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int spawnX = (new Double(player.field_70165_t)).intValue();
    int spawnY = (new Double(player.field_70163_u)).intValue();
    int spawnZ = (new Double(player.field_70161_v)).intValue();
    if (spawnX < 0)
      spawnX--; 
    if (spawnZ < 0)
      spawnZ--; 
    if (EventUtils.canInteract((EntityPlayer)player, spawnX + 2, spawnY, spawnZ + 2) && EventUtils.canInteract((EntityPlayer)player, spawnX - 2, spawnY, spawnZ - 2) && 
      EventUtils.canInteract((EntityPlayer)player, spawnX + 2, spawnY, spawnZ - 2) && EventUtils.canInteract((EntityPlayer)player, spawnX - 2, spawnY, spawnZ + 2)) {
      spawnStructureOnSurface(player.field_70170_p, spawnX, spawnZ, 5, 5, Blocks.field_150431_aC, player);
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, ((spawnX - 2) + 0.5F), player.field_70170_p.func_72825_h(spawnX - 2, spawnZ - 2), ((spawnZ - 2) + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, ((spawnX + 2) + 0.5F), player.field_70170_p.func_72825_h(spawnX + 2, spawnZ + 2), ((spawnZ + 2) + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, ((spawnX - 2) + 0.5F), player.field_70170_p.func_72825_h(spawnX - 2, spawnZ + 2), ((spawnZ + 2) + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, ((spawnX + 2) + 0.5F), player.field_70170_p.func_72825_h(spawnX + 2, spawnZ - 2), ((spawnZ - 2) + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, (spawnX + 0.5F), player.field_70170_p.func_72825_h(spawnX, spawnZ + 2), ((spawnZ + 2) + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, (spawnX + 0.5F), player.field_70170_p.func_72825_h(spawnX, spawnZ - 2), ((spawnZ - 2) + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, ((spawnX + 2) + 0.5F), player.field_70170_p.func_72825_h(spawnX + 2, spawnZ), (spawnZ + 0.5F)));
      player.field_70170_p.func_72838_d((Entity)new EntityChristmasSnowGolem(player.field_70170_p, ((spawnX - 2) + 0.5F), player.field_70170_p.func_72825_h(spawnX - 2, spawnZ), (spawnZ + 0.5F)));
    } 
  }
  
  public void spawnStructureOnSurface(World world, int x, int z, int width, int length, Block block, EntityPlayerMP player) {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oz = z - length / 2; oz < z + Math.ceil((length / 2.0F)); oz++) {
        int oy = world.func_72825_h(ox, oz);
        if (EventUtils.canInteract((EntityPlayer)player, ox, oy, oz))
          world.func_147449_b(ox, oy, oz, block); 
      } 
    } 
  }
  
  public String getName() {
    return "Snow army";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "snow_army";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\SnowArmy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */