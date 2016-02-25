package GAME;

import java.util.*;

/**
 * Created by dot on 23-02-2016.
 */
public class MapGenerator {

    public int width = 200;
    public int height = 70;
    /*Bordersize og Playerviewfield SKAL pt være ens da man ellers risikere at få out of bounds exception
    På sigt skal draw player view ændres til at udfylde "tomt" kort med væg,
    pt ingen anelse pt hvordan jeg gør, dette er den midlertidige løsning */
    public int borderSize = 8; // OBS
    public int playerViewField = 8; // OBS

    public String seed = "Daniel";
    public Boolean useRandomSeed = false;
    public int randomFillPercent = 49;

    int[][] map;
    int[][] preBorderedMap;
    int[][] borderedMap;

    Coord playerStartingLocation;

    List<List<Coord>> regions;

    void Start() {
        GenerateMap();
    }

    void GenerateMap() {
        map = new int[width][height];
        RandomFillMap();

        for (int i = 0; i < 5; i ++) {
            SmoothMap();
        }

        ProcessMap ();



        preBorderedMap = map;

        determinePlayerStartingPosition();

        preBorderedMap[playerStartingLocation.tileX][playerStartingLocation.tileY] = 5;

        borderedMap = new int[width + borderSize * 2][height + borderSize * 2];

        for (int x = 0; x < borderedMap.length; x++) {
            for (int y = 0; y < borderedMap[x].length; y++) {
                if (x >= borderSize && x < width - borderSize && y >= borderSize && y < height - borderSize) {
                    borderedMap[x][y] = preBorderedMap[x - borderSize][y - borderSize];
                }
                else {
                    borderedMap[x][y] = 1;
                }
            }
        }
    }

    void ProcessMap() {
        List<List<Coord>> wallRegions = GetRegions (1);
        int wallThresholdSize = 50;

        for (List<Coord> wallRegion : wallRegions) {
            if (wallRegion.size() < wallThresholdSize) {
                for (Coord tile : wallRegion) {
                    map[tile.tileX][tile.tileY] = 0;
                }
            }
        }

        List<List<Coord>> roomRegions = GetRegions (0);
        int roomThresholdSize = 50;
        List<Room> survivingRooms = new ArrayList<Room>();


        for (List<Coord> roomRegion : roomRegions) {
            if (roomRegion.size() < roomThresholdSize) {
                for (Coord tile : roomRegion) {
                    map[tile.tileX][tile.tileY] = 1;
                }
            }
            else {
                survivingRooms.add(new Room(roomRegion, map));
            }
        }

        ConnectClosestRooms(survivingRooms);
    }

    void ConnectClosestRooms(List<Room> allRooms){

    }

    List<List<Coord>> GetRegions(int tileType) {
        regions = new ArrayList<List<Coord>>();
        int[][] mapFlags = new int[width][height];

        for (int x = 0; x < width; x ++) {
            for (int y = 0; y < height; y ++) {
                if (mapFlags[x][y] == 0 && map[x][y] == tileType) {
                    List<Coord> newRegion = GetRegionTiles(x,y);
                    regions.add(newRegion);

                    for (Coord tile : newRegion) {
                        mapFlags[tile.tileX][tile.tileY] = 1;
                    }
                }
            }
        }

        return regions;
    }

    List<Coord> GetRegionTiles(int startX, int startY) {
        List<Coord> tiles = new ArrayList<Coord> ();
        int[][] mapFlags = new int[width][height];
        int tileType = map [startX][startY];

        LinkedList<Coord> queue = new LinkedList<Coord> ();
        queue.add (new Coord (startX, startY));
        mapFlags [startX][startY] = 1;

        while (queue.size() > 0) {
            Coord tile = queue.remove();
            tiles.add(tile);

            for (int x = tile.tileX - 1; x <= tile.tileX + 1; x++) {
                for (int y = tile.tileY - 1; y <= tile.tileY + 1; y++) {
                    if (IsInMapRange(x,y) && (y == tile.tileY || x == tile.tileX)) {
                        if (mapFlags[x][y] == 0 && map[x][y] == tileType) {
                            mapFlags[x][y] = 1;
                            queue.add(new Coord(x,y));
                        }
                    }
                }
            }
        }

        return tiles;
    }


