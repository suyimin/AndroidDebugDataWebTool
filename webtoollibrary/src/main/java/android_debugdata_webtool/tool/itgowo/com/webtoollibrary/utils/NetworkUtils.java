/*
 *
 *  *    Copyright (C) 2016 Amit Shekhar
 *  *    Copyright (C) 2011 Android Open Source Project
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package android_debugdata_webtool.tool.itgowo.com.webtoollibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by amitshekhar on 15/11/16.
 */

public final class NetworkUtils {

    private NetworkUtils() {
        // This class in not publicly instantiable
    }

    /**
     * 获取WIFI网络地址，局域网
     *
     * @param context
     * @param port
     * @return
     */
    public static String getAddressLog(Context context, int port) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        @SuppressLint("DefaultLocale")
        final String formattedIpAddress = String.format("%d.%d.%d.%d",
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
        return formattedIpAddress + ":" + port;
    }

    /**
     * 获取数据的网址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
