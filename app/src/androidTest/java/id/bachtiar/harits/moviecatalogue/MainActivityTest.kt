package id.bachtiar.harits.moviecatalogue

import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.android.material.chip.Chip
import id.bachtiar.harits.moviecatalogue.ui.MainActivity
import id.bachtiar.harits.moviecatalogue.ui.movie.MovieViewHolder
import id.bachtiar.harits.moviecatalogue.ui.tvshow.TvShowViewHolder
import id.bachtiar.harits.moviecatalogue.util.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
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
            .perform(scrollTo<TvShowViewHolder>(hasDescendant(withText("Peaky Blinders"))))
        onView(allOf(withText("Peaky Blinders"), isDescendantOfA(withId(R.id.item_tv_show))))
            .perform(click())
        onView(isRoot()).perform(waitFor(500L))
        checkDetailTvShow()
        onView(allOf(withText("Peaky Blinders"), isDescendantOfA(withId(R.id.view_pager))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTabMovieJourney() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
            .perform(scrollTo<MovieViewHolder>(hasDescendant(withText("Sing 2"))))
        onView(allOf(withText("Sing 2"), isDescendantOfA(withId(R.id.item_movie))))
            .perform(click())
        onView(isRoot()).perform(waitFor(500L))
        checkDetailMovie()
        onView(allOf(withText("Sing 2"), isDescendantOfA(withId(R.id.view_pager))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkAddAndRemoveFavoriteMovie() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(2, clickOnViewChild(R.id.btn_favorite)))
        onView(withText("Favorite Added"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
        onView(withId(R.id.normal))
            .perform(click())
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(1))
        onView(isRoot()).perform(waitFor(2000L))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, clickOnViewChild(R.id.btn_favorite)))
        onView(withText("Favorite Removed"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkAddAndRemoveFavoriteTvShow() {
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
        onView(isRoot()).perform(waitFor(500L))
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(2, clickOnViewChild(R.id.btn_favorite)))
        onView(withText("Favorite Added"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
        onView(withId(R.id.normal))
            .perform(click())
        onView(withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(1))
        onView(isRoot()).perform(waitFor(2000L))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, clickOnViewChild(R.id.btn_favorite)))
        onView(withText("Favorite Removed"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    private fun checkDetailMovie() {
        onView(withId(R.id.tv_progress))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_total_user_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_sub_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.container_detail)).perform(swipeUp())
        chipContainsText("Comedy")
        chipContainsText("Animation")
        chipContainsText("Family")
        chipContainsText("Music")
        onView(withId(R.id.tv_description))
            .check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }

    private fun checkDetailTvShow() {
        onView(withId(R.id.tv_progress))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_total_user_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_sub_desc))
            .check(matches(isDisplayed()))
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
            .check(matches(isDisplayed()))
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

    private fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById(viewId))
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
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

    class ToastMatcher : TypeSafeMatcher<Root>() {

        override fun describeTo(description: Description) {
            description.appendText("is toast")
        }

        override fun matchesSafely(root: Root): Boolean {
            val type = root.windowLayoutParams.get().type
            if (type == WindowManager.LayoutParams.TYPE_TOAST) {
                val windowToken = root.decorView.windowToken
                val appToken = root.decorView.applicationWindowToken
                if (windowToken === appToken) {
                    return true
                }
            }
            return false
        }
    }

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter!!.itemCount, `is`(expectedCount))
        }
    }
}