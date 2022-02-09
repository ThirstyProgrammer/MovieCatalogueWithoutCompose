package id.bachtiar.harits.moviecatalogue.util

import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

object DataDummy {

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