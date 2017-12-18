JSON Validation Service.
U can/can't receive formatted json or error message.

To deploy:
run: `sudo docker run --rm -d -p 18080:8080 stakenschneider/validationservice`
start: `curl -s --data-binary @filename.json localhost:18080`
