package utility;

public interface TruthTableInterface {
    // an interface for the truth table

    void initializeInputs(String[] inputNames);
    // initialize the input names for the table
    // inputNames should be an array of unique strings
    // calling this method is destructive and will rewrite the table

    void initializeOutputs(String[] outputNames);
    // initialize the output names for the table
    // outputNames should be an array of unique strings
    // calling this method is destructive and will rewrite the table

    int sizeInput();
    // returns the number of inputs

    int sizeOutput();
    // returns the number of outputs

    int size();
    // returns the number of rows in the truth table

    int findRowByInputValue(String[] inputNames, int inputValues);
    int findRowByInputValue(String[] inputNames, boolean[] inputValues);
    // returns the row index in the current table found by the given inputNames and inputValues
    // inputValues can be represented by the binary representation of an interger or a boolean array

    boolean getOutput(String outputName, boolean[] inputValues);
    boolean getOutput(String outputName, int inputValues);
    // gets the output value for the specified output name and row index
    // inputValues can be represented by the binary representation of an interger or a boolean array

    void setOutput(String outputName, boolean[] inputValues, boolean outputValue);
    void setOutput(String outputName, int inputValues, boolean outputValue);
    // sets the output value for the specified output name and row index
    // inputValues can be represented by the binary representation of an interger or a boolean array

    void setOutputRow(boolean[] inputValues, int outputValues);
    void setOutputRow(boolean[] inputValues, boolean[] outputValues);
    void setOutputRow(int inputValues, int outputValues);
    void setOutputRow(int inputValues, boolean[] outputValues);
    // sets all output values for the desired row
    // inputValues can be represented by the binary representation of an interger or a boolean array
    // outputValues can be represented by the binary representation of an interger or a boolean array

    void setOutputColumn(String outputName, int outputValues);
    void setOutputColumn(String outputName, boolean[] outputValues);
    // sets all output values for the desired column
    // outputValues can be represented by the binary representation of an interger or a boolean array
    // order of row depends on inputs

    boolean isEqual(TruthTable otherTable);
    // returns true if the table values are equivalent
    // it will check for matching input and output names
    // order of input and output names do not matter

    boolean isRowEqual(TruthTable otherTable, boolean[] inputValues);
    boolean isRowEqual(TruthTable otherTable, int inputValues);
    // returns true if the table values for the specified row are equivalent
    // inputValues is the row on the current table, this comparison will search for the appropriate row on the other table
    // inputValues can be represented by the binary representation of an interger or a boolean array

    String toString();
    // returns a string representation of the truth table

    TruthTable copy();
    // returns a copy of the TruthTable
}
