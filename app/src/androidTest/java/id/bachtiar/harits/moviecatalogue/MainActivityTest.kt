package id.bachtiar.harits.moviecatalogue

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
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
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.StringContains.containsString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
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
            .perform(actionOnItemAtPosition<TvShowViewHolder>(9, click()))
        checkDetailSupernatural()
        onView(allOf(withText("Supernatural"), isDescendantOfA(withId(R.id.view_pager))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTabMovieJourney() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
            .perform(actionOnItemAtPosition<TvShowViewHolder>(4, click()))
        checkDetailWreckItRalph()
        onView(allOf(withText("Wreck-It Ralph"), isDescendantOfA(withId(R.id.view_pager))))
            .check(matches(isDisplayed()))
    }

    private fun checkDetailWreckItRalph() {
        onView(withId(R.id.tv_progress))
            .check(matches(withText("73")))
        onView(withId(R.id.tv_total_user_rating))
            .check(matches(withText("10.324 Ratings")))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText("January 1, 2012")))
        onView(withId(R.id.tv_sub_desc))
            .check(matches(withText("Director :\nRick Moore\n\nScreenplay :\nPhil Johnston, Jennifer Lee\n\nStory :\nRick Moore, Jim Reardon")))
        onView(withId(R.id.rv_cast))
            .check(matches(isDisplayed()))
        onView(hasItemAtPosition(0, hasDescendant(withText("John C. Reilly"))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(ScrollToBottomAction())
        onView(hasItemAtPosition(8, hasDescendant(withText("Jess Harnell"))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.container_detail)).perform(swipeUp())
        chipContainsText("Family")
        chipContainsText("Animation")
        chipContainsText("Comedy")
        chipContainsText("Adventure")
        onView(withId(R.id.tv_description))
            .check(matches(withText("Wreck-It Ralph is the 9-foot-tall, 643-pound villain of an arcade video game named Fix-It Felix Jr., in which the game's titular hero fixes buildings that Ralph destroys. Wanting to prove he can be a good guy and not just a villain, Ralph escapes his game and lands in Hero's Duty, a first-person shooter where he helps the game's hero battle against alien invaders. He later enters Sugar Rush, a kart racing game set on tracks made of candies, cookies and other sweets. There, Ralph meets Vanellope von Schweetz who has learned that her game is faced with a dire threat that could affect the entire arcade, and one that Ralph may have inadvertently started.")))
        onView(isRoot()).perform(pressBack())
    }

    private fun checkDetailSupernatural() {
        onView(withId(R.id.tv_progress))
            .check(matches(withText("82")))
        onView(withId(R.id.tv_total_user_rating))
            .check(matches(withText("5.645 Ratings")))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText("September 13, 2005")))
        onView(withId(R.id.tv_sub_desc))
            .check(matches(withText("Creator :\nEric Kripke")))
        onView(withId(R.id.rv_cast))
            .check(matches(isDisplayed()))
        onView(hasItemAtPosition(0, hasDescendant(withText("Jared Padalecki"))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(ScrollToBottomAction())
        onView(hasItemAtPosition(8, hasDescendant(withText("Samantha Smith"))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.container_detail)).perform(swipeUp())
        chipContainsText("Drama")
        chipContainsText("Mystery")
        chipContainsText("Science Fiction")
        chipContainsText("Fantasy")
        onView(withId(R.id.tv_description))
            .check(matches(withText("When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.")))
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