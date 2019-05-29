#!/bin/sh

HASH_FUNCTION=md5
NPM=./node/npm
CACHE_DIR=node_modules_cache

# Get the current cache-key
CACHE_KEY=$(cat ../package.json | $HASH_FUNCTION)

# Create temp dir
mkdir -p $CACHE_DIR

# Install only if there is no cache-key
if [ ! -d "$CACHE_DIR/$CACHE_KEY" ]; then
  rm -rf $CACHE_DIR/* && \
    mkdir -p "$CACHE_DIR/$CACHE_KEY" && \
    cd .. && \
    rm -rf node_modules && \
    sh $NPM install
fi