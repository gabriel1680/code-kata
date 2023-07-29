import { fizzbuzz } from "fizz-buzz";

describe("FizzBuzz", () => {

    it.each([ 3, 6, 9, 12, 18 ])
        ("should return 'Fizz' when n is divisible by 3 (%s)", (n) => {
        expect(fizzbuzz(n)).toBe('Fizz');
    });

    it.each([ 5, 20, 25, 35 ])
        ("should return 'Buzz' when n is divisible by 5 (%s)", (n) => {
        expect(fizzbuzz(n)).toBe('Buzz');
    });

    it.each([ 15, 30, 45 ])
        ("should return 'FizzBuzz' when n is divisible by 3 and 5 (%s)", (n) => {
        expect(fizzbuzz(n)).toBe('FizzBuzz');
    });

    it.each([ 1, 2, 4, 7, 8 ])
        ("should return the number 'n' when n is neither divisible by 3 nor 5 (%s)", (n) => {
        expect(fizzbuzz(n)).toBe(n);
    });

});

