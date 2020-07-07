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

class MatrixDeterminantTests extends Specification {

    @Shared
    MockMvc mockMvc
    @Shared
    ObjectMapper mapper

    def setupSpec() {
        mapper = new ObjectMapper()
        mockMvc = MockMvcBuilders.standaloneSetup(new MatrixController(new MatrixFunctionsServiceImpl())).setControllerAdvice(new GlobalExceptionHandler()).build()
    }

    def baseDeterminantUri = "/findDeterminant"

    def "Test exception when matrix is not a square"() {
        given: "a matrix that is NOT a square <NonSquareMatrix>"
        Matrix matrix = new Matrix()
        double[][] values = [
                [1d, 5d]
        ]

        matrix.setValues(values)

        when: "we try and find the determinant"
        def response = mockMvc.perform(post(baseDeterminantUri).contentType("application/json").content(mapper.writeValueAsString(matrix)))

        then: "a 400 is returned stating that you cannot have a non-square matrix"
        assert response.andExpect(status().isBadRequest())
        assert response.andExpect(content().string("Cannot find the determinant of a non square matrix"))

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
        def response = mockMvc.perform(post(baseDeterminantUri).contentType("application/json").content(mapper.writeValueAsString(matrix)))

        then: "a 200 is returned along with a determinant of 1"
        assert response.andExpect(status().isOk())
        assert response.andExpect(content().string("1.00"))

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
        def response = mockMvc.perform(post(baseDeterminantUri).contentType("application/json").content(mapper.writeValueAsString(matrix)))

        then: "a 200 is returned along with a determinant of -4"
        assert response.andExpect(status().isOk())
        assert response.andExpect(content().string("-4.00"))

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
        def response = mockMvc.perform(post(baseDeterminantUri).contentType("application/json").content(mapper.writeValueAsString(matrix)))

        then: "a 200 is returned along with a determinant of 1366716"
        assert response.andExpect(status().isOk())
        assert response.andExpect(content().string("1366716.00"))

        and: ""
        reportInfo("6x6Matrix: <br>" + convertNewLineToHTMLBreakForReport(matrix))
    }

    def convertNewLineToHTMLBreakForReport(Matrix matrix) {
        return matrix.toString().replaceAll("\n", "<br>")
    }

}
