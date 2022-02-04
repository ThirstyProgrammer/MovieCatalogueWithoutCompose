package id.bachtiar.harits.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import id.bachtiar.harits.moviecatalogue.model.*
import id.bachtiar.harits.moviecatalogue.utils.getOrAwaitValueTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote: RemoteDataSource = mock()
    private val fakeRepository = FakeMovieCatalogueRepository(remote)
    private lateinit var dummyResponseMovies: DataResult<Movies.Response>
    private lateinit var dummyResponseMovie: DataResult<Movie>
    private lateinit var dummyResponseTvShows: DataResult<TvShows.Response>
    private lateinit var dummyResponseTvShow: DataResult<TvShow>

    @Test
    fun getPopularMovies() {
        dummyResponseMovies = DataResult(
            ViewState.SUCCESS, Movies.Response(
                page = 1,
                totalPages = 32141,
                results = listOf(
                    Movies.Data(
                        id = 634649,
                        title = "Spider-Man: No Way Home",
                        poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                        releaseDate = "2021-12-15",
                        overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                    ),
                    Movies.Data(
                        id = 524434,
                        title = "Spider-Man: No Way Home",
                        poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                        releaseDate = "2021-11-03",
                        overview = "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                    ),
                    Movies.Data(
                        id = 585083,
                        title = "Hotel Transylvania: Transformania",
                        poster = "/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
                        releaseDate = "2022-01-13",
                        overview = "When Van Helsing's mysterious invention, the \\\"Monsterfication Ray,\\\" goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                    ),
                )
            ), ""
        )
        val dummyMovies = MutableLiveData<DataResult<Movies.Response>>()
        dummyMovies.value = dummyResponseMovies
        `when`(remote.getPopularMovies(1)).thenReturn(dummyMovies)
        val response = fakeRepository.getPopularMovies(1).getOrAwaitValueTest()
        verify(remote).getPopularMovies(1)
        assertNotNull(response)
        assertEquals(response.status, dummyResponseMovies.status)
        assertEquals(response.message, dummyResponseMovies.message)
        assertEquals(response.data, dummyResponseMovies.data)
    }

    @Test
    fun getPopularTvShows() {
        dummyResponseTvShows = DataResult(
            ViewState.SUCCESS,
            TvShows.Response(
                page = 1,
                totalPages = 6192,
                results = listOf(
                    TvShows.Data(
                        id = 85552,
                        title = "Euphoria",
                        overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                        firstAirDate = "2019-06-16",
                        poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg"
                    ),
                    TvShows.Data(
                        id = 110492,
                        title = "Peacemaker",
                        overview = "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.”",
                        firstAirDate = "2022-01-13",
                        poster = "/hE3LRZAY84fG19a18pzpkZERjTE.jpg"
                    ),
                    TvShows.Data(
                        id = 115036,
                        title = "The Book of Boba Fett",
                        overview = "Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by Jabba the Hutt and his crime syndicate.",
                        firstAirDate = "2021-12-29",
                        poster = "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg"
                    ),
                )
            ),
            ""
        )
        val dummyTvShows = MutableLiveData<DataResult<TvShows.Response>>()
        dummyTvShows.value = dummyResponseTvShows
        `when`(remote.getPopularTvShows(1)).thenReturn(dummyTvShows)
        val response = fakeRepository.getPopularTvShows(1).getOrAwaitValueTest()
        verify(remote).getPopularTvShows(1)
        assertNotNull(response)
        assertEquals(response.status, dummyResponseTvShows.status)
        assertEquals(response.message, dummyResponseTvShows.message)
        assertEquals(response.data, dummyResponseTvShows.data)
    }

    @Test
    fun getMovie() {
        dummyResponseMovie = DataResult(
            ViewState.SUCCESS,
            Movie(
                title = "Spider-Man: No Way Home",
                poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                releaseDate = "2021-12-1",
                rating = 8.4,
                totalUserRating = 6727,
                productionCompanies = listOf(
                    ProductionCompanies(
                        id = 420,
                        name = "Marvel Studios"
                    ),
                    ProductionCompanies(
                        id = 84041,
                        name = "Pascal Pictures"
                    ),
                    ProductionCompanies(
                        id = 5,
                        name = "Columbia Pictures"
                    )
                ),
                genres = listOf(
                    Genres(
                        id = 28,
                        name = "Action"
                    ),
                    Genres(
                        id = 12,
                        name = "Adventure"
                    ),
                    Genres(
                        id = 878,
                        name = "Science Fiction"
                    ),
                ),
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            ),
            ""
        )
        val dummyMovie = MutableLiveData<DataResult<Movie>>()
        dummyMovie.value = dummyResponseMovie
        `when`(remote.getMovie(1)).thenReturn(dummyMovie)
        val response = fakeRepository.getMovie(1).getOrAwaitValueTest()
        verify(remote).getMovie(1)
        assertNotNull(response)
        assertEquals(response.status, dummyResponseMovie.status)
        assertEquals(response.message, dummyResponseMovie.message)
        assertEquals(response.data, dummyResponseMovie.data)
    }

    @Test
    fun getTvShow() {
        dummyResponseTvShow = DataResult(
            ViewState.SUCCESS,
            TvShow(
                title = "Euphoria",
                poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                firstAirDate = "2019-06-16",
                rating = 8.4,
                totalUserRating = 6006,
                productionCompanies = listOf(
                    ProductionCompanies(
                        id = 41077,
                        name = "A24"
                    ),
                    ProductionCompanies(
                        id = 139809,
                        name = "The Reasonable Bunch"
                    ),
                    ProductionCompanies(
                        id = 126587,
                        name = "Little Lamb Productions"
                    ),
                    ProductionCompanies(
                        id = 124433,
                        name = "DreamCrew"
                    ),
                ),
                genres = listOf(
                    Genres(
                        id = 18,
                        name = "Drama"
                    )
                ),
                seasons = listOf(
                    Seasons(
                        name = "Specials",
                        poster = "/6x31o4AjfVxakRJ0OQpFH8f9Kzb.jpg",
                        airDate = "2020-12-06",
                        episodeCount = 2,
                        seasonNumber = 0
                    ),
                    Seasons(
                        name = "Season 1",
                        poster = "/5mi3aRl16yKmfpQJMzvqN5TXkdA.jpg",
                        airDate = "2019-06-16",
                        episodeCount = 8,
                        seasonNumber = 1
                    ),
                    Seasons(
                        name = "Season 2",
                        poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                        airDate = "2022-01-09",
                        episodeCount = 8,
                        seasonNumber = 2
                    ),
                ),
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media."
            ),
            ""
        )
        val dummyTvShow = MutableLiveData<DataResult<TvShow>>()
        dummyTvShow.value = dummyResponseTvShow
        `when`(remote.getTvShow(1)).thenReturn(dummyTvShow)
        val response = fakeRepository.getTvShow(1).getOrAwaitValueTest()
        verify(remote).getTvShow(1)
        assertNotNull(response)
        assertEquals(response.status, dummyResponseTvShow.status)
        assertEquals(response.message, dummyResponseTvShow.message)
        assertEquals(response.data, dummyResponseTvShow.data)
    }
}