    public List<List<Coord>> SortRegionsBySize() {

        Collections.sort(regions, new Comparator<List>() {
            public int compare(List list2, List list1) {
                return Integer.valueOf(list1.size()).compareTo(Integer.valueOf(list2.size()));
            }
        });

        return regions;

    }


    boolean IsInMapRange(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }


    void RandomFillMap() {

        if (useRandomSeed) {
            seed = "";
            seed += System.currentTimeMillis();
        }

        Random pseudoRandom = new Random((seed.hashCode()));

        for (int x = 0; x < width; x ++) {
            for (int y = 0; y < height; y ++) {
                if (x == 0 || x == width-1 || y == 0 || y == height -1) {
                    map[x][y] = 1;
                }
                else {
                    map[x][y] = (pseudoRandom.nextInt(100) < randomFillPercent)? 1: 0;
                }
            }
        }
    }

    void SmoothMap() {
        for (int x = 0; x < width; x ++) {
            for (int y = 0; y < height; y ++) {
                int neighbourWallTiles = GetSurroundingWallCount(x,y);

                if (neighbourWallTiles > 4)
                    map[x][y] = 1;
                else if (neighbourWallTiles < 4)
                    map[x][y] = 0;

            }
        }
    }

    int GetSurroundingWallCount(int gridX, int gridY) {
        int wallCount = 0;
        for (int neighbourX = gridX - 1; neighbourX <= gridX + 1; neighbourX ++) {
            for (int neighbourY = gridY - 1; neighbourY <= gridY + 1; neighbourY ++) {
                if (IsInMapRange(neighbourX,neighbourY)) {
                    if (neighbourX != gridX || neighbourY != gridY) {
                        wallCount += map[neighbourX][neighbourY];
                    }
                }
                else {
                    wallCount ++;
                }
            }
        }

        return wallCount;
    }

    public void printRegionList() {
        List<List<Coord>> sortedRegionsBySize;
        sortedRegionsBySize = SortRegionsBySize();

        System.out.print(regions);
        System.out.println();
        System.out.print(sortedRegionsBySize);
    }

    void determinePlayerStartingPosition() {
        List<List<Coord>> sortedRegionsBySize;
        sortedRegionsBySize = SortRegionsBySize();
        playerStartingLocation = sortedRegionsBySize.remove(0).remove(0);
    }


    /**
     * A java version of struct containing simple Coord information
     */
    final class Coord {
        public int tileX;
        public int tileY;

        public Coord(int x, int y) {
            tileX = x;
            tileY = y;
        }
    }

    class Room {
        public List<Coord> tiles;
        public List<Coord> edgeTiles;
        public List<Room> connectedRooms;
        public int roomSize;

        public Room() {

        }

        public Room(List<Coord> roomTiles, int[][] map){
            tiles = roomTiles;
            roomSize = tiles.size();
            connectedRooms = new ArrayList<Room>();

            edgeTiles = new ArrayList<Coord>();
            for (Coord tile : tiles){
                for (int x = tile.tileX-1; x <= tile.tileX; x++) {
                    for (int y = tile.tileY-1; y <= tile.tileY+1; y++) {
                        if (x == tile.tileX || y == tile.tileY) {
                            if (map[x][y] == 1) {
                                edgeTiles.add(tile);
                            }
                        }
                    }
                }
            }


        }

        public void ConnectRooms(Room roomA, Room roomB) {
            roomA.connectedRooms.add(roomB);
            roomB.connectedRooms.add(roomA);
        }

        public boolean IsConnected(Room otherRoom) {
            return connectedRooms.contains(otherRoom);
        }
    }


    /**
     * Draws the map to console
     */
    void DrawWorldMap() {
        if (map != null) {
            for (int y = 0; y < height; y ++) {
                for (int x = 0; x < width; x ++) {
                    System.out.print(borderedMap [x][y]);
                }
                System.out.println("");
            }
        }
    }

    void DrawPlayerMap(Coord playerLocation) {
        for (int y = playerLocation.tileY - playerViewField; y <= playerLocation.tileY + playerViewField; y++) {
            for (int x = playerLocation.tileX - playerViewField; x <= playerLocation.tileX + playerViewField; x++) {
                if (IsInMapRange(playerLocation.tileX,playerLocation.tileY)) {
                    System.out.print(borderedMap[x+borderSize][y+borderSize]);}
            }
            System.out.println("");
        }
    }
}

