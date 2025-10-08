#!/bin/bash

for i in {1..4}; do
    marp --pdf lab-$i.md --theme pragmatech.css --allow-local-files
done
