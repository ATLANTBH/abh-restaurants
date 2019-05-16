
-- Create hibernate default sequence
CREATE SEQUENCE hibernate_sequence START 1;

-- Create users table
CREATE TABLE users (
  id BIGINT CONSTRAINT users_pk PRIMARY KEY,

  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,

  username VARCHAR(255) NOT NULL
    CONSTRAINT users_username_uq UNIQUE,
  email VARCHAR(512) NOT NULL
    CONSTRAINT users_email_uq UNIQUE,

  password VARCHAR(255) NOT NULL,

  is_activated BOOLEAN DEFAULT FALSE,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create procedure for updating timestamps (update timestamp)
CREATE OR REPLACE FUNCTION update_table_timestamps() RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END
$$;

-- Create trigger on users table
CREATE TRIGGER update_users_timestamp_trigger
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();
