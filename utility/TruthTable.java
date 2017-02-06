package utility;

public class TruthTable implements TruthTableInterface {
    // a class to represent a turth table
    private static final int TO_STRING_NAME_LENGTH = 9;
    private static final String TRUE_STRING        = "1";
    private static final String FALSE_STRING       = "0";

    // STATIC METHODS

    private static String cropName(String name) {
        if (name.length() > TO_STRING_NAME_LENGTH) return name.substring(0, TO_STRING_NAME_LENGTH);

        // centering shorter names
        // when uneven spacing, adds an extra to the front
        boolean addToFront = true;
        while (name.length() < TO_STRING_NAME_LENGTH) {
            if (addToFront) name = " " + name;
            else name = name + " ";
            addToFront = !addToFront;
        }
        return name;
    }

    private static String stringify(boolean value) {
        return cropName((value ? TRUE_STRING : FALSE_STRING));
    }

    private static String[] copyOf(String[] array) {
        // returns a deep copy of the array
        String[] newArray = new String[array.length];
        for (int i = 0; i < array.length; i++) newArray[i] = array[i];
        return newArray;
    }

    private static int indexOf(String[] array, String stringToFind) {
        // returns the index of the stringToFind in the array
        // returns -1 if a string is not found
        // return the first matching string, only checks for string values, not object reference
        for (int i = 0; i < array.length; i++) {
            if (stringToFind.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static int convertToInteger(boolean[] array) {
        // returns a integer based upon the array representing a binary number
        int binary = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                binary = binary | (1 << (array.length - 1 - i));
            }
        }
        return binary;
    }

    public static boolean[] convertToBooleanArray(int n, int arraySize) {
        // returns a boolean array based on the binary representation of the integer
        // only works if n is positive
        boolean[] binary = new boolean[arraySize];
        for (int i = 0; i < arraySize; i++) {
            binary[(arraySize - 1) - i] = (n & (1 << i)) > 0; // binary and w/ mask, then check if > 0
        }
        return binary;
    }

    private String[] inputNames = new String[0];
    private String[] outputNames = new String[0];
    private boolean[][] outputValues = new boolean[0][0];

    public TruthTable() {}

    public void initializeInputs(String[] inputNames) {
        // initialize the input names for the table
        // inputNames should be an array of unique strings
        // calling this method is destructive and will rewrite the table
        this.inputNames   = copyOf(inputNames);
        this.outputValues = new boolean[(1 << this.sizeInput())][this.sizeOutput()];
    }

    public void initializeOutputs(String[] outputNames) {
        // initialize the output names for the table
        // outputNames should be an array of unique strings
        // calling this method is destructive and will rewrite the table
        this.outputNames  = copyOf(outputNames);
        this.outputValues = new boolean[(1 << this.sizeInput())][this.sizeOutput()];
    }

    public int sizeInput() {
        // returns the number of inputs
        return this.inputNames.length;
    }

    public int sizeOutput() {
        // returns the number of outputs
        return this.outputNames.length;
    }

    public int size() {
        // returns the number of rows in the truth table
        return 1 << this.sizeInput(); // 2 to the power of input size
    }

    public int findRowByInputValue(String[] inputNames, int inputValues) {
        return this.findRowByInputValue(inputNames, convertToBooleanArray(inputValues, inputNames.length));
    }

    public int findRowByInputValue(String[] inputNames, boolean[] inputValues) {
        // returns the row index in the current table found by the given inputNames and inputValues
        // inputValues can be represented by the binary representation of an interger or a boolean array
        boolean[] rearrangedValues = new boolean[inputValues.length];
        for (int i = 0; i < inputNames.length; i++) {
            int inputIndex = indexOf(this.inputNames, inputNames[i]);
            rearrangedValues[inputIndex] = inputValues[i];
        }
        return convertToInteger(rearrangedValues);
    }

    public boolean getOutput(String outputName, boolean[] inputValues) {
        return this.getOutput(outputName, convertToInteger(inputValues));
    }

    public boolean getOutput(String outputName, int inputValues) {
        // gets the output value for the specified output name and row index
        // inputValues can be represented by the binary representation of an interger or a boolean array
        return this.outputValues[inputValues][indexOf(this.outputNames, outputName)];
    }

    public void setOutput(String outputName, boolean[] inputValues, boolean outputValue) {
        this.setOutput(outputName, convertToInteger(inputValues), outputValue);
    }

    public void setOutput(String outputName, int inputValues, boolean outputValue) {
        // sets the output value for the specified output name and row index
        // inputValues can be represented by the binary representation of an interger or a boolean array
        this.outputValues[inputValues][indexOf(this.outputNames, outputName)] = outputValue;
    }

    public void setOutputRow(boolean[] inputValues, int outputValues) {
        this.setOutputRow(convertToInteger(inputValues), convertToBooleanArray(outputValues, this.sizeOutput()));
    }

    public void setOutputRow(boolean[] inputValues, boolean[] outputValues) {
        this.setOutputRow(convertToInteger(inputValues), outputValues);
    }

    public void setOutputRow(int inputValues, int outputValues) {
        this.setOutputRow(inputValues, convertToBooleanArray(outputValues, this.sizeOutput()));
    }

    public void setOutputRow(int inputValues, boolean[] outputValues) {
        // sets all output values for the desired row
        // inputValues can be represented by the binary representation of an interger or a boolean array
        // outputValues can be represented by the binary representation of an interger or a boolean array
        for (int i = 0; i < this.sizeOutput(); i++) this.outputValues[inputValues][i] = outputValues[i];
    }

    public void setOutputColumn(String outputName, int outputValues) {
        this.setOutputColumn(outputName, convertToBooleanArray(outputValues, this.size()));
    }

    public void setOutputColumn(String outputName, boolean[] outputValues) {
        // sets all output values for the desired column
        // outputValues can be represented by the binary representation of an interger or a boolean array
        // order of row depends on inputs
        for (int i = 0; i < this.size(); i++) this.setOutput(outputName, i, outputValues[i]);
    }

    public boolean isEqual(TruthTable otherTable) {
        // returns true if the table values are equivalent
        // it will check for matching input and output names
        // order of input and output names do not matter
        for (int i = 0; i < this.size(); i++) {
            if (!this.isRowEqual(otherTable, i)) return false;
        }
        return true;
    }

    public boolean isRowEqual(TruthTable otherTable, boolean[] inputValues) {
        return this.isRowEqual(otherTable, convertToInteger(inputValues));
    }

    public boolean isRowEqual(TruthTable otherTable, int inputValues) {
        // returns true if the table values for the specified row are equivalent
        // inputValues is the row on the current table, this comparison will search for the appropriate row on the other table
        // inputValues can be represented by the binary representation of an interger or a boolean array
        int otherTableRow = otherTable.findRowByInputValue(this.inputNames, inputValues);

        for (int i = 0; i < this.sizeOutput(); i++) {
            if (this.outputValues[inputValues][i] != otherTable.getOutput(this.outputNames[i], otherTableRow)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        // returns a string representation of the truth table

        // initialize
        String outputString = "";

        // build separator
        String separator = "";

        for (int i = 0; i < this.sizeInput(); i++) separator += "|-" + cropName("-----------") + "-";
        separator += "||";
        for (int i = 0; i < this.sizeOutput(); i++) separator += "|-" + cropName("-----------") + "-";
        separator += "|";

        // assemble header
        outputString += separator + "\n";
        for (String inputName : this.inputNames) outputString += "| " + cropName(inputName) + " ";
        outputString += "||";
        for (String outputName : this.outputNames) outputString += "| " + cropName(outputName) + " ";
        outputString += "|\n";
        outputString += separator + "\n";

        // assemble each row
        for (int i = 0; i < this.size(); i++) {
            for (boolean inputValue : convertToBooleanArray(i, this.sizeInput())) {
                outputString += "| " + stringify(inputValue) + " ";
            }
            outputString += "||";
            for (boolean outputValue : this.outputValues[i]) {
                outputString += "| " + stringify(outputValue) + " ";
            }
            outputString += "|\n";
        }

        outputString += separator;
        return outputString;
    } /* toString */

    public TruthTable copy() {
        // returns a copy of the TruthTable
        TruthTable tableCopy = new TruthTable();

        tableCopy.initializeInputs(this.inputNames);
        tableCopy.initializeOutputs(this.outputNames);
        for (int i = 0; i < this.size(); i++) tableCopy.setOutputRow(i, this.outputValues[i]);
        return tableCopy;
    }
}
