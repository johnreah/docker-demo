package com.johnreah.springcloud.server;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class Resource {

    private String serverHostName;
    private String serverIpAddress;
    private int requestPort;
    private long requestNumber;
    private final ZonedDateTime resourceCreatedAt;

    public static Resource getInfo() {
        return Resource.builder()
                .resourceCreatedAt(ZonedDateTime.now())
                .build();
    }

}
