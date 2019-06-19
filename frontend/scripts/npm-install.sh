#!/bin/sh

HASH_FUNCTION=md5sum
NPM=npm
CURRENT_DIR=scripts
CACHE_DIR=node_modules_cache

# Get the current cache-key
CACHE_KEY=$(cat ../package.json | $HASH_FUNCTION)

# Create temp dir
mkdir -p $CACHE_DIR

PATH="$PWD/../node/":$PATH

# Install only if there is no cache-key
if [ ! -d "$CACHE_DIR/$CACHE_KEY" ]; then
  rm -rf $CACHE_DIR/* && \
    mkdir -p "$CACHE_DIR/$CACHE_KEY" && \
    cd .. && \
    node --version && \
    npm --version && \
    rm -rf node_modules && \
    $NPM install && \
    cp -R node_modules/ "$CURRENT_DIR/$CACHE_DIR/$CACHE_KEY/"
fi
