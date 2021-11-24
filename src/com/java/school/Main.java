package com.java.school;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//  Serhiy Subbotin
//  serhiysubbotin@gmail.com

public class Main {
    public static void main(String[] args) {
        //task01
        /* Check that user enters positive integer number */
        int integerNumber = checkInputNumber("integer number <= 2147483647:\n");
        //Method takes an integer, and returns the sum of its digits
        int task01result = task01(integerNumber);
        display("01: ", task01result);

        //task02
        //Method that takes an empty array of 100 elements and fills it with the first 100 prime numbers.
        int []emptyArray = new int[100];
        int []filledArray = task02(emptyArray);
        display("02: {", filledArray);

        //task02_1
        int arraySize = checkInputNumber("number for the array's size:\n");
        int []filledArrayUsing_task02 = task02_1(arraySize);
        display("02_1: {", filledArrayUsing_task02);

        //task03
        int maxArraySize = 10, minValueArrayElement = 0, maxValueArrayElement = 100;
        int[] inputArrayForArithmetic = randomArray(maxArraySize,minValueArrayElement,maxValueArrayElement);
        display("03: {",inputArrayForArithmetic);
        float task03result = task03(inputArrayForArithmetic);
        System.out.println("Arithmetic mean: " + task03result + "\n");

        //task03_1
        int[] inputArrayForGeometric = randomArray(maxArraySize,minValueArrayElement,maxValueArrayElement);
        display("03_1: {", inputArrayForGeometric);
        double task03_1result = task03_1(inputArrayForGeometric);
        System.out.println("Geometric mean: " + task03_1result + "\n");

        //task03_2
        int[] inputArrayManually = createArray();
        display("03_2: {", inputArrayManually);
        //System.out.println(Arrays.toString(inputArrayManually));
        double task03_2result = task03_2(inputArrayManually);
        System.out.println("Geometric mean: " + task03_2result + "\n");

        //task04
        for(int i=1;i<=5;i++) {//Checking the fastest sorting method
            int[] inputArrayForSort = randomArray(maxArraySize, minValueArrayElement, maxValueArrayElement);
            System.out.println("\nCheck[" + i + "]\n");
            int[] task04_result = task04(inputArrayForSort);
            System.out.println(Arrays.toString(task04_result));
        }

        //task05
        String sentence = "Java School allows you to try java programming language on practice. " +
                "Java one of the most popular programming languages. I love Java!!!";
        String findStr = "proGraMminG";
        int taskresult05 = task05(findStr, sentence);
        display("05: ", sentence, findStr, taskresult05);

        //task05_1
        int [] taskresult05_1 = task05_1(findStr, sentence);
        if(taskresult05_1!=null)
            display("05_1: {",taskresult05_1);

        //task05_2
        task05_2();
        wordOccurrenceTest(sentence);

        //OptionalTask01
        int days = 29;
        int [] arrayWithRamdomTemperature = optionalTask1(days);
        System.out.println("\nResult of OptionalTask1:");
        display(arrayWithRamdomTemperature);

        //OptionalTask02
        System.out.println("\nResult of OptionalTask02:");
        String [] clothesByTheSeason = optionalTask2(arrayWithRamdomTemperature);
        display(arrayWithRamdomTemperature.length,arrayWithRamdomTemperature,clothesByTheSeason);

        //OptionalTask03
        System.out.println("\nResult of OptionalTask03:");
        int inputDays = checkInputNumber("number of days: ");
        int [] randomTemperature = optionalTask1(inputDays);
        String [] resultOptimalTask03 = optionalTask2(randomTemperature);
        display(inputDays,randomTemperature,resultOptimalTask03);

    }


    public static int task01 (int number){
        //Method takes an integer, and returns the sum of its digits
        int sumOfDigits = 0;
        for(int i=0;i<String.valueOf(number).length();i++) {
            sumOfDigits += Integer.parseInt(String.valueOf(String.valueOf(number).charAt(i)));
            System.out.print("[" + String.valueOf(number).charAt(i) + "]+");
        }
        System.out.println("\b = " + sumOfDigits);
        return sumOfDigits; //returns the sum from the digits
    }


    public static int[] task02(int [] array) {
        //Method that takes an empty array of 100 elements and fills it with the first 100 prime numbers.
        int indexOfArray = 0;
        int number = 1;
        while (true) {
            if (isPrime(number)) { //checking prime numbers
                array[indexOfArray++] = number;
            }
            if (indexOfArray == array.length)
                break;
            number++;
        }
        return array;
    }


