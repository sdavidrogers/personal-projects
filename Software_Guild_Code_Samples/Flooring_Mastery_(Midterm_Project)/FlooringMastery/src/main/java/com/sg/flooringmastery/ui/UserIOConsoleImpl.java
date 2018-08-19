package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    
    
    private final Scanner input;

    
    
    public UserIOConsoleImpl() {
        this.input = new Scanner(System.in);
    }

    
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    
    
    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }
    
    
    
    @Override
    public String readString(String prompt, ArrayList<String> values) {
        String returnVal;
        boolean innerBreak = false;
        do {
            returnVal = readString(prompt);
            for (String value : values) {
                if (returnVal.equals(value)) {
                    innerBreak = true;
                }
            }
            if (innerBreak) {
                break;
            }
            String valString = "";
            for (String value : values) {
                valString += value + " ";
            }
            System.out.println("Invalid entry. Enter one of the following values: ( " 
                    + valString + ")");
        } while (true);
        return returnVal;
    }
    
    
    
    @Override
    public String readStringDate(String prompt) {
        String returnVal;
        this.print(prompt);
        int year = readInt("Enter year: ", 1900, 3000);
        int month = readInt("Enter month (1-12): ", 1, 12);
        int day = readInt("Enter day (1-31): ", 1, 31);
        String yearString = String.valueOf(year);
        String monthString = String.valueOf(month);
        String dayString = String.valueOf(day);
        if (yearString.length() > 4) {
            yearString = yearString.substring(yearString.length() - 4);
        }
        if (monthString.length() == 1) {
            monthString = "0" + monthString;
        } else if (monthString.length() > 2) {
            monthString = monthString.substring(monthString.length() - 2);
        }
        if (dayString.length() == 1) {
            dayString = "0" + dayString;
        } else if (dayString.length() > 2) {
            dayString = dayString.substring(dayString.length() - 2);
        }
        returnVal = yearString + "-" + monthString + "-" + dayString;
        return returnVal;
    }

    
    
    @Override
    public double readDouble(String prompt) {
        double returnVal;
        do {
            try {
                System.out.print(prompt);
                returnVal = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Enter a decimal value number (0.00).");
            }
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public double readDouble(String prompt, double min, double max) {
        double returnVal;
        do {
            returnVal = readDouble(prompt);
            if ((returnVal >= min) && (returnVal <= max)) {
                break;
            }
            System.out.println("Invalid entry. Enter a number between " + min + " and " + max + ".");
        } while (true);
        return returnVal;
    }
    
    
    
    @Override 
    public double readDoubleNotNegative(String prompt) {
        double returnVal;
        do {
            returnVal = readDouble(prompt);
            if (returnVal > 1) {
                break;
            }
            System.out.println("Invalid entry. Enter a positive number.");
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public float readFloat(String prompt) {
        float returnVal;
        do {
            try {
                System.out.print(prompt);
                returnVal = Float.parseFloat(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Enter a floating point value number.");
            }
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public float readFloat(String prompt, float min, float max) {
        float returnVal;
        do {
            returnVal = readFloat(prompt);
            if ((returnVal >= min) && (returnVal <= max)) {
                break;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public int readInt(String prompt) {
        int returnVal;
        do {
            try {
                System.out.print(prompt);
                returnVal = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Enter a whole number.");
            }
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public int readInt(String prompt, int min, int max) {
        int returnVal;
        do {
            returnVal = readInt(prompt);
            if ((returnVal >= min) && (returnVal <= max)) {
                break;
            }
            System.out.println("Invalid entry. Enter a number between " + min + " and " + max + ".");
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public long readLong(String prompt) {
        long returnVal;
        do {
            try {
                System.out.print(prompt);
                returnVal = Long.parseLong(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a long value number.");
            }
        } while (true);
        return returnVal;
    }

    
    
    @Override
    public long readLong(String prompt, long min, long max) {
        long returnVal;
        do {
            returnVal = readLong(prompt);
            if ((returnVal >= min) && (returnVal <= max)) {
                break;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        } while (true);
        return returnVal;
    }
    
    
    
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal returnVal;
        do {
            try {
                System.out.print(prompt);
                returnVal = new BigDecimal(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        } while (true);
        return returnVal;
    }
    
    
    
    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal returnVal;
        do {
            returnVal = readBigDecimal(prompt);
            if ((returnVal.compareTo(min) >= 0) && (returnVal.compareTo(max) <= 0)) {
                break;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        } while (true);
        return returnVal;
    }

}
