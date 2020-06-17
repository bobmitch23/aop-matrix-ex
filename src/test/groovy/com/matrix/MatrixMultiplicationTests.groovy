package com.matrix

import com.matrix.domain.Matrix
import com.matrix.service.MatrixFunctionsService
import com.matrix.service.MatrixFunctionsServiceImpl
import spock.lang.Shared
import spock.lang.Specification

class MatrixMultiplicationTests extends Specification {
    @Shared
    private MatrixFunctionsService matrixFunctionsService

    def setupSpec() {
        matrixFunctionsService = new MatrixFunctionsServiceImpl();
    }

    def "Test exception is thrown when matrix dimensions do not match"() {
        given: "two matrices where num rows of first matrix DOES NOT equal num columns of second matrix"
        Matrix firstMatrix = new Matrix()
        double[][] firstValues = [
                [1d, 5d]
        ]
        firstMatrix.setValues(firstValues)

        Matrix secondMatrix = new Matrix()
        double[][] secondValues = [
                [1d],
                [2d],
                [3d]
        ]
        secondMatrix.setValues(secondValues)

        when: "we try and multiple the two matrices together"
        Matrix result = matrixFunctionsService.multiply(firstMatrix, secondMatrix)

        then: "an exception is thrown"
        def e = thrown(IllegalArgumentException)

    }

    def "Test matrix multiplication is working"() {
        given: "two matrices where num rows of first matrix DOES equal num columns of second matrix"
        Matrix firstMatrix = new Matrix()
        double[][] firstValues = [
                [1d, 5d],
                [2d, 3d],
                [1d, 7d]
        ]
        firstMatrix.setValues(firstValues)

        Matrix secondMatrix = new Matrix()
        double[][] secondValues = [
                [1d, 2d, 3d, 7d],
                [5d, 2d, 8d, 1d]
        ]
        secondMatrix.setValues(secondValues)

        Matrix expectedMatrix = new Matrix()
        double[][] expectedValues = [
                [26d, 12d, 43d, 12d],
                [17d, 10d, 30d, 17d],
                [36d, 16d, 59d, 14d]
        ]
        expectedMatrix.setValues(expectedValues)

        when: "we try and multiple the two matrices together"
        Matrix resultMatrix = matrixFunctionsService.multiply(firstMatrix, secondMatrix)

        then: "the result is equal to the expected matrix"
        resultMatrix == expectedMatrix

    }
}
