import { fizzbuzz } from "fizz-buzz";

describe("FizzBuzz", () => {
    it("should return 'Fizz' when n is divisible by 3", () => {
        expect(fizzbuzz(3)).toBe('Fizz');
    });

    it("should return 'Buzz' when n is divisible by 5", () => {
        expect(fizzbuzz(5)).toBe('Buzz');
    });

    it("should return 'FizzBuzz' when n is divisible by 3 and 5", () => {
        expect(fizzbuzz(15)).toBe('FizzBuzz');
    });

    it("should return the number 'n' when n is neither divisible by 3 nor 5", () => {
        expect(fizzbuzz(1)).toBe(1);
    });
});
