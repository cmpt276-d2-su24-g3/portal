#!/bin/bash

screen_name="chatbot"
screen -L -Logfile chatbot.log -dmS $screen_name
screen -x -S $screen_name -p 0 -X stuff "\
    cd /chatbot && \
        source venv/bin/activate && \
        uvicorn main:app \
    \n"

screen_name="portal"
screen -L -Logfile portal.log -dmS $screen_name
screen -x -S $screen_name -p 0 -X stuff "java -jar portal.jar \n"

tail -f chatbot.log portal.log