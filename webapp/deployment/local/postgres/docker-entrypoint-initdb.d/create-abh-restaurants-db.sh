#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 \
  --username "$POSTGRES_USER" \
  --dbname "$POSTGRES_DB" \
  --command "create database \"abh-restaurants\""

# Create extensions on abh-restaurants db
psql -v ON_ERROR_STOP=1 \
  --username "$POSTGRES_USER" \
  --dbname "abh-restaurants" \
  --command "CREATE EXTENSION IF NOT EXISTS postgis; CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";"


psql -v ON_ERROR_STOP=1 \
  --username "$POSTGRES_USER" \
  --dbname "$POSTGRES_DB" \
  --command "create database \"abh-devdays-java\""

# Create extensions on abh-restaurants db
psql -v ON_ERROR_STOP=1 \
  --username "$POSTGRES_USER" \
  --dbname "abh-devdays-java" \
  --command "CREATE EXTENSION IF NOT EXISTS postgis; CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";"