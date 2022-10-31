package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

impozzzzzzxzxart java.awt.*;
import java.io.File;
import java.sql.Timestamp;


public class Engine {
    TERenderer ter = new TERenderer();
    public static String seed;
    public static int SEED;
    public static TETile[][] board;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final Font font = new Font(Font.SERIF, Font.PLAIN, 25);
    public static final File GIT_DIR = new File(System.getProperty("user.dir"));
    public static final File SAVED = Utils.join(GIT_DIR, "saved");
    public static final File newSave = Utils.join(SAVED, "new_Save");
    public static boolean gameStart = true;
    public static boolean gameOver = false;
    public static boolean toggle = true;
    public static boolean chinesetoggle = false;
    public static int currentscore = 0;
    public static int avatarcolor = 0;


    /**
     * Method used to create fresh frame with textual UI.
     *
     */
    public void drawFrame(String s) {
        StdDraw.clear(new Color(45, 64, 89));
        StdDraw.setPenColor(new Color(255, 180, 0));
        if (gameStart) {
            Font bigFont = new Font(Font.MONOSPACED, Font.BOLD, 80);
            StdDraw.setFont(bigFont);
            StdDraw.text(WIDTH / 2, HEIGHT - 10, "MIDNIGHT MAZE");

            Font bigFoot = new Font(Font.MONOSPACED, Font.BOLD, 25);
            StdDraw.setFont(bigFoot);
            StdDraw.setPenColor(new Color(217, 216, 208));
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 2, "New Game: (N)");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Load Game: (L)");
            StdDraw.text(WIDTH / 2, HEIGHT /2 - 2, "Quit: (:Q)");


            Font bigFont2 = new Font(Font.MONOSPACED, Font.BOLD, 40);
            StdDraw.setFont(bigFont2);
            StdDraw.setPenColor(new Color(255, 180, 0));
            StdDraw.text(WIDTH / 2, HEIGHT - 14, "Endless Fantasies & Pixeled Perfection");
            Font bigFont3 = new Font(Font.MONOSPACED, Font.BOLD, 30);
            StdDraw.setFont(bigFont3);
            StdDraw.text(WIDTH / 2, HEIGHT /2 - 9, "By: Darren Sun, Noel Pregot, Milgo Essa");

        } else {
            Font info = new Font(Font.MONOSPACED, Font.PLAIN, 10);
            StdDraw.line(0, HEIGHT - 3, WIDTH, HEIGHT - 3);
            StdDraw.text(5, HEIGHT - 1, s);
        }
        StdDraw.show();
    }

    public String mouseTile(double x_pos, double y_pos, TETile[][] world) {
        int x = (int) x_pos;
        int y = (int) y_pos;
        TETile tile = world[x][y];
        return tile.description();
    }

    public String newGame() {
        String typed = "";
        StdDraw.clear(new Color(45, 64, 89));
        StdDraw.setPenColor(new Color(255, 180, 0));
        Font fonte = new Font(Font.MONOSPACED, Font.BOLD, 40);
        StdDraw.setFont(fonte);
        StdDraw.text(WIDTH / 2, HEIGHT - 8, "Type seed followed by s!");
        boolean status = true;
        while (status) {
            StdDraw.clear(new Color(45, 64, 89));
            StdDraw.text(WIDTH / 2, HEIGHT - 8, "Type seed followed by s!");
            if (StdDraw.hasNextKeyTyped()) {
                char character = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (character == 'S') {
                    typed = typed + character + ",";
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, typed);
                    StdDraw.show();
                    break;
                }
                typed = typed + character;
                StdDraw.text(WIDTH / 2, HEIGHT / 2, typed);
                StdDraw.show();
            }
        }
        return typed;
    }

    //This method prints the HUD
    public void detecttileinfront(char typed, int x, int y) {
        if(chinesetoggle) {
            //Up
            if(typed == 'W') {
                String s;
                if (board[x][y + 1] == Tileset.mango){
                    s = "墙";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 不能穿越");
                    StdDraw.show();
                }
                if (board[x][y + 1] == Tileset.sapphire){
                    s = "地面";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 继续走");
                    StdDraw.show();
                }
                if (board[x][y + 1] == Tileset.LOCKED_DOOR){
                    s = "锁着的门";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x][y + 1] == Tileset.FLOWER){
                    s = "花 : 加一分";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }

            //Down
            if(typed == 'S') {
                String s;
                if (board[x][y - 1] == Tileset.mango){
                    s = "墙";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 不能穿越");
                    StdDraw.show();
                }
                if (board[x][y - 1] == Tileset.sapphire){
                    s = "地面";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 继续走");
                    StdDraw.show();
                }
                if (board[x][y - 1] == Tileset.LOCKED_DOOR){
                    s = "锁着的门";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x][y - 1] == Tileset.FLOWER){
                    s = "花 : 加一分";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }

            //Left
            if(typed == 'A') {
                String s;
                if (board[x - 1][y] == Tileset.mango){
                    s = "墙";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 不能穿越");
                    StdDraw.show();
                }
                if (board[x - 1][y] == Tileset.sapphire){
                    s = "地面";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 继续走");
                    StdDraw.show();
                }
                if (board[x - 1][y] == Tileset.LOCKED_DOOR){
                    s = "锁着的门";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x - 1][y] == Tileset.FLOWER){
                    s = "花 : 加一分";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }

            //Right
            if(typed == 'D') {
                String s;
                if (board[x + 1][y] == Tileset.mango){
                    s = "墙";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 不能穿越");
                    StdDraw.show();
                }
                if (board[x + 1][y] == Tileset.sapphire){
                    s = "地面";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : 继续走");
                    StdDraw.show();
                }
                if (board[x + 1][y] == Tileset.LOCKED_DOOR){
                    s = "锁着的门";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x + 1][y] == Tileset.FLOWER){
                    s = "花 : 加一分";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH/2, 39, "探索世界, 滿天的自由, 走过鲜花赚取积分");
            StdDraw.text(WIDTH/2, 38, "如果收集了百分之六十六的鮮花, 你就是勝利者");
            StdDraw.text(WIDTH/2, 1, "头像颜色 : 白{T}, 红{Y}, 绿{U}, 蓝{I}, 橙{O}, 黑{P}");
            StdDraw.text(4, 7, "W: 上");
            StdDraw.text(4, 6, "A: 左");
            StdDraw.text(4, 5, "S: 下");
            StdDraw.text(4, 4, "D: 右");
            StdDraw.text(4, 3, ":Q 保存並退出");
            StdDraw.text(4, 2, "K: 切換固定眼");
            StdDraw.text(4, 1, "J: 換語言");
            StdDraw.text(75, 39, "分数: " + currentscore);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            StdDraw.text(70, 1, "時間: " + timestamp.toString());
            StdDraw.show();
        }

        else {
            //Up
            if (typed == 'W') {
                String s;
                if (board[x][y + 1] == Tileset.mango) {
                    s = "Wall";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : No passage");
                    StdDraw.show();
                }
                if (board[x][y + 1] == Tileset.sapphire) {
                    s = "Floor";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : Traversible");
                    StdDraw.show();
                }
                if (board[x][y + 1] == Tileset.LOCKED_DOOR) {
                    s = "You found the locked door!";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x][y + 1] == Tileset.FLOWER){
                    s = "Flower : 1 Point";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }

            //Down
            if (typed == 'S') {
                String s;
                if (board[x][y - 1] == Tileset.mango) {
                    s = "Wall";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : No passage");
                    StdDraw.show();
                }
                if (board[x][y - 1] == Tileset.sapphire) {
                    s = "Floor";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : Traversible");
                    StdDraw.show();
                }
                if (board[x][y - 1] == Tileset.LOCKED_DOOR) {
                    s = "You found the locked door!";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x][y - 1] == Tileset.FLOWER){
                    s = "Flower : 1 Point";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }

            //Left
            if (typed == 'A') {
                String s;
                if (board[x - 1][y] == Tileset.mango) {
                    s = "Wall";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : No passage");
                    StdDraw.show();
                }
                if (board[x - 1][y] == Tileset.sapphire) {
                    s = "Floor";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : Traversible");
                    StdDraw.show();
                }
                if (board[x - 1][y] == Tileset.LOCKED_DOOR) {
                    s = "You found the locked door!";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x - 1][y] == Tileset.FLOWER){
                    s = "Flower : 1 Point";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }

            //Right
            if (typed == 'D') {
                String s;
                if (board[x + 1][y] == Tileset.mango) {
                    s = "Wall";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : No passage");
                    StdDraw.show();
                }
                if (board[x + 1][y] == Tileset.sapphire) {
                    s = "Floor";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s + " : Traversible");
                    StdDraw.show();
                }
                if (board[x + 1][y] == Tileset.LOCKED_DOOR) {
                    s = "You found the locked door!";
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
                if (board[x + 1][y] == Tileset.FLOWER){
                    s = "Flower : 1 Point";
                    currentscore += 1;
                    //The tile ahead is at x and y
                    StdDraw.setPenColor(Color.white);
                    StdDraw.text(5, 39, s);
                    StdDraw.show();
                }
            }
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH/2, 39, "EXPLORE THE WORLD AT YOUR OWN WILL! GET TO THE LOCKED DOOR TO ESCAPE THE TAVERN!");
            StdDraw.text(WIDTH/2, 38, "COLLECT 66% OF ALL FLOWERS TO WIN");
            StdDraw.text(WIDTH/2, 1, "AVATAR COLORS : WHITE{T}, RED{Y}, GREEN{U}, BLUE{I}, ORANGE{O}, BLACK{P}");
            StdDraw.text(4, 7, "W: Up");
            StdDraw.text(4, 6, "A: Left");
            StdDraw.text(4, 5, "S: Down");
            StdDraw.text(4, 4, "D: Right");
            StdDraw.text(4, 3, ":Q Quit");
            StdDraw.text(5, 2, "K: Toggle Finite Eye");
            StdDraw.text(5, 1, "J: Switch Language");
            StdDraw.text(75, 39, "Current Score: " + currentscore);
            StdDraw.text(71, 38, "WALK OVER FLOWERS TO EARN POINTS!");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            StdDraw.text(70, 1, "Current time: " + timestamp.toString());
            StdDraw.show();
        }
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        Room.initBoard();
        StdDraw.setCanvasSize(WIDTH* 16, HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        drawFrame("");
        gameStart = false;
        String inputs = "";
        //Get world
        while(board == null){
            if (StdDraw.hasNextKeyTyped()) {
                char typed = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (typed == 'N') {
                    inputs = inputs + "N";
                    seed = newGame();
                    inputs = inputs + seed;
                    ter.initialize(WIDTH, HEIGHT);
                    board = generateWorld(inputs);
                    ter.renderFrame(board);
                }
                if (typed == 'L') {
                    if (SAVED.exists()) {
                        String saved_Inputs = Utils.readContentsAsString(newSave);
                        inputs = saved_Inputs;
                        board = interactWithInputString(saved_Inputs);
                        ter.initialize(WIDTH, HEIGHT);
                        ter.renderFrame(board);
                    } else {
                        System.exit(0);

                    }
                }
            }
        }
        //Interact with the keys
        while (!gameOver) {
            if (StdDraw.hasNextKeyTyped()) {
                char typed = Character.toUpperCase(StdDraw.nextKeyTyped());
                interactWithCharacter(typed, inputs);
                inputs = inputs + typed;
                if(currentscore >= Room.coinsCount * 2 / 3){
                    StdDraw.clear(Color.YELLOW);
                    StdDraw.setPenColor(Color.black);
                    Font fOnt = new Font(Font.MONOSPACED, Font.PLAIN, 35);
                    StdDraw.setFont(fOnt);
                    if(chinesetoggle){
                        StdDraw.text(WIDTH/2, HEIGHT/2, "你赢了!");
                        StdDraw.text(WIDTH/2, HEIGHT/2 - 3, "打 :Q 來保存并退出!");
                    }
                    if(!chinesetoggle){
                        StdDraw.text(WIDTH/2, HEIGHT/2, "Victory!");
                        StdDraw.text(WIDTH/2, HEIGHT/2 - 3, "ENTER :Q TO SAVE AND EXIT!");
                    }
                    StdDraw.show();
                }
                StdDraw.show();
            }
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     *
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        //Make the world
        board = generateWorld(input);

        //Make sure there are no calls to rendering stuff*****

        //Deal with movement
        String[] inputcomponents = input.split(",");
        String movement = inputcomponents[1];
        String concatenateinputs = input.substring(0, input.length() - 1);
        if(movement.length() > 1) {
            for(int i = 0; i<movement.length(); i++){
                interactWithCharacter(movement.charAt(i), concatenateinputs);
            }
        }
        return board;
    }

    public TETile[][] generateWorld(String input){
        Room.initBoard();
        String[] entries = input.split("N|n");
        String seed = entries[1];
        entries[1] = seed.split("S|s")[0];
        Room.SEED = Long.parseLong(entries[1]);
        Room.createRooms();
        return Room.returnWorld();
    }

    //This helper method renders the frame per key press and also can render the world based on whether or not finite eye is toggled.
    private void helper (TETile[][] bd){
        if(toggle){
            TETile [][] newwrld = new TETile[WIDTH][HEIGHT];
            for(int i = 0; i < WIDTH; i++) {
                for(int j = 0; j < HEIGHT; j++){
                    newwrld[i][j] = Tileset.land;
                }
            }


            for(int k = -6; k < 6; k++) {
                for(int c = -6; c < 6; c++) {
                    try{
                        newwrld[Room.avatar_x + k][Room.avatar_y + c] = board[Room.avatar_x + k][Room.avatar_y + c];
                    }
                    catch (ArrayIndexOutOfBoundsException a){
                        toggle = false;
                        ter.renderFrame(bd);
                        return;
                    }
                }
            }
            ter.renderFrame(newwrld);
        }
        else {
            ter.renderFrame(bd);
        }
    }

    public void interactWithCharacter(char typed, String inputs){
        if(typed == 'K') {
            toggle = !toggle;
            helper(board);
        }

        if(typed == 'J') {
            chinesetoggle = !chinesetoggle;
        }

        // Move up
        if (typed == 'W') {
            int y = Room.avatar_y + 1;
            int x = Room.avatar_x;
            if (Room.checkAhead(x, y, board)) {
                return;
            }
            board = Room.eraseAvatar(Room.avatar_x, Room.avatar_y, board);
            if(board[x][y] != Tileset.mango) {
                board = Room.createAvatar(x, y, board);
            }
            helper(board);
            detecttileinfront(typed, x, y);
        }

        // Move left
        else if (typed == 'A') {
            int y = Room.avatar_y;
            int x = Room.avatar_x - 1;
            if (Room.checkAhead(x, y, board)) {
                return;
            } else {
                board = Room.eraseAvatar(Room.avatar_x, Room.avatar_y, board);
                if(board[x][y] != Tileset.mango) {
                    board = Room.createAvatar(x, y, board);
                }
                helper(board);
                detecttileinfront(typed, x, y);
            }

        }

        // Move down
        else if (typed == 'S') {
            int y = Room.avatar_y - 1;
            int x = Room.avatar_x;
            if (Room.checkAhead(x, y, board)) {
                return;
            }
            board = Room.eraseAvatar(Room.avatar_x, Room.avatar_y, board);
            if(board[x][y] != Tileset.mango) {
                board = Room.createAvatar(x, y, board);
            }
            helper(board);
            detecttileinfront(typed, x, y);

        }

        // Move right
        else if (typed == 'D') {
            int y = Room.avatar_y;
            int x = Room.avatar_x + 1;
            if (Room.checkAhead(x, y, board)) {
                return;
            }
            board = Room.eraseAvatar(Room.avatar_x, Room.avatar_y, board);
            if(board[x][y] != Tileset.mango) {
                board = Room.createAvatar(x, y, board);
            }
            helper(board);
            detecttileinfront(typed, x, y);
        }

        else if (typed == ':') {
            if (!SAVED.exists()) {
                SAVED.mkdir();
            }
            Utils.writeContents(newSave, inputs);
        }

        else if(typed == 'Q') {
            System.exit(0);
        }

        //Deals with avatar color selection
        else if(typed == 'T') {
            avatarcolor = 0;
        }
        else if(typed == 'Y') {
            avatarcolor = 1;
        }
        else if(typed == 'U') {
            avatarcolor = 2;
        }
        else if(typed == 'I') {
            avatarcolor = 3;
        }
        else if(typed == 'O') {
            avatarcolor = 4;
        }
        else if(typed == 'P') {
            avatarcolor = 5;
        }
    }
}