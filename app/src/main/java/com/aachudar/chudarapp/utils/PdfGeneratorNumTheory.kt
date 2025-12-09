package com.aachudar.chudarapp.utils

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import com.aachudar.chudarapp.data.Problem
import java.io.File
import java.io.FileOutputStream

fun generateSolutionsPdfNumberTheory(context: Context, problems: List<Problem>, solutions: Map<String, String>): File {
    val pdf = PdfDocument()
    val textPaint = TextPaint().apply { textSize = 14f }
    val titlePaint = TextPaint().apply { textSize = 16f; isFakeBoldText = true }

    var pageNumber = 1
    var pageInfo = PdfDocument.PageInfo.Builder(595, 842, pageNumber).create()
    var page = pdf.startPage(pageInfo)
    var canvas = page.canvas
    var y = 50f

    problems.forEachIndexed { i, problem ->
        val solutionText = solutions[problem.questionText] ?: "Solution not available."

        // ✅ Draw wrapped question
        val questionText = "Q${i + 1}: ${problem.questionText}"
        val questionLayout = StaticLayout.Builder
            .obtain(questionText, 0, questionText.length, titlePaint, 500) // wrap at 500px
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(0f, 1f)
            .setIncludePad(false)
            .build()

        canvas.save()
        canvas.translate(30f, y) // left margin
        questionLayout.draw(canvas)
        canvas.restore()
        y += questionLayout.height + 10f

        // ✅ Draw correct answer (also wrapped, just in case)
        val answerText = "Correct Answer: ${problem.correctAnswer}"
        val answerLayout = StaticLayout.Builder
            .obtain(answerText, 0, answerText.length, textPaint, 500)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(0f, 1f)
            .setIncludePad(false)
            .build()

        canvas.save()
        canvas.translate(50f, y)
        answerLayout.draw(canvas)
        canvas.restore()
        y += answerLayout.height + 10f

        // ✅ Draw wrapped explanation
        val explanation = "Explanation: $solutionText"
        val explanationLayout = StaticLayout.Builder
            .obtain(explanation, 0, explanation.length, textPaint, 500)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(0f, 1f)
            .setIncludePad(false)
            .build()

        canvas.save()
        canvas.translate(50f, y)
        explanationLayout.draw(canvas)
        canvas.restore()
        y += explanationLayout.height + 40f

        // New page if near bottom
        if (y > 750f) {
            pdf.finishPage(page)
            pageNumber++
            pageInfo = PdfDocument.PageInfo.Builder(595, 842, pageNumber).create()
            page = pdf.startPage(pageInfo)
            canvas = page.canvas
            y = 50f
        }
    }

    pdf.finishPage(page)

    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        "NumberTheorySolutions.pdf"
    )
    pdf.writeTo(FileOutputStream(file))
    pdf.close()

    return file
}
