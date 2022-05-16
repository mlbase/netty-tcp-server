package com.netty.socket.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TestDecoder extends ByteToMessageDecoder {

    private int DATA_LENTH = 2048;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if( in.readableBytes() < DATA_LENTH) {
            return;
        }

        out.add(in.readBytes(DATA_LENTH));
    }
}
