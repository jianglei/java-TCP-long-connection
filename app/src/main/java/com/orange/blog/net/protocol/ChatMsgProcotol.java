package com.orange.blog.net.protocol;

import com.orange.blog.common.ProjectApplication;
import com.orange.blog.net.ProtocolException;
import com.orange.blog.net.SocketUtil;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Created by orange on 16/6/13.
 */
public class ChatMsgProcotol extends BasicProtocol implements Serializable{

    public static final int SLEFUUID_LEN=32;
    public static final int MSGTARGETUUID_LEN=32;
    public static final int CLIENTVERION_LEN=4;


    private String message="";
    private String selfUUid="00000000000000000000000000000000";
    private String msgTargetUUID="00000000000000000000000000000000";
    private int clientVersion=1;
    public static final String CHATMEGCOMMEND="0001";

    public ChatMsgProcotol(String msg){
        this.message=msg;
    }

    public ChatMsgProcotol(){
        selfUUid= ProjectApplication.getUUID();
        clientVersion=ProjectApplication.versionID;
    }
    public String getMsgTargetUUID() {
        return msgTargetUUID;
    }

    public void setMsgTargetUUID(String msgTargetUUID) {
        this.msgTargetUUID = msgTargetUUID;
    }

    public String getMessage() {
        return message;
    }

    public int getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(int clientVersion) {
        this.clientVersion = clientVersion;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSelfUUid() {
        return selfUUid;
    }

    public void setSelfUUid(String selfUUid) {
        this.selfUUid = selfUUid;
    }

    @Override
    public String toString() {
        return "信息:"+message+",自身UUID:"+selfUUid+",消息目的UUID:"+msgTargetUUID+",版本号:"+clientVersion;
    }

    @Override
    public String getCommend() {
        return CHATMEGCOMMEND;
    }

    @Override
    public byte[] getContentData() {
        byte[] pos=super.getContentData();
        ByteArrayOutputStream baos=new ByteArrayOutputStream(256);
        baos.write(pos,0,pos.length);
        baos.write(selfUUid.getBytes(),0,SLEFUUID_LEN);
        baos.write(msgTargetUUID.getBytes(),0,MSGTARGETUUID_LEN);
        baos.write(SocketUtil.int2ByteArrays(clientVersion),0,CLIENTVERION_LEN);
        byte[] message=getMessage().getBytes();
        baos.write(message,0,message.length);
        return baos.toByteArray();
    }

    @Override
    public int parseBinary(byte[] data) throws ProtocolException {
        int pos=super.parseBinary(data);
        selfUUid=new String(data,pos,SLEFUUID_LEN);
        pos+=SLEFUUID_LEN;
        msgTargetUUID=new String(data,pos,MSGTARGETUUID_LEN);
        pos+=MSGTARGETUUID_LEN;
        clientVersion=SocketUtil.bytes2Int(data, pos);
        pos+=CLIENTVERION_LEN;
        try {
            message=new String(data,pos,data.length-pos,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pos;
    }
}
