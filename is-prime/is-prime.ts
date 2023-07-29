export function isPrime(n: number): boolean {
    if (n % 2 === 0 && n !== 2) {
        return false;
    }
    if (n % 3 === 0 && n !== 3) {
        return false;
    }
    return true;
}

