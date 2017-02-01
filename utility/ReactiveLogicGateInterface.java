package utility;

public interface ReactiveLogicGateInterface {
    // interface for the reactive logic gate

    void setName(String instanceName);
    // gives the current instance a name

    String getName();
    // returns the name of the instance

    String getGateName();
    // returns the type of gate being represented

    String toString();
    // returns a human readable string with information about the current state

    boolean getInput1();
    // returns the current state of input 1

    boolean getInput2();
    // returns the current state of input 2

    boolean getOutput();
    // returns the current state of output

    void setInput1(boolean newState);
    // changes the state of input 1
    // also updates the output and any reactive logic gates connected to the output

    void setInput2(boolean newState);
    // changes the state of input 2
    // also updates the output and any reactive logic gates connected to the output

    void solderOutputToInput1Of(ReactiveLogicGate gateForInput);
    // makes a connection between the output of the current instance
    // and input 1 of gateForInput

    void solderOutputToInput2Of(ReactiveLogicGate gateForInput);
    // makes a connection between the output of the current instance
    // and input 2 of gateForInput
}
