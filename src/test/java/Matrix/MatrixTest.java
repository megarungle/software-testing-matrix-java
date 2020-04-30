package Matrix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void testMultiplyMatricesNormalSizes1() {
        double[][] fM = {{1, 2}, {2, 3}, {3, 4}};
        double[][] sM = {{-1, -2, -3}, {-2, -3, -4}};
        double[][] res = {{-5, -8, -11}, {-8, -13, -18}, {-11, -18, -25}};

        assertTrue(Matrix.isEqual(res, Matrix.multiplyMatrices(fM, sM)));
    }

    @Test
    public void testMultiplyMatricesNormalSizes2() {
        double[][] fM = {{5, -2}, {-31, 0}, {10, 19}};
        double[][] sM = {{1, -20, 0}, {0, 40, 14}};
        double[][] res = {{5, -180, -28}, {-31, 620, 0}, {10, 560, 266}};

        assertTrue(Matrix.isEqual(res, Matrix.multiplyMatrices(fM, sM)));
    }

    @Test
    public void testMultiplyMatricesBadSizes() throws IllegalArgumentException {
        int row = 50;
        int col = 100;

        double[][] fM = new double[row][col];
        double[][] sM = new double[row][col];

        assertThrows(IllegalArgumentException.class, () -> Matrix.multiplyMatrices(fM, sM));
    }

    @Test
    public void testSumNormalSizes1() {
        double[][] fM = {{1, 2}, {2, 3}, {3, 4}};
        double[][] sM = {{-1, -2}, {-2, -3}, {-3, -4}};
        double[][] res = {{0, 0}, {0, 0}, {0, 0}};

        assertTrue(Matrix.isEqual(res, Matrix.sum(fM, sM)));
    }

    @Test
    public void testSumNormalSizes2() {
        double[][] fM = {{59, -6}, {21, 2}, {5, 5}};
        double[][] sM = {{1, 2}, {-1, -5}, {2, 2}};
        double[][] res = {{60, -4}, {20, -3}, {7, 7}};

        assertTrue(Matrix.isEqual(res, Matrix.sum(fM, sM)));
    }

    @Test
    public void testSumBadSizes() throws IllegalArgumentException {
        int row = 99;
        int col = 2;

        double[][] fM = new double[row][col];
        double[][] sM = new double[col][row];

        assertThrows(IllegalArgumentException.class, () -> Matrix.sum(fM, sM));
    }

    @Test
    public void testMultiplyNum() {
        double[][] m = {{7, 70}, {21, 210}, {-100, -1000}, {1, 0}};
        double c = 2;
        double[][] res = {{14, 140}, {42, 420}, {-200, -2000}, {2, 0}};

        assertTrue(Matrix.isEqual(res, Matrix.multiplyNum(m, c)));
    }

    @Test
    public void testSubNormalSizes1() {
        double[][] fM = {{10, 11}, {22, 33}, {-3, 50}};
        double[][] sM = {{10, 11}, {3, -4}, {0, 5}};
        double[][] res = {{0, 0}, {19, 37}, {-3, 45}};

        assertTrue(Matrix.isEqual(res, Matrix.sub(fM, sM)));
    }

    @Test
    public void testSubNormalSizes2() {
        double[][] fM = {{9, 3}, {4, 2}, {-16, -4}};
        double[][] sM = {{3, 9}, {-2, -4}, {0, 1}};
        double[][] res = {{6, -6}, {6, 6}, {-16, -5}};

        assertTrue(Matrix.isEqual(res, Matrix.sub(fM, sM)));
    }

    @Test
    public void testSubBadSizes() throws IllegalArgumentException {
        int row = 24;
        int col = 60;

        double[][] fM = new double[row][col];
        double[][] sM = new double[col][row];

        assertThrows(IllegalArgumentException.class, () -> Matrix.sub(fM, sM));
    }

    @Test
    public void testDet() {
        double[][] m = {{7, 70, 3}, {-21, 5, 0}, {-1, 0, 1}};
        double res = 1520;

        assertEquals(res, Matrix.det(m));
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(new double[][]{{100, 101, -102}, {-102, 101, 100}},
                             new double[][]{{1, 2, 3}, {3, 2, 1}},
                             new double[][]{{101, 103, -99}, {-99, 103, 101}}),
                Arguments.of(new double[][]{{-5, 6}, {1, 0}, {2, -3}, {0, 4}},
                             new double[][]{{1, 4}, {10, -3}, {-6, -3}, {1, 0}},
                             new double[][]{{-4, 10}, {11, -3}, {-4, -6}, {1, 4}}),
                Arguments.of(new double[][]{{-400}, {200}, {-1}, {1000}, {5200}},
                             new double[][]{{-100}, {300}, {6}, {230}, {-1}},
                             new double[][]{{-500}, {500}, {5}, {1230}, {5199}}),
                Arguments.of(new double[][]{{6, 7, -9}, {-1, -2, 3}},
                             new double[][]{{-16, -17, 19}, {11, 12, -13}},
                             new double[][]{{-10, -10, 10}, {10, 10, -10}})
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void parTestSumNormalSizes(double[][] firstMatrix, double[][] secondMatrix, double[][] result) {
        assertTrue(Matrix.isEqual(result, Matrix.sum(firstMatrix, secondMatrix)));
    }
}
