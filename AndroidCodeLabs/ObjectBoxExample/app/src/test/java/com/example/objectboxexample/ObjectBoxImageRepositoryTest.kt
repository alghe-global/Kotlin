package com.example.objectboxexample

import app.cash.turbine.test
import com.example.objectboxexample.mock.ImageMockDataSource
import io.objectbox.BoxStore
import io.objectbox.config.DebugFlags
import io.objectbox.kotlin.boxFor
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

open class ObjectBoxImageRepositoryTest {

    private lateinit var repository: ImageRepository

    private var _store: BoxStore? = null
    protected val boxStore: BoxStore
        get() = _store!!

    @Before
    fun setUp() {
        /** Delete any files in the test directory
         * before each test to start with a clean database
         */
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        _store = MyObjectBox.builder()
            /** Use a custom directory to store the database files in */
            .directory(TEST_DIRECTORY)
            /** Enable debug flags for more detailed ObjectBox log output */
            .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
            .build()

        repository = ObjectBoxImageRepository(boxStore)
    }

    @After
    fun tearDown() {
        /**
         * Close the database, reset pointer and
         * delete all files in the test directory
         */
        _store?.close()
        _store = null
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
    }

    @Test
    fun testGetLatest10ImagesInCity_emptyBox_returnsEmptyList() = runTest {
        val city = "city1"
        val country = "country1"
        repository.getLatest10ImagesInCity(city, country).test {
            val emission = awaitItem()
            awaitComplete()
            assertEquals(emptyList<Image>(), emission)
        }
    }

    @Test
    fun testGetLatest10ImagesInCountry_emptyBox_returnsEmptyList() = runTest {
        val country = "country10"
        repository.getLatest10ImagesInCountry(country).test {
            val emission = awaitItem()
            awaitComplete()
            assertEquals(emptyList<Image>(), emission)
        }
    }

    @Test
    fun testGetLatest10ImagesByOwner_emptyBox_returnsEmptyList() = runTest {
        val eventOwner = "my@email.tld"
        repository.getLatest10ImagesByOwner(eventOwner).test {
            val emission = awaitItem()
            awaitComplete()
            assertEquals(emptyList<Image>(), emission)
        }
    }

    @Test
    fun testGetLatest10ImagesInCity_populatedBox_emitsExpectedImagesForCity() = runTest {
        val city = "city1"
        val country = "country1"
        val images = ImageMockDataSource().generateMockData()

        /** Insert test Images into the database */
        boxStore.boxFor<Image>().put(images)

        repository.getLatest10ImagesInCity(city, country).test {
            val emission = awaitItem()
            awaitComplete()
            assertEquals(listOf(images[0]), emission)
        }
    }

    @Test
    fun testGetLatest10ImagesInCountry_populatedBox_emitsExpectedImagesForCountry() = runTest {
        val country = "country10"
        val images = ImageMockDataSource().generateMockData()

        /** Insert test Images into the database */
        boxStore.boxFor<Image>().put(images)

        repository.getLatest10ImagesInCountry(country).test {
            val emission = awaitItem()
            awaitComplete()
            assertEquals(listOf(images[9]), emission)
        }
    }

    @Test
    fun testGetLatest10ImagesByOwner_populatedBox_emitsExpectedImagesByOwner() = runTest {
        val owner = "my@email.tld"
        val images = ImageMockDataSource().generateMockData()

        /** Insert test Images into the database */
        boxStore.boxFor<Image>().put(images)

        repository.getLatest10ImagesByOwner(owner).test {
            val emission = awaitItem()
            awaitComplete()
            assertEquals(images.reversed(), emission)
        }
    }

    companion object {
        private val TEST_DIRECTORY = File("mytests/test-db")
    }
}