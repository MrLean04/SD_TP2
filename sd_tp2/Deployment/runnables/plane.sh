#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws02.ua.pt "cd src/shared/Plane && sh run.sh"