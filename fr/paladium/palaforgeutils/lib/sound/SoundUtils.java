package fr.paladium.palaforgeutils.lib.sound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public enum SoundUtils {
  AMBIENT_CAVE("ambient.cave"),
  ANVIL_BREAK("random.anvil_break"),
  ANVIL_LAND("random.anvil_land"),
  ANVIL_USE("random.anvil_use"),
  ARROW_HIT("random.arrow_hit"),
  BOW("random.bow"),
  BUTTON_CLICK("random.click"),
  CHEST_CLOSE("random.chestclosed"),
  CHEST_OPEN("random.chestopen"),
  CLICK("gui.button.press"),
  DOOR_CLOSE("random.door_close"),
  DOOR_OPEN("random.door_open"),
  DRINK("random.drink"),
  EAT("random.eat"),
  EXPLODE("random.explode"),
  FIRE("fire.fire"),
  FIRE_IGNITE("fire.ignite"),
  FIREWORK_BLAST("fireworks.blast"),
  FIREWORK_LARGE_BLAST("fireworks.largeBlast"),
  FIREWORK_LAUNCH("fireworks.launch"),
  FIREWORK_TWINKLE("fireworks.twinkle"),
  FIREWORK_TWINKLE_FAR("fireworks.twinkle_far"),
  FIZZ("random.fizz"),
  FUSE("game.tnt.primed"),
  GLASS("step.stone"),
  ITEM_BREAK("random.break"),
  ITEM_PICKUP("random.pop"),
  LAVA("liquid.lava"),
  LAVA_POP("liquid.lavapop"),
  LEVEL_UP("random.levelup"),
  MINECART_BASE("minecart.base"),
  MINECART_INSIDE("minecart.inside"),
  NOTE_BASS("note.bass"),
  NOTE_PIANO("note.harp"),
  NOTE_BASS_DRUM("note.bd"),
  NOTE_SNARE_DRUM("note.snare"),
  NOTE_STICKS("note.hat"),
  NOTE_BASS_GUITAR("note.bassattack"),
  PISTON_EXTEND("tile.piston.out"),
  PISTON_RETRACT("tile.piston.in"),
  PORTAL("portal.portal"),
  PORTAL_TRAVEL("portal.travel"),
  PORTAL_TRIGGER("portal.trigger"),
  SPLASH("random.splash"),
  SWIM("random.swim"),
  WATER("liquid.water"),
  WOOD_CLICK("random.wood_click"),
  BAT_DEATH("mob.bat.death"),
  BAT_HURT("mob.bat.hurt"),
  BAT_IDLE("mob.bat.idle"),
  BAT_LOOP("mob.bat.loop"),
  BAT_TAKEOFF("mob.bat.takeoff"),
  BLAZE_BREATH("mob.blaze.breathe"),
  BLAZE_DEATH("mob.blaze.death"),
  BLAZE_HIT("mob.blaze.hit"),
  CAT_HISS("mob.cat.hiss"),
  CAT_HIT("mob.cat.hit"),
  CAT_MEOW("mob.cat.meow"),
  CAT_PURR("mob.cat.purr"),
  CAT_PURREOW("mob.cat.purreow"),
  CHICKEN_IDLE("mob.chicken.say"),
  CHICKEN_HURT("mob.chicken.hurt"),
  CHICKEN_EGG_POP("mob.chicken.plop"),
  CHICKEN_WALK("mob.chicken.step"),
  COW_IDLE("mob.cow.say"),
  COW_HURT("mob.cow.hurt"),
  COW_WALK("mob.cow.step"),
  CREEPER_DEATH("mob.creeper.death"),
  CREEPER_HISS("creeper.primed"),
  ENDERDRAGON_DEATH("mob.enderdragon.end"),
  ENDERDRAGON_GROWL("mob.enderdragon.growl"),
  ENDERDRAGON_HIT("mob.enderdragon.hit"),
  ENDERMAN_DEATH("mob.endermen.death"),
  ENDERMAN_HIT("mob.endermen.hit"),
  ENDERMAN_IDLE("mob.endermen.idle"),
  ENDERMAN_TELEPORT("mob.endermen.portal"),
  GHAST_SCREAM("mob.ghast.scream"),
  GHAST_SCREAM2("mob.ghast.charge"),
  GHAST_CHARGE("mob.ghast.fireball"),
  GHAST_DEATH("mob.ghast.death"),
  GHAST_FIREBALL("mob.ghast.fireball"),
  IRONGOLEM_DEATH("mob.irongolem.death"),
  IRONGOLEM_HIT("mob.irongolem.hit"),
  IRONGOLEM_THROW("mob.irongolem.throw"),
  IRONGOLEM_WALK("mob.irongolem.walk"),
  MAGMACUBE_WALK("mob.magmacube.big"),
  MAGMACUBE_WALK2("mob.magmacube.small"),
  PIG_IDLE("mob.pig.say"),
  PIG_DEATH("mob.pig.death"),
  PIG_WALK("mob.pig.step"),
  SHEEP_IDLE("mob.sheep.say"),
  SHEEP_SHEAR("mob.sheep.shear"),
  SHEEP_WALK("mob.sheep.step"),
  SILVERFISH_HIT("mob.silverfish.hit"),
  SILVERFISH_KILL("mob.silverfish.kill"),
  SILVERFISH_IDLE("mob.silverfish.say"),
  SILVERFISH_WALK("mob.silverfish.step"),
  SKELETON_IDLE("mob.skeleton.say"),
  SKELETON_DEATH("mob.skeleton.death"),
  SKELETON_HURT("mob.skeleton.hurt"),
  SKELETON_WALK("mob.skeleton.step"),
  SLIME_ATTACK("mob.slime.attack"),
  SLIME_WALK("mob.slime.big"),
  SLIME_WALK2("mob.slime.small"),
  SPIDER_IDLE("mob.spider.say"),
  SPIDER_DEATH("mob.spider.death"),
  SPIDER_WALK("mob.spider.step"),
  WITHER_DEATH("mob.wither.death"),
  WITHER_HURT("mob.wither.hurt"),
  WITHER_IDLE("mob.wither.idle"),
  WITHER_SHOOT("mob.wither.shoot"),
  WITHER_SPAWN("mob.wither.spawn"),
  WOLF_BARK("mob.wolf.bark"),
  WOLF_DEATH("mob.wolf.death"),
  WOLF_GROWL("mob.wolf.growl"),
  WOLF_HOWL("mob.wolf.howl"),
  WOLF_HURT("mob.wolf.hurt"),
  WOLF_PANT("mob.wolf.panting"),
  WOLF_SHAKE("mob.wolf.shake"),
  WOLF_WALK("mob.wolf.step"),
  WOLF_WHINE("mob.wolf.whine"),
  ZOMBIE_METAL("mob.zombie.metal"),
  ZOMBIE_WOOD("mob.zombie.wood"),
  ZOMBIE_WOODBREAK("mob.zombie.woodbreak"),
  ZOMBIE_IDLE("mob.zombie.say"),
  ZOMBIE_DEATH("mob.zombie.death"),
  ZOMBIE_HURT("mob.zombie.hurt"),
  ZOMBIE_INFECT("mob.zombie.infect"),
  ZOMBIE_UNFECT("mob.zombie.unfect"),
  ZOMBIE_REMEDY("mob.zombie.remedy"),
  ZOMBIE_WALK("mob.zombie.step"),
  ZOMBIE_PIG_IDLE("mob.zombiepig.zpig"),
  ZOMBIE_PIG_ANGRY("mob.zombiepig.zpigangry"),
  ZOMBIE_PIG_DEATH("mob.zombiepig.zpigdeath"),
  ZOMBIE_PIG_HURT("mob.zombiepig.zpighurt");
  
  private final String soundName;
  
  public String getSoundName() {
    return this.soundName;
  }
  
  SoundUtils(String soundName) {
    this.soundName = soundName;
  }
  
  @SideOnly(Side.SERVER)
  public void playSound(EntityPlayerMP player, double x, double y, double z, float volume, float pitch) {
    S29PacketSoundEffect packet = new S29PacketSoundEffect(this.soundName, x, y, z, volume, pitch);
    player.field_71135_a.func_147359_a((Packet)packet);
  }
  
  @SideOnly(Side.CLIENT)
  public void playClientSound(double x, double y, double z, float volume, float pitch) {
    (Minecraft.func_71410_x()).field_71441_e.func_72980_b(x, y, z, this.soundName, volume, pitch, false);
  }
  
  @SideOnly(Side.CLIENT)
  public void playClientSound(float volume, float pitch) {
    (Minecraft.func_71410_x()).field_71441_e.func_72980_b((Minecraft.func_71410_x()).field_71439_g.field_70165_t, (Minecraft.func_71410_x()).field_71439_g.field_70163_u, (Minecraft.func_71410_x()).field_71439_g.field_70161_v, this.soundName, volume, pitch, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sound\SoundUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */