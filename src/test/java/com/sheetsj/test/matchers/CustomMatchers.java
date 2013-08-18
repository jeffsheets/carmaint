package com.sheetsj.test.matchers;

import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Matcher;
import org.mockito.internal.matchers.Matches;

/**
 * Custom matchers to help with property validations
 */
public class CustomMatchers
{
    public static final String UNSET_REGEX = "^\\$\\{.*\\}$";

    /**
     * A custom matchesRegex matcher that uses the Mockito Matches class
     * @param regex to match
     * @return hamcrest matcher
     */
    public static Matcher<Object> matchesRegex(String regex)
    {
        return new Matches(regex);
    }

    public static Matcher<Object> isStringInjected()
    {
        return not(matchesRegex(UNSET_REGEX));
    }

}
