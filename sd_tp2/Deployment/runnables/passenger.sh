#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd0405@l040101-ws07.ua.pt "cd src/entities/Passenger && sh run.sh"