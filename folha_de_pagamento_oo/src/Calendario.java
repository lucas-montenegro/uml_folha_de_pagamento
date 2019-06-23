public class Calendario {
    private int initialDay;
    private int day_of_week;
    private int day;
    private int month;
    private int year;
    private int [][] calendary = new int[12][3];

    public int getInitialDay() {
        return initialDay;
    }

    public void setInitialDay(int initialDay) {
        this.initialDay = initialDay;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int[][] getCalendary() {
        return calendary;
    }

    public void setCalendary(int[][] calendary) {
        this.calendary = calendary;
    }

    public int nextDay(int day, int month) {
        if(++day == 32 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) return 1;
        else if(day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) return 1;
        else if(day == 29 && month == 2) return 1;
        else return day;
    }
    public int nextMonth(int day, int month) {
        if(day == 1) month++;
        return month;
    }

    public int nextYear(int day, int month, int year) {
        if(day == 1 && month == 1) year++;
        return year;
    }

    public void initializeCalendary(int initialDay) {
        for (int i = 0; i < 12; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11) {
                calendary[i][0] = initialDay;
                calendary[i][2] = 31;

                if ((initialDay + 30) % 7 <= 5 && (initialDay + 30) % 7 > 0) {
                    calendary[i][1] = 31;
                } else if ((initialDay + 29) % 7 <= 5 && (initialDay + 29) % 7 > 0) {
                    calendary[i][1] = 30;
                } else if ((initialDay + 28) % 7 <= 5 && (initialDay + 28) % 7 > 0) {
                    calendary[i][1] = 29;
                }
                initialDay = (initialDay + 31) % 7;
            } else if (i == 1) {
                calendary[i][0] = initialDay;
                calendary[i][2] = 28;

                if ((initialDay + 27) % 7 <= 5 && (initialDay + 27) % 7 > 0) {
                    calendary[i][1] = 28;
                } else if ((initialDay + 26) % 7 <= 5 && (initialDay + 26) % 7 > 0) {
                    calendary[i][1] = 27;
                } else if ((initialDay + 25) % 7 <= 5 && (initialDay + 25) % 7 > 0) {
                    calendary[i][1] = 26;
                }
                initialDay = (initialDay + 28) % 7;
            } else if (i == 3 || i == 5 || i == 8 || i == 10) {
                calendary[i][0] = initialDay;
                calendary[i][2] = 30;

                if ((initialDay + 29) % 7 <= 5 && (initialDay + 29) % 7 > 0) {
                    calendary[i][1] = 30;
                } else if ((initialDay + 28) % 7 <= 5 && (initialDay + 28) % 7 > 0) {
                    calendary[i][1] = 29;
                } else if ((initialDay + 27) % 7 <= 5 && (initialDay + 27) % 7 > 0) {
                    calendary[i][1] = 28;
                }
                initialDay = (initialDay + 30) % 7;
            }
        }
    }
}
