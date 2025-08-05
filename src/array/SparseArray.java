package array;

/**
 * 稀疏数组：当一个数组中大部分元素为0.或者为同一个值的数组时，可以使用稀疏数组来保存该数组
 * 稀疏数组的处理方法：
 *      1、记录数组一共有几行几列，有多少个不同的值
 *      2、把具有不同值的元素的行列及值记录在一个小规模数组中，从而缩小程序的规模
 *
 * 二维数组转稀疏数组思路
 *      1、遍历原始的二维数组，得到有效数据的个数sum
 *      2、根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
 *      3、将二维数组的有效数据存入到稀疏数组
 *
 * 稀疏数组转原始的二维数组思路
 *      1、先读取稀疏数组的第一行，根据第一行的数据，创建原始二维数组
 *      2、读取稀疏数组第一行后的数据，并赋值给原始的二维数组即可
 *
 * 转换后的二维数组有n行3列，第一列存储原数组非0值数据对应的行号，第二列存储原数组非0值数据对应的列号，第三列存储原数组非0值数据的值，
 * 其中a[0][0]存放原数组的总行数，a[0][1]存放原数组的总列数，a[0][2]存放原数组有多少个非0值数据
 *
 * @author Mr.MC
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0：没有棋子；1：黑子；2：蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        // 输出原始的二维数组
        System.out.println("============原始的二维数组============");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 二维数组转稀疏数组
        // 1、先遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2、创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1.length;
        sparseArr[0][2] = sum;

        // 用于记录稀疏数组当前行数
        int line = 1;
        // 遍历二维数组，将非0值存放到稀疏数组中
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr[line][0] = i;
                    sparseArr[line][1] = j;
                    sparseArr[line][2] = chessArr1[i][j];
                    line++;
                }
            }
        }

        // 输出稀疏数组
        System.out.println();
        System.out.println("============转换后的稀疏数组============");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 稀疏数组转二维数组
        // 1、先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2、再读取稀疏数组第一行后的数据，并赋值给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出复原的二维数组
        System.out.println();
        System.out.println("============复原的二维数组============");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
