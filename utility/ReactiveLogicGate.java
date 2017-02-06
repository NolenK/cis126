package utility;

import java.util.LinkedList;

public class ReactiveLogicGate implements ReactiveLogicGateInterface {
    // the reactive logic gate is the building block for reactive circuits

    // ================ CLASS PROPERTIES ================
    private int gateId;
    private String instanceName = "";
    private boolean input1State = false;
    private boolean input2State = false;
    private boolean outputState = false;
    private LinkedList<ReactiveLogicGate> dependantLogicGatesInput1 = new LinkedList<ReactiveLogicGate>();
    private LinkedList<ReactiveLogicGate> dependantLogicGatesInput2 = new LinkedList<ReactiveLogicGate>();

    // logic gate types
    public static final String [] gateNames = { "NOT", "AND", "OR", "XOR", "NAND", "NOR" };
    public static final int NOT  = 0;
    public static final int AND  = 1;
    public static final int OR   = 2;
    public static final int XOR  = 3;
    public static final int NAND = 4;
    public static final int NOR  = 5;
    // ================ END CLASS PROPERTIES ================


    // ================ CONSTRUCTORS ================
    public ReactiveLogicGate(int gateType) {
        // establishes what type of gate is being represented
        this.gateId = gateType;
        this.updateOutput();
    }

    public ReactiveLogicGate(int gateType, boolean initialInput1State, boolean initialInput2State) {
        // establishes what type of gate is being represented
        // also initializes inputs (useful to avoid creating infinite loops)
        this.gateId = gateType;
        this.setInput1(initialInput1State);
        this.setInput2(initialInput2State);
        this.updateOutput();
    }

    // ================ END CONSTRUCTORS ================


    public void setName(String instanceName) {
        // gives the current instance a name
        this.instanceName = instanceName;
    }

    public String getName() {
        // returns the name of the instance
        return (this.instanceName.equals("") ? "anonymous" : this.instanceName);
    }

    public String getGateName() {
        // returns the type of gate being represented
        return gateNames[this.gateId];
    }

    public String toString() {
        // returns a human readable string with information about the current state
        String output = "";

        output += String.format("ReactiveLogicGate<%s> %s: {\n", this.getGateName(), this.getName());
        output += "    currentState: {\n";
        output += String.format("        input1: %s,\n", Boolean.toString(this.input1State));
        output += String.format("        input2: %s,\n", Boolean.toString(this.input2State));
        output += String.format("        output: %s\n", Boolean.toString(this.outputState));
        output += "}";
        return output;
    }

    public boolean getInput1() {
        // returns the current state of input 1
        return this.input1State;
    }

    public boolean getInput2() {
        // returns the current state of input 2
        return this.input2State;
    }

    public boolean getOutput() {
        // returns the current state of output
        return this.outputState;
    }

    public void setInput1(boolean newState) {
        // changes the state of input 1
        // also updates the output and any reactive logic gates connected to the output

        // check if the state is changing before updating
        boolean shouldUpdate = (this.input1State != newState);

        // update state and progagate changes
        this.input1State = newState;
        if (shouldUpdate) this.updateOutput();
    }

    public void setInput2(boolean newState) {
        // changes the state of input 2
        // also updates the output and any reactive logic gates connected to the output

        // check if the state is changing before updating
        boolean shouldUpdate = (this.input2State != newState);

        // update state and progagate changes
        this.input2State = newState;
        if (shouldUpdate) this.updateOutput();
    }

    public void solderOutputToInput1Of(ReactiveLogicGate gateForInput) {
        // makes a connection between the output of the current instance
        // and input 1 of gateForInput

        // add connection to list of dependants
        this.dependantLogicGatesInput1.add(gateForInput);

        // update the connection so that the value are consistent
        // handles cases where the current output does not match the input of gateForInput
        gateForInput.setInput1(this.outputState);
    }

    public void solderOutputToInput2Of(ReactiveLogicGate gateForInput) {
        // makes a connection between the output of the current instance
        // and input 1 of gateForInput

        // add connection to list of dependants
        this.dependantLogicGatesInput2.add(gateForInput);

        // update the connection so that the value are consistent
        // handles cases where the current output does not match the input of gateForInput
        gateForInput.setInput2(this.outputState);
    }

    private void updateOutput() {
        // handler for updating changes to input state
        boolean newState = false;

        // perform logic based on gate type
        switch (this.gateId) {
            case NOT:
                newState = !(this.input1State);
                break;
            case AND:
                newState = (this.input1State && this.input2State);
                break;
            case OR:
                newState = (this.input1State || this.input2State);
                break;
            case XOR:
                newState = (this.input1State ^ this.input2State);
                break;
            case NAND:
                newState = !(this.input1State && this.input2State);
                break;
            case NOR:
                newState = !(this.input1State || this.input2State);
                break;
            default: break; // should not get here
        }

        // check if the state is changing before updating
        boolean shouldUpdate = (this.outputState != newState);

        // update state and progagate changes to all dependants
        this.outputState = newState;
        if (shouldUpdate) {
            // send updates to all dependant logic gates
            for (ReactiveLogicGate logicGate : this.dependantLogicGatesInput1) logicGate.setInput1(this.outputState);
            for (ReactiveLogicGate logicGate : this.dependantLogicGatesInput2) logicGate.setInput2(this.outputState);
        }
    } /* updateOutput */
}
