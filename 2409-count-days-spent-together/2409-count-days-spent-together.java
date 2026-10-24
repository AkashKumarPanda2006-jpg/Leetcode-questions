
class Solution {
    private static final int[] PREFIX = {
        0, 31, 59, 90, 120, 151,
        181, 212, 243, 273, 304, 334
    };

    private int dayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        return PREFIX[month - 1] + day;
    }

    public int countDaysTogether(
        String arriveAlice,
        String leaveAlice,
        String arriveBob,
        String leaveBob
    ) {
        String start = arriveAlice.compareTo(arriveBob) > 0
            ? arriveAlice : arriveBob;

        String end = leaveAlice.compareTo(leaveBob) < 0
            ? leaveAlice : leaveBob;

        int startDay = dayOfYear(start);
        int endDay = dayOfYear(end);

        return Math.max(0, endDay - startDay + 1);
    }
}
