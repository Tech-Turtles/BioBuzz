package org.firstinspires.ftc.teamcode.utility.commands;

import org.firstinspires.ftc.teamcode.control.ControllerInput;

public class IntakeCommand {

    // value
    private double value;
    // bools
    private boolean valid;
    private boolean positive;

    public IntakeCommand(double value) {
        // get validity
        valid = ControllerInput.buffer(value);
        // get positivity
        if (value > 0) {
            positive = true;
        } else if (value < 0) {
            positive = false;
        }
    }

    public boolean getValidity() {
        return valid;
    }
    public boolean getPositivity() {
        return positive;
    }

    public double getValue() {
        return value;
    }
}
