package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.allatori.annotations.StringEncryption;
import com.allatori.annotations.StringEncryptionType;
import fr.paladium.palamod.modules.argus.methods.MethodGetFunc150751a;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
@StringEncryption("maximum")
@StringEncryptionType("strong")
@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class BytecodeChannel extends SimpleChannelInboundHandler {
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
    if (msg.getClass() != MethodGetFunc150751a.fpp)
      return; 
    BytecodeChunkReceiver.handle(msg);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\BytecodeChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */