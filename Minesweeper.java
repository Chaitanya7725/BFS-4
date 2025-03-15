import java.util.Arrays;

// TC: O(m * n) in the worst case, all the coordinates in the matrices will be traversed 
// SC: O(m * n) which is the recursions stack of dfs being called.

// Runs successfully on leetcode.
// starting from the initial coordinates, the execution will check itself and later for 
// count of mines. if mine is found at the current then it will set it as X and return. 
// if not found then it will check in its surroundings as per DFS modifying the current board.
public class Minesweeper {

    public static void main(String[] args) {
        char[][] board = new char[][] { { 'B', '1', 'E', '1', 'B' }, { 'B', '1', 'M', '1', 'B' },
                { 'B', '1', '1', '1', 'B' }, { 'B', 'B', 'B', 'B', 'B' } }; // [["B","1","E","1","B"],["B","1","X","1","B"],
        // ["B","1","1","1","B"],["B","B","B","B","B"]]
        for (char[] cs : updateBoard(board, new int[] { 1, 2 })) {
            System.out.println(Arrays.toString(cs));
        }
    }

    static int m;
    static int n;
    static int[][] dirs;

    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;
        m = board.length;
        n = board[0].length;
        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click);
        return board;
    }

    private static void dfs(char[][] board, int[] click) {
        // base
        if (click[0] < 0 || click[0] >= m || click[1] < 0 || click[0] >= n || board[click[0]][click[1]] != 'E')
            return;
        // logic
        int count = count(board, click);
        if (count == 0) {
            board[click[0]][click[1]] = 'B';
            for (int[] dir : dirs) {
                int nr = dir[0] + click[0];
                int nc = dir[1] + click[1];
                dfs(board, new int[] { nr, nc });
            }
        } else {
            board[click[0]][click[1]] = (char) (count + '0');
        }
    }

    private static int count(char[][] board, int[] click) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + click[0];
            int nc = dir[1] + click[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}