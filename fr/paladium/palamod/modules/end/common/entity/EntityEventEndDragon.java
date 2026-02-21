package fr.paladium.palamod.modules.end.common.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import fr.paladium.palamod.modules.end.server.utils.EndState;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityEventEndDragon extends EntityLiving implements IBossDisplayData, IMob, IAnimatable, IAnimationTickable {
  private static int targetRadius = 50;
  
  private int homeX;
  
  private int homeY;
  
  private int homeZ;
  
  private Entity target;
  
  private double targetX;
  
  private double targetY;
  
  private double targetZ;
  
  private long lastTarget;
  
  private boolean forceNewTarget;
  
  private long lifetime;
  
  private final Map<UUID, Float> damageByPlayers;
  
  private final AnimationFactory factory = new AnimationFactory(this);
  
  public EntityEventEndDragon(World world) {
    super(world);
    this.field_98038_p = true;
    this.damageByPlayers = new ConcurrentHashMap<>();
    func_70606_j(func_110138_aP());
    func_70105_a(16.0F, 3.0F);
    this.field_70145_X = true;
    this.field_70178_ae = true;
    this.targetY = 150.0D;
    this.field_70158_ak = true;
    func_110163_bv();
  }
  
  public void setHome(int x, int y, int z) {
    this.homeX = x;
    this.homeY = y;
    this.homeZ = z;
    func_70012_b(x, y, z, this.field_70170_p.field_73012_v.nextFloat() * 360.0F, 0.0F);
    this.lifetime = System.currentTimeMillis() + 600000L;
  }
  
  public void func_70636_d() {
    if (this.field_70170_p.field_72995_K && this.field_70173_aa % 28 == 0 && this.field_70181_x > 0.0D)
      this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.enderdragon.wings", 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F, false); 
    if (func_110143_aJ() <= 0.0F) {
      float xExplosionOffset = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      float yExplosionOffset = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
      float zExplosionOffset = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      this.field_70170_p.func_72869_a("largeexplode", this.field_70165_t + xExplosionOffset, this.field_70163_u + 2.0D + yExplosionOffset, this.field_70161_v + zExplosionOffset, 0.0D, 0.0D, 0.0D);
    } else {
      this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
      if (this.field_70170_p.field_72995_K) {
        if (this.field_70716_bi > 0) {
          double d10 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / this.field_70716_bi;
          double d0 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / this.field_70716_bi;
          double d1 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / this.field_70716_bi;
          double d2 = MathHelper.func_76138_g(this.field_70712_bm - this.field_70177_z);
          this.field_70177_z = (float)(this.field_70177_z + d2 / this.field_70716_bi);
          this.field_70125_A = (float)(this.field_70125_A + (this.field_70705_bn - this.field_70125_A) / this.field_70716_bi);
          this.field_70716_bi--;
          func_70107_b(d10, d0, d1);
          func_70101_b(this.field_70177_z, this.field_70125_A);
        } 
      } else {
        double d10 = this.targetX - this.field_70165_t;
        double d0 = this.targetY - this.field_70163_u;
        double d1 = this.targetZ - this.field_70161_v;
        double d2 = d10 * d10 + d0 * d0 + d1 * d1;
        if (this.target != null) {
          this.targetX = this.target.field_70165_t;
          this.targetZ = this.target.field_70161_v;
          double d3 = this.targetX - this.field_70165_t;
          double d5 = this.targetZ - this.field_70161_v;
          double d7 = Math.sqrt(d3 * d3 + d5 * d5);
          double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;
          if (d8 > 10.0D)
            d8 = 10.0D; 
          this.targetY = this.target.field_70121_D.field_72338_b + d8;
        } else {
          this.targetX += this.field_70146_Z.nextGaussian() * 2.0D;
          this.targetZ += this.field_70146_Z.nextGaussian() * 2.0D;
        } 
        if (System.currentTimeMillis() - this.lastTarget > 5000L && (this.forceNewTarget || d2 < 9.0D || d2 > 22500.0D))
          setNewTarget(); 
        d0 /= MathHelper.func_76133_a(d10 * d10 + d1 * d1);
        float f12 = 0.6F;
        if (d0 < -f12)
          d0 = -f12; 
        if (d0 > f12)
          d0 = f12; 
        this.field_70181_x += d0 * 0.10000000149011612D;
        this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
        double d4 = 180.0D - Math.atan2(d10, d1) * 180.0D / Math.PI;
        double d6 = MathHelper.func_76138_g(d4 - this.field_70177_z);
        if (d6 > 50.0D)
          d6 = 50.0D; 
        if (d6 < -50.0D)
          d6 = -50.0D; 
        Vec3 vec3 = Vec3.func_72443_a(this.targetX - this.field_70165_t, this.targetY - this.field_70163_u, this.targetZ - this.field_70161_v).func_72432_b();
        Vec3 vec32 = Vec3.func_72443_a(MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F), this.field_70181_x, -MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F)).func_72432_b();
        float f5 = (float)(vec32.func_72430_b(vec3) + 0.5D) / 1.5F;
        if (f5 < 0.0F)
          f5 = 0.0F; 
        this.field_70704_bt *= 0.8F;
        float f6 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0F + 1.0F;
        double d9 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0D + 1.0D;
        if (d9 > 40.0D)
          d9 = 40.0D; 
        this.field_70704_bt = (float)(this.field_70704_bt + d6 * 0.699999988079071D / d9 / f6);
        this.field_70177_z += this.field_70704_bt * 0.1F;
        float f7 = (float)(2.0D / (d9 + 1.0D));
        float f8 = 0.06F;
        func_70060_a(0.0F, -1.0F, 0.06F * (f5 * f7 + 1.0F - f7));
        func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        Vec3 vec31 = Vec3.func_72443_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72432_b();
        float f9 = (float)(vec31.func_72430_b(vec32) + 1.0D) / 2.0F;
        f9 = 0.8F + 0.15F * f9;
        this.field_70159_w *= f9;
        this.field_70179_y *= f9;
        this.field_70181_x *= 0.9100000262260437D;
      } 
      this.field_70761_aq = this.field_70177_z;
      if (!this.field_70170_p.field_72995_K && this.field_70737_aN == 0) {
        List<?> entities = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D));
        attackEntitiesInList(entities);
        if (!entities.isEmpty())
          this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.enderdragon.growl", 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F, false); 
      } 
    } 
  }
  
  private void attackEntitiesInList(List<?> entities) {
    if (!entities.isEmpty())
      return; 
    for (int i = 0; i < entities.size(); i++) {
      Entity entity = (Entity)entities.get(i);
      if (entity instanceof EntityLivingBase)
        entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 16.0F); 
    } 
  }
  
  private void setNewTarget() {
    this.lastTarget = System.currentTimeMillis();
    func_70661_as().func_75499_g();
    if ((this.target == null || this.field_70170_p.field_73012_v.nextInt(2) == 0) && !this.field_70170_p.field_73010_i.isEmpty()) {
      this.target = this.field_70170_p.field_73010_i.get(this.field_70146_Z.nextInt(this.field_70170_p.field_73010_i.size()));
      if (this.target.func_70011_f(this.homeX, this.homeY, this.homeZ) > targetRadius) {
        int i = 0;
        do {
          this.target = this.field_70170_p.field_73010_i.get(i);
          i++;
        } while (this.target.func_70011_f(this.homeX, this.homeY, this.homeZ) > targetRadius && i < this.field_70170_p.field_73010_i.size());
        if (this.target.func_70011_f(this.homeX, this.homeY, this.homeZ) > targetRadius)
          this.target = null; 
      } 
    } else {
      this.target = null;
    } 
    if (this.target == null) {
      boolean targetFound = false;
      do {
        this.targetX = (this.field_70146_Z.nextFloat() * targetRadius - (targetRadius / 2) + this.homeX);
        this.targetY = (this.field_70146_Z.nextFloat() * 50.0F + this.homeY);
        this.targetZ = (this.field_70146_Z.nextFloat() * targetRadius - (targetRadius / 2) + this.homeZ);
        double d0 = this.field_70165_t - this.targetX;
        double d1 = this.field_70163_u - this.targetY;
        double d2 = this.field_70161_v - this.targetZ;
        targetFound = (d0 * d0 + d1 * d1 + d2 * d2 > 30.0D);
      } while (!targetFound);
      this.target = null;
    } 
    if (this.target != null) {
      this.targetX = this.target.field_70165_t;
      this.targetY = this.target.field_70163_u;
      this.targetZ = this.target.field_70161_v;
    } 
    PathEntity path = this.field_70170_p.func_72844_a((Entity)this, MathHelper.func_76128_c(this.targetX), (int)this.targetY, MathHelper.func_76128_c(this.targetZ), 100.0F, true, true, true, false);
    func_70661_as().func_75484_a(path, 1.0D);
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (source.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)source.func_76346_g();
      this.damageByPlayers.put(player.func_110124_au(), Float.valueOf(((Float)this.damageByPlayers.getOrDefault(player.func_110124_au(), Float.valueOf(0.0F))).floatValue() + damage));
    } 
    return super.func_70097_a(source, damage);
  }
  
  @SideOnly(Side.SERVER)
  public void func_70645_a(DamageSource source) {
    if (!this.field_70170_p.field_72995_K && EndManager.getInstance().getState() == EndState.DRAGON) {
      func_145779_a(ItemsRegister.TRIXIUM, 450);
      func_145779_a(ItemsRegister.PALADIUM_INGOT, 350);
      ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eLe dragon a été vaincu.");
      Optional<Map.Entry<UUID, Float>> optionalKiller = this.damageByPlayers.entrySet().stream().sorted(Collections.reverseOrder((Comparator)Map.Entry.comparingByValue())).findFirst();
      if (optionalKiller.isPresent() && this.field_70170_p.func_152378_a((UUID)((Map.Entry)optionalKiller.get()).getKey()) != null)
        text = new ChatComponentText("§8[§dEnd§8] §eLe dragon a été vaincu par §c" + this.field_70170_p.func_152378_a((UUID)((Map.Entry)optionalKiller.get()).getKey()).func_70005_c_()); 
      EndManager.getInstance().startEgghunt(optionalKiller.isPresent() ? ((this.field_70170_p.func_152378_a((UUID)((Map.Entry)optionalKiller.get()).getKey()) != null) ? this.field_70170_p.func_152378_a((UUID)((Map.Entry)optionalKiller.get()).getKey()) : null) : null);
      ChatStyle style = new ChatStyle();
      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bCliquez pour vous téléporter au §d/warp end")));
      style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp end"));
      text.func_150255_a(style);
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
      for (Object playerObj : this.field_70170_p.field_73010_i) {
        if (!(playerObj instanceof EntityPlayerMP))
          continue; 
        EntityPlayerMP player = (EntityPlayerMP)playerObj;
        S29PacketSoundEffect packet = new S29PacketSoundEffect("mob.enderdragon.end", player.field_70165_t, player.field_70163_u, player.field_70161_v, func_70599_aP(), 4.0F);
        player.field_71135_a.func_147359_a((Packet)packet);
        ModuleTitle.getInstance().sendTitle(new MinecraftTitle("§dEvent END", "§cLe dragon est mort", 500L, 3000L, 500L), player);
      } 
    } 
    super.func_70645_a(source);
  }
  
  protected int func_70693_a(EntityPlayer entity) {
    return 3395;
  }
  
  public void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(targetRadius);
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  @SideOnly(Side.SERVER)
  public void func_70071_h_() {}
  
  public void func_70109_d(NBTTagCompound nbt) {
    super.func_70109_d(nbt);
    nbt.func_74768_a("homeX", this.homeX);
    nbt.func_74768_a("homeY", this.homeY);
    nbt.func_74768_a("homeZ", this.homeZ);
    nbt.func_74772_a("lifetime", this.lifetime);
  }
  
  public void func_70020_e(NBTTagCompound nbt) {
    super.func_70020_e(nbt);
    this.homeX = nbt.func_74762_e("homeX");
    this.homeY = nbt.func_74762_e("homeY");
    this.homeZ = nbt.func_74762_e("homeZ");
    this.lifetime = nbt.func_74763_f("lifetime");
  }
  
  public void func_70623_bb() {}
  
  public boolean func_70067_L() {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public void func_70108_f(Entity entity) {
    if (!(entity instanceof net.minecraft.entity.projectile.EntityThrowable) && !(entity instanceof net.minecraft.entity.IProjectile))
      return; 
    super.func_70108_f(entity);
  }
  
  public String func_70639_aQ() {
    return "mob.enderdragon.growl";
  }
  
  public String func_70621_aR() {
    return "mob.enderdragon.hit";
  }
  
  public float func_70599_aP() {
    return 3.0F;
  }
  
  public void tick() {
    super.func_70071_h_();
  }
  
  public int tickTimer() {
    return this.field_70173_aa;
  }
  
  public AnimationFactory getFactory() {
    return this.factory;
  }
  
  public void registerControllers(AnimationData data) {
    data.addAnimationController(new AnimationController(this, "movementController", 0.0F, this::predicateMovement));
  }
  
  private <E extends IAnimatable> PlayState predicateMovement(AnimationEvent<E> event) {
    if (this.field_70181_x > 0.0D) {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.mohiras.walk", Boolean.valueOf(true)));
    } else {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.mohiras.attack", Boolean.valueOf(true)));
    } 
    return PlayState.CONTINUE;
  }
  
  public AxisAlignedBB func_70046_E() {
    return this.field_70121_D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\entity\EntityEventEndDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */