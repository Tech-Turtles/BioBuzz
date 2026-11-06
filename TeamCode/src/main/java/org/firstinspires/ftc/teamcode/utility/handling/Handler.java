package org.firstinspires.ftc.teamcode.utility.handling;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.opmode.autonomous.programs.ShootBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Handler {
    private static final File file = new File(AppUtil.FIRST_FOLDER, "position.txt");

    public static void writePosition(Pose2D position) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // position x
            writer.write(String.valueOf(position.getX(DistanceUnit.MM)));
            writer.newLine();
            // position y
            writer.write(String.valueOf(position.getY(DistanceUnit.MM)));
            writer.newLine();
            // heading
            writer.write(String.valueOf(position.getHeading(AngleUnit.RADIANS)));
            writer.newLine();
        }
    }

    public static Pose2D readPosition() throws  IOException {
        Pose2D position = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.RADIANS, 0);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value;
            // position x
            if ((value = reader.readLine()) != null) {
                position = new Pose2D(DistanceUnit.MM, Double.parseDouble(value), position.getY(DistanceUnit.MM), AngleUnit.RADIANS, position.getHeading(AngleUnit.RADIANS));
            }
            // position y
            if ((value = reader.readLine()) != null) {
                position = new Pose2D(DistanceUnit.MM, position.getX(DistanceUnit.MM), Double.parseDouble(value), AngleUnit.RADIANS, position.getHeading(AngleUnit.RADIANS));
            }
            // heading
            if ((value = reader.readLine()) != null) {
                position = new Pose2D(DistanceUnit.MM, position.getX(DistanceUnit.MM), position.getY(DistanceUnit.MM), AngleUnit.RADIANS, Double.parseDouble(value));
            }
        }
        return position;
    }
}
