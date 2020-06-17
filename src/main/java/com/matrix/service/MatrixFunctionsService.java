package com.matrix.service;

import com.matrix.domain.Matrix;

public interface MatrixFunctionsService {
    Matrix multiply(Matrix firstMatrix, Matrix secondMatrix);

    double findDeterminant(Matrix matrix);
}
