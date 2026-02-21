package fr.paladium.palamod.modules.homefinder.common.init;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.homefinder.common.blocks.BlockHomeFinder;

public class HBlocksInit implements HInit {
  public void init() {
    BlocksRegister.HOME_FINDER = new BlockHomeFinder("home_finder");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\init\HBlocksInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */