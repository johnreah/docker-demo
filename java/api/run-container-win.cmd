docker run --rm -it -p 8080:8080 --name api --hostname api-container -e ADMIN_SERVER_URL="" -e API_DELAY_MS=0 demo/api
