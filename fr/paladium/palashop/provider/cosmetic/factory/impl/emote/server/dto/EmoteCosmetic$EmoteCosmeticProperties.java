package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class EmoteCosmeticProperties extends CosmeticProperties {
  private boolean loop;
  
  private EmoteCosmeticCancelProperty cancel;
  
  public void setLoop(boolean loop) {
    this.loop = loop;
  }
  
  public void setCancel(EmoteCosmeticCancelProperty cancel) {
    this.cancel = cancel;
  }
  
  public String toString() {
    return "EmoteCosmetic.EmoteCosmeticProperties(loop=" + isLoop() + ", cancel=" + getCancel() + ")";
  }
  
  public EmoteCosmeticProperties() {
    this.loop = false;
    this.cancel = new EmoteCosmeticCancelProperty();
  }
  
  public EmoteCosmeticProperties(boolean loop, EmoteCosmeticCancelProperty cancel) {
    this.loop = false;
    this.cancel = new EmoteCosmeticCancelProperty();
    this.loop = loop;
    this.cancel = cancel;
  }
  
  public boolean isLoop() {
    return this.loop;
  }
  
  public EmoteCosmeticCancelProperty getCancel() {
    return this.cancel;
  }
  
  public static class EmoteCosmeticCancelProperty {
    private boolean move;
    
    private boolean sneak;
    
    private boolean jump;
    
    private boolean interact;
    
    public void setMove(boolean move) {
      this.move = move;
    }
    
    public void setSneak(boolean sneak) {
      this.sneak = sneak;
    }
    
    public void setJump(boolean jump) {
      this.jump = jump;
    }
    
    public void setInteract(boolean interact) {
      this.interact = interact;
    }
    
    public String toString() {
      return "EmoteCosmetic.EmoteCosmeticProperties.EmoteCosmeticCancelProperty(move=" + isMove() + ", sneak=" + isSneak() + ", jump=" + isJump() + ", interact=" + isInteract() + ")";
    }
    
    public EmoteCosmeticCancelProperty() {
      this.move = true;
      this.sneak = true;
      this.jump = true;
      this.interact = true;
    }
    
    public EmoteCosmeticCancelProperty(boolean move, boolean sneak, boolean jump, boolean interact) {
      this.move = true;
      this.sneak = true;
      this.jump = true;
      this.interact = true;
      this.move = move;
      this.sneak = sneak;
      this.jump = jump;
      this.interact = interact;
    }
    
    public boolean isMove() {
      return this.move;
    }
    
    public boolean isSneak() {
      return this.sneak;
    }
    
    public boolean isJump() {
      return this.jump;
    }
    
    public boolean isInteract() {
      return this.interact;
    }
    
    public boolean shouldCancel(@NonNull Entity entity) {
      if (entity == null)
        throw new NullPointerException("entity is marked non-null but is null"); 
      if (this.move && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).field_70721_aZ > 0.1F)
        return true; 
      if ((this.sneak && entity.func_70093_af()) || (this.jump && entity.field_70181_x > 0.009999999776482582D))
        return true; 
      if (this.interact && entity instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer)entity;
        if (player.field_82175_bq || player.func_70113_ah() || player.func_70632_aY() || player.func_71039_bw())
          return true; 
      } 
      return false;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\server\dto\EmoteCosmetic$EmoteCosmeticProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */