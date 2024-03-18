package com.example.objectboxexample.mock

import com.example.objectboxexample.Image

class ImageMockDataSource {
    fun generateMockData(): List<Image> {
        val imageOwner = "my@email.tld"

        val imageOne = "city1"
        val locationOne = "country1"
        val timestampOne = 0L

        val imageTwo = "city2"
        val locationTwo = "country2"
        val timestampTwo = 1L

        val imageThree = "city3"
        val locationThree = "country3"
        val timestampThree = 2L

        val imageFour = "city4"
        val locationFour = "country4"
        val timestampFour = 3L

        val imageFive = "city5"
        val locationFive = "country5"
        val timestampFive = 4L

        val imageSix = "city6"
        val locationSix = "country6"
        val timestampSix = 5L

        val imageSeven = "city7"
        val locationSeven = "country7"
        val timestampSeven = 6L

        val imageEight = "city8"
        val locationEight = "country8"
        val timestampEight = 7L

        val imageNine = "city9"
        val locationNine = "country9"
        val timestampNine = 8L

        val imageTen = "city10"
        val locationTen = "country10"
        val timestampTen = 9L

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
        return imageList
    }
}