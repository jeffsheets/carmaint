package com.sheetsj.test.matchers;

import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.collection.IsArrayContainingInOrder;

import com.sheetsj.util.BusinessValidationException;

/**
 * Hamcrest matcher for BusinessValidationException arguments
 *
 * delegates actual matching to the standard hamcrest contains() matcher
 *
 * NOTE: Use BusinessValidationExceptionMatchers class for easy static import matcher references
 */
public class BusinessValidationArgumentsMatcher extends TypeSafeDiagnosingMatcher<BusinessValidationException>
{
    private final List<Matcher<? super Object>> matchers;

    public BusinessValidationArgumentsMatcher(List<Matcher<? super Object>> matchers)
    {
        this.matchers = matchers;
    }

    public void describeTo(Description description)
    {
        Description desc = description.appendText("exception has arguments containing ");
        desc.appendList("[", ", ", "]", matchers);
    }

    /**
     * Delegates matching to the standard hamcrest contains() matcher
     */
    @Override
    protected boolean matchesSafely(BusinessValidationException exception, Description mismatchDescription)
    {
        IsArrayContainingInOrder<Object> delegateMatcher = new IsArrayContainingInOrder<Object>(matchers);
        return delegateMatcher.matches(exception.getArgs());
    }

    /**
     * varargs matcher for each element in exception arguments list
     * <p/>
     * For example:
     * <pre>thrown.expect(containsArguments("foo", barInt))</pre>
     *
     * @param items to match for each item in arguments list
     * @return BusinessValidationException Matcher
     */
    public static Matcher<BusinessValidationException> containsArguments(Object... items)
    {
        List<Matcher<? super Object>> matchers = new ArrayList<Matcher<? super Object>>();
        for (Object item : items)
        {
            matchers.add(equalTo(item));
        }

        return containsArguments(matchers);
    }

    /**
     * Generic matcher for each argument in the exception arguments list.
     * <p/>
     * For example:
     * <pre>thrown.expect(containsArguments(Arrays.asList(equalTo("foo"), equalTo("bar"))))</pre>
     * @param itemMatchers for each item in arguments list
     * @return BusinessValidationException Matcher
     */
    public static Matcher<BusinessValidationException> containsArguments(List<Matcher<? super Object>> itemMatchers)
    {
        return new BusinessValidationArgumentsMatcher(itemMatchers);
    }

}
