EntityVelocity
==============

Apply velocity to a created entity or player!

Commands:
(/velocity can also be abbreviated as /vel, entity can be abbreviated as "e", player can be abbreviated as "p")
 
/velocity entity [entity] [# of entity] [velX] [velY] [velZ] [x] [y] [z]
Applys velocity to a new entity created
[entity] = type of entity created with velocity
[# of entity] = number of entities you wish to spawn (default 1)
[velX] = X coordinate of the velocity applied to the entity (default 0.0)
[velY] = Y coordinate of the velocity applied to the entity (default 0.0)
[velZ] = Z coordinate of the velocity applied to the entity (default 0.0)
[x] = X coordinate where the entity spawns (default where player is standing)
[y] = Y coordinate where the entity spawns (default where player is standing)
[z] = Z coordinate where the entity spawns (default where player is standing)
Permission: velocity.velocity.entity
 
/velocity player [player] [velX] [velY] [velZ]
Applies velocity to a player.
[player] = player that has velocity applied to
[velX] = X coordinate of the velocity applied to the entity (default 0.0)
[velY] = Y coordinate of the velocity applied to the entity (default 0.0)
[velZ] = Z coordinate of the velocity applied to the entity (default 0.0)
Permission: velocity.velocity.player
 
/velocity types
Displays a list of entity types that may be usable in /velocity entity [entity]
Permission: velocity.velocity.types
