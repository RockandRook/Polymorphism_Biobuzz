package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private CRServo intakeMotor1, intakeMotor2;
    public Intake(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        intakeMotor1 = hardwareMap.get(CRServo.class,"intake1");
        intakeMotor2 = hardwareMap.get(CRServo.class,"intake2");
    }

    public void startMotor(){
        intakeMotor1.setPower(1);
    }
    public void stopMotor(){
        intakeMotor2.setPower(0);
    }
}
