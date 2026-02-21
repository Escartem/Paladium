package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.items.LingeringPotion;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityLingeringEffect extends Entity implements IEntityAdditionalSpawnData {
  private static final int TICKS_DATA_WATCHER = 10;
  
  private static final int WIDTH_DATA_WATCHER = 11;
  
  private static final int HEIGHT_DATA_WATCHER = 12;
  
  private EntityLivingBase thrower;
  
  private ItemStack stack;
  
  private final int MAX_TICKS = 600;
  
  public EntityLingeringEffect(World world) {
    super(world);
    this.field_70129_M = 0.0F;
    func_70105_a(1.0F, 1.0F);
  }
  
  public EntityLingeringEffect(World world, EntityLingeringPotion potion) {
    this(world, potion.getStack(), potion.func_85052_h());
    func_70107_b(potion.field_70165_t, potion.field_70163_u, potion.field_70161_v);
  }
  
  public EntityLingeringEffect(World world, ItemStack stack, EntityLivingBase thrower) {
    this(world);
    this.stack = stack;
    this.thrower = thrower;
  }
  
  public boolean func_70104_M() {
    return true;
  }
  
  public void func_70108_f(Entity e) {
    if (!(e instanceof EntityLivingBase))
      return; 
    EntityLivingBase entity = (EntityLivingBase)e;
    List<PotionEffect> effects = ((LingeringPotion)ModItems.lingering_potion).func_77832_l(this.stack);
    boolean addedEffect = false;
    for (PotionEffect effect : effects) {
      int effectID = effect.func_76456_a();
      if (Potion.field_76425_a[effectID].func_76403_b()) {
        Potion.field_76425_a[effectID].func_76402_a(this.thrower, entity, effect.func_76458_c(), 0.25D);
        addedEffect = true;
        continue;
      } 
      if (!entity.func_82165_m(effectID)) {
        entity.func_70690_d(effect);
        addedEffect = true;
      } 
    } 
    if (addedEffect) {
      int ticks = this.field_70180_af.func_75679_c(10);
      if (setTickCount(ticks + 100))
        return; 
    } 
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_75682_a(10, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(11, Float.valueOf(6.0F));
    this.field_70180_af.func_75682_a(12, Float.valueOf(0.5F));
  }
  
  public void func_70071_h_() {
    int ticks = this.field_70180_af.func_75679_c(10);
    if (this.field_70170_p.field_72995_K) {
      float w = this.field_70180_af.func_111145_d(11);
      if (w != this.field_70130_N)
        this.field_70130_N = w; 
      float h = this.field_70180_af.func_111145_d(12);
      if (h != this.field_70131_O)
        this.field_70131_O = h; 
      if (this.field_70173_aa % 5 == 0) {
        double radius = 3.0D * (600 - ticks) / 600.0D;
        int colour = this.stack.func_77973_b().func_82790_a(this.stack, 0);
        float red = (colour >> 16 & 0xFF) / 255.0F;
        float green = (colour >> 8 & 0xFF) / 255.0F;
        float blue = (colour >> 0 & 0xFF) / 255.0F;
        for (int i = 0; i < 30; i++) {
          float variation = 0.75F + this.field_70146_Z.nextFloat() * 0.25F;
          this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t - radius + this.field_70146_Z.nextFloat() * radius * 2.0D, this.field_70163_u, this.field_70161_v - radius + this.field_70146_Z
              .nextFloat() * radius * 2.0D, (red * variation), (green * variation), (blue * variation));
        } 
      } 
      return;
    } 
    setTickCount(++ticks);
  }
  
  private boolean setTickCount(int ticks) {
    this.field_70180_af.func_75692_b(10, Integer.valueOf(ticks));
    if (ticks >= 600) {
      func_70106_y();
      return true;
    } 
    double radius = 3.0D * (600 - ticks) / 600.0D;
    func_70105_a((float)radius * 2.0F, 0.5F);
    return false;
  }
  
  public void func_70091_d(double x, double y, double z) {}
  
  public void func_70024_g(double x, double y, double z) {}
  
  protected void func_70105_a(float width, float height) {
    super.func_70105_a(width, height);
    this.field_70180_af.func_75692_b(11, Float.valueOf(this.field_70130_N));
    this.field_70180_af.func_75692_b(12, Float.valueOf(this.field_70131_O));
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    ByteBufUtils.writeItemStack(buffer, this.stack);
  }
  
  public void readSpawnData(ByteBuf buffer) {
    this.stack = ByteBufUtils.readItemStack(buffer);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    setTickCount(nbt.func_74762_e("Ticks"));
    this.stack = ItemStack.func_77949_a(nbt.func_74775_l("Potion"));
    if (this.stack == null)
      func_70106_y(); 
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    nbt.func_74768_a("Ticks", this.field_70180_af.func_75679_c(10));
    if (this.stack != null)
      nbt.func_74782_a("Potion", (NBTBase)this.stack.func_77955_b(new NBTTagCompound())); 
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public EntityLivingBase getThrower() {
    return this.thrower;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityLingeringEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */