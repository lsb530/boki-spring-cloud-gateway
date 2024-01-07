#!/bin/bash

for i in {1..30}
do
    curl -X GET http://localhost:8888/users -H "Content-Type: application/json"
    sleep 1  # Wait for 1 second between requests
done
