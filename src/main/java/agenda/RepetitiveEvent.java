package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    
    private ChronoUnit frequency;
    private List<LocalDate> listException = new ArrayList<>();
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        listException.add(date);
        }
    
     @Override
    public boolean isInDay(LocalDate date) {
        boolean isInDay = false;
        if (super.isInDay(date) == true) {
            return true;
        }
        for (LocalDate d : listException) {
            if (d.equals(date)) {
                return false;
            }
        }
        LocalDate start = myStart.toLocalDate();
        while (date.isAfter(start)||date.equals(start)) {
            if (super.isInDay(start) == true) {
                return true;
            }
            if (frequency == ChronoUnit.DAYS) {
                start = start.plusDays(1);
            }
            if (frequency == ChronoUnit.WEEKS) {
                start = start.plusWeeks(1);
            }
            if (frequency == ChronoUnit.MONTHS) {
                start = start.plusMonths(1);
            }

        }
        return isInDay;
    }


    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency; 
    }

}
