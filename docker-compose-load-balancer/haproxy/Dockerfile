FROM haproxy

RUN apt update && apt install -y \
    apache2-utils \
    curl

COPY haproxy.cfg /usr/local/etc/haproxy/haproxy.cfg
