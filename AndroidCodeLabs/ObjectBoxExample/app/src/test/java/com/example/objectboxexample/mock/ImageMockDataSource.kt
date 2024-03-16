package com.example.objectboxexample.mock

import com.example.objectboxexample.Image

object ImageMockDataSource {
    const val imageOwner = "my@email.tld"

    const val imageOne = "city1"
    const val locationOne = "country1"
    const val timestampOne = 0L

    const val imageTwo = "city2"
    const val locationTwo = "country2"
    const val timestampTwo = 1L

    const val imageThree = "city3"
    const val locationThree = "country3"
    const val timestampThree = 2L

    const val imageFour = "city4"
    const val locationFour = "country4"
    const val timestampFour = 3L

    const val imageFive = "city5"
    const val locationFive = "country5"
    const val timestampFive = 4L

    const val imageSix = "city6"
    const val locationSix = "country6"
    const val timestampSix = 5L

    const val imageSeven = "city7"
    const val locationSeven = "country7"
    const val timestampSeven = 6L

    const val imageEight = "city8"
    const val locationEight = "country8"
    const val timestampEight = 7L

    const val imageNine = "city9"
    const val locationNine = "country9"
    const val timestampNine = 8L

    const val imageTen = "city10"
    const val locationTen = "country10"
    const val timestampTen = 9L

    val imageList = listOf(
        Image(
            location = "${imageOne},${locationOne}",
            timestamp = timestampOne,
            owner = imageOwner
        ),
        Image(
            location = "${imageTwo},${locationTwo}",
            timestamp = timestampTwo,
            owner = imageOwner
        ),
        Image(
            location = "${imageThree},${locationThree}",
            timestamp = timestampThree,
            owner = imageOwner
        ),
        Image(
            location = "${imageFour},${locationFour}",
            timestamp = timestampFour,
            owner = imageOwner
        ),
        Image(
            location = "${imageFive},${locationFive}",
            timestamp = timestampFive,
            owner = imageOwner
        ),
        Image(
            location = "${imageSix},${locationSix}",
            timestamp = timestampSix,
            owner = imageOwner
        ),
        Image(
            location = "${imageSeven},${locationSeven}",
            timestamp = timestampSeven,
            owner = imageOwner
        ),
        Image(
            location = "${imageEight},${locationEight}",
            timestamp = timestampEight,
            owner = imageOwner
        ),
        Image(
            location = "${imageNine},${locationNine}",
            timestamp = timestampNine,
            owner = imageOwner
        ),
        Image(
            location = "${imageTen},${locationTen}",
            timestamp = timestampTen,
            owner = imageOwner
        )
    )
}