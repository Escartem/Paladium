package fr.paladium.palamod.modules.paladium.utils;

import cpw.mods.fml.relauncher.Side;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Packet {
  Side side();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\Packet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */