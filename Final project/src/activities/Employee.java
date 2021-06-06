/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activities;

public class Employee {

      public Employee(String _fName, String _lName, int _age, Gender _gender, State _state, Type _type) {
            this.fName = _fName.trim();
            this.lName = _lName.trim();
            this.age = _age;
            this.gender = _gender;
            this.state = _state;
            this.type = _type;
      }

      public int getAge() {
            return age;
      }

      public void setAge(int _age) {
            this.age = _age;
      }

      public Gender getGender() {
            return gender;
      }

      public void setGender(Gender _gender) {
            this.gender = _gender;
      }

      public State getState() {
            return state;
      }

      public void setState(State _state) {
            this.state = _state;
      }

      public Type getType() {
            return type;
      }

      public void setType(Type _type) {
            this.type = _type;
      }

      public String getFullName() {
            return fName + " " + lName;
      }

      public String getFName() {
            return fName;
      }

      public void setFName(String _fName) {
            this.fName = _fName;
      }

      public String getLName() {
            return lName;
      }

      public void setlName(String _lName) {
            this.lName = _lName;
      }

      @Override
      public String toString() {
            return this.getFName() + ","
                    + this.getLName() + ","
                    + this.getAge() + ","
                    + this.getGender() + ","
                    + this.getState() + ","
                    + this.getType();
      }

      public static enum State {
            SINGLE, MARRIED, DEVORCED, WIDOWER
      }

      public static enum Type {
            FULL_TIME, PART_TIME
      }

      public static enum Gender {
            MALE, FEMALE
      }
      private String fName;
      private String lName;
      private int age;
      private Gender gender;
      // married , single
      private State state;
      //  full time , part time
      private Type type;

      public double getSalary() {
            double salary = 2000;
            if ( this.getType() == Type.FULL_TIME ) {
                  salary = 3000;
            }
            if ( this.getState() == State.MARRIED ) {
                  salary += 500;
            }
            if ( this.getAge() > 50 ) {
                  salary += 500;
            } else if ( this.getAge() > 25 ) {
                  salary += 200;
            }
            return salary;
      }
}
