package fr.paladium.palaforgeutils.lib.sidedaction.test;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;
import java.util.concurrent.CompletableFuture;

public class ClientActionTest {
  public void execute() {
    ClientActionResult<Side> test1 = test1("Hello World 1");
    ClientActionResult<Side> test2 = test2("Hello World 2");
    test1.execute(PlayerSelector.ALL()).thenAccept(result -> System.out.println("[Test1] Result: " + result));
    test2.execute(PlayerSelector.ALL()).thenAccept(result -> System.out.println("[Test2] Result: " + result));
    test3().execute(PlayerSelector.ALL()).thenAccept(result -> System.out.println("[Test3] Result: " + result));
  }
  
  @ClientAction
  public static ClientActionResult<Side> test1(String text) {
    return ClientActionHook.useClient(() -> {
          System.out.println("[Test1] exec: " + text + " at " + System.currentTimeMillis());
          return CompletableFuture.completedFuture(FMLCommonHandler.instance().getSide());
        }new Object[] { text });
  }
  
  @ClientAction
  public static ClientActionResult<Side> test2(String text) {
    return ClientActionHook.useClient(() -> {
          System.out.println("[Test2] exec: " + text + " at " + System.currentTimeMillis());
          return CompletableFuture.completedFuture(FMLCommonHandler.instance().getSide());
        }new Object[] { text });
  }
  
  @ClientAction
  public static ClientActionResult<Void> test3() {
    return ClientActionHook.useClient(() -> System.out.println("[Test3] executed at " + System.currentTimeMillis()), new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\test\ClientActionTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */