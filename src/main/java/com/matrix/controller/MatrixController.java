package com.matrix.controller;

import com.matrix.domain.Matrix;
import com.matrix.service.MatrixFunctionsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;

@RestController
public class MatrixController {
    private final MatrixFunctionsService matrixFunctionsService;

    public MatrixController(MatrixFunctionsService matrixFunctionsService) {
        this.matrixFunctionsService = matrixFunctionsService;
    }

    @PostMapping(value = "/multiply", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> multiply(@RequestBody List<Matrix> matrices) {
        Matrix result = matrixFunctionsService.multiply(matrices);
        return ResponseEntity.ok(result.toString());
    }

    @PostMapping(value = "/findDeterminant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findDeterminant(@RequestBody Matrix matrix) {
        double result = matrixFunctionsService.findDeterminant(matrix);
        DecimalFormat df = new DecimalFormat("0.00");
        return ResponseEntity.ok(df.format(result));
    }
}
