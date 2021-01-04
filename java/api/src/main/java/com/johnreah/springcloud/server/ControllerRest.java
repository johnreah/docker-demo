package com.johnreah.springcloud.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class ControllerRest {

    AtomicLong requestsServed = new AtomicLong(0);

    @GetMapping("/info")
    public Resource getInfo(HttpServletRequest httpServletRequest) throws UnknownHostException, SocketException {
        Resource resource = Resource.getInfo();
        resource.setIpAddress(Config.getIpAddress());
        resource.setPort(httpServletRequest.getLocalPort());
        resource.setRequestNumber(requestsServed.addAndGet(1));
        log.debug("Address {}:{} | Request {} | CreatedAt {}", resource.getIpAddress(), resource.getPort(), resource.getRequestNumber(), resource.getResourceCreatedAt());
        return resource;
    }

}
