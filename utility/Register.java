package utility;

public class Register implements RegisterInterface {
    // implementation of a software version of a processor register

    // ================ CLASS PROPERTIES ================
    private static final int DEFAULT_NUMBER_OF_BITS = 8;
    private DFlipFlop[] memory;
    // ================ END CLASS PROPERTIES ================


    // ================ CONSTRUCTORS ================
    public Register() {
        // providing a constructor to initialize the register with a default
        // ensures that the constructor is user friendly
        this.initializeRegister(DEFAULT_NUMBER_OF_BITS);
    };

    public Register(int numberOfBits) {
        // initializes the register with the specified number of bits
        this.initializeRegister(numberOfBits);
    };

    private void initializeRegister(int numberOfBits) {
        // initializes a register with the appropriate number of bits
        // method provided to avoid duplication of code in constructors
        this.memory = new DFlipFlop[numberOfBits];
    }

    // ================ END CONSTRUCTORS ================

    public void setDatalines(String binaryData) {}

    public void setDatalines(int binaryData) {}

    public void setDatalines(boolean[] binaryData) {}

    public int getDatalines() {
        return 0;
    }

    public void clockIt() {
        // sends a HIGH pulse across the clock dataline
        // the clock cannot be accurately represented in a serial program
        // this method mocks the clock signal in a processor

        // setting all clock inputs to true (HIGH)
        for (DFlipFlop bit : this.memory) bit.setClock(true);

        // setting all clock inputs to false (LOW)
        for (DFlipFlop bit : this.memory) bit.setClock(false);
    }

    public int size() {
        // returns the number of bits that the register can store
        return this.memory.length;
    }

    public void clearBit(int bitPosition) {
        // clears the bit at the specified position
        // clearing the bit causes the bit to become false
        //
        // the least significant bit starts at position 0
        // and increases as the significance increases

        // setting input D of bit to false (LOW)
        this.memory[bitPosition].setD(false);

        // sending a clock pulse through the register
        // more efficient to only send a pulse to the indiviual bit; however,
        // sending a clock pulse to everything is more representative of the hardware
        this.clockIt();
    }

    public void setBit(int bitPosition) {
        // sets the bit at the specified position
        // setting the bit causes the bit to become true
        //
        // the least significant bit starts at position 0
        // and increases as the significance increases

        // setting input D of bit to true (HIGH)
        this.memory[bitPosition].setD(true);

        // sending a clock pulse through the register
        // more efficient to only send a pulse to the indiviual bit; however,
        // sending a clock pulse to everything is more representative of the hardware
        this.clockIt();
    }

    public boolean getBit(int bitPosition) {
        // returns the value of the bit at the specified position
        //
        // the least significant bit starts at position 0
        // and increases as the significance increases

        // output of d-flipflop is Q
        return this.memory[bitPosition].getQ();
    }
}
