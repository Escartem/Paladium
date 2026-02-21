package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data.FlowerData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChampDeFleur extends ATickable<HashSet<FlowerData>> {
  private static final String[] SCHEMATIC_NAMES = new String[] { "flower1", "flower2", "flower3", "flower4" };
  
  private static ChampDeFleur instance;
  
  private final Random random;
  
  public static ChampDeFleur getInstance() {
    return instance;
  }
  
  public ChampDeFleur() {
    super(new HashSet(), 1000L);
    instance = this;
    this.random = new Random();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int structureY = 250;
    DoubleLocation location = new DoubleLocation(x, 250.0D, z);
    Schematic currentSchematic = SchematicUtils.loadSchematic(getRandomSchematicName());
    if (currentSchematic == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de charger la structure." });
      return;
    } 
    StructureUtils.pasteTimedAsync((EntityPlayer)player, currentSchematic, location, TimeUnit.MINUTES
        
        .toMillis(5L), false, timed -> {
          if (timed == null) {
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
            return;
          } 
          ((HashSet<FlowerData>)this.data).add(new FlowerData(player, timed));
          BlockPos middleTop = currentSchematic.getCenter();
          MonthlyUtils.teleport((EntityPlayer)player, middleTop.getX(), middleTop.getY(), middleTop.getZ());
          giveSilkTouchItem(player);
        });
  }
  
  private void giveSilkTouchItem(EntityPlayerMP player) {
    ItemStack stack = new ItemStack(Items.field_151050_s);
    stack.func_77966_a(Enchantment.field_77348_q, 1);
    ItemUtils.spawnItemAtEntity((Entity)player, stack);
  }
  
  protected void onTick(long now) {
    if (((HashSet)this.data).isEmpty())
      return; 
    ((HashSet)this.data).removeIf(lavaData -> lavaData.isExpired(MonthlyUtils.getPlayer(lavaData.getPlayerUniqueId()), now));
  }
  
  private String getRandomSchematicName() {
    return SCHEMATIC_NAMES[this.random.nextInt(SCHEMATIC_NAMES.length)];
  }
  
  public String getName() {
    return "Champ de fleur";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "may/champ_de_fleur";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\ChampDeFleur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */