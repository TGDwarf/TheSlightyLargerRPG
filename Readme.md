Readme for the Slightly Larger RPG

First off a recommendation for this guy, he speaks very well and explains stuff with graphical examples for the first part of most videos, things like flood flow algorithms and other math concepts, thus making it easier to grasp the idea before jumping into the code part.

https://www.youtube.com/user/Cercopithecan/playlists

Most of his stuff is focused around Unity or Blender, but the code part of the Unity Tutorials can often be used without Unity as I have done.
He writes in c# the best language of all(my opinion) so i also had to translate from c# to java, not a big task, but for a rookie, which in a lot of ways i still view myself as, it did take a while.


The first video i used was this one about cellular automaton:

https://www.youtube.com/watch?v=v7yyZZjF1z4&list=PLFt_AvWsXl0eZgMK_DT5_biRkWXftAOf9&index=1

It fairly quickly explains as well as shows how to create basic 2d caves that can be adjusted by changing just a few integers.


The second video i used was the 5th one in the same series showing how to detect regions.
With our first "maps" generating small dots of walls and very small caves not connected to the main one.
This video shows how to find "wall" regions and "room" regions, and in doing so gives you the chance to remove regions you deem not worthy (small rooms, small pillars of wall).

https://www.youtube.com/watch?v=xYOG8kH2tF8&list=PLFt_AvWsXl0eZgMK_DT5_biRkWXftAOf9&index=5

Lastly I was planning to use videos 6-8 to generate passageways between rooms, but because he uses Unity functions to determine some things, I had to think of other ways to go about it.
There is currently a working theory of how to do this, but so far not yet implemented.


The menu system of the game for the most part is a combination of code from Michael Nielsen taken from his blog as well as a factory pattern example he whipped up in a jiffy, combined with some advise on which variables to send along, the menu although time consuming (so many options) did come along fairly quickly.

The basic menu: http://codeblog.dk/wordpress/?p=288

The basic factory pattern: INSERT LINK HERE!


Next came a lot of work on drawing the map, this required a few changes to the map generator and currently I am by no means happy with how its done, but for now it works.

The map being drawn it was time to get some Player action going.
The solution to spawning a player and later some creatures are my own and as such may lack in grace, but again, it works, to explain it le me first clarify some stuff.

A this point my map generator still doesn't have the ability to connect rooms, but will most often with the current settings generate 1 fairly large room in a random shape, I used this as the playable area of my game world.
With this in mind I knew that my player had to spawn in the biggest room of the map, not just any free map tile.
So I used the list of regions generated by the map generator that was to be used to connect rooms.

After sorting the list from biggest to small, I get the biggest room i.e the one with the biggest .Size and get the first element in the list and set the player starting location to this point, usually leading to a spawnpoint in the upper left corner of the map.

The monsters needing to be placed semi randomly use the same function but instead of the first Point in the room tile list, I use a random function for as many monsters as needed and get the Point of the random value, low = 0, high = list size.

Lastly with a Player and Creatures spawning it was time to add combat.

Here in large part I got the assistance of Morten, many of the combat methods and variables are of his design or was brainstormed while working with him, mostly changing names and values to fit with the rest of the program, his name is written in the files where either a single method or a whole section was used.

The handling of XP as well as increasing levels was the last thing added in regards to completing the goals set for this part of the course.


Specs for Text RPG

Classic textbased RPG game, it must include the following:

Player entity

Can raise it’s level through killing monsters - Done

Somekind of healthpool - Done

Atleast tree damaging skills - Done

Healthpool/Skill damage scales with level - Done

Monster entity

Healthpool - Done

Atleast one damage skill - Done

Higher level monsters should hit harder/have lager healthpools - Nope

Map/Levels

The player must be able to walk around a map, one step at a time - Done

Levels must be loaded from a textfile - Exchanged for a mapcreator with customizable width / height (endless map)
When a player steps onto a field with a mob, the game must enter combat mode - Done

Combat mode

Turn based - Done

If the player wins, he is awarded xp/level - Done

If the player dies, he’s bounced back to the previous field - Waaay to soft, if the player dies its game over, start fresh.
