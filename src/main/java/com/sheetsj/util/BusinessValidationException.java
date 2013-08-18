package com.sheetsj.util;


/**
 * This represents a business exception in the system
 *
 * @author: Copyright NNG 2011
 */
public class BusinessValidationException extends RuntimeException
{
    public static final String GLOBAL_SOURCE_FIELD = "business.validation.global";
    private static final long serialVersionUID = 1L;
    private String sourceField = GLOBAL_SOURCE_FIELD;
    private Object[] args = new Object[] {};

    /**
     * Default constructor
     */
    public BusinessValidationException()
    {
        super();
    }

    /**
     * Generate a business validation exception
     *
     * @param message  the validation message
     */
    public BusinessValidationException(String message)
    {
        super(message);
    }

    /**
     * Generate a business validation exception
     *
     * @param message       the validation message
     * @param messageArgs   array of dynamic exceptions
     */
    public BusinessValidationException(String message, Object... messageArgs)
    {
        super(message);
        args = messageArgs;
    }

    /**
     * Generate a business validation exception
     *
     * @param message  the validation message
     * @param cause    the base exception
     */
    public BusinessValidationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Generate a business validation exception
     *
     * @param cause    the base exception
     */
    public BusinessValidationException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Generate a business validation exception
     *
     * @param sourceField  the field generating the exception
     * @param message      the validation message
     */
    public BusinessValidationException(String sourceField, String message)
    {
        this(message);
        this.sourceField = sourceField;
    }

    /**
     * Generate a business validation exception
     *
     * @param sourceField  the field generating the exception
     * @param message      the validation message
     * @param messageArgs  the message arguments
     */
    public BusinessValidationException(String sourceField, String message, Object... messageArgs)
    {
        this(sourceField, message);
        args = messageArgs;
    }

    /**
     * Generate a business validation exception
     *
     * @param sourceField  the field generating the exception
     * @param message      the validation message
     * @param cause        the base exception
     */
    public BusinessValidationException(String sourceField, String message, Throwable cause)
    {
        this(message, cause);
        this.sourceField = sourceField;
    }

    public String getSourceField()
    {
        return sourceField;
    }

    public void setSourceField(String sourceField)
    {
        this.sourceField = sourceField;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public void setArgs(Object[] messageArgs)
    {
        this.args = messageArgs;
    }
}
