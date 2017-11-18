run: `sudo docker run --rm -d -p 18080:8080 stakenschneider/validationservice`

start: `curl -s --data-binary @filename.json localhost:18080`