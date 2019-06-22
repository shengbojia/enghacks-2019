
char incomingByte = 'a';
void setup() {
  // put your setup code here, to run once:
    pinMode(7, OUTPUT);
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
  if (Serial.available() > 0) {
    // read the incoming byte:
      incomingByte = Serial.read();
    if (incomingByte == 'b'){
       digitalWrite(7, HIGH);
     }
    else if (incomingByte =='c'){
      digitalWrite(7, LOW);
    }
}
}
