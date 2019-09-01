package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.session.Session;
import com.masami.nettyDemo.utils.IDUtil;
import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.CreateGroupRequestPacket;
import com.masami.protocol.command.response.CreateGroupRespnsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupPacket) throws Exception {
        List<String> userNameList = new ArrayList<>();
        DefaultChannelGroup channelsGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userIdList = createGroupPacket.getUserIdList();
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if(channel!=null){
                channelsGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }


        String groupId = IDUtil.randomUserId();


        CreateGroupRespnsePacket createGroupRespnsePacket = new CreateGroupRespnsePacket();
        createGroupRespnsePacket.setSuccess(true);
        createGroupRespnsePacket.setGourpId(groupId);
        createGroupRespnsePacket.setUserNameList(userNameList);
        //把channelGroup 存进sessionUtil中
        SessionUtil.bindChannelGroup(groupId,channelsGroup);

        System.out.print("群创建成功，id 为[" + createGroupRespnsePacket.getGourpId() + "], ");
        System.out.println("群里面有：" + createGroupRespnsePacket.getUserNameList());
        channelsGroup.writeAndFlush(createGroupRespnsePacket);

    }




}
