package fr.paladium.palaforgeutils.lib.firework;

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
  private static final Random random = new Random();
  
  private static final List<Color> colors = new ArrayList<>();
  
  private static final List<FireworkEffect.Type> types = new ArrayList<>();
  
  static {
    types.add(FireworkEffect.Type.BALL_LARGE);
    registerColors();
  }
  
  private static void registerColors() {
    colors.add(Color.LIME);
    colors.add(Color.MAROON);
    colors.add(Color.YELLOW);
    colors.add(Color.SILVER);
    colors.add(Color.TEAL);
    colors.add(Color.ORANGE);
    colors.add(Color.PURPLE);
    colors.add(Color.RED);
    colors.add(Color.GREEN);
    colors.add(Color.AQUA);
    colors.add(Color.BLUE);
    colors.add(Color.FUCHSIA);
    colors.add(Color.OLIVE);
    colors.add(Color.NAVY);
  }
  
  private static Color getRandomColor() {
    int size = colors.size();
    return colors.get(random.nextInt(size));
  }
  
  public static void launchRandomFirework(Location location) {
    Firework firework = (Firework)location.getWorld().spawn(location, Firework.class);
    FireworkMeta fireworkMeta = firework.getFireworkMeta();
    fireworkMeta.addEffects(new FireworkEffect[] { FireworkEffect.builder().with(types.get(0)).withColor(getRandomColor()).build() });
    firework.setFireworkMeta(fireworkMeta);
    try {
      Object entityFirework = firework.getClass().getMethod("getHandle", new Class[0]).invoke(firework, new Object[0]);
      Field lifespan = entityFirework.getClass().getDeclaredField("field_92055_b");
      lifespan.setAccessible(true);
      lifespan.set(entityFirework, Integer.valueOf(2));
    } catch (NoClassDefFoundError|Exception error) {
      error.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\firework\FireworkUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */