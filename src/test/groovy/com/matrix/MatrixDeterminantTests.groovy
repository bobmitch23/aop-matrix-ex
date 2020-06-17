package com.matrix

import com.matrix.domain.Matrix
import com.matrix.service.MatrixFunctionsService
import com.matrix.service.MatrixFunctionsServiceImpl
import spock.lang.Shared
import spock.lang.Specification

class MatrixDeterminantTests extends Specification {
    @Shared
    private MatrixFunctionsService matrixFunctionsService

    def setupSpec() {
        matrixFunctionsService = new MatrixFunctionsServiceImpl();
    }

    def "Test exception when matrix is not a square"() {
        given: "a matrix that is NOT a square"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d, 5d]
        ]
        matrix.setValues(values)


        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "an exception is thrown"
        def e = thrown(IllegalArgumentException)
    }

    def "Test determinant of a 1x1 matrix"() {
        given: "a 1x1 matrix"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d]
        ]
        matrix.setValues(values)

        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "the answer is as expected"
        result == 1d
    }

    def "Test determinant of a 2x2 matrix"() {
        given: "a 2x2 matrix"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d, 4d],
                [3d, 8d]
        ]
        matrix.setValues(values)


        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "the answer is as expected"
        result == -4d
    }

    def "Test determinant of a 6x6 matrix"() {
        given: "a 6x6 matrix"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d, 4d, 9d, 3d, 14d, 6d],
                [7d, 8d, 9d, 3d, 9d, 6d],
                [-3d, -4d, -9d, 3d, 18d, 3d],
                [-9d, 4d, 1d, -1d, 7d, 6d],
                [11d, 6d, 6d, 6d, -6d, -6d],
                [-4d, -4d, 7d, 8d, 9d, 10d]
        ]
        matrix.setValues(values)


        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "the answer is as expected"
        result == 1366716
    }

}
