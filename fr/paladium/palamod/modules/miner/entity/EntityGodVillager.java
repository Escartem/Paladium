package fr.paladium.palamod.modules.miner.entity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityGodVillager extends EntityAgeable implements IMerchant, INpc, IEntityAdditionalSpawnData {
  private EntityPlayer buyingPlayer;
  
  private MerchantRecipeList buyingList;
  
  private int timeUntilReset;
  
  private boolean needsInitilization;
  
  private int wealth;
  
  private String lastBuyingPlayer;
  
  private boolean isLookingForHome;
  
  private int type;
  
  public EntityPlayer getBuyingPlayer() {
    return this.buyingPlayer;
  }
  
  public MerchantRecipeList getBuyingList() {
    return this.buyingList;
  }
  
  public int getTimeUntilReset() {
    return this.timeUntilReset;
  }
  
  public boolean isNeedsInitilization() {
    return this.needsInitilization;
  }
  
  public int getWealth() {
    return this.wealth;
  }
  
  public String getLastBuyingPlayer() {
    return this.lastBuyingPlayer;
  }
  
  public boolean isLookingForHome() {
    return this.isLookingForHome;
  }
  
  public int getType() {
    return this.type;
  }
  
  public EntityGodVillager(World world) {
    this(world, 0);
  }
  
  public EntityGodVillager(World world, int type) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    func_70661_as().func_75498_b(true);
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMoveIndoors((EntityCreature)this));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.6D));
    this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0F, 1.0F));
    this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityVillager.class, 5.0F, 0.02F));
    this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6D));
    this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0F));
    func_94058_c((this.type == 0) ? "§6God Villager" : ((this.type == 1) ? "§cChristmas Villager" : ((this.type == 2) ? "§6Voyante" : "§6Villa-nul")));
  }
  
  public void setType(int type) {
    this.type = type;
    func_94058_c((type == 0) ? "§6God Villager" : ((type == 1) ? "§cChristmas Villager" : ((type == 2) ? "§6Voyante" : "§6Villa-nul")));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  protected void func_70629_bd() {
    if (!isTrading() && this.timeUntilReset > 0) {
      this.timeUntilReset--;
      if (this.timeUntilReset <= 0) {
        if (this.needsInitilization) {
          if (this.buyingList.size() > 1) {
            Iterator<?> iterator = this.buyingList.iterator();
            while (iterator.hasNext()) {
              MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();
              if (merchantrecipe.func_82784_g())
                merchantrecipe.func_82783_a(this.field_70146_Z.nextInt(6) + this.field_70146_Z.nextInt(6) + 2); 
            } 
          } 
          addDefaultEquipmentAndRecipies();
          this.needsInitilization = false;
        } 
        func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, 0));
      } 
    } 
    super.func_70629_bd();
  }
  
  public boolean func_70085_c(EntityPlayer p_70085_1_) {
    ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
    boolean flag = (itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx);
    if (flag || !func_70089_S() || isTrading() || func_70631_g_() || p_70085_1_.func_70093_af())
      return super.func_70085_c(p_70085_1_); 
    if (!this.field_70170_p.field_72995_K) {
      func_70932_a_(p_70085_1_);
      if (this.type == 2) {
        func_70106_y();
        PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_VOYANT, true), (EntityPlayerMP)p_70085_1_);
      } else {
        p_70085_1_.func_71030_a(this, func_94057_bL());
      } 
    } 
    return true;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    super.func_70014_b(p_70014_1_);
    p_70014_1_.func_74768_a("Riches", this.wealth);
    if (this.buyingList != null)
      p_70014_1_.func_74782_a("Offers", (NBTBase)this.buyingList.func_77202_a()); 
    p_70014_1_.func_74768_a("Type", this.type);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.wealth = nbt.func_74762_e("Riches");
    if (nbt.func_150297_b("Offers", 10)) {
      NBTTagCompound nbttagcompound1 = nbt.func_74775_l("Offers");
      this.buyingList = new MerchantRecipeList(nbttagcompound1);
    } 
    if (nbt.func_74764_b("Type"))
      this.type = nbt.func_74762_e("Type"); 
  }
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  protected String func_70639_aQ() {
    return isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
  }
  
  protected String func_70621_aR() {
    return "mob.villager.hit";
  }
  
  protected String func_70673_aS() {
    return "mob.villager.death";
  }
  
  public void func_70932_a_(EntityPlayer p_70932_1_) {
    this.buyingPlayer = p_70932_1_;
  }
  
  public EntityPlayer func_70931_l_() {
    return this.buyingPlayer;
  }
  
  public boolean isTrading() {
    return (this.buyingPlayer != null);
  }
  
  public void func_70933_a(MerchantRecipe p_70933_1_) {
    p_70933_1_.func_77399_f();
    if (p_70933_1_.func_77393_a((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) {
      this.timeUntilReset = 40;
      this.needsInitilization = true;
      if (this.buyingPlayer != null) {
        this.lastBuyingPlayer = this.buyingPlayer.func_70005_c_();
      } else {
        this.lastBuyingPlayer = null;
      } 
    } 
    if (p_70933_1_.func_77394_a().func_77973_b() == Items.field_151166_bC)
      this.wealth += (p_70933_1_.func_77394_a()).field_77994_a; 
    if (this.lastBuyingPlayer != null && 
      this.field_70170_p.func_72924_a(this.lastBuyingPlayer) != null)
      this.field_70170_p.func_72924_a(this.lastBuyingPlayer).func_71053_j(); 
    EventUtils.spawnParticle(this.field_70170_p, "smoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1000, 0.5D);
    EventUtils.spawnParticle(this.field_70170_p, "flame", this.field_70165_t, this.field_70163_u, this.field_70161_v, 400, 0.1D);
    this.field_70757_a = -func_70627_aG();
    func_85030_a("mob.villager.yes", func_70599_aP(), func_70647_i());
    if (!this.field_70170_p.field_72995_K && this.buyingPlayer != null)
      JobsPlayer.get((Entity)this.buyingPlayer).addXp(JobType.MINER, ObjectiveType.FISH, 15000.0D, this.buyingPlayer); 
    func_70106_y();
  }
  
  public void func_110297_a_(ItemStack p_110297_1_) {
    if (!this.field_70170_p.field_72995_K && this.field_70757_a > -func_70627_aG() + 20) {
      this.field_70757_a = -func_70627_aG();
      if (p_110297_1_ != null) {
        func_85030_a("mob.villager.yes", func_70599_aP(), func_70647_i());
      } else {
        func_85030_a("mob.villager.no", func_70599_aP(), func_70647_i());
      } 
    } 
  }
  
  public MerchantRecipeList func_70934_b(EntityPlayer p_70934_1_) {
    if (this.buyingList == null)
      addDefaultEquipmentAndRecipies(); 
    return this.buyingList;
  }
  
  private void addDefaultEquipmentAndRecipies() {
    this.buyingList = new MerchantRecipeList();
    if (this.type == 0) {
      MerchantRecipe recipe = new MerchantRecipe(new ItemStack(BlocksRegister.BLOCK_PALADIUM, 64), new ItemStack(ItemsRegister.ENDIUM_NUGGET));
      recipe.func_82783_a(1);
      this.buyingList.add(recipe);
    } 
    if (this.type == 1) {
      MerchantRecipe recipe1 = new MerchantRecipe(new ItemStack(ItemsRegister.ENDIUM_INGOT, 1), new ItemStack(Blocks.field_150346_d));
      recipe1.func_82783_a(1);
      this.buyingList.add(recipe1);
      MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(BlocksRegister.BLOCK_PALADIUM, 1), new ItemStack(Blocks.field_150347_e));
      recipe2.func_82783_a(1);
      this.buyingList.add(recipe2);
      MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT, 1), new ItemStack((Item)Items.field_151021_T));
      recipe3.func_82783_a(1);
      this.buyingList.add(recipe3);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70930_a(MerchantRecipeList p_70930_1_) {}
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte p_70103_1_) {
    if (p_70103_1_ == 12) {
      generateRandomParticles("heart");
    } else if (p_70103_1_ == 13) {
      generateRandomParticles("angryVillager");
    } else if (p_70103_1_ == 14) {
      generateRandomParticles("happyVillager");
    } else {
      super.func_70103_a(p_70103_1_);
    } 
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
    p_110161_1_ = super.func_110161_a(p_110161_1_);
    return p_110161_1_;
  }
  
  @SideOnly(Side.CLIENT)
  private void generateRandomParticles(String p_70942_1_) {
    for (int i = 0; i < 5; i++) {
      double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
      double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
      double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
      this.field_70170_p.func_72869_a(p_70942_1_, this.field_70165_t + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + 1.0D + (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d0, d1, d2);
    } 
  }
  
  public void setLookingForHome() {
    this.isLookingForHome = true;
  }
  
  public boolean func_110164_bC() {
    return false;
  }
  
  public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
    return null;
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(this.type);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.type = additionalData.readInt(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\entity\EntityGodVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */