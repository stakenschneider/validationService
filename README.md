# JSON Validation Service.
U can/can't receive formatted json or error message.

## To deploy:
Build gradle package and run Docker image:

`./gradlew build dockerImage`

run: `sudo docker run --rm -d -p 80:80 validationService` 4 gradle

run: `sudo docker run --rm -d -p 80:80 stakenschneider/validationservice`

start: `curl --upload-file filename.json http://localhost:80`

## Errors
Error format have structure like

```{
    "errorCode"  : 12345,
    "errorMessage" : ["verbose, plain language description of the problem with hints about how to fix it]",
    "errorPlace" : ["highlight the point where error has occurred"],
    "resource"   : ["filename"],
    "request-id" : 12345
}
