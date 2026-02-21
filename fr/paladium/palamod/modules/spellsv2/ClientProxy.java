package fr.paladium.palamod.modules.spellsv2;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palamod.modules.spellsv2.entity.EntityEgg;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.render.RenderEntityEgg;
import fr.paladium.palamod.modules.spellsv2.render.RenderEntityGhost;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.CustomMovementInput;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.Render;

public class ClientProxy extends CommonProxy {
  public static CustomMovementInput customMovementInput;
  
  public static Map<Spells, LocalDateTime> spellStarting = new HashMap<>();
  
  public static Map<Spells, LocalDateTime> spellEnding = new HashMap<>();
  
  public static boolean showHotbar = true;
  
  public static boolean frozen;
  
  public static int spooky = 0;
  
  public static LocalDateTime frozenStart;
  
  public static LocalDateTime frozenEnd;
  
  public void registerRenders() {
    Minecraft mc = Minecraft.func_71410_x();
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, (Render)new RenderEntityGhost());
    RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, (Render)new RenderEntityEgg());
    ClientManager.loadMacro();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */