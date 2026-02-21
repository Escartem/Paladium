package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palajobs.core.pojo.objectives.types.CrusherObjective;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.CrusherAchievement;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.tutorial.common.event.CrusherCraftEvent;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileCrusher extends PaladiumTileInventory implements ISidedInventory {
  public int[] fuelTanks = new int[(EnumCrusherRecipes.values()).length];
  
  public int[] tanks = new int[(EnumCrusherRecipes.values()).length];
  
  private ItemStack lastFuel;
  
  private ItemStack lastFruit;
  
  private EnumCrusherRecipes selectedRecipe = EnumCrusherRecipes.PALADIUM;
  
  private EnumCrusherResult extractItem = EnumCrusherResult.NONE;
  
  private int work = 0;
  
  private int workf = 0;
  
  private UUID ownerId;
  
  public UUID getOwnerId() {
    return this.ownerId;
  }
  
  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }
  
  public TileCrusher() {
    super("tile.crusher", 3);
  }
  
  public void setWork(int work) {
    this.work = work;
  }
  
  public EnumCrusherResult getExtractItem() {
    return this.extractItem;
  }
  
  public int getWork() {
    return this.work;
  }
  
  public void setExtractItem(EnumCrusherResult extractItem) {
    this.extractItem = extractItem;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public void setCraft(EnumCrusherResult extractItem) {
    if (canCraft(extractItem) && this.extractItem == EnumCrusherResult.NONE && func_70301_a(2) == null)
      setExtractItem(extractItem); 
  }
  
  public boolean canCraft(EnumCrusherResult extractItem) {
    return (getTank(extractItem) != 0);
  }
  
  public int getTankEndium() {
    return this.tanks[EnumCrusherRecipes.ENDIUM.ordinal()];
  }
  
  public int getTankPaladium() {
    return this.tanks[EnumCrusherRecipes.PALADIUM.ordinal()];
  }
  
  public int getTankAmethyst() {
    return this.tanks[EnumCrusherRecipes.AMETHYST.ordinal()];
  }
  
  public int getTankTitane() {
    return this.tanks[EnumCrusherRecipes.TITANE.ordinal()];
  }
  
  public void setWorkf(int workf) {
    this.workf = workf;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("work", this.work);
    compound.func_74768_a("workf", this.workf);
    compound.func_74768_a("extract", this.extractItem.ordinal() - 1);
    compound.func_74783_a("tanks", this.tanks);
    compound.func_74783_a("fueltanks", this.fuelTanks);
    if (this.ownerId != null)
      compound.func_74778_a("owner", UUIDUtils.toString(this.ownerId)); 
  }
  
  public int getWorkf() {
    return this.workf;
  }
  
  public float getArrowProgression() {
    return this.workf / 1000.0F;
  }
  
  public float getFuelProgression() {
    return this.work / 100.0F;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.work = compound.func_74762_e("work");
    this.workf = compound.func_74762_e("workf");
    this.extractItem = EnumCrusherResult.values()[compound.func_74762_e("extract") + 1];
    if (compound.func_74764_b("tanks"))
      this.tanks = compound.func_74759_k("tanks"); 
    if (compound.func_74764_b("fueltanks"))
      this.fuelTanks = compound.func_74759_k("fueltanks"); 
    if (compound.func_74764_b("owner"))
      this.ownerId = UUIDUtils.parseUUID(compound.func_74779_i("owner")); 
  }
  
  private void applyPetSkill(ItemStack stack) {
    EntityPlayerMP player = PlayerUtils.getPlayer(this.ownerId);
    if (player == null || stack == null)
      return; 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.CRUSHER_BOOSTER.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    double random = Math.random() * 100.0D;
    if (random > value)
      return; 
    int stackSize = stack.field_77994_a;
    if (stackSize <= 0 || stackSize >= stack.func_77976_d())
      return; 
    stack.field_77994_a++;
  }
  
  public void func_145845_h() {
    if ((this.lastFruit == null && func_70301_a(1) != null) || (this.lastFruit != null && func_70301_a(1) == null) || (this.lastFruit != null && 
      !this.lastFruit.func_77969_a(func_70301_a(1)))) {
      this.work = 0;
      this.lastFruit = func_70301_a(1);
      if (func_70301_a(1) != null) {
        this.selectedRecipe = EnumCrusherRecipes.findCurrentRecipe(func_70301_a(1).func_77973_b());
      } else {
        this.selectedRecipe = EnumCrusherRecipes.PALADIUM;
      } 
    } 
    if (func_70301_a(1) == null) {
      if ((this.lastFuel == null && func_70301_a(0) != null) || (this.lastFuel != null && func_70301_a(0) == null) || (this.lastFruit != null && 
        !this.lastFruit.func_77969_a(func_70301_a(0)))) {
        this.work = 0;
        this.lastFuel = func_70301_a(0);
      } 
    } else {
      this.lastFuel = func_70301_a(0);
    } 
    if (!this.field_145850_b.field_72995_K) {
      if (func_70301_a(1) != null) {
        if (this.tanks[this.selectedRecipe.ordinal()] < this.selectedRecipe.getBarSize())
          if (func_70301_a(0) != null && func_70301_a(0).func_77973_b() == this.selectedRecipe.getFuel()) {
            if (this.work >= 100) {
              this.tanks[this.selectedRecipe.ordinal()] = this.tanks[this.selectedRecipe.ordinal()] + 1;
              func_70298_a(0, 1);
              func_70298_a(1, 1);
              this.work = 0;
            } else {
              this.work++;
            } 
          } else if (this.fuelTanks[this.selectedRecipe.ordinal()] > 0) {
            if (this.work >= 100) {
              this.fuelTanks[this.selectedRecipe.ordinal()] = this.fuelTanks[this.selectedRecipe.ordinal()] - 1;
              this.tanks[this.selectedRecipe.ordinal()] = this.tanks[this.selectedRecipe.ordinal()] + 1;
              func_70298_a(1, 1);
              this.work = 0;
            } else {
              this.work++;
            } 
          }  
      } else if (func_70301_a(0) != null) {
        EnumCrusherRecipes fuel = EnumCrusherRecipes.findRecipeFromFuel(func_70301_a(0).func_77973_b());
        if (fuel != null && this.fuelTanks[fuel.ordinal()] < 64)
          if (this.work >= 100) {
            this.fuelTanks[fuel.ordinal()] = this.fuelTanks[fuel.ordinal()] + 1;
            func_70298_a(0, 1);
            this.work = 0;
          } else {
            this.work++;
          }  
      } 
      if (func_70301_a(2) == null && this.extractItem != EnumCrusherResult.NONE) {
        if (this.workf >= 1000) {
          this.workf = 0;
          int t = getTank(this.extractItem);
          clearTank(this.extractItem);
          if (t <= 0) {
            setExtractItem(EnumCrusherResult.NONE);
            return;
          } 
          if (!this.field_145850_b.field_72995_K) {
            EntityPlayer player = this.field_145850_b.func_72977_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, 50.0D);
            if (t >= this.extractItem.getBarSize()) {
              ItemStack stack = new ItemStack(this.extractItem.getItem(), this.extractItem.getMaxRate());
              applyPetSkill(stack);
              func_70299_a(2, stack);
              if (this.extractItem.getItem().equals(ItemsRegister.PALADIUM_INGOT))
                PPalaDynamique.instance.addGenerated("CRUSHER", this.extractItem.getMaxRate()); 
              if (player != null) {
                CrusherObjective.performCheck(player, stack, stack.field_77994_a);
                CrusherAchievement.performCheck(player, stack);
                MinecraftForge.EVENT_BUS.post((Event)new CrusherCraftEvent(player, stack));
              } 
            } else {
              float progress = 1.0F * t / this.extractItem.getBarSize();
              if (progress > 0.75F) {
                rollRecipe(progress, 75);
              } else if (progress > 0.5F) {
                rollRecipe(progress, 50);
              } else if (progress > 0.25F) {
                rollRecipe(progress, 25);
              } else {
                int r1 = (int)(Math.random() * 10000.0D);
                if (r1 == 0) {
                  ItemStack stack = new ItemStack(this.extractItem.getItem(), 1);
                  func_70299_a(2, stack);
                  if (this.extractItem.getItem().equals(ItemsRegister.PALADIUM_INGOT))
                    PPalaDynamique.instance.addGenerated("CRUSHER", 1.0D); 
                  if (player != null) {
                    CrusherObjective.performCheck(player, stack);
                    CrusherAchievement.performCheck(player, stack);
                    MinecraftForge.EVENT_BUS.post((Event)new CrusherCraftEvent(player, stack));
                  } 
                } else {
                  ItemStack stack = new ItemStack(Blocks.field_150346_d, 1);
                  func_70299_a(2, stack);
                  if (player != null) {
                    CrusherObjective.performCheck(player, stack);
                    CrusherAchievement.performCheck(player, stack);
                    MinecraftForge.EVENT_BUS.post((Event)new CrusherCraftEvent(player, stack));
                  } 
                } 
              } 
            } 
          } 
          setExtractItem(EnumCrusherResult.NONE);
        } else {
          this.workf++;
        } 
      } else if (this.workf != 0) {
        this.workf = 0;
      } 
    } 
  }
  
  private void rollRecipe(float progress, int chance) {
    EntityPlayer player = this.field_145850_b.func_72977_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, 50.0D);
    int r1 = (int)(Math.random() * 100.0D);
    if (r1 < chance) {
      ItemStack stack = new ItemStack(this.extractItem.getItem(), Math.max(1, (int)(this.extractItem.getMaxRate() * progress)));
      func_70299_a(2, stack);
      if (this.extractItem.getItem().equals(ItemsRegister.PALADIUM_INGOT))
        PPalaDynamique.instance.addGenerated("CRUSHER", stack.field_77994_a); 
      if (player != null) {
        CrusherAchievement.performCheck(player, stack);
        CrusherObjective.performCheck(player, stack);
        MinecraftForge.EVENT_BUS.post((Event)new CrusherCraftEvent(player, stack));
      } 
    } else {
      ItemStack stack = new ItemStack(Blocks.field_150346_d, 1);
      func_70299_a(2, stack);
      if (player != null) {
        CrusherAchievement.performCheck(player, stack);
        CrusherObjective.performCheck(player, stack);
        MinecraftForge.EVENT_BUS.post((Event)new CrusherCraftEvent(player, stack));
      } 
    } 
  }
  
  public int getTank(EnumCrusherResult id) {
    if (id == EnumCrusherResult.NONE)
      return -1; 
    return this.tanks[id.ordinal() - 1];
  }
  
  public void clearTank(EnumCrusherResult id) {
    if (id != EnumCrusherResult.NONE)
      this.tanks[id.ordinal() - 1] = 0; 
  }
  
  public EnumCrusherRecipes getSelectedRecipe() {
    return this.selectedRecipe;
  }
  
  public int[] func_94128_d(int side) {
    switch (side) {
      case 0:
        return new int[] { 2 };
      case 5:
        return new int[] { 0 };
      case 4:
        return new int[] { 1 };
    } 
    return new int[0];
  }
  
  public boolean func_102007_a(int slot, ItemStack stack, int side) {
    if (slot == 0)
      return (EnumCrusherRecipes.findRecipeFromFuel(stack.func_77973_b()) != null); 
    if (slot == 1)
      return (EnumCrusherRecipes.findCurrentRecipe(stack.func_77973_b()) != null); 
    return false;
  }
  
  public boolean func_102008_b(int slot, ItemStack stack, int side) {
    return (slot == 2);
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public enum EnumCrusherResult {
    NONE(null, 0),
    ENDIUM((String)ItemsRegister.ENDIUM_NUGGET, 1),
    PALADIUM((String)ItemsRegister.PALADIUM_INGOT, 15),
    TITANE((String)ItemsRegister.TITANE_INGOT, 15),
    AMETHYST((String)ItemsRegister.AMETHYST_INGOT, 5);
    
    private final Item item;
    
    private final int maxRate;
    
    EnumCrusherResult(Item item, int maxRate) {
      this.item = item;
      this.maxRate = maxRate;
    }
    
    public Item getItem() {
      return this.item;
    }
    
    public int getBarSize() {
      return (this == NONE) ? 0 : TileCrusher.EnumCrusherRecipes.values()[ordinal() - 1].getBarSize();
    }
    
    public int getMaxRate() {
      return this.maxRate;
    }
    
    public static EnumCrusherResult findByItem(Item item) {
      for (EnumCrusherResult r : values()) {
        if (r.getItem() == item)
          return r; 
      } 
      return null;
    }
    
    public EnumCrusherResult next() {
      if (ordinal() + 1 >= (values()).length)
        return values()[0]; 
      return values()[ordinal() + 1];
    }
  }
  
  public enum EnumCrusherRecipes {
    ENDIUM((String)ItemsRegister.PALAMIXEDCOAL, ItemsRegister.FRUIT_ORANGEBLUE, 128),
    PALADIUM((String)ItemsRegister.TITANEMIXEDCOAL, ItemsRegister.FRUIT_KIWANO, 64),
    TITANE((String)ItemsRegister.AMETHYSTMIXEDCOAL, ItemsRegister.FRUIT_CHERVIL, 32),
    AMETHYST((String)ItemsRegister.GOLDMIXEDCOAL, ItemsRegister.FRUIT_EGGPLANT, 32);
    
    private final Item fuel;
    
    private final Item fruit;
    
    private final int barSize;
    
    EnumCrusherRecipes(Item fuel, Item fruit, int barSize) {
      this.fuel = fuel;
      this.fruit = fruit;
      this.barSize = barSize;
    }
    
    public Item getFuel() {
      return this.fuel;
    }
    
    public Item getFruit() {
      return this.fruit;
    }
    
    public int getBarSize() {
      return this.barSize;
    }
    
    public TileCrusher.EnumCrusherResult getResult() {
      return TileCrusher.EnumCrusherResult.values()[ordinal() + 1];
    }
    
    public EnumCrusherRecipes next() {
      if (ordinal() + 1 >= (values()).length)
        return values()[0]; 
      return values()[ordinal() + 1];
    }
    
    public static EnumCrusherRecipes findCurrentRecipe(Item fruit) {
      for (EnumCrusherRecipes r : values()) {
        if (r.getFruit() == fruit)
          return r; 
      } 
      return null;
    }
    
    public static EnumCrusherRecipes findRecipeFromFuel(Item fuel) {
      for (EnumCrusherRecipes r : values()) {
        if (r.getFuel() == fuel)
          return r; 
      } 
      return null;
    }
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileCrusher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */