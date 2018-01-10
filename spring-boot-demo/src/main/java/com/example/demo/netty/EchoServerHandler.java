package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

@Sharable // 1 标识这类的实例之间可以在 channel 里面共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/**
	 * 新用户连接
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		if (!checkIsRegister(channel)) { // 不存在的话广播给其他用户
			ctx.writeAndFlush("welcome:" + channel.remoteAddress() + "加入");
			channelGroup.add(channel);
		}
	}

	/**
	 * 连接退出
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		if (checkIsRegister(channel)) {
			ctx.writeAndFlush("info:" + channel.remoteAddress() + "退出");
			channelGroup.remove(channel);
		}
	}
	
	/**
	 * 处理客户点写入消息
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Channel channel = ctx.channel();
		channel.writeAndFlush(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("EchoServerHandler.channelReadComplete()");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)// 4 冲刷所有待审消息到远程节点。关闭通道后，操作完成
				.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("EchoServerHandler.exceptionCaught()");
		cause.printStackTrace(); // 5 打印异常堆栈跟踪
		ctx.close(); // 6 关闭通道
	}
	
	private boolean checkIsRegister(Channel channel) {
		return channelGroup.parallelStream().anyMatch(x -> x == channel);
	}
}
