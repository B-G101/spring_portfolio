package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        // check first if the number is divisible by 4 since leap yeas occur every 4 years. if it is not divisible by 4 then we know it isn't a leap year
        if (year%4 == 0) {
            // then check if the number is also divisible by 100 which would include numbers like 2000, 1900, etc some of which are and are not leap years
            if (year%100 == 0) {
                // to make sure the right centural years are counted we must only include the numbers that are divisible by 400, otherwise it is not a leap year
                if (year%400 == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;   
        }

        }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    public static int firstDayOfYear(int year) {
    // initializing variables used throughout the program
    int nonleap, totaldays, day;
    // initializing variable B which is 1900 because it is a year thats first day is a Monday
    int B = 1900;
 
    // counts the total number of years between the initial year and the final year aka year-1900
    // for example: 2000 and 1900 would have 99 years between them which would be set as the new variable
    year = (year - 1) - B;

    // counting the number of leap years that are between B=1900 and year and storing them as variable lyear
    // incriments the value by 1
    int lyear = numberOfLeapYears(B, (B + year));
 
    nonleap = year - lyear;
 
    // using the values of leap years and nonleap years to calculate the total number of days in between year and B = 1900
    // since we subtracted one year previously we must add one day back so that it ends on january 1st of the next year
    // ex: jan 1. 1900 + 99 years = dec 31 1999 meaning that we have to add 1 day to get to jan 1. 2000
    totaldays = (nonleap * 365) + (lyear * 366) + 1;
 
    // finding the actual day that it lands on
    day = (totaldays % 7);
 
    if (day == 0)
      return 1;
 
    else if (day == 1)
      return 2;
 
    else if (day == 2)
      return 3;
 
    else if (day == 3)
      return 4;
 
    else if (day == 4)
      return 5;
 
    else if (day == 5)
      return 6;
 
    else if (day == 6)
      return 0;
 
    // if the number is not typed corrected this error message will appear  
    else
      return 0;
        }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    public static int dayOfYear(int month, int day, int year) {
        // initializing the day of the year variable
        int dayOY = 0;
        // if the month is before august then odds are 31 days and evens are 30 days which are accounted for and added to the total count
        if (month < 8){
            for (int i = 1; i <= month-1; i++){
                if (i % 2 == 0){
                    dayOY = (dayOY + 30);
                }
                else {
                    dayOY = (dayOY + 31);
                }
            }

        }
        
        // if the month is after august or is august then odds are 30 days and evens are 31 days which are added to the total
        else {
            for (int i = 1; i < 8; i++){
                if (i % 2 == 0){
                    dayOY = (dayOY + 30);
                }
                else {
                    dayOY = (dayOY + 31);
                }
            }
                   
            for (int i = 0; i < month-8; i++){
                if (i % 2 == 0){
                    dayOY = (dayOY + 31);
                }
                else{
                    dayOY = (dayOY + 30);
                }
            }
        }
        // since feb is not accounted for in the code we must subtract 30 and add the correct number of days for the month of feb depending on whether or not it is a leap year
        if (month >= 2){
            if (isLeapYear(year)) {
                dayOY = dayOY - 30;
                dayOY = (dayOY + 29);
            }
            else {
                dayOY = dayOY - 30;
                dayOY = (dayOY + 28);
            }
        }

        dayOY = dayOY + day;
        return dayOY;
    }
        

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
         // to be implemented in part (a)

         int numLeapYears = 0;  //intializes the variable numLeapYears starting at 0
         for (int i = year1; i <= year2; i++) { //incriments the value by 1
            if (isLeapYear(i)) { 
                 numLeapYears ++;  //checking how many leap years happen between the two years 
             }
         }
         return numLeapYears; //ends the function
        }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        // to be implemented in part (b)
        int firstDay = firstDayOfYear(year);  //initializes the firstDay as the firstDayOfYear
        int theDate = dayOfYear(month, day, year); //finds the day of the year
        int theDay = (firstDay + theDate - 1) % 7; //calculates the day of the year from the first day
        return theDay; //ends the function
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(9, 27, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }

}