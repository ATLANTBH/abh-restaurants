#!/bin/sh

NODE=node
EMBER=node_modules/.bin/ember

PATH="$PWD/node/":$PATH
PATH="$PWD/../node/":$PATH

$NODE $EMBER build --environment=production
