package fr.paladium.palamod.modules.palaboss.common.entity.boss;

import com.google.gson.JsonObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.HurtBossAchievement;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.attacks.Attack;
import fr.paladium.palamod.modules.palaboss.common.boss.BossAttributes;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityBossBase extends EntityMob {
  private BossAttributes attributes;
  
  private final List<Attack> attacks;
  
  private int punchTimer;
  
  private int lastAttack;
  
  private boolean main = false;
  
  private List<ItemStack> loots;
  
  public boolean isMain() {
    return this.main;
  }
  
  public void setMain(boolean main) {
    this.main = main;
  }
  
  public List<ItemStack> getLoots() {
    return this.loots;
  }
  
  public void setLoots(List<ItemStack> loots) {
    this.loots = loots;
  }
  
  public EntityBossBase(World world) {
    super(world);
    this.field_70178_ae = true;
    this.attributes = BossRegistry.INSTANCE.getBossAttributes(getClass());
    int sizeMultiplier = this.attributes.getAttributeInteger("sizeMultiplier", 1);
    func_70105_a(getEntityWidth() * sizeMultiplier, getEntityHeight() * sizeMultiplier);
    this.attacks = new ArrayList<>();
    fillAttacks();
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    if (compound.func_74764_b("main"))
      this.main = compound.func_74767_n("main"); 
    if (compound.func_74764_b("loots")) {
      this.loots = new ArrayList<>();
      NBTTagList list = compound.func_150295_c("loots", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound itemTag = list.func_150305_b(i);
        this.loots.add(ItemStack.func_77949_a(itemTag));
      } 
    } 
    super.func_70020_e(compound);
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    compound.func_74757_a("main", this.main);
    if (this.loots != null) {
      NBTTagList list = new NBTTagList();
      for (ItemStack item : this.loots) {
        if (item != null) {
          NBTTagCompound itemTag = new NBTTagCompound();
          item.func_77955_b(itemTag);
          list.func_74742_a((NBTBase)itemTag);
        } 
      } 
      compound.func_74782_a("loots", (NBTBase)list);
    } 
    super.func_70109_d(compound);
  }
  
  private void fillAttacks() {
    this.attacks.clear();
    if (this.attributes == null)
      this.attributes = BossRegistry.INSTANCE.getBossAttributes(getClass()); 
    for (Map.Entry<Attack, JsonObject> entry : (Iterable<Map.Entry<Attack, JsonObject>>)this.attributes.getAttacks().entrySet())
      this.attacks.add(entry.getKey()); 
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    this.attributes = BossRegistry.INSTANCE.getBossAttributes(getClass());
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.attributes.getAttributeDouble("maxHealth", 100.0D) * ((this.loots != null) ? 0.3D : 1.0D));
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.attributes.getAttributeDouble("attackDamage", 10.0D) * ((this.loots != null) ? 0.3D : 1.0D));
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.attributes.getAttributeDouble("movementSpeed", 0.8D));
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(this.attributes.getAttributeDouble("knockbackResistance", Double.MAX_VALUE));
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(this.attributes.getAttributeDouble("followRange", 40.0D));
  }
  
  public void func_70653_a(Entity entity, float p_70653_2_, double p_70653_3_, double p_70653_5_) {}
  
  public void func_70636_d() {
    super.func_70636_d();
    if (this.punchTimer > 0)
      this.punchTimer--; 
    if (this.loots == null) {
      if (!this.field_70170_p.field_72995_K && !this.field_70128_L && (PPalaBoss.commonConfig == null || this.field_70173_aa - this.lastAttack > PPalaBoss.commonConfig.getDespawnTime() * 60 * 20))
        func_70097_a(DamageSource.field_76376_m, Float.MAX_VALUE); 
      if (!this.field_70170_p.field_72995_K && (!isMain() || !PPalaBoss.serverBossData.isActive() || PPalaBoss.serverBossData.getUuid() == null))
        func_70106_y(); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte p_70103_1_) {
    if (p_70103_1_ == 4) {
      this.punchTimer = 10;
      func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
    } else {
      super.func_70103_a(p_70103_1_);
    } 
  }
  
  public void func_70645_a(DamageSource p_70645_1_) {
    if (!this.field_70170_p.field_72995_K && !this.field_70128_L)
      if (this.loots != null) {
        for (ItemStack item : this.loots) {
          if (item != null)
            func_70099_a(item, 0.0F); 
        } 
      } else {
        int random = this.field_70170_p.field_73012_v.nextInt(1000);
        if (random <= 10) {
          func_145779_a(ItemsRegister.ENDIUM_NUGGET, 1);
          func_145779_a(ItemsRegister.PALADIUM_GREEN_INGOT, 1);
        } else if (random <= 30) {
          func_145779_a(ItemsRegister.ENDIUM_FRAGMENT, 3);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM), 64);
          PPalaDynamique.instance.addGenerated("BOSS", 576.0D);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM), 64);
          func_145779_a(Item.func_150898_a((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK), 5);
          func_70099_a(new ItemStack(Items.field_151144_bL, 3, 1), 1.0F);
        } else if (random <= 80) {
          func_145779_a(ItemsRegister.ENDIUM_FRAGMENT, 1);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM), 64);
          PPalaDynamique.instance.addGenerated("BOSS", 576.0D);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM), 64);
          func_70099_a(new ItemStack(Items.field_151144_bL, 3, 1), 1.0F);
        } else if (random <= 280) {
          func_145779_a(ItemsRegister.SPELL_SCROLL, 1);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM), 48);
          PPalaDynamique.instance.addGenerated("BOSS", 432.0D);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM), 48);
          func_70099_a(new ItemStack(Items.field_151144_bL, 3, 1), 1.0F);
        } else if (random <= 630) {
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM), 32);
          PPalaDynamique.instance.addGenerated("BOSS", 288.0D);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM), 32);
          func_70099_a(new ItemStack(Items.field_151144_bL, 3, 1), 1.0F);
          ItemStack item = new ItemStack(ItemsRegister.BIG_RING);
          item.func_77966_a(Enchantment.field_77347_r, 2);
          func_70099_a(item, 0.0F);
        } else if (random <= 1000) {
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM), 20);
          PPalaDynamique.instance.addGenerated("BOSS", 180.0D);
          func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM), 20);
          ItemStack item = new ItemStack(ItemsRegister.PALADIUM_HELMET);
          item.func_77966_a(Enchantment.field_77332_c, 4);
          item.func_77966_a(Enchantment.field_77347_r, 2);
          func_70099_a(item, 0.0F);
          item = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
          item.func_77966_a(Enchantment.field_77332_c, 4);
          item.func_77966_a(Enchantment.field_77347_r, 2);
          func_70099_a(item, 0.0F);
          item = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
          item.func_77966_a(Enchantment.field_77332_c, 4);
          item.func_77966_a(Enchantment.field_77347_r, 2);
          func_70099_a(item, 0.0F);
          item = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
          item.func_77966_a(Enchantment.field_77332_c, 4);
          item.func_77966_a(Enchantment.field_77347_r, 2);
          func_70099_a(item, 0.0F);
          item = new ItemStack(ItemsRegister.PALADIUM_SWORD);
          item.func_77966_a(Enchantment.field_77338_j, 4);
          item.func_77966_a(Enchantment.field_77347_r, 2);
          item.func_77966_a(Enchantment.field_77346_s, 1);
          func_70099_a(item, 0.0F);
        } 
        func_145779_a(ItemsRegister.TRIXIUM, 5);
        func_145779_a(Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM), 55);
      }  
    super.func_70645_a(p_70645_1_);
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    if (source.func_76346_g() != null) {
      if (source.func_76346_g() instanceof EntityPlayer) {
        EntityPlayer p = (EntityPlayer)source.func_76346_g();
        HurtBossAchievement.performCheck(p);
        if (((EntityPlayer)source.func_76346_g()).field_71075_bZ.field_75098_d)
          return super.func_70097_a(source, f); 
      } 
      if (source.func_76346_g() instanceof EntityLivingBase) {
        func_70784_b(source.func_76346_g());
        func_70624_b((EntityLivingBase)source.func_76346_g());
      } 
    } 
    this.lastAttack = this.field_70173_aa;
    return super.func_70097_a(source, f);
  }
  
  public List<Attack> getAttacks() {
    if (this.attacks == null)
      fillAttacks(); 
    return this.attacks;
  }
  
  public BossAttributes getAttributes() {
    return this.attributes;
  }
  
  public void setPunchTimer(int punchTimer) {
    this.punchTimer = punchTimer;
  }
  
  public int getPunchTimer() {
    return this.punchTimer;
  }
  
  public String name() {
    return func_70005_c_();
  }
  
  public void func_70015_d(int p_70015_1_) {}
  
  public boolean func_70058_J() {
    return false;
  }
  
  protected void func_70081_e(int p_70081_1_) {}
  
  protected void func_70044_A() {}
  
  public boolean func_90999_ad() {
    return false;
  }
  
  public abstract float getEntityWidth();
  
  public abstract float getEntityHeight();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\boss\EntityBossBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */