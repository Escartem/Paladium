package fr.paladium.palamod.modules.end.server.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkUtils {
  private static FireworkUtils instance;
  
  private final List<Color> colors;
  
  private final List<FireworkEffect.Type> types;
  
  public FireworkUtils() {
    this.colors = new ArrayList<>();
    this.types = new ArrayList<>();
    this.types.add(FireworkEffect.Type.BALL_LARGE);
    registerColors();
  }
  
  public void registerColors() {
    this.colors.add(Color.LIME);
    this.colors.add(Color.MAROON);
    this.colors.add(Color.YELLOW);
    this.colors.add(Color.SILVER);
    this.colors.add(Color.TEAL);
    this.colors.add(Color.ORANGE);
    this.colors.add(Color.PURPLE);
    this.colors.add(Color.RED);
    this.colors.add(Color.GREEN);
    this.colors.add(Color.AQUA);
    this.colors.add(Color.BLUE);
    this.colors.add(Color.FUCHSIA);
    this.colors.add(Color.GRAY);
    this.colors.add(Color.OLIVE);
    this.colors.add(Color.NAVY);
    this.colors.add(Color.BLACK);
  }
  
  public Color getRandomColor() {
    int size = this.colors.size();
    Random ran = new Random();
    return this.colors.get(ran.nextInt(size));
  }
  
  public void launchRandomFirework(Location location) {
    Firework firework = (Firework)location.getWorld().spawn(location, Firework.class);
    FireworkMeta fireworkMeta = firework.getFireworkMeta();
    fireworkMeta.addEffects(new FireworkEffect[] { FireworkEffect.builder().with(this.types.get(0)).withColor(getRandomColor()).build() });
    firework.setFireworkMeta(fireworkMeta);
    try {
      Object entityFirework = firework.getClass().getMethod("getHandle", new Class[0]).invoke(firework, new Object[0]);
      Field lifespan = entityFirework.getClass().getDeclaredField("field_92055_b");
      lifespan.setAccessible(true);
      lifespan.set(entityFirework, Integer.valueOf(2));
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
  
  public static FireworkUtils getInstance() {
    if (instance == null)
      return instance = new FireworkUtils(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\serve\\utils\FireworkUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */