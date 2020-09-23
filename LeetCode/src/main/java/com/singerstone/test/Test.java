package com.singerstone.test;

/**
 * Created by chenbinhao on 2018/7/12. YY:909075276
 */

public class Test {


    public static void main(String[] args) {
        String input = "VideoStatServantImp.cpp:1691:ReportVideoSrcInfo|94503c047e582a6528ab4e5a1b7588cb|182.140.153.126||177427784|0|isom|video/avc|269|1332|750|1022305|5|112.117.220.201|50923|0|mttbrowser://url=http://c.gdt.qq.com/gdt_mclick.fcg?viewid=v3VmNxGpzPvupHcUaibfyVCI6Q3SrmMgIh15hJVig8PjiVrwNdRcwR9gkpoxTo2wGODD_hALi7K8ySFerWD55wgYswe0CFiTwsYwkDBimzqea3q4xyYIfg3oLVbC8T144ofyMCvt6pKDsEXSfMJmXCHpSHQVbkhWk1wErjNVu6_BzExlFySiVQJ1upgGqZbou3qLdT8XO52rzMtxlJzA0Gb90hHhXVeGkI3Ng0vRPJgP0!VRkuu9bRYKDaEayrZ8Bfif6YGReURdMEjn!iHiRTPwTarAOGpTn2vu74FM3jc&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%2216X%22%7D&clklpp=__CLICK_LPP__&noyyburl=1&xp=3&pid=5090437164960441&qbappid=27&qbposid=100222,windowType=1,ChannelID=com.tencent.mtt,PosID=103,nfa_aid=ABsSAAGHfiAgMAVGAFYJMjUyMTg3ODUzZgnmmJPlrZfoval2AIACkl7m7w6svMzc6gwWACYAC/oPBgAWACYANgBGAFkMZgB2AIYAkP+mALYAxgDWAOYA9g8A8BAC9hEAC/YQAPYRAPYSAPYTAPYUIWdpZD0yMzA1ODQzNDIyMTIwNTA1NDU1JmVjcG09My42NfYVAPkXAAEKAjua2NkSO5rY2SkMMgAIAABGAnx8C/YYAPUZQA0zM0AAAAD8GvAbAfUcQL/+AAAAAADwHf/6HgoFAAAAAAAAAAAcKQwLGgUAAAAAAAAAABwLKgUAAAAAAAAAABwLC/ofDBYAC/YgBTM5MDU29iEKMjg4Njc5OTUwNPUiAAAAAAAAAAD6IwwcJgA8Cw==|http://adsmind.gdtimg.com/ads_svp_video__0b6bxeabsaaapiaiyjeosvpbvoiedg4qagka.f40.mp4?dis_k=b01bde34b99ca8a2394dcf9a4e111006&dis_t=1586402868|若你孩子18岁不到，让他读这10本，长大考清华笑着感谢你！仅售79|206|0|60|startplaytime=1592193443195&wifi_ssid=Tencent-GuestWiFi&wdpversion=9901&wangka=0&cpuversion=17&PlayerState=-16-17-18-19-20-21-6&playnum=2&type=0&versionName=10.5.0.7100&qimei=e49db90a8aac7e44&RON=Flyme 7.1.4.3A&wdpdecoder=100,50,-1,1332,750,50923&rnVersion=1053&PlayerStateDepr=12-14-2-3-6--1&host=adsmind.gdtimg.com&wifi_mac=2c:5a:0f:d2:6c:8c&step=0-1-2&uin=default_user&android_id=e49db90a8aac7e44&autoPlay=1&decodeTypein=21&lbs=510107|51231|com.tencent.mtt|QV=3&PL=ADR&PR=QB&PP=com.tencent.mtt&PPVN=10.5.0.7100&TBSVC=44000&CO=BK&COVC=050505&PB=GE&VE=P&DE=PHONE&CHID=0&LCID=11297&MO= 16X &RL=1080*2070&OS=8.1.0&API=27&REF=qb_0&TM=00|51003|51012|||8|0|0|-1|6507358|15208|1565805622|1565856853|findstream=86&fdft=202&onprepared=59&multisw=0&firstrate=3610871&X-Android-Received-Millis=1592193443353&surfacecreate=61&firstframe=57&macAddress=40:A3:6B:80:EA:5E&audiocodingformat=audio/mp4a-latm&XServerIp=0&audiotype=0&imei=867884038787973&X-Android-Sent-Millis=1592193443346&audioprofile=1&Content-Length=5263608&multihw=1&PlayerMode=4&playercreate=6|QV=3&PL=ADR&PR=QB&PP=com.tencent.mtt&PPVN=10.5.0.7100&TBSVC=44000&CO=BK&COVC=050505&PB=GE&VE=P&DE=PHONE&CHID=0&LCID=11297&MO= 16X &RL=1080*2070&OS=8.1.0&API=27&REF=qb_0&TM=00|0|0|";
        String[] output = input.split("\\|");
        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
        }
    }


}
