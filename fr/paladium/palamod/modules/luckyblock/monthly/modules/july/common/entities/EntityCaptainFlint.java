package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities;

import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import net.minecraft.world.World;

public class EntityCaptainFlint extends EntityNPC {
  public static final String ENTITY_NAME = "entityCaptainFlint";
  
  public EntityCaptainFlint(World world) {
    super(world);
  }
  
  public EntityCaptainFlint(World world, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract, boolean focus) {
    super(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
    this.focus = focus;
  }
  
  public EntityCaptainFlint(World world, String displayName, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract, boolean focus) {
    super(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
    this.focus = focus;
    func_94058_c(displayName);
    func_94061_f(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\entities\EntityCaptainFlint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */