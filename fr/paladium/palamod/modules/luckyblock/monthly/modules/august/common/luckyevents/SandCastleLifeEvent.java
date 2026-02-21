package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.MajordomeData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class SandCastleLifeEvent extends ALuckyEvent implements ISchematic {
  private static final String EVENT_NAME = "La vie de château (de sable)";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 550;
  
  private static final String TEXTURE_PATH = "august/sand_castle_life";
  
  public static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/majordome.png";
  
  public static final long DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  public static final String SCHEMATIC_NAME = "lbaout_chateausable.schematic";
  
  public static SandCastleLifeEvent INSTANCE;
  
  private final List<MajordomeData> datas;
  
  public SandCastleLifeEvent() {
    INSTANCE = this;
    this.datas = new ArrayList<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    Location location = new Location(world, x, y, z);
    EntityNPC majordome = getMajordome("Majordome", world, location);
    Schematic schematic = null;
    try {
      schematic = SchematicUtils.paste((EntityPlayer)player, 
          
          SchematicUtils.load("lbaout_chateausable.schematic"), world, x, y, z, false);
    } catch (Exception e) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
      e.printStackTrace();
    } 
    world.func_72838_d((Entity)majordome);
    add((Entity)majordome, location);
  }
  
  public EntityNPC getMajordome(String name, World world, Location location) {
    EntityNPC majordome = new EntityNPC(world, name, "palamod:textures/entity/npc/majordome.png", location.getX(), location.getY() - 2.0D, location.getZ(), DURATION, DURATION, true);
    return majordome;
  }
  
  public void add(Entity entity, Location location) {
    if (get(entity) != null)
      return; 
    this.datas.add(new MajordomeData(entity.func_110124_au(), location));
  }
  
  public MajordomeData get(Entity entity) {
    return get(entity.func_110124_au());
  }
  
  public boolean remove(MajordomeData data) {
    return this.datas.remove(data);
  }
  
  public MajordomeData get(UUID uniqueId) {
    return this.datas.stream()
      .filter(data -> data.getEntityUniqueId().equals(uniqueId))
      .findFirst()
      .orElse(null);
  }
  
  public String getName() {
    return "La vie de château (de sable)";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 550;
  }
  
  public String getTexture() {
    return "august/sand_castle_life";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SandCastleLifeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */