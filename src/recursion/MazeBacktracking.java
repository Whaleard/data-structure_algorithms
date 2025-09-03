package recursion;

/**
 * 迷宫回溯问题
 * @author Mr.MC
 */
public class MazeBacktracking {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示不可移动区域（墙）
        // 先把上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 再把左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 剩余不可移动区域置1
        map[3][1] = 1;
        map[3][2] = 1;

        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 使用递归回溯给小球找路
        getWay(map, 1, 1);

        // 输出新的地图，小球走过并标识过的递归
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来找出路
     *
     * 说明：1、当map[i][j]为0表示该点没有走过；为1表示墙；为2表示通路可以走；为3表示该点已经走过；但是走不通
     *      2、走迷宫时，需要定义一个策略：下->右->上->左，如果该点走不通，再回溯
     * @param map 表示地图
     * @param i 从哪个位置开始找（地图二维数组的行索引）
     * @param j 从哪个位置开始找（地图二维数组的列索引）
     * @return
     */
    public static boolean getWay(int[][] map, int i, int j) {
        // 通路已经找到
        if (map[6][5] == 2) {
            return true;
        } else {
            // 如果当前这个点还没有走过
            if (map[i][j] == 0) {
                // 按照策略 下->右->上->左 走
                // 假定该点可以走通
                map[i][j] = 2;
                // 向下走
                if (getWay(map, i + 1, j)) {
                    return true;
                } else if (getWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (getWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (getWay(map, i, j - 1)) {
                    // 向左走
                    return true;
                } else {
                    // 说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
