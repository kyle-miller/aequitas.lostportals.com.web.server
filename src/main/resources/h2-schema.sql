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

CREATE TABLE circles (
 id VARCHAR(36) NOT NULL PRIMARY KEY,
 entityId VARCHAR(36),
 latitude DECIMAL(3, 8) NOT NULL,
 longitude DECIMAL(3, 8) NOT NULL,
 radius INTEGER NOT NULL,
 outlineColor VARCHAR(25) NOT NULL,
 fillColor VARCHAR(25) NOT NULL
);