    public static int [] task02_1(int arraySize){
        //method that takes an array of arbitrary size, creates it and reuses task2 to solve
        int []specifiedArraySize = new int[arraySize];
        return task02(specifiedArraySize);
    }


    public static float task03(int [] arbitraryArray) {
        //method that takes an array of integers of arbitrary size and returns the arithmetic mean of those numbers
        float sum = 0;
        for (int index = 0; index < arbitraryArray.length; index++) {
            float number = arbitraryArray[index];
            sum +=number;
            System.out.println("Array (" + index + ") = " + arbitraryArray[index]);
        }
        System.out.println((int)sum + " / " + arbitraryArray.length);
        return (sum / arbitraryArray.length);
    }


    public static double task03_1(int [] arbitraryArray) {
        double sum = 1;
        for (int index = 0; index < arbitraryArray.length; index++) {
            double number = arbitraryArray[index];
            sum *= number;
            System.out.println("Array (" + index + ") = " + arbitraryArray[index]);
        }
        return (Math.pow(sum, 1./arbitraryArray.length));
    }


    public static double task03_2(int [] arbitraryArray) {
        double sum = 1;
        for (int index = 0; index < arbitraryArray.length; index++) {
            double number = arbitraryArray[index];
            sum *= number;
            System.out.println("Array (" + index + ") = " + arbitraryArray[index]);
        }
        return (Math.pow(sum, 1./arbitraryArray.length));
    }


    public static int [] task04(int [] ArrayForSort) {
        int[] arrayForBubbleSort = ArrayForSort.clone();
        display("04:\nArray before sort: {", arrayForBubbleSort);
        long time = timer(() -> {//сheck the bubble sort runtime
            //sorting array elements using bubble sort
            int[] task04_result_Bubble_Sort = bubbleSort(ArrayForSort.clone());
            System.out.println("Array after bubble sort: " + Arrays.toString(task04_result_Bubble_Sort));
        }, TimeUnit.NANOSECONDS);
        System.out.println("NANOSECONDS:" + time);
        long time2 = timer(() -> {//сheck the bogo sort runtime
            int[] arrayForBogoSort = ArrayForSort.clone();
            //sorting array elements using bogo sort
            int[] task04_result_bogoSort = bogoSort(arrayForBogoSort.clone());
            System.out.println("Array after bogo sort: " + Arrays.toString(task04_result_bogoSort));
        }, TimeUnit.NANOSECONDS);
        System.out.println("NANOSECONDS:" + time2);
        long time3 = timer(() -> {//сheck the quick sort runtime
            int[] arrayForQuickSort = ArrayForSort.clone();
            //sorting array elements using quick sort
            quicksort(arrayForQuickSort, 0, arrayForQuickSort.length - 1);
            System.out.println("Array after quick sort: " + Arrays.toString(arrayForQuickSort));
        }, TimeUnit.NANOSECONDS);
        System.out.println("NANOSECONDS:" + time3);
        System.out.println("The fastest sorting method: " + getMin(time, time2, time3) + "sort");
        return bubbleSort(arrayForBubbleSort);
    }


