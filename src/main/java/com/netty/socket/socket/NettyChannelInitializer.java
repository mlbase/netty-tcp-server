package com.netty.socket.socket;

import com.netty.socket.decoder.TestDecoder;
import com.netty.socket.handler.TestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final TestHandler testHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        TestDecoder testDecoder = new TestDecoder();

        pipeline.addLast(testDecoder);
        pipeline.addLast(testHandler);
    }
}
