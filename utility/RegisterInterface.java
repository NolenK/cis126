package utility;

public interface RegisterInterface {
    // interface for interacting with a register

    void setDatalines(String binaryData);
    void setDatalines(int binaryData);
    void setDatalines(boolean[] binaryData);
    // sets the full register with the binary data
    // the index in the boolean array represents the bit position
    // the least significant bit starts at position 0
    // and increases as the significance increases
    // binaryData[0] is the least significant bit
    // binaryData[binaryData.length - 1] is the most significant bit

    String getDatalinesString();
    int getDatalinesInteger();
    boolean[] getDatalines();
    // sets the full register with the binary data
    // the index in the boolean array represents the bit position
    // the least significant bit starts at position 0
    // and increases as the significance increases
    // getDatalines()[0] is the least significant bit
    // getDatalines()[getDatalines().length - 1] is the most significant bit

    void clockIt();
    // sends a HIGH pulse across the clock dataline
    // the clock cannot be accurately represented in a serial program
    // this method mocks the clock signal in a processor

    int size();
    // returns the number of bits that the register can store

    void clearBit(int bitPosition);
    // clears the bit at the specified position
    // clearing the bit causes the bit to become false
    //
    // the least significant bit starts at position 0
    // and increases as the significance increases

    void setBit(int bitPosition);
    // sets the bit at the specified position
    // setting the bit causes the bit to become true
    //
    // the least significant bit starts at position 0
    // and increases as the significance increases

    boolean getBit(int bitPosition);
    // returns the value of the bit at the specified position
    //
    // the least significant bit starts at position 0
    // and increases as the significance increases
}
