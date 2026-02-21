package fr.paladium.palaforgeutils.lib.sidedaction.test;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import java.util.concurrent.CompletableFuture;

public class ServerActionTest {
  public void execute() {
    CompletableFuture<String> test1 = test1("Hello World 1");
    CompletableFuture<String> test2 = test2("Hello World 2");
    test1.thenAccept(result -> System.out.println("[Test1] Result: " + result));
    test2.thenAccept(result -> System.out.println("[Test2] Result: " + result));
    test3();
  }
  
  @ServerAction
  public static CompletableFuture<String> test1(String text) {
    return ServerActionHook.useServer(context -> {
          System.out.println("[Test1] exec: " + text + " from " + context.getPlayer().func_70005_c_() + " at " + System.currentTimeMillis());
          return CompletableFuture.completedFuture(FMLCommonHandler.instance().getSide() + " - " + System.currentTimeMillis());
        }new Object[] { text });
  }
  
  @ServerAction
  public static CompletableFuture<String> test2(String text) {
    return ServerActionHook.useServer(context -> {
          System.out.println("[Test2] exec: " + text + " from " + context.getPlayer().func_70005_c_() + " at " + System.currentTimeMillis());
          return CompletableFuture.completedFuture(FMLCommonHandler.instance().getSide() + " - " + System.currentTimeMillis());
        }new Object[] { text });
  }
  
  @ServerAction
  public static void test3() {
    ServerActionHook.useServer(context -> System.out.println("[Test3] executed from " + context.getPlayer().func_70005_c_() + " at " + System.currentTimeMillis()), new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\test\ServerActionTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */