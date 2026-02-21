package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.RabbitRenderer;
import fr.paladium.palamod.modules.luckyblock.entity.EntityMineralRabbit;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class RenderMineralRabbit extends RabbitRenderer {
  private static Map<Item, ResourceLocation> textures = new HashMap<>();
  
  public static void initTextures() {
    textures.put(Items.field_151045_i, new ResourceLocation("palamod:textures/entity/mineral_rabbit/diamond.png"));
    textures.put(ItemsRegister.PALADIUM_INGOT, new ResourceLocation("palamod:textures/entity/mineral_rabbit/paladium.png"));
    textures.put(ItemsRegister.TITANE_INGOT, new ResourceLocation("palamod:textures/entity/mineral_rabbit/titane.png"));
    textures.put(ItemsRegister.AMETHYST_INGOT, new ResourceLocation("palamod:textures/entity/mineral_rabbit/amethyst.png"));
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    EntityMineralRabbit rabbit = (EntityMineralRabbit)entity;
    Item mineral = rabbit.getMineral();
    if (textures.containsKey(mineral))
      return textures.get(mineral); 
    return new ResourceLocation("textures/entity/rabbit/brown.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderMineralRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */