package fr.paladium.palamod.modules.paladium.registerer;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.events.EventHandlerBucket;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class PRegister_Fluids {
  public static Fluid FLUIDS_SULFURICWATER;
  
  public static Fluid FLUIDS_ANGELICWATER;
  
  public static void preInit() {
    FLUIDS_SULFURICWATER = (new Fluid("sulfuricWater")).setDensity(200);
    FluidRegistry.registerFluid(FLUIDS_SULFURICWATER);
    FLUIDS_ANGELICWATER = (new Fluid("angelicWater")).setDensity(200);
    FluidRegistry.registerFluid(FLUIDS_ANGELICWATER);
  }
  
  public static void init() {
    FluidContainerRegistry.registerFluidContainer(FLUIDS_SULFURICWATER, new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC), new ItemStack(Items.field_151133_ar));
    EventHandlerBucket.buckets.put(BlocksRegister.FLUIDS_SULFURICWATER, ItemsRegister.BUCKET_SULFURIC);
    FluidContainerRegistry.registerFluidContainer(FLUIDS_ANGELICWATER, new ItemStack((Item)ItemsRegister.BUCKET_ANGELIC), new ItemStack(Items.field_151133_ar));
    EventHandlerBucket.buckets.put(BlocksRegister.FLUIDS_ANGELICWATER, ItemsRegister.BUCKET_ANGELIC);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Fluids.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */