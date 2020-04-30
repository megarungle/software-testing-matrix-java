package Matrix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Matrix {

    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; ++i) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }

        return cell;
    }

    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix[0].length != secondMatrix.length) {
            throw new java.lang.IllegalArgumentException("The number of columns of the first matrix must " +
                    "be equal to the number of rows of the second matrix!");
        }

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; ++row) {
            for (int col = 0; col < result[row].length; ++col) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    public static void print(double[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }

    public static double[][] sum(double[][] firstMatrix, double[][] secondMatrix) {
        if ((firstMatrix.length != secondMatrix.length) && (firstMatrix[0].length != secondMatrix[0].length)) {
            throw new java.lang.IllegalArgumentException("Matrix sizes must be equal!");
        }

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];

        for (int row = 0; row < result.length; ++row) {
            for (int col = 0; col < result[row].length; ++col) {
                result[row][col] = firstMatrix[row][col] + secondMatrix[row][col];
            }
        }

        return  result;
    }

    public static double[][] multiplyNum(double[][] matrix, double c) {
        double[][] result = new double[matrix.length][matrix[0].length];

        for (int row = 0; row < result.length; ++row) {
            for (int col = 0; col < result[row].length; ++col) {
                result[row][col] = c * matrix[row][col];
            }
        }

        return result;
    }

    public static double[][] sub(double[][] firstMatrix, double[][] secondMatrix) {
        if ((firstMatrix.length != secondMatrix.length) && (firstMatrix[0].length != secondMatrix[0].length)) {
            throw new java.lang.IllegalArgumentException("Matrix sizes must be equal!");
        }

        double[][] tmp = multiplyNum(secondMatrix, -1);

        return sum(firstMatrix, tmp);
    }

    private static boolean isSquare(double[][] matrix) {
        return (matrix.length == matrix[0].length);
    }

    private static double[][] getSubmatrix(double[][] matrix, int rowToExclude, int colToExclude) {
        double[][] result = new double[matrix.length - 1][];

        for (int i = 0; i < matrix.length; ++i)
        {
            if (i < matrix.length - 1)
                result[i] = new double[matrix.length - 1];
            for (int j = 0; j < matrix.length; ++j)
                if (i != rowToExclude && j != colToExclude)
                    result[i < rowToExclude ? i : i - 1][j < colToExclude ? j : j - 1] = matrix[i][j];
        }

        return result;
    }

    public static double det(double[][] matrix) {
        if (!isSquare(matrix)) {
            throw new java.lang.IllegalArgumentException("The matrix must be square!");
        }

        if (matrix.length == 1) {
            return matrix[0][0];
        }

        double det = 0;
        for (int i = 0; i < matrix.length; ++i)
        {
            if (i % 2 == 0)
                det += matrix[0][i] * det(getSubmatrix(matrix, 0, i));
            else
                det -= matrix[0][i] * det(getSubmatrix(matrix, 0, i));
        }

        return det;
    }

    public static boolean isEqual(double[][] firstMatrix, double[][] secondMatrix) {
        if ((firstMatrix.length != secondMatrix.length) || (firstMatrix[0].length != secondMatrix[0].length)) {
            return false;
        }

        for (int row = 0; row < firstMatrix.length; ++row) {
            for (int col = 0; col < firstMatrix[0].length; ++col) {
                if (firstMatrix[row][col] != secondMatrix[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static double[][] readFromFile(String s) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/resources/" + s + ".txt"),
                                                    StandardCharsets.UTF_8);

            int col = ((lines.get(0)).split(" ")).length;
            int row = lines.size();

            double[][] result = new double[row][col];

            int i = 0;
            for (String line: lines) {
                String[] linesSplit = line.split(" ");
                int j = 0;
                for (String lineSplit: linesSplit) {
                    result[i][j] = Double.parseDouble(lineSplit);
                    ++j;
                }
                ++i;
            }

            return result;
        }
        catch (IOException e) {
            System.out.print("Can not open or find " + e.getMessage() + " file!");
            return null;
        }
    }
}
