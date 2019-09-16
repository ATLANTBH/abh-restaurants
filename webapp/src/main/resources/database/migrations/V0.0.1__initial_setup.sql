
-- Create hibernate default sequence
CREATE SEQUENCE hibernate_sequence START 1;

-- Create users table
CREATE TABLE users (
  id BIGINT CONSTRAINT users_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,

  email VARCHAR(512) NOT NULL CONSTRAINT users_email_uq UNIQUE,

  password VARCHAR(255) NOT NULL,

  is_admin BOOLEAN DEFAULT FALSE,

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

-- Create city table
CREATE TABLE city (
  id BIGINT CONSTRAINT city_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  name VARCHAR(64) NOT NULL CONSTRAINT city_name_uq UNIQUE,
  bounds TEXT,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on city table
CREATE TRIGGER update_city_timestamp_trigger
BEFORE UPDATE ON city
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

-- Create restaurant table
CREATE TABLE restaurant (
  id BIGINT CONSTRAINT restaurant_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  name VARCHAR(64) NOT NULL,

  city_id BIGINT NOT NULL REFERENCES city(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  address VARCHAR(128) NOT NULL,
  phone VARCHAR(17) NOT NULL,

  price_range SMALLINT NOT NULL,

  cover_image_path VARCHAR(256) NOT NULL,
  profile_image_path VARCHAR(256) NOT NULL,

  description TEXT NOT NULL,

  menu TEXT NOT NULL,

  latitude REAL DEFAULT 0,
  longitude REAL DEFAULT 0,

  open_time TIME WITHOUT TIME ZONE NOT NULL DEFAULT '09:00:00',
  close_time TIME WITHOUT TIME ZONE NOT NULL DEFAULT '23:00:00',

  number_of_ratings INTEGER DEFAULT 0,
  average_rating FLOAT DEFAULT 0,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on restaurant table
CREATE TRIGGER update_restaurant_timestamp_trigger
BEFORE UPDATE ON restaurant
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

-- Create restaurant table table :)
CREATE TABLE restaurant_table (
  id BIGINT CONSTRAINT restaurant_table_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  restaurant_id BIGINT NOT NULL REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,

  number_of_chairs INTEGER NOT NULL,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on restaurant table
CREATE TRIGGER update_restaurant_table_timestamp_trigger
BEFORE UPDATE ON restaurant_table
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

-- Create reservation table
CREATE TABLE reservation (
  id BIGINT CONSTRAINT reservation_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  table_id BIGINT NOT NULL REFERENCES restaurant_table(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  user_id BIGINT REFERENCES users(id) ON UPDATE CASCADE ON DELETE RESTRICT,

  start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  reserved_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,

  is_confirmed BOOLEAN DEFAULT FALSE,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on restaurant table
CREATE TRIGGER update_reservation_table_timestamp_trigger
BEFORE UPDATE ON reservation
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

-- Create cuisine table
CREATE TABLE cuisine (
  id BIGINT CONSTRAINT cuisine_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  name VARCHAR(64) NOT NULL,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on cuisine table
CREATE TRIGGER update_cuisine_table_timestamp_trigger
BEFORE UPDATE ON cuisine
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();


-- Create restaurant cuisine table
CREATE TABLE restaurant_cuisine (
  id BIGINT CONSTRAINT restaurant_cuisine_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  restaurant_id BIGINT NOT NULL REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  cuisine_id BIGINT NOT NULL REFERENCES cuisine(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on restaurant_cuisine table
CREATE TRIGGER update_restaurant_cuisine_timestamp_trigger
BEFORE UPDATE ON restaurant_cuisine
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

-- Create restaurant_review table
CREATE TABLE restaurant_review (
  id BIGINT CONSTRAINT restaurant_review_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  restaurant_id BIGINT NOT NULL REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  user_id BIGINT NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE RESTRICT,

  rating INTEGER NOT NULL,
  review TEXT NOT NULL,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create trigger on restaurant_review table
CREATE TRIGGER update_restaurant_review_timestamp_trigger
BEFORE UPDATE ON restaurant_review
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

-- Create restaurant_photo table
CREATE TABLE restaurant_photo (
  id BIGINT CONSTRAINT restaurant_photo_pk PRIMARY KEY DEFAULT nextval('hibernate_sequence'),

  restaurant_id BIGINT NOT NULL REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,

  photo_path VARCHAR(256) NOT NULL,

  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);


-- Create trigger on restaurant_photo table
CREATE TRIGGER update_restaurant_photo_timestamp_trigger
BEFORE UPDATE ON restaurant_photo
FOR EACH ROW
EXECUTE PROCEDURE update_table_timestamps();

