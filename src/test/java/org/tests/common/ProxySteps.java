package org.tests.common;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.Assert;

public class ProxySteps {

    private final ProxyServer bmp;

    public ProxySteps(ProxyServer bmp) {
        this.bmp = bmp;
    }

    public void validateHar() {
        Har har = bmp.getHar();
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();
            System.out.println(request.getUrl() + " : " + response.getStatus()
                    + ", " + entry.getTime() + "ms");
            int status = response.getStatus();
            Assert.assertTrue("Invalid status = " + status, 200 <= status && status < 400);
        }
    }

}
