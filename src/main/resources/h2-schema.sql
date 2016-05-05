CREATE TABLE circles (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 entityId VARCHAR(36),
 latitude DECIMAL(11, 8) NOT NULL,
 longitude DECIMAL(11, 8) NOT NULL,
 radius INTEGER NOT NULL,
 outlineColor VARCHAR(25) NOT NULL,
 fillColor VARCHAR(25) NOT NULL
);

CREATE TABLE entities (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 title VARCHAR(50) NOT NULL
);

CREATE TABLE entityEntityTypeXrefs (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 entityId VARCHAR(36) NOT NULL,
 entityTypeId VARCHAR(36) NOT NULL
);

CREATE TABLE entityTypes (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 parentId VARCHAR(36),
 name VARCHAR(50) NOT NULL,
 show BOOLEAN NOT NULL
);

CREATE TABLE icons (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 url VARCHAR(100),
 name VARCHAR(50) NOT NULL
);

CREATE TABLE markers (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 entityId VARCHAR(36),
 latitude DECIMAL(11, 8) NOT NULL,
 longitude DECIMAL(11, 8) NOT NULL,
 iconId VARCHAR(36) NOT NULL
);

CREATE TABLE notes (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 entityId VARCHAR(36),
 note VARCHAR(500) NOT NULL,
 position INTEGER NOT NULL
);

CREATE TABLE polygons (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 entityId VARCHAR(36),
 vertices VARCHAR(500) NOT NULL
);
