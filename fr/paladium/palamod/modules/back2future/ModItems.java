package fr.paladium.palamod.modules.back2future;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.back2future.items.Beetroot;
import fr.paladium.palamod.modules.back2future.items.BeetrootSeeds;
import fr.paladium.palamod.modules.back2future.items.BeetrootSoup;
import fr.paladium.palamod.modules.back2future.items.ChorusFruit;
import fr.paladium.palamod.modules.back2future.items.DragonBreath;
import fr.paladium.palamod.modules.back2future.items.Elytra;
import fr.paladium.palamod.modules.back2future.items.EndCrystal;
import fr.paladium.palamod.modules.back2future.items.ItemArmourStand;
import fr.paladium.palamod.modules.back2future.items.LingeringPotion;
import fr.paladium.palamod.modules.back2future.items.MuttonCooked;
import fr.paladium.palamod.modules.back2future.items.MuttonRaw;
import fr.paladium.palamod.modules.back2future.items.PoppedChorusFruit;
import fr.paladium.palamod.modules.back2future.items.PrismarineCrystals;
import fr.paladium.palamod.modules.back2future.items.PrismarineShard;
import fr.paladium.palamod.modules.back2future.items.RabbitCooked;
import fr.paladium.palamod.modules.back2future.items.RabbitFoot;
import fr.paladium.palamod.modules.back2future.items.RabbitHide;
import fr.paladium.palamod.modules.back2future.items.RabbitRaw;
import fr.paladium.palamod.modules.back2future.items.RabbitStew;
import fr.paladium.palamod.modules.back2future.items.TippedArrow;
import java.lang.reflect.Field;
import net.minecraft.item.Item;

public class ModItems {
  public static final Item raw_mutton = (Item)new MuttonRaw();
  
  public static final Item cooked_mutton = (Item)new MuttonCooked();
  
  public static final Item prismarine_shard = (Item)new PrismarineShard();
  
  public static final Item prismarine_crystals = (Item)new PrismarineCrystals();
  
  public static final Item armour_stand = (Item)new ItemArmourStand();
  
  public static final Item raw_rabbit = (Item)new RabbitRaw();
  
  public static final Item cooked_rabbit = (Item)new RabbitCooked();
  
  public static final Item rabbit_foot = (Item)new RabbitFoot();
  
  public static final Item rabbit_hide = (Item)new RabbitHide();
  
  public static final Item rabbit_stew = (Item)new RabbitStew();
  
  public static final Item beetroot = (Item)new Beetroot();
  
  public static final Item beetroot_seeds = (Item)new BeetrootSeeds();
  
  public static final Item beetroot_soup = (Item)new BeetrootSoup();
  
  public static final Item chorus_fruit = (Item)new ChorusFruit();
  
  public static final Item popped_chorus_fruit = (Item)new PoppedChorusFruit();
  
  public static final Item tipped_arrow = (Item)new TippedArrow();
  
  public static final Item lingering_potion = (Item)new LingeringPotion();
  
  public static final Item dragon_breath = (Item)new DragonBreath();
  
  public static final Item elytra = (Item)new Elytra();
  
  public static final Item end_crystal = (Item)new EndCrystal();
  
  public static void init() {
    try {
      for (Field f : ModItems.class.getDeclaredFields()) {
        Object obj = f.get(null);
        if (obj instanceof Item) {
          registerItem((Item)obj);
        } else if (obj instanceof Item[]) {
          for (Item item : (Item[])obj)
            registerItem(item); 
        } 
      } 
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } 
  }
  
  private static void registerItem(Item item) {
    if (!(item instanceof IConfigurable) || ((IConfigurable)item).isEnabled()) {
      String name = item.func_77658_a();
      String[] strings = name.split("\\.");
      GameRegistry.registerItem(item, strings[strings.length - 1]);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\ModItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */