#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws06.ua.pt "cd src/entities/Hostess && sh run.sh"