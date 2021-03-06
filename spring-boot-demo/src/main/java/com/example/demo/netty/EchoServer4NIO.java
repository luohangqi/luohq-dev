package com.example.demo.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 长连接server
 * 
 * @author luohq
 * @Data 2017年12月29日
 * @Description TODO
 */
public class EchoServer4NIO {
	private final int port;

	public EchoServer4NIO(int port) {
		this.port = port;
	}
	
	public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3 创建 EventLoopGroup
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4 创建 ServerBootstrap
             .channel(NioServerSocketChannel.class)        //5 指定使用 NIO 的传输 Channel
             .localAddress(new InetSocketAddress(port))    //6 设置 socket 地址使用所选的端口
             .childHandler(new ChannelInitializer<SocketChannel>() { //7 添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                 @Override
                 public void initChannel(SocketChannel ch) 
                     throws Exception {
                     ch.pipeline().addLast(
                             new EchoServerHandler());
                 }
             });

            ChannelFuture f = b.bind().sync();            //8 绑定的服务器;sync 等待服务器关闭
            System.out.println(EchoServer4NIO.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9 关闭 channel 和 块，直到它被关闭
        } finally {
            group.shutdownGracefully().sync();            //10 关机的 EventLoopGroup，释放所有资源
        }
    }
}
