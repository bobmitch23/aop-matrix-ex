package com.matrix.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@NoArgsConstructor
public class Matrix {
    private double[][] values;

    public Matrix(int rows, int columns) {
        values = new double[rows][columns];
    }

    public int getNumRows() {
        return values.length;
    }

    public int getNumColumns() {
        return values[0].length;
    }

    public double getValue(int row, int column) {
        return values[row][column];
    }

    public void setValue(int row, int column, double value) {
        values[row][column] = value;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < getNumRows(); i++) {
            String row = "";
            for (int j = 0; j < getNumColumns(); j++) {
                row += df.format(values[i][j]) + " ";
            }
            builder.append(row);
            builder.append("\n");
        }
        return builder.toString();
    }
}
