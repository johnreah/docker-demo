docker service create ^
    --name api ^
    --network demo-network ^
    --replicas 1 ^
    --publish 8080:8080 ^
    --env ADMIN_SERVER_URL=http://admin:8093 ^
    --env API_DELAY_MS=0 ^
    --hostname "{{.Service.Name}}-{{.Node.ID}}" ^
    demo/api
