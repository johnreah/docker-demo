package com.johnreah.springcloud.server;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class Resource {

    private String ipAddress;
    private int port;
    private long requestNumber;
    private final ZonedDateTime resourceCreatedAt;

    public static Resource getInfo() {
        return Resource.builder()
                .resourceCreatedAt(ZonedDateTime.now())
                .build();
    }

}
