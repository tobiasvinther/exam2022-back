package sem3.exam2022.services;

import java.time.Duration;

public class UtilityService {

    public static String StringifyRideTime(Duration duration) {
        long HH = duration.toHours();
        long MM = duration.toMinutesPart();
        long SS = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }

}
