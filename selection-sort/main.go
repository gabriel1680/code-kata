package main

func SelectionSort(n []int) ([]int) {
    if len(n) == 0 {
        return n
    }
    for i := 0; i < len(n); i++ {
        if i == 0 {
            if n[i] > n[i + 1] {
                aux := n[i]
                n[i] = n[i + 1]
                n[i + 1] = aux
            }
            continue
        }
        if n[i - 1] > n[i] {
            aux := n[i - 1]
            n[i - 1] = n[i]
            n[i] = aux
        }
    }
    return n
}

