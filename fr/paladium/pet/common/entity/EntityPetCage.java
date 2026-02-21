package fr.paladium.pet.common.entity;

import fr.paladium.pet.client.renderer.TileEntityPetCageRenderer;
import fr.paladium.pet.client.renderer.data.CageRenderData;
import fr.paladium.pet.client.renderer.data.CageRenderState;
import fr.paladium.pet.client.renderer.data.IntLocation;
import fr.paladium.pet.common.registry.impl.PetBlockRegistry;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityPetCage extends EntityLiving implements IAnimatable, IAnimationTickable {
  private final AnimationFactory factory;
  
  public EntityPetCage(World world) {
    super(world);
    func_70105_a(1.4F, 1.6F);
    this.factory = new AnimationFactory(this);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  public void registerControllers(AnimationData data) {
    data.addAnimationController(new AnimationController(this, "movementController", 0.0F, this::predicateMovement));
  }
  
  protected <E extends IAnimatable> PlayState predicateMovement(AnimationEvent<E> event) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    HashSet<IntLocation> removeList = new HashSet<>();
    boolean modified = false;
    for (Map.Entry<IntLocation, CageRenderData> entry : (Iterable<Map.Entry<IntLocation, CageRenderData>>)TileEntityPetCageRenderer.DATAS.entrySet()) {
      IntLocation location = entry.getKey();
      CageRenderData data = entry.getValue();
      if (data == null)
        continue; 
      int distance = location.distance(((EntityPlayer)entityClientPlayerMP).field_70165_t, ((EntityPlayer)entityClientPlayerMP).field_70163_u, ((EntityPlayer)entityClientPlayerMP).field_70161_v);
      Block block = this.field_70170_p.func_147439_a(location.getX(), location.getY(), location.getZ());
      if (distance >= 100 || block == null || !block.equals(PetBlockRegistry.PET_CAGE)) {
        removeList.add(location);
        continue;
      } 
      CageRenderState state = data.getState();
      if (state == CageRenderState.IDLE) {
        event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", Boolean.TRUE));
        modified = true;
        continue;
      } 
      if (state == CageRenderState.FOOD || state == CageRenderState.PET);
    } 
    if (modified)
      event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", Boolean.TRUE)); 
    removeList.forEach(TileEntityPetCageRenderer.DATAS::remove);
    return PlayState.CONTINUE;
  }
  
  public AnimationFactory getFactory() {
    return this.factory;
  }
  
  public int tickTimer() {
    return this.field_70173_aa;
  }
  
  public void tick() {
    func_70071_h_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\entity\EntityPetCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */