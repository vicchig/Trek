
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public MapGenerator() {
    }

    public static int[][] generateAll(int rooms) {
        int[][] map = new int[rooms * 2 + 3][rooms * 2 + 3];
        map[map.length / 2][map.length / 2] = 1;
        generateMap(map, map.length / 2, map.length / 2, rooms, rooms);
        bossRoom(map);
        createSpecialRoom(map, rooms, 2);
        createSecretRoom(map, rooms, 8);
        //display(map); // subject to removal
        return map;
    }

    public static void display(int[][] box) {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if (box[i][j] == 1) {
                    if (i == box.length / 2 && j == box.length / 2) {
                        System.out.print(ANSI_GREEN + "1" + " " + ANSI_RESET); //box[i][j] initially instead of █
                    } else {
                        System.out.print(ANSI_RED + "1" + " " + ANSI_RESET); //box[i][j] initially
                    }
                }
                if (box[i][j] == 0) {
                    System.out.print(ANSI_BLUE + "  " + ANSI_RESET);
                }
                if (box[i][j] == 9) {
                    System.out.print(ANSI_RESET + "9" + " " + ANSI_RESET);
                }
                if (box[i][j] == 8) {
                    System.out.print(ANSI_CYAN + "8" + " " + ANSI_RESET);
                }
                if (box[i][j] == 2) {
                    System.out.print(ANSI_BLUE + "2" + " " + ANSI_RESET);
                }
            }
            System.out.println("");
        }
    }

    public static int[][] generateMap(int[][] map, int x, int y, int rooms, int max) {
        int rand;
        int tempx = x, tempy = y;

        if (rooms <= 0) {
            return map;
        }

        rand = randNum(1, 4);
        if (rand < 4) {
            int randx, randy;
            do {
                randx = randNum(0, map.length - 1);
                randy = randNum(0, map.length - 1);
            } while (!((map[randy][randx] == 1) && ((map[randy - 1][randx + 1] == 0 && map[randy + 1][randx + 1] == 0)
                    || (map[randy + 1][randx - 1] == 0 && map[randy + 1][randx + 1] == 0)
                    || (map[randy + 1][randx - 1] == 0 && map[randy - 1][randx - 1] == 0)
                    || (map[randy - 1][randx + 1] == 0 && map[randy - 1][randx - 1] == 0))));
            return generateMap(map, randx, randy, rooms, max);

        } else {
            rand = randNum(1, 4);
            if (rand == 1 && map[y - 1][x] == 0 && map[y - 2][x] == 0 && map[y - 1][x - 1] == 0 && map[y - 1][x + 1] == 0) {
                map[y - 1][x] = 1;
                tempy -= 1;
            } else if (rand == 2 && map[y][x + 1] == 0 && map[y][x + 2] == 0 && map[y - 1][x + 1] == 0 && map[y + 1][x + 1] == 0) {
                map[y][x + 1] = 1;
                tempx += 1;
            } else if (rand == 3 && map[y][x - 1] == 0 && map[y][x - 2] == 0 && map[y - 1][x - 1] == 0 && map[y + 1][x - 1] == 0) {
                map[y][x - 1] = 1;
                tempx -= 1;
            } else if (rand == 4 && map[y + 1][x] == 0 && map[y + 2][x] == 0 && map[y + 1][x + 1] == 0 && map[y + 1][x - 1] == 0) {
                map[y + 1][x] = 1;
                tempy += 1;
            } else {
                return generateMap(map, tempx, tempy, rooms, max);
            }
            map[tempy][tempx] = 1;
            return generateMap(map, tempx, tempy, rooms - 1, max);
        }
    }

    public static int randNum(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    private static int[][] bossRoom(int[][] map) {
        boolean startAgain = true;
        int bossRoomx = map.length / 2, bossRoomy = map.length / 2;
        
        while (true) {
            if (startAgain) {
                startAgain = !startAgain;
                ArrayList<Integer[]> paths = new ArrayList();

                for (int i = 1; i < map.length - 1; i++) {
                    for (int j = 1; j < map[i].length - 1; j++) {
                        if (paths(map, j, i) == 1) {
                            paths.add(new Integer[]{j, i});
                        }
                    }
                }

                for (int i = 0; i < paths.size(); i++) {
                    if (Math.abs(bossRoomx - map.length / 2) + Math.abs(bossRoomy - map.length / 2) < Math.abs(paths.get(i)[0] - map.length / 2) + Math.abs(paths.get(i)[1] - map.length / 2)) {
                        bossRoomx = paths.get(i)[0];
                        bossRoomy = paths.get(i)[1];
                    }
                }

                int rand = randNum(1, 4);
                if (rand == 1 && map[bossRoomy - 1][bossRoomx] == 0 && map[bossRoomy - 2][bossRoomx] == 0 && map[bossRoomy - 1][bossRoomx - 1] == 0 && map[bossRoomy - 1][bossRoomx + 1] == 0) {
                    map[bossRoomy - 1][bossRoomx] = 1;
                    bossRoomy -= 1;
                } else if (rand == 2 && map[bossRoomy][bossRoomx + 1] == 0 && map[bossRoomy][bossRoomx + 2] == 0 && map[bossRoomy - 1][bossRoomx + 1] == 0 && map[bossRoomy + 1][bossRoomx + 1] == 0) {
                    map[bossRoomy][bossRoomx + 1] = 1;
                    bossRoomx += 1;
                } else if (rand == 3 && map[bossRoomy][bossRoomx - 1] == 0 && map[bossRoomy][bossRoomx - 2] == 0 && map[bossRoomy - 1][bossRoomx - 1] == 0 && map[bossRoomy + 1][bossRoomx - 1] == 0) {
                    map[bossRoomy][bossRoomx - 1] = 1;
                    bossRoomx -= 1;
                } else if (rand == 4 && map[bossRoomy + 1][bossRoomx] == 0 && map[bossRoomy + 2][bossRoomx] == 0 && map[bossRoomy + 1][bossRoomx + 1] == 0 && map[bossRoomy + 1][bossRoomx - 1] == 0) {
                    map[bossRoomy + 1][bossRoomx] = 1;
                    bossRoomy += 1;
                } else {
                    startAgain = true;
                }

                map[bossRoomy][bossRoomx] = 9;
                return map;
            }
        }
    }

    public static int paths(int[][] map, int x, int y) {
        int count = 0;

        if (map[y + 1][x] == 1 && map[y][x] == 1) {
            count += 1;
        }
        if (map[y - 1][x] == 1 && map[y][x] == 1) {
            count += 1;
        }
        if (map[y][x + 1] == 1 && map[y][x] == 1) {
            count += 1;
        }
        if (map[y][x - 1] == 1 && map[y][x] == 1) {
            count += 1;
        }
        return count;
    }

    public static int[][] createSpecialRoom(int[][] map, int rooms, int type) {
        int rand;
        rand = randNum(1, rooms);
        int xloc = 0, yloc = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    rand -= 1;
                }
                if (rand == 0) {
                    xloc = j;
                    yloc = i;
                    rand -= 1;
                }
            }
        }
        if (checkRoomPossibility(map, xloc, yloc, type)) {
            return createSpecialRoom(map, rooms, type);
        }
        return map;
    }

    public static int[][] createSecretRoom(int[][] map, int rooms, int type) {
        int rand;
        int count = 0;
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (checkRoomPossibility(map, i, j)) {
                    count += 1;
                }
            }
        }
        rand = randNum(1, count);
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (checkRoomPossibility(map, i, j)) {
                    rand -= 1;
                }
                if (rand == 0) {
                    map[i][j] = type;
                    rand -= 1;
                }
            }
        }

        return map;
    }

    public static boolean checkRoomPossibility(int[][] map, int x, int y, int type) {
        int rand = randNum(1, 4);
        if (rand == 1 && map[y - 1][x] == 0 && map[y - 2][x] == 0 && map[y - 1][x - 1] == 0 && map[y - 1][x + 1] == 0) {
            map[y - 1][x] = type;
            return false;
        } else if (rand == 2 && map[y + 1][x] == 0 && map[y + 2][x] == 0 && map[y + 1][x + 1] == 0 && map[y + 1][x - 1] == 0) {
            map[y + 1][x] = type;
            return false;
        } else if (rand == 3 && map[y][x + 1] == 0 && map[y][x + 2] == 0 && map[y - 1][x + 1] == 0 && map[y + 1][x + 1] == 0) {
            map[y][x + 1] = type;
            return false;
        } else if (rand == 4 && map[y][x - 1] == 0 && map[y][x - 2] == 0 && map[y - 1][x - 1] == 0 && map[y + 1][x - 1] == 0) {
            map[y][x - 1] = type;
            return false;
        }
        return true;
    }

    public static boolean checkRoomPossibility(int[][] map, int i, int j) {
        if (map[i][j] == 0 && ((map[i - 1][j] != 0 && map[i - 1][j] != 9 && map[i + 1][j] != 0 && map[i + 1][j] != 9)
                || (map[i][j - 1] != 0 && map[i][j - 1] != 9 && map[i][j + 1] != 0 && map[i][j + 1] != 9)
                || (map[i - 1][j] != 0 && map[i - 1][j] != 9 && map[i][j + 1] != 0 && map[i][j + 1] != 9)
                || (map[i - 1][j] != 0 && map[i - 1][j] != 9 && map[i][j - 1] != 0 && map[i][j - 1] != 9)
                || (map[i + 1][j] != 0 && map[i + 1][j] != 9 && map[i][j - 1] != 0 && map[i][j - 1] != 9)
                || (map[i + 1][j] != 0 && map[i + 1][j] != 9 && map[i][j + 1] != 0 && map[i][j + 1] != 9))) {
            return true;
        }
        return false;
    }
}
