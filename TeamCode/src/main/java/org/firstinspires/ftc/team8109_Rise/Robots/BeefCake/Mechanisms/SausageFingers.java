package org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SausageFingers {
    ClawLeft clawLeft;
    ClawRight clawRight;

    enum ClawState {
        OPEN,
        LEFT,
        RIGHT,
        CLOSED
    }
    ClawState clawState;
    Gamepad gamepad1;
    public SausageFingers(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap){
        clawLeft = new ClawLeft(gamepad1, telemetry, hardwareMap);
        clawRight = new ClawRight(gamepad1, telemetry, hardwareMap);

        this.gamepad1 = gamepad1;

        clawState = ClawState.OPEN;
    }

    public void toggleClaw(){
        clawLeft.toggleClaw();
        clawRight.toggleClaw();
    }

    public void setClawOpen(){
        clawLeft.setAngle(ClawLeft.openPosition);
        clawRight.setAngle(ClawRight.openPosition);
    }

    public void setClawClosed(){
        clawLeft.setAngle(ClawLeft.closedPosition);
        clawRight.setAngle(ClawRight.closedPosition);
    }

    public void ClawPosition() {

        switch(clawState) {
            case OPEN:
                clawLeft.setAngle(clawLeft.openPosition);
                clawRight.setAngle(clawRight.openPosition);

                if (gamepad1.left_bumper)
                    clawState = ClawState.LEFT;
                else if (gamepad1.right_bumper)
                    clawState = ClawState.RIGHT;
                else if (gamepad1.y)
                    clawState = ClawState.CLOSED;
                break;

            case LEFT:
                clawLeft.setAngle(clawLeft.openPosition);
                clawRight.setAngle(clawRight.closedPosition);


                if (gamepad1.left_bumper)
                    clawState = ClawState.CLOSED;
                else if (gamepad1.right_bumper)
                    clawState = ClawState.OPEN;
                else if (gamepad1.y)
                    clawState = ClawState.OPEN;
                break;

            case RIGHT:
                clawLeft.setAngle(clawLeft.closedPosition);
                clawRight.setAngle(clawRight.openPosition);

                if (gamepad1.left_bumper)
                    clawState = ClawState.OPEN;
                else if (gamepad1.right_bumper)
                    clawState = ClawState.CLOSED;
                else if (gamepad1.y)
                    clawState = ClawState.OPEN;
                break;

            case CLOSED:
                clawLeft.setAngle(clawLeft.closedPosition);
                clawRight.setAngle(clawRight.closedPosition);

                if (gamepad1.left_bumper)
                    clawState = ClawState.LEFT;
                else if (gamepad1.right_bumper)
                    clawState = ClawState.RIGHT;
                else if (gamepad1.y)
                    clawState = ClawState.OPEN;
                break;
        }
    }
}
