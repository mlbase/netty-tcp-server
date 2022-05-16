package com.netty.socket.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class TestHandler extends ChannelInboundHandlerAdapter {
    private int DATA_LENGTH = 2048;
    private ByteBuf buff;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buff = ctx.alloc().buffer(DATA_LENGTH);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buff = null;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String remoteAddress = ctx.channel().remoteAddress().toString();
        log.info("Remote Address: "+ remoteAddress);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf mBuf = (ByteBuf) msg;
        buff.writeBytes(mBuf);
        mBuf.release();

        final ChannelFuture f = ctx.writeAndFlush(buff);
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
        cause.printStackTrace();
    }
}
