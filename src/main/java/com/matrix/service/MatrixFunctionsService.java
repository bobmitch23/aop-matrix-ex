package com.matrix.service;

import com.matrix.domain.Matrix;

import java.util.List;

public interface MatrixFunctionsService {
    Matrix multiply(List<Matrix> matrices);

    double findDeterminant(Matrix matrix);
}
