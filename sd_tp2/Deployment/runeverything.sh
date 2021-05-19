#!/bin/bash

#expect senddata.exp &&

sh runnables/repo.sh &
sh runnables/plane.sh &
sh runnables/destination.sh &
sh runnables/departure.sh &
sh runnables/pilot.sh &
sh runnables/passenger.sh &
sh runnables/hostess.sh &

sleep 60

expect getlog.exp