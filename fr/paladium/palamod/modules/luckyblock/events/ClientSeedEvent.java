package fr.paladium.palamod.modules.luckyblock.events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ClientSeedEvent {
  public static ClientSeedEvent event;
  
  private World world;
  
  private int x;
  
  private int y;
  
  private int z;
  
  private int ticks = 0;
  
  public ClientSeedEvent(World world, int x, int y, int z) {
    event = this;
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
    this.ticks = 1200;
  }
  
  public void tick() {
    GL11.glPushMatrix();
    GL11.glTranslated(this.x + 0.5D, this.y + 1.6D, this.z + 0.5D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
    GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glDisable(2896);
    int fontSize = (Minecraft.func_71410_x()).field_71466_p.field_78288_b;
    String name = "fraise";
    int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
    (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\ClientSeedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */