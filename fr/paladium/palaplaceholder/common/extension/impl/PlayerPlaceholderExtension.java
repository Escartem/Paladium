package fr.paladium.palaplaceholder.common.extension.impl;

import fr.paladium.palaplaceholder.common.extension.PlaceholderExtension;
import fr.paladium.palaplaceholder.common.extension.placeholder.Placeholder;
import java.text.DecimalFormat;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerPlaceholderExtension extends PlaceholderExtension {
  private final DecimalFormat formatter = new DecimalFormat("0.00");
  
  public String getIdentifier() {
    return "player";
  }
  
  public void init() {
    registerPlaceholder(new Placeholder[] { Placeholder.create().key("displayname").consumer(EntityPlayer::func_70005_c_), 
          Placeholder.create().key("uuid").consumer(player -> player.func_110124_au().toString()), 
          Placeholder.create().key("x").consumer(player -> this.formatter.format(player.field_70165_t)), 
          Placeholder.create().key("y").consumer(player -> this.formatter.format(player.field_70163_u)), 
          Placeholder.create().key("z").consumer(player -> this.formatter.format(player.field_70161_v)), 
          Placeholder.create().key("bed_x").consumer(player -> this.formatter.format((player.getBedLocation(player.field_71093_bK) == null) ? 0.0D : (player.getBedLocation(player.field_71093_bK)).field_71574_a)), 
          Placeholder.create().key("bed_y").consumer(player -> this.formatter.format((player.getBedLocation(player.field_71093_bK) == null) ? 0.0D : (player.getBedLocation(player.field_71093_bK)).field_71572_b)), 
          Placeholder.create().key("bed_z").consumer(player -> this.formatter.format((player.getBedLocation(player.field_71093_bK) == null) ? 0.0D : (player.getBedLocation(player.field_71093_bK)).field_71573_c)) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\common\extension\impl\PlayerPlaceholderExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */