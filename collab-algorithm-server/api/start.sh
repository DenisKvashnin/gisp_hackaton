#!/bin/bash
app="gisp"
# docker build -t ${app} .
docker run -d -p 56733:88 \
  --name=${app} \
  -v $PWD:/app ${app}
