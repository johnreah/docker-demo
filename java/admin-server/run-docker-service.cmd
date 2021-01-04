docker service create --name admin --network demo --replicas 1 --publish 8093:8093 --hostname admin demo/admin-server
