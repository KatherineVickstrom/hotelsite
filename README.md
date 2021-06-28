# hotelsite

Hotelsite is a hotel booking website and api built for the CSUMB CST438 final project.

## Installation

This Hotelsite is deployed on [heroku](https://cst438hotel.herokuapp.com/). 

If cloning the code to deploy elsewhere, 

* Update the application.properties file with the database connection info 
* Run the hotelsite_v2.sql file (located in the root directory) in MySQL to build the schema and tables in your database and fill the hotels table with data

## Usage

Visit the [/hotelsearch](https://cst438hotel.herokuapp.com/hotelsearch) page to book a reservation through the web app.

To use the hotel api directly, use the following HTTP commands.

### Search for a hotel
```bash
GET /api/hotels/{city}
```
If hotels are found, it returns a json file containing a list of json objects one for each hotel.

If no hotels are found for that city, returns 404 NOT FOUND.

#### Example
```bash
GET /api/hotels/new%20york
```
Returns:
```json
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
```

### Create Reservation

```bash
POST /api/reservation
```
with the body of the HTTP POST containing for example
```json
{
    "hotelId": 2,
    "roomtype": "double",
    "startdate": "2021-06-18",
    "enddate": "2021-06-19"
}
```
This results in the following saved entity
```json 
{
    "id": 36,
    "hotelId": 2,
    "roomtype": "double",
    "startdate": "2021-06-18",
    "enddate": "2021-06-19",
    "user": null
}
```
**Note:** user is null, so package-booked hotel reservations can't be searched for by user. 

The API user must save the returned "id" generated for the reservation to access this reservation later if it needs to be updated or deleted.

### Retrieve hotel info
```bash
GET /api/hotel/{hotelid}
```
Returns a json object of the hotel or 404 NOT FOUND. 

### Update reservation 
```bash
PUT /api/reservations/{reservationid}
```
with the body of the HTTP PUT containing for example
```json
{
    "id": 34,
    "hotelId": 2,
    "roomtype": "queen",
    "startdate": "2021-06-18",
    "enddate": "2021-06-18",
}
```

**Note:** The user cannot be reassigned and will be ignored if included in the json object passed. 

### Delete reservation
```bash
DELETE /api/reservations/{reservationid}
```
If successful, returns 204 request successful, no content returned.
If fails, returns 404 Not Found.

## Acknowledgements

Thanks go to Bijan Pourazari from Team 8 (working on the Travel site 2) for collaborating with us on the api design. 

Thanks go to Professor Wisneski for staying late in office hours Monday and Tuesday nights to help debug and help direct our efforts on the project.

## Project Status
The original contributors have stopped development of this site. Future developers may wish to continue adding some of the planned features,
* Add filter feature to hotel search
* Add web app interface for updating an existing reservation
* Add account page to view past reservations and book hotel again
* Add review option for users to review past bookings
* Add reviews link next to stars displayed in hotel search results to view past reviews
