package com.soultotec.transactionservice.application.mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class DateMapper {

    public LocalDateTime mapToDateTime(Instant instant) {

        if (Objects.nonNull(instant)) {

            return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        }
        return null;
    }

    public Instant mapToInstant(LocalDateTime localDateTime) {

        if (Objects.nonNull(localDateTime)) {
            return localDateTime.toInstant(ZoneOffset.UTC);
        }

        return null;
    }
}
