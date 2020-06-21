#!/bin/sh
docker build . -t gdg/demo-app:0.1
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 gdg/demo-app:0.1"
