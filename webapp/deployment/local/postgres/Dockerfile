FROM postgres:11.3

COPY ./docker-entrypoint-initdb.d/create-abh-restaurants-db.sh /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/
RUN set -ex \
    && apt-get update \
    && apt-get install postgresql-11-postgis-2.5 -y
