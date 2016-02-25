package GAME;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by dot on 23-02-2016.
 */
public class MapGenerator {

    ConsolePrinter consolePrinter = new ConsolePrinter();

    public int width = 200;
    public int height = 70;

    public int getBorderSize() {
        return borderSize;
    }

    /*Bordersize og Playerviewfield SKAL pt være ens da man ellers risikere at få out of bounds exception
        På sigt skal draw player view ændres til at udfylde "tomt" kort med væg,
        pt ingen anelse pt hvordan jeg gør, dette er den midlertidige løsning */
    public int borderSize = 14; // OBS
    public int playerViewField = 14; // OBS

    public String seed = "Daniel";
    public Boolean useRandomSeed = false;
    public int randomFillPercent = 49;

    int[][] map;
    int[][] preBorderedMap;
    int[][] borderedMap;

    Point playerStartingLocation;

    List<List<Point>> regions;

    void Start() {
        GenerateMap();
    }

    void GenerateMap() {
        map = new int[width][height];
        RandomFillMap();

        for (int i = 0; i < 5; i++) {
            SmoothMap();
        }

        ProcessMap();


        preBorderedMap = map;

        determinePlayerStartingPosition();

        borderedMap = new int[width + borderSize * 2][height + borderSize * 2];

        for (int x = 0; x < borderedMap.length; x++) {
            for (int y = 0; y < borderedMap[x].length; y++) {
                if (x >= borderSize && x < width - borderSize && y >= borderSize && y < height - borderSize) {
                    borderedMap[x][y] = preBorderedMap[x - borderSize][y - borderSize];
                } else {
                    borderedMap[x][y] = 1;
                }
            }
        }
    }

    void ProcessMap() {
        List<List<Point>> wallRegions = GetRegions(1);
        int wallThresholdSize = 50;

        for (List<Point> wallRegion : wallRegions) {
            if (wallRegion.size() < wallThresholdSize) {
                for (Point tile : wallRegion) {
                    map[tile.x][tile.y] = 0;
                }
            }
        }

        List<List<Point>> roomRegions = GetRegions(0);
        int roomThresholdSize = 50;
        List<Room> survivingRooms = new ArrayList<Room>();


        for (List<Point> roomRegion : roomRegions) {
            if (roomRegion.size() < roomThresholdSize) {
                for (Point tile : roomRegion) {
                    map[tile.x][tile.y] = 1;
                }
            } else {
                survivingRooms.add(new Room(roomRegion, map));
            }
        }

        ConnectClosestRooms(survivingRooms);
    }

    void ConnectClosestRooms(List<Room> allRooms) {

    }

    List<List<Point>> GetRegions(int tileType) {
        regions = new ArrayList<List<Point>>();
        int[][] mapFlags = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (mapFlags[x][y] == 0 && map[x][y] == tileType) {
                    List<Point> newRegion = GetRegionTiles(x, y);
                    regions.add(newRegion);

                    for (Point tile : newRegion) {
                        mapFlags[tile.x][tile.y] = 1;
                    }
                }
            }
        }

        return regions;
    }

    List<Point> GetRegionTiles(int startX, int startY) {
        List<Point> tiles = new ArrayList<Point>();
        int[][] mapFlags = new int[width][height];
        int tileType = map[startX][startY];

        LinkedList<Point> queue = new LinkedList<Point>();
        queue.add(new Point(startX, startY));
        mapFlags[startX][startY] = 1;

        while (queue.size() > 0) {
            Point tile = queue.remove();
            tiles.add(tile);

            for (int x = tile.x - 1; x <= tile.x + 1; x++) {
                for (int y = tile.y - 1; y <= tile.y + 1; y++) {
                    if (IsInMapRange(x, y) && (y == tile.y || x == tile.x)) {
                        if (mapFlags[x][y] == 0 && map[x][y] == tileType) {
                            mapFlags[x][y] = 1;
                            queue.add(new Point(x, y));
                        }
                    }
                }
            }
        }

        return tiles;
    }


    public List<List<Point>> SortRegionsBySize() {

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

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    map[x][y] = 1;
                } else {
                    map[x][y] = (pseudoRandom.nextInt(100) < randomFillPercent) ? 1 : 0;
                }
            }
        }
    }

    void SmoothMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int neighbourWallTiles = GetSurroundingWallCount(x, y);

                if (neighbourWallTiles > 4)
                    map[x][y] = 1;
                else if (neighbourWallTiles < 4)
                    map[x][y] = 0;

            }
        }
    }

    int GetSurroundingWallCount(int gridX, int gridY) {
        int wallCount = 0;
        for (int neighbourX = gridX - 1; neighbourX <= gridX + 1; neighbourX++) {
            for (int neighbourY = gridY - 1; neighbourY <= gridY + 1; neighbourY++) {
                if (IsInMapRange(neighbourX, neighbourY)) {
                    if (neighbourX != gridX || neighbourY != gridY) {
                        wallCount += map[neighbourX][neighbourY];
                    }
                } else {
                    wallCount++;
                }
            }
        }

        return wallCount;
    }

    public void printRegionList() {
        List<List<Point>> sortedRegionsBySize;
        sortedRegionsBySize = SortRegionsBySize();

        System.out.print(regions);
        System.out.println();
        System.out.print(sortedRegionsBySize);
    }

    void determinePlayerStartingPosition() {
        List<List<Point>> sortedRegionsBySize;
        sortedRegionsBySize = SortRegionsBySize();
        playerStartingLocation = sortedRegionsBySize.remove(0).remove(0);
    }

    class Room {
        public List<Point> tiles;
        public List<Point> edgeTiles;
        public List<Room> connectedRooms;
        public int roomSize;

        public Room() {

        }

        public Room(List<Point> roomTiles, int[][] map) {
            tiles = roomTiles;
            roomSize = tiles.size();
            connectedRooms = new ArrayList<Room>();

            edgeTiles = new ArrayList<Point>();
            for (Point tile : tiles) {
                for (int x = tile.x - 1; x <= tile.x; x++) {
                    for (int y = tile.y - 1; y <= tile.y + 1; y++) {
                        if (x == tile.x || y == tile.y) {
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
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    System.out.print(borderedMap[x][y]);
                }
                System.out.println("");
            }
        }
    }

    void DrawPlayerMap(Point playerLocation) {
        consolePrinter.clearScreen();
        borderedMap[playerLocation.x+borderSize][playerLocation.y+borderSize] = 5;
        for (int y = playerLocation.y - playerViewField; y <= playerLocation.y + playerViewField; y++) {
            for (int x = playerLocation.x - playerViewField; x <= playerLocation.x + playerViewField; x++) {
                if (IsInMapRange(playerLocation.x, playerLocation.y)) {
                    consolePrinter.printMap(borderedMap[x + borderSize][y + borderSize]);
                }

            }
            System.out.println("");
        }
        borderedMap[playerLocation.x + borderSize][playerLocation.y + borderSize] = 0;
    }
}

