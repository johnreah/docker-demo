docker service create ^
    --name api ^
    --network demo ^
    --replicas 1 ^
    --publish 8080:8080 ^
    --env ADMIN_SERVER_URL=http://admin:8093 ^
    demo/api
