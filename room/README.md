Room Service
==============

The Room Service hold the CRUD operations concering rooms and some minor extentions, like room getting by id, fllornumber or buildingname.

The REST-Interface is at follows:

    room/rooms/   -GET: Fetch all rooms
	    		  -POST(room): Create Room
				  -UPDATE(room): Change room
				  -DELETE(room): Remove room
				  
    room/rooms/{id}   				-GET: Fetch room with id {id}
	room/rooms/buildings/{name}		-GET: Fetch rooms in building with name {name}
	room/rooms/floors/{number}		-GET: Fetch rooms that are on floor number {number}