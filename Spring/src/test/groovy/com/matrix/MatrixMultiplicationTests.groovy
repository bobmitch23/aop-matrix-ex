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
        given: "two matrices where num rows of first matrix DOES NOT equal num columns of second matrix <Matrix1>, <Matrix2>"
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
        Matrix result = matrixFunctionsService.multiply(Arrays.asList(firstMatrix, secondMatrix))

        then: "an exception is thrown"
        def e = thrown(IllegalArgumentException)

        and: ""
        reportInfo("Matrix1:<br>" + convertNewLineToHTMLBreakForReport(firstMatrix))
        reportInfo("Matrix2:<br>" + convertNewLineToHTMLBreakForReport(secondMatrix))

    }

    def "Test matrix multiplication with two matrices"() {
        given: "two matrices where num rows of first matrix DOES equal num columns of second matrix <Matrix1>, <Matrix2>"
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
        Matrix result = matrixFunctionsService.multiply(Arrays.asList(firstMatrix, secondMatrix))

        then: "the result is equal to the expected matrix <ExpectedMatrix>"
        result == expectedMatrix

        and: ""
        reportInfo("Matrix1:<br>" + convertNewLineToHTMLBreakForReport(firstMatrix))
        reportInfo("Matrix2:<br>" + convertNewLineToHTMLBreakForReport(secondMatrix))
        reportInfo("ExpectedMatrix:<br>" + convertNewLineToHTMLBreakForReport(expectedMatrix))
    }

    def "Test matrix multiplication with four matrices"() {
        given: "<Matrix1>, <Matrix2>, <Matrix3>, <Matrix4>"
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

        Matrix thirdMatrix = new Matrix()
        double[][] thirdValues = [
                [4d, 1d],
                [7d, 7d],
                [1d, 2d],
                [8d, 5d]
        ]
        thirdMatrix.setValues(thirdValues)

        Matrix fourthMatrix = new Matrix()
        double[][] fourthValues = [
                [2d],
                [2d]
        ]
        fourthMatrix.setValues(fourthValues)

        Matrix expectedMatrix = new Matrix()
        double[][] expectedValues = [
                [1166d],
                [1072d],
                [1526d]
        ]
        expectedMatrix.setValues(expectedValues)

        when: "we try and multiple the four matrices together"
        Matrix result = matrixFunctionsService.multiply(Arrays.asList(firstMatrix, secondMatrix, thirdMatrix, fourthMatrix))

        then: "the result is equal to the expected matrix <ExpectedMatrix>"
        result == expectedMatrix

        and: ""
        reportInfo("Matrix1:<br>" + convertNewLineToHTMLBreakForReport(firstMatrix))
        reportInfo("Matrix2:<br>" + convertNewLineToHTMLBreakForReport(secondMatrix))
        reportInfo("Matrix3:<br>" + convertNewLineToHTMLBreakForReport(thirdMatrix))
        reportInfo("Matrix4:<br>" + convertNewLineToHTMLBreakForReport(fourthMatrix))
        reportInfo("ExpectedMatrix:<br>" + convertNewLineToHTMLBreakForReport(expectedMatrix))
    }

    def convertNewLineToHTMLBreakForReport(Matrix matrix) {
        return matrix.toString().replaceAll("\n", "<br>")
    }
}
