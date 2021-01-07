package com.johnreah.springcloud.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@Configuration
@Slf4j
public class Config {

    private static String serverHostName;
    private static String serverIpAddress;
    private static long meanApiDelayMs;

    public Config() {
        try {
            serverHostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try(final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            serverIpAddress = datagramSocket.getLocalAddress().getHostAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    static public String getServerHostName() {
        return serverHostName;
    }

    static public String getServerIpAddress() {
        return serverIpAddress;
    }

    @Value("${api.delay.ms}")
    public void setApiDelayMs(long meanApiDelayMs) {
        log.info("Setting API delay to {}ms", meanApiDelayMs);
        Config.meanApiDelayMs = meanApiDelayMs;
    }

    static public long getMeanApiDelayMs() {
        return Config.meanApiDelayMs;
    }

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

}
