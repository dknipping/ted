This is the service for administrating rooms.

Current REST-Facade is as follows:

/rooms
  |
  |-GET: Get all rooms
  |
  |-POST Room: Create rooms
  |
  |-PUT Room: Update (or create) room
  |
  |-DELETE Room: Delete a room
  |
  |- /{id}
  |   |
  |   |- GET: Get room with id {id}
  |
  |- /buildings
  |      |
  |		 |- /{buildingName}
  |               |
  |               |- GET: Get all rooms in building with name {buildingName}
  |      
  |- /floors
        |
        |- /{floorNumber}
                 |
                 |- GET: Get all rooms on floor {floorNumber}			 