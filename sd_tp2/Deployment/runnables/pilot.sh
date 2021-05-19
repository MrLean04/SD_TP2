#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd0405@l040101-ws05.ua.pt "cd src/entities/Pilot && sh run.sh"