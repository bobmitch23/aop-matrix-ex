package com.matrix


import com.fasterxml.jackson.databind.ObjectMapper
import com.matrix.controller.GlobalExceptionHandler
import com.matrix.controller.MatrixController
import com.matrix.domain.Matrix
import com.matrix.service.MatrixFunctionsServiceImpl
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class MatrixMultiplicationTests extends Specification {
    @Shared
    MockMvc mockMvc
    @Shared
    ObjectMapper mapper

    def setupSpec() {
        mapper = new ObjectMapper()
        mockMvc = MockMvcBuilders.standaloneSetup(new MatrixController(new MatrixFunctionsServiceImpl())).setControllerAdvice(new GlobalExceptionHandler()).build()
    }

    def baseMultiplyUri = "/multiply"

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
        def response = mockMvc.perform(post(baseMultiplyUri).contentType("application/json").content(mapper.writeValueAsString(Arrays.asList(firstMatrix, secondMatrix))))

        then: "a 400 is returned indicating the num cols of the first matrix must equal the num rows of the second matrix"
        assert response.andExpect(status().isBadRequest())
        assert response.andExpect(content().string("The number of columns in the first matrix must equal the number of rows in the second matrix"))

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
        def response = mockMvc.perform(post(baseMultiplyUri).contentType("application/json").content(mapper.writeValueAsString(Arrays.asList(firstMatrix, secondMatrix))))

        then: "a 200 is returned with expected matrix <ExpectedMatrix>"
        assert response.andExpect(status().isOk())
        assert expectedMatrix.equals(mapper.readValue(response.andReturn().getResponse().getContentAsString(), Matrix.class))

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
        def response = mockMvc.perform(post(baseMultiplyUri).contentType("application/json").content(mapper.writeValueAsString(Arrays.asList(firstMatrix, secondMatrix, thirdMatrix, fourthMatrix))))

        then: "a 200 is returned with expected matrix <ExpectedMatrix>"
        assert response.andExpect(status().isOk())
        assert expectedMatrix.equals(mapper.readValue(response.andReturn().getResponse().getContentAsString(), Matrix.class))

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
