package com.johnreah.springcloud.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class ControllerRest {

    AtomicLong requestsServed = new AtomicLong(0);

    @GetMapping("/info")
    public Resource getInfo(HttpServletRequest httpServletRequest) throws UnknownHostException, SocketException {

        if (Config.getMeanApiDelayMs() > 0) {
            long msToSleep = ThreadLocalRandom.current().nextLong(0, Config.getMeanApiDelayMs() * 2);
            try {
                Thread.sleep(msToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Resource resource = Resource.getInfo();
        resource.setServerHostName(Config.getServerHostName());
        resource.setServerIpAddress(Config.getServerIpAddress());
        resource.setRequestPort(httpServletRequest.getLocalPort());
        resource.setRequestNumber(requestsServed.addAndGet(1));
        log.debug("Address {}:{} | Request {} | CreatedAt {}", resource.getServerIpAddress(), resource.getRequestPort(), resource.getRequestNumber(), resource.getResourceCreatedAt());
        return resource;
    }

}
