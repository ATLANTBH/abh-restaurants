#!/bin/sh

NODE=node/node
EMBER=node_modules/.bin/ember

$NODE $EMBER build --environment=production
