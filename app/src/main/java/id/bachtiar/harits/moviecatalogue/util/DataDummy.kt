package id.bachtiar.harits.moviecatalogue.util

import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

object DataDummy {

    fun getMovies(): List<MoviesEntity> {
        return listOf(
            MoviesEntity(
                movieId = 634649,
                title = "Spider-Man: No Way Home",
                poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                releaseDate = "2021-12-15",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                isFavourite = false
            ),
            MoviesEntity(
                movieId = 524434,
                title = "Spider-Man: No Way Home",
                poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                releaseDate = "2021-11-03",
                overview = "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                isFavourite = false
            ),
            MoviesEntity(
                movieId = 585083,
                title = "Hotel Transylvania: Transformania",
                poster = "/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
                releaseDate = "2022-01-13",
                overview = "When Van Helsing's mysterious invention, the \\\"Monsterfication Ray,\\\" goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                isFavourite = false
            )
        )
    }

    fun getTvShows(): List<TvShowsEntity> {
        return listOf(
            TvShowsEntity(
                tvShowId = 85552,
                title = "Euphoria",
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                firstAirDate = "2019-06-16",
                poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                isFavourite = false
            ),
            TvShowsEntity(
                tvShowId = 110492,
                title = "Peacemaker",
                overview = "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.”",
                firstAirDate = "2022-01-13",
                poster = "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                isFavourite = false
            ),
            TvShowsEntity(
                tvShowId = 115036,
                title = "The Book of Boba Fett",
                overview = "Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by Jabba the Hutt and his crime syndicate.",
                firstAirDate = "2021-12-29",
                poster = "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
                isFavourite = false
            ),
        )
    }

    fun getMovie(): MovieEntity {
        return MovieEntity(
            movieId = 1,
            title = "Spider-Man: No Way Home",
            poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            releaseDate = "2021-12-1",
            rating = 8.4,
            totalUserRating = 6727,
            productionCompanies = "Marvel Studios,Pascal Pictures,Columbia Pictures",
            genres = "Action, Adventure, Science Fiction",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        )
    }

    fun getTvShow(): TvShowEntity {
        return TvShowEntity(
            tvShowId = 1,
            title = "Euphoria",
            poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            firstAirDate = "2019-06-16",
            rating = 8.4,
            totalUserRating = 6006,
            productionCompanies = "A24,The Reasonable Bunch,Little Lamb Productions",
            genres = "Drama,",
            seasons = "dummy_object_season",
            overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media."
        )
    }

    fun generateSelectedMovies(): MoviesEntity {
        return MoviesEntity(
            movieId = 634649,
            title = "Spider-Man: No Way Home",
            poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            releaseDate = "2021-12-15",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            isFavourite = false
        )
    }

    fun generateSelectedUpdatedMovies(): MoviesEntity {
        return MoviesEntity(
            movieId = 634649,
            title = "Spider-Man: No Way Home",
            poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            releaseDate = "2021-12-15",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            isFavourite = true
        )
    }

    fun generateSelectedMovie(): MovieEntity {
        return MovieEntity(
            movieId = 1,
            title = "Spider-Man: No Way Home",
            poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            releaseDate = "2021-12-1",
            rating = 8.4,
            totalUserRating = 6727,
            productionCompanies = "Marvel Studios,Pascal Pictures,Columbia Pictures",
            genres = "Action, Adventure, Science Fiction",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        )
    }

    fun generateSelectedTvShows(): TvShowsEntity {
        return TvShowsEntity(
            tvShowId = 85552,
            title = "Euphoria",
            overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            firstAirDate = "2019-06-16",
            poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            isFavourite = false
        )
    }

    fun generateSelectedUpdatedTvShows(): TvShowsEntity {
        return TvShowsEntity(
            tvShowId = 85552,
            title = "Euphoria",
            overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            firstAirDate = "2019-06-16",
            poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            isFavourite = true
        )
    }

    fun generateSelectedTvShow(): TvShowEntity {
        return TvShowEntity(
            tvShowId = 1,
            title = "Euphoria",
            poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            firstAirDate = "2019-06-16",
            rating = 8.4,
            totalUserRating = 6006,
            productionCompanies = "A24,The Reasonable Bunch,Little Lamb Productions",
            genres = "Drama,",
            seasons = "dummy_object_season",
            overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media."
        )
    }
}