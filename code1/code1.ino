
#include <Wire.h>
#include <SoftwareSerial.h>


// defines pins numbers
const int trigPin = 9;
const int echoPin = 10;
const int buzzPin = 6;
const int SENSORPIN = 7;
const int LEDPIN = 13;


const char EMPTY = 'E';
const char DEVICE_PLACED = 'D';
const char TAKEN = 'T';


const char ALARM_ON = 'A';
const char ALARM_OFF = 'B';
const char RESET = 'R';


// defines variables

bool DoBeep = false;
bool reset_device = false;
float average = 0;
bool alarm_on = true;
bool device_placed = false;
int state = -1;
int prev_state = -1;
float array[10];
long duration;
int distance;
//--- NEED TO RESET THESE ONES
bool InfUnbroken = false;
int sensorState = 0;
int lastState = 0;
char incomingByte = 'a';
int valid_input = 0;






void ResetDevice(float & average, bool & reset_device, bool &alarm_on, bool &device_placed, int & state, int &prev_state, bool &InfUnbroken,int &sensorState, int &lastState, char &incomingByte,int &valid_input ) {
  average = 0;
  reset_device = false;
  alarm_on = true;
  device_placed = false;
  state = -1;
  prev_state = -1;
  InfUnbroken = false;
  sensorState = 0;
  lastState = 0;
  incomingByte = -1;
  valid_input = 0;  
}

bool GetInfStatus(int SENSORPIN, int LEDPIN) {
  // read the state of the pushbutton value:
  sensorState = digitalRead(SENSORPIN);

  // check if the sensor beam is broken
  // if it is, the sensorState is LOW:
  if (sensorState == LOW) {
    // turn LED on:
    digitalWrite(LEDPIN, HIGH);
  }
  else {
    // turn LED off:
    digitalWrite(LEDPIN, LOW);
  }

  if (sensorState && !lastState) {
    //Serial.println("Unbroken");
    return true;
  }
  if (!sensorState && lastState) {
    //Serial.println("Broken");
    return false;
  }
  lastState = sensorState;
}



void setup() {
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  Serial.begin(9600);

  // initialize the LED pin as an output:
  pinMode(LEDPIN, OUTPUT);      
  // initialize the sensor pin as an input:
  pinMode(SENSORPIN, INPUT);     
  digitalWrite(SENSORPIN, HIGH); // turn on the pullup
  
}


void beep (int pin1, int pin2) {
  digitalWrite(pin1, HIGH);
  digitalWrite(pin2, HIGH);
  delay(1000);
  digitalWrite(pin1, LOW);
  digitalWrite(pin2, LOW);
  delay(1000);
}

float GetDistance (int trigPin, int echoPin) {
  // Clears the trigPin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  // Reads the echoPin, returns the sound wave travel time in microseconds
  duration = pulseIn(echoPin, HIGH);
  // Calculating the distance
  distance = duration * 0.034 / 2;
  // Prints the distance on the Serial Monitor
  //Serial.print("Distance: ");
  ////Serial.println(distance);
  return distance;
}






void loop() {
  while (!reset_device) {
    if (Serial.available() > 0) {
                // read the incoming byte:
                incomingByte = Serial.read();
                if (incomingByte == ALARM_ON){
                  alarm_on = true;
                }
                else if (incomingByte == ALARM_OFF){
                  alarm_on = false;
                }
                else if (incomingByte == RESET){
                  reset_device = true;
                }
    }
    
    for (int i = 0; i < 10; i++) {
      array[i] = GetDistance(trigPin, echoPin);
      if (array[i] != 0) {
        valid_input++;
      }
     // //Serial.println(array[i]);
    }
    if (valid_input == 0){
       valid_input =1; //so we don't divide by 0
    }
    average = (array[0] + array[1] + array[2] + array[3] + array[4] + array[5] + array[6] + array[7] + array[8] + array[9]) / valid_input;
    valid_input = 0;
    InfUnbroken  = GetInfStatus(SENSORPIN, LEDPIN);
    //Serial.println(average);
    if (average <= 30 && !InfUnbroken) {
      state = 1;
      if (prev_state != state) {
        //Serial.println("device place");
        Serial.write(DEVICE_PLACED);
      }
      device_placed = true;
      while (device_placed) {
         if (Serial.available() > 0) {
                // read the incoming byte:
                incomingByte = Serial.read();
                if (incomingByte == ALARM_ON){
                  alarm_on = true;
                }
                else if (incomingByte == ALARM_OFF){
                  alarm_on = false;
                }
                else if (incomingByte == RESET){
                  reset_device = true;
                }
    }
        for (int i = 0; i < 10; i++) {
          array[i] = GetDistance(trigPin, echoPin);
          if (array[i] != 0) {
            valid_input++;
          }
         // //Serial.println(array[i]);
        }
        if (valid_input == 0){
          valid_input =1; //so we don't divide by 0
        }
        average = (array[0] + array[1] + array[2] + array[3] + array[4] + array[5] + array[6] + array[7] + array[8] + array[9]) / valid_input;
        valid_input = 0;
        InfUnbroken  = GetInfStatus(SENSORPIN, LEDPIN);
        //Serial.println(average);
        if (average > 30 && InfUnbroken) {
          state = 2;
          if (prev_state != state) {
            //Serial.println("taken");
            Serial.write(TAKEN);
          }
          device_placed = false;
          while (!reset_device){
        if (Serial.available() > 0) {
                // read the incoming byte:
                incomingByte = Serial.read();
                if (incomingByte == ALARM_ON){
                  alarm_on = true;
                }
                else if (incomingByte == ALARM_OFF){
                  alarm_on = false;
                }
                else if (incomingByte == RESET){
                  reset_device = true;
                }
    }
          if (alarm_on){
            beep (5,6);
          }
          else{
            break;
          }
          //TODO: stay in taken state
          delay (500);
          }
        }
        delay (1000);
      }
    }
    else {
      state = 0;
      if (prev_state != state) {
        //Serial.println("empty");
        //Serial.write(EMPTY);
      }
    }
    prev_state = state;
    delay (1000);
  }
  ResetDevice(average, reset_device, alarm_on, device_placed, state, prev_state, InfUnbroken,sensorState, lastState, incomingByte,valid_input );

}
