import { isPrime } from "is-prime";

describe("isPrime", () => {

    it.each([1, 2, 3, 7, 11, 13, 17, 19, 23, 29, 31])
        ("should return true for prime numbers such as (%s)", (n) => {
        expect(isPrime(n)).toBeTruthy();
    });

    it.each([0, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24])
        ("should return false for not prime numbers such as (%s)", (n) => {
        expect(isPrime(n)).toBeFalsy();
    });

});
