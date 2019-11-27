package com.wzt.demo.utils;

import cn.hutool.core.util.StrUtil;
import com.wzt.demo.Constants;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author wangzitao
 * @create 2018-05-18 9:54
 **/
public class NetworkIPUtils {
    /**
     * 获取服务器IP地址
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getServerIp() {
        String SERVER_IP = null;
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                SERVER_IP = ip.getHostAddress();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                    SERVER_IP = ip.getHostAddress();
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return SERVER_IP;
    }

    /**
     * 获取请求IP地址
     *
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StrUtil.isNotEmpty(XFor) && !Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }
        XFor = Xip;
        if (StrUtil.isNotEmpty(XFor) && !Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            return XFor;
        }
        if (StrUtil.isBlank(XFor) || Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(XFor) || Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(XFor) || Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(XFor) || Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(XFor) || Constants.NetWork.UNKNOWN.equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}

