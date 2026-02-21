package fr.paladium.palaautomation.common.tile.pipe.type;

import fr.paladium.palaautomation.common.block.pipe.PipeType;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGreenPaladiumPipe extends ATileEntityPipe {
  public TileEntityGreenPaladiumPipe() {
    super(PipeType.GREEN_PALADIUM);
  }
  
  public TileEntity getTileEntity() {
    return (TileEntity)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\pipe\type\TileEntityGreenPaladiumPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */