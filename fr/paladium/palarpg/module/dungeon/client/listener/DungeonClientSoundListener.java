package fr.paladium.palarpg.module.dungeon.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.dungeon.common.event.DungeonWorldUpdateEvent;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

public class DungeonClientSoundListener {
  private static SoundRecord music;
  
  @SubscribeEvent
  public void onDungeonUpdate(DungeonWorldUpdateEvent.Pre event) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (optionalDungeonWorld.isPresent()) {
      DungeonWorld oldDungeonWorld = optionalDungeonWorld.get();
      if (!oldDungeonWorld.getState().getMusic().equals(event.getWorld().getState().getMusic()))
        reset(); 
    } 
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    if ((Minecraft.func_71410_x()).field_71439_g == null || !PalaRPGMod.proxy.isDungeonWorld() || (Minecraft.func_71410_x()).field_71474_y.func_151438_a(SoundCategory.RECORDS) == 0.0F || (Minecraft.func_71410_x()).field_71474_y.func_151438_a(SoundCategory.MASTER) == 0.0F) {
      reset();
      return;
    } 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    try {
      if (PalaRPGMod.proxy.isDungeonWorld() && optionalDungeonWorld.isPresent()) {
        DungeonWorld dungeonWorld = optionalDungeonWorld.get();
        if (music == null || !music.isPlaying()) {
          Minecraft.func_71410_x().func_147118_V().func_147690_c();
          music = SoundRecord.create(new ResourceLocation("palarpg", "sounds/dungeon/music/" + dungeonWorld.getState().getMusic())).volume(0.0F).volume(dungeonWorld.getState().getVolume(), 1000L).loop().build(SoundCategory.RECORDS).play();
        } 
      } else if (music != null) {
        music = null;
      } 
    } catch (Exception exception) {}
  }
  
  @SubscribeEvent
  public void onPlaySound(PlaySoundEvent17 event) {
    if (event.sound instanceof SoundRecord || event.category != SoundCategory.MUSIC)
      return; 
    event.result = null;
  }
  
  public static void reset() {
    if (music == null)
      return; 
    if (!music.isPlaying()) {
      music = null;
      return;
    } 
    music.volume(0.0F, 1000L, sound -> music = null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\listener\DungeonClientSoundListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */