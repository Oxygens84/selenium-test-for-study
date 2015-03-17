package org.tests;

import net.lightbody.bmp.proxy.ProxyServer;

public class ProxyFactory {

    public static ProxyServer initProxy() throws Exception {
        ProxyServer bmp = new ProxyServer(8080);
        bmp.start();
        return bmp;
    }

}
