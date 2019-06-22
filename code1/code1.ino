#include <Wire.h>
#include <SoftwareSerial.h>


// defines pins numbers
const int trigPin = 9;
const int echoPin = 10;
// defines variables

bool DoBeep = false;
bool reset_device = false;
float average = 0;
bool alarm_on = true;
bool device_placed = false;
int state = -1;
int prev_state = -1;
float array[5];


long duration;
int distance;


void ResetDevice(float & average, bool & reset_device, bool &alarm_on, bool &device_placed, int & state, int &prev_state){
    average = 0;
    reset_device = false;
    alarm_on = true;
    device_placed = false;
    state = -1;
    prev_state = -1;
}


void setup() {
pinMode(trigPin, OUTPUT); 
pinMode(echoPin, INPUT);
pinMode(6, OUTPUT);
pinMode(7, OUTPUT); 
Serial.begin(9600); 
}


void beep (int pin1, int pin2){
  digitalWrite(pin1, HIGH);
  digitalWrite(pin2, HIGH);
  delay(1000);
  digitalWrite(pin1, LOW);
  digitalWrite(pin2, LOW);
  delay(1000);
}

float GetDistance (int trigPin, int echoPin){
  // Clears the trigPin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  // Reads the echoPin, returns the sound wave travel time in microseconds
  duration = pulseIn(echoPin, HIGH);
  // Calculating the distance
  distance= duration*0.034/2;
  // Prints the distance on the Serial Monitor
  Serial.print("Distance: ");
  Serial.println(distance);
  return distance;
}




  

void loop() {
        while (!reset_device) {
            for (int i = 0; i < 10; i++) {
                 array[i] = (float)GetDistance(trigPin,echoPin);
                 Serial.println(array[i]);
            }
            average = (array[0]+array[1]+ array[2]+ array[3]+array[4]+array[5]+array[6]+ array[7]+ array[8]+array[9])/10;
            Serial.println(average);
            if (average<30){
                state = 1;
                if (prev_state!= state) {
                    Serial.println("device place");
                }
                device_placed = true;
                while (device_placed){
                    for (int i = 0; i < 10; i++) {
                        array[i] = GetDistance(trigPin,echoPin);
                    }
                    average = (array[0]+array[1]+ array[2]+ array[3]+array[4]+array[5]+array[6]+ array[7]+ array[8]+array[9])/10;
                    Serial.println(average);
                    if (average >25){
                        state = 2;
                        if (prev_state!=state) {
                            Serial.println("taken");
                        }
                        device_placed = false;
                       // while (!reset_device){
                            //TODO: stay in taken state
                       // }
                    }
                delay (1000);
                }
            }
            else{
                state = 0;
                if (prev_state!=state) {
                    Serial.println("empty");
                }
            }
            prev_state = state;
            delay (1000);
        }
    ResetDevice(average, reset_device, alarm_on, device_placed, state, prev_state);


}
