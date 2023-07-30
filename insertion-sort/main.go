package main

func InsertionSort(n []int) []int {
    var key int
    for i := 1; i < len(n); i++ {
        j := i - 1
        key = n[i]
        for j >= 0 && n[j] > key {
            n[j + 1] = n[j]
            j -= 1
        }
        n[j + 1] = key
    }
    return n
}

