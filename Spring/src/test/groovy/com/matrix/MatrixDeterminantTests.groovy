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
        given: "a matrix that is NOT a square <NonSquareMatrix>"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d, 5d]
        ]
        matrix.setValues(values)

        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "an exception is thrown"
        def e = thrown(IllegalArgumentException)

        and: ""
        reportInfo("NonSquareMatrix: <br>" + convertNewLineToHTMLBreakForReport(matrix))
    }

    def "Test determinant of a 1x1 matrix"() {
        given: "a 1x1 matrix <1x1Matrix>"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d]
        ]
        matrix.setValues(values)

        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "the determinant is 1"
        result == 1d

        and: ""
        reportInfo("1x1Matrix: <br>" + convertNewLineToHTMLBreakForReport(matrix))
    }

    def "Test determinant of a 2x2 matrix"() {
        given: "a 2x2 matrix <2x2Matrix>"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d, 4d],
                [3d, 8d]
        ]
        matrix.setValues(values)


        when: "we try and find the determinant"
        double result = matrixFunctionsService.findDeterminant(matrix)

        then: "the determinant is -4"
        result == -4d

        and: ""
        reportInfo("2x2Matrix: <br>" + convertNewLineToHTMLBreakForReport(matrix))
    }

    def "Test determinant of a 6x6 matrix"() {
        given: "a 6x6 matrix <6x6Matrix>"
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

        then: "the determinant is 1366716"
        result == 1366716

        and: ""
        reportInfo("6x6Matrix: <br>" + convertNewLineToHTMLBreakForReport(matrix))
    }

    def convertNewLineToHTMLBreakForReport(Matrix matrix) {
        return matrix.toString().replaceAll("\n", "<br>")
    }

}
