package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.common.items.BaseItemSeed;
import fr.paladium.tutorial.common.event.SeedPlanterUseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class SeedPlanterSeedFood extends BaseItem implements ITooltipWiki {
  public int r = 0;
  
  public int lvlmin = 0;
  
  public ArrayList<String> addInformation;
  
  private HashMap<Integer, ItemStack> seeds = new HashMap<>();
  
  public SeedPlanterSeedFood(String unlocalizedName, int radius, int lvlmin, ArrayList<String> information) {
    super("seedplanter/" + unlocalizedName);
    this.r = radius;
    this.lvlmin = lvlmin;
    this.addInformation = information;
    func_77655_b(unlocalizedName);
    this.seeds.put(Integer.valueOf(0), new ItemStack(Items.field_151014_N));
    this.seeds.put(Integer.valueOf(1), new ItemStack(Items.field_151172_bF));
    this.seeds.put(Integer.valueOf(2), new ItemStack(Items.field_151081_bc));
    this.seeds.put(Integer.valueOf(3), new ItemStack(Items.field_151080_bb));
    this.seeds.put(Integer.valueOf(4), new ItemStack(Items.field_151075_bm));
    this.seeds.put(Integer.valueOf(5), new ItemStack(Items.field_151174_bG));
    this.seeds.put(Integer.valueOf(6), new ItemStack((Item)ItemsRegister.SEED_CHERVIL));
    this.seeds.put(Integer.valueOf(7), new ItemStack((Item)ItemsRegister.SEED_EGGPLANT));
    this.seeds.put(Integer.valueOf(8), new ItemStack((Item)ItemsRegister.SEED_ICE));
    this.seeds.put(Integer.valueOf(9), new ItemStack((Item)ItemsRegister.SEED_KIWANO));
    this.seeds.put(Integer.valueOf(10), new ItemStack((Item)ItemsRegister.SEED_ORANGEBLUE));
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      MovingObjectPosition movingObjectPosition = func_77621_a(world, player, true);
      if (player.func_70093_af()) {
        if (item.func_77942_o() && item.func_77978_p().func_74764_b("seed")) {
          NBTTagCompound tag = item.func_77978_p();
          int index = tag.func_74762_e("seed") + 1;
          tag.func_74768_a("seed", (index > this.seeds.size() - 1) ? 0 : index);
          player.func_146105_b((IChatComponent)new ChatComponentText("§eGraine sélectionnée: §a" + ((ItemStack)this.seeds.get(Integer.valueOf(tag.func_74762_e("seed")))).func_82833_r()));
        } else {
          NBTTagCompound tag = new NBTTagCompound();
          tag.func_74768_a("seed", 0);
          item.func_77982_d(tag);
        } 
        return super.func_77659_a(item, world, player);
      } 
      if (item.func_77942_o() && item.func_77978_p().func_74764_b("seed")) {
        NBTTagCompound tag = item.func_77978_p();
        int index = tag.func_74762_e("seed");
        if (movingObjectPosition != null && movingObjectPosition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
          ItemStack seedStack = this.seeds.get(Integer.valueOf(index));
          boolean hasItem = false;
          int rf = this.r / 2;
          for (int x = rf * -1; x <= rf; x++) {
            for (int z = rf * -1; z <= rf; z++) {
              int posX = movingObjectPosition.field_72311_b + x;
              int posY = movingObjectPosition.field_72312_c + 1;
              int posZ = movingObjectPosition.field_72309_d + z;
              for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
                ItemStack itemStack = player.field_71071_by.field_70462_a[i];
                if (itemStack != null && itemStack.func_77969_a(seedStack)) {
                  hasItem = true;
                  Block crops = Blocks.field_150464_aj;
                  if (itemStack.func_77973_b() instanceof ItemSeeds) {
                    ItemSeeds itemSeeds = (ItemSeeds)itemStack.func_77973_b();
                    crops = itemSeeds.getPlant(null, 0, 0, 0);
                  } else if (itemStack.func_77973_b() instanceof BaseItemSeed) {
                    BaseItemSeed itemSeeds = (BaseItemSeed)itemStack.func_77973_b();
                    crops = itemSeeds.getPlant(null, 0, 0, 0);
                  } else if (itemStack.func_77973_b() instanceof ItemSeedFood) {
                    ItemSeedFood itemSeeds = (ItemSeedFood)itemStack.func_77973_b();
                    crops = itemSeeds.getPlant(null, 0, 0, 0);
                  } else {
                    return super.func_77659_a(item, world, player);
                  } 
                  if (world.func_147437_c(posX, posY, posZ) && world.func_147439_a(posX, posY - 1, posZ) != null) {
                    boolean canPlant = (world.func_147439_a(posX, posY - 1, posZ) instanceof net.minecraft.block.BlockFarmland || world.func_147439_a(posX, posY - 1, posZ) instanceof net.minecraft.block.BlockSoulSand || world.func_147439_a(posX, posY - 1, posZ) instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt);
                    if (canPlant && itemStack.field_77994_a > 0) {
                      MinecraftForge.EVENT_BUS.post((Event)new SeedPlanterUseEvent(player, posX, posY, posZ, crops));
                      world.func_147449_b(posX, posY, posZ, crops);
                      itemStack.field_77994_a--;
                      if (itemStack.field_77994_a <= 0)
                        player.field_71071_by.func_70299_a(i, null); 
                      break;
                    } 
                  } 
                } 
              } 
            } 
          } 
          if (!hasItem)
            player.func_146105_b((IChatComponent)new ChatComponentText("§cVous ne possédez pas de §e" + seedStack.func_82833_r() + " §cdans votre inventaire.")); 
          player.field_71069_bz.func_75142_b();
        } 
      } else {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74768_a("seed", 0);
        item.func_77982_d(tag);
      } 
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#9.-les-seedplanters";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\SeedPlanterSeedFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */