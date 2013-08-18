package com.sheetsj.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.sheetsj.util.BusinessValidationException;

/**
 * Hamcrest matcher for BusinessValidationException SourceFields
 *
 * NOTE: Use BusinessValidationExceptionMatchers class for easy static import matcher references
 */
public class BusinessValidationSourceFieldMatcher extends TypeSafeMatcher<BusinessValidationException>
{
    private final String sourceField;

    public BusinessValidationSourceFieldMatcher(String sourceField)
    {
        this.sourceField = sourceField;
    }

    public void describeTo(Description description)
    {
        description.appendText("exception has sourceField \"").appendText(sourceField).appendText("\"");
    }

    @Override
    protected boolean matchesSafely(BusinessValidationException item)
    {
        return sourceField.equals(item.getSourceField());
    }

    /**
     * A custom hasSourceField matcher
     * @param sourceField to match
     * @return hamcrest matcher
     */
    public static Matcher<BusinessValidationException> hasSourceField(String sourceField)
    {
        return new BusinessValidationSourceFieldMatcher(sourceField);
    }

}
