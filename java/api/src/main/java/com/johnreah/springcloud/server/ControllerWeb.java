package com.johnreah.springcloud.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;
import java.net.UnknownHostException;

@Controller
@Slf4j
public class ControllerWeb {

    @GetMapping("/")
    public String getInfo(HttpServletRequest httpServletRequest, Model model) throws UnknownHostException, SocketException {
        model.addAttribute("host", httpServletRequest.getLocalName());
        model.addAttribute("ip", Config.getIpAddress());
        model.addAttribute("port", httpServletRequest.getLocalPort());
        return "index";
    }

}
