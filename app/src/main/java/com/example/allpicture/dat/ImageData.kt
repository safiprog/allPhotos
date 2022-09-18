package com.example.allpicture.dat

data class ImageData(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)