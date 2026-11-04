package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utility.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.utility.commands.IntakeCommand;

/**
 * Class for handling controller input. You can see which buttons do what all in one place.
 * @author Brantley
 */
public class ControllerInput {

    // Declare gamepads
    public Gamepad gamepad1;
    public Gamepad gamepad2;

    /**
     * @author Brantley
     * @param gamepad1 the first gamepad for input
     * @param gamepad2 the second gamepad for input
     */
    public ControllerInput(Gamepad gamepad1, Gamepad gamepad2) {
        // Assign gamepads
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    // drive input

    /**
     * Right trigger is slow button
     * @author Brantley
     * @param drivepad the gamepad involved with collecting this driving info
     * @return a list with three values: forward, strafe, and twist in that order. Use forward = getDrive().get(0)...
     */

    public DriveCommand getDrive(Gamepad drivepad) {

        // define speed modifier
        double speed = 1.0;
        if (drivepad.right_trigger_pressed) {
            speed = 0.25;
        }

        // Get drive values from the gamepad decidedly used for driving
        double forward = drivepad.left_stick_y * speed;
        double strafe = drivepad.left_stick_x * speed;
        double twist = -drivepad.right_stick_x * speed;

        // Create DriveCommand to store these drive values
        DriveCommand command = new DriveCommand(forward, strafe, twist, speed);
        // Give back the list of drive values
        return (command);
    }

    // intake input

    /**
     * @param intakepad is the gamepad that provides intake control data here
     * @return the y of the left stick on the intakepad. Up means take in, down means outtake.
     * @author Brantley
     */

    public IntakeCommand getIntake(Gamepad intakepad) {
        return (new IntakeCommand(intakepad.left_stick_y));
    }

    /**
     * @param intakepad the gamepad that handles idle intaking
     * @return the absolute value of the left stick y. Useful for keeping balls in and taking them in efficiently
     * @author Brantley
     */

    public boolean getShooter(Gamepad shooterpad) {
        return (shooterpad.left_trigger_pressed);
    }

    // game state input
    public boolean getEndgame() {
        // establish booleans
        boolean endgameForGamepad1 = gamepad1.left_stick_button && gamepad1.right_stick_button;
        boolean endgameForGamepad2 = gamepad2.left_stick_button && gamepad2.right_stick_button;
        // now return final test
        return (endgameForGamepad1 || endgameForGamepad2);
    }

    // pinpoint input
    public boolean getResetPosition(Gamepad resetpad) {
        // return the condition
        return (resetpad.dpadUpWasPressed());
    }

    // get red alliance
    public boolean getRedAlliance(Gamepad alliancepad) {
        // return the condition
        return (alliancepad.a);
    }

    public boolean getBlueALliance(Gamepad alliancepad) {
        // return the conditoin
        return (alliancepad.b);
    }

    // buffer function
    public static boolean buffer(double input) {
        // buffer the input if it is greater or less than 0.1 or -0.1
        return (Math.abs(input) >= 0.1);
    }
}
