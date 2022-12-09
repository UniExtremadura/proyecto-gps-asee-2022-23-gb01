package es.unex.asee_proyectoprueba;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unex.asee_proyectoprueba.activities.LoginActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CU10_AppInfoTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void cU10_AppInfoTest() {
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.tvRegisterLogin), withText(R.string.register),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etUsernameRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText.perform(scrollTo(), replaceText("Usuario"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etEmailRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatEditText2.perform(scrollTo(), replaceText("usuario@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.etPasswordRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        appCompatEditText3.perform(scrollTo(), replaceText("Usuario1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.etRepeatPasswordRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                10)));
        appCompatEditText4.perform(scrollTo(), replaceText("Usuario1"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.bRegister), withText(R.string.register),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_profile), withContentDescription(R.string.title_profile),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText(R.string.title_profile)));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.ibAppInfo), withContentDescription(R.string.app_info),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.ibAppInfo), withContentDescription(R.string.app_info),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                10)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction viewGroup = onView(
                allOf(withId(androidx.preference.R.id.decor_content_parent),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView2.check(matches(withText(R.string.app_info)));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.ivAppIconProfile), withContentDescription(R.string.description_default_profile_picture),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.tvWhatIs),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView3.check(matches(withText(R.string.app_info_what_is)));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tvWhatIsResponse),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView4.check(matches(withText(R.string.app_info_what_is_response)));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.tvWhatCanDo),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView5.check(matches(withText(R.string.app_info_what_can_do)));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.tvWhatCanDoResponse),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView6.check(matches(withText(R.string.app_info_what_can_do_response)));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.tvWho),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView7.check(matches(withText(R.string.app_info_who)));

        viewGroup.perform(swipeUp());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.tvWhoResponse),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView8.check(matches(withText(R.string.app_info_who_response)));

        pressBack();

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.bDeleteAccount), withText(R.string.profile_delete_account),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.bDelete), withText(R.string.profile_delete_permanently),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton3.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
