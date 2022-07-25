package com.example.moim.models

class DataResponse (
    val user : UserData,
    val token : String,
    val appointments : List<GroupData>,
) {
}