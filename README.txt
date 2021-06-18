# hotelsite
CST438 final project hotel booking site

*****************API INFO**********************

Search for hotels by city example
----------------------------------
GET /api/hotels/new%20york
returns
[
    {
        "id": 2,
        "name": "Marriot",
        "address": "321 Main St.",
        "city": "New York",
        "stars": 5,
        "price": 150
    },
    {
        "id": 1,
        "name": "Holiday Inn",
        "address": "123 Main St",
        "city": "New York",
        "stars": 5,
        "price": 100
    }
]
If no hotels found for that city, returns 404 NOT FOUND
Create Reservation example
--------------------------
POST: /api/reservation
Body:
{
    "id": 34,
    "hotelId": 2,
    "roomtype": "double",
    "startdate": "2021-06-18",
    "enddate": "2021-06-19"
}
Results in the following saved entity
{
    "id": 36,
    "hotelId": 2,
    "roomtype": "double",
    "startdate": "2021-06-18",
    "enddate": "2021-06-19",
    "user": null
}
Note: user is null, so package-booked hotel reservations can't be searched for by user, but the prof said that is fine. 
Package-site must save the returned "id" generated for the reservation to access this reservation later if it needs to be updated or deleted.
Update Reservation Example
--------------------------
PUT /api/reservations/34
body
{
    "id": 34,
    "hotelId": 2,
    "roomtype": "queen",
    "startdate": "2021-06-18",
    "enddate": "2021-06-18",
}
we don't care about the user bc it can't be reassigned, so can be omited from the json object
Delete Reservation Example
---------------------------
DELETE /api/reservations/33
if successful, returns return 204,  request successful.  no content returned.
if fails, returns 404 Not Found return code

***************DATABASE INFO*********************

Use this SQL script to build datatbase
---------------------------------------
USE `hotelsite`;

DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `hotels`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `hotels` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `stars` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO hotels (id, name, address, city, stars, price)
VALUES
(1, "Holiday Inn", "123 Main St", "New York", 4, 100),
(2, "Marriot", "321 Main St.", "New York", 5, 125),
(3, "Holiday Inn", "123 Main St", "Los Angeles", 4, 100),
(4, "Holiday Inn", "123 Main St", "Los Angeles", 4, 100),
(5, "Holiday Inn", "123 Main St", "Chicago", 4, 100),
(6, "Marriot", "321 Main St.", "Chicago", 5, 125),
(7, "Holiday Inn", "123 Main St", "Houston", 4, 100),
(8, "Holiday Inn", "123 Main St", "Houston", 4, 100),
(9, "Holiday Inn", "123 Main St", "Phoenix", 4, 100),
(10, "Marriot", "321 Main St.", "Phoenix", 5, 125),
(11, "Holiday Inn", "123 Main St", "Philadelphia", 4, 100),
(12, "Holiday Inn", "123 Main St", "Philadelphia", 4, 100),
(13, "Holiday Inn", "123 Main St", "San Antonio", 4, 100),
(14, "Marriot", "321 Main St.", "San Antonio", 5, 125),
(15, "Holiday Inn", "123 Main St", "San Diego", 4, 100),
(16, "Holiday Inn", "123 Main St", "San Diego", 4, 100),
(17, "Holiday Inn", "123 Main St", "Dallas", 4, 100),
(18, "Marriot", "321 Main St.", "Dallas", 5, 125),
(19, "Holiday Inn", "123 Main St", "San Jose", 4, 100),
(20, "Holiday Inn", "123 Main St", "San Jose", 4, 100);

CREATE TABLE `reservation` (
  `id` int NOT NULL,
  `enddate` varchar(255) DEFAULT NULL,
  `hotel_id` int NOT NULL,
  `roomtype` varchar(255) DEFAULT NULL,
  `startdate` varchar(255) DEFAULT NULL,
  `user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hotel_fk_idx` (`hotel_id`),
  CONSTRAINT `hotel_fk` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`),
  KEY `user_fk_idx` (`user`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
