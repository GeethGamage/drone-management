package com.musala.dronemanagement.validator;

import com.musala.dronemanagement.annotation.ValueOfEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 4:12:21 PM
 */
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence>
{
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation)
    {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context)
    {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}
