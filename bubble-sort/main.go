package main

func BubbleSort(n []int) []int {
    for i := 0; i < len(n) - 1; i++ {
        for j := 0; j < len(n) - i - 1; j++ {
            if n[j] > n[j + 1] {
                swap(&n, j, j + 1)
            }
        }
    }
    return n
}

func swap(n *[]int, left int, right int) {
    aux := (*n)[left]
    (*n)[left] = (*n)[right]
    (*n)[right] = aux
}

