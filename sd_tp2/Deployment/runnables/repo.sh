#!/bin/bash
sshpass -p qwerty ssh -tt -o StrictHostKeyChecking=no sd405@l040101-ws04.ua.pt "cd src/shared/Repo && sh run.sh"