    public static int task05(String findStr, String sentence) {
        if (sentence.isEmpty() || findStr.isEmpty()) {//input check
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = sentence.toLowerCase().indexOf(findStr.toLowerCase(), index)) != -1) {
            count++;
            index += findStr.length();
        }
        return count;
    }
    public static int [] task05_1(String findStr, String sentence){

        if (sentence.isEmpty() || findStr.isEmpty()) {//input check
            return null;
        }
        int count = 0;
        int index = 0;
        int [] posArray = new int[task05(findStr, sentence)];
        while ((index = sentence.toLowerCase().indexOf(findStr.toLowerCase(), index)) != -1) {
            index += findStr.length();
            posArray[count] = index-findStr.length();
            count++;
        }
        return posArray;
    }


    public  static void task05_2() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a substring:");
        String findStr = in.nextLine();
        System.out.println("Enter the main string:");
        String sentence = in.nextLine();

        display("05_2: ", sentence, findStr, task05(findStr,sentence));
        int [] taskresult05_2 = task05_1(findStr, sentence);
        if(taskresult05_2!=null)
            display("05_2: Array of starting indexes: " + findStr + " = {", taskresult05_2);
    }


    public static int [] optionalTask1(int days){
        int[] arrayWithRandomTemperature = new int[days];
        //arrayWithRandomTemperature = randomArray(arrayWithRandomTemperature);
        return randomArray(arrayWithRandomTemperature);
    }


    public enum temperatureClassification {
        FROST, COOL, WARM, HOT
    }

    public static String [] optionalTask2(int [] arrayWithRandomTemperature) {
        String [] arrayClothes = new String[arrayWithRandomTemperature.length];
        System.out.println("-100 < FROST < 0 < COOL < 20 < WARM < 35 < HOT < 100");
        for(int i = 0; i<arrayWithRandomTemperature.length;i++) {
            int temp = arrayWithRandomTemperature[i];
            temperatureClassification choice;
            //condition for "HOT"
            if(temp>35) choice = temperatureClassification.HOT;
                //condition for "WARM"
            else if(temp>20 && temp<35) choice = temperatureClassification.WARM;
                //condition for "COOL"
            else if(temp>0 && temp<20) choice = temperatureClassification.COOL;
                //condition for "FROST"
            else choice = temperatureClassification.FROST;
            switch (choice){
                case HOT:
                    arrayClothes[i] = "Sauna cap + Swim trunks";
                    break;
                case WARM:
                    arrayClothes[i] = "T-shirt + Shorts";
                    break;
                case COOL:
                    arrayClothes[i] = "Warmed jacket + Sneakers";
                    break;
                case FROST:
                    arrayClothes[i] = "Fur coat + Warm boots";
                    break;
            }
        }
        return  arrayClothes;
    }


    public static void wordOccurrenceTest(String sentence) {
        System.out.println(sentence);
        //String textOnly = sentence.replaceAll("[^\\da-zA-Zа ]", "");//leave the text only
        int wordCount = 0;
        String[] splits = sentence.split("\\s");
        for (String ignored : splits) {
            wordCount++;//count the number of words
        }
        for(int index=0;index<wordCount;index++){
            int substringCount = task05(splits[index], sentence);
            int [] taskresult = task05_1(splits[index], sentence);
            System.out.println(splits[index] + " = " + substringCount + Arrays.toString(taskresult));
        }
    }


    public static int checkInputNumber(String task){
        //method for correct input
        Scanner in = new Scanner(System.in);
        int checkPosNumber;
        do {
            System.out.print("Please enter a positive ".concat(task));
            while (!in.hasNextInt()){//check input
                System.out.println("That is not a number. Please enter a number: ");
                in.next();
            }
            checkPosNumber = in.nextInt();
        } while (checkPosNumber <= 0);
        return checkPosNumber;
    }


    public static int[] bubbleSort(int [] arrayBubbleSort) {
        //еру bubble sorting method of the array
        int temp;
        for (int i = 0; i < arrayBubbleSort.length; i++) {
            for (int j = 1; j < (arrayBubbleSort.length - i); j++) {
                if (arrayBubbleSort[j - 1] > arrayBubbleSort[j]) {
                    //swap elements
                    temp = arrayBubbleSort[j - 1];
                    arrayBubbleSort[j - 1] = arrayBubbleSort[j];
                    arrayBubbleSort[j] = temp;
                }
            }
        }
        return arrayBubbleSort;
    }


    public static  int[] bogoSort(int [] arrayBogoSort){
        //the bogo sorting method of the array
        while (!isSortedForBogo(arrayBogoSort)) {// if array is not sorted then shuffle
            // the array again
            for (int i = 0; i < arrayBogoSort.length; i++){
                // to generate permutation of the array
                int randomPosition = (int) (Math.random() * arrayBogoSort.length);
                int temp = arrayBogoSort[i];
                arrayBogoSort[i] = arrayBogoSort[randomPosition];
                arrayBogoSort[randomPosition] = temp;
            }
        }
        return arrayBogoSort;
    }

    public static boolean isSortedForBogo(int[] array)  {
        // To check if array is sorted or not
        for (int i = 1; i < array.length; i++){
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }


    // function to implement quick sort
    public static void quicksort(int [] array, int start, int end){
        // array[] - array to be sorted, start - starting index, end - ending index
        // recursion
        if (start < end)
        {
            int pivot = partitionForQuick(array, start, end);// pivot – pivot element around which array will be partitioned
            quicksort(array, start, pivot - 1);// call quicksort recursively to sort sub array before pivot
            quicksort(array, pivot + 1, end);// call quicksort recursively to sort sub array after pivot
        }
    }

    /* function that consider last element as pivot,
    place the pivot at its exact position, and place
    smaller elements to left of pivot and greater
    elements to right of pivot.  */
    public static int partitionForQuick (int [] array, int start, int end) {
        // // pivot (Element to be placed at right position)
        int pivot = array[end];
        // Main loop
        int i = start-1;//Index of smaller element
        for(int j = start;j<end;j++) {
            // Move any small numbers you find to the left side of the list
            if(array[j] <= pivot) {
                i++;// increment index of smaller element
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Move the pivot to the middle
        array[end] = array[i+1];
        array[i+1] = pivot;
        // Return the location of the pivot
        return i+1;
    }

    public static int[] randomArray(int arraySize, int low, int high){
        //array of random size from 1 to arraySize (value is set when calling the method)
        int []listOfNumbers = new int[(int) (Math.random() * arraySize)+1];
        for(int index=0; index<listOfNumbers.length;index++){
            //random array elements from low to high (value is set when calling the method)
            listOfNumbers[index] = (int) ((Math.random() * high) + low);
        }
        return listOfNumbers;
    }


    public static int[] createArray() {
        //method for correct input of an array
        Scanner in = new Scanner(System.in);
        boolean correctInput = false;
        int size = 0;
        while (!correctInput) { //until CorrectInput = True
            try {
                System.out.println("Enter the size of the array that will be created: ");
                size = in.nextInt();  // size - enter the size of the array
                if (size < 1) {
                    throw new NegativeArraySizeException();//exclusion of input less than 1
                }
                else
                    correctInput = true;
            }
            catch (InputMismatchException e){//exclusion entering the wrong format
                System.err.println("You have not entered the correct number format. Please try again.");
                in.next();//reentry
            }
            catch (NegativeArraySizeException e) {//exclusion of input negative value
                System.err.println("You have not entered a positive number. Please try again.");
            }
        }
        int[] myArray = new int[size];//size - given size of array
        for (int i = 0; i < size; i++) {
            boolean correctValue = false;
            while (!correctValue) {//until correctVatue = True
                try {
                    System.out.println("Enter the element of the array :");
                    myArray[i] = in.nextInt();//array entry
                    correctValue = true;
                } catch (InputMismatchException e) {//exclusion entering the wrong format
                    System.err.println("You have not entered the correct number format. Please try again.");
                    in.next();//in the case of incorrect input, re-entry
                }
            }
        }
        return myArray;//return an array with input elements
    }


    public static boolean isPrime(int number) {
        //method for checking prime numbers
        if (number <= 1) {
            return false;
        }
        for (int counter = 2; counter <= Math.sqrt(number); counter++) {
            if (number % counter == 0) {
                return false;
            }
        }
        return true;
    }


    public static int[] randomArray(int [] listOfTemperature){
        for(int index=0; index<listOfTemperature.length;index++){
            //random array elements from -100 to 100
            listOfTemperature[index] = (int) ((Math.random() * 200) - 100);
        }
        return listOfTemperature;

    }


    public static void display(String number, int value){
        //data output for task01
        System.out.println("Result of task " + number + value + "\n");
    }


    public static void display(String number, int [] array){
        //data output for task02, task02_1, task03, task03_1, task03_2, task05_1
        System.out.print("Result of task " + number);  // + Arrays.toString(array));
        for (int index = 0; index <= array.length-1; index++) {
            System.out.print(array[index] + ",");
        }
        System.out.println("\b}");
    }


    public static void display(String number, String value, String value2, int value3){
        //data output for task05
        System.out.println("\n" + value + "\n" + "Result of task " + number + value2 + " = " + value3 + "\n");
    }


    public static void display(int [] arrayTemperature){
        //data output for Optimal task01
        for(int index = 0; index<arrayTemperature.length;index++){
            System.out.println(index+1 + "day,  " + arrayTemperature[index] + " degrees ");
        }
    }


    public static void display(int days, int [] arrayTemperature, String [] outerAndOuterwear) {
        //data output for Optimal task02, Optimal task 03
        for (int index = 0; index < days; index++) {
            System.out.println(index + 1 + "day,  " + arrayTemperature[index] + " degrees ->  " + outerAndOuterwear[index]);
        }
    }


    public static long timer(Runnable method, TimeUnit timeUnit) {
        //Calculating sorting time
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        return TimeUnit.NANOSECONDS.convert(time, timeUnit);
    }
    public static String getMin(long bubble, long bogo, long quick){
        //method for finding the fastest sorting
        if(bubble<=bogo && bubble <=quick){
            return "bubble";
        }
        else if(bogo <= quick){
            return "bogo";
        }
        else return "quick";
    }
}