/*
    A class for performing mathematical operations on complex numbers.
    Contains the following methods:
        ComplexNumber(double real, double imag) - Constructor
        add(ComplexNumber a, ComplexNumber b) - Adding numbers
        sub(ComplexNumber a, ComplexNumber b) - Subtracting numbers
        mul(ComplexNumber a, ComplexNumber b) - Multiplication of numbers
        div(ComplexNumber a, ComplexNumber b) - Dividing numbers
        pow(ComplexNumber num, int n) - Exponentiation of a number
 */
public class ComplexNumber {

    double real, imag;

    /*
        Constructor method
        Accepts two numbers of the double type - the real and imaginary part of a complex number
     */
    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /*
        A method for adding two complex numbers
        Accepts two ComplexNumber numbers.
        Returns a new object of type ComplexNumber - the result of adding two numbers
     */
    public static ComplexNumber add(ComplexNumber a, ComplexNumber b){
        return new ComplexNumber(a.real + b.real, a.imag + b.imag);
    }

    /*
        A method for subtracting two complex numbers
        Accepts two ComplexNumber numbers.
        Returns a new object of type ComplexNumber - the result of subtracting two numbers
     */
    public static ComplexNumber sub(ComplexNumber a, ComplexNumber b){
        return new ComplexNumber(a.real - b.real, a.imag - b.imag);
    }

    /*
        A method for multiplying two complex numbers
        Accepts two ComplexNumber numbers.
        Returns a new object of the ComplexNumber class - the result of multiplying two numbers
     */
    public static ComplexNumber mul(ComplexNumber a, ComplexNumber b){
        return new ComplexNumber(a.real * b.real - a.imag * b.imag, a.imag * b.real + a.real * b.imag);
    }

    /*
        A method for dividing two complex numbers
        Accepts two ComplexNumber numbers.
        Returns a new object of the ComplexNumber class - the result of dividing two numbers
     */
    public static ComplexNumber div(ComplexNumber a, ComplexNumber b){
        return new ComplexNumber((a.real * b.real + a.imag * b.imag) / (b.real * b.real + b.imag * b.imag),
                                (a.imag * b.real - a.real * b.imag) / (b.real * b.real + b.imag * b.imag));
    }

    /*
        A method for raising a complex number to a power
        Takes a complex number and the degree to which it should be raised.
        Returns a new number of type ComplexNumber - the result of exponentiation of the number
     */
    public static ComplexNumber pow(ComplexNumber num, int n){
        ComplexNumber res = new ComplexNumber(1, 0);
        for (int i = 0; i < n; i++){
            res = ComplexNumber.mul(res, num);
        }
        return res;
    }

    @Override
    public String toString(){
        return String.format("%f + %fj", this.real, this.imag);
    }


}
