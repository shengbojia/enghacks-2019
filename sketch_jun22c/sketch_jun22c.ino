#include <String.h>

String incomingByte = "not recieved";


void setup() {

  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
 if (Serial.available() > 0) {
                // read the incoming byte:
                incomingByte = Serial.read();
    }

 // Serial.println(incomingByte);
  Serial.write('a');
  delay (1000);
}
