package com.sheetsj.test.matchers;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.rules.ExpectedException;

import com.sheetsj.util.BusinessValidationException;


/**
 * Hamcrest matchers for BusinessValidationExceptionMatchers
 */
public class BusinessValidationExceptionMatchers
{

    /**
     * Convenience method to match message without arguments
     * on our custom BusinessValidationException.
     *
     * NOTE: expectMessage with equalTo() instead of the standard hamcrest contains()
     *
     * @param thrown ExpectedException to attach to
     * @param message that is expected
     */
    public static void expect(String message, ExpectedException thrown)
    {
        thrown.expect(BusinessValidationException.class);
        thrown.expectMessage(equalTo(message));
    }

    /**
     * Convenience method to match message, and arguments
     * on our custom BusinessValidationException.
     *
     * NOTE: expectMessage with equalTo() instead of the standard hamcrest contains()
     *
     * @param thrown ExpectedException to attach to
     * @param message that is expected
     * @param args that are expected
     */
    public static void expect(String message, ExpectedException thrown, Object... args)
    {
        expect(message, thrown);
        thrown.expect(containsArguments(args));
    }

    /**
     * Convenience method to match sourceField, and message with no arguments
     * on our custom BusinessValidationException.
     *
     * NOTE: expectMessage with equalTo() instead of the standard hamcrest contains()
     *
     * @param sourceField that is expected
     * @param message that is expected
     * @param thrown ExpectedException to attach to
     */
    public static void expect(String sourceField, String message, ExpectedException thrown)
    {
        expect(message, thrown);
        thrown.expect(hasSourceField(sourceField));
    }

    /**
     * Convenience method to match sourceField, message, and arguments
     * on our custom BusinessValidationException.
     *
     * NOTE: expectMessage with equalTo() instead of the standard hamcrest contains()
     *
     * @param sourceField that is expected
     * @param message that is expected
     * @param thrown ExpectedException to attach to
     * @param args that are expected
     */
    public static void expect(String sourceField, String message, ExpectedException thrown, Object... args)
    {
        expect(sourceField, message, thrown);
        thrown.expect(containsArguments(args));
    }

    /**
     * A custom hasSourceField matcher
     * @param sourceField to match
     * @return hamcrest matcher
     */
    public static Matcher<BusinessValidationException> hasSourceField(String sourceField)
    {
        return BusinessValidationSourceFieldMatcher.hasSourceField(sourceField);
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
        return BusinessValidationArgumentsMatcher.containsArguments(items);
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
        return BusinessValidationArgumentsMatcher.containsArguments(itemMatchers);
    }
}
