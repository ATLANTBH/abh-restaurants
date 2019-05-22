--
-- Drop/delete everything in V0.0.1 migration script
--
DROP TABLE IF EXISTS users CASCADE;

DROP SEQUENCE IF EXISTS hibernate_sequence;
DROP FUNCTION IF EXISTS update_table_timestamps CASCADE;

DROP TABLE IF EXISTS restaurant_photo CASCADE;
DROP TABLE IF EXISTS restaurant_cuisine CASCADE;
DROP TABLE IF EXISTS cuisine CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS restaurant_review CASCADE;
DROP TABLE IF EXISTS restaurant_table CASCADE;
DROP TABLE IF EXISTS restaurant CASCADE;
DROP TABLE IF EXISTS city CASCADE;
