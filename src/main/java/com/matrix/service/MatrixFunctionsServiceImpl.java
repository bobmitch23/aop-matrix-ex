package com.matrix.service;

import com.matrix.domain.Matrix;
import org.springframework.stereotype.Component;

@Component
public class MatrixFunctionsServiceImpl implements MatrixFunctionsService {

    @Override
    public Matrix multiply(Matrix firstMatrix, Matrix secondMatrix) {
        if (firstMatrix.getNumColumns() != secondMatrix.getNumRows()) {
            throw new IllegalArgumentException("The number of columns in the first matrix must equal the number of rows in the second matrix");
        }
        Matrix result = new Matrix(firstMatrix.getNumRows(), secondMatrix.getNumColumns());

        for (int row = 0; row < result.getNumRows(); row++) {
            for (int col = 0; col < result.getNumColumns(); col++) {
                result.setValue(row, col, multiplyMatricesCell(firstMatrix, secondMatrix, row, col));
            }
        }

        return result;
    }

    private double multiplyMatricesCell(Matrix firstMatrix, Matrix secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.getNumRows(); i++) {
            cell += firstMatrix.getValue(row, i) * secondMatrix.getValue(i, col);
        }
        return cell;
    }

    @Override
    public double findDeterminant(Matrix matrix) {
        if (matrix.getNumRows() != matrix.getNumColumns()) {
            throw new IllegalArgumentException();
        }
        if (matrix.getNumRows() == 1) {
            return matrix.getValue(0, 0);
        } else {
            double sum = 0;
            int sign;
            int n = matrix.getNumRows();
            for (int i = 0; i < n; i++) {
                Matrix smallerMatrix = new Matrix(n - 1, n - 1);
                for (int a = 1; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        if (b < i) {
                            smallerMatrix.setValue(a - 1, b, matrix.getValue(a, b));
                        } else if (b > i) {
                            smallerMatrix.setValue(a - 1, b - 1, matrix.getValue(a, b));
                        }
                    }
                }
                sign = i % 2 == 0 ? 1 : -1;
                sum += sign * matrix.getValue(0, i) * (findDeterminant(smallerMatrix));
            }
            return sum;
        }
    }
}
