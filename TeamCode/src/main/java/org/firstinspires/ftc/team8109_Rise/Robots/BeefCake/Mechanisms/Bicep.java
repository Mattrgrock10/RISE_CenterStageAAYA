package org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.BeefWrist;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Hardware.Arms.ServoArm;
import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.BeefySlides;

public class Bicep extends ServoArm {

    static String[] name = {"armLeft", "armRight"};
    Gamepad gamepad1;
    Telemetry telemetry;

    boolean toggle1 = true;
    boolean toggle2 = false;

    boolean triggerToggle1 = true;
    boolean triggerToggle2 = false;

    boolean lastToggleX = false;
    boolean lastTriggerLeft = false;


    enum BicepStates{
        HOME,
        FIRST_LINE,
        THIRD_LINE,
        MANUAL
    }

    BicepStates bicepState;
    public Bicep(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap) {
        super(ServoArmType.DOUBLE_SERVO, name, hardwareMap);

        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;
        bicepState = BicepStates.HOME;
    }

    public void bicepStates(){
        switch (bicepState){
            case HOME:
                setAngle(0);
                break;
            case FIRST_LINE:
                setAngle(270);
                break;

            case THIRD_LINE:
                setAngle(300);
                break;

            case MANUAL:

                break;
        }
    }


    public void togglePosition(){
        bicepStates();
        switch (bicepState){
            case HOME:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    bicepState = BicepStates.FIRST_LINE;
                }

                break;

            case FIRST_LINE:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle2){
                    toggle2 = false;
                    toggle1 = true;

                    bicepState = BicepStates.HOME;
                }

                break;
        }
        lastToggleX = gamepad1.x;
        lastTriggerLeft = gamepad1.left_bumper;
    }

    public void slidesToggle(BeefySlides.SlidesState slidesState, BeefWrist.WristPosition wristPosition){
        bicepStates();
        switch (slidesState){
            case HOME:
                wristPosition = BeefWrist.WristPosition.MOVEMENT_POSITION;
                bicepState = BicepStates.HOME;
                wristPosition = BeefWrist.WristPosition.INTAKE_POSITION;
                break;

            case FIRST_LINE:
                wristPosition = BeefWrist.WristPosition.MOVEMENT_POSITION;
                bicepState = BicepStates.FIRST_LINE;
                wristPosition = BeefWrist.WristPosition.OUTTAKE_POSITION;
                break;
            case THIRD_LINE:
                wristPosition = BeefWrist.WristPosition.MOVEMENT_POSITION;
                bicepState = BicepStates.THIRD_LINE;
                wristPosition = BeefWrist.WristPosition.OUTTAKE_POSITION;
                break;
            case MANUAL:
                bicepState = BicepStates.FIRST_LINE;
                break;
        }
    }

}