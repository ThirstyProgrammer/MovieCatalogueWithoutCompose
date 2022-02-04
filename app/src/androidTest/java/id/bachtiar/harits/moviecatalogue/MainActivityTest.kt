package id.bachtiar.harits.moviecatalogue

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.android.material.chip.Chip
import id.bachtiar.harits.moviecatalogue.ui.tvshow.TvShowViewHolder
import id.bachtiar.harits.moviecatalogue.util.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun checkTabLayoutAndViewPagerDisplayed() {
        onView(withId(R.id.tab_layout))
            .check(matches(isDisplayed()))
        onView(withId(R.id.view_pager))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkSwipePageWorking() {
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.view_pager))
            .perform(swipeRight())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTabTVShowJourney() {
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
            .perform(actionOnItemAtPosition<TvShowViewHolder>(13, click()))
        checkDetailTvShow()
        onView(allOf(withText("Peaky Blinders"), isDescendantOfA(withId(R.id.view_pager))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTabMovieJourney() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
            .perform(actionOnItemAtPosition<TvShowViewHolder>(4, click()))
        checkDetailMovie()
        onView(allOf(withText("Sing 2"), isDescendantOfA(withId(R.id.view_pager))))
            .check(matches(isDisplayed()))
    }

    private fun checkDetailMovie() {
        onView(withId(R.id.tv_progress))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_total_user_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText("December 01, 2021")))
        onView(withId(R.id.tv_sub_desc))
            .check(matches(withText("Production Company :\nIllumination Entertainment, Universal Pictures")))
        onView(withId(R.id.container_detail)).perform(swipeUp())
        chipContainsText("Comedy")
        chipContainsText("Animation")
        chipContainsText("Family")
        chipContainsText("Music")
        onView(withId(R.id.tv_description))
            .check(matches(withText("Buster and his new cast now have their sights set on debuting a new show at the Crystal Tower Theater in glamorous Redshore City. But with no connections, he and his singers must sneak into the Crystal Entertainment offices, run by the ruthless wolf mogul Jimmy Crystal, where the gang pitches the ridiculous idea of casting the lion rock legend Clay Calloway in their show. Buster must embark on a quest to find the now-isolated Clay and persuade him to return to the stage.")))
        onView(isRoot()).perform(pressBack())
    }

    private fun checkDetailTvShow() {
        onView(withId(R.id.tv_progress))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_total_user_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText("September 12, 2013")))
        onView(withId(R.id.tv_sub_desc))
            .check(matches(withText("Production Company :\nTiger Aspect Productions, BBC Studios, Caryn Mandabach Productions, Screen Yorkshire")))
        onView(withId(R.id.rv_seasons))
            .check(matches(isDisplayed()))
        onView(hasItemAtPosition(0, hasDescendant(withText("Specials"))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_seasons)).perform(ScrollToBottomAction())
        onView(hasItemAtPosition(6, hasDescendant(withText("Series 6"))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.container_detail)).perform(swipeUp())
        chipContainsText("Drama")
        chipContainsText("Crime")
        onView(withId(R.id.tv_description))
            .check(matches(withText("A gangster family epic set in 1919 Birmingham, England and centered on a gang who sew razor blades in the peaks of their caps, and their fierce boss Tommy Shelby, who means to move up in the world.")))
        onView(isRoot()).perform(pressBack())
    }

    private fun chipContainsText(text: String) {
        onView(allOf(withText(containsString(text)), isAssignableFrom(Chip::class.java))).check(matches(isDisplayed()))
    }

    private fun hasItemAtPosition(position: Int, matcher: Matcher<View>) : Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("has item at position $position : ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                val viewHolder = item?.findViewHolderForAdapterPosition(position)
                return matcher.matches(viewHolder?.itemView)
            }
        }
    }

    class ScrollToBottomAction : ViewAction {
        override fun getDescription(): String {
            return "scroll RecyclerView to bottom"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView
            val itemCount = recyclerView.adapter?.itemCount
            val position = itemCount?.minus(1) ?: 0
            recyclerView.scrollToPosition(position)
            uiController?.loopMainThreadUntilIdle()
        }
    }
}