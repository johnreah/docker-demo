# docker-compose load balancer demo

This one has a single HAProxy instance and two JAVA API instances.

Each has its own build directory and Dockerfile: `./haproxy, ./api/1 and ./api/2`

Run with: `docker-compose up`

Test haproxy from browser with http://localhost/haproxy?stats
Test API from command line with: curl get localhost:8080/info

Run shell on haproxy with: `docker exec -it <haproxy id> /bin/bash`

The haproxy image doesn't have curl or apache benchmark; these are added in the Dockerfile.

Load test from haproxy or host with: `ab -n 10000 -c 20 http://localhost:8080/info`

