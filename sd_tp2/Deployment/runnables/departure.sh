#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws01.ua.pt "cd src/shared/DepartureAirport && sh run.sh"