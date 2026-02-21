package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.factions.api.util.Vec2i;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntitySantaNoescroc;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palaschematic.PalaSchematic;
import fr.paladium.palaschematic.utils.Schematic;
import java.io.File;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class NoescrocFather extends ALuckyEvent {
  private static final int HEIGHT = 21;
  
  private static final int WIDTH = 25;
  
  private static final int LENGHT = 17;
  
  private static final int SIZE_SOUTH = 10;
  
  private static final int SIZE_NORTH = 6;
  
  private static final int SIZE_EAST = 10;
  
  private static final int SIZE_WEST = 14;
  
  private static final int X_OFFSET = 10;
  
  private static final int Y_OFFSET = -1;
  
  private static final int Z_OFFSET = -6;
  
  public static final String SCHEMATIC_PATH = "noescroc_house.schematic";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (checkIfCanPlace(player, x, y, z))
      try {
        File schematicFile = new File(PalaMod.conf, "noescroc_house.schematic");
        Schematic schematic = PalaSchematic.get().getSchematicManager().load(schematicFile);
        PalaSchematic.get().getSchematicManager().paste(schematic, new Location(Bukkit.getWorlds().get(0), x, y, z));
      } catch (NoClassDefFoundError|Exception e) {
        e.printStackTrace();
      }  
    player.field_70170_p.func_72838_d((Entity)new EntitySantaNoescroc(player.field_70170_p, (x + 0.5F), y, (z + 0.5F)));
  }
  
  public String getName() {
    return "Père Noescroc";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "noescroc_father";
  }
  
  protected boolean checkIfCanPlace(EntityPlayerMP player, int x, int y, int z) {
    if (5 >= y || y >= 234)
      return false; 
    Chunk chunkS = player.field_70170_p.func_72938_d(x, z);
    Chunk chunkDL = player.field_70170_p.func_72938_d(x + 10, z - 6);
    Chunk chunkHL = player.field_70170_p.func_72938_d(x + 10, z + 10);
    Chunk chunkHR = player.field_70170_p.func_72938_d(x - 14, z + 10);
    Chunk chunkDR = player.field_70170_p.func_72938_d(x - 14, z - 6);
    Chunk chunkHM = player.field_70170_p.func_72938_d(x, z + 10);
    Chunk chunkDM = player.field_70170_p.func_72938_d(x, z - 6);
    if (ClaimController.getInstance().isClaimed(new Vec2i(chunkS.field_76635_g, chunkS.field_76647_h)) || 
      ClaimController.getInstance().isClaimed(new Vec2i(chunkDL.field_76635_g, chunkDL.field_76647_h)) || 
      ClaimController.getInstance().isClaimed(new Vec2i(chunkHL.field_76635_g, chunkHL.field_76647_h)) || 
      ClaimController.getInstance().isClaimed(new Vec2i(chunkHR.field_76635_g, chunkHR.field_76647_h)) || 
      ClaimController.getInstance().isClaimed(new Vec2i(chunkDR.field_76635_g, chunkDR.field_76647_h)) || 
      ClaimController.getInstance().isClaimed(new Vec2i(chunkHM.field_76635_g, chunkHM.field_76647_h)) || 
      ClaimController.getInstance().isClaimed(new Vec2i(chunkDM.field_76635_g, chunkDM.field_76647_h)))
      return false; 
    for (int sY = 0; sY < 21; sY++) {
      for (int sZ = 0; sZ < 17; sZ++) {
        for (int sX = 0; sX < 25; sX++) {
          if (!EventUtils.canInteract((EntityPlayer)player, x - sX + 10, y + sY + -1 + 13, z + sZ + -6))
            return false; 
        } 
      } 
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\NoescrocFather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */