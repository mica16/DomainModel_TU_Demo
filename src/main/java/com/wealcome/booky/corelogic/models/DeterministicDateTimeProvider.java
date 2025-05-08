package com.wealcome.booky.corelogic.models;

import java.time.LocalDateTime;

public class DeterministicDateTimeProvider implements DateTimeProvider
{
    public LocalDateTime currentDateTime;

    @Override
    public LocalDateTime now()
    {
        return currentDateTime;
    }
}
