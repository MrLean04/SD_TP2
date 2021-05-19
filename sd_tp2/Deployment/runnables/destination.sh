#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws03.ua.pt "cd src/shared/DestinationAirport && sh run.sh"