package com.example.campusdirecter.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Value Object that represents a time interval.
 *
 * @author Saboffzl
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@Value
public class Interval {

    LocalDateTime start;
    String duration; // Must be a string in order to be deserialized by Gson

    public Interval(LocalDateTime start, Duration duration) {
        this(start, duration.get(ChronoUnit.SECONDS) + "s");
    }

    public Interval(LocalDateTime start, long minutes) {
        this(start, minutes + "m");
    }

    protected Interval(LocalDateTime start, String duration) {
        this.start = start;
        this.duration = duration;
    }

    public Duration getDuration() {
        return SiParser.parse(duration);
    }

    public LocalDateTime getEnd() {
        Duration duration = SiParser.parse(this.duration);
        return start.plus(duration);
    }


    /**
     * @author Vincent Nadoll (s3003870@ba-sachsen.de)
     */
    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private enum SiParser {
        DAYS("d", ChronoUnit.DAYS),
        HOURS("h", ChronoUnit.HOURS),
        MINUTES("m", ChronoUnit.MINUTES),
        SECONDS("s", ChronoUnit.SECONDS),
        MILLIS("ms", ChronoUnit.MILLIS),
        ;

        /**
         * Matches any positive integer w/ at least one digit an optional whitespace and a mandatory
         * ASCII-alphabetical string w/ at least one character. So it matches:
         * <ul>
         *     <li>90m</li>
         *     <li>0 m</li>
         *     <li>1337 abc</li>
         * </ul>
         * but not
         * <ul>
         *     <li>1.5 h</li>
         *     <li>m</li>
         *     <li>&nbsp;</li>
         *     <li>ä</li>
         *     <li>12</li>
         * </ul>
         * Keep in mind that the actual conversion requires the unit, the tail part of the string,
         * to match one of the SI units defined above. This pattern won't restrict these.
         */
        private static final Pattern PATTERN = Pattern.compile("(\\d+)\\s?([a-zA-Z]+)");

        private final String siUnit;
        private final ChronoUnit unit;

        public static Duration parse(String duration) {
            Matcher matcher = PATTERN.matcher(duration);
            matcher.matches();
            String number = matcher.group(1);
            String unit = matcher.group(2);

            SiParser parser = from(unit);
            return Duration.of(Long.parseLong(number), parser.getUnit());
        }

        private static SiParser from(String siUnit) {
            return Arrays.stream(values())
                    .filter(bySiUnit(matches(siUnit)))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(""
                            + "Unable to find SI unit parser for arg="
                            + siUnit));
        }

        private static Predicate<SiParser> bySiUnit(Function<String, Boolean> predicate) {
            return siParser -> predicate.apply(siParser.getSiUnit());
        }

        private static Function<String, Boolean> matches(String obj) {
            return self -> Objects.equals(self, obj);
        }
    }